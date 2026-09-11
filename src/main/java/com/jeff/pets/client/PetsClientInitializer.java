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
import com.jeff.pets.client.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.client.rendering.vanilla.husk.ClientHuskRenderer;
import com.jeff.pets.client.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.llama.ClientLlamaRenderer;
import com.jeff.pets.client.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.client.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.client.rendering.vanilla.parrot.ClientParrotRenderer;
import com.jeff.pets.client.rendering.vanilla.phantom.ClientPhantomRenderer;
import com.jeff.pets.client.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.client.rendering.vanilla.polarbear.ClientPolarBearRenderer;
import com.jeff.pets.client.rendering.vanilla.pufferfish.ClientPufferFishRenderer;
import com.jeff.pets.client.rendering.vanilla.rabbit.ClientRabbitRenderer;
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
import com.jeff.pets.client.rendering.vanilla.vex.ClientVexRenderer;
import com.jeff.pets.client.rendering.vanilla.villager.ClientVillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.vindicator.ClientVindicatorRenderer;
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
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.resource.ResourcePackLoader;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

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

    public static final Map<Class, Factory> renderSupplierMap = new HashMap();
    public static final Map<EntityRenderDispatcher, Context> renderManagerMap = new WeakHashMap();
    public static List<String> ADDONS = new ArrayList<>();
    public static KeyBinding keyMapping;
    private static volatile File pendingResourcePack;

    /**
     * Misc rendering stuff
     */
    @Override
    public void onInitializeClient() {
        try {
            pendingResourcePack = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        this.createKeyBinding();
    }

    public static void registerPendingResourcePack() {
        File file = pendingResourcePack;
        if (file == null) {
            return;
        }
        pendingResourcePack = null;
        registerAsResourcePack(file);
    }

    private static void registerAsResourcePack(File file) {
        MinecraftClient client = MinecraftClient.getInstance();
        ResourcePackLoader loader = client.getResourcePackLoader();
        String packName = "file/" + file.getName();
        if (!client.options.resourcePacks.contains(packName)) {
            client.options.resourcePacks.add(packName);
            client.options.save();
        }
        try {
            Futures.addCallback(loader.loadServerPack(file), new FutureCallback<Object>() {
                @Override
                public void onSuccess(Object result) {
                    client.execute(() -> {
                        client.reloadResources();
                        return new Object();
                    });
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    public static void register() {
        register(Head.class, HeadRenderer::new);
        register(Duck.class, DuckRenderer::new);
        register(Racoon.class, RacoonRenderer::new);
        register(Penguin.class, PenguinRenderer::new);
        register(ClientSheep.class, ClientSheepRenderer::new);
        register(ClientCat.class, ClientCatRenderer::new);
        register(ClientBat.class, ClientBatRenderer::new);
        register(ClientChicken.class, ClientChickenRenderer::new);
        register(ClientCod.class, ClientCodRenderer::new);
        register(ClientCow.class, ClientCowRenderer::new);
        register(ClientDonkey.class, ClientDonkeyRenderer::new);
        register(ClientHorse.class, ClientHorseRenderer::new);
        register(ClientMooshroom.class, ClientMooshroomRenderer::new);
        register(ClientParrot.class, ClientParrotRenderer::new);
        register(ClientPig.class, ClientPigRenderer::new);
        register(ClientRabbit.class, ClientRabbitRenderer::new);
        register(ClientSalmon.class, ClientSalmonRenderer::new);
        register(ClientSnowGolem.class, ClientSnowGolemRenderer::new);
        register(ClientSquid.class, ClientSquidRenderer::new);
        register(ClientVillager.class, ClientVillagerRenderer::new);
        register(ClientCaveSpider.class, ClientCaveSpiderRenderer::new);
        register(ClientDolphin.class, ClientDolphinRenderer::new);
        register(ClientEnderman.class, ClientEndermanRenderer::new);
        register(ClientIronGolem.class, ClientIronGolemRenderer::new);
        register(ClientLlama.class, ClientLlamaRenderer::new);
        register(ClientPolarBear.class, ClientPolarBearRenderer::new);
        register(ClientPufferFish.class, ClientPufferFishRenderer::new);
        register(ClientSpider.class, ClientSpiderRenderer::new);
        register(ClientWolf.class, ClientWolfRenderer::new);
        register(ClientElderGuardian.class, ClientElderGuardianRenderer::new);
        register(ClientBlaze.class, ClientBlazeRenderer::new);
        register(ClientCreeper.class, ClientCreeperRenderer::new);
        register(ClientDrowned.class, ClientDrownedRenderer::new);
        register(ClientEndermite.class, ClientEndermiteRenderer::new);
        register(ClientEvoker.class, ClientEvokerRenderer::new);
        register(ClientGhast.class, ClientGhastRenderer::new);
        register(ClientGuardian.class, ClientGuardianRenderer::new);
        register(ClientHusk.class, ClientHuskRenderer::new);
        register(ClientMagmaCube.class, ClientMagmaCubeRenderer::new);
        register(ClientPhantom.class, ClientPhantomRenderer::new);
        register(ClientShulker.class, ClientShulkerRenderer::new);
        register(ClientSilverfish.class, ClientSilverfishRenderer::new);
        register(ClientSkeleton.class, ClientSkeletonRenderer::new);
        register(ClientSlime.class, ClientSlimeRenderer::new);
        register(ClientStray.class, ClientStrayRenderer::new);
        register(ClientVex.class, ClientVexRenderer::new);
        register(ClientVindicator.class, ClientVindicatorRenderer::new);
        register(ClientWitch.class, ClientWitchRenderer::new);
        register(ClientWitherSkeleton.class, ClientWitherSkeletonRenderer::new);
        register(ClientZombie.class, ClientZombieRenderer::new);
        register(ClientZombieVillager.class, ClientZombieVillagerRenderer::new);
        register(ClientEnderDragon.class, ClientEnderDragonRenderer::new);
        register(ClientWither.class, ClientWitherRenderer::new);
        register(DumboOctopus.class, DumboOctopusRenderer::new);
        register(Koi.class, KoiRenderer::new);
        register(Stingray.class, StingrayRenderer::new);
        register(ClientZombiePigman.class, ClientZombiePigmanRenderer::new);
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */
    void createKeyBinding() {
        keyMapping = new KeyBinding("Open Pets Menu", Keyboard.KEY_P, "petsmod.keymapping");
    }

    public static void register(Class entityClass, Factory factory) {
        synchronized (renderSupplierMap) {
            renderSupplierMap.put(entityClass, factory);

            for (EntityRenderDispatcher manager : renderManagerMap.keySet()) {
                renderManagerMap.get(manager).rendererMap.put(entityClass, factory.create(manager, renderManagerMap.get(manager)));
            }
        }
    }

    @FunctionalInterface
    public interface Factory {
        MobEntityRenderer<? extends Entity> create(EntityRenderDispatcher var1, Context var2);
    }

    public static final class Context {
        private final Map<Class, MobEntityRenderer<? extends Entity>> rendererMap;

        public Context(Map<Class, MobEntityRenderer<? extends Entity>> rendererMap) {
            super();
            this.rendererMap = rendererMap;
        }
    }
}