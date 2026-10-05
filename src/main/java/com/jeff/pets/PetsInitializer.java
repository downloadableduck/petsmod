package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import cpw.mods.fml.common.asm.transformers.ModAccessTransformer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

import static com.jeff.pets.PetsInitializer.MOD_ID;

@SideOnly(Side.CLIENT)
@Mod(modid=MOD_ID, acceptedMinecraftVersions = "[1.7.2,1.7.10]")
public class PetsInitializer {

    public static final String MOD_ID = "pets_mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    static {
        //PetsClientInitializer.register();
        PetsSounds.initialize();
    }

    public PetsInitializer() throws NoSuchFieldException, IllegalAccessException {
      //  PetsClientInitializer.register();
        PetsSounds.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Central());
        MinecraftForge.EVENT_BUS.register(new PetsClientInitializer());
        FMLCommonHandler.instance().bus().register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Central.setupCommands(event);
        PetsClientInitializer.register(event);
    }

    @SubscribeEvent
    public void onGameJoin(WorldEvent.Load event) {
        new Central().createJoinHandler(event);
    }

    @SubscribeEvent
    public void onGameTick(TickEvent.ClientTickEvent event) {
        new Central().createTickWatcher(event);
    }
}
