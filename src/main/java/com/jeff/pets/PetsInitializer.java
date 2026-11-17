//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "com.jeff.pets-mod";
    public static final Logger LOGGER = LogManager.getLogger("com.jeff.pets-mod");

    public PetsInitializer() {
    }

    public void onInitialize() {
        PetsSounds.initialize();
        LOGGER.info("quack");
    }
}
