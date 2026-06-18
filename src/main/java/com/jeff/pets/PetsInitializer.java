package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.mob.aprilfools.*;
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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import static com.jeff.pets.PetsInitializer.Entities.*;
import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
@Mod(MOD_ID)
public class PetsInitializer {
    public static final String MOD_ID = "pets_mod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        public static final DeferredRegister<@NotNull EntityType<?>> ENTITY_TYPES =
                DeferredRegister.create(Registries.ENTITY_TYPE, PetsInitializer.MOD_ID);

    private static ResourceKey<@NotNull EntityType<?>> createResourceKey(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, path));
    }

    public PetsInitializer(IEventBus bus) {
        DeferredHolder<?, ?> ignored = ALLAY;
        ENTITY_TYPES.register(bus);
        bus.addListener(this::onInitialize);

    }

    public void onInitialize(EntityAttributeCreationEvent event) {

        event.put(Entities.RACOON.get(), Racoon.createAttributes().build());
        event.put(Entities.DUCK.get(), Duck.createAttributes().build());
        event.put(Entities.PENGUIN.get(), Penguin.createAttributes().build());
        event.put(Entities.SHEEP.get(), ClientSheep.createAttributes().build());
        event.put(Entities.CAT.get(), ClientCat.createAttributes().build());
        event.put(Entities.ALLAY.get(), ClientAllay.createAttributes().build());
        event.put(Entities.ARMADILLO.get(), ClientArmadillo.createAttributes().build());
        event.put(Entities.AXOLOTL.get(), ClientAxolotl.createAttributes().build());
        event.put(Entities.BAT.get(), ClientBat.createAttributes().build());
        event.put(Entities.CAMEL.get(), ClientCamel.createAttributes().build());
        event.put(Entities.CHICKEN.get(), ClientChicken.createAttributes().build());
        event.put(Entities.COD.get(), ClientCod.createAttributes().build());
        event.put(Entities.COPPER_GOLEM.get(), ClientCopperGolem.createAttributes().build());
        event.put(Entities.COW.get(), ClientCow.createAttributes().build());
        event.put(Entities.DONKEY.get(), ClientDonkey.createAttributes().build());
        event.put(Entities.FROG.get(), ClientFrog.createAttributes().build());
        event.put(Entities.HORSE.get(), ClientHorse.createAttributes().build());
        event.put(Entities.MOOSHROOM.get(), ClientMooshroom.createAttributes().build());
        event.put(Entities.PARROT.get(), ClientParrot.createAttributes().build());
        event.put(Entities.PIG.get(), ClientPig.createAttributes().build());
        event.put(Entities.RABBIT.get(), ClientRabbit.createAttributes().build());
        event.put(Entities.SALMON.get(), ClientSalmon.createAttributes().build());
        event.put(Entities.SNIFFER.get(), ClientSniffer.createAttributes().build());
        event.put(Entities.SNOW_GOLEM.get(), ClientSnowGolem.createAttributes().build());
        event.put(Entities.SQUID.get(), ClientSquid.createAttributes().build());
        event.put(Entities.STRIDER.get(), ClientStrider.createAttributes().build());
        event.put(Entities.TADPOLE.get(), ClientTadpole.createAttributes().build());
        event.put(Entities.TROPICAL_FISH.get(), ClientTropicalFish.createAttributes().build());
        event.put(Entities.TURTLE.get(), ClientTurtle.createAttributes().build());
        event.put(Entities.VILLAGER.get(), ClientVillager.createAttributes().build());
        event.put(Entities.WANDERING_TRADER.get(), ClientWanderingTrader.createAttributes().build());
        event.put(Entities.BEE.get(), ClientBee.createAttributes().build());
        event.put(Entities.CAVE_SPIDER.get(), ClientCaveSpider.createAttributes().build());
        event.put(Entities.DOLPHIN.get(), ClientDolphin.createAttributes().build());
        event.put(Entities.ENDERMAN.get(), ClientEnderman.createAttributes().build());
        event.put(Entities.FOX.get(), ClientFox.createAttributes().build());
        event.put(Entities.GOAT.get(), ClientGoat.createAttributes().build());
        event.put(Entities.IRON_GOLEM.get(), ClientIronGolem.createAttributes().build());
        event.put(Entities.LLAMA.get(), ClientLlama.createAttributes().build());
        event.put(Entities.NAUTILUS.get(), ClientNautilus.createAttributes().build());
        event.put(Entities.PANDA.get(), ClientPanda.createAttributes().build());
        event.put(Entities.PIGLIN.get(), ClientPiglin.createAttributes().build()); // Fixed matching target type
        event.put(Entities.POLAR_BEAR.get(), ClientPolarBear.createAttributes().build());
        event.put(Entities.PUFFERFISH.get(), ClientPufferFish.createAttributes().build());
        event.put(Entities.SPIDER.get(), ClientSpider.createAttributes().build());
        event.put(Entities.WOLF.get(), ClientWolf.createAttributes().build());
        event.put(Entities.BLAZE.get(), ClientBlaze.createAttributes().build());
        event.put(Entities.BREEZE.get(), ClientBreeze.createAttributes().build());
        event.put(Entities.CREAKING.get(), ClientCreaking.createAttributes().build());
        event.put(Entities.CREEPER.get(), ClientCreeper.createAttributes().build());
        event.put(Entities.ELDER_GUARDIAN_COOKIE.get(), ClientElderGuardian.createAttributes().build());
        event.put(Entities.ENDERMITE.get(), ClientEndermite.createAttributes().build());
        event.put(Entities.EVOKER.get(), ClientEvoker.createAttributes().build());
        event.put(Entities.GHAST.get(), ClientGhast.createAttributes().build());
        event.put(Entities.HAPPY_GHAST.get(), ClientHappyGhast.createAttributes().build());
        event.put(Entities.GUARDIAN.get(), ClientGuardian.createAttributes().build());
        event.put(Entities.HOGLIN.get(), ClientHoglin.createAttributes().build());
        event.put(Entities.MAGMA_CUBE.get(), ClientMagmaCube.createAttributes().build());
        event.put(Entities.PHANTOM.get(), ClientPhantom.createAttributes().build());
        event.put(Entities.PILLAGER.get(), ClientPillager.createAttributes().build());
        event.put(Entities.RAVAGER.get(), ClientRavager.createAttributes().build());
        event.put(Entities.SHULKER.get(), ClientShulker.createAttributes().build());
        event.put(Entities.SILVERFISH.get(), ClientSilverfish.createAttributes().build());
        event.put(Entities.SKELETON.get(), ClientSkeleton.createAttributes().build());
        event.put(Entities.SLIME.get(), ClientSlime.createAttributes().build());
        event.put(Entities.VEX.get(), ClientVex.createAttributes().build());
        event.put(Entities.VINDICATOR.get(), ClientVindicator.createAttributes().build());
        event.put(Entities.WARDEN.get(), ClientWarden.createAttributes().build());
        event.put(Entities.WITCH.get(), ClientWitch.createAttributes().build());
        event.put(Entities.ZOMBIE.get(), ClientZombie.createAttributes().build());
        event.put(Entities.ZOMBIE_VILLAGER.get(), ClientZombieVillager.createAttributes().build());
        event.put(Entities.HUSK.get(), ClientHusk.createAttributes().build());
        event.put(Entities.DROWNED.get(), ClientDrowned.createAttributes().build());
        event.put(Entities.BOGGED.get(), ClientBogged.createAttributes().build());
        event.put(Entities.PARCHED.get(), ClientParched.createAttributes().build());
        event.put(Entities.STRAY.get(), ClientStray.createAttributes().build());
        event.put(Entities.WITHER_SKELETON.get(), ClientWitherSkeleton.createAttributes().build());
        event.put(Entities.ENDER_DRAGON.get(), ClientEnderDragon.createAttributes().build());
        event.put(Entities.WITHER.get(), ClientWither.createAttributes().build());
        event.put(Entities.ANGRY_GHAST.get(), AngryGhast.createAttributes().build());
        event.put(Entities.BATATO.get(), Batato.createAttributes().build());
        event.put(Entities.DIAMOND_CHICKEN.get(), DiamondChicken.createAttributes().build());
        event.put(Entities.LOVE_GOLEM.get(), LoveGolem.createAttributes().build());
        event.put(Entities.MEGA_SPUD.get(), MegaSpud.createAttributes().build());
        event.put(Entities.MOON_COW.get(), MoonCow.createAttributes().build());
        event.put(Entities.NERD_CREEPER.get(), NerdCreeper.createAttributes().build());
        event.put(Entities.PINK_WITHER.get(), PinkWither.createAttributes().build());
        event.put(Entities.PLAGUEWHALE_SLAB.get(), PlaguewhaleSlab.createAttributes().build());
        event.put(Entities.POISONOUS_POTATO_ZOMBIE.get(), PoisonousPotatoZombie.createAttributes().build());
        event.put(Entities.RAY_TRACING.get(), RayTracing.createAttributes().build());
        event.put(Entities.REDSTONE_BUG.get(), RedstoneBug.createAttributes().build());
        event.put(Entities.SMILING_CREEPER.get(), SmilingCreeper.createAttributes().build());
        event.put(Entities.TOXIFIN_SLAB.get(), ToxifinSlab.createAttributes().build());
        event.put(Entities.POTATO_HUSK.get(), PotatoHusk.createAttributes().build());
        event.put(Entities.HEAD.get(), Head.createAttributes().build());
        event.put(Entities.TRAITOR.get(), Traitor.createAttributes().build());
        event.put(Entities.DUMBO_OCTOPUS.get(), DumboOctopus.createAttributes().build());
        event.put(Entities.KOI.get(), Koi.createAttributes().build());
        event.put(Entities.STINGRAY.get(), Stingray.createAttributes().build());
        event.put(Entities.SULFUR_CUBE.get(), ClientSulfurCube.createAttributes().build());

        PetsSounds.initialize();

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
    public static class Entities {
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Racoon>> RACOON =
                ENTITY_TYPES.register("racoon", () ->
                        EntityType.Builder.of(Racoon::new, MobCategory.CREATURE)
                                .sized(1.0f, 1.0f)
                                .eyeHeight(1.0f)
                                .build(createResourceKey("racoon"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientAllay>> ALLAY =
                ENTITY_TYPES.register("clientallay", () ->
                        EntityType.Builder.of(ClientAllay::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.35f, 0.6f)
                                .eyeHeight(0.6f)
                                .build(createResourceKey("clientallay"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientArmadillo>> ARMADILLO =
                ENTITY_TYPES.register("clientarmadillo", () ->
                        EntityType.Builder.of(ClientArmadillo::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.65f)
                                .eyeHeight(0.65f)
                                .build(createResourceKey("clientarmadillo"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientAxolotl>> AXOLOTL =
                ENTITY_TYPES.register("clientaxolotl", () ->
                        EntityType.Builder.of(ClientAxolotl::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 1.0f)
                                .eyeHeight(1.0f)
                                .build(createResourceKey("clientaxolotl"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientBat>> BAT =
                ENTITY_TYPES.register("clientbat", () ->
                        EntityType.Builder.of(ClientBat::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)
                                .eyeHeight(0.9f)
                                .build(createResourceKey("clientbat"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCamel>> CAMEL =
                ENTITY_TYPES.register("clientcamel", () ->
                        EntityType.Builder.of(ClientCamel::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.7f, 2.375f)
                                .eyeHeight(2.375f)
                                .build(createResourceKey("clientcamel"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Duck>> DUCK =
                ENTITY_TYPES.register("duck", () ->
                        EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
                                .sized(0.4f, 0.7f)
                                .eyeHeight(1.0f)
                                .build(createResourceKey("duck"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Penguin>> PENGUIN =
                ENTITY_TYPES.register("penguin", () ->
                        EntityType.Builder.of(Penguin::new, MobCategory.AMBIENT)
                                .sized(1.0f, 1.5f)
                                .eyeHeight(1.0f)
                                .build(createResourceKey("penguin"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSheep>> SHEEP =
                ENTITY_TYPES.register("clientsheep", () ->
                        EntityType.Builder.of(ClientSheep::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.3f)
                                .eyeHeight(1.3f)
                                .build(createResourceKey("clientsheep"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCat>> CAT =
                ENTITY_TYPES.register("clientcat", () ->
                        EntityType.Builder.of(ClientCat::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.7f)
                                .eyeHeight(1.0f)
                                .build(createResourceKey("clientcat"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientChicken>> CHICKEN =
                ENTITY_TYPES.register("clientchicken", () ->
                        EntityType.Builder.of(ClientChicken::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.7f)
                                .eyeHeight(0.7f)
                                .build(createResourceKey("clientchicken"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCod>> COD =
                ENTITY_TYPES.register("clientcod", () ->
                        EntityType.Builder.of(ClientCod::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.3f)
                                .eyeHeight(0.3f)
                                .build(createResourceKey("clientcod"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCopperGolem>> COPPER_GOLEM =
                ENTITY_TYPES.register("clientcoppergolem", () ->
                        EntityType.Builder.of(ClientCopperGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.49f, 0.98f)
                                .eyeHeight(0.98f)
                                .build(createResourceKey("clientcoppergolem"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCow>> COW =
                ENTITY_TYPES.register("clientcow", () ->
                        EntityType.Builder.of(ClientCow::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)
                                .eyeHeight(1.4f)
                                .build(createResourceKey("clientcow"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientDonkey>> DONKEY =
                ENTITY_TYPES.register("clientdonkey", () ->
                        EntityType.Builder.of(ClientDonkey::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.5f)
                                .eyeHeight(1.5f)
                                .build(createResourceKey("clientdonkey"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientFrog>> FROG =
                ENTITY_TYPES.register("clientfrog", () ->
                        EntityType.Builder.of(ClientFrog::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.5f)
                                .eyeHeight(0.5f)
                                .build(createResourceKey("clientfrog"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientHorse>> HORSE =
                ENTITY_TYPES.register("clienthorse", () ->
                        EntityType.Builder.of(ClientHorse::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.6f)
                                .eyeHeight(1.6f)
                                .build(createResourceKey("clienthorse"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientMooshroom>> MOOSHROOM =
                ENTITY_TYPES.register("clientmooshroom", () ->
                        EntityType.Builder.of(ClientMooshroom::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)
                                .eyeHeight(1.4f)
                                .build(createResourceKey("clientmooshroom"))
                );

        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientParrot>> PARROT =
                ENTITY_TYPES.register("clientparrot", () ->
                        EntityType.Builder.of(ClientParrot::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)
                                .eyeHeight(0.9f)
                                .build(createResourceKey("clientparrot")));

        public static final ResourceKey<@NotNull EntityType<?>> PIG_KEY = createResourceKey("clientpig");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPig>> PIG =
                ENTITY_TYPES.register("clientpig", () ->
                        EntityType.Builder.of(ClientPig::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.9f)
                                .eyeHeight(0.9f)
                                .build(PIG_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> RABBIT_KEY = createResourceKey("clientrabbit");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientRabbit>> RABBIT =
                ENTITY_TYPES.register("clientrabbit", () ->
                        EntityType.Builder.of(ClientRabbit::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.5f)
                                .eyeHeight(0.5f)
                                .build(RABBIT_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SALMON_KEY = createResourceKey("clientsalmon");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSalmon>> SALMON =
                ENTITY_TYPES.register("clientsalmon", () ->
                        EntityType.Builder.of(ClientSalmon::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.35f, 0.2f)
                                .eyeHeight(0.2f)
                                .build(SALMON_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SNIFFER_KEY = createResourceKey("clientsniffer");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSniffer>> SNIFFER =
                ENTITY_TYPES.register("clientsniffer", () ->
                        EntityType.Builder.of(ClientSniffer::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.9f, 1.75f)
                                .eyeHeight(1.75f)
                                .build(SNIFFER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SNOW_GOLEM_KEY = createResourceKey("clientsnowgolem");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSnowGolem>> SNOW_GOLEM =
                ENTITY_TYPES.register("clientsnowgolem", () ->
                        EntityType.Builder.of(ClientSnowGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 1.9f)
                                .eyeHeight(1.9f)
                                .build(SNOW_GOLEM_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SQUID_KEY = createResourceKey("clientsquid");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSquid>> SQUID =
                ENTITY_TYPES.register("clientsquid", () ->
                        EntityType.Builder.of(ClientSquid::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.8f, -0.8f)
                                .eyeHeight(0.8f)
                                .build(SQUID_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> STRIDER_KEY = createResourceKey("clientstrider");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientStrider>> STRIDER =
                ENTITY_TYPES.register("clientstrider", () ->
                        EntityType.Builder.of(ClientStrider::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.7f)
                                .eyeHeight(1.7f)
                                .build(STRIDER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> TADPOLE_KEY = createResourceKey("clienttadpole");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientTadpole>> TADPOLE =
                ENTITY_TYPES.register("clienttadpole", () ->
                        EntityType.Builder.of(ClientTadpole::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .eyeHeight(0.3f)
                                .build(TADPOLE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> TROPICAL_FISH_KEY = createResourceKey("clienttropicalfish");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientTropicalFish>> TROPICAL_FISH =
                ENTITY_TYPES.register("clienttropicalfish", () ->
                        EntityType.Builder.of(ClientTropicalFish::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 1.0f)
                                .eyeHeight(1.0f)
                                .build(TROPICAL_FISH_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> TURTLE_KEY = createResourceKey("clientturtle");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientTurtle>> TURTLE =
                ENTITY_TYPES.register("clientturtle", () ->
                        EntityType.Builder.of(ClientTurtle::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.2f, 0.4f)
                                .eyeHeight(0.4f)
                                .build(TURTLE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> VILLAGER_KEY = createResourceKey("clientvillager");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientVillager>> VILLAGER =
                ENTITY_TYPES.register("clientvillager", () ->
                        EntityType.Builder.of(ClientVillager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(VILLAGER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> WANDERING_TRADER_KEY = createResourceKey("clientwanderingtrader");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientWanderingTrader>> WANDERING_TRADER =
                ENTITY_TYPES.register("clientwanderingtrader", () ->
                        EntityType.Builder.of(ClientWanderingTrader::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(WANDERING_TRADER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> BEE_KEY = createResourceKey("clientbee");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientBee>> BEE =
                ENTITY_TYPES.register("clientbee", () ->
                        EntityType.Builder.of(ClientBee::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.6f)
                                .eyeHeight(0.6f)
                                .build(BEE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> CAVE_SPIDER_KEY = createResourceKey("clientcavespider");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCaveSpider>> CAVE_SPIDER =
                ENTITY_TYPES.register("clientcavespider", () ->
                        EntityType.Builder.of(ClientCaveSpider::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.5f)
                                .eyeHeight(0.5f)
                                .build(CAVE_SPIDER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> DOLPHIN_KEY = createResourceKey("clientdolphin");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientDolphin>> DOLPHIN =
                ENTITY_TYPES.register("clientdolphin", () ->
                        EntityType.Builder.of(ClientDolphin::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.6f)
                                .eyeHeight(0.6f)
                                .build(DOLPHIN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ENDERMAN_KEY = createResourceKey("clientenderman");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientEnderman>> ENDERMAN =
                ENTITY_TYPES.register("clientenderman", () ->
                        EntityType.Builder.of(ClientEnderman::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 2.9f)
                                .eyeHeight(2.9f)
                                .build(ENDERMAN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> FOX_KEY = createResourceKey("clientfox");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientFox>> FOX =
                ENTITY_TYPES.register("clientfox", () ->
                        EntityType.Builder.of(ClientFox::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.7f)
                                .eyeHeight(0.7f)
                                .build(FOX_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> GOAT_KEY = createResourceKey("clientgoat");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientGoat>> GOAT =
                ENTITY_TYPES.register("clientgoat", () ->
                        EntityType.Builder.of(ClientGoat::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.3f)
                                .eyeHeight(1.3f)
                                .build(GOAT_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> IRON_GOLEM_KEY = createResourceKey("clientirongolem");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientIronGolem>> IRON_GOLEM =
                ENTITY_TYPES.register("clientirongolem", () ->
                        EntityType.Builder.of(ClientIronGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 2.7f)
                                .eyeHeight(2.7f)
                                .build(IRON_GOLEM_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> LLAMA_KEY = createResourceKey("clientllama");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientLlama>> LLAMA =
                ENTITY_TYPES.register("clientllama", () ->
                        EntityType.Builder.of(ClientLlama::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.87f)
                                .eyeHeight(1.87f)
                                .build(LLAMA_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> NAUTILUS_KEY = createResourceKey("clientnautilus");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientNautilus>> NAUTILUS =
                ENTITY_TYPES.register("clientnautilus", () ->
                        EntityType.Builder.of(ClientNautilus::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.875f, 0.95f)
                                .eyeHeight(0.95f)
                                .build(NAUTILUS_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PANDA_KEY = createResourceKey("clientpanda");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPanda>> PANDA =
                ENTITY_TYPES.register("clientpanda", () ->
                        EntityType.Builder.of(ClientPanda::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3f, 1.25f)
                                .eyeHeight(1.25f)
                                .build(PANDA_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PIGLIN_KEY = createResourceKey("clientpiglin");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPiglin>> PIGLIN =
                ENTITY_TYPES.register("clientpiglin", () ->
                        EntityType.Builder.of(ClientPiglin::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(PIGLIN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> POLAR_BEAR_KEY = createResourceKey("clientpolarbear");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPolarBear>> POLAR_BEAR =
                ENTITY_TYPES.register("clientpolarbear", () ->
                        EntityType.Builder.of(ClientPolarBear::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 1.4f)
                                .eyeHeight(1.4f)
                                .build(POLAR_BEAR_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PUFFERFISH_KEY = createResourceKey("clientpufferfish");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPufferFish>> PUFFERFISH =
                ENTITY_TYPES.register("clientpufferfish", () ->
                        EntityType.Builder.of(ClientPufferFish::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.7f)
                                .eyeHeight(0.7f)
                                .build(PUFFERFISH_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SPIDER_KEY = createResourceKey("clientspider");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSpider>> SPIDER =
                ENTITY_TYPES.register("clientspider", () ->
                        EntityType.Builder.of(ClientSpider::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 0.9f)
                                .eyeHeight(0.9f)
                                .build(SPIDER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> WOLF_KEY = createResourceKey("clientwolf");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientWolf>> WOLF =
                ENTITY_TYPES.register("clientwolf", () ->
                        EntityType.Builder.of(ClientWolf::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.85f)
                                .eyeHeight(0.85f)
                                .build(WOLF_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> BLAZE_KEY = createResourceKey("clientblaze");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientBlaze>> BLAZE =
                ENTITY_TYPES.register("clientblaze", () ->
                        EntityType.Builder.of(ClientBlaze::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.8f)
                                .eyeHeight(1.8f)
                                .build(BLAZE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> BREEZE_KEY = createResourceKey("clientbreeze");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientBreeze>> BREEZE =
                ENTITY_TYPES.register("clientbreeze", () ->
                        EntityType.Builder.of(ClientBreeze::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.77f)
                                .eyeHeight(1.77f)
                                .build(BREEZE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> CREAKING_KEY = createResourceKey("clientcreaking");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCreaking>> CREAKING =
                ENTITY_TYPES.register("clientcreaking", () ->
                        EntityType.Builder.of(ClientCreaking::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 2.7f)
                                .eyeHeight(2.7f)
                                .build(CREAKING_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> CREEPER_KEY = createResourceKey("clientcreeper");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientCreeper>> CREEPER =
                ENTITY_TYPES.register("clientcreeper", () ->
                        EntityType.Builder.of(ClientCreeper::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .eyeHeight(1.7f)
                                .build(CREEPER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ELDER_GUARDIAN_KEY = createResourceKey("clientelderguardian");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientElderGuardian>> ELDER_GUARDIAN_COOKIE =
                ENTITY_TYPES.register("clientelderguardian", () ->
                        EntityType.Builder.of(ClientElderGuardian::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.9975f, 1.9975f)
                                .eyeHeight(1.9975f)
                                .build(ELDER_GUARDIAN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ENDERMITE_KEY = createResourceKey("clientendermite");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientEndermite>> ENDERMITE =
                ENTITY_TYPES.register("clientendermite", () ->
                        EntityType.Builder.of(ClientEndermite::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .eyeHeight(0.3f)
                                .build(ENDERMITE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> EVOKER_KEY = createResourceKey("clientevoker");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientEvoker>> EVOKER =
                ENTITY_TYPES.register("clientevoker", () ->
                        EntityType.Builder.of(ClientEvoker::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(EVOKER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> GHAST_KEY = createResourceKey("clientghast");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientGhast>> GHAST =
                ENTITY_TYPES.register("clientghast", () ->
                        EntityType.Builder.of(ClientGhast::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(4.0f, 4.0f)
                                .eyeHeight(4.0f)
                                .build(GHAST_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> HAPPY_GHAST_KEY = createResourceKey("clienthappyghast");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientHappyGhast>> HAPPY_GHAST =
                ENTITY_TYPES.register("clienthappyghast", () ->
                        EntityType.Builder.of(ClientHappyGhast::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(4.0f, 4.0f)
                                .eyeHeight(4.0f)
                                .build(HAPPY_GHAST_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> GUARDIAN_KEY = createResourceKey("clientguardian");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientGuardian>> GUARDIAN =
                ENTITY_TYPES.register("clientguardian", () ->
                        EntityType.Builder.of(ClientGuardian::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.85f, 0.85f)
                                .eyeHeight(0.85f)
                                .build(GUARDIAN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> HOGLIN_KEY = createResourceKey("clienthoglin");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientHoglin>> HOGLIN =
                ENTITY_TYPES.register("clienthoglin", () ->
                        EntityType.Builder.of(ClientHoglin::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.4f)
                                .eyeHeight(1.4f)
                                .build(HOGLIN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> MAGMA_CUBE_KEY = createResourceKey("clientmagmacube");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientMagmaCube>> MAGMA_CUBE =
                ENTITY_TYPES.register("clientmagmacube", () ->
                        EntityType.Builder.of(ClientMagmaCube::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 2.0f)
                                .eyeHeight(2.0f)
                                .build(MAGMA_CUBE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PHANTOM_KEY = createResourceKey("clientphantom");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPhantom>> PHANTOM =
                ENTITY_TYPES.register("clientphantom", () ->
                        EntityType.Builder.of(ClientPhantom::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.5f)
                                .eyeHeight(0.5f)
                                .build(PHANTOM_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PILLAGER_KEY = createResourceKey("clientpillager");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientPillager>> PILLAGER =
                ENTITY_TYPES.register("clientpillager", () ->
                        EntityType.Builder.of(ClientPillager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(PILLAGER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> RAVAGER_KEY = createResourceKey("clientravager");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientRavager>> RAVAGER =
                ENTITY_TYPES.register("clientravager", () ->
                        EntityType.Builder.of(ClientRavager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.95f, 2.2f)
                                .eyeHeight(2.2f)
                                .build(RAVAGER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SHULKER_KEY = createResourceKey("clientshulker");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientShulker>> SHULKER =
                ENTITY_TYPES.register("clientshulker", () ->
                        EntityType.Builder.of(ClientShulker::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 2.0f)
                                .eyeHeight(2.0f)
                                .build(SHULKER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SILVERFISH_KEY = createResourceKey("clientsilverfish");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSilverfish>> SILVERFISH =
                ENTITY_TYPES.register("clientsilverfish", () ->
                        EntityType.Builder.of(ClientSilverfish::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .eyeHeight(0.3f)
                                .build(SILVERFISH_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SKELETON_KEY = createResourceKey("clientskeleton");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSkeleton>> SKELETON =
                ENTITY_TYPES.register("clientskeleton", () ->
                        EntityType.Builder.of(ClientSkeleton::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(SKELETON_KEY)
                );
        public static final ResourceKey<@NotNull EntityType<?>> SLIME_KEY = createResourceKey("clientslime");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSlime>> SLIME =
                ENTITY_TYPES.register("clientslime", () ->
                        EntityType.Builder.of(ClientSlime::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 2.0f)
                                .eyeHeight(2.0f)
                                .build(SLIME_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> VEX_KEY = createResourceKey("clientvex");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientVex>> VEX =
                ENTITY_TYPES.register("clientvex", () ->
                        EntityType.Builder.of(ClientVex::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.8f)
                                .eyeHeight(1.0f)
                                .build(VEX_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> VINDICATOR_KEY = createResourceKey("clientvindicator");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientVindicator>> VINDICATOR =
                ENTITY_TYPES.register("clientvindicator", () ->
                        EntityType.Builder.of(ClientVindicator::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(VINDICATOR_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> WARDEN_KEY = createResourceKey("clientwarden");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientWarden>> WARDEN =
                ENTITY_TYPES.register("clientwarden", () ->
                        EntityType.Builder.of(ClientWarden::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 2.9f)
                                .eyeHeight(2.9f)
                                .build(WARDEN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> WITCH_KEY = createResourceKey("clientwitch");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientWitch>> WITCH =
                ENTITY_TYPES.register("clientwitch", () ->
                        EntityType.Builder.of(ClientWitch::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(WITCH_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_KEY = createResourceKey("clientzombie");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientZombie>> ZOMBIE =
                ENTITY_TYPES.register("clientzombie", () ->
                        EntityType.Builder.of(ClientZombie::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(ZOMBIE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_VILLAGER_KEY = createResourceKey("clientzombievillager");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientZombieVillager>> ZOMBIE_VILLAGER =
                ENTITY_TYPES.register("clientzombievillager", () ->
                        EntityType.Builder.of(ClientZombieVillager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(2.0f)
                                .build(ZOMBIE_VILLAGER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> HUSK_KEY = createResourceKey("clienthusk");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientHusk>> HUSK =
                ENTITY_TYPES.register("clienthusk", () ->
                        EntityType.Builder.of(ClientHusk::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(2.0f)
                                .build(HUSK_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> DROWNED_KEY = createResourceKey("clientdrowned");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientDrowned>> DROWNED =
                ENTITY_TYPES.register("clientdrowned", () ->
                        EntityType.Builder.of(ClientDrowned::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(2.0f)
                                .build(DROWNED_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> BOGGED_KEY = createResourceKey("clientbogged");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientBogged>> BOGGED =
                ENTITY_TYPES.register("clientbogged", () ->
                        EntityType.Builder.of(ClientBogged::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(2.0f)
                                .build(BOGGED_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PARCHED_KEY = createResourceKey("clientparched");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientParched>> PARCHED =
                ENTITY_TYPES.register("clientparched", () ->
                        EntityType.Builder.of(ClientParched::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(PARCHED_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> STRAY_KEY = createResourceKey("clientstray");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientStray>> STRAY =
                ENTITY_TYPES.register("clientstray", () ->
                        EntityType.Builder.of(ClientStray::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(STRAY_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> WITHER_SKELETON_KEY = createResourceKey("clientwitherskeleton");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientWitherSkeleton>> WITHER_SKELETON =
                ENTITY_TYPES.register("clientwitherskeleton", () ->
                        EntityType.Builder.of(ClientWitherSkeleton::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(WITHER_SKELETON_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ENDER_DRAGON_KEY = createResourceKey("clientenderdragon");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientEnderDragon>> ENDER_DRAGON =
                ENTITY_TYPES.register("clientenderdragon", () ->
                        EntityType.Builder.of(ClientEnderDragon::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(16.0f, 8.0f)
                                .eyeHeight(1.0f)
                                .build(ENDER_DRAGON_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> WITHER_KEY = createResourceKey("clientwither");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientWither>> WITHER =
                ENTITY_TYPES.register("clientwither", () ->
                        EntityType.Builder.of(ClientWither::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 3.0f)
                                .eyeHeight(3.0f)
                                .build(WITHER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> ANGRY_GHAST_KEY = createResourceKey("clientangryghast");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<AngryGhast>> ANGRY_GHAST =
                ENTITY_TYPES.register("clientangryghast", () ->
                        EntityType.Builder.of(AngryGhast::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(4.0f, 4.0f)
                                .eyeHeight(4.0f)
                                .build(ANGRY_GHAST_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> BATATO_KEY = createResourceKey("batato");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Batato>> BATATO =
                ENTITY_TYPES.register("batato", () ->
                        EntityType.Builder.of(Batato::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)
                                .eyeHeight(0.9f)
                                .build(BATATO_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> DIAMOND_CHICKEN_KEY = createResourceKey("diamond_chicken");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<DiamondChicken>> DIAMOND_CHICKEN =
                ENTITY_TYPES.register("diamond_chicken", () ->
                        EntityType.Builder.of(DiamondChicken::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.7f)
                                .eyeHeight(0.7f)
                                .build(DIAMOND_CHICKEN_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> LOVE_GOLEM_KEY = createResourceKey("love_golem");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<LoveGolem>> LOVE_GOLEM =
                ENTITY_TYPES.register("love_golem", () ->
                        EntityType.Builder.of(LoveGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 2.7f)
                                .eyeHeight(3.0f)
                                .build(LOVE_GOLEM_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> MEGA_SPUD_KEY = createResourceKey("mega_spud");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<MegaSpud>> MEGA_SPUD =
                ENTITY_TYPES.register("mega_spud", () ->
                        EntityType.Builder.of(MegaSpud::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(9.0f, 12.0f)
                                .eyeHeight(12.0f)
                                .build(MEGA_SPUD_KEY)
                );
        public static final ResourceKey<@NotNull EntityType<?>> MOON_COW_KEY = createResourceKey("moon_cow");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<MoonCow>> MOON_COW =
                ENTITY_TYPES.register("moon_cow", () ->
                        EntityType.Builder.of(MoonCow::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)
                                .eyeHeight(1.4f)
                                .build(MOON_COW_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> NERD_CREEPER_KEY = createResourceKey("nerd_creeper");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<NerdCreeper>> NERD_CREEPER =
                ENTITY_TYPES.register("nerd_creeper", () ->
                        EntityType.Builder.of(NerdCreeper::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .eyeHeight(1.7f)
                                .build(NERD_CREEPER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PINK_WITHER_KEY = createResourceKey("pink_wither");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<PinkWither>> PINK_WITHER =
                ENTITY_TYPES.register("pink_wither", () ->
                        EntityType.Builder.of(PinkWither::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 3.0f)
                                .eyeHeight(3.0f)
                                .build(PINK_WITHER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> PLAGUEWHALE_SLAB_KEY = createResourceKey("plaguewhale_slab");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<PlaguewhaleSlab>> PLAGUEWHALE_SLAB =
                ENTITY_TYPES.register("plaguewhale_slab", () ->
                        EntityType.Builder.of(PlaguewhaleSlab::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.9975f, 1.9975f)
                                .eyeHeight(2.0f)
                                .build(PLAGUEWHALE_SLAB_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> POISONOUS_POTATO_ZOMBIE_KEY = createResourceKey("poisonous_potato_zombie");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<PoisonousPotatoZombie>> POISONOUS_POTATO_ZOMBIE =
                ENTITY_TYPES.register("poisonous_potato_zombie", () ->
                        EntityType.Builder.of(PoisonousPotatoZombie::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(POISONOUS_POTATO_ZOMBIE_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> RAY_TRACING_KEY = createResourceKey("ray_tracing");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<RayTracing>> RAY_TRACING =
                ENTITY_TYPES.register("ray_tracing", () ->
                        EntityType.Builder.of(RayTracing::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(RAY_TRACING_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> REDSTONE_BUG_KEY = createResourceKey("redstone_bug");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<RedstoneBug>> REDSTONE_BUG =
                ENTITY_TYPES.register("redstone_bug", () ->
                        EntityType.Builder.of(RedstoneBug::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .eyeHeight(0.3f)
                                .build(REDSTONE_BUG_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SMILING_CREEPER_KEY = createResourceKey("smiling_creeper");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<SmilingCreeper>> SMILING_CREEPER =
                ENTITY_TYPES.register("smiling_creeper", () ->
                        EntityType.Builder.of(SmilingCreeper::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .eyeHeight(1.7f)
                                .build(SMILING_CREEPER_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> TOXIFIN_SLAB_KEY = createResourceKey("toxifin_slab");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ToxifinSlab>> TOXIFIN_SLAB =
                ENTITY_TYPES.register("toxifin_slab", () ->
                        EntityType.Builder.of(ToxifinSlab::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.85f, 0.85f)
                                .eyeHeight(0.85f)
                                .build(TOXIFIN_SLAB_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> POTATO_HUSK_KEY = createResourceKey("potatohusk");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<PotatoHusk>> POTATO_HUSK =
                ENTITY_TYPES.register("potatohusk", () ->
                        EntityType.Builder.of(PotatoHusk::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(POTATO_HUSK_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> HEAD_KEY = createResourceKey("head");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Head>> HEAD =
                ENTITY_TYPES.register("head", () ->
                        EntityType.Builder.of(Head::new, MobCategory.CREATURE)
                                .sized(0.5f, 0.5f)
                                .eyeHeight(0.5f)
                                .build(HEAD_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> TRAITOR_KEY = createResourceKey("traitor");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Traitor>> TRAITOR =
                ENTITY_TYPES.register("traitor", () ->
                        EntityType.Builder.of(Traitor::new, MobCategory.CREATURE)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .eyeHeight(1.95f)
                                .build(TRAITOR_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> DUMBO_OCTOPUS_KEY = createResourceKey("dumbo_octopus");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<DumboOctopus>> DUMBO_OCTOPUS =
                ENTITY_TYPES.register("dumbo_octopus", () ->
                        EntityType.Builder.of(DumboOctopus::new, MobCategory.WATER_AMBIENT)
                                .sized(0.5f, 0.5f)
                                .eyeHeight(0.5f)
                                .build(DUMBO_OCTOPUS_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> KOI_KEY = createResourceKey("koi");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Koi>> KOI =
                ENTITY_TYPES.register("koi", () ->
                        EntityType.Builder.of(Koi::new, MobCategory.WATER_AMBIENT)
                                .sized(0.6f, 0.6f)
                                .eyeHeight(0.6f)
                                .build(KOI_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> STINGRAY_KEY = createResourceKey("stingray");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<Stingray>> STINGRAY =
                ENTITY_TYPES.register("stingray", () ->
                        EntityType.Builder.of(Stingray::new, MobCategory.WATER_AMBIENT)
                                .sized(1.0f, 0.4f)
                                .eyeHeight(0.4f)
                                .build(STINGRAY_KEY)
                );

        public static final ResourceKey<@NotNull EntityType<?>> SULFUR_CUBE_KEY = createResourceKey("sulfur_cube");
        public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<ClientSulfurCube>> SULFUR_CUBE =
                ENTITY_TYPES.register("sulfur_cube", () ->
                        EntityType.Builder.of(ClientSulfurCube::new, MobCategory.WATER_AMBIENT)
                                .sized(0.98f, 0.98f)
                                .eyeHeight(0.6f)
                                .build(STINGRAY_KEY)
                );
    }
}