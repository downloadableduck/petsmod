package com.jeff.pets.network;

import com.google.gson.Gson;
import com.jeff.pets.Utils;
import com.jeff.pets.client.network.TeleportPetPayload;
import com.jeff.pets.mob.AbstractPet;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.network.PetsNetworked.LOGGER;

public class NetworkManager {
    private static final String API_KEY = "YhoXQw.GzPPLQ:kcT0BcVYJUv8njmENa0gI6G3dp4IOJbKrVSJDVPiaoA";

    private static final NetworkManager INSTANCE = new NetworkManager();

    private AblyRealtime ably;
    private Channel channel;

    private final Gson GSON = new Gson();

    public static Map<UUID, AbstractPet> map = new ConcurrentHashMap<>();

    public static NetworkManager get() {
        return INSTANCE;
    }

    public void connect(String ip) {
        LOGGER.info("Connected");
        try {
            ably = new AblyRealtime(API_KEY);
            String channelName = "server:" + ip.replaceAll("[^a-zA-Z0-9]", "_");
            LOGGER.info(channelName);
            channel = ably.channels.get(channelName);

            channel.subscribe("pets_general", (message) -> {
                String json = (String) message.data;
                LOGGER.info("Received packet: {}", message.data);
                GeneralPetsPayload payload = GSON.fromJson(json, GeneralPetsPayload.class);
                Minecraft.getInstance().execute(() -> {
                    AbstractPet pet = Utils.getPet(payload.petSpecies);
                    Player player = Minecraft.getInstance().level.getPlayerByUUID(payload.playerUUID);
                    if (player.getUUID().equals(Minecraft.getInstance().player.getUUID())) return;
                    AbstractPet entity = map.get(player.getUUID());
                    if (entity != null) {
                        Utils.despawnEntity(entity);
                    }
                    if (player.getUUID().equals(payload.playerUUID)) {
                        Utils.summonPet(pet, payload.petName, player);
                        pet.petSkin = payload.petSkin;
                        map.put(player.getUUID(), pet);
                    }
                });
            });
            channel.subscribe("pets_skin_change", (message) -> {
                String json = (String) message.data;
                ChangePetSkinPayload payload = GSON.fromJson(json, ChangePetSkinPayload.class);
                Minecraft.getInstance().execute(() -> {
                    AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                    pet.petSkin = payload.petSkin;
                    LOGGER.info("Recived pet skin packet: {}", message.data + " pet skin is now: " + pet.petSkin);
                });
            });
            channel.subscribe("pets_name_change", message -> {
                String json = (String) message.data;
                ChangePetNamePayload payload = GSON.fromJson(json, ChangePetNamePayload.class);
                Minecraft.getInstance().execute(() -> {
                    AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                    pet.setName(payload.petName);
                });
            });
            channel.subscribe("teleport_pet", message -> {
                String json = (String) message.data;
                TeleportPetPayload payload = GSON.fromJson(json, TeleportPetPayload.class);

                Minecraft.getInstance().execute(() -> {
                    AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                    pet.teleportTo(payload.x, payload.y, payload.z);
                });
            });
            channel.subscribe("toggle_pet", message -> {
                String json = (String) message.data;
                TogglePetPayload payload = GSON.fromJson(json, TogglePetPayload.class);
                Minecraft.getInstance().execute(() -> {
                    AbstractPet pet = map.get(UUID.fromString(payload.uuid));
                    if (payload.on) {
                        Player player = Minecraft.getInstance().level.getPlayerByUUID(UUID.fromString(payload.uuid));
                        if (player.getUUID().equals(Minecraft.getInstance().player.getUUID())) return;
                        if (player.getUUID().equals(UUID.fromString(payload.uuid))) {
                            Utils.summonPet(pet, payload.petName, player);
                            map.put(player.getUUID(), pet);
                        }
                    } else {
                        Utils.despawnEntity(pet);
                    }
                });
            });

        } catch (AblyException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        LOGGER.info("Disconnected.");
        ably.close();
    }

    public void broadcastGeneral(String playerUuid, boolean petOn, String petSpecies, String petName, String petSkin) {
        if (channel != null) {
            GeneralPetsPayload payload = new GeneralPetsPayload(playerUuid, petOn, petSpecies, petName, petSkin);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("pets_general", jsonPayload);
                LOGGER.info("Sent general packet: {}", jsonPayload);
            } catch (AblyException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastChangePetSkin(String uuid, String petSkin) {
        if (channel != null) {
            ChangePetSkinPayload payload = new ChangePetSkinPayload(uuid, petSkin);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("pets_skin_change", jsonPayload);
                LOGGER.info("Sent pet skin packet: {}", jsonPayload);
            } catch (AblyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void broadcastChangePetName(String uuid, String petName) {
        if (channel != null) {
            ChangePetNamePayload payload = new ChangePetNamePayload(uuid, petName);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("pets_name_change", jsonPayload);
                LOGGER.info("Sent pet name packet: {}", jsonPayload);
            } catch (AblyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void broadcastTeleportPet(String uuid, double x, double y, double z) {
        if (channel != null) {
            TeleportPetPayload payload = new TeleportPetPayload(uuid, x, y, z);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("teleport_pet", jsonPayload);
                LOGGER.info("Sent pet teleport packet: {}", jsonPayload);
            } catch (AblyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void broadcastTogglePet(String uuid, String petName, boolean petOn) {
        if (channel != null) {
            TogglePetPayload payload = new TogglePetPayload(uuid, petOn, petName);
            String jsonPayload = GSON.toJson(payload);

            try {
                channel.publish("toggle_pet", jsonPayload);
                LOGGER.info("Sent toggle pet packet: {}", jsonPayload);
            } catch (AblyException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
