package com.jeff.pets.test.client;

import com.jeff.pets.client.addon.PetsModAddons;
import com.jeff.pets.client.addon.impl.PetSkin;
import com.jeff.pets.test.PetsModTestInit;
import com.jeff.pets.test.client.render.TestDummyRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

import java.util.List;
import java.util.Optional;

import static com.jeff.pets.test.PetsModTestInit.MOD_ID;

public class PetsModTestModule implements ClientModInitializer {

    public static final PetsModAddons ADDON = PetsModAddons.registerAddon(MOD_ID);
    public static String testDummyName = "";
    public static String testDummySkin = "normal";

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(PetsModTestInit.TEST_DUMMY, TestDummyRenderer::new);
        try {
            ADDON.addPet(PetsModTestInit.TEST_DUMMY, "dummy", this.getClass().getDeclaredField("testDummyName"), Optional.of(new PetSkin(List.of("normal", "red"), this.getClass().getDeclaredField("testDummySkin"))));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
