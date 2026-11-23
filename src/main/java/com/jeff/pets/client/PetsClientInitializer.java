//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.input.InputDevice;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.core.entity.Entity;
import com.jeff.pets.client.rendering.custom.aprilfools.head.HeadRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus.DumboOctopusRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.koi.KoiRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.stingray.StingrayRenderer;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinRenderer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.client.rendering.vanilla.bat.ClientBatRenderer;
import com.jeff.pets.client.rendering.vanilla.cat.ClientCatRenderer;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowRenderer;
import com.jeff.pets.client.rendering.vanilla.creeper.ClientCreeperRenderer;
import com.jeff.pets.client.rendering.vanilla.donkey.ClientDonkeyRenderer;
import com.jeff.pets.client.rendering.vanilla.elderguardian.ClientElderGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.enderdragon.ClientEnderDragonRenderer;
import com.jeff.pets.client.rendering.vanilla.endermite.ClientEndermiteRenderer;
import com.jeff.pets.client.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.client.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.client.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.client.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.client.rendering.vanilla.rabbit.ClientRabbitRenderer;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepRenderer;
import com.jeff.pets.client.rendering.vanilla.shulker.ClientShulkerRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ClientSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.slime.ClientSlimeRenderer;
import com.jeff.pets.client.rendering.vanilla.squid.ClientSquidRenderer;
import com.jeff.pets.client.rendering.vanilla.wither.ClientWitherRenderer;
import com.jeff.pets.client.rendering.vanilla.witherskeleton.ClientWitherSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie_pigman.ClientZombiePigmanRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.jeff.pets.mob.custom.aquatic.Koi;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.jeff.pets.mob.custom.first.Duck;
import com.jeff.pets.mob.custom.first.Penguin;
import com.jeff.pets.mob.custom.first.Racoon;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.jeff.pets.mob.vanilla.boss.ClientWither;
import com.jeff.pets.mob.vanilla.hostile.*;
import com.jeff.pets.mob.vanilla.neutral.*;
import com.jeff.pets.mob.vanilla.passive.*;
import org.lwjgl.glfw.GLFW;

import java.util.*;

public class PetsClientInitializer implements ClientModInitializer {
    public static List<String> ADDONS = new ArrayList();
    public static KeyBinding keyMapping;
    public static final Map<Class<? extends Entity>, Factory> renderSupplierMap = new HashMap();
    public static final Map<EntityRendererDispatcher, Context> renderManagerMap = new WeakHashMap();

    public PetsClientInitializer() {
    }

    private static PetsClientInitializer get() {
        return new PetsClientInitializer();
    }

    public void onInitializeClient() {
        this.createKeyBinding();
        register(Head.class, HeadRenderer::new);
        register(Duck.class, (context, context2) -> {
            return new DuckRenderer(context, context2);
        });
        register(Racoon.class, RacoonRenderer::new);
        register(Penguin.class, PenguinRenderer::new);
        register(ClientSheep.class, ClientSheepRenderer::new);
        register(ClientCat.class, ClientCatRenderer::new);
        register(ClientBat.class, ClientBatRenderer::new);
        register(ClientChicken.class, ClientChickenRenderer::new);
        register(ClientCow.class, ClientCowRenderer::new);
        register(ClientDonkey.class, ClientDonkeyRenderer::new);
        register(ClientHorse.class, ClientHorseRenderer::new);
        register(ClientMooshroom.class, ClientMooshroomRenderer::new);
        register(ClientPig.class, ClientPigRenderer::new);
        register(ClientRabbit.class, ClientRabbitRenderer::new);
        register(ClientSquid.class, ClientSquidRenderer::new);
        register(ClientIronGolem.class, ClientIronGolemRenderer::new);
        register(ClientElderGuardian.class, ClientElderGuardianRenderer::new);
        register(ClientCreeper.class, ClientCreeperRenderer::new);
        register(ClientEndermite.class, ClientEndermiteRenderer::new);
        register(ClientGhast.class, ClientGhastRenderer::new);
        register(ClientGuardian.class, ClientGuardianRenderer::new);
        register(ClientMagmaCube.class, ClientMagmaCubeRenderer::new);
        register(ClientShulker.class, ClientShulkerRenderer::new);
        register(ClientSkeleton.class, ClientSkeletonRenderer::new);
        register(ClientSlime.class, ClientSlimeRenderer::new);
        register(ClientWitherSkeleton.class, ClientWitherSkeletonRenderer::new);
        register(ClientZombie.class, ClientZombieRenderer::new);
        register(ClientEnderDragon.class, ClientEnderDragonRenderer::new);
        register(ClientWither.class, ClientWitherRenderer::new);
        register(DumboOctopus.class, DumboOctopusRenderer::new);
        register(Koi.class, KoiRenderer::new);
        register(Stingray.class, StingrayRenderer::new);
        register(ClientZombiePigman.class, ClientZombiePigmanRenderer::new);
    }

    void createKeyBinding() {
    }

    public static void register(Class<? extends Entity> entityClass, Factory factory) {
        synchronized(renderSupplierMap) {
            renderSupplierMap.put(entityClass, factory);
            Iterator var3 = renderManagerMap.keySet().iterator();

            while(var3.hasNext()) {
                EntityRendererDispatcher manager = (EntityRendererDispatcher)var3.next();
                ((Context)renderManagerMap.get(manager)).rendererMap.put(entityClass, factory.create(manager, (Context)renderManagerMap.get(manager)));
            }

        }
    }

    @FunctionalInterface
    public interface Factory {
        EntityRenderer create(EntityRendererDispatcher var1, Context var2);
    }

    public static final class Context {
        private final Map<Class<? extends Entity>, EntityRenderer> rendererMap;

        public Context(Map<Class<? extends Entity>, EntityRenderer> rendererMap) {
            this.rendererMap = rendererMap;
        }
    }
}
