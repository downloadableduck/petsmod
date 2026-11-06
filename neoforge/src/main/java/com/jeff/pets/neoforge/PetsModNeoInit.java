package com.jeff.pets.neoforge;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.api.Loader;
import com.jeff.pets.client.autoupdate.AutoUpdater;
import com.jeff.pets.client.network.PetsNetworked;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.lifecycle.ClientStartedEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@Mod("pets_mod")
@EventBusSubscriber
public final class PetsModNeoInit {
    public PetsModNeoInit(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (_, _) -> new PetsConfigScreen());
    }

    @SubscribeEvent
    public static void onInit(FMLClientSetupEvent event) {
        Central.instantiate(new Loader() {
            @Override
            public Path configDir() {
                return Minecraft.getInstance().gameDirectory.toPath().resolve("config");
            }

            @Override
            public String build() {
                return ModList.get().getModContainerById("pets_mod").get().getModInfo().getVersion().toString();
            }

            @Override
            public Collection<String> modsInstalled() {
                return List.of();
            }

            @Override
            public boolean devEnv() {
                return !FMLEnvironment.isProduction();
            }
        });
        Central.get().onInitializeClient();
        AutoUpdater.onInitializeClient();
    }

    @SubscribeEvent
    public static void onAttributeInit(EntityAttributeCreationEvent event) {
        PetsInitializer.onInitialize(event::put);
    }

    @SubscribeEvent
    public static void onKeyBindingInit(RegisterKeyMappingsEvent event) {
        PetsClientInitializer.createKeyBinding((keyMapping -> {
            event.register(keyMapping);
            return keyMapping;
        }));
    }

    @SubscribeEvent
    public static void onModelLayersInit(EntityRenderersEvent.RegisterLayerDefinitions event) {
        PetsClientInitializer.registerRenderers();
        PetsClientInitializer.registerModelLayers((location, definition) -> event.registerLayerDefinition(location, definition::getLayerDefinition));
    }

    @SubscribeEvent
    public static void onInitCommands(RegisterClientCommandsEvent event) {
        Central.get().setupCommands(() -> (CommandDispatcher) event.getDispatcher());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Central.createTickWatcher();
        PetsClientInitializer.createKeyBindingWatcher();
    }

    @SubscribeEvent
    public static void onClientStarted(ClientStartedEvent event) {
        Central.registerAddonConfigData();
        PetsClientInitializer.printAddons();
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        AutoUpdater.onGameJoin();
        PetsNetworked.createConnectHandler();
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        PetsNetworked.createDisconnectHandler();
    }

    @SubscribeEvent
    public static void onClientLevelChange(LevelEvent.Load event) {
        Central.createJoinHandler();
        PetsNetworked.createLevelChangeHandler();
    }
}
