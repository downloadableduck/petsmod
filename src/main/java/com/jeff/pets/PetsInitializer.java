package com.jeff.pets;

import com.jeff.pets.custom.Duck;
import com.jeff.pets.custom.Penguin;
import com.jeff.pets.custom.Racoon;
import com.jeff.pets.vanilla.boss.ClientEnderDragon;
import com.jeff.pets.vanilla.boss.ClientWither;
import com.jeff.pets.vanilla.hostile.*;
import com.jeff.pets.vanilla.passive.*;
import com.jeff.pets.vanilla.neutral.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.ElderGuardian;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "pets";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceKey<@NotNull EntityType<?>> RACOON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "racoon"));

    public static final ResourceKey<@NotNull EntityType<?>> ALLAY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientallay"));

    public static final ResourceKey<@NotNull EntityType<?>> ARMADILLO_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientarmadillo"));

    public static final ResourceKey<@NotNull EntityType<?>> AXOLOTL_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientaxolotl"));

    public static final ResourceKey<@NotNull EntityType<?>> BAT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientbat"));

    public static final ResourceKey<@NotNull EntityType<?>> CAMEL_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcamel"));

    public static final ResourceKey<@NotNull EntityType<?>> DUCK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "duck"));

    public static final ResourceKey<@NotNull EntityType<?>> PENGUIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "penguin"));
    public static final ResourceKey<@NotNull EntityType<?>> SHEEP_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientsheep"));

    public static final ResourceKey<@NotNull EntityType<?>> CAT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcat"));

    public static final ResourceKey<@NotNull EntityType<?>> CHICKEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientchicken"));

    public static final ResourceKey<@NotNull EntityType<?>> COD_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcod"));

    public static final ResourceKey<@NotNull EntityType<?>> COPPER_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcoppergolem"));

    public static final ResourceKey<@NotNull EntityType<?>> COW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcow"));

    public static final ResourceKey<@NotNull EntityType<?>> DONKEY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientdonkey"));

    public static final ResourceKey<@NotNull EntityType<?>> FROG_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientfrog"));

    public static final ResourceKey<@NotNull EntityType<?>> HORSE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clienthorse"));

    public static final ResourceKey<@NotNull EntityType<?>> MOOSHROOM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientmooshroom"));

    public static final ResourceKey<@NotNull EntityType<?>> PARROT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientparrot"));

    public static final ResourceKey<@NotNull EntityType<?>> PIG_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientpig"));

    public static final ResourceKey<@NotNull EntityType<?>> RABBIT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientrabbit"));

    public static final ResourceKey<@NotNull EntityType<?>> SALMON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientsalmon"));

    public static final ResourceKey<@NotNull EntityType<?>> SNIFFER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientsniffer"));

    public static final ResourceKey<@NotNull EntityType<?>> SNOW_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientsnowgolem"));

    public static final ResourceKey<@NotNull EntityType<?>> SQUID_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientsquid"));

    public static final ResourceKey<@NotNull EntityType<?>> STRIDER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientstrider"));

    public static final ResourceKey<@NotNull EntityType<?>> TADPOLE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clienttadpole"));

    public static final ResourceKey<@NotNull EntityType<?>> TROPICAL_FISH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clienttropicalfish"));

    public static final ResourceKey<@NotNull EntityType<?>> TURTLE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientturtle"));

    public static final ResourceKey<@NotNull EntityType<?>> VILLAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientvillager"));

    public static final ResourceKey<@NotNull EntityType<?>> WANDERING_TRADER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwanderingtrader"));

    public static final ResourceKey<@NotNull EntityType<?>> BEE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientbee"));

    public static final ResourceKey<@NotNull EntityType<?>> CAVE_SPIDER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcavespider"));

    public static final ResourceKey<@NotNull EntityType<?>> DOLPHIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientdolphin"));

    public static final ResourceKey<@NotNull EntityType<?>> ENDERMAN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientenderman"));

    public static final ResourceKey<@NotNull EntityType<?>> FOX_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientfox"));

    public static final ResourceKey<@NotNull EntityType<?>> GOAT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientgoat"));

    public static final ResourceKey<@NotNull EntityType<?>> IRON_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientirongolem"));

    public static final ResourceKey<@NotNull EntityType<?>> LLAMA_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientllama"));

    public static final ResourceKey<@NotNull EntityType<?>> NAUTILUS_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientnautilus"));

    public static final ResourceKey<@NotNull EntityType<?>> PANDA_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientpanda"));

    public static final ResourceKey<@NotNull EntityType<?>> PIGLIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientpiglin"));

    public static final ResourceKey<@NotNull EntityType<?>> POLAR_BEAR_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientpolarbear"));

    public static final ResourceKey<@NotNull EntityType<?>> PUFFERFISH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientpufferfish"));

    public static final ResourceKey<@NotNull EntityType<?>> SPIDER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientspider"));

    public static final ResourceKey<@NotNull EntityType<?>> WOLF_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwolf"));

    public static final ResourceKey<@NotNull EntityType<?>> BLAZE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientblaze"));

    public static final ResourceKey<@NotNull EntityType<?>> BREEZE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientbreeze"));

    public static final ResourceKey<@NotNull EntityType<?>> CREAKING_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcreaking"));

    public static final ResourceKey<@NotNull EntityType<?>> CREEPER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientcreeper"));

    public static final ResourceKey<@NotNull EntityType<?>> ELDER_GUARDIAN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientelderguardian"));

    public static final ResourceKey<@NotNull EntityType<?>> ENDERMITE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientendermite"));

    public static final ResourceKey<@NotNull EntityType<?>> EVOKER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwolf"));

    public static final ResourceKey<@NotNull EntityType<?>> GHAST_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientghast"));

    public static final ResourceKey<@NotNull EntityType<?>> HAPPY_GHAST_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clienthappyghast"));
    public static final ResourceKey<@NotNull EntityType<?>> GUARDIAN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientguardian"));

    public static final ResourceKey<@NotNull EntityType<?>> HOGLIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clienthoglin"));

    public static final ResourceKey<@NotNull EntityType<?>> MAGMA_CUBE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientmagmacube"));

    public static final ResourceKey<@NotNull EntityType<?>> PHANTOM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientphantom"));

    public static final ResourceKey<@NotNull EntityType<?>> PILLAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientpillager"));

    public static final ResourceKey<@NotNull EntityType<?>> RAVAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientravager"));

    public static final ResourceKey<@NotNull EntityType<?>> SHULKER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientshulker"));

    public static final ResourceKey<@NotNull EntityType<?>> SILVERFISH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientsilverfish"));

    public static final ResourceKey<@NotNull EntityType<?>> SKELETON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientskeleton"));

    public static final ResourceKey<@NotNull EntityType<?>> SLIME_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientslime"));

    public static final ResourceKey<@NotNull EntityType<?>> VEX_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientvex"));

    public static final ResourceKey<@NotNull EntityType<?>> VINDICATOR_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientvindicator"));

    public static final ResourceKey<@NotNull EntityType<?>> WARDEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwarden"));

    public static final ResourceKey<@NotNull EntityType<?>> WITCH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwitch"));

    public static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientzombie"));

    public static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_VILLAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientzombievillager"));

    public static final ResourceKey<@NotNull EntityType<?>> HUSK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clienthusk"));

    public static final ResourceKey<@NotNull EntityType<?>> DROWNED_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientdrowned"));

    public static final ResourceKey<@NotNull EntityType<?>> BOGGED_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientbogged"));

    public static final ResourceKey<@NotNull EntityType<?>> PARCHED_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientparched"));

    public static final ResourceKey<@NotNull EntityType<?>> STRAY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientstray"));

    public static final ResourceKey<@NotNull EntityType<?>> WITHER_SKELETON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwitherskeleton"));

    public static final ResourceKey<@NotNull EntityType<?>> ENDER_DRAGON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientenderdragon"));

    public static final ResourceKey<@NotNull EntityType<?>> WITHER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "clientwither"));


    public static final EntityType<@NotNull Racoon> RACOON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "racoon"),
            EntityType.Builder.of(Racoon::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .eyeHeight(1)
                    .build(RACOON_KEY));

    public static final EntityType<@NotNull Duck> DUCK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "duck"),
            EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .eyeHeight(1)
                    .build(DUCK_KEY));

    public static final EntityType<@NotNull Penguin> PENGUIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "penguin"),
            EntityType.Builder.of(Penguin::new, MobCategory.AMBIENT)
                    .sized(1f, 1.5f)
                    .eyeHeight(1f)
                    .build(PENGUIN_KEY)
    );

    public static final EntityType<@NotNull ClientSheep> SHEEP = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientsheep"),
            EntityType.Builder.of(ClientSheep::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(SHEEP_KEY)
    );

    public static final EntityType<@NotNull ClientCat> CAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcat"),
            EntityType.Builder.of(ClientCat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(CAT_KEY)
    );

    public static final EntityType<@NotNull ClientAllay> ALLAY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientallay"),
            EntityType.Builder.of(ClientAllay::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(ALLAY_KEY)
            );

    public static final EntityType<@NotNull ClientArmadillo> ARMADILLO = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientarmadillo"),
            EntityType.Builder.of(ClientArmadillo::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                .eyeHeight(1f)
                .build(ARMADILLO_KEY)
    );

    public static final EntityType<@NotNull ClientAxolotl> AXOLOTL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientaxolotl"),
            EntityType.Builder.of(ClientAxolotl::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(AXOLOTL_KEY)
    );

    public static final EntityType<@NotNull ClientBat> BAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientbat"),
            EntityType.Builder.of(ClientBat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(BAT_KEY)
    );

    public static final EntityType<@NotNull ClientCamel> CAMEL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcamel"),
            EntityType.Builder.of(ClientCamel::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(3f, 2f)
                    .eyeHeight(2f)
                    .build(CAMEL_KEY)
    );

    public static final EntityType<@NotNull ClientChicken> CHICKEN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientchicken"),
            EntityType.Builder.of(ClientChicken::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(CHICKEN_KEY)
    );

    public static final EntityType<@NotNull ClientCod> COD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcod"),
            EntityType.Builder.of(ClientCod::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(COD_KEY)
    );

    public static final EntityType<@NotNull ClientCopperGolem> COPPER_GOLEM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcoppergolem"),
            EntityType.Builder.of(ClientCopperGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(COPPER_GOLEM_KEY)
    );

    public static final EntityType<@NotNull ClientCow> COW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcow"),
            EntityType.Builder.of(ClientCow::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 1f)
                    .eyeHeight(1f)
                    .build(COW_KEY)
    );

    public static final EntityType<@NotNull ClientDonkey> DONKEY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientdonkey"),
            EntityType.Builder.of(ClientDonkey::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(DONKEY_KEY)
    );

    public static final EntityType<@NotNull ClientFrog> FROG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientfrog"),
            EntityType.Builder.of(ClientFrog::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(FROG_KEY)
    );

    public static final EntityType<@NotNull ClientHorse> HORSE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clienthorse"),
            EntityType.Builder.of(ClientHorse::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(HORSE_KEY)
    );

    public static final EntityType<@NotNull ClientMooshroom> MOOSHROOM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientmooshroom"),
            EntityType.Builder.of(ClientMooshroom::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 1f)
                    .eyeHeight(1f)
                    .build(MOOSHROOM_KEY)
    );

    public static final EntityType<@NotNull ClientParrot> PARROT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientparrot"),
            EntityType.Builder.of(ClientParrot::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(PARROT_KEY)
    );

    public static final EntityType<@NotNull ClientPig> PIG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientpig"),
            EntityType.Builder.of(ClientPig::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(PIG_KEY)
    );

    public static final EntityType<@NotNull ClientRabbit> RABBIT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientrabbit"),
            EntityType.Builder.of(ClientRabbit::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(RABBIT_KEY)
    );

    public static final EntityType<@NotNull ClientSalmon> SALMON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientsalmon"),
            EntityType.Builder.of(ClientSalmon::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(SALMON_KEY)
    );

    public static final EntityType<@NotNull ClientSniffer> SNIFFER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientsniffer"),
            EntityType.Builder.of(ClientSniffer::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(3f, 2f)
                    .eyeHeight(2f)
                    .build(SNIFFER_KEY)
    );

    public static final EntityType<@NotNull ClientSnowGolem> SNOW_GOLEM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.of(ClientSnowGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(SNOW_GOLEM_KEY)
    );

    public static final EntityType<@NotNull ClientSquid> SQUID = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientsquid"),
            EntityType.Builder.of(ClientSquid::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, -1f)
                    .eyeHeight(1f)
                    .build(SQUID_KEY)
    );

    public static final EntityType<@NotNull ClientStrider> STRIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientstrider"),
            EntityType.Builder.of(ClientStrider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(STRIDER_KEY)
    );

    public static final EntityType<@NotNull ClientTadpole> TADPOLE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clienttadpole"),
            EntityType.Builder.of(ClientTadpole::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(TADPOLE_KEY)
    );

    public static final EntityType<@NotNull ClientTropicalFish> TROPICAL_FISH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clienttropicalfish"),
            EntityType.Builder.of(ClientTropicalFish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(TROPICAL_FISH_KEY)
    );

    public static final EntityType<@NotNull ClientTurtle> TURTLE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientturtle"),
            EntityType.Builder.of(ClientTurtle::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(TURTLE_KEY)
    );

    public static final EntityType<@NotNull ClientVillager> VILLAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientvillager"),
            EntityType.Builder.of(ClientVillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(VILLAGER_KEY)
    );

    public static final EntityType<@NotNull ClientWanderingTrader> WANDERING_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientwandeinrgtrader"),
            EntityType.Builder.of(ClientWanderingTrader::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(WANDERING_TRADER_KEY)
    );

    public static final EntityType<@NotNull ClientBee> BEE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientbee"),
            EntityType.Builder.of(ClientBee::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(BEE_KEY)
    );

    public static final EntityType<@NotNull ClientCaveSpider> CAVE_SPIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcavespider"),
            EntityType.Builder.of(ClientCaveSpider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(CAVE_SPIDER_KEY)
    );

    public static final EntityType<@NotNull ClientDolphin> DOLPHIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientdolphin"),
            EntityType.Builder.of(ClientDolphin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(DOLPHIN_KEY)
    );

    public static final EntityType<@NotNull ClientEnderman> ENDERMAN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientenderman"),
            EntityType.Builder.of(ClientEnderman::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 3f)
                    .eyeHeight(3f)
                    .build(ENDERMAN_KEY)
    );

    public static final EntityType<@NotNull ClientFox> FOX = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientfox"),
            EntityType.Builder.of(ClientFox::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(FOX_KEY)
    );

    public static final EntityType<@NotNull ClientGoat> GOAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientgoat"),
            EntityType.Builder.of(ClientGoat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(GOAT_KEY)
    );

    public static final EntityType<@NotNull ClientIronGolem> IRON_GOLEM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientirongolem"),
            EntityType.Builder.of(ClientIronGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 3f)
                    .eyeHeight(3f)
                    .build(IRON_GOLEM_KEY)
    );

    public static final EntityType<@NotNull ClientLlama> LLAMA = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientllama"),
            EntityType.Builder.of(ClientLlama::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(LLAMA_KEY)
    );

    public static final EntityType<@NotNull ClientNautilus> NAUTILUS = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientnautilus"),
            EntityType.Builder.of(ClientNautilus::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 1f)
                    .eyeHeight(1f)
                    .build(NAUTILUS_KEY)
    );

    public static final EntityType<@NotNull ClientPanda> PANDA = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientpanda"),
            EntityType.Builder.of(ClientPanda::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(PANDA_KEY)
    );

    public static final EntityType<@NotNull ClientPiglin> PIGLIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientpiglin"),
            EntityType.Builder.of(ClientPiglin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(PIGLIN_KEY)
    );

    public static final EntityType<@NotNull ClientPolarBear> POLAR_BEAR = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientpolarbear"),
            EntityType.Builder.of(ClientPolarBear::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 1f)
                    .eyeHeight(1f)
                    .build(POLAR_BEAR_KEY)
    );

    public static final EntityType<@NotNull ClientPufferFish> PUFFERFISH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientpufferfish"),
            EntityType.Builder.of(ClientPufferFish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(PUFFERFISH_KEY)
    );

    public static final EntityType<@NotNull ClientSpider> SPIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientspider"),
            EntityType.Builder.of(ClientSpider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(SPIDER_KEY)
    );

    public static final EntityType<@NotNull ClientWolf> WOLF = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientwolf"),
            EntityType.Builder.of(ClientWolf::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(WOLF_KEY)
    );

    public static final EntityType<@NotNull ClientBlaze> BLAZE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientblaze"),
            EntityType.Builder.of(ClientBlaze::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(BLAZE_KEY)
    );

    public static final EntityType<@NotNull ClientBreeze> BREEZE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientbreeze"),
            EntityType.Builder.of(ClientBreeze::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(BREEZE_KEY)
    );

    public static final EntityType<@NotNull ClientCreaking> CREAKING = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcreaking"),
            EntityType.Builder.of(ClientCreaking::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 3f)
                    .eyeHeight(3f)
                    .build(CREAKING_KEY)
    );

    public static final EntityType<@NotNull ClientCreeper> CREEPER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientcreeper"),
            EntityType.Builder.of(ClientCreeper::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(CREEPER_KEY)
    );

    public static final EntityType<@NotNull ClientElderGuardian> ELDER_GUARDIAN_COOKIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientelderguardian"),
            EntityType.Builder.of(ClientElderGuardian::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(3f, 3f)
                    .eyeHeight(3f)
                    .build(ELDER_GUARDIAN_KEY)
    );

    public static final EntityType<@NotNull ClientEndermite> ENDERMITE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientendermite"),
            EntityType.Builder.of(ClientEndermite::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(ENDERMITE_KEY)
    );

    public static final EntityType<@NotNull ClientEvoker> EVOKER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientevoker"),
            EntityType.Builder.of(ClientEvoker::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(EVOKER_KEY)
    );

    public static final EntityType<@NotNull ClientHappyGhast> HAPPY_GHAST = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clienthappyghast"),
            EntityType.Builder.of(ClientHappyGhast::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)
                    .eyeHeight(4f)
                    .build(HAPPY_GHAST_KEY)
    );

    public static final EntityType<@NotNull ClientGhast> GHAST = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientghast"),
            EntityType.Builder.of(ClientGhast::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)
                    .eyeHeight(4f)
                    .build(GHAST_KEY)
    );

    public static final EntityType<@NotNull ClientGuardian> GUARDIAN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientguardian"),
            EntityType.Builder.of(ClientGuardian::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(GUARDIAN_KEY)
    );

    public static final EntityType<@NotNull ClientHoglin> HOGLIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clienthoglin"),
            EntityType.Builder.of(ClientHoglin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(HOGLIN_KEY)
    );

    public static final EntityType<@NotNull ClientMagmaCube> MAGMA_CUBE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientmagmacube"),
            EntityType.Builder.of(ClientMagmaCube::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(MAGMA_CUBE_KEY)
    );

    public static final EntityType<@NotNull ClientPhantom> PHANTOM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientphantom"),
            EntityType.Builder.of(ClientPhantom::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(PHANTOM_KEY)
    );

    public static final EntityType<@NotNull ClientPillager> PILLAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientpillager"),
            EntityType.Builder.of(ClientPillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(PILLAGER_KEY)
    );

    public static final EntityType<@NotNull ClientRavager> RAVAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientravager"),
            EntityType.Builder.of(ClientRavager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(3f, 2f)
                    .eyeHeight(2f)
                    .build(RAVAGER_KEY)
    );

    public static final EntityType<@NotNull ClientSilverfish> SILVERFISH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientsilverfish"),
            EntityType.Builder.of(ClientSilverfish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(SILVERFISH_KEY)
    );

    public static final EntityType<@NotNull ClientShulker> SHULKER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientshulker"),
            EntityType.Builder.of(ClientShulker::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(SHULKER_KEY)
    );

    public static final EntityType<@NotNull ClientSkeleton> SKELETON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientskeleton"),
            EntityType.Builder.of(ClientSkeleton::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(SKELETON_KEY)
    );

    public static final EntityType<@NotNull ClientSlime> SLIME = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientslime"),
            EntityType.Builder.of(ClientSlime::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(SLIME_KEY)
    );

    public static final EntityType<@NotNull ClientVex> VEX = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientvex"),
            EntityType.Builder.of(ClientVex::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(VEX_KEY)
    );

    public static final EntityType<@NotNull ClientVindicator> VINDICATOR = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientvindicator"),
            EntityType.Builder.of(ClientVindicator::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(VINDICATOR_KEY)
    );

    public static final EntityType<@NotNull ClientWarden> WARDEN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientwarden"),
            EntityType.Builder.of(ClientWarden::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 3f)
                    .eyeHeight(3f)
                    .build(WARDEN_KEY)
    );

    public static final EntityType<@NotNull ClientWitch> WITCH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientwitch"),
            EntityType.Builder.of(ClientWitch::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(WITCH_KEY)
    );

    public static final EntityType<@NotNull ClientZombie> ZOMBIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientzombie"),
            EntityType.Builder.of(ClientZombie::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(ZOMBIE_KEY)
    );

    public static final EntityType<@NotNull ClientZombieVillager> ZOMBIE_VILLAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientzombievillager"),
            EntityType.Builder.of(ClientZombieVillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(ZOMBIE_VILLAGER_KEY)
    );

    public static final EntityType<@NotNull ClientHusk> HUSK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clienthusk"),
            EntityType.Builder.of(ClientHusk::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(HUSK_KEY)
    );

    public static final EntityType<@NotNull ClientDrowned> DROWNED = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientdrowned"),
            EntityType.Builder.of(ClientDrowned::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(DROWNED_KEY)
    );

    public static final EntityType<@NotNull ClientBogged> BOGGED = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientbogged"),
            EntityType.Builder.of(ClientBogged::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(BOGGED_KEY)
    );

        public static final EntityType<@NotNull ClientParched> PARCHED = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientparched"),
            EntityType.Builder.of(ClientParched::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(PARCHED_KEY)
    );

    public static final EntityType<@NotNull ClientStray> STRAY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientstray"),
            EntityType.Builder.of(ClientStray::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(STRAY_KEY)
    );

    public static final EntityType<@NotNull ClientWitherSkeleton> WITHER_SKELETON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.of(ClientWitherSkeleton::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(WITHER_SKELETON_KEY)
    );

    public static final EntityType<@NotNull ClientEnderDragon> ENDER_DRAGON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientenderdragon"),
            EntityType.Builder.of(ClientEnderDragon::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(16f, 8f)
                    .eyeHeight(1f)
                    .build(ENDER_DRAGON_KEY)
    );

    public static final EntityType<@NotNull ClientWither> WITHER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "clientwither"),
            EntityType.Builder.of(ClientWither::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(WITHER_KEY)
    );

    @Override
    public void onInitialize() {

        FabricDefaultAttributeRegistry.register(RACOON, Racoon.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DUCK, Duck.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PENGUIN, Penguin.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SHEEP, ClientSheep.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CAT, ClientCat.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ALLAY, ClientAllay.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ARMADILLO, ClientArmadillo.createAttributes().build());
        FabricDefaultAttributeRegistry.register(AXOLOTL, ClientAxolotl.createAttributes().build());
        FabricDefaultAttributeRegistry.register(BAT, ClientBat.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CAMEL, ClientCamel.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CHICKEN, ClientChicken.createAttributes().build());
        FabricDefaultAttributeRegistry.register(COD, ClientCod.createAttributes().build());
        FabricDefaultAttributeRegistry.register(COPPER_GOLEM, ClientCopperGolem.createAttributes().build());
        FabricDefaultAttributeRegistry.register(COW, ClientCow.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DONKEY, ClientDonkey.createAttributes().build());
        FabricDefaultAttributeRegistry.register(FROG, ClientFrog.createAttributes().build());
        FabricDefaultAttributeRegistry.register(HORSE, ClientHorse.createAttributes().build());
        FabricDefaultAttributeRegistry.register(MOOSHROOM, ClientMooshroom.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PARROT, ClientParrot.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PIG, ClientPig.createAttributes().build());
        FabricDefaultAttributeRegistry.register(RABBIT, ClientRabbit.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SALMON, ClientSalmon.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SNIFFER, ClientSniffer.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SNOW_GOLEM, ClientSnowGolem.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SQUID, ClientSquid.createAttributes().build());
        FabricDefaultAttributeRegistry.register(STRIDER, ClientStrider.createAttributes().build());
        FabricDefaultAttributeRegistry.register(TADPOLE, ClientTadpole.createAttributes().build());
        FabricDefaultAttributeRegistry.register(TROPICAL_FISH, ClientTropicalFish.createAttributes().build());
        FabricDefaultAttributeRegistry.register(TURTLE, ClientTurtle.createAttributes().build());
        FabricDefaultAttributeRegistry.register(VILLAGER, ClientVillager.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WANDERING_TRADER, ClientWanderingTrader.createAttributes().build());
        FabricDefaultAttributeRegistry.register(BEE, ClientBee.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CAVE_SPIDER, ClientCaveSpider.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DOLPHIN, ClientDolphin.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ENDERMAN, ClientEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(FOX, ClientFox.createAttributes().build());
        FabricDefaultAttributeRegistry.register(GOAT, ClientGoat.createAttributes().build());
        FabricDefaultAttributeRegistry.register(IRON_GOLEM, ClientIronGolem.createAttributes().build());
        FabricDefaultAttributeRegistry.register(LLAMA, ClientLlama.createAttributes().build());
        FabricDefaultAttributeRegistry.register(NAUTILUS, ClientNautilus.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PANDA, ClientPanda.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PIGLIN, ClientWanderingTrader.createAttributes().build());
        FabricDefaultAttributeRegistry.register(POLAR_BEAR, ClientPolarBear.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PUFFERFISH, ClientPufferFish.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SPIDER, ClientSpider.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WOLF, ClientWolf.createAttributes().build());
        FabricDefaultAttributeRegistry.register(BLAZE, ClientBlaze.createAttributes().build());
        FabricDefaultAttributeRegistry.register(BREEZE, ClientBreeze.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CREAKING, ClientCreaking.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CREEPER, ClientCreeper.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ELDER_GUARDIAN_COOKIE, ClientElderGuardian.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ENDERMITE, ClientEndermite.createAttributes().build());
        FabricDefaultAttributeRegistry.register(EVOKER, ClientEvoker.createAttributes().build());
        FabricDefaultAttributeRegistry.register(GHAST, ClientGhast.createAttributes().build());
        FabricDefaultAttributeRegistry.register(HAPPY_GHAST, ClientHappyGhast.createAttributes().build());
        FabricDefaultAttributeRegistry.register(GUARDIAN, ClientGuardian.createAttributes().build());
        FabricDefaultAttributeRegistry.register(HOGLIN, ClientHoglin.createAttributes().build());
        FabricDefaultAttributeRegistry.register(MAGMA_CUBE, ClientMagmaCube.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PHANTOM, ClientPhantom.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PILLAGER, ClientPillager.createAttributes().build());
        FabricDefaultAttributeRegistry.register(RAVAGER, ClientRavager.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SHULKER, ClientShulker.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SILVERFISH, ClientSilverfish.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SKELETON, ClientSkeleton.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SLIME, ClientSlime.createAttributes().build());
        FabricDefaultAttributeRegistry.register(VEX, ClientVex.createAttributes().build());
        FabricDefaultAttributeRegistry.register(VINDICATOR, ClientVindicator.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WARDEN, ClientWarden.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WITCH, ClientWitch.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ZOMBIE, ClientZombie.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ZOMBIE_VILLAGER, ClientZombieVillager.createAttributes().build());
        FabricDefaultAttributeRegistry.register(HUSK, ClientHusk.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DROWNED, ClientDrowned.createAttributes().build());
        FabricDefaultAttributeRegistry.register(BOGGED, ClientBogged.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PARCHED, ClientParched.createAttributes().build());
        FabricDefaultAttributeRegistry.register(STRAY, ClientStray.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WITHER_SKELETON, ClientWitherSkeleton.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ENDER_DRAGON, ClientEnderDragon.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WITHER, ClientWither.createAttributes().build());

        PetsSounds.initialize();

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
}