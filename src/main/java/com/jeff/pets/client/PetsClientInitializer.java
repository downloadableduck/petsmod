package com.jeff.pets.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.custom.aprilfools.head.HeadRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus.DumboOctopusRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.koi.KoiRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.stingray.StingrayRenderer;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinRenderer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.client.rendering.vanilla.bat.ClientBatRenderer;
import com.jeff.pets.client.rendering.vanilla.bee.ClientBeeRenderer;
import com.jeff.pets.client.rendering.vanilla.blaze.ClientBlazeRenderer;
import com.jeff.pets.client.rendering.vanilla.cat.ClientCatRenderer;
import com.jeff.pets.client.rendering.vanilla.cavespider.ClientCaveSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenRenderer;
import com.jeff.pets.client.rendering.vanilla.cod.ClientCodRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowRenderer;
import com.jeff.pets.client.rendering.vanilla.creeper.ClientCreeperRenderer;
import com.jeff.pets.client.rendering.vanilla.dolphin.ClientDolphinRenderer;
import com.jeff.pets.client.rendering.vanilla.donkey.ClientDonkeyRenderer;
import com.jeff.pets.client.rendering.vanilla.drowned.ClientDrownedRenderer;
import com.jeff.pets.client.rendering.vanilla.elderguardian.ClientElderGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.enderdragon.ClientEnderDragonRenderer;
import com.jeff.pets.client.rendering.vanilla.enderman.ClientEndermanRenderer;
import com.jeff.pets.client.rendering.vanilla.endermite.ClientEndermiteRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerRenderer;
import com.jeff.pets.client.rendering.vanilla.fox.ClientFoxRenderer;
import com.jeff.pets.client.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.hoglin.ClientHoglinRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.client.rendering.vanilla.husk.ClientHuskRenderer;
import com.jeff.pets.client.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.llama.ClientLlamaRenderer;
import com.jeff.pets.client.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.client.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.client.rendering.vanilla.panda.ClientPandaRenderer;
import com.jeff.pets.client.rendering.vanilla.parrot.ClientParrotRenderer;
import com.jeff.pets.client.rendering.vanilla.phantom.ClientPhantomRenderer;
import com.jeff.pets.client.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.client.rendering.vanilla.piglin.ClientPiglinRenderer;
import com.jeff.pets.client.rendering.vanilla.pillager.ClientPillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.polarbear.ClientPolarBearRenderer;
import com.jeff.pets.client.rendering.vanilla.pufferfish.ClientPufferFishRenderer;
import com.jeff.pets.client.rendering.vanilla.rabbit.ClientRabbitRenderer;
import com.jeff.pets.client.rendering.vanilla.ravager.ClientRavagerRenderer;
import com.jeff.pets.client.rendering.vanilla.salmon.ClientSalmonRenderer;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepRenderer;
import com.jeff.pets.client.rendering.vanilla.shulker.ClientShulkerRenderer;
import com.jeff.pets.client.rendering.vanilla.silverfish.ClientSilverfishRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ClientSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.slime.ClientSlimeRenderer;
import com.jeff.pets.client.rendering.vanilla.snowgolem.ClientSnowGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.spider.ClientSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.squid.ClientSquidRenderer;
import com.jeff.pets.client.rendering.vanilla.stray.ClientStrayRenderer;
import com.jeff.pets.client.rendering.vanilla.strider.ClientStriderRenderer;
import com.jeff.pets.client.rendering.vanilla.turtle.ClientTurtleRenderer;
import com.jeff.pets.client.rendering.vanilla.vex.ClientVexRenderer;
import com.jeff.pets.client.rendering.vanilla.villager.ClientVillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.vindicator.ClientVindicatorRenderer;
import com.jeff.pets.client.rendering.vanilla.wanderingtrader.ClientWanderingTraderRenderer;
import com.jeff.pets.client.rendering.vanilla.witch.ClientWitchRenderer;
import com.jeff.pets.client.rendering.vanilla.wither.ClientWitherRenderer;
import com.jeff.pets.client.rendering.vanilla.witherskeleton.ClientWitherSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.wolf.ClientWolfRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieRenderer;
import com.jeff.pets.client.rendering.vanilla.zombievillager.ClientZombieVillagerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static com.jeff.pets.PetsInitializer.LOGGER;

