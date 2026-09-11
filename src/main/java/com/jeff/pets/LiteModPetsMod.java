package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.mojang.realmsclient.dto.RealmsServer;
import com.mumfrey.liteloader.*;
import com.mumfrey.liteloader.client.ducks.IRenderManager;
import com.mumfrey.liteloader.client.overlays.IEntityRenderer;
import com.mumfrey.liteloader.core.LiteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.SPacketJoinGame;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LiteModPetsMod implements LiteMod, ServerCommandProvider, Tickable, JoinGameListener, InitCompleteListener {

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
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {

    }

    @Override
    public String getName() {
        return "petsmod";
    }

    @Override
    public void provideCommands(ServerCommandManager commandManager) {
        Central.setupCommands(commandManager);
    }

    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        Central.createTickWatcher();
    }

    @Override
    public void onJoinGame(INetHandler netHandler, SPacketJoinGame joinGamePacket, ServerData serverData, RealmsServer realmsServer) {
        Central.createJoinHandler();
    }

    @Override
    public void onInitCompleted(Minecraft minecraft, LiteLoader loader) {
        PetsClientInitializer.register();
        try {
            Field field = RenderManager.class.getDeclaredField("k");
            field.setAccessible(true);
            Map map = (Map) field.get(minecraft.getRenderManager());
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue().create(new RenderManager(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getItemRenderer()), new PetsClientInitializer.Context((Map) map)));
            }
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
