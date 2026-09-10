package com.jeff.pets.client.network;

import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PetsNetworked {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");

    public static void createConnectHandler() {
        try {
            NetworkManager.get().connect(Minecraft.getInstance().getCurrentServer().ip);
        } catch (Exception e) {}
    }

    public static void createDisconnectHandler() {
        try {
            NetworkManager.get().disconnect();
        } catch (Exception e) {}
    }

    public static void createLevelChangeHandler() {
        try {
            LOGGER.info("hello world");
            NetworkManager.get().sayByeBye();
            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.getCurrentServer() != null) {
                NetworkManager.get().disconnect();
                NetworkManager.get().connect(minecraft.getCurrentServer().ip);
            }
        } catch (Exception e) {}
    }
}
