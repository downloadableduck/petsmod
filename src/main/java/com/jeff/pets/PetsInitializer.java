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
import net.minecraft.entity.EntityType;
import net.minecraft.resource.DefaultResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "pets-mod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final EntityType<Racoon> RACOON = EntityType.register(
            "pets-mod:racoon",
            EntityType.EntityBuilder.create(Racoon.class, (world) -> new Racoon(PetsInitializer.RACOON, world)));

    public static final EntityType<ClientBat> BAT = EntityType.register(
            "pets-mod:clientbat",
            EntityType.EntityBuilder.create(ClientBat.class, (world) -> new ClientBat(PetsInitializer.BAT, world)).dontSummon()
    );

    public static final EntityType<Duck> DUCK = EntityType.register(
            "pets-mod:duck",
            EntityType.EntityBuilder.create(Duck.class, (world) -> new Duck(PetsInitializer.DUCK, world))
    );

    public static final EntityType<Penguin> PENGUIN = EntityType.register(
            "pets-mod:penguin",
            EntityType.EntityBuilder.create(Penguin.class, (world) -> new Penguin(PetsInitializer.PENGUIN, world))
    );

    public static final EntityType<ClientSheep> SHEEP = EntityType.register(
            "pets-mod:clientsheep",
            EntityType.EntityBuilder.create(ClientSheep.class, (world) -> new ClientSheep(PetsInitializer.SHEEP, world)).dontSummon()
    );

    public static final EntityType<ClientCat> CAT = EntityType.register(
            "pets-mod:clientcat",
            EntityType.EntityBuilder.create(ClientCat.class, (world) -> new ClientCat(PetsInitializer.CAT, world)).dontSummon()
    );

    public static final EntityType<ClientChicken> CHICKEN = EntityType.register(
            "pets-mod:clientchicken",
            EntityType.EntityBuilder.create(ClientChicken.class, (world) -> new ClientChicken(PetsInitializer.CHICKEN, world)).dontSummon()
    );

    public static final EntityType<ClientCod> COD = EntityType.register(
            "pets-mod:clientcod",
            EntityType.EntityBuilder.create(ClientCod.class, (world) -> new ClientCod(PetsInitializer.COD, world)).dontSummon()
    );

    public static final EntityType<ClientCow> COW = EntityType.register(
            "pets-mod:clientcow",
            EntityType.EntityBuilder.create(ClientCow.class, (world) -> new ClientCow(PetsInitializer.COW, world)).dontSummon()
    );

    public static final EntityType<ClientDonkey> DONKEY = EntityType.register(
            "pets-mod:clientdonkey",
            EntityType.EntityBuilder.create(ClientDonkey.class, (world) -> new ClientDonkey(PetsInitializer.DONKEY, world)).dontSummon()
    );

    public static final EntityType<ClientHorse> HORSE = EntityType.register(
            "pets-mod:clienthorse",
            EntityType.EntityBuilder.create(ClientHorse.class, (world) -> new ClientHorse(PetsInitializer.HORSE, world)).dontSummon()
    );

    public static final EntityType<ClientMooshroom> MOOSHROOM = EntityType.register(
            "pets-mod:clientmooshroom",
            EntityType.EntityBuilder.create(ClientMooshroom.class, (world) -> new ClientMooshroom(PetsInitializer.MOOSHROOM, world)).dontSummon()
    );

    public static final EntityType<ClientParrot> PARROT = EntityType.register(
            "pets-mod:clientparrot",
            EntityType.EntityBuilder.create(ClientParrot.class, (world) -> new ClientParrot(PetsInitializer.PARROT, world)).dontSummon()
    );

    public static final EntityType<ClientPig> PIG = EntityType.register(
            "pets-mod:clientpig",
            EntityType.EntityBuilder.create(ClientPig.class, (world) -> new ClientPig(PetsInitializer.PIG, world)).dontSummon()
    );

    public static final EntityType<ClientRabbit> RABBIT = EntityType.register(
            "pets-mod:clientrabbit",
            EntityType.EntityBuilder.create(ClientRabbit.class, (world) -> new ClientRabbit(PetsInitializer.RABBIT, world)).dontSummon()
    );

    public static final EntityType<ClientSalmon> SALMON = EntityType.register(
            "pets-mod:clientsalmon",
            EntityType.EntityBuilder.create(ClientSalmon.class, (world) -> new ClientSalmon(PetsInitializer.SALMON, world)).dontSummon()
    );

    public static final EntityType<ClientSnowGolem> SNOW_GOLEM = EntityType.register(
            "pets-mod:clientsnowgolem",
            EntityType.EntityBuilder.create(ClientSnowGolem.class, (world) -> new ClientSnowGolem(PetsInitializer.SNOW_GOLEM, world)).dontSummon()
    );

    public static final EntityType<ClientSquid> SQUID = EntityType.register(
            "pets-mod:clientsquid",
            EntityType.EntityBuilder.create(ClientSquid.class, (world) -> new ClientSquid(PetsInitializer.SQUID, world)).dontSummon()
    );

    public static final EntityType<ClientVillager> VILLAGER = EntityType.register(
            "pets-mod:clientvillager",
            EntityType.EntityBuilder.create(ClientVillager.class, (world) -> new ClientVillager(PetsInitializer.VILLAGER, world)).dontSummon()
    );

    public static final EntityType<ClientCaveSpider> CAVE_SPIDER = EntityType.register(
            "pets-mod:clientcavespider",
            EntityType.EntityBuilder.create(ClientCaveSpider.class, (world) -> new ClientCaveSpider(PetsInitializer.CAVE_SPIDER, world)).dontSummon()
    );

    public static final EntityType<ClientDolphin> DOLPHIN = EntityType.register(
            "pets-mod:clientdolphin",
            EntityType.EntityBuilder.create(ClientDolphin.class, (world) -> new ClientDolphin(PetsInitializer.DOLPHIN, world)).dontSummon()
    );

    public static final EntityType<ClientEnderman> ENDERMAN = EntityType.register(
            "pets-mod:clientenderman",
            EntityType.EntityBuilder.create(ClientEnderman.class, (world) -> new ClientEnderman(PetsInitializer.ENDERMAN, world)).dontSummon()
    );

    public static final EntityType<ClientIronGolem> IRON_GOLEM = EntityType.register(
            "pets-mod:clientirongolem",
            EntityType.EntityBuilder.create(ClientIronGolem.class, (world) -> new ClientIronGolem(PetsInitializer.IRON_GOLEM, world)).dontSummon()
    );

    public static final EntityType<ClientLlama> LLAMA = EntityType.register(
            "pets-mod:clientllama",
            EntityType.EntityBuilder.create(ClientLlama.class, (world) -> new ClientLlama(PetsInitializer.LLAMA, world)).dontSummon()
    );
    public static final EntityType<ClientPolarBear> POLAR_BEAR = EntityType.register(
            "pets-mod:clientpolarbear",
            EntityType.EntityBuilder.create(ClientPolarBear.class, (world) -> new ClientPolarBear(PetsInitializer.POLAR_BEAR, world)).dontSummon()
    );

    public static final EntityType<ClientPufferFish> PUFFERFISH = EntityType.register(
            "pets-mod:clientpufferfish",
            EntityType.EntityBuilder.create(ClientPufferFish.class, (world) -> new ClientPufferFish(PetsInitializer.PUFFERFISH, world)).dontSummon()
    );

    public static final EntityType<ClientSpider> SPIDER = EntityType.register(
            "pets-mod:clientspider",
            EntityType.EntityBuilder.create(ClientSpider.class, (world) -> new ClientSpider(PetsInitializer.SPIDER, world)).dontSummon()
    );

    public static final EntityType<ClientWolf> WOLF = EntityType.register(
            "pets-mod:clientwolf",
            EntityType.EntityBuilder.create(ClientWolf.class, (world) -> new ClientWolf(PetsInitializer.WOLF, world)).dontSummon()
    );

    public static final EntityType<ClientBlaze> BLAZE = EntityType.register(
            "pets-mod:clientblaze",
            EntityType.EntityBuilder.create(ClientBlaze.class, (world) -> new ClientBlaze(PetsInitializer.BLAZE, world)).dontSummon()
    );

    public static final EntityType<ClientCreeper> CREEPER = EntityType.register(
            "pets-mod:clientcreeper",
            EntityType.EntityBuilder.create(ClientCreeper.class, (world) -> new ClientCreeper(PetsInitializer.CREEPER, world)).dontSummon()
    );

    public static final EntityType<ClientElderGuardian> ELDER_GUARDIAN_COOKIE = EntityType.register(
            "pets-mod:clientelderguardian",
            EntityType.EntityBuilder.create(ClientElderGuardian.class, (world) -> new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE, world)).dontSummon()
    );

    public static final EntityType<ClientEndermite> ENDERMITE = EntityType.register(
            "pets-mod:clientendermite",
            EntityType.EntityBuilder.create(ClientEndermite.class, (world) -> new ClientEndermite(PetsInitializer.ENDERMITE, world)).dontSummon()
    );

    public static final EntityType<ClientEvoker> EVOKER = EntityType.register(
            "pets-mod:clientevoker",
            EntityType.EntityBuilder.create(ClientEvoker.class, (world) -> new ClientEvoker(PetsInitializer.EVOKER, world)).dontSummon()
    );

    public static final EntityType<ClientGhast> GHAST = EntityType.register(
            "pets-mod:clientghast",
            EntityType.EntityBuilder.create(ClientGhast.class, (world) -> new ClientGhast(PetsInitializer.GHAST, world)).dontSummon()
    );

    public static final EntityType<ClientGuardian> GUARDIAN = EntityType.register(
            "pets-mod:clientguardian",
            EntityType.EntityBuilder.create(ClientGuardian.class, (world) -> new ClientGuardian(PetsInitializer.GUARDIAN, world)).dontSummon()
    );

    public static final EntityType<ClientMagmaCube> MAGMA_CUBE = EntityType.register(
            "pets-mod:clientmagmacube",
            EntityType.EntityBuilder.create(ClientMagmaCube.class, (world) -> new ClientMagmaCube(PetsInitializer.MAGMA_CUBE, world)).dontSummon()
    );

    public static final EntityType<ClientPhantom> PHANTOM = EntityType.register(
            "pets-mod:clientphantom",
            EntityType.EntityBuilder.create(ClientPhantom.class, (world) -> new ClientPhantom(PetsInitializer.PHANTOM, world)).dontSummon()
    );

    public static final EntityType<ClientShulker> SHULKER = EntityType.register(
            "pets-mod:clientshulker",
            EntityType.EntityBuilder.create(ClientShulker.class, (world) -> new ClientShulker(PetsInitializer.SHULKER, world)).dontSummon()
    );

    public static final EntityType<ClientSilverfish> SILVERFISH = EntityType.register(
            "pets-mod:clientsilverfish",
            EntityType.EntityBuilder.create(ClientSilverfish.class, (world) -> new ClientSilverfish(PetsInitializer.SILVERFISH, world)).dontSummon()
    );

    public static final EntityType<ClientSkeleton> SKELETON = EntityType.register(
            "pets-mod:clientskeleton",
            EntityType.EntityBuilder.create(ClientSkeleton.class, (world) -> new ClientSkeleton(PetsInitializer.SKELETON, world)).dontSummon()
    );

    public static final EntityType<ClientSlime> SLIME = EntityType.register(
            "pets-mod:clientslime",
            EntityType.EntityBuilder.create(ClientSlime.class, (world) -> new ClientSlime(PetsInitializer.SLIME, world)).dontSummon()
    );

    public static final EntityType<ClientVex> VEX = EntityType.register(
            "pets-mod:clientvex",
            EntityType.EntityBuilder.create(ClientVex.class, (world) -> new ClientVex(PetsInitializer.VEX, world)).dontSummon()
    );

    public static final EntityType<ClientVindicator> VINDICATOR = EntityType.register(
            "pets-mod:clientvindicator",
            EntityType.EntityBuilder.create(ClientVindicator.class, (world) -> new ClientVindicator(PetsInitializer.VINDICATOR, world)).dontSummon()
    );

    public static final EntityType<ClientWitch> WITCH = EntityType.register(
            "pets-mod:clientwitch",
            EntityType.EntityBuilder.create(ClientWitch.class, (world) -> new ClientWitch(PetsInitializer.WITCH, world)).dontSummon()
    );

    public static final EntityType<ClientZombie> ZOMBIE = EntityType.register(
            "pets-mod:clientzombie",
            EntityType.EntityBuilder.create(ClientZombie.class, (world) -> new ClientZombie(PetsInitializer.ZOMBIE, world)).dontSummon()
    );

    public static final EntityType<ClientZombieVillager> ZOMBIE_VILLAGER = EntityType.register(
            "pets-mod:clientzombievillager",
            EntityType.EntityBuilder.create(ClientZombieVillager.class, (world) -> new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER, world)).dontSummon()
    );

    public static final EntityType<ClientHusk> HUSK = EntityType.register(
            "pets-mod:clienthusk",
            EntityType.EntityBuilder.create(ClientHusk.class, (world) -> new ClientHusk(PetsInitializer.HUSK, world)).dontSummon()
    );

    public static final EntityType<ClientDrowned> DROWNED = EntityType.register(
            "pets-mod:clientdrowned",
            EntityType.EntityBuilder.create(ClientDrowned.class, (world) -> new ClientDrowned(PetsInitializer.DROWNED, world)).dontSummon()
    );

    public static final EntityType<ClientStray> STRAY = EntityType.register(
            "pets-mod:clientstray",
            EntityType.EntityBuilder.create(ClientStray.class, (world) -> new ClientStray(PetsInitializer.STRAY, world)).dontSummon()
    );

    public static final EntityType<ClientWitherSkeleton> WITHER_SKELETON = EntityType.register(
            "pets-mod:clientwitherskeleton",
            EntityType.EntityBuilder.create(ClientWitherSkeleton.class, (world) -> new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON, world)).dontSummon()
    );

    public static final EntityType<ClientEnderDragon> ENDER_DRAGON = EntityType.register(
            "pets-mod:clientenderdragon",
            EntityType.EntityBuilder.create(ClientEnderDragon.class, (world) -> new ClientEnderDragon(PetsInitializer.ENDER_DRAGON, world)).dontSummon()
    );

    public static final EntityType<ClientWither> WITHER = EntityType.register(
            "pets-mod:clientwither",
            EntityType.EntityBuilder.create(ClientWither.class, (world) -> new ClientWither(PetsInitializer.WITHER, world)).dontSummon()
    );

    public static final EntityType<Head> HEAD = EntityType.register(
            "pets-mod:head",
            EntityType.EntityBuilder.create(Head.class, (world) -> new Head(PetsInitializer.HEAD, world))
    );

    public static final EntityType<DumboOctopus> DUMBO_OCTOPUS = EntityType.register(
            "pets-mod:dumbo_octopus",
            EntityType.EntityBuilder.create(DumboOctopus.class, (world) -> new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS, world))
    );

    public static final EntityType<Koi> KOI = EntityType.register(
            "pets-mod:koi",
            EntityType.EntityBuilder.create(Koi.class, (world) -> new Koi(PetsInitializer.KOI, world))
    );

    public static final EntityType<Stingray> STINGRAY = EntityType.register(
            "pets-mod:stringray",
            EntityType.EntityBuilder.create(Stingray.class, (world) -> new Stingray(PetsInitializer.STINGRAY, world))
    );

    public static final EntityType<ClientZombiePigman> ZOMBIE_PIGMAN = EntityType.register(
            "pets-mod:zombie_pigman",
            EntityType.EntityBuilder.create(ClientZombiePigman.class, (world) -> new ClientZombiePigman(PetsInitializer.ZOMBIE_PIGMAN, world))
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