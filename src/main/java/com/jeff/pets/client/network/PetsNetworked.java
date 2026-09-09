package com.jeff.pets.client.network;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jeff.pets.PetsInitializer.MOD_ID;

@EventBusSubscriber
public final class PetsNetworked {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");


    @SubscribeEvent
    public static void createConnectHandler(ClientPlayerNetworkEvent.LoggingIn event) {
        try {
            NetworkManager.get().connect(Minecraft.getInstance().getCurrentServer().ip);
        } catch (Exception e) {}
    }

    @SubscribeEvent
    public static void createDisconnectHandler(ClientPlayerNetworkEvent.LoggingOut event) {
        try {
            NetworkManager.get().disconnect();
        } catch (Exception e) {}
    }

    @SubscribeEvent
    public static void createLevelChangeHandler(LevelEvent.Load event) {
        try {
            if (event.getLevel().isClientSide()) {
                NetworkManager.get().sayByeBye();

                if (Minecraft.getInstance().getCurrentServer() != null) {
                    NetworkManager.get().disconnect();
                    NetworkManager.get().connect(Minecraft.getInstance().getCurrentServer().ip);
                }
            }
        } catch (Exception e) {}
    }
}
