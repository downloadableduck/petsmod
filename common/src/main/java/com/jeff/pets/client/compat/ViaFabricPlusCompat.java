package com.jeff.pets.client.compat;

import com.jeff.pets.client.Central;

import java.lang.reflect.Method;

public class ViaFabricPlusCompat {
    public static boolean isViaFabricPlusPresent() {
        return Central.get().modsInstalled.contains("viafabricplus");
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
                } catch (Exception ignored) {
                }
            }

        } catch (Exception ignored) {
        }
        return false;
    }
}
