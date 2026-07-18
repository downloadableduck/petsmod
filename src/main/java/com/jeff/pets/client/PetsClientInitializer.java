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
import com.jeff.pets.client.rendering.vanilla.zombie_pigman.ClientZombiePigmanRenderer;
import com.jeff.pets.client.rendering.vanilla.zombievillager.ClientZombieVillagerRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.jeff.pets.mob.custom.aquatic.Koi;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.jeff.pets.mob.custom.first.Duck;
import com.jeff.pets.mob.custom.first.Penguin;
import com.jeff.pets.mob.custom.first.Racoon;
import com.jeff.pets.mob.vanilla.passive.*;
import com.jeff.pets.mob.vanilla.neutral.*;
import com.jeff.pets.mob.vanilla.hostile.*;
import com.jeff.pets.mob.vanilla.boss.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

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
    public static KeyBinding keyMapping;

    /**
     * Misc rendering stuff
     */
    @Override
    public void onInitializeClient() {

        this.createKeyBinding();

        EntityRendererRegistry.INSTANCE.register(Head.class, HeadRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Duck.class, (context, context2) -> new DuckRenderer(context, context2));
        EntityRendererRegistry.INSTANCE.register(Racoon.class, RacoonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Penguin.class, PenguinRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSheep.class, ClientSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientCat.class, ClientCatRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientBat.class, ClientBatRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientChicken.class, ClientChickenRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientCod.class, ClientCodRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientCow.class, ClientCowRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientDonkey.class, ClientDonkeyRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientHorse.class, ClientHorseRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientMooshroom.class, ClientMooshroomRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientParrot.class, ClientParrotRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientPig.class, ClientPigRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientRabbit.class, ClientRabbitRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSalmon.class, ClientSalmonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSnowGolem.class, ClientSnowGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSquid.class, ClientSquidRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientTurtle.class, ClientTurtleRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientVillager.class, ClientVillagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientWanderingTrader.class, ClientWanderingTraderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientCaveSpider.class, ClientCaveSpiderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientDolphin.class, ClientDolphinRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientEnderman.class, ClientEndermanRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientFox.class, ClientFoxRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientIronGolem.class, ClientIronGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientLlama.class, ClientLlamaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientPanda.class, ClientPandaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientPolarBear.class, ClientPolarBearRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientPufferFish.class, ClientPufferFishRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSpider.class, ClientSpiderRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientWolf.class, ClientWolfRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientElderGuardian.class, ClientElderGuardianRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientBlaze.class, ClientBlazeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientCreeper.class, ClientCreeperRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientDrowned.class, ClientDrownedRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientEndermite.class, ClientEndermiteRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientEvoker.class, ClientEvokerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientGhast.class, ClientGhastRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientGuardian.class, ClientGuardianRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientHusk.class, ClientHuskRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientMagmaCube.class, ClientMagmaCubeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientPhantom.class, ClientPhantomRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientPillager.class, ClientPillagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientRavager.class, ClientRavagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientShulker.class, ClientShulkerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSilverfish.class, ClientSilverfishRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSkeleton.class, ClientSkeletonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientSlime.class, ClientSlimeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientStray.class, ClientStrayRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientVex.class, ClientVexRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientVindicator.class, ClientVindicatorRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientWitch.class, ClientWitchRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientWitherSkeleton.class, ClientWitherSkeletonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientZombie.class, ClientZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientZombieVillager.class, ClientZombieVillagerRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientEnderDragon.class, ClientEnderDragonRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientWither.class, ClientWitherRenderer::new);
        EntityRendererRegistry.INSTANCE.register(DumboOctopus.class, DumboOctopusRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Koi.class, KoiRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Stingray.class, StingrayRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ClientZombiePigman.class, ClientZombiePigmanRenderer::new);
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */
    void createKeyBinding() {
        keyMapping = new KeyBinding("Open Pets Menu", GLFW.GLFW_KEY_P, "petsmod.keymapping");
    }
}