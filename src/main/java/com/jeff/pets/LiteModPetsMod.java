package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.mojang.realmsclient.dto.RealmsServer;
import com.mumfrey.liteloader.*;
import com.mumfrey.liteloader.api.MixinConfigProvider;
import com.mumfrey.liteloader.client.ducks.IRenderManager;
import com.mumfrey.liteloader.core.LiteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.S01PacketJoinGame;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.io.File;
import java.util.Map;

public class LiteModPetsMod implements LiteMod, Tickable, JoinGameListener, InitCompleteListener, MixinConfigProvider, OutboundChatFilter {

    public static final String MOD_ID = "pets_mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public LiteModPetsMod() {
        PetsClientInitializer.register();
        PetsSounds.initialize();
        new Central();
        new PetsClientInitializer();
    }

    static {
        MixinEnvironment.setCompatibilityLevel(MixinEnvironment.CompatibilityLevel.JAVA_8);
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
        PetsClientInitializer.register();
        RenderManager manager = minecraft.getRenderManager();
        Map map = ((IRenderManager) manager).getRenderMap();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            PetsClientInitializer.renderManagerMap.put(manager, new PetsClientInitializer.Context(map));
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue().create(manager, new PetsClientInitializer.Context((Map) map)));
            }
        }
    }

    @Override
    public MixinEnvironment.CompatibilityLevel getCompatibilityLevel() {
        return MixinEnvironment.CompatibilityLevel.JAVA_8;
    }

    @Override
    public String[] getMixinConfigs() {
        return new String[]{"pets.client.mixins.json"};
    }

    @Override
    public String[] getErrorHandlers() {
        return new String[0];
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
