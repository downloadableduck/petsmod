package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.mojang.realmsclient.dto.RealmsServer;
import com.mumfrey.liteloader.*;
import com.mumfrey.liteloader.core.LiteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.S01PacketJoinGame;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;

public class LiteModPetsMod implements LiteMod, Tickable, JoinGameListener, InitCompleteListener, OutboundChatFilter {

    public static final String MOD_ID = "pets_mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public LiteModPetsMod() {
        PetsClientInitializer.register();
        PetsSounds.initialize();
        new Central();
        new PetsClientInitializer();
    }

    @Override
    public String getVersion() {
        return "0.7.9";
    }

    @Override
    public void init(File configPath) {
        PetsClientInitializer.register();
        PetsSounds.initialize();
        new Central();
        new PetsClientInitializer();
        Minecraft.getInstance().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getInstance().gameSettings.keyBindings, PetsClientInitializer.openConfigScreen);
        LiteLoader.getInterfaceManager().registerListener(this);
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {

    }

    @Override
    public String getName() {
        return "petsmod";
    }

    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        Central.createTickWatcher();
    }

    @Override
    public void onJoinGame(INetHandler netHandler, S01PacketJoinGame joinGamePacket, ServerData serverData, RealmsServer realmsServer) {
        Central.createJoinHandler();
    }

    @Override
    public void onInitCompleted(Minecraft minecraft, LiteLoader loader) {
        try {
            PetsClientInitializer.register();
            RenderManager manager = minecraft.getRenderManager();
            Field field = manager.getClass().getDeclaredField("k");
            field.setAccessible(true);
            Map map = (Map) field.get(manager);
            synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
                PetsClientInitializer.renderManagerMap.put(manager, new PetsClientInitializer.Context(map));
                for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                    map.put(entry.getKey(), entry.getValue().create(manager, new PetsClientInitializer.Context((Map) map)));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onSendChatMessage(String message) {
        if (message.startsWith("/petspecies")) {
            String species = message.replace("/petspecies ", "");
            Central.executePetSpeciesCommand(species);
            return false;
        } else if (message.equals("/pethelp")) {
            Central.executePetHelpCommand();
            return false;
        } else if (message.contains("/petskin")) {
            String skin = message.replace("/petskin ", "");
            Central.executePetSkinCommand(skin);
            return false;
        } else if (message.contains("/petname")) {
            String name = message.replace("/petname ", "");
            Central.executePetNameCommand(name);
            return false;
        } else if (message.equals("/teleportpet")) {
            Central.executePetTeleportCommand();
            return false;
        } else if (message.contains("/pet")) {
            String preference = message.replace("/pet ", "");
            Central.executeToggleCommand(preference);
            return false;
        }
        return true;
    }
}
