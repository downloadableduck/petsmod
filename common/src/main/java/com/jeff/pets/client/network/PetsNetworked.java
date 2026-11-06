package com.jeff.pets.client.network;

import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PetsNetworked {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");

    @SuppressWarnings("all")
    public static void createConnectHandler() {
        if (Minecraft.getInstance().getCurrentServer() != null)
            NetworkManager.get().connect(Minecraft.getInstance().getCurrentServer().ip);
    }

    public static void createDisconnectHandler() {
        NetworkManager.get().disconnect();
    }

    public static void createLevelChangeHandler() {
        NetworkManager.get().sayByeBye();
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.getCurrentServer() != null) {
            NetworkManager.get().disconnect();
            NetworkManager.get().connect(minecraft.getCurrentServer().ip);
        }
    }
}
