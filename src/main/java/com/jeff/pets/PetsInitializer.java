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
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "pets-mod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static final ResourceKey<@NotNull EntityType<?>> RACOON_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "racoon"));
    public static final EntityType<@NotNull Racoon> RACOON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "racoon"),
            EntityType.Builder.of(Racoon::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .build(RACOON_KEY.location().getPath()));
    private static final ResourceKey<@NotNull EntityType<?>> BAT_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientbat"));
    public static final EntityType<@NotNull ClientBat> BAT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientbat"),
            EntityType.Builder.of(ClientBat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)

                    .build(BAT_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> DUCK_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "duck"));
    public static final EntityType<@NotNull Duck> DUCK = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "duck"),
            EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.7f)

                    .build(DUCK_KEY.location().getPath()));
    private static final ResourceKey<@NotNull EntityType<?>> PENGUIN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "penguin"));
    public static final EntityType<@NotNull Penguin> PENGUIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "penguin"),
            EntityType.Builder.of(Penguin::new, MobCategory.AMBIENT)
                    .sized(1f, 1.5f)

                    .build(PENGUIN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SHEEP_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientsheep"));
    public static final EntityType<@NotNull ClientSheep> SHEEP = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsheep"),
            EntityType.Builder.of(ClientSheep::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.3f)

                    .build(SHEEP_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> CAT_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientcat"));
    public static final EntityType<@NotNull ClientCat> CAT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcat"),
            EntityType.Builder.of(ClientCat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.7f)

                    .build(CAT_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> CHICKEN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientchicken"));
    public static final EntityType<@NotNull ClientChicken> CHICKEN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientchicken"),
            EntityType.Builder.of(ClientChicken::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.7f)

                    .build(CHICKEN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> COD_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientcod"));
    public static final EntityType<@NotNull ClientCod> COD = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcod"),
            EntityType.Builder.of(ClientCod::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.3f)

                    .build(COD_KEY.location().getPath())
    );

    private static final ResourceKey<@NotNull EntityType<?>> COW_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientcow"));
    public static final EntityType<@NotNull ClientCow> COW = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcow"),
            EntityType.Builder.of(ClientCow::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)

                    .build(COW_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> DONKEY_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientdonkey"));
    public static final EntityType<@NotNull ClientDonkey> DONKEY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientdonkey"),
            EntityType.Builder.of(ClientDonkey::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.5f)

                    .build(DONKEY_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> HORSE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clienthorse"));
    public static final EntityType<@NotNull ClientHorse> HORSE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clienthorse"),
            EntityType.Builder.of(ClientHorse::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.6f)

                    .build(HORSE_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> MOOSHROOM_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientmooshroom"));
    public static final EntityType<@NotNull ClientMooshroom> MOOSHROOM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientmooshroom"),
            EntityType.Builder.of(ClientMooshroom::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)

                    .build(MOOSHROOM_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> PARROT_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientparrot"));
    public static final EntityType<@NotNull ClientParrot> PARROT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientparrot"),
            EntityType.Builder.of(ClientParrot::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)

                    .build(PARROT_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> PIG_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientpig"));
    public static final EntityType<@NotNull ClientPig> PIG = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpig"),
            EntityType.Builder.of(ClientPig::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.9f)

                    .build(PIG_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> RABBIT_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientrabbit"));
    public static final EntityType<@NotNull ClientRabbit> RABBIT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientrabbit"),
            EntityType.Builder.of(ClientRabbit::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.5f)

                    .build(RABBIT_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SALMON_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientsalmon"));
    public static final EntityType<@NotNull ClientSalmon> SALMON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsalmon"),
            EntityType.Builder.of(ClientSalmon::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.35f, 0.2f)

                    .build(SALMON_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SNOW_GOLEM_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientsnowgolem"));
    public static final EntityType<@NotNull ClientSnowGolem> SNOW_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.of(ClientSnowGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 1.9f)

                    .build(SNOW_GOLEM_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SQUID_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientsquid"));
    public static final EntityType<@NotNull ClientSquid> SQUID = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsquid"),
            EntityType.Builder.of(ClientSquid::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.8f, -0.8f)

                    .build(SQUID_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> STRIDER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientstrider"));
    public static final EntityType<@NotNull ClientStrider> STRIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientstrider"),
            EntityType.Builder.of(ClientStrider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.7f)

                    .build(STRIDER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> TURTLE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientturtle"));
    public static final EntityType<@NotNull ClientTurtle> TURTLE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientturtle"),
            EntityType.Builder.of(ClientTurtle::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.2f, 0.4f)

                    .build(TURTLE_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> VILLAGER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientvillager"));
    public static final EntityType<@NotNull ClientVillager> VILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientvillager"),
            EntityType.Builder.of(ClientVillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(VILLAGER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> WANDERING_TRADER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientwanderingtrader"));
    public static final EntityType<@NotNull ClientWanderingTrader> WANDERING_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwandeinrgtrader"),
            EntityType.Builder.of(ClientWanderingTrader::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(WANDERING_TRADER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> BEE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientbee"));
    public static final EntityType<@NotNull ClientBee> BEE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientbee"),
            EntityType.Builder.of(ClientBee::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.6f)

                    .build(BEE_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> CAVE_SPIDER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientcavespider"));
    public static final EntityType<@NotNull ClientCaveSpider> CAVE_SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcavespider"),
            EntityType.Builder.of(ClientCaveSpider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.5f)

                    .build(CAVE_SPIDER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> DOLPHIN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientdolphin"));
    public static final EntityType<@NotNull ClientDolphin> DOLPHIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientdolphin"),
            EntityType.Builder.of(ClientDolphin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.6f)

                    .build(DOLPHIN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> ENDERMAN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientenderman"));
    public static final EntityType<@NotNull ClientEnderman> ENDERMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientenderman"),
            EntityType.Builder.of(ClientEnderman::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 2.9f)

                    .build(ENDERMAN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> FOX_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientfox"));
    public static final EntityType<@NotNull ClientFox> FOX = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientfox"),
            EntityType.Builder.of(ClientFox::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.7f)

                    .build(FOX_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> IRON_GOLEM_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientirongolem"));
    public static final EntityType<@NotNull ClientIronGolem> IRON_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientirongolem"),
            EntityType.Builder.of(ClientIronGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 2.7f)

                    .build(IRON_GOLEM_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> LLAMA_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientllama"));
    public static final EntityType<@NotNull ClientLlama> LLAMA = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientllama"),
            EntityType.Builder.of(ClientLlama::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.87f)

                    .build(LLAMA_KEY.location().getPath())
    );

    private static final ResourceKey<@NotNull EntityType<?>> PANDA_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientpanda"));
    public static final EntityType<@NotNull ClientPanda> PANDA = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpanda"),
            EntityType.Builder.of(ClientPanda::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3f, 1.25f)

                    .build(PANDA_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> PIGLIN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientpiglin"));
    public static final EntityType<@NotNull ClientPiglin> PIGLIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpiglin"),
            EntityType.Builder.of(ClientPiglin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(PIGLIN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> POLAR_BEAR_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientpolarbear"));
    public static final EntityType<@NotNull ClientPolarBear> POLAR_BEAR = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpolarbear"),
            EntityType.Builder.of(ClientPolarBear::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 1.4f)

                    .build(POLAR_BEAR_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> PUFFERFISH_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientpufferfish"));
    public static final EntityType<@NotNull ClientPufferFish> PUFFERFISH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpufferfish"),
            EntityType.Builder.of(ClientPufferFish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.7f)

                    .build(PUFFERFISH_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SPIDER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientspider"));
    public static final EntityType<@NotNull ClientSpider> SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientspider"),
            EntityType.Builder.of(ClientSpider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 0.9f)

                    .build(SPIDER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> WOLF_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientwolf"));
    public static final EntityType<@NotNull ClientWolf> WOLF = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwolf"),
            EntityType.Builder.of(ClientWolf::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.85f)

                    .build(WOLF_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> BLAZE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientblaze"));
    public static final EntityType<@NotNull ClientBlaze> BLAZE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientblaze"),
            EntityType.Builder.of(ClientBlaze::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.8f)

                    .build(BLAZE_KEY.location().getPath())
    );

    private static final ResourceKey<@NotNull EntityType<?>> CREEPER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientcreeper"));
    public static final EntityType<@NotNull ClientCreeper> CREEPER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcreeper"),
            EntityType.Builder.of(ClientCreeper::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.7f)

                    .build(CREEPER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> ELDER_GUARDIAN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientelderguardian"));
    public static final EntityType<@NotNull ClientElderGuardian> ELDER_GUARDIAN_COOKIE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientelderguardian"),
            EntityType.Builder.of(ClientElderGuardian::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.9975f, 1.9975f)

                    .build(ELDER_GUARDIAN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> ENDERMITE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientendermite"));
    public static final EntityType<@NotNull ClientEndermite> ENDERMITE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientendermite"),
            EntityType.Builder.of(ClientEndermite::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)

                    .build(ENDERMITE_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> EVOKER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientwolf"));
    public static final EntityType<@NotNull ClientEvoker> EVOKER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientevoker"),
            EntityType.Builder.of(ClientEvoker::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(EVOKER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> GHAST_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientghast"));
    public static final EntityType<@NotNull ClientGhast> GHAST = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientghast"),
            EntityType.Builder.of(ClientGhast::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)

                    .build(GHAST_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> GUARDIAN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientguardian"));
    public static final EntityType<@NotNull ClientGuardian> GUARDIAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientguardian"),
            EntityType.Builder.of(ClientGuardian::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.85f, 0.85f)

                    .build(GUARDIAN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> HOGLIN_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clienthoglin"));
    public static final EntityType<@NotNull ClientHoglin> HOGLIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clienthoglin"),
            EntityType.Builder.of(ClientHoglin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.4f)

                    .build(HOGLIN_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> MAGMA_CUBE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientmagmacube"));
    public static final EntityType<@NotNull ClientMagmaCube> MAGMA_CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientmagmacube"),
            EntityType.Builder.of(ClientMagmaCube::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)


                    .build(MAGMA_CUBE_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> PHANTOM_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientphantom"));
    public static final EntityType<@NotNull ClientPhantom> PHANTOM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientphantom"),
            EntityType.Builder.of(ClientPhantom::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.5f)

                    .build(PHANTOM_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> PILLAGER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientpillager"));
    public static final EntityType<@NotNull ClientPillager> PILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpillager"),
            EntityType.Builder.of(ClientPillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(PILLAGER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> RAVAGER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientravager"));
    public static final EntityType<@NotNull ClientRavager> RAVAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientravager"),
            EntityType.Builder.of(ClientRavager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.95f, 2.2f)

                    .build(RAVAGER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SHULKER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientshulker"));
    public static final EntityType<@NotNull ClientShulker> SHULKER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientshulker"),
            EntityType.Builder.of(ClientShulker::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)

                    .build(SHULKER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SILVERFISH_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientsilverfish"));
    public static final EntityType<@NotNull ClientSilverfish> SILVERFISH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsilverfish"),
            EntityType.Builder.of(ClientSilverfish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)

                    .build(SILVERFISH_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SKELETON_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientskeleton"));
    public static final EntityType<@NotNull ClientSkeleton> SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientskeleton"),
            EntityType.Builder.of(ClientSkeleton::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(SKELETON_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> SLIME_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientslime"));
    public static final EntityType<@NotNull ClientSlime> SLIME = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientslime"),
            EntityType.Builder.of(ClientSlime::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)

                    .build(SLIME_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> VEX_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientvex"));
    public static final EntityType<@NotNull ClientVex> VEX = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientvex"),
            EntityType.Builder.of(ClientVex::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.8f)

                    .build(VEX_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> VINDICATOR_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientvindicator"));
    public static final EntityType<@NotNull ClientVindicator> VINDICATOR = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientvindicator"),
            EntityType.Builder.of(ClientVindicator::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(VINDICATOR_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> WITCH_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientwitch"));
    public static final EntityType<@NotNull ClientWitch> WITCH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwitch"),
            EntityType.Builder.of(ClientWitch::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(WITCH_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientzombie"));
    public static final EntityType<@NotNull ClientZombie> ZOMBIE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientzombie"),
            EntityType.Builder.of(ClientZombie::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(ZOMBIE_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_VILLAGER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientzombievillager"));
    public static final EntityType<@NotNull ClientZombieVillager> ZOMBIE_VILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientzombievillager"),
            EntityType.Builder.of(ClientZombieVillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(ZOMBIE_VILLAGER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> HUSK_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clienthusk"));
    public static final EntityType<@NotNull ClientHusk> HUSK = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clienthusk"),
            EntityType.Builder.of(ClientHusk::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(HUSK_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> DROWNED_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientdrowned"));
    public static final EntityType<@NotNull ClientDrowned> DROWNED = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientdrowned"),
            EntityType.Builder.of(ClientDrowned::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(DROWNED_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> STRAY_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientstray"));
    public static final EntityType<@NotNull ClientStray> STRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientstray"),
            EntityType.Builder.of(ClientStray::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(STRAY_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> WITHER_SKELETON_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientwitherskeleton"));
    public static final EntityType<@NotNull ClientWitherSkeleton> WITHER_SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.of(ClientWitherSkeleton::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)

                    .build(WITHER_SKELETON_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> ENDER_DRAGON_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientenderdragon"));
    public static final EntityType<@NotNull ClientEnderDragon> ENDER_DRAGON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientenderdragon"),
            EntityType.Builder.of(ClientEnderDragon::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(16f, 8f)

                    .build(ENDER_DRAGON_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> WITHER_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "clientwither"));
    public static final EntityType<@NotNull ClientWither> WITHER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwither"),
            EntityType.Builder.of(ClientWither::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 3f)

                    .build(WITHER_KEY.location().getPath())
    );
    private static final ResourceKey<@NotNull EntityType<?>> HEAD_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "head"));
    public static final EntityType<@NotNull Head> HEAD = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "head"),
            EntityType.Builder.of(Head::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)

                    .build(HEAD_KEY.location().getPath()));

    private static final ResourceKey<@NotNull EntityType<?>> DUMBO_OCTOPUS_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "dumbo_octopus"));
    public static final EntityType<@NotNull DumboOctopus> DUMBO_OCTOPUS = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "dumbo_octopus"),
            EntityType.Builder.of(DumboOctopus::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)

                    .build(DUMBO_OCTOPUS_KEY.location().getPath())
    );

    private static final ResourceKey<@NotNull EntityType<?>> KOI_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "koi"));
    public static final EntityType<@NotNull Koi> KOI = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "koi"),
            EntityType.Builder.of(Koi::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f, 0.6f)

                    .build(KOI_KEY.location().getPath())
    );

    private static final ResourceKey<@NotNull EntityType<?>> STINGRAY_KEY =
            ResourceKey.create(Registry.ENTITY_TYPE.key(), new ResourceLocation(MOD_ID, "stingray"));
    public static final EntityType<@NotNull Stingray> STINGRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "stringray"),
            EntityType.Builder.of(Stingray::new, MobCategory.WATER_AMBIENT)
                    .sized(1.0f, 0.4f)

                    .build(STINGRAY_KEY.location().getPath())
    );

    /**
     * Registers the entities' attributes. Warns about the call to register not working, but it
     * ends up working fine in-game - likely a mixup in either the Fabric API or IntelliJ.
     */
    @Override
    public void onInitialize() {

        FabricDefaultAttributeRegistry.register(RACOON, Racoon.createAttributes());
        FabricDefaultAttributeRegistry.register(DUCK, Duck.createAttributes());
        FabricDefaultAttributeRegistry.register(PENGUIN, Penguin.createAttributes());
        FabricDefaultAttributeRegistry.register(SHEEP, ClientSheep.createAttributes());
        FabricDefaultAttributeRegistry.register(CAT, ClientCat.createAttributes());
        FabricDefaultAttributeRegistry.register(BAT, ClientBat.createAttributes());
        FabricDefaultAttributeRegistry.register(CHICKEN, ClientChicken.createAttributes());
        FabricDefaultAttributeRegistry.register(COD, ClientCod.createAttributes());
        FabricDefaultAttributeRegistry.register(COW, ClientCow.createAttributes());
        FabricDefaultAttributeRegistry.register(DONKEY, ClientDonkey.createAttributes());
        FabricDefaultAttributeRegistry.register(HORSE, ClientHorse.createAttributes());
        FabricDefaultAttributeRegistry.register(MOOSHROOM, ClientMooshroom.createAttributes());
        FabricDefaultAttributeRegistry.register(PARROT, ClientParrot.createAttributes());
        FabricDefaultAttributeRegistry.register(PIG, ClientPig.createAttributes());
        FabricDefaultAttributeRegistry.register(RABBIT, ClientRabbit.createAttributes());
        FabricDefaultAttributeRegistry.register(SALMON, ClientSalmon.createAttributes());
        FabricDefaultAttributeRegistry.register(SNOW_GOLEM, ClientSnowGolem.createAttributes());
        FabricDefaultAttributeRegistry.register(SQUID, ClientSquid.createAttributes());
        FabricDefaultAttributeRegistry.register(STRIDER, ClientStrider.createAttributes());
        FabricDefaultAttributeRegistry.register(TURTLE, ClientTurtle.createAttributes());
        FabricDefaultAttributeRegistry.register(VILLAGER, ClientVillager.createAttributes());
        FabricDefaultAttributeRegistry.register(WANDERING_TRADER, ClientWanderingTrader.createAttributes());
        FabricDefaultAttributeRegistry.register(BEE, ClientBee.createAttributes());
        FabricDefaultAttributeRegistry.register(CAVE_SPIDER, ClientCaveSpider.createAttributes());
        FabricDefaultAttributeRegistry.register(DOLPHIN, ClientDolphin.createAttributes());
        FabricDefaultAttributeRegistry.register(ENDERMAN, ClientEnderman.createAttributes());
        FabricDefaultAttributeRegistry.register(FOX, ClientFox.createAttributes());
        FabricDefaultAttributeRegistry.register(IRON_GOLEM, ClientIronGolem.createAttributes());
        FabricDefaultAttributeRegistry.register(LLAMA, ClientLlama.createAttributes());
        FabricDefaultAttributeRegistry.register(PANDA, ClientPanda.createAttributes());
        FabricDefaultAttributeRegistry.register(PIGLIN, ClientWanderingTrader.createAttributes());
        FabricDefaultAttributeRegistry.register(POLAR_BEAR, ClientPolarBear.createAttributes());
        FabricDefaultAttributeRegistry.register(PUFFERFISH, ClientPufferFish.createAttributes());
        FabricDefaultAttributeRegistry.register(SPIDER, ClientSpider.createAttributes());
        FabricDefaultAttributeRegistry.register(WOLF, ClientWolf.createAttributes());
        FabricDefaultAttributeRegistry.register(BLAZE, ClientBlaze.createAttributes());
        FabricDefaultAttributeRegistry.register(CREEPER, ClientCreeper.createAttributes());
        FabricDefaultAttributeRegistry.register(ELDER_GUARDIAN_COOKIE, ClientElderGuardian.createAttributes());
        FabricDefaultAttributeRegistry.register(ENDERMITE, ClientEndermite.createAttributes());
        FabricDefaultAttributeRegistry.register(EVOKER, ClientEvoker.createAttributes());
        FabricDefaultAttributeRegistry.register(GHAST, ClientGhast.createAttributes());
        FabricDefaultAttributeRegistry.register(GUARDIAN, ClientGuardian.createAttributes());
        FabricDefaultAttributeRegistry.register(HOGLIN, ClientHoglin.createAttributes());
        FabricDefaultAttributeRegistry.register(MAGMA_CUBE, ClientMagmaCube.createAttributes());
        FabricDefaultAttributeRegistry.register(PHANTOM, ClientPhantom.createAttributes());
        FabricDefaultAttributeRegistry.register(PILLAGER, ClientPillager.createAttributes());
        FabricDefaultAttributeRegistry.register(RAVAGER, ClientRavager.createAttributes());
        FabricDefaultAttributeRegistry.register(SHULKER, ClientShulker.createAttributes());
        FabricDefaultAttributeRegistry.register(SILVERFISH, ClientSilverfish.createAttributes());
        FabricDefaultAttributeRegistry.register(SKELETON, ClientSkeleton.createAttributes());
        FabricDefaultAttributeRegistry.register(SLIME, ClientSlime.createAttributes());
        FabricDefaultAttributeRegistry.register(VEX, ClientVex.createAttributes());
        FabricDefaultAttributeRegistry.register(VINDICATOR, ClientVindicator.createAttributes());
        FabricDefaultAttributeRegistry.register(WITCH, ClientWitch.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE, ClientZombie.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_VILLAGER, ClientZombieVillager.createAttributes());
        FabricDefaultAttributeRegistry.register(HUSK, ClientHusk.createAttributes());
        FabricDefaultAttributeRegistry.register(DROWNED, ClientDrowned.createAttributes());
        FabricDefaultAttributeRegistry.register(STRAY, ClientStray.createAttributes());
        FabricDefaultAttributeRegistry.register(WITHER_SKELETON, ClientWitherSkeleton.createAttributes());
        FabricDefaultAttributeRegistry.register(ENDER_DRAGON, ClientEnderDragon.createAttributes());
        FabricDefaultAttributeRegistry.register(WITHER, ClientWither.createAttributes());
        FabricDefaultAttributeRegistry.register(HEAD, Head.createAttributes());
        FabricDefaultAttributeRegistry.register(DUMBO_OCTOPUS, DumboOctopus.createAttributes());
        FabricDefaultAttributeRegistry.register(KOI, Koi.createAttributes());
        FabricDefaultAttributeRegistry.register(STINGRAY, Stingray.createAttributes());

        PetsSounds.initialize();

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
}