/**
 * Another {@code initializer} class. This class does a couple of things:
 * <p> - Assigns renderers to the entities defined in {@link PetsInitializer}
 * <p> - Bakes models into the layers
 * <p> - Creates the keybind to open the config screen, by default {@code p}
 * <p> Suppresses: Deprecation warnings, as {@link EntityRenderers} is marked as
 * {@code deprecated}. I will likely have to find a suitable replacement sometime, but for now,
 * suppressing the warnings will work.
 *
 * @see PetsInitializer
 * @see Central
 */

public class PetsClientInitializer implements ClientModInitializer {

    public static List<String> ADDONS = new ArrayList<>();

    /**
     * Misc rendering stuff
     */
    @Override
    public void onInitializeClient() {

        this.createKeyBinding();

        EntityRendererRegistry.INSTANCE.register(PetsInitializer.HEAD, HeadRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.DUCK, DuckRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.RACOON, RacoonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PENGUIN, PenguinRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SHEEP, ClientSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.CAT, ClientCatRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.BAT, ClientBatRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.CHICKEN, ClientChickenRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.COD, ClientCodRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.COW, ClientCowRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.DONKEY, ClientDonkeyRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.HORSE, ClientHorseRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.MOOSHROOM, ClientMooshroomRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PARROT, ClientParrotRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PIG, ClientPigRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.RABBIT, ClientRabbitRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SALMON, ClientSalmonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SNOW_GOLEM, ClientSnowGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SQUID, ClientSquidRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.STRIDER, ClientStriderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.TURTLE, ClientTurtleRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.VILLAGER, ClientVillagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.WANDERING_TRADER, ClientWanderingTraderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.BEE, ClientBeeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.CAVE_SPIDER, ClientCaveSpiderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.DOLPHIN, ClientDolphinRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.ENDERMAN, ClientEndermanRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.FOX, ClientFoxRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.IRON_GOLEM, ClientIronGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.LLAMA, ClientLlamaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PANDA, ClientPandaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PIGLIN, ClientPiglinRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.POLAR_BEAR, ClientPolarBearRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PUFFERFISH, ClientPufferFishRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SPIDER, ClientSpiderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.WOLF, ClientWolfRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.ELDER_GUARDIAN_COOKIE, ClientElderGuardianRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.BLAZE, ClientBlazeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.CREEPER, ClientCreeperRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.DROWNED, ClientDrownedRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.ENDERMITE, ClientEndermiteRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.EVOKER, ClientEvokerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.GHAST, ClientGhastRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.GUARDIAN, ClientGuardianRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.HOGLIN, ClientHoglinRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.HUSK, ClientHuskRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.MAGMA_CUBE, ClientMagmaCubeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PHANTOM, ClientPhantomRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.PILLAGER, ClientPillagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.RAVAGER, ClientRavagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SHULKER, ClientShulkerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SILVERFISH, ClientSilverfishRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SKELETON, ClientSkeletonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.SLIME, ClientSlimeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.STRAY, ClientStrayRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.VEX, ClientVexRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.VINDICATOR, ClientVindicatorRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.WITCH, ClientWitchRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.WITHER_SKELETON, ClientWitherSkeletonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.ZOMBIE, ClientZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.ZOMBIE_VILLAGER, ClientZombieVillagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.ENDER_DRAGON, ClientEnderDragonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.WITHER, ClientWitherRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.DUMBO_OCTOPUS, DumboOctopusRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.KOI, KoiRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PetsInitializer.STINGRAY, StingrayRenderer::new);

        ClientLifecycleEvents.CLIENT_STARTED.register((mc) -> {
            LOGGER.info("PetsMod addons loaded:{}", ADDONS);
        });
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */
    void createKeyBinding() {
        KeyMapping keyMapping = KeyBindingHelper.registerKeyBinding(new KeyMapping("Open Pets Menu", GLFW.GLFW_KEY_P, "petsmod.keymapping"));

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (keyMapping.consumeClick()) {
                client.setScreen(PetsConfigScreen.getInstance().getModConfigScreenFactory().create(client.screen));
            }
        });
    }
}