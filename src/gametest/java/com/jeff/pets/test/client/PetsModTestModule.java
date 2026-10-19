package com.jeff.pets.test.client;

import com.jeff.pets.client.addon.PetsModAddons;
import com.jeff.pets.client.addon.impl.PetSkin;
import com.jeff.pets.test.PetsModTestInit;
import com.jeff.pets.test.client.render.TestDummyRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.lifecycle.ClientLifecycleEvent;
import net.neoforged.neoforge.client.event.lifecycle.ClientStartedEvent;

import java.util.List;
import java.util.Optional;

import static com.jeff.pets.test.PetsModTestInit.MOD_ID;

@EventBusSubscriber
@Mod(value=MOD_ID, dist = Dist.CLIENT)
public class PetsModTestModule {

    public static final PetsModAddons ADDON = PetsModAddons.registerAddon(MOD_ID);
    public static String testDummyName = "";
    public static String testDummySkin = "normal";

    public PetsModTestModule(IEventBus bus) {
        bus.addListener(this::registerRenderers);
    }

    public void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PetsModTestInit.TEST_DUMMY.get(), TestDummyRenderer::new);
    }

    @SubscribeEvent
    public static void onInit(ClientStartedEvent event) {
        try {
            ADDON.addPet(PetsModTestInit.TEST_DUMMY.get(), "dummy", PetsModTestModule.class.getDeclaredField("testDummyName"), Optional.of(new PetSkin(List.of("normal", "red"), PetsModTestModule.class.getDeclaredField("testDummySkin"))));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
