package com.jeff.pets.test.client;

import com.jeff.pets.client.addon.PetsModAddons;
import com.jeff.pets.client.addon.impl.PetSkin;
import com.jeff.pets.test.PetsModTestInit;
import com.jeff.pets.test.client.render.TestDummyRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.renderer.entity.EntityRenderers;

import java.util.List;
import java.util.Optional;

import static com.jeff.pets.test.PetsModTestInit.MOD_ID;

public class PetsModTestModule implements ClientModInitializer {

    public static final PetsModAddons ADDON = PetsModAddons.registerAddon(MOD_ID);
    public static String testDummyName = "";
    public static String testDummySkin = "normal";
    public static boolean aBoolean;

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(PetsModTestInit.TEST_DUMMY, TestDummyRenderer::new);
        try {
            ADDON.addSerializableField(this.getClass().getDeclaredField("aBoolean"));
            ADDON.addPet(PetsModTestInit.TEST_DUMMY, "dummy", this.getClass().getDeclaredField("testDummyName"), Optional.of(new PetSkin(List.of("normal", "red"), this.getClass().getDeclaredField("testDummySkin"))));
            ADDON.addSetting("test", this.getClass().getDeclaredField("aBoolean"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
