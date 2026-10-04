package com.jeff.pets.client.network;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PetsNetworked {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");


    public static void createConnectHandler() {
        NetworkManager.get().connect(Minecraft.getInstance().getCurrentServerEntry().ip);
    }

    public static void createDisconnectHandler() {
        NetworkManager.get().disconnect();
    }

    public static void createLevelChangeHandler() {
        NetworkManager.get().sayByeBye();
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.getCurrentServerEntry() != null) {
            NetworkManager.get().disconnect();
            NetworkManager.get().connect(minecraft.getCurrentServerEntry().ip);
        }
    }
}
