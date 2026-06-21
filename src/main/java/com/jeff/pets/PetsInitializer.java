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
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "pets-mod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final ResourceKey<@NotNull EntityType<?>> RACOON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "racoon"));
    public static final EntityType<@NotNull Racoon> RACOON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "racoon"),
            EntityType.Builder.of(Racoon::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .eyeHeight(1)
                    .build(RACOON_KEY));
    private static final ResourceKey<@NotNull EntityType<?>> ALLAY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientallay"));
    public static final EntityType<@NotNull ClientAllay> ALLAY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientallay"),
            EntityType.Builder.of(ClientAllay::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.35f, 0.6f)
                    .eyeHeight(0.6f)
                    .build(ALLAY_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ARMADILLO_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientarmadillo"));
    public static final EntityType<@NotNull ClientArmadillo> ARMADILLO = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientarmadillo"),
            EntityType.Builder.of(ClientArmadillo::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.65f)
                    .eyeHeight(0.65f)
                    .build(ARMADILLO_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> AXOLOTL_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientaxolotl"));
    public static final EntityType<@NotNull ClientAxolotl> AXOLOTL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientaxolotl"),
            EntityType.Builder.of(ClientAxolotl::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(AXOLOTL_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> BAT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbat"));
    public static final EntityType<@NotNull ClientBat> BAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbat"),
            EntityType.Builder.of(ClientBat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)
                    .eyeHeight(0.9f)
                    .build(BAT_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> CAMEL_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcamel"));
    public static final EntityType<@NotNull ClientCamel> CAMEL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcamel"),
            EntityType.Builder.of(ClientCamel::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.7f, 2.375f)
                    .eyeHeight(2.375f)
                    .build(CAMEL_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> DUCK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "duck"));
    public static final EntityType<@NotNull Duck> DUCK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "duck"),
            EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.7f)
                    .eyeHeight(1)
                    .build(DUCK_KEY));
    private static final ResourceKey<@NotNull EntityType<?>> PENGUIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "penguin"));
    public static final EntityType<@NotNull Penguin> PENGUIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "penguin"),
            EntityType.Builder.of(Penguin::new, MobCategory.AMBIENT)
                    .sized(1f, 1.5f)
                    .eyeHeight(1f)
                    .build(PENGUIN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SHEEP_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsheep"));
    public static final EntityType<@NotNull ClientSheep> SHEEP = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsheep"),
            EntityType.Builder.of(ClientSheep::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.3f)
                    .eyeHeight(1.3f)
                    .build(SHEEP_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> CAT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcat"));
    public static final EntityType<@NotNull ClientCat> CAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcat"),
            EntityType.Builder.of(ClientCat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.7f)
                    .eyeHeight(1f)
                    .build(CAT_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> CHICKEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientchicken"));
    public static final EntityType<@NotNull ClientChicken> CHICKEN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientchicken"),
            EntityType.Builder.of(ClientChicken::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.7f)
                    .eyeHeight(0.7f)
                    .build(CHICKEN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> COD_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcod"));
    public static final EntityType<@NotNull ClientCod> COD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcod"),
            EntityType.Builder.of(ClientCod::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.3f)
                    .eyeHeight(0.3f)
                    .build(COD_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> COW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcow"));
    public static final EntityType<@NotNull ClientCow> COW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcow"),
            EntityType.Builder.of(ClientCow::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)
                    .eyeHeight(1.4f)
                    .build(COW_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> DONKEY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientdonkey"));
    public static final EntityType<@NotNull ClientDonkey> DONKEY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientdonkey"),
            EntityType.Builder.of(ClientDonkey::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.5f)
                    .eyeHeight(1.5f)
                    .build(DONKEY_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> FROG_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientfrog"));
    public static final EntityType<@NotNull ClientFrog> FROG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientfrog"),
            EntityType.Builder.of(ClientFrog::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.5f)
                    .eyeHeight(0.5f)
                    .build(FROG_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> HORSE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthorse"));
    public static final EntityType<@NotNull ClientHorse> HORSE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthorse"),
            EntityType.Builder.of(ClientHorse::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.6f)
                    .eyeHeight(1.6f)
                    .build(HORSE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> MOOSHROOM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientmooshroom"));
    public static final EntityType<@NotNull ClientMooshroom> MOOSHROOM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientmooshroom"),
            EntityType.Builder.of(ClientMooshroom::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)
                    .eyeHeight(1.4f)
                    .build(MOOSHROOM_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PARROT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientparrot"));
    public static final EntityType<@NotNull ClientParrot> PARROT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientparrot"),
            EntityType.Builder.of(ClientParrot::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)
                    .eyeHeight(0.9f)
                    .build(PARROT_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PIG_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpig"));
    public static final EntityType<@NotNull ClientPig> PIG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpig"),
            EntityType.Builder.of(ClientPig::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.9f)
                    .eyeHeight(0.9f)
                    .build(PIG_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> RABBIT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientrabbit"));
    public static final EntityType<@NotNull ClientRabbit> RABBIT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientrabbit"),
            EntityType.Builder.of(ClientRabbit::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.5f)
                    .eyeHeight(0.5f)
                    .build(RABBIT_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SALMON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsalmon"));
    public static final EntityType<@NotNull ClientSalmon> SALMON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsalmon"),
            EntityType.Builder.of(ClientSalmon::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.35f, 0.2f)
                    .eyeHeight(0.2f)
                    .build(SALMON_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SNIFFER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsniffer"));
    public static final EntityType<@NotNull ClientSniffer> SNIFFER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsniffer"),
            EntityType.Builder.of(ClientSniffer::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.9f, 1.75f)
                    .eyeHeight(1.75f)
                    .build(SNIFFER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SNOW_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsnowgolem"));
    public static final EntityType<@NotNull ClientSnowGolem> SNOW_GOLEM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.of(ClientSnowGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 1.9f)
                    .eyeHeight(1.9f)
                    .build(SNOW_GOLEM_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SQUID_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsquid"));
    public static final EntityType<@NotNull ClientSquid> SQUID = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsquid"),
            EntityType.Builder.of(ClientSquid::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.8f, -0.8f)
                    .eyeHeight(0.8f)
                    .build(SQUID_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> STRIDER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientstrider"));
    public static final EntityType<@NotNull ClientStrider> STRIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientstrider"),
            EntityType.Builder.of(ClientStrider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.7f)
                    .eyeHeight(1.7f)
                    .build(STRIDER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> TADPOLE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienttadpole"));
    public static final EntityType<@NotNull ClientTadpole> TADPOLE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienttadpole"),
            EntityType.Builder.of(ClientTadpole::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)
                    .eyeHeight(0.3f)
                    .build(TADPOLE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> TROPICAL_FISH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienttropicalfish"));
    public static final EntityType<@NotNull ClientTropicalFish> TROPICAL_FISH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienttropicalfish"),
            EntityType.Builder.of(ClientTropicalFish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 1f)
                    .eyeHeight(1f)
                    .build(TROPICAL_FISH_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> TURTLE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientturtle"));
    public static final EntityType<@NotNull ClientTurtle> TURTLE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientturtle"),
            EntityType.Builder.of(ClientTurtle::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.2f, 0.4f)
                    .eyeHeight(0.4f)
                    .build(TURTLE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> VILLAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientvillager"));
    public static final EntityType<@NotNull ClientVillager> VILLAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientvillager"),
            EntityType.Builder.of(ClientVillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(VILLAGER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> WANDERING_TRADER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwanderingtrader"));
    public static final EntityType<@NotNull ClientWanderingTrader> WANDERING_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwandeinrgtrader"),
            EntityType.Builder.of(ClientWanderingTrader::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(WANDERING_TRADER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> BEE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbee"));
    public static final EntityType<@NotNull ClientBee> BEE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbee"),
            EntityType.Builder.of(ClientBee::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.6f)
                    .eyeHeight(0.6f)
                    .build(BEE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> CAVE_SPIDER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcavespider"));
    public static final EntityType<@NotNull ClientCaveSpider> CAVE_SPIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcavespider"),
            EntityType.Builder.of(ClientCaveSpider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.5f)
                    .eyeHeight(0.5f)
                    .build(CAVE_SPIDER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> DOLPHIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientdolphin"));
    public static final EntityType<@NotNull ClientDolphin> DOLPHIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientdolphin"),
            EntityType.Builder.of(ClientDolphin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.6f)
                    .eyeHeight(0.6f)
                    .build(DOLPHIN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ENDERMAN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientenderman"));
    public static final EntityType<@NotNull ClientEnderman> ENDERMAN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientenderman"),
            EntityType.Builder.of(ClientEnderman::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 2.9f)
                    .eyeHeight(2.9f)
                    .build(ENDERMAN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> FOX_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientfox"));
    public static final EntityType<@NotNull ClientFox> FOX = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientfox"),
            EntityType.Builder.of(ClientFox::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.7f)
                    .eyeHeight(0.7f)
                    .build(FOX_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> GOAT_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientgoat"));
    public static final EntityType<@NotNull ClientGoat> GOAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientgoat"),
            EntityType.Builder.of(ClientGoat::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.3f)
                    .eyeHeight(1.3f)
                    .build(GOAT_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> IRON_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientirongolem"));
    public static final EntityType<@NotNull ClientIronGolem> IRON_GOLEM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientirongolem"),
            EntityType.Builder.of(ClientIronGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 2.7f)
                    .eyeHeight(2.7f)
                    .build(IRON_GOLEM_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> LLAMA_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientllama"));
    public static final EntityType<@NotNull ClientLlama> LLAMA = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientllama"),
            EntityType.Builder.of(ClientLlama::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.87f)
                    .eyeHeight(1.87f)
                    .build(LLAMA_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> PANDA_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpanda"));
    public static final EntityType<@NotNull ClientPanda> PANDA = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpanda"),
            EntityType.Builder.of(ClientPanda::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3f, 1.25f)
                    .eyeHeight(1.25f)
                    .build(PANDA_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PIGLIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpiglin"));
    public static final EntityType<@NotNull ClientPiglin> PIGLIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpiglin"),
            EntityType.Builder.of(ClientPiglin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(PIGLIN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> POLAR_BEAR_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpolarbear"));
    public static final EntityType<@NotNull ClientPolarBear> POLAR_BEAR = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpolarbear"),
            EntityType.Builder.of(ClientPolarBear::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 1.4f)
                    .eyeHeight(1.4f)
                    .build(POLAR_BEAR_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PUFFERFISH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpufferfish"));
    public static final EntityType<@NotNull ClientPufferFish> PUFFERFISH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpufferfish"),
            EntityType.Builder.of(ClientPufferFish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.7f)
                    .eyeHeight(0.7f)
                    .build(PUFFERFISH_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SPIDER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientspider"));
    public static final EntityType<@NotNull ClientSpider> SPIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientspider"),
            EntityType.Builder.of(ClientSpider::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 0.9f)
                    .eyeHeight(0.9f)
                    .build(SPIDER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> WOLF_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwolf"));
    public static final EntityType<@NotNull ClientWolf> WOLF = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwolf"),
            EntityType.Builder.of(ClientWolf::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.85f)
                    .eyeHeight(0.85f)
                    .build(WOLF_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> BLAZE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientblaze"));
    public static final EntityType<@NotNull ClientBlaze> BLAZE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientblaze"),
            EntityType.Builder.of(ClientBlaze::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.8f)
                    .eyeHeight(1.8f)
                    .build(BLAZE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> BREEZE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbreeze"));
    public static final EntityType<@NotNull ClientBreeze> BREEZE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbreeze"),
            EntityType.Builder.of(ClientBreeze::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.77f)
                    .eyeHeight(1.77f)
                    .build(BREEZE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> CREAKING_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcreaking"));
    public static final EntityType<@NotNull ClientCreaking> CREAKING = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcreaking"),
            EntityType.Builder.of(ClientCreaking::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 2.7f)
                    .eyeHeight(2.7f)
                    .build(CREAKING_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> CREEPER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcreeper"));
    public static final EntityType<@NotNull ClientCreeper> CREEPER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientcreeper"),
            EntityType.Builder.of(ClientCreeper::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.7f)
                    .eyeHeight(1.7f)
                    .build(CREEPER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ELDER_GUARDIAN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientelderguardian"));
    public static final EntityType<@NotNull ClientElderGuardian> ELDER_GUARDIAN_COOKIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientelderguardian"),
            EntityType.Builder.of(ClientElderGuardian::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.9975f, 1.9975f)
                    .eyeHeight(1.9975f)
                    .build(ELDER_GUARDIAN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ENDERMITE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientendermite"));
    public static final EntityType<@NotNull ClientEndermite> ENDERMITE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientendermite"),
            EntityType.Builder.of(ClientEndermite::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)
                    .eyeHeight(0.3f)
                    .build(ENDERMITE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> EVOKER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwolf"));
    public static final EntityType<@NotNull ClientEvoker> EVOKER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientevoker"),
            EntityType.Builder.of(ClientEvoker::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(EVOKER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> GHAST_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientghast"));
    public static final EntityType<@NotNull ClientGhast> GHAST = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientghast"),
            EntityType.Builder.of(ClientGhast::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)
                    .eyeHeight(4f)
                    .build(GHAST_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> HAPPY_GHAST_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthappyghast"));
    public static final EntityType<@NotNull ClientHappyGhast> HAPPY_GHAST = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthappyghast"),
            EntityType.Builder.of(ClientHappyGhast::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)
                    .eyeHeight(4f)
                    .build(HAPPY_GHAST_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> GUARDIAN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientguardian"));
    public static final EntityType<@NotNull ClientGuardian> GUARDIAN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientguardian"),
            EntityType.Builder.of(ClientGuardian::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.85f, 0.85f)
                    .eyeHeight(0.85f)
                    .build(GUARDIAN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> HOGLIN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthoglin"));
    public static final EntityType<@NotNull ClientHoglin> HOGLIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthoglin"),
            EntityType.Builder.of(ClientHoglin::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.4f)
                    .eyeHeight(1.4f)
                    .build(HOGLIN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> MAGMA_CUBE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientmagmacube"));
    public static final EntityType<@NotNull ClientMagmaCube> MAGMA_CUBE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientmagmacube"),
            EntityType.Builder.of(ClientMagmaCube::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .eyeHeight(2f)
                    .build(MAGMA_CUBE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PHANTOM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientphantom"));
    public static final EntityType<@NotNull ClientPhantom> PHANTOM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientphantom"),
            EntityType.Builder.of(ClientPhantom::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.5f)
                    .eyeHeight(0.5f)
                    .build(PHANTOM_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PILLAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpillager"));
    public static final EntityType<@NotNull ClientPillager> PILLAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientpillager"),
            EntityType.Builder.of(ClientPillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(PILLAGER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> RAVAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientravager"));
    public static final EntityType<@NotNull ClientRavager> RAVAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientravager"),
            EntityType.Builder.of(ClientRavager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.95f, 2.2f)
                    .eyeHeight(2.2f)
                    .build(RAVAGER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SHULKER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientshulker"));
    public static final EntityType<@NotNull ClientShulker> SHULKER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientshulker"),
            EntityType.Builder.of(ClientShulker::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .eyeHeight(2f)
                    .build(SHULKER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SILVERFISH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsilverfish"));
    public static final EntityType<@NotNull ClientSilverfish> SILVERFISH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientsilverfish"),
            EntityType.Builder.of(ClientSilverfish::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)
                    .eyeHeight(0.3f)
                    .build(SILVERFISH_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SKELETON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientskeleton"));
    public static final EntityType<@NotNull ClientSkeleton> SKELETON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientskeleton"),
            EntityType.Builder.of(ClientSkeleton::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(SKELETON_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SLIME_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientslime"));
    public static final EntityType<@NotNull ClientSlime> SLIME = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientslime"),
            EntityType.Builder.of(ClientSlime::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .eyeHeight(2f)
                    .build(SLIME_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> VEX_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientvex"));
    public static final EntityType<@NotNull ClientVex> VEX = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientvex"),
            EntityType.Builder.of(ClientVex::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.8f)
                    .eyeHeight(1f)
                    .build(VEX_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> VINDICATOR_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientvindicator"));
    public static final EntityType<@NotNull ClientVindicator> VINDICATOR = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientvindicator"),
            EntityType.Builder.of(ClientVindicator::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(VINDICATOR_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> WARDEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwarden"));
    public static final EntityType<@NotNull ClientWarden> WARDEN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwarden"),
            EntityType.Builder.of(ClientWarden::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 2.9f)
                    .eyeHeight(2.9f)
                    .build(WARDEN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> WITCH_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwitch"));
    public static final EntityType<@NotNull ClientWitch> WITCH = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwitch"),
            EntityType.Builder.of(ClientWitch::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(WITCH_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientzombie"));
    public static final EntityType<@NotNull ClientZombie> ZOMBIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientzombie"),
            EntityType.Builder.of(ClientZombie::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(ZOMBIE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ZOMBIE_VILLAGER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientzombievillager"));
    public static final EntityType<@NotNull ClientZombieVillager> ZOMBIE_VILLAGER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientzombievillager"),
            EntityType.Builder.of(ClientZombieVillager::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(2f)
                    .build(ZOMBIE_VILLAGER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> HUSK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthusk"));
    public static final EntityType<@NotNull ClientHusk> HUSK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clienthusk"),
            EntityType.Builder.of(ClientHusk::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(2f)
                    .build(HUSK_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> DROWNED_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientdrowned"));
    public static final EntityType<@NotNull ClientDrowned> DROWNED = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientdrowned"),
            EntityType.Builder.of(ClientDrowned::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(2f)
                    .build(DROWNED_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> BOGGED_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbogged"));
    public static final EntityType<@NotNull ClientBogged> BOGGED = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientbogged"),
            EntityType.Builder.of(ClientBogged::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(2f)
                    .build(BOGGED_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> STRAY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientstray"));
    public static final EntityType<@NotNull ClientStray> STRAY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientstray"),
            EntityType.Builder.of(ClientStray::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(STRAY_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> WITHER_SKELETON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwitherskeleton"));
    public static final EntityType<@NotNull ClientWitherSkeleton> WITHER_SKELETON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.of(ClientWitherSkeleton::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(WITHER_SKELETON_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ENDER_DRAGON_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientenderdragon"));
    public static final EntityType<@NotNull ClientEnderDragon> ENDER_DRAGON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientenderdragon"),
            EntityType.Builder.of(ClientEnderDragon::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(16f, 8f)
                    .eyeHeight(1f)
                    .build(ENDER_DRAGON_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> WITHER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwither"));
    public static final EntityType<@NotNull ClientWither> WITHER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwither"),
            EntityType.Builder.of(ClientWither::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 3f)
                    .eyeHeight(3f)
                    .build(WITHER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> ANGRY_GHAST_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "clientwither"));
    public static final EntityType<@NotNull AngryGhast> ANGRY_GHAST = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("angry_ghast"),
            EntityType.Builder.of(AngryGhast::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)
                    .eyeHeight(4f)
                    .build(ANGRY_GHAST_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> BATATO_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "batato"));
    public static final EntityType<@NotNull Batato> BATATO = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("batato"),
            EntityType.Builder.of(Batato::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)
                    .eyeHeight(0.9f)
                    .build(BATATO_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> DIAMOND_CHICKEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "diamond_chicken"));
    public static final EntityType<@NotNull DiamondChicken> DIAMOND_CHICKEN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("diamond_chicken"),
            EntityType.Builder.of(DiamondChicken::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.7f)
                    .eyeHeight(0.7f)
                    .build(DIAMOND_CHICKEN_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> LOVE_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "love_golem"));
    public static final EntityType<@NotNull LoveGolem> LOVE_GOLEM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("love_golem"),
            EntityType.Builder.of(LoveGolem::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 2.7f)
                    .eyeHeight(3f)
                    .build(LOVE_GOLEM_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> MEGA_SPUD_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "mega_spud"));
    public static final EntityType<@NotNull MegaSpud> MEGA_SPUD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("mega_spud"),
            EntityType.Builder.of(MegaSpud::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(9f, 12f)
                    .eyeHeight(12f)
                    .build(MEGA_SPUD_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> MOON_COW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "moon_cow"));
    public static final EntityType<@NotNull MoonCow> MOON_COW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("moon_cow"),
            EntityType.Builder.of(MoonCow::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)
                    .eyeHeight(1.4f)
                    .build(MOON_COW_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> NERD_CREEPER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "nerd_creeper"));
    public static final EntityType<@NotNull NerdCreeper> NERD_CREEPER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("nerd_creeper"),
            EntityType.Builder.of(NerdCreeper::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.7f)
                    .eyeHeight(1.7f)
                    .build(NERD_CREEPER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PINK_WITHER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pink_wither"));
    public static final EntityType<@NotNull PinkWither> PINK_WITHER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("pink_wither"),
            EntityType.Builder.of(PinkWither::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(2f, 3f)
                    .eyeHeight(3f)
                    .build(PINK_WITHER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> PLAGUEWHALE_SLAB_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "plaguewhale_slab"));
    public static final EntityType<@NotNull PlaguewhaleSlab> PLAGUEWHALE_SLAB = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "plaguewhale_slab"),
            EntityType.Builder.of(PlaguewhaleSlab::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(1.9975f, 1.9975f)
                    .eyeHeight(2f)
                    .build(PLAGUEWHALE_SLAB_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> POISONOUS_POTATO_ZOMBIE_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "poisonous_potato_zombie"));
    public static final EntityType<@NotNull PoisonousPotatoZombie> POISONOUS_POTATO_ZOMBIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("poisonous_potato_zombie"),
            EntityType.Builder.of(PoisonousPotatoZombie::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(POISONOUS_POTATO_ZOMBIE_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> RAY_TRACING_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "ray_tracing"));
    public static final EntityType<@NotNull RayTracing> RAY_TRACING = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("ray_tracing"),
            EntityType.Builder.of(RayTracing::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(RAY_TRACING_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> REDSTONE_BUG_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "redstone_bug"));
    public static final EntityType<@NotNull RedstoneBug> REDSTONE_BUG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("redstone_bug"),
            EntityType.Builder.of(RedstoneBug::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)
                    .eyeHeight(0.3f)
                    .build(REDSTONE_BUG_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> SMILING_CREEPER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "smiling_creeper"));
    public static final EntityType<@NotNull SmilingCreeper> SMILING_CREEPER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("smiling_creeper"),
            EntityType.Builder.of(SmilingCreeper::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.7f)
                    .eyeHeight(1.7f)
                    .build(SMILING_CREEPER_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> TOXIFIN_SLAB_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "toxifin_slab"));
    public static final EntityType<@NotNull ToxifinSlab> TOXIFIN_SLAB = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("toxifin_slab"),
            EntityType.Builder.of(ToxifinSlab::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.85f, 0.85f)
                    .eyeHeight(0.85f)
                    .build(TOXIFIN_SLAB_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> POTATO_HUSK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "potatohusk"));
    public static final EntityType<@NotNull PotatoHusk> POTATO_HUSK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.withDefaultNamespace("potato_husk"),
            EntityType.Builder.of(PotatoHusk::new, MobCategory.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(POTATO_HUSK_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> HEAD_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "head"));
    public static final EntityType<@NotNull Head> HEAD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "head"),
            EntityType.Builder.of(Head::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .eyeHeight(0.5f)
                    .build(HEAD_KEY));
    private static final ResourceKey<@NotNull EntityType<?>> TRAITOR_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "traitor"));
    public static final EntityType<@NotNull Traitor> TRAITOR = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "traitor"),
            EntityType.Builder.of(Traitor::new, MobCategory.CREATURE)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .eyeHeight(1.95f)
                    .build(TRAITOR_KEY));

    private static final ResourceKey<@NotNull EntityType<?>> DUMBO_OCTOPUS_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "dumbo_octopus"));
    public static final EntityType<@NotNull DumboOctopus> DUMBO_OCTOPUS = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "dumbo_octopus"),
            EntityType.Builder.of(DumboOctopus::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)
                    .eyeHeight(0.5f)
                    .build(DUMBO_OCTOPUS_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> KOI_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "koi"));
    public static final EntityType<@NotNull Koi> KOI = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "koi"),
            EntityType.Builder.of(Koi::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f, 0.6f)
                    .eyeHeight(0.6f)
                    .build(KOI_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> STINGRAY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "stingray"));
    public static final EntityType<@NotNull Stingray> STINGRAY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "stringray"),
            EntityType.Builder.of(Stingray::new, MobCategory.WATER_AMBIENT)
                    .sized(1.0f, 0.4f)
                    .eyeHeight(0.4f)
                    .build(STINGRAY_KEY)
    );

    /**
     * Registers the entities' attributes. Warns about the call to register not working, but it
     * ends up working fine in-game - likely a mixup in either the Fabric API or IntelliJ.
     */
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
        FabricDefaultAttributeRegistry.register(STRAY, ClientStray.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WITHER_SKELETON, ClientWitherSkeleton.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ENDER_DRAGON, ClientEnderDragon.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WITHER, ClientWither.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ANGRY_GHAST, AngryGhast.createAttributes().build());
        FabricDefaultAttributeRegistry.register(BATATO, Batato.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DIAMOND_CHICKEN, DiamondChicken.createAttributes().build());
        FabricDefaultAttributeRegistry.register(LOVE_GOLEM, LoveGolem.createAttributes().build());
        FabricDefaultAttributeRegistry.register(MEGA_SPUD, MegaSpud.createAttributes().build());
        FabricDefaultAttributeRegistry.register(MOON_COW, MoonCow.createAttributes().build());
        FabricDefaultAttributeRegistry.register(NERD_CREEPER, NerdCreeper.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PINK_WITHER, PinkWither.createAttributes().build());
        FabricDefaultAttributeRegistry.register(PLAGUEWHALE_SLAB, PlaguewhaleSlab.createAttributes().build());
        FabricDefaultAttributeRegistry.register(POISONOUS_POTATO_ZOMBIE, PoisonousPotatoZombie.createAttributes().build());
        FabricDefaultAttributeRegistry.register(RAY_TRACING, RayTracing.createAttributes().build());
        FabricDefaultAttributeRegistry.register(REDSTONE_BUG, RedstoneBug.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SMILING_CREEPER, SmilingCreeper.createAttributes().build());
        FabricDefaultAttributeRegistry.register(TOXIFIN_SLAB, ToxifinSlab.createAttributes().build());
        FabricDefaultAttributeRegistry.register(POTATO_HUSK, PotatoHusk.createAttributes().build());
        FabricDefaultAttributeRegistry.register(HEAD, Head.createAttributes().build());
        FabricDefaultAttributeRegistry.register(TRAITOR, Traitor.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DUMBO_OCTOPUS, DumboOctopus.createAttributes().build());
        FabricDefaultAttributeRegistry.register(KOI, Koi.createAttributes().build());
        FabricDefaultAttributeRegistry.register(STINGRAY, Stingray.createAttributes().build());

        PetsSounds.initialize();

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
}