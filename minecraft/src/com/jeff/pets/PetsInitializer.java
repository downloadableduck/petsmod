package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.mob.custom.first.*;
import com.jeff.pets.mob.custom.aquatic.*;
import com.jeff.pets.mob.vanilla.passive.*;
import com.jeff.pets.mob.vanilla.hostile.*;
import com.jeff.pets.mob.vanilla.neutral.*;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PetsInitializer {

    public static final String MOD_ID = "pets_mod";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    static {
        //PetsClientInitializer.register();
        PetsSounds.initialize();
    }

    public PetsInitializer(){
      //  PetsClientInitializer.register();
        PetsSounds.initialize();
    }
}
