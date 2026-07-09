package com.jeff.pets.client.compat;

import com.jeff.pets.PetsInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Method;

public class ViaFabricPlusCompat {
    public static boolean isViaFabricPlusPresent() {
        return FabricLoader.getInstance().isModLoaded("viafabricplus");
    }

    public static boolean shouldUpdateThingy() {
        if (!isViaFabricPlusPresent()) {
            return false;
        }
        try {
            Class<?> viaFabricPlus = Class.forName("com.viaversion.viafabricplus.ViaFabricPlusImpl");
            Object instance = viaFabricPlus.getField("INSTANCE").get(null);
            Object protocolVersion = viaFabricPlus.getMethod("getTargetVersion").invoke(instance);
            if (instance != null) {
                try {
                    Method getVersionMethod = protocolVersion.getClass().getMethod("getVersion");

                    if ((Integer) getVersionMethod.invoke(protocolVersion) == 47 || (Integer) getVersionMethod.invoke(protocolVersion) == 4) {
                        return true;
                    }
                } catch (Exception e) {
                    PetsInitializer.LOGGER.error("aaaaaaaaaa");
                }
            }

        } catch (Exception e) {
            PetsInitializer.LOGGER.error("bbbbbb");
        }
        return false;
    }
}
