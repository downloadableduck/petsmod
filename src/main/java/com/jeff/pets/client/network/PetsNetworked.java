package com.jeff.pets.network;

import com.jeff.pets.Utils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jeff.pets.Central.CONFIG;

public final class PetsNetworked implements ClientModInitializer {
    public static final String MOD_ID = "pets_networked";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pets Networking");


    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) -> {
            NetworkManager.get().connect(Minecraft.getInstance().getCurrentServer().ip);
        });

        ClientPlayConnectionEvents.DISCONNECT.register(((clientPacketListener, minecraft) ->{
            NetworkManager.get().disconnect();
        }));

        ClientEntityEvents.ENTITY_LOAD.register(((entity, clientLevel) -> {
            if (entity instanceof Player player) {
                if (NetworkManager.map.containsKey(player.getUUID())) {
                    NetworkManager.get().broadcastGeneral(player.getStringUUID(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin());
                }
            }
        }));
    }
}
