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
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.datafixers.Schemas;
import net.minecraft.datafixers.TypeReferences;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "pets-mod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final EntityType<Racoon> RACOON = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "racoon"),
            EntityType.Builder.create(Racoon::new, EntityCategory.CREATURE)
                    .setSize(1.0f, 1.0f)
                    .build("pets-mod:racoon")
    );

    public static final EntityType<ClientBat> BAT = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientbat"),
            EntityType.Builder.create(ClientBat::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.5f, 0.9f)
                    .build("clientbat")
    );

    public static final EntityType<Duck> DUCK = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "duck"),
            EntityType.Builder.create(Duck::new, EntityCategory.CREATURE)
                    .setSize(0.4f, 0.7f)
                    .build("duck")
    );

    public static final EntityType<Penguin> PENGUIN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "penguin"),
            EntityType.Builder.create(Penguin::new, EntityCategory.AMBIENT)
                    .setSize(1.0f, 1.5f)
                    .build("penguin")
    );

    public static final EntityType<ClientSheep> SHEEP = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientsheep"),
            EntityType.Builder.create(ClientSheep::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 1.3f)
                    .build("clientsheep")
    );

    public static final EntityType<ClientCat> CAT = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientcat"),
            EntityType.Builder.create(ClientCat::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 0.7f)
                    .build("clientcat")
    );

    public static final EntityType<ClientChicken> CHICKEN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientchicken"),
            EntityType.Builder.create(ClientChicken::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.4f, 0.7f)
                    .build("clientchicken")
    );

    public static final EntityType<ClientCod> COD = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientcod"),
            EntityType.Builder.create(ClientCod::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.5f, 0.3f)
                    .build("clientcod")
    );

    public static final EntityType<ClientCow> COW = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientcow"),
            EntityType.Builder.create(ClientCow::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 1.4f)
                    .build("clientcow")
    );

    public static final EntityType<ClientDonkey> DONKEY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientdonkey"),
            EntityType.Builder.create(ClientDonkey::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.3965f, 1.5f)
                    .build("clientdonkey")
    );

    public static final EntityType<ClientHorse> HORSE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clienthorse"),
            EntityType.Builder.create(ClientHorse::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.3965f, 1.6f)
                    .build("clienthorse")
    );

    public static final EntityType<ClientMooshroom> MOOSHROOM = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientmooshroom"),
            EntityType.Builder.create(ClientMooshroom::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 1.4f)
                    .build("clientmooshroom")
    );

    public static final EntityType<ClientParrot> PARROT = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientparrot"),
            EntityType.Builder.create(ClientParrot::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.5f, 0.9f)
                    .build("clientparrot")
    );

    public static final EntityType<ClientPig> PIG = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientpig"),
            EntityType.Builder.create(ClientPig::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 0.9f)
                    .build("clientpig")
    );

    public static final EntityType<ClientRabbit> RABBIT = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientrabbit"),
            EntityType.Builder.create(ClientRabbit::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.4f, 0.5f)
                    .build("clientrabbit")
    );

    public static final EntityType<ClientSalmon> SALMON = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientsalmon"),
            EntityType.Builder.create(ClientSalmon::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.35f, 0.2f)
                    .build("clientsalmon")
    );

    public static final EntityType<ClientSnowGolem> SNOW_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.create(ClientSnowGolem::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.7f, 1.9f)
                    .build("clientsnowgolem")
    );

    public static final EntityType<ClientSquid> SQUID = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientsquid"),
            EntityType.Builder.create(ClientSquid::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.8f, -0.8f)
                    .build("clientsquid")
    );

    public static final EntityType<ClientTurtle> TURTLE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientturtle"),
            EntityType.Builder.create(ClientTurtle::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.2f, 0.4f)
                    .build("clientturtle")
    );

    public static final EntityType<ClientVillager> VILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientvillager"),
            EntityType.Builder.create(ClientVillager::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientvillager")
    );

    public static final EntityType<ClientWanderingTrader> WANDERING_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientwanderingtrader"),
            EntityType.Builder.create(ClientWanderingTrader::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientwanderingtrader")
    );

    public static final EntityType<ClientCaveSpider> CAVE_SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientcavespider"),
            EntityType.Builder.create(ClientCaveSpider::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.7f, 0.5f)
                    .build("clientcavespider")
    );

    public static final EntityType<ClientDolphin> DOLPHIN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientdolphin"),
            EntityType.Builder.create(ClientDolphin::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 0.6f)
                    .build("clientdolphin")
    );

    public static final EntityType<ClientEnderman> ENDERMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientenderman"),
            EntityType.Builder.create(ClientEnderman::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 2.9f)
                    .build("clientenderman")
    );

    public static final EntityType<ClientFox> FOX = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientfox"),
            EntityType.Builder.create(ClientFox::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 0.7f)
                    .build("clientfox")
    );

    public static final EntityType<ClientIronGolem> IRON_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientirongolem"),
            EntityType.Builder.create(ClientIronGolem::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.4f, 2.7f)
                    .build("clientirongolem")
    );

    public static final EntityType<ClientLlama> LLAMA = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientllama"),
            EntityType.Builder.create(ClientLlama::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 1.87f)
                    .build("clientllama")
    );

    public static final EntityType<ClientPanda> PANDA = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientpanda"),
            EntityType.Builder.create(ClientPanda::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.3f, 1.25f)
                    .build("clientpanda")
    );

    public static final EntityType<ClientPolarBear> POLAR_BEAR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientpolarbear"),
            EntityType.Builder.create(ClientPolarBear::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.4f, 1.4f)
                    .build("clientpolarbear")
    );

    public static final EntityType<ClientPufferFish> PUFFERFISH = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientpufferfish"),
            EntityType.Builder.create(ClientPufferFish::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.7f, 0.7f)
                    .build("clientpufferfish")
    );

    public static final EntityType<ClientSpider> SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientspider"),
            EntityType.Builder.create(ClientSpider::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.4f, 0.9f)
                    .build("clientspider")
    );

    public static final EntityType<ClientWolf> WOLF = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientwolf"),
            EntityType.Builder.create(ClientWolf::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 0.85f)
                    .build("clientwolf")
    );

    public static final EntityType<ClientBlaze> BLAZE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientblaze"),
            EntityType.Builder.create(ClientBlaze::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.8f)
                    .build("clientblaze")
    );

    public static final EntityType<ClientCreeper> CREEPER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientcreeper"),
            EntityType.Builder.create(ClientCreeper::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.7f)
                    .build("clientcreeper")
    );
    public static final EntityType<ClientElderGuardian> ELDER_GUARDIAN_COOKIE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientelderguardian"),
            EntityType.Builder.create(ClientElderGuardian::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.9975f, 1.9975f)
                    .build("clientelderguardian")
    );

    public static final EntityType<ClientEndermite> ENDERMITE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientendermite"),
            EntityType.Builder.create(ClientEndermite::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.4f, 0.3f)
                    .build("clientendermite")
    );

    public static final EntityType<ClientEvoker> EVOKER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientevoker"),
            EntityType.Builder.create(ClientEvoker::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientevoker")
    );

    public static final EntityType<ClientGhast> GHAST = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientghast"),
            EntityType.Builder.create(ClientGhast::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(4f, 4f)
                    .build("clientghast")
    );

    public static final EntityType<ClientGuardian> GUARDIAN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientguardian"),
            EntityType.Builder.create(ClientGuardian::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.85f, 0.85f)
                    .build("clientguardian")
    );

    public static final EntityType<ClientMagmaCube> MAGMA_CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientmagmacube"),
            EntityType.Builder.create(ClientMagmaCube::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(2f, 2f)
                    .build("clientmagmacube")
    );

    public static final EntityType<ClientPhantom> PHANTOM = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientphantom"),
            EntityType.Builder.create(ClientPhantom::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.9f, 0.5f)
                    .build("clientphantom")
    );

    public static final EntityType<ClientPillager> PILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientpillager"),
            EntityType.Builder.create(ClientPillager::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientpillager")
    );

    public static final EntityType<ClientRavager> RAVAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientravager"),
            EntityType.Builder.create(ClientRavager::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1.95f, 2.2f)
                    .build("clientravager")
    );

    public static final EntityType<ClientShulker> SHULKER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientshulker"),
            EntityType.Builder.create(ClientShulker::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(1f, 2f)
                    .build("clientshulker")
    );

    public static final EntityType<ClientSilverfish> SILVERFISH = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientsilverfish"),
            EntityType.Builder.create(ClientSilverfish::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.4f, 0.3f)
                    .build("clientsilverfish")
    );

    public static final EntityType<ClientSkeleton> SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientskeleton"),
            EntityType.Builder.create(ClientSkeleton::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientskeleton")
    );

    public static final EntityType<ClientSlime> SLIME = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientslime"),
            EntityType.Builder.create(ClientSlime::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(2f, 2f)
                    .build("clientslime")
    );

    public static final EntityType<ClientVex> VEX = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientvex"),
            EntityType.Builder.create(ClientVex::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.4f, 0.8f)
                    .build("clientvex")
    );

    public static final EntityType<ClientVindicator> VINDICATOR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientvindicator"),
            EntityType.Builder.create(ClientVindicator::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientvindicator")
    );

    public static final EntityType<ClientWitch> WITCH = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientwitch"),
            EntityType.Builder.create(ClientWitch::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientwitch")
    );

    public static final EntityType<ClientZombie> ZOMBIE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientzombie"),
            EntityType.Builder.create(ClientZombie::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientzombie")
    );

    public static final EntityType<ClientZombieVillager> ZOMBIE_VILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientzombievillager"),
            EntityType.Builder.create(ClientZombieVillager::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientzombievillager")
    );

    public static final EntityType<ClientHusk> HUSK = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clienthusk"),
            EntityType.Builder.create(ClientHusk::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clienthusk")
    );

    public static final EntityType<ClientDrowned> DROWNED = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientdrowned"),
            EntityType.Builder.create(ClientDrowned::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientdrowned")
    );

    public static final EntityType<ClientStray> STRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientstray"),
            EntityType.Builder.create(ClientStray::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientstray")
    );

    public static final EntityType<ClientWitherSkeleton> WITHER_SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.create(ClientWitherSkeleton::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(0.6f, 1.95f)
                    .build("clientwitherskeleton")
    );

    public static final EntityType<ClientEnderDragon> ENDER_DRAGON = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientenderdragon"),
            EntityType.Builder.create(ClientEnderDragon::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(16f, 8f)
                    .build("clientenderdragon")
    );

    public static final EntityType<ClientWither> WITHER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "clientwither"),
            EntityType.Builder.create(ClientWither::new, EntityCategory.AMBIENT)
                    .disableSummon()
                    .setSize(2f, 3f)
                    .build("clientwither")
    );

    public static final EntityType<Head> HEAD = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "head"),
            EntityType.Builder.create(Head::new, EntityCategory.CREATURE)
                    .setSize(0.5f, 0.5f)
                    .build("head")
    );

    public static final EntityType<DumboOctopus> DUMBO_OCTOPUS = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "dumbo_octopus"),
            EntityType.Builder.create(DumboOctopus::new, EntityCategory.AMBIENT)
                    .setSize(0.5f, 0.5f)
                    .build("dumbo_octopus")
    );

    public static final EntityType<Koi> KOI = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "koi"),
            EntityType.Builder.create(Koi::new, EntityCategory.AMBIENT)
                    .setSize(0.6f, 0.6f)
                    .build("koi")
    );

    public static final EntityType<Stingray> STINGRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "stringray"),
            EntityType.Builder.create(Stingray::new, EntityCategory.AMBIENT)
                    .setSize(1.0f, 0.4f)
                    .build("stingray")
    );

    public static final EntityType<ClientZombiePigman> ZOMBIE_PIGMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "zombie_pigman"),
            EntityType.Builder.create(ClientZombiePigman::new, EntityCategory.AMBIENT)
                    .setSize(0.6f, 1.95f)
                    .build("zombie_pigman")
    );

    /**
     * Registers the entities' attributes. Warns about the call to register not working, but it
     * ends up working fine in-game - likely a mixup in either the Fabric API or IntelliJ.
     */
    @Override
    public void onInitialize() {


        PetsSounds.initialize();


        //DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
}