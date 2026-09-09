package com.jeff.pets.client.network;

import com.jeff.pets.client.Utils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jeff.pets.client.Central.CONFIG;

public final class PetsNetworked implements ClientModInitializer {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");


    @Override
    public void onInitializeClient() {
        this.createConnectHandler();
        this.createDisconnectHandler();
        this.createLevelChangeHandler();
    }

    private void createConnectHandler() {
        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) -> {
            NetworkManager.get().connect(Minecraft.getInstance().getCurrentServer().ip);
        });
    }

    private void createDisconnectHandler() {
        ClientPlayConnectionEvents.DISCONNECT.register(((clientPacketListener, minecraft) -> {
            NetworkManager.get().disconnect();
        }));
    }

    private void createLevelChangeHandler() {
        ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register(((minecraft, clientLevel) -> {
            NetworkManager.get().sayByeBye();

            if (minecraft.getCurrentServer() != null) {
                NetworkManager.get().disconnect();
                NetworkManager.get().connect(minecraft.getCurrentServer().ip);
            }
        }));
    }
}
