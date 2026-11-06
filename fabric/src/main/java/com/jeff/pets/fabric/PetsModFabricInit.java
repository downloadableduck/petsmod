package com.jeff.pets.fabric;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.api.Loader;
import com.jeff.pets.client.autoupdate.AutoUpdater;
import com.jeff.pets.client.network.PetsNetworked;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class PetsModFabricInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Central.instantiate(new Loader() {
            @Override
            public Path configDir() {
                return FabricLoader.getInstance().getConfigDir();
            }

            @Override
            public String build() {
                return FabricLoader.getInstance().getModContainer("pets-mod").get().getMetadata().getVersion().getFriendlyString();
            }

            @Override
            public Collection<String> modsInstalled() {
                Collection<String> mods = new ArrayList<>();
                FabricLoader.getInstance().getAllMods().forEach((modContainer -> mods.add(modContainer.getMetadata().getId())));
                return mods;
            }

            @Override
            public boolean devEnv() {
                return FabricLoader.getInstance().isDevelopmentEnvironment();
            }
        });
        Central.get().onInitializeClient();
        PetsInitializer.onInitialize(FabricDefaultAttributeRegistry::register);
        PetsClientInitializer.createKeyBinding(KeyMappingHelper::registerKeyMapping);
        PetsClientInitializer.registerRenderers();
        PetsClientInitializer.registerModelLayers(((location, layerDefinition) -> ModelLayerRegistry.registerModelLayer(location, layerDefinition::getLayerDefinition)));
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, _) -> Central.get().setupCommands(() -> (CommandDispatcher) dispatcher)));
        AutoUpdater.onInitializeClient();
        ClientTickEvents.END_CLIENT_TICK.register((_ -> {
            Central.createTickWatcher();
            PetsClientInitializer.createKeyBindingWatcher();
        }));
        ClientLifecycleEvents.CLIENT_STARTED.register((_) -> {
            Central.registerAddonConfigData();
            PetsClientInitializer.printAddons();
        });
        ClientPlayConnectionEvents.JOIN.register((_, _, _) -> {
            AutoUpdater.onGameJoin();
            PetsNetworked.createConnectHandler();
        });
        ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register((_, _) -> {
            Central.createJoinHandler();
            PetsNetworked.createLevelChangeHandler();
        });
        ClientPlayConnectionEvents.DISCONNECT.register((_, _) -> PetsNetworked.createDisconnectHandler());
    }
}
