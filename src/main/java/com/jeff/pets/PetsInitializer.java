package com.jeff.pets;

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
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
@Mod("pets_mod")
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PetsInitializer {
    public static final String MOD_ID = "pets_mod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    static {
        MinecraftForge.EVENT_BUS.register(PetsInitializer.class);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(PetsInitializer.class);
        Entities.ENTITY_TYPES.register(bus);
        RegistryObject<?> ignored = Entities.BAT;
        PetsSounds.initialize(bus);
    }

    public PetsInitializer() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(PetsInitializer.class);
        Entities.ENTITY_TYPES.register(bus);
        RegistryObject<?> ignored = Entities.BAT;
    }

    @SubscribeEvent
    public static void onInitialize(FMLCommonSetupEvent event) {

        //event.enqueueWork(() -> {
        GlobalEntityTypeAttributes.put(Entities.RACOON.get(), Racoon.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.DUCK.get(), Duck.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PENGUIN.get(), Penguin.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SHEEP.get(), ClientSheep.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.CAT.get(), ClientCat.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.BAT.get(), ClientBat.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.CHICKEN.get(), ClientChicken.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.COD.get(), ClientCod.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.COW.get(), ClientCow.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.DONKEY.get(), ClientDonkey.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.HORSE.get(), ClientHorse.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.MOOSHROOM.get(), ClientMooshroom.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PARROT.get(), ClientParrot.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PIG.get(), ClientPig.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.RABBIT.get(), ClientRabbit.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SALMON.get(), ClientSalmon.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SNOW_GOLEM.get(), ClientSnowGolem.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SQUID.get(), ClientSquid.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.STRIDER.get(), ClientStrider.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.TURTLE.get(), ClientTurtle.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.VILLAGER.get(), ClientVillager.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.WANDERING_TRADER.get(), ClientWanderingTrader.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.BEE.get(), ClientBee.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.CAVE_SPIDER.get(), ClientCaveSpider.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.DOLPHIN.get(), ClientDolphin.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.ENDERMAN.get(), ClientEnderman.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.FOX.get(), ClientFox.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.IRON_GOLEM.get(), ClientIronGolem.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.LLAMA.get(), ClientLlama.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PANDA.get(), ClientPanda.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PIGLIN.get(), ClientPiglin.createAttributes().build()); // Fixed matching target type
        GlobalEntityTypeAttributes.put(Entities.POLAR_BEAR.get(), ClientPolarBear.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PUFFERFISH.get(), ClientPufferFish.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SPIDER.get(), ClientSpider.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.WOLF.get(), ClientWolf.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.BLAZE.get(), ClientBlaze.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.CREEPER.get(), ClientCreeper.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.ELDER_GUARDIAN_COOKIE.get(), ClientElderGuardian.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.ENDERMITE.get(), ClientEndermite.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.EVOKER.get(), ClientEvoker.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.GHAST.get(), ClientGhast.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.GUARDIAN.get(), ClientGuardian.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.HOGLIN.get(), ClientHoglin.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.MAGMA_CUBE.get(), ClientMagmaCube.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PHANTOM.get(), ClientPhantom.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.PILLAGER.get(), ClientPillager.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.RAVAGER.get(), ClientRavager.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SHULKER.get(), ClientShulker.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SILVERFISH.get(), ClientSilverfish.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SKELETON.get(), ClientSkeleton.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.SLIME.get(), ClientSlime.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.VEX.get(), ClientVex.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.VINDICATOR.get(), ClientVindicator.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.WITCH.get(), ClientWitch.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.ZOMBIE.get(), ClientZombie.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.ZOMBIE_VILLAGER.get(), ClientZombieVillager.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.HUSK.get(), ClientHusk.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.DROWNED.get(), ClientDrowned.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.STRAY.get(), ClientStray.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.WITHER_SKELETON.get(), ClientWitherSkeleton.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.ENDER_DRAGON.get(), ClientEnderDragon.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.WITHER.get(), ClientWither.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.HEAD.get(), Head.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.DUMBO_OCTOPUS.get(), DumboOctopus.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.KOI.get(), Koi.createAttributes().build());
        GlobalEntityTypeAttributes.put(Entities.STINGRAY.get(), Stingray.createAttributes().build());
        //});

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }

    public static class Entities {

        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
                DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

        public static final RegistryObject<EntityType<Racoon>> RACOON =
                ENTITY_TYPES.register("racoon", () ->
                        EntityType.Builder.of((EntityType<Racoon> type, World World) -> new Racoon(type, World), EntityClassification.CREATURE)
                                .sized(1.0f, 1.0f)

                                .build(("racoon"))
                );

        public static final RegistryObject<EntityType<ClientBat>> BAT =
                ENTITY_TYPES.register("clientbat", () ->
                        EntityType.Builder.of((EntityType<ClientBat> type, World World) -> new ClientBat(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)

                                .build(("clientbat"))
                );

        public static final RegistryObject<EntityType<Duck>> DUCK =
                ENTITY_TYPES.register("duck", () ->
                        EntityType.Builder.of((EntityType<Duck> type, World World) -> new Duck(type, World), EntityClassification.CREATURE)
                                .sized(0.4f, 0.7f)

                                .build(("duck"))
                );

        public static final RegistryObject<EntityType<Penguin>> PENGUIN =
                ENTITY_TYPES.register("penguin", () ->
                        EntityType.Builder.of((EntityType<Penguin> type, World World) -> new Penguin(type, World), EntityClassification.AMBIENT)
                                .sized(1.0f, 1.5f)

                                .build(("penguin"))
                );

        public static final RegistryObject<EntityType<ClientSheep>> SHEEP =
                ENTITY_TYPES.register("clientsheep", () ->
                        EntityType.Builder.of((EntityType<ClientSheep> type, World World) -> new ClientSheep(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.3f)

                                .build(("clientsheep"))
                );

        public static final RegistryObject<EntityType<ClientCat>> CAT =
                ENTITY_TYPES.register("clientcat", () ->
                        EntityType.Builder.of((EntityType<ClientCat> type, World World) -> new ClientCat(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.7f)

                                .build(("clientcat"))
                );

        public static final RegistryObject<EntityType<ClientChicken>> CHICKEN =
                ENTITY_TYPES.register("clientchicken", () ->
                        EntityType.Builder.of((EntityType<ClientChicken> type, World World) -> new ClientChicken(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.7f)

                                .build(("clientchicken"))
                );

        public static final RegistryObject<EntityType<ClientCod>> COD =
                ENTITY_TYPES.register("clientcod", () ->
                        EntityType.Builder.of((EntityType<ClientCod> type, World World) -> new ClientCod(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.3f)

                                .build(("clientcod"))
                );

        public static final RegistryObject<EntityType<ClientCow>> COW =
                ENTITY_TYPES.register("clientcow", () ->
                        EntityType.Builder.of((EntityType<ClientCow> type, World World) -> new ClientCow(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)

                                .build(("clientcow"))
                );

        public static final RegistryObject<EntityType<ClientDonkey>> DONKEY =
                ENTITY_TYPES.register("clientdonkey", () ->
                        EntityType.Builder.of((EntityType<ClientDonkey> type, World World) -> new ClientDonkey(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.5f)

                                .build(("clientdonkey"))
                );

        public static final RegistryObject<EntityType<ClientHorse>> HORSE =
                ENTITY_TYPES.register("clienthorse", () ->
                        EntityType.Builder.of((EntityType<ClientHorse> type, World World) -> new ClientHorse(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.6f)

                                .build(("clienthorse"))
                );

        public static final RegistryObject<EntityType<ClientMooshroom>> MOOSHROOM =
                ENTITY_TYPES.register("clientmooshroom", () ->
                        EntityType.Builder.of((EntityType<ClientMooshroom> type, World World) -> new ClientMooshroom(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.4f)

                                .build(("clientmooshroom"))
                );

        public static final RegistryObject<EntityType<ClientParrot>> PARROT =
                ENTITY_TYPES.register("clientparrot", () ->
                        EntityType.Builder.of((EntityType<ClientParrot> type, World World) -> new ClientParrot(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.5f, 0.9f)

                                .build(("clientparrot")));

        public static final RegistryObject<EntityType<ClientPig>> PIG =
                ENTITY_TYPES.register("clientpig", () ->
                        EntityType.Builder.of((EntityType<ClientPig> type, World World) -> new ClientPig(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.9f)
                                .build("clientpig")
                );

        public static final RegistryObject<EntityType<ClientRabbit>> RABBIT =
                ENTITY_TYPES.register("clientrabbit", () ->
                        EntityType.Builder.of((EntityType<ClientRabbit> type, World World) -> new ClientRabbit(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.5f)
                                .build("clientrabbit")
                );

        public static final RegistryObject<EntityType<ClientSalmon>> SALMON =
                ENTITY_TYPES.register("clientsalmon", () ->
                        EntityType.Builder.of((EntityType<ClientSalmon> type, World World) -> new ClientSalmon(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.35f, 0.2f)
                                .build("clientsalmon")
                );

        public static final RegistryObject<EntityType<ClientSnowGolem>> SNOW_GOLEM =
                ENTITY_TYPES.register("clientsnowgolem", () ->
                        EntityType.Builder.of((EntityType<ClientSnowGolem> type, World World) -> new ClientSnowGolem(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 1.9f)
                                .build("clientsnowgolem")
                );

        public static final RegistryObject<EntityType<ClientSquid>> SQUID =
                ENTITY_TYPES.register("clientsquid", () ->
                        EntityType.Builder.of((EntityType<ClientSquid> type, World World) -> new ClientSquid(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.8f, -0.8f)
                                .build("clientsquid")
                );

        public static final RegistryObject<EntityType<ClientStrider>> STRIDER =
                ENTITY_TYPES.register("clientstrider", () ->
                        EntityType.Builder.of((EntityType<ClientStrider> type, World World) -> new ClientStrider(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.7f)
                                .build("clientstrider")
                );


        public static final RegistryObject<EntityType<ClientTurtle>> TURTLE =
                ENTITY_TYPES.register("clientturtle", () ->
                        EntityType.Builder.of((EntityType<ClientTurtle> type, World World) -> new ClientTurtle(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.2f, 0.4f)
                                .build("clientturtle")
                );

        public static final RegistryObject<EntityType<ClientVillager>> VILLAGER =
                ENTITY_TYPES.register("clientvillager", () ->
                        EntityType.Builder.of((EntityType<ClientVillager> type, World World) -> new ClientVillager(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientvillager")
                );

        public static final RegistryObject<EntityType<ClientWanderingTrader>> WANDERING_TRADER =
                ENTITY_TYPES.register("clientwanderingtrader", () ->
                        EntityType.Builder.of((EntityType<ClientWanderingTrader> type, World World) -> new ClientWanderingTrader(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientwanderingtrader")
                );

        public static final RegistryObject<EntityType<ClientBee>> BEE =
                ENTITY_TYPES.register("clientbee", () ->
                        EntityType.Builder.of((EntityType<ClientBee> type, World World) -> new ClientBee(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.6f)
                                .build("clientbee")
                );

        public static final RegistryObject<EntityType<ClientCaveSpider>> CAVE_SPIDER =
                ENTITY_TYPES.register("clientcavespider", () ->
                        EntityType.Builder.of((EntityType<ClientCaveSpider> type, World World) -> new ClientCaveSpider(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.5f)
                                .build("clientcavespider")
                );

        public static final RegistryObject<EntityType<ClientDolphin>> DOLPHIN =
                ENTITY_TYPES.register("clientdolphin", () ->
                        EntityType.Builder.of((EntityType<ClientDolphin> type, World World) -> new ClientDolphin(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.6f)
                                .build("clientdolphin")
                );

        public static final RegistryObject<EntityType<ClientEnderman>> ENDERMAN =
                ENTITY_TYPES.register("clientenderman", () ->
                        EntityType.Builder.of((EntityType<ClientEnderman> type, World World) -> new ClientEnderman(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 2.9f)
                                .build("clientenderman")
                );

        public static final RegistryObject<EntityType<ClientFox>> FOX =
                ENTITY_TYPES.register("clientfox", () ->
                        EntityType.Builder.of((EntityType<ClientFox> type, World World) -> new ClientFox(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.7f)
                                .build("clientfox")
                );

        public static final RegistryObject<EntityType<ClientIronGolem>> IRON_GOLEM =
                ENTITY_TYPES.register("clientirongolem", () ->
                        EntityType.Builder.of((EntityType<ClientIronGolem> type, World World) -> new ClientIronGolem(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 2.7f)
                                .build("clientirongolem")
                );

        public static final RegistryObject<EntityType<ClientLlama>> LLAMA =
                ENTITY_TYPES.register("clientllama", () ->
                        EntityType.Builder.of((EntityType<ClientLlama> type, World World) -> new ClientLlama(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 1.87f)
                                .build("clientllama")
                );

        public static final RegistryObject<EntityType<ClientPanda>> PANDA =
                ENTITY_TYPES.register("clientpanda", () ->
                        EntityType.Builder.of((EntityType<ClientPanda> type, World World) -> new ClientPanda(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.3f, 1.25f)
                                .build("clientpanda")
                );

        public static final RegistryObject<EntityType<ClientPiglin>> PIGLIN =
                ENTITY_TYPES.register("clientpiglin", () ->
                        EntityType.Builder.of((EntityType<ClientPiglin> type, World World) -> new ClientPiglin(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientpiglin")
                );

        public static final RegistryObject<EntityType<ClientPolarBear>> POLAR_BEAR =
                ENTITY_TYPES.register("clientpolarbear", () ->
                        EntityType.Builder.of((EntityType<ClientPolarBear> type, World World) -> new ClientPolarBear(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 1.4f)
                                .build("clientpolarbear")
                );

        public static final RegistryObject<EntityType<ClientPufferFish>> PUFFERFISH =
                ENTITY_TYPES.register("clientpufferfish", () ->
                        EntityType.Builder.of((EntityType<ClientPufferFish> type, World World) -> new ClientPufferFish(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.7f, 0.7f)
                                .build("clientpufferfish")
                );

        public static final RegistryObject<EntityType<ClientSpider>> SPIDER =
                ENTITY_TYPES.register("clientspider", () ->
                        EntityType.Builder.of((EntityType<ClientSpider> type, World World) -> new ClientSpider(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.4f, 0.9f)
                                .build("clientspider")
                );

        public static final RegistryObject<EntityType<ClientWolf>> WOLF =
                ENTITY_TYPES.register("clientwolf", () ->
                        EntityType.Builder.of((EntityType<ClientWolf> type, World World) -> new ClientWolf(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 0.85f)
                                .build("clientwolf")
                );

        public static final RegistryObject<EntityType<ClientBlaze>> BLAZE =
                ENTITY_TYPES.register("clientblaze", () ->
                        EntityType.Builder.of((EntityType<ClientBlaze> type, World World) -> new ClientBlaze(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.8f)
                                .build("clientblaze")
                );

        public static final RegistryObject<EntityType<ClientCreeper>> CREEPER =
                ENTITY_TYPES.register("clientcreeper", () ->
                        EntityType.Builder.of((EntityType<ClientCreeper> type, World World) -> new ClientCreeper(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.7f)
                                .build("clientcreeper")
                );

        public static final RegistryObject<EntityType<ClientElderGuardian>> ELDER_GUARDIAN_COOKIE =
                ENTITY_TYPES.register("clientelderguardian", () ->
                        EntityType.Builder.of((EntityType<ClientElderGuardian> type, World World) -> new ClientElderGuardian(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.9975f, 1.9975f)
                                .build("clientelderguardian")
                );

        public static final RegistryObject<EntityType<ClientEndermite>> ENDERMITE =
                ENTITY_TYPES.register("clientendermite", () ->
                        EntityType.Builder.of((EntityType<ClientEndermite> type, World World) -> new ClientEndermite(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .build("clientendermite")
                );

        public static final RegistryObject<EntityType<ClientEvoker>> EVOKER =
                ENTITY_TYPES.register("clientevoker", () ->
                        EntityType.Builder.of((EntityType<ClientEvoker> type, World World) -> new ClientEvoker(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientevoker")
                );

        public static final RegistryObject<EntityType<ClientGhast>> GHAST =
                ENTITY_TYPES.register("clientghast", () ->
                        EntityType.Builder.of((EntityType<ClientGhast> type, World World) -> new ClientGhast(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(4.0f, 4.0f)
                                .build("clientghast")
                );

        public static final RegistryObject<EntityType<ClientGuardian>> GUARDIAN =
                ENTITY_TYPES.register("clientguardian", () ->
                        EntityType.Builder.of((EntityType<ClientGuardian> type, World World) -> new ClientGuardian(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.85f, 0.85f)
                                .build("clientguardian")
                );

        public static final RegistryObject<EntityType<ClientHoglin>> HOGLIN =
                ENTITY_TYPES.register("clienthoglin", () ->
                        EntityType.Builder.of((EntityType<ClientHoglin> type, World World) -> new ClientHoglin(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.3965f, 1.4f)
                                .build("clienthoglin")
                );

        public static final RegistryObject<EntityType<ClientMagmaCube>> MAGMA_CUBE =
                ENTITY_TYPES.register("clientmagmacube", () ->
                        EntityType.Builder.of((EntityType<ClientMagmaCube> type, World World) -> new ClientMagmaCube(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 2.0f)
                                .build("clientmagmacube")
                );

        public static final RegistryObject<EntityType<ClientPhantom>> PHANTOM =
                ENTITY_TYPES.register("clientphantom", () ->
                        EntityType.Builder.of((EntityType<ClientPhantom> type, World World) -> new ClientPhantom(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.9f, 0.5f)
                                .build("clientphantom")
                );

        public static final RegistryObject<EntityType<ClientPillager>> PILLAGER =
                ENTITY_TYPES.register("clientpillager", () ->
                        EntityType.Builder.of((EntityType<ClientPillager> type, World World) -> new ClientPillager(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientpillager")
                );

        public static final RegistryObject<EntityType<ClientRavager>> RAVAGER =
                ENTITY_TYPES.register("clientravager", () ->
                        EntityType.Builder.of((EntityType<ClientRavager> type, World World) -> new ClientRavager(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.95f, 2.2f)
                                .build("clientravager")
                );

        public static final RegistryObject<EntityType<ClientShulker>> SHULKER =
                ENTITY_TYPES.register("clientshulker", () ->
                        EntityType.Builder.of((EntityType<ClientShulker> type, World World) -> new ClientShulker(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(1.0f, 2.0f)
                                .build("clientshulker")
                );

        public static final RegistryObject<EntityType<ClientSilverfish>> SILVERFISH =
                ENTITY_TYPES.register("clientsilverfish", () ->
                        EntityType.Builder.of((EntityType<ClientSilverfish> type, World World) -> new ClientSilverfish(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.3f)
                                .build("clientsilverfish")
                );

        public static final RegistryObject<EntityType<ClientSkeleton>> SKELETON =
                ENTITY_TYPES.register("clientskeleton", () ->
                        EntityType.Builder.of((EntityType<ClientSkeleton> type, World World) -> new ClientSkeleton(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientskeleton")
                );

        public static final RegistryObject<EntityType<ClientSlime>> SLIME =
                ENTITY_TYPES.register("clientslime", () ->
                        EntityType.Builder.of((EntityType<ClientSlime> type, World World) -> new ClientSlime(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 2.0f)
                                .build("clientslime")
                );

        public static final RegistryObject<EntityType<ClientVex>> VEX =
                ENTITY_TYPES.register("clientvex", () ->
                        EntityType.Builder.of((EntityType<ClientVex> type, World World) -> new ClientVex(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.4f, 0.8f)
                                .build("clientvex")
                );

        public static final RegistryObject<EntityType<ClientVindicator>> VINDICATOR =
                ENTITY_TYPES.register("clientvindicator", () ->
                        EntityType.Builder.of((EntityType<ClientVindicator> type, World World) -> new ClientVindicator(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientvindicator")
                );

        public static final RegistryObject<EntityType<ClientWitch>> WITCH =
                ENTITY_TYPES.register("clientwitch", () ->
                        EntityType.Builder.of((EntityType<ClientWitch> type, World World) -> new ClientWitch(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientwitch")
                );

        public static final RegistryObject<EntityType<ClientZombie>> ZOMBIE =
                ENTITY_TYPES.register("clientzombie", () ->
                        EntityType.Builder.of((EntityType<ClientZombie> type, World World) -> new ClientZombie(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientzombie")
                );

        public static final RegistryObject<EntityType<ClientZombieVillager>> ZOMBIE_VILLAGER =
                ENTITY_TYPES.register("clientzombievillager", () ->
                        EntityType.Builder.of((EntityType<ClientZombieVillager> type, World World) -> new ClientZombieVillager(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientzombievillager")
                );

        public static final RegistryObject<EntityType<ClientHusk>> HUSK =
                ENTITY_TYPES.register("clienthusk", () ->
                        EntityType.Builder.of((EntityType<ClientHusk> type, World World) -> new ClientHusk(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clienthusk")
                );

        public static final RegistryObject<EntityType<ClientDrowned>> DROWNED =
                ENTITY_TYPES.register("clientdrowned", () ->
                        EntityType.Builder.of((EntityType<ClientDrowned> type, World World) -> new ClientDrowned(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientdrowned")
                );

        public static final RegistryObject<EntityType<ClientStray>> STRAY =
                ENTITY_TYPES.register("clientstray", () ->
                        EntityType.Builder.of((EntityType<ClientStray> type, World World) -> new ClientStray(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientstray")
                );

        public static final RegistryObject<EntityType<ClientWitherSkeleton>> WITHER_SKELETON =
                ENTITY_TYPES.register("clientwitherskeleton", () ->
                        EntityType.Builder.of((EntityType<ClientWitherSkeleton> type, World World) -> new ClientWitherSkeleton(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(0.6f, 1.95f)
                                .build("clientwitherskeleton")
                );

        public static final RegistryObject<EntityType<ClientEnderDragon>> ENDER_DRAGON =
                ENTITY_TYPES.register("clientenderdragon", () ->
                        EntityType.Builder.of((EntityType<ClientEnderDragon> type, World World) -> new ClientEnderDragon(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(16.0f, 8.0f)
                                .build("clientenderdragon")
                );

        public static final RegistryObject<EntityType<ClientWither>> WITHER =
                ENTITY_TYPES.register("clientwither", () ->
                        EntityType.Builder.of((EntityType<ClientWither> type, World World) -> new ClientWither(type, World), EntityClassification.AMBIENT)
                                .noSummon()
                                .sized(2.0f, 3.0f)
                                .build("clientwither")
                );

        public static final RegistryObject<EntityType<Head>> HEAD =
                ENTITY_TYPES.register("head", () ->
                        EntityType.Builder.of((EntityType<Head> type, World World) -> new Head(type, World), EntityClassification.CREATURE)
                                .sized(0.5f, 0.5f)
                                .build("head")
                );

        public static final RegistryObject<EntityType<DumboOctopus>> DUMBO_OCTOPUS =
                ENTITY_TYPES.register("dumbo_octopus", () ->
                        EntityType.Builder.of((EntityType<DumboOctopus> type, World World) -> new DumboOctopus(type, World), EntityClassification.WATER_AMBIENT)
                                .sized(0.5f, 0.5f)
                                .build("dumbo_octopus")
                );

        public static final RegistryObject<EntityType<Koi>> KOI =
                ENTITY_TYPES.register("koi", () ->
                        EntityType.Builder.of((EntityType<Koi> type, World World) -> new Koi(type, World), EntityClassification.WATER_AMBIENT)
                                .sized(0.6f, 0.6f)
                                .build("koi")
                );

        public static final RegistryObject<EntityType<Stingray>> STINGRAY =
                ENTITY_TYPES.register("stingray", () ->
                        EntityType.Builder.of((EntityType<Stingray> type, World World) -> new Stingray(type, World), EntityClassification.WATER_AMBIENT)
                                .sized(1.0f, 0.4f)
                                .build("stingray")
                );
    }
}