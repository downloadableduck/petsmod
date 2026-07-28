package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
@Mod(MOD_ID)
@Mod.EventBusSubscriber
public class PetsInitializer {

    static {
        PetsClientInitializer.register();
        MinecraftForge.EVENT_BUS.register(PetsInitializer.class);
        IEventBus bus = MinecraftForge.EVENT_BUS;
        PetsSounds.initialize(bus);
        bus.register(PetsInitializer.class);
        PetsSounds.initialize(bus);
    }

    public PetsInitializer() {
        PetsClientInitializer.register();
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        PetsSounds.initialize(bus);
        bus.register(Central.class);
        bus.register(new Central());
        bus.register(PetsInitializer.class);
    }
    public static final String MOD_ID = "pets_mod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final EntityType<Racoon> RACOON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "racoon"),
            EntityType.Builder.of(Racoon::new, EntityClassification.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build("racoon")
    );

    public static final EntityType<ClientBat> BAT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientbat"),
            EntityType.Builder.of(ClientBat::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)
                    .build("clientbat")
    );

    public static final EntityType<Duck> DUCK = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "duck"),
            EntityType.Builder.of(Duck::new, EntityClassification.CREATURE)
                    .sized(0.4f, 0.7f)
                    .build("duck")
    );

    public static final EntityType<Penguin> PENGUIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "penguin"),
            EntityType.Builder.of(Penguin::new, EntityClassification.AMBIENT)
                    .sized(1.0f, 1.5f)
                    .build("penguin")
    );

    public static final EntityType<ClientSheep> SHEEP = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsheep"),
            EntityType.Builder.of(ClientSheep::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.3f)
                    .build("clientsheep")
    );

    public static final EntityType<ClientCat> CAT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcat"),
            EntityType.Builder.of(ClientCat::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.7f)
                    .build("clientcat")
    );

    public static final EntityType<ClientChicken> CHICKEN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientchicken"),
            EntityType.Builder.of(ClientChicken::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.7f)
                    .build("clientchicken")
    );

    public static final EntityType<ClientCod> COD = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcod"),
            EntityType.Builder.of(ClientCod::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.3f)
                    .build("clientcod")
    );

    public static final EntityType<ClientCow> COW = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcow"),
            EntityType.Builder.of(ClientCow::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)
                    .build("clientcow")
    );

    public static final EntityType<ClientDonkey> DONKEY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientdonkey"),
            EntityType.Builder.of(ClientDonkey::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.5f)
                    .build("clientdonkey")
    );

    public static final EntityType<ClientHorse> HORSE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clienthorse"),
            EntityType.Builder.of(ClientHorse::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.3965f, 1.6f)
                    .build("clienthorse")
    );

    public static final EntityType<ClientMooshroom> MOOSHROOM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientmooshroom"),
            EntityType.Builder.of(ClientMooshroom::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.4f)
                    .build("clientmooshroom")
    );

    public static final EntityType<ClientParrot> PARROT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientparrot"),
            EntityType.Builder.of(ClientParrot::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.5f, 0.9f)
                    .build("clientparrot")
    );

    public static final EntityType<ClientPig> PIG = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpig"),
            EntityType.Builder.of(ClientPig::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.9f)
                    .build("clientpig")
    );

    public static final EntityType<ClientRabbit> RABBIT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientrabbit"),
            EntityType.Builder.of(ClientRabbit::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.5f)
                    .build("clientrabbit")
    );

    public static final EntityType<ClientSalmon> SALMON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsalmon"),
            EntityType.Builder.of(ClientSalmon::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.35f, 0.2f)
                    .build("clientsalmon")
    );

    public static final EntityType<ClientSnowGolem> SNOW_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.of(ClientSnowGolem::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 1.9f)
                    .build("clientsnowgolem")
    );

    public static final EntityType<ClientSquid> SQUID = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsquid"),
            EntityType.Builder.of(ClientSquid::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.8f, -0.8f)
                    .build("clientsquid")
    );

    public static final EntityType<ClientTurtle> TURTLE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientturtle"),
            EntityType.Builder.of(ClientTurtle::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.2f, 0.4f)
                    .build("clientturtle")
    );

    public static final EntityType<ClientVillager> VILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientvillager"),
            EntityType.Builder.of(ClientVillager::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientvillager")
    );

    public static final EntityType<ClientWanderingTrader> WANDERING_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwanderingtrader"),
            EntityType.Builder.of(ClientWanderingTrader::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientwanderingtrader")
    );

    public static final EntityType<ClientCaveSpider> CAVE_SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcavespider"),
            EntityType.Builder.of(ClientCaveSpider::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.5f)
                    .build("clientcavespider")
    );

    public static final EntityType<ClientDolphin> DOLPHIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientdolphin"),
            EntityType.Builder.of(ClientDolphin::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.6f)
                    .build("clientdolphin")
    );

    public static final EntityType<ClientEnderman> ENDERMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientenderman"),
            EntityType.Builder.of(ClientEnderman::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 2.9f)
                    .build("clientenderman")
    );

    public static final EntityType<ClientFox> FOX = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientfox"),
            EntityType.Builder.of(ClientFox::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.7f)
                    .build("clientfox")
    );

    public static final EntityType<ClientIronGolem> IRON_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientirongolem"),
            EntityType.Builder.of(ClientIronGolem::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 2.7f)
                    .build("clientirongolem")
    );

    public static final EntityType<ClientLlama> LLAMA = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientllama"),
            EntityType.Builder.of(ClientLlama::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 1.87f)
                    .build("clientllama")
    );

    public static final EntityType<ClientPanda> PANDA = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpanda"),
            EntityType.Builder.of(ClientPanda::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.3f, 1.25f)
                    .build("clientpanda")
    );

    public static final EntityType<ClientPolarBear> POLAR_BEAR = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpolarbear"),
            EntityType.Builder.of(ClientPolarBear::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 1.4f)
                    .build("clientpolarbear")
    );

    public static final EntityType<ClientPufferFish> PUFFERFISH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpufferfish"),
            EntityType.Builder.of(ClientPufferFish::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.7f, 0.7f)
                    .build("clientpufferfish")
    );

    public static final EntityType<ClientSpider> SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientspider"),
            EntityType.Builder.of(ClientSpider::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.4f, 0.9f)
                    .build("clientspider")
    );

    public static final EntityType<ClientWolf> WOLF = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwolf"),
            EntityType.Builder.of(ClientWolf::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 0.85f)
                    .build("clientwolf")
    );

    public static final EntityType<ClientBlaze> BLAZE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientblaze"),
            EntityType.Builder.of(ClientBlaze::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.8f)
                    .build("clientblaze")
    );

    public static final EntityType<ClientCreeper> CREEPER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientcreeper"),
            EntityType.Builder.of(ClientCreeper::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.7f)
                    .build("clientcreeper")
    );
    public static final EntityType<ClientElderGuardian> ELDER_GUARDIAN_COOKIE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientelderguardian"),
            EntityType.Builder.of(ClientElderGuardian::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.9975f, 1.9975f)
                    .build("clientelderguardian")
    );

    public static final EntityType<ClientEndermite> ENDERMITE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientendermite"),
            EntityType.Builder.of(ClientEndermite::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)
                    .build("clientendermite")
    );

    public static final EntityType<ClientEvoker> EVOKER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientevoker"),
            EntityType.Builder.of(ClientEvoker::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientevoker")
    );

    public static final EntityType<ClientGhast> GHAST = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientghast"),
            EntityType.Builder.of(ClientGhast::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(4f, 4f)
                    .build("clientghast")
    );

    public static final EntityType<ClientGuardian> GUARDIAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientguardian"),
            EntityType.Builder.of(ClientGuardian::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.85f, 0.85f)
                    .build("clientguardian")
    );

    public static final EntityType<ClientMagmaCube> MAGMA_CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientmagmacube"),
            EntityType.Builder.of(ClientMagmaCube::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .build("clientmagmacube")
    );

    public static final EntityType<ClientPhantom> PHANTOM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientphantom"),
            EntityType.Builder.of(ClientPhantom::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.9f, 0.5f)
                    .build("clientphantom")
    );

    public static final EntityType<ClientPillager> PILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientpillager"),
            EntityType.Builder.of(ClientPillager::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientpillager")
    );

    public static final EntityType<ClientRavager> RAVAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientravager"),
            EntityType.Builder.of(ClientRavager::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1.95f, 2.2f)
                    .build("clientravager")
    );

    public static final EntityType<ClientShulker> SHULKER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientshulker"),
            EntityType.Builder.of(ClientShulker::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(1f, 2f)
                    .build("clientshulker")
    );

    public static final EntityType<ClientSilverfish> SILVERFISH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientsilverfish"),
            EntityType.Builder.of(ClientSilverfish::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.3f)
                    .build("clientsilverfish")
    );

    public static final EntityType<ClientSkeleton> SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientskeleton"),
            EntityType.Builder.of(ClientSkeleton::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientskeleton")
    );

    public static final EntityType<ClientSlime> SLIME = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientslime"),
            EntityType.Builder.of(ClientSlime::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(2f, 2f)
                    .build("clientslime")
    );

    public static final EntityType<ClientVex> VEX = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientvex"),
            EntityType.Builder.of(ClientVex::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.4f, 0.8f)
                    .build("clientvex")
    );

    public static final EntityType<ClientVindicator> VINDICATOR = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientvindicator"),
            EntityType.Builder.of(ClientVindicator::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientvindicator")
    );

    public static final EntityType<ClientWitch> WITCH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwitch"),
            EntityType.Builder.of(ClientWitch::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientwitch")
    );

    public static final EntityType<ClientZombie> ZOMBIE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientzombie"),
            EntityType.Builder.of(ClientZombie::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientzombie")
    );

    public static final EntityType<ClientZombieVillager> ZOMBIE_VILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientzombievillager"),
            EntityType.Builder.of(ClientZombieVillager::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientzombievillager")
    );

    public static final EntityType<ClientHusk> HUSK = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clienthusk"),
            EntityType.Builder.of(ClientHusk::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clienthusk")
    );

    public static final EntityType<ClientDrowned> DROWNED = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientdrowned"),
            EntityType.Builder.of(ClientDrowned::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientdrowned")
    );

    public static final EntityType<ClientStray> STRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientstray"),
            EntityType.Builder.of(ClientStray::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientstray")
    );

    public static final EntityType<ClientWitherSkeleton> WITHER_SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.of(ClientWitherSkeleton::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(0.6f, 1.95f)
                    .build("clientwitherskeleton")
    );

    public static final EntityType<ClientEnderDragon> ENDER_DRAGON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientenderdragon"),
            EntityType.Builder.of(ClientEnderDragon::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(16f, 8f)
                    .build("clientenderdragon")
    );

    public static final EntityType<ClientWither> WITHER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "clientwither"),
            EntityType.Builder.of(ClientWither::new, EntityClassification.AMBIENT)
                    .noSummon()
                    .sized(2f, 3f)
                    .build("clientwither")
    );

    public static final EntityType<Head> HEAD = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "head"),
            EntityType.Builder.of(Head::new, EntityClassification.CREATURE)
                    .sized(0.5f, 0.5f)
                    .build("head")
    );

    public static final EntityType<DumboOctopus> DUMBO_OCTOPUS = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "dumbo_octopus"),
            EntityType.Builder.of(DumboOctopus::new, EntityClassification.AMBIENT)
                    .sized(0.5f, 0.5f)
                    .build("dumbo_octopus")
    );

    public static final EntityType<Koi> KOI = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "koi"),
            EntityType.Builder.of(Koi::new, EntityClassification.AMBIENT)
                    .sized(0.6f, 0.6f)
                    .build("koi")
    );

    public static final EntityType<Stingray> STINGRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "stringray"),
            EntityType.Builder.of(Stingray::new, EntityClassification.AMBIENT)
                    .sized(1.0f, 0.4f)
                    .build("stingray")
    );

    public static final EntityType<ClientZombiePigman> ZOMBIE_PIGMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "zombie_pigman"),
            EntityType.Builder.of(ClientZombiePigman::new, EntityClassification.AMBIENT)
                    .sized(0.6f, 1.95f)
                    .build("zombie_pigman")
    );

    /**
     * Registers the entities' attributes. Warns about the call to register not working, but it
     * ends up working fine in-game - likely a mixup in either the Fabric API or IntelliJ.
     */
    @SubscribeEvent
    public static void onInitialize(FMLCommonSetupEvent event) {

        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
}