package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import cpw.mods.fml.common.asm.transformers.ModAccessTransformer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        PetsClientInitializer.register();
        PetsSounds.initialize();
        MinecraftForge.EVENT_BUS.register(PetsInitializer.class);
        MinecraftForge.EVENT_BUS.register(Central.class);
    }

    public PetsInitializer() throws NoSuchFieldException, IllegalAccessException {
        PetsClientInitializer.register();
        PetsSounds.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Central.class);
        MinecraftForge.EVENT_BUS.register(new Central());
        MinecraftForge.EVENT_BUS.register(new PetsClientInitializer());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Central.setupCommands(event);
    }
}
