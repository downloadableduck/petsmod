package com.jeff.pets;

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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
@Mod("pets_mod")
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PetsInitializer {
    public static final String MOD_ID = "pets_mod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    static {
        MinecraftForge.EVENT_BUS.register(PetsInitializer.class);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(PetsInitializer.class);
        Entities.ENTITY_TYPES.register(bus);
        RegistryObject<?> ignored = Entities.ALLAY;
        PetsSounds.initialize(bus);
    }

    public PetsInitializer() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(PetsInitializer.class);
        Entities.ENTITY_TYPES.register(bus);
        RegistryObject<?> ignored = Entities.ALLAY;
    }

    @SubscribeEvent
    public static void onInitialize(EntityAttributeCreationEvent event) {

        event.put(Entities.RACOON.get(), Racoon.createAttributes().build());
        event.put(Entities.DUCK.get(), Duck.createAttributes().build());
        event.put(Entities.PENGUIN.get(), Penguin.createAttributes().build());
        event.put(Entities.SHEEP.get(), ClientSheep.createAttributes().build());
        event.put(Entities.CAT.get(), ClientCat.createAttributes().build());
        event.put(Entities.ALLAY.get(), ClientAllay.createAttributes().build());
        event.put(Entities.AXOLOTL.get(), ClientAxolotl.createAttributes().build());
        event.put(Entities.BAT.get(), ClientBat.createAttributes().build());
        event.put(Entities.CHICKEN.get(), ClientChicken.createAttributes().build());
        event.put(Entities.COD.get(), ClientCod.createAttributes().build());
        event.put(Entities.COW.get(), ClientCow.createAttributes().build());
        event.put(Entities.DONKEY.get(), ClientDonkey.createAttributes().build());
        event.put(Entities.FROG.get(), ClientFrog.createAttributes().build());
        event.put(Entities.HORSE.get(), ClientHorse.createAttributes().build());
        event.put(Entities.MOOSHROOM.get(), ClientMooshroom.createAttributes().build());
        event.put(Entities.PARROT.get(), ClientParrot.createAttributes().build());
        event.put(Entities.PIG.get(), ClientPig.createAttributes().build());
        event.put(Entities.RABBIT.get(), ClientRabbit.createAttributes().build());
        event.put(Entities.SALMON.get(), ClientSalmon.createAttributes().build());
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
        event.put(Entities.PANDA.get(), ClientPanda.createAttributes().build());
        event.put(Entities.PIGLIN.get(), ClientPiglin.createAttributes().build()); // Fixed matching target type
        event.put(Entities.POLAR_BEAR.get(), ClientPolarBear.createAttributes().build());
        event.put(Entities.PUFFERFISH.get(), ClientPufferFish.createAttributes().build());
        event.put(Entities.SPIDER.get(), ClientSpider.createAttributes().build());
        event.put(Entities.WOLF.get(), ClientWolf.createAttributes().build());
        event.put(Entities.BLAZE.get(), ClientBlaze.createAttributes().build());
        event.put(Entities.CREEPER.get(), ClientCreeper.createAttributes().build());
        event.put(Entities.ELDER_GUARDIAN_COOKIE.get(), ClientElderGuardian.createAttributes().build());
        event.put(Entities.ENDERMITE.get(), ClientEndermite.createAttributes().build());
        event.put(Entities.EVOKER.get(), ClientEvoker.createAttributes().build());
        event.put(Entities.GHAST.get(), ClientGhast.createAttributes().build());
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

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }

    public static class Entities {

        public static final DeferredRegister<@NotNull EntityType<?>> ENTITY_TYPES =
                DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

        public static final RegistryObject<@NotNull EntityType<Racoon>> RACOON =
                ENTITY_TYPES.register("racoon", () ->
                        EntityType.Builder.of(Racoon::new, MobCategory.CREATURE)
                                .sized(1.0f, 1.0f)
                                
                                .build(("racoon"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientAllay>> ALLAY =
                ENTITY_TYPES.register("clientallay", () ->
                        EntityType.Builder.of(ClientAllay::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.35f, 0.6f)
                                
                                .build(("clientallay"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientAxolotl>> AXOLOTL =
                ENTITY_TYPES.register("clientaxolotl", () ->
                        EntityType.Builder.of(ClientAxolotl::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 1.0f)
                                
                                .build(("clientaxolotl"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientBat>> BAT =
                ENTITY_TYPES.register("clientbat", () ->
                        EntityType.Builder.of(ClientBat::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)
                                
                                .build(("clientbat"))
                );

        public static final RegistryObject< @NotNull EntityType<Duck>> DUCK =
                ENTITY_TYPES.register("duck", () ->
                        EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
                                .sized(0.4f, 0.7f)
                                
                                .build(("duck"))
                );

        public static final RegistryObject< @NotNull EntityType<Penguin>> PENGUIN =
                ENTITY_TYPES.register("penguin", () ->
                        EntityType.Builder.of(Penguin::new, MobCategory.AMBIENT)
                                .sized(1.0f, 1.5f)
                                
                                .build(("penguin"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientSheep>> SHEEP =
                ENTITY_TYPES.register("clientsheep", () ->
                        EntityType.Builder.of(ClientSheep::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.3f)
                                
                                .build(("clientsheep"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientCat>> CAT =
                ENTITY_TYPES.register("clientcat", () ->
                        EntityType.Builder.of(ClientCat::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.7f)
                                
                                .build(("clientcat"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientChicken>> CHICKEN =
                ENTITY_TYPES.register("clientchicken", () ->
                        EntityType.Builder.of(ClientChicken::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.7f)
                                
                                .build(("clientchicken"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientCod>> COD =
                ENTITY_TYPES.register("clientcod", () ->
                        EntityType.Builder.of(ClientCod::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.3f)
                                
                                .build(("clientcod"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientCow>> COW =
                ENTITY_TYPES.register("clientcow", () ->
                        EntityType.Builder.of(ClientCow::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)
                                
                                .build(("clientcow"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientDonkey>> DONKEY =
                ENTITY_TYPES.register("clientdonkey", () ->
                        EntityType.Builder.of(ClientDonkey::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.5f)
                                
                                .build(("clientdonkey"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientFrog>> FROG =
                ENTITY_TYPES.register("clientfrog", () ->
                        EntityType.Builder.of(ClientFrog::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.5f)
                                
                                .build(("clientfrog"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientHorse>> HORSE =
                ENTITY_TYPES.register("clienthorse", () ->
                        EntityType.Builder.of(ClientHorse::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.6f)
                                
                                .build(("clienthorse"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientMooshroom>> MOOSHROOM =
                ENTITY_TYPES.register("clientmooshroom", () ->
                        EntityType.Builder.of(ClientMooshroom::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)
                                
                                .build(("clientmooshroom"))
                );

        public static final RegistryObject< @NotNull EntityType<ClientParrot>> PARROT =
                ENTITY_TYPES.register("clientparrot", () ->
                        EntityType.Builder.of(ClientParrot::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)
                                
                                .build(("clientparrot")));

        public static final RegistryObject< @NotNull EntityType<ClientPig>> PIG =
                ENTITY_TYPES.register("clientpig", () ->
                        EntityType.Builder.of(ClientPig::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.9f)
                                .build("clientpig")
                );

        public static final RegistryObject< @NotNull EntityType<ClientRabbit>> RABBIT =
                ENTITY_TYPES.register("clientrabbit", () ->
                        EntityType.Builder.of(ClientRabbit::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.5f)
                                .build("clientrabbit")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSalmon>> SALMON =
                ENTITY_TYPES.register("clientsalmon", () ->
                        EntityType.Builder.of(ClientSalmon::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.35f, 0.2f)
                                .build("clientsalmon")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSnowGolem>> SNOW_GOLEM =
                ENTITY_TYPES.register("clientsnowgolem", () ->
                        EntityType.Builder.of(ClientSnowGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 1.9f)
                                .build("clientsnowgolem")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSquid>> SQUID =
                ENTITY_TYPES.register("clientsquid", () ->
                        EntityType.Builder.of(ClientSquid::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.8f, -0.8f)
                                .build("clientsquid")
                );

        public static final RegistryObject< @NotNull EntityType<ClientStrider>> STRIDER =
                ENTITY_TYPES.register("clientstrider", () ->
                        EntityType.Builder.of(ClientStrider::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.7f)
                                .build("clientstrider")
                );

        public static final RegistryObject< @NotNull EntityType<ClientTadpole>> TADPOLE =
                ENTITY_TYPES.register("clienttadpole", () ->
                        EntityType.Builder.of(ClientTadpole::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .build("clienttadpole")
                );

        public static final RegistryObject< @NotNull EntityType<ClientTropicalFish>> TROPICAL_FISH =
                ENTITY_TYPES.register("clienttropicalfish", () ->
                        EntityType.Builder.of(ClientTropicalFish::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 1.0f)
                                .build("clienttropicalfish")
                );

        public static final RegistryObject< @NotNull EntityType<ClientTurtle>> TURTLE =
                ENTITY_TYPES.register("clientturtle", () ->
                        EntityType.Builder.of(ClientTurtle::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.2f, 0.4f)
                                .build("clientturtle")
                );

        public static final RegistryObject< @NotNull EntityType<ClientVillager>> VILLAGER =
                ENTITY_TYPES.register("clientvillager", () ->
                        EntityType.Builder.of(ClientVillager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientvillager")
                );

        public static final RegistryObject< @NotNull EntityType<ClientWanderingTrader>> WANDERING_TRADER =
                ENTITY_TYPES.register("clientwanderingtrader", () ->
                        EntityType.Builder.of(ClientWanderingTrader::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientwanderingtrader")
                );

        public static final RegistryObject< @NotNull EntityType<ClientBee>> BEE =
                ENTITY_TYPES.register("clientbee", () ->
                        EntityType.Builder.of(ClientBee::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.6f)
                                .build("clientbee")
                );

        public static final RegistryObject< @NotNull EntityType<ClientCaveSpider>> CAVE_SPIDER =
                ENTITY_TYPES.register("clientcavespider", () ->
                        EntityType.Builder.of(ClientCaveSpider::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.5f)
                                .build("clientcavespider")
                );

        public static final RegistryObject< @NotNull EntityType<ClientDolphin>> DOLPHIN =
                ENTITY_TYPES.register("clientdolphin", () ->
                        EntityType.Builder.of(ClientDolphin::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.6f)
                                .build("clientdolphin")
                );

        public static final RegistryObject< @NotNull EntityType<ClientEnderman>> ENDERMAN =
                ENTITY_TYPES.register("clientenderman", () ->
                        EntityType.Builder.of(ClientEnderman::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 2.9f)
                                .build("clientenderman")
                );

        public static final RegistryObject< @NotNull EntityType<ClientFox>> FOX =
                ENTITY_TYPES.register("clientfox", () ->
                        EntityType.Builder.of(ClientFox::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.7f)
                                .build("clientfox")
                );

        public static final RegistryObject< @NotNull EntityType<ClientGoat>> GOAT =
                ENTITY_TYPES.register("clientgoat", () ->
                        EntityType.Builder.of(ClientGoat::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.3f)
                                .build("clientgoat")
                );

        public static final RegistryObject< @NotNull EntityType<ClientIronGolem>> IRON_GOLEM =
                ENTITY_TYPES.register("clientirongolem", () ->
                        EntityType.Builder.of(ClientIronGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 2.7f)
                                .build("clientirongolem")
                );

        public static final RegistryObject< @NotNull EntityType<ClientLlama>> LLAMA =
                ENTITY_TYPES.register("clientllama", () ->
                        EntityType.Builder.of(ClientLlama::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.87f)
                                .build("clientllama")
                );

        public static final RegistryObject< @NotNull EntityType<ClientPanda>> PANDA =
                ENTITY_TYPES.register("clientpanda", () ->
                        EntityType.Builder.of(ClientPanda::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3f, 1.25f)
                                .build("clientpanda")
                );

        public static final RegistryObject< @NotNull EntityType<ClientPiglin>> PIGLIN =
                ENTITY_TYPES.register("clientpiglin", () ->
                        EntityType.Builder.of(ClientPiglin::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientpiglin")
                );

        public static final RegistryObject< @NotNull EntityType<ClientPolarBear>> POLAR_BEAR =
                ENTITY_TYPES.register("clientpolarbear", () ->
                        EntityType.Builder.of(ClientPolarBear::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 1.4f)
                                .build("clientpolarbear")
                );

        public static final RegistryObject< @NotNull EntityType<ClientPufferFish>> PUFFERFISH =
                ENTITY_TYPES.register("clientpufferfish", () ->
                        EntityType.Builder.of(ClientPufferFish::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.7f)
                                .build("clientpufferfish")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSpider>> SPIDER =
                ENTITY_TYPES.register("clientspider", () ->
                        EntityType.Builder.of(ClientSpider::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 0.9f)
                                .build("clientspider")
                );

        public static final RegistryObject< @NotNull EntityType<ClientWolf>> WOLF =
                ENTITY_TYPES.register("clientwolf", () ->
                        EntityType.Builder.of(ClientWolf::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.85f)
                                .build("clientwolf")
                );

        public static final RegistryObject< @NotNull EntityType<ClientBlaze>> BLAZE =
                ENTITY_TYPES.register("clientblaze", () ->
                        EntityType.Builder.of(ClientBlaze::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.8f)
                                .build("clientblaze")
                );

        public static final RegistryObject< @NotNull EntityType<ClientCreeper>> CREEPER =
                ENTITY_TYPES.register("clientcreeper", () ->
                        EntityType.Builder.of(ClientCreeper::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .build("clientcreeper")
                );

        public static final RegistryObject< @NotNull EntityType<ClientElderGuardian>> ELDER_GUARDIAN_COOKIE =
                ENTITY_TYPES.register("clientelderguardian", () ->
                        EntityType.Builder.of(ClientElderGuardian::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.9975f, 1.9975f)
                                .build("clientelderguardian")
                );

        public static final RegistryObject< @NotNull EntityType<ClientEndermite>> ENDERMITE =
                ENTITY_TYPES.register("clientendermite", () ->
                        EntityType.Builder.of(ClientEndermite::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .build("clientendermite")
                );

        public static final RegistryObject< @NotNull EntityType<ClientEvoker>> EVOKER =
                ENTITY_TYPES.register("clientevoker", () ->
                        EntityType.Builder.of(ClientEvoker::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientevoker")
                );

        public static final RegistryObject< @NotNull EntityType<ClientGhast>> GHAST =
                ENTITY_TYPES.register("clientghast", () ->
                        EntityType.Builder.of(ClientGhast::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(4.0f, 4.0f)
                                .build("clientghast")
                );

        public static final RegistryObject< @NotNull EntityType<ClientGuardian>> GUARDIAN =
                ENTITY_TYPES.register("clientguardian", () ->
                        EntityType.Builder.of(ClientGuardian::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.85f, 0.85f)
                                .build("clientguardian")
                );

        public static final RegistryObject< @NotNull EntityType<ClientHoglin>> HOGLIN =
                ENTITY_TYPES.register("clienthoglin", () ->
                        EntityType.Builder.of(ClientHoglin::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.4f)
                                .build("clienthoglin")
                );

        public static final RegistryObject< @NotNull EntityType<ClientMagmaCube>> MAGMA_CUBE =
                ENTITY_TYPES.register("clientmagmacube", () ->
                        EntityType.Builder.of(ClientMagmaCube::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 2.0f)
                                .build("clientmagmacube")
                );

        public static final RegistryObject< @NotNull EntityType<ClientPhantom>> PHANTOM =
                ENTITY_TYPES.register("clientphantom", () ->
                        EntityType.Builder.of(ClientPhantom::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.5f)
                                .build("clientphantom")
                );

        public static final RegistryObject< @NotNull EntityType<ClientPillager>> PILLAGER =
                ENTITY_TYPES.register("clientpillager", () ->
                        EntityType.Builder.of(ClientPillager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientpillager")
                );

        public static final RegistryObject< @NotNull EntityType<ClientRavager>> RAVAGER =
                ENTITY_TYPES.register("clientravager", () ->
                        EntityType.Builder.of(ClientRavager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.95f, 2.2f)
                                .build("clientravager")
                );

        public static final RegistryObject< @NotNull EntityType<ClientShulker>> SHULKER =
                ENTITY_TYPES.register("clientshulker", () ->
                        EntityType.Builder.of(ClientShulker::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 2.0f)
                                .build("clientshulker")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSilverfish>> SILVERFISH =
                ENTITY_TYPES.register("clientsilverfish", () ->
                        EntityType.Builder.of(ClientSilverfish::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .build("clientsilverfish")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSkeleton>> SKELETON =
                ENTITY_TYPES.register("clientskeleton", () ->
                        EntityType.Builder.of(ClientSkeleton::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientskeleton")
                );

        public static final RegistryObject< @NotNull EntityType<ClientSlime>> SLIME =
                ENTITY_TYPES.register("clientslime", () ->
                        EntityType.Builder.of(ClientSlime::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 2.0f)
                                .build("clientslime")
                );

        public static final RegistryObject< @NotNull EntityType<ClientVex>> VEX =
                ENTITY_TYPES.register("clientvex", () ->
                        EntityType.Builder.of(ClientVex::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.8f)
                                .build("clientvex")
                );

        public static final RegistryObject< @NotNull EntityType<ClientVindicator>> VINDICATOR =
                ENTITY_TYPES.register("clientvindicator", () ->
                        EntityType.Builder.of(ClientVindicator::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientvindicator")
                );

        public static final RegistryObject< @NotNull EntityType<ClientWarden>> WARDEN =
                ENTITY_TYPES.register("clientwarden", () ->
                        EntityType.Builder.of(ClientWarden::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 2.9f)
                                .build("clientwarden")
                );

        public static final RegistryObject< @NotNull EntityType<ClientWitch>> WITCH =
                ENTITY_TYPES.register("clientwitch", () ->
                        EntityType.Builder.of(ClientWitch::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientwitch")
                );

        public static final RegistryObject< @NotNull EntityType<ClientZombie>> ZOMBIE =
                ENTITY_TYPES.register("clientzombie", () ->
                        EntityType.Builder.of(ClientZombie::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientzombie")
                );

        public static final RegistryObject< @NotNull EntityType<ClientZombieVillager>> ZOMBIE_VILLAGER =
                ENTITY_TYPES.register("clientzombievillager", () ->
                        EntityType.Builder.of(ClientZombieVillager::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientzombievillager")
                );

        public static final RegistryObject< @NotNull EntityType<ClientHusk>> HUSK =
                ENTITY_TYPES.register("clienthusk", () ->
                        EntityType.Builder.of(ClientHusk::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clienthusk")
                );

        public static final RegistryObject< @NotNull EntityType<ClientDrowned>> DROWNED =
                ENTITY_TYPES.register("clientdrowned", () ->
                        EntityType.Builder.of(ClientDrowned::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientdrowned")
                );

        public static final RegistryObject< @NotNull EntityType<ClientStray>> STRAY =
                ENTITY_TYPES.register("clientstray", () ->
                        EntityType.Builder.of(ClientStray::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientstray")
                );

        public static final RegistryObject< @NotNull EntityType<ClientWitherSkeleton>> WITHER_SKELETON =
                ENTITY_TYPES.register("clientwitherskeleton", () ->
                        EntityType.Builder.of(ClientWitherSkeleton::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientwitherskeleton")
                );

        public static final RegistryObject< @NotNull EntityType<ClientEnderDragon>> ENDER_DRAGON =
                ENTITY_TYPES.register("clientenderdragon", () ->
                        EntityType.Builder.of(ClientEnderDragon::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(16.0f, 8.0f)
                                .build("clientenderdragon")
                );

        public static final RegistryObject< @NotNull EntityType<ClientWither>> WITHER =
                ENTITY_TYPES.register("clientwither", () ->
                        EntityType.Builder.of(ClientWither::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 3.0f)
                                .build("clientwither")
                );

        public static final RegistryObject< @NotNull EntityType<AngryGhast>> ANGRY_GHAST =
                ENTITY_TYPES.register("clientangryghast", () ->
                        EntityType.Builder.of(AngryGhast::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(4.0f, 4.0f)
                                .build("clientangryghast")
                );

        public static final RegistryObject< @NotNull EntityType<Batato>> BATATO =
                ENTITY_TYPES.register("batato", () ->
                        EntityType.Builder.of(Batato::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)
                                .build("batato")
                );

        public static final RegistryObject< @NotNull EntityType<DiamondChicken>> DIAMOND_CHICKEN =
                ENTITY_TYPES.register("diamond_chicken", () ->
                        EntityType.Builder.of(DiamondChicken::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.7f)
                                .build("diamond_chicken")
                );

        public static final RegistryObject< @NotNull EntityType<LoveGolem>> LOVE_GOLEM =
                ENTITY_TYPES.register("love_golem", () ->
                        EntityType.Builder.of(LoveGolem::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 2.7f)
                                .build("love_golem")
                );

        public static final RegistryObject< @NotNull EntityType<MegaSpud>> MEGA_SPUD =
                ENTITY_TYPES.register("mega_spud", () ->
                        EntityType.Builder.of(MegaSpud::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(9.0f, 12.0f)
                                .build("mega_spud")
                );

        public static final RegistryObject< @NotNull EntityType<MoonCow>> MOON_COW =
                ENTITY_TYPES.register("moon_cow", () ->
                        EntityType.Builder.of(MoonCow::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)
                                .build("moon_cow")
                );

        public static final RegistryObject< @NotNull EntityType<NerdCreeper>> NERD_CREEPER =
                ENTITY_TYPES.register("nerd_creeper", () ->
                        EntityType.Builder.of(NerdCreeper::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .build("nerd_creeper")
                );

        public static final RegistryObject< @NotNull EntityType<PinkWither>> PINK_WITHER =
                ENTITY_TYPES.register("pink_wither", () ->
                        EntityType.Builder.of(PinkWither::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 3.0f)
                                .build("pink_wither")
                );

        public static final RegistryObject< @NotNull EntityType<PlaguewhaleSlab>> PLAGUEWHALE_SLAB =
                ENTITY_TYPES.register("plaguewhale_slab", () ->
                        EntityType.Builder.of(PlaguewhaleSlab::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(1.9975f, 1.9975f)
                                .build("plaguewhale_slab")
                );

        public static final RegistryObject< @NotNull EntityType<PoisonousPotatoZombie>> POISONOUS_POTATO_ZOMBIE =
                ENTITY_TYPES.register("poisonous_potato_zombie", () ->
                        EntityType.Builder.of(PoisonousPotatoZombie::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("poisonous_potato_zombie")
                );

        public static final RegistryObject< @NotNull EntityType<RayTracing>> RAY_TRACING =
                ENTITY_TYPES.register("ray_tracing", () ->
                        EntityType.Builder.of(RayTracing::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("ray_tracing")
                );

        public static final RegistryObject< @NotNull EntityType<RedstoneBug>> REDSTONE_BUG =
                ENTITY_TYPES.register("redstone_bug", () ->
                        EntityType.Builder.of(RedstoneBug::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .build("redstone_bug")
                );

        public static final RegistryObject< @NotNull EntityType<SmilingCreeper>> SMILING_CREEPER =
                ENTITY_TYPES.register("smiling_creeper", () ->
                        EntityType.Builder.of(SmilingCreeper::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .build("smiling_creeper")
                );

        public static final RegistryObject< @NotNull EntityType<ToxifinSlab>> TOXIFIN_SLAB =
                ENTITY_TYPES.register("toxifin_slab", () ->
                        EntityType.Builder.of(ToxifinSlab::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.85f, 0.85f)
                                .build("toxifin_slab")
                );

        public static final RegistryObject< @NotNull EntityType<PotatoHusk>> POTATO_HUSK =
                ENTITY_TYPES.register("potatohusk", () ->
                        EntityType.Builder.of(PotatoHusk::new, MobCategory.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("potatohusk")
                );

        public static final RegistryObject< @NotNull EntityType<Head>> HEAD =
                ENTITY_TYPES.register("head", () ->
                        EntityType.Builder.of(Head::new, MobCategory.CREATURE)
                                .sized(0.5f, 0.5f)
                                .build("head")
                );

        public static final RegistryObject< @NotNull EntityType<Traitor>> TRAITOR =
                ENTITY_TYPES.register("traitor", () ->
                        EntityType.Builder.of(Traitor::new, MobCategory.CREATURE)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("traitor")
                );

        public static final RegistryObject< @NotNull EntityType<DumboOctopus>> DUMBO_OCTOPUS =
                ENTITY_TYPES.register("dumbo_octopus", () ->
                        EntityType.Builder.of(DumboOctopus::new, MobCategory.WATER_AMBIENT)
                                .sized(0.5f, 0.5f)
                                .build("dumbo_octopus")
                );

        public static final RegistryObject< @NotNull EntityType<Koi>> KOI =
                ENTITY_TYPES.register("koi", () ->
                        EntityType.Builder.of(Koi::new, MobCategory.WATER_AMBIENT)
                                .sized(0.6f, 0.6f)
                                .build("koi")
                );

        public static final RegistryObject< @NotNull EntityType<Stingray>> STINGRAY =
                ENTITY_TYPES.register("stingray", () ->
                        EntityType.Builder.of(Stingray::new, MobCategory.WATER_AMBIENT)
                                .sized(1.0f, 0.4f)
                                .build("stingray")
                );
    }
}