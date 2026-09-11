package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.asm.transformers.AccessTransformer;
import net.minecraftforge.fml.common.asm.transformers.ModAccessTransformer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

import static com.jeff.pets.PetsInitializer.MOD_ID;

@Mod(modid=MOD_ID, acceptedMinecraftVersions = "[1.11,1.11.2]")
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
