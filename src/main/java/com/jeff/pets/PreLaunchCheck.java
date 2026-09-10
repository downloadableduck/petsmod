package com.jeff.pets;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

/**
 * Runs during the loader's {@code preLaunch} phase, before any Minecraft classes are
 * loaded. Used to perform early environment checks that are safe to run at this point.
 */
public class PreLaunchCheck implements PreLaunchEntrypoint {

    /**
     * Exposes the detected environment type on the loader's object share so the client
     * initializers can look it up without re-evaluating the environment themselves.
     */
    @Override
    public void onPreLaunch() {
        EnvType environment = FabricLoader.getInstance().getEnvironmentType();
        FabricLoader.getInstance().getObjectShare().put("petsmod:environmentType", environment);
    }
}