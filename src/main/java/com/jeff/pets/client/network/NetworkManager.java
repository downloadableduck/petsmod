package com.jeff.pets.client.network;

import com.google.gson.Gson;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.network.payload.*;
import com.jeff.pets.mob.AbstractPet;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ClientOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.network.PetsNetworked.LOGGER;

public class NetworkManager {

    private static final NetworkManager INSTANCE = new NetworkManager();
    public static Map<UUID, AbstractPet> map = new ConcurrentHashMap<>();
    public static List<UUID> allPlayers = new ArrayList<>();
    private final Gson GSON = new Gson();
    private AblyRealtime ably;
    private Channel channel;
    private ClientOptions options;

    public static NetworkManager get() {
        return INSTANCE;
    }

    public void connect(String ip) {
        this.log("Connected");
        try {
            ably = new AblyRealtime(this.options);
            String channelName = "petsmod:server:" + this.getIp(ip);
            this.log(channelName);
            channel = ably.channels.get(channelName);

            channel.subscribe("requestPetState", (message) -> {
                try {
                    this.log("Received a request for current pet state");
                    Minecraft.getInstance().execute(() -> {
                        this.broadcastGeneral(Minecraft.getInstance().player.getStringUUID(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
                    });
                } catch (Throwable t) {
                    this.log(t);
                }
            });

            channel.subscribe("pets_general", (message) -> {
                try {
                    String json = (String) message.data;
                    GeneralPetsPayload payload = GSON.fromJson(json, GeneralPetsPayload.class);
                    if (this.isMe(payload.uuid)) return;
                    this.log("Received packet: {}", message.data);
                    Minecraft.getInstance().execute(() -> {
                        AbstractPet pet = Utils.getPet(payload.petSpecies);
                        Player player = Minecraft.getInstance().level.getPlayerByUUID(payload.playerUUID);
                        AbstractPet entity = map.get(player.getUUID());
                        if (entity != null) {
                            Utils.despawnEntity(entity);
                        }
                        if (player.getUUID().equals(payload.playerUUID)) {
                            Utils.summonPet(pet, payload.petName, player);
                            pet.petSkin = payload.petSkin;
                            pet.setBaby(payload.isBaby);
                            this.put(player.getUUID(), pet);
                        }
                    });
                } catch (Exception e) {
                    this.log(e);
                }
            });
            channel.subscribe("pets_skin_change", (message) -> {
                try {
                    String json = (String) message.data;
                    ChangePetSkinPayload payload = GSON.fromJson(json, ChangePetSkinPayload.class);
                    if (this.isMe(payload.uuid)) return;
                    Minecraft.getInstance().execute(() -> {
                        AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                        if (!Objects.equals(payload.petSkin, "baby") && !Objects.equals(payload.petSkin, "adult")) {
                            pet.petSkin = payload.petSkin;
                            this.log("Received pet skin packet: {}", message.data + " pet skin is now: " + pet.petSkin);
                        } else {
                            pet.setBaby(Objects.equals(payload.petSkin, "baby"));
                        }
                    });
                } catch (Exception e) {
                    this.log(e);
                }
            });
            channel.subscribe("pets_name_change", message -> {
                try {
                    String json = (String) message.data;
                    ChangePetNamePayload payload = GSON.fromJson(json, ChangePetNamePayload.class);
                    if (this.isMe(payload.uuid)) return;
                    Minecraft.getInstance().execute(() -> {
                        AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                        pet.setName(payload.petName);
                    });
                } catch (Exception e) {
                    this.log(e);
                }
            });
            channel.subscribe("teleport_pet", message -> {
                try {
                    String json = (String) message.data;
                    TeleportPetPayload payload = GSON.fromJson(json, TeleportPetPayload.class);
                    if (this.isMe(payload.uuid)) return;
                    Minecraft.getInstance().execute(() -> {
                        AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                        pet.teleportTo(payload.x, payload.y, payload.z);
                    });
                } catch (Exception e) {
                    this.log(e);
                }
            });
            channel.subscribe("toggle_pet", message -> {
                try {
                    String json = (String) message.data;
                    TogglePetPayload payload = GSON.fromJson(json, TogglePetPayload.class);
                    if (this.isMe(payload.uuid)) return;
                    this.log("Received toggle pet payload: {}", payload);
                    Minecraft.getInstance().execute(() -> {
                        AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                        if (payload.on) {
                            Player player = Minecraft.getInstance().level.getPlayerByUUID(UUID.fromString(payload.uuid));
                            if (player.getUUID().equals(Minecraft.getInstance().player.getUUID())) return;
                            if (player.getUUID().equals(UUID.fromString(payload.uuid))) {
                                Utils.summonPet(pet, payload.petName, player);
                                this.put(player.getUUID(), pet);
                            }
                        } else {
                            Utils.despawnEntity(pet);
                        }
                    });
                } catch (Exception e) {
                    this.log(e);
                }
            });
            channel.subscribe("toggle_baby", message -> {
                try {
                    String json = (String) message.data;
                    ToggleBabyPayload payload = GSON.fromJson(json, ToggleBabyPayload.class);
                    Player player = Minecraft.getInstance().level.getPlayerByUUID(UUID.fromString(payload.uuid));
                    if (this.isMe(payload.uuid)) return;
                    this.log("Received baby toggle payload: {}", payload);
                    AbstractPet pet = map.get(player);
                    pet.setBaby(payload.isBaby);
                } catch (Exception e) {
                    this.log(e);
                }
            });

            channel.subscribe("put_on_head", message -> {
                try {
                    String json = (String) message.data;
                    PutPetOnHeadPayload payload = GSON.fromJson(json, PutPetOnHeadPayload.class);
                    Player player = Minecraft.getInstance().level.getPlayerByUUID(UUID.fromString(payload.uuid));
                    if (this.isMe(payload.uuid)) return;
                    this.log("Received put on head packet: {}", payload);
                    AbstractPet pet = map.get(player);
                    if (payload.onHead) {
                        pet.startRiding(player);
                    } else {
                        pet.stopRiding();
                    }
                } catch (Exception e) {
                    this.log(e);
                }
            });

            channel.subscribe("bye", message -> {
                try {
                    String uuid = (String) message.data;
                    this.log("Received goodbye packet from: " + uuid);
                    Minecraft.getInstance().execute(() -> {
                        AbstractPet pet = map.get(UUID.fromString(uuid));
                        pet.discard();
                    });
                } catch (Exception e) {
                    this.log(e);
                }
            });

            this.requestPetState();
        } catch (AblyException e) {
            this.log(e.getMessage());
        }
    }

    public void disconnect() {
        this.log("Disconnected.");
        ably.close();
    }

    public void broadcastGeneral(String playerUuid,
                                 boolean petOn, String petSpecies, String petName,
                                 String petSkin, boolean isBaby) {
        if (channel != null) {
            GeneralPetsPayload payload = new GeneralPetsPayload(playerUuid, petOn, petSpecies, petName, petSkin, isBaby);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("pets_general", jsonPayload);
                this.log("Sent general packet: {}", jsonPayload);
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }

    public void broadcastChangePetSkin(String uuid, String petSkin) {
        if (channel != null) {
            ChangePetSkinPayload payload = new ChangePetSkinPayload(uuid, petSkin);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("pets_skin_change", jsonPayload);
                this.log("Sent pet skin packet: {}", jsonPayload);
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }

    public void broadcastChangePetName(String uuid, String petName) {
        if (channel != null) {
            ChangePetNamePayload payload = new ChangePetNamePayload(uuid, petName);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("pets_name_change", jsonPayload);
                this.log("Sent pet name packet: {}", jsonPayload);
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }

    public void broadcastTeleportPet(String uuid, double x, double y, double z) {
        if (channel != null) {
            TeleportPetPayload payload = new TeleportPetPayload(uuid, x, y, z);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("teleport_pet", jsonPayload);
                this.log("Sent pet teleport packet: {}", jsonPayload);
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }

    public void broadcastTogglePet(String uuid, String petName, boolean petOn) {
        if (channel != null) {
            TogglePetPayload payload = new TogglePetPayload(uuid, petOn, petName);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("toggle_pet", jsonPayload);
                this.log("Sent toggle pet packet: {}", jsonPayload);
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }
    
    public void put(UUID uuid, AbstractPet pet) {
        map.put(uuid, pet);
        allPlayers.add(uuid);
    }

    public NetworkManager() {
        this.options = new ClientOptions();
        this.options.authUrl = "https://petsmod.downloadableduck.workers.dev";
        this.options.echoMessages = false;
        this.options.logLevel = 0;
    }

    private String getIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return "singleplayer";
        }

        String address = ip.split(":")[0].toLowerCase().trim();

        Pattern pattern = Pattern.compile("([^.]+?\\.(?:co\\.[a-z]{2}|[a-z]{2,}))(?::\\d+)?$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(address);

        if (matcher.find()) {
            address = matcher.group(1);
        }
        return address.replaceAll("[^a-zA-Z0-9]", "_");
    }

    public void requestPetState() {
        if (channel != null) {
            try {
                channel.publish("requestPetState", "string");
                this.log("Sent a request for the pet state.");
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }

    public void broadcastToggleBaby(String uuid, boolean isBaby) {
        if (channel != null) {
            try {
                channel.publish("toggle_baby", new ToggleBabyPayload(uuid, isBaby));
            } catch (AblyException e) {
                this.log(e.getMessage());
            }
        }
    }

    public void broadcastHeadPayload(String uuid, boolean onHead) {
        if (channel != null) {
            try {
                channel.publish("put_on_head", new PutPetOnHeadPayload(uuid, onHead));
            } catch (AblyException e) {
                this.log(e.getMessage());;
            }
        }
    }

    public void sayByeBye() {
        if (channel != null) {
            try {
                channel.publish("bye", Minecraft.getInstance().player.getStringUUID());
            } catch (Exception e) {
                this.log(e.getMessage());
            }
        }
    }

    public boolean isMe(UUID uuid) {
        try {
            return Minecraft.getInstance().player.getUUID().equals(uuid);
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean isMe(String uuid) {
        return this.isMe(UUID.fromString(uuid));
    }
    
    public void log(String string, Object ... optionals) {
        if (false) {
            String message2 = string;
            for (Object arg : optionals) {
                message2 = message2.replaceFirst("\\{\\}", String.valueOf(arg));
            }
            LOGGER.info(message2);
        }
    }

    public void log(Throwable e) {
        this.log("", e);
    }
}
