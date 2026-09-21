package com.jeff.pets.client.network;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PetsNetworked {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");

    @SubscribeEvent
    private void createConnectHandler(PlayerEvent.PlayerLoggedInEvent event) {
        NetworkManager.get().connect(Minecraft.getInstance().getCurrentServerData().serverIP);
    }

    @SubscribeEvent
    private void createDisconnectHandler(PlayerEvent.PlayerLoggedOutEvent event) {
        NetworkManager.get().disconnect();
    }

    @SubscribeEvent
    private void createLevelChangeHandler(WorldEvent.Load event) {
        NetworkManager.get().sayByeBye();
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.getCurrentServerData() != null) {
            NetworkManager.get().disconnect();
            NetworkManager.get().connect(minecraft.getCurrentServerData().serverIP);
        }
    }
}
