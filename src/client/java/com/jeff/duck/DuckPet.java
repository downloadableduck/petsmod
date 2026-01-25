package com.jeff.duck;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.jeff.duck.DuckInitializer.DUCK;

public class DuckPet implements ClientModInitializer {

    public static final List<Entity> summonedEntity = new ArrayList<>();
    private static final SuggestionProvider<SharedSuggestionProvider> DUCK_TYPES = (context, builder) ->
            SharedSuggestionProvider.suggest(new String[]{
                    "pekin",
                    "mallard",
            }, builder);
    private static final SuggestionProvider<SharedSuggestionProvider> ON_OFF = (context, builder) ->
            SharedSuggestionProvider.suggest(new String[]{
                    "off",
                    "on",
            }, builder);
    public static DuckSavedData CONFIG;
    public static int duckSkin;
    public Duck duck;
    int i;

    public DuckPet() {
    }

    public void onInitializeClient() {
        AutoConfig.register(DuckSavedData.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(DuckSavedData.class).getConfig();

        if (CONFIG.duckTexturePath == null) {
            CONFIG.duckTexturePath = "textures/entity/mallard_male.png";
        }

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("duck")
                .then(ClientCommandManager.argument("preference", StringArgumentType.string())
                        .suggests(SuggestionProviders.cast(ON_OFF))
                        .executes(context -> {
                                    String preference = StringArgumentType.getString(context, "preference");
                                    if (Objects.equals(preference, "off")) {
                                        CONFIG.duckSummonedPreference = false;
                                        context.getSource().sendFeedback(Component.literal("§6Duck §coff."));
                                        AutoConfig.getConfigHolder(DuckSavedData.class).save();
                                    } else if (Objects.equals(preference, "on")) {
                                        CONFIG.duckSummonedPreference = true;
                                        AutoConfig.getConfigHolder(DuckSavedData.class).save();
                                        context.getSource().sendFeedback(Component.literal("§6Duck §aon."));

                                    } else {
                                        context.getSource().sendFeedback(Component.literal("§c§lUnknown value " + preference + "! Possible values: §r§aon, §6off"));
                                    }
                                    return 1;
                                }
                        )
                )
        ));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("duckname")
                .then(ClientCommandManager.argument("name", StringArgumentType.greedyString())
                        .executes(context -> {
                            CONFIG.duckName = StringArgumentType.getString(context, "name");
                            AutoConfig.getConfigHolder(DuckSavedData.class).save();

                            if (!summonedEntity.isEmpty()) {
                                Entity activeDuck = summonedEntity.getFirst();
                                if (activeDuck != null) {
                                    activeDuck.setCustomName(Component.literal(CONFIG.duckName));
                                    context.getSource().sendFeedback(Component.literal("§aYour duck's name has been set to §6" + CONFIG.duckName));
                                }
                            }
                            duck.setCustomName(Component.literal(CONFIG.duckName));
                            return 1;
                        }))));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("resetduckname")
                .executes((context) -> {
                    CONFIG.duckName = "";
                    AutoConfig.getConfigHolder(DuckSavedData.class).save();
                    duck.setCustomName(Component.literal(CONFIG.duckName));

                    return 1;
                })));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("teleportduck")
                .executes((context) -> {
                    if (duck != null) {
                        duck.tryToTeleportToOwner();
                    }
                    return 1;
                })));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("duckspecies")
                .then(ClientCommandManager.argument("species", StringArgumentType.greedyString())
                        .suggests(SuggestionProviders.cast(DUCK_TYPES))
                        .executes((context) -> {
                                    String species = StringArgumentType.getString(context, "species");
                                    if (Objects.equals(species, "male mallard") || Objects.equals(species, "mallard") || Objects.equals(species, "default")) {
                                        CONFIG.duckTexturePath = "textures/entity/mallard_male.png";
                                        AutoConfig.getConfigHolder(DuckSavedData.class).save();
                                    } else if (Objects.equals(species, "pekin") || Objects.equals(species, "pekin duck")) {
                                        CONFIG.duckTexturePath = "textures/entity/pekin.png";
                                        AutoConfig.getConfigHolder(DuckSavedData.class).save();
                                    } else {
                                        context.getSource().sendFeedback(Component.literal("§c§lThat is not a valid species. Valid species: §r§2Mallard, §rPekin."));
                                    }
                                    return 1;
                                }
                        ))));

        ClientTickEvents.END_CLIENT_TICK.register(client -> client.execute(() -> {

            Minecraft minecraft = Minecraft.getInstance();
            ClientLevel world = minecraft.level;
            duckSkin = (int) (Math.random() * 3);
            if (client.player != null && CONFIG.duckSummonedPreference && summonedEntity.isEmpty()) {
                summonDuck();
            }

            if (!CONFIG.duckSummonedPreference && !summonedEntity.isEmpty()) {
                assert world != null;
                world.removeEntity(i, Entity.RemovalReason.DISCARDED);
                summonedEntity.clear();
            }

        }));
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> client.execute(summonedEntity::clear));
    }

    public void summonDuck() {

        Minecraft minecraft = Minecraft.getInstance();

        LocalPlayer player = minecraft.player;
        ClientLevel world = minecraft.level;

        duck = new Duck(DUCK, world);

        Vec3 lookAngle;

        {
            assert player != null;
            lookAngle = player.getLookAngle();
        }

        double x = player.getX() - (lookAngle.x * 0.5);
        double y = player.getY() + 0.5;
        double z = player.getZ() - (lookAngle.z * 0.5);

        if (world != null) {

            duck.setPos(x, y, z);
            i = duck.getId();
            if (CONFIG.duckName == null) {
                CONFIG.duckName = "";
            }
            duck.setCustomName(Component.literal(CONFIG.duckName));
            CONFIG.uuid = duck.getId();
            duck.setServerEntity(false);

            world.addEntity(duck);
            duck.tame(player);
            summonedEntity.add(duck);
        }
    }
}
