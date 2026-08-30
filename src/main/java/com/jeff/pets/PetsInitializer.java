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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.resource.Identifier;
import net.minecraft.util.registry.IdRegistry;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Registers all of the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
public class PetsInitializer implements ModInitializer {
    public static final String MOD_ID = "pets-mod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final EntityType<Racoon> RACOON = register(
            new Identifier(MOD_ID, "racoon"),
            EntityType.Builder.of(Racoon.class, (world) -> new Racoon(PetsInitializer.RACOON, world))
                    .build("pets-mod:racoon")
    );

    public static final EntityType<ClientBat> BAT = register(
            new Identifier(MOD_ID, "clientbat"),
            EntityType.Builder.of(ClientBat.class, (world) -> new ClientBat(PetsInitializer.BAT, world))
                    .notSummonable()
                    .build("clientbat")
    );

    public static final EntityType<Duck> DUCK = register(
            new Identifier(MOD_ID, "duck"),
            EntityType.Builder.of(Duck.class, (world) -> new Duck(PetsInitializer.DUCK, world))
                    .build("duck")
    );

    public static final EntityType<Penguin> PENGUIN = register(
            new Identifier(MOD_ID, "penguin"),
            EntityType.Builder.of(Penguin.class, (world) -> new Penguin(PetsInitializer.PENGUIN, world))
                    .build("penguin")
    );

    public static final EntityType<ClientSheep> SHEEP = register(
            new Identifier(MOD_ID, "clientsheep"),
            EntityType.Builder.of(ClientSheep.class, (world) -> new ClientSheep(PetsInitializer.SHEEP, world))
                    .notSummonable()
                    .build("clientsheep")
    );

    public static final EntityType<ClientCat> CAT = register(
            new Identifier(MOD_ID, "clientcat"),
            EntityType.Builder.of(ClientCat.class, (world) -> new ClientCat(PetsInitializer.CAT, world))
                    .notSummonable()
                    .build("clientcat")
    );

    public static final EntityType<ClientChicken> CHICKEN = register(
            new Identifier(MOD_ID, "clientchicken"),
            EntityType.Builder.of(ClientChicken.class, (world) -> new ClientChicken(PetsInitializer.CHICKEN, world))
                    .notSummonable()
                    .build("clientchicken")
    );

    public static final EntityType<ClientCod> COD = register(
            new Identifier(MOD_ID, "clientcod"),
            EntityType.Builder.of(ClientCod.class, (world) -> new ClientCod(PetsInitializer.COD, world))
                    .notSummonable()
                    .build("clientcod")
    );

    public static final EntityType<ClientCow> COW = register(
            new Identifier(MOD_ID, "clientcow"),
            EntityType.Builder.of(ClientCow.class, (world) -> new ClientCow(PetsInitializer.COW, world))
                    .notSummonable()
                    .build("clientcow")
    );

    public static final EntityType<ClientDonkey> DONKEY = register(
            new Identifier(MOD_ID, "clientdonkey"),
            EntityType.Builder.of(ClientDonkey.class, (world) -> new ClientDonkey(PetsInitializer.DONKEY, world))
                    .notSummonable()
                    .build("clientdonkey")
    );

    public static final EntityType<ClientHorse> HORSE = register(
            new Identifier(MOD_ID, "clienthorse"),
            EntityType.Builder.of(ClientHorse.class, (world) -> new ClientHorse(PetsInitializer.HORSE, world))
                    .notSummonable()
                    .build("clienthorse")
    );

    public static final EntityType<ClientMooshroom> MOOSHROOM = register(
            new Identifier(MOD_ID, "clientmooshroom"),
            EntityType.Builder.of(ClientMooshroom.class, (world) -> new ClientMooshroom(PetsInitializer.MOOSHROOM, world))
                    .notSummonable()
                    .build("clientmooshroom")
    );

    public static final EntityType<ClientParrot> PARROT = register(
            new Identifier(MOD_ID, "clientparrot"),
            EntityType.Builder.of(ClientParrot.class, (world) -> new ClientParrot(PetsInitializer.PARROT, world))
                    .notSummonable()
                    .build("clientparrot")
    );

    public static final EntityType<ClientPig> PIG = register(
            new Identifier(MOD_ID, "clientpig"),
            EntityType.Builder.of(ClientPig.class, (world) -> new ClientPig(PetsInitializer.PIG, world))
                    .notSummonable()
                    .build("clientpig")
    );

    public static final EntityType<ClientRabbit> RABBIT = register(
            new Identifier(MOD_ID, "clientrabbit"),
            EntityType.Builder.of(ClientRabbit.class, (world) -> new ClientRabbit(PetsInitializer.RABBIT, world))
                    .notSummonable()
                    .build("clientrabbit")
    );

    public static final EntityType<ClientSalmon> SALMON = register(
            new Identifier(MOD_ID, "clientsalmon"),
            EntityType.Builder.of(ClientSalmon.class, (world) -> new ClientSalmon(PetsInitializer.SALMON, world))
                    .notSummonable()
                    .build("clientsalmon")
    );

    public static final EntityType<ClientSnowGolem> SNOW_GOLEM = register(
            new Identifier(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.of(ClientSnowGolem.class, (world) -> new ClientSnowGolem(PetsInitializer.SNOW_GOLEM, world))
                    .notSummonable()
                    .build("clientsnowgolem")
    );

    public static final EntityType<ClientSquid> SQUID = register(
            new Identifier(MOD_ID, "clientsquid"),
            EntityType.Builder.of(ClientSquid.class, (world) -> new ClientSquid(PetsInitializer.SQUID, world))
                    .notSummonable()
                    .build("clientsquid")
    );

    public static final EntityType<ClientTurtle> TURTLE = register(
            new Identifier(MOD_ID, "clientturtle"),
            EntityType.Builder.of(ClientTurtle.class, (world) -> new ClientTurtle(PetsInitializer.TURTLE, world))
                    .notSummonable()
                    .build("clientturtle")
    );

    public static final EntityType<ClientVillager> VILLAGER = register(
            new Identifier(MOD_ID, "clientvillager"),
            EntityType.Builder.of(ClientVillager.class, (world) -> new ClientVillager(PetsInitializer.VILLAGER, world))
                    .notSummonable()
                    .build("clientvillager")
    );

    public static final EntityType<ClientCaveSpider> CAVE_SPIDER = register(
            new Identifier(MOD_ID, "clientcavespider"),
            EntityType.Builder.of(ClientCaveSpider.class, (world) -> new ClientCaveSpider(PetsInitializer.CAVE_SPIDER, world))
                    .notSummonable()
                    .build("clientcavespider")
    );

    public static final EntityType<ClientDolphin> DOLPHIN = register(
            new Identifier(MOD_ID, "clientdolphin"),
            EntityType.Builder.of(ClientDolphin.class, (world) -> new ClientDolphin(PetsInitializer.DOLPHIN, world))
                    .notSummonable()
                    .build("clientdolphin")
    );

    public static final EntityType<ClientEnderman> ENDERMAN = register(
            new Identifier(MOD_ID, "clientenderman"),
            EntityType.Builder.of(ClientEnderman.class, (world) -> new ClientEnderman(PetsInitializer.ENDERMAN, world))
                    .notSummonable()
                    .build("clientenderman")
    );

    public static final EntityType<ClientIronGolem> IRON_GOLEM = register(
            new Identifier(MOD_ID, "clientirongolem"),
            EntityType.Builder.of(ClientIronGolem.class, (world) -> new ClientIronGolem(PetsInitializer.IRON_GOLEM, world))
                    .notSummonable()
                    .build("clientirongolem")
    );

    public static final EntityType<ClientLlama> LLAMA = register(
            new Identifier(MOD_ID, "clientllama"),
            EntityType.Builder.of(ClientLlama.class, (world) -> new ClientLlama(PetsInitializer.LLAMA, world))
                    .notSummonable()
                    .build("clientllama")
    );

    public static final EntityType<ClientPolarBear> POLAR_BEAR = register(
            new Identifier(MOD_ID, "clientpolarbear"),
            EntityType.Builder.of(ClientPolarBear.class, (world) -> new ClientPolarBear(PetsInitializer.POLAR_BEAR, world))
                    .notSummonable()
                    .build("clientpolarbear")
    );

    public static final EntityType<ClientPufferFish> PUFFERFISH = register(
            new Identifier(MOD_ID, "clientpufferfish"),
            EntityType.Builder.of(ClientPufferFish.class, (world) -> new ClientPufferFish(PetsInitializer.PUFFERFISH, world))
                    .notSummonable()
                    .build("clientpufferfish")
    );

    public static final EntityType<ClientSpider> SPIDER = register(
            new Identifier(MOD_ID, "clientspider"),
            EntityType.Builder.of(ClientSpider.class, (world) -> new ClientSpider(PetsInitializer.SPIDER, world))
                    .notSummonable()
                    .build("clientspider")
    );

    public static final EntityType<ClientWolf> WOLF = register(
            new Identifier(MOD_ID, "clientwolf"),
            EntityType.Builder.of(ClientWolf.class, (world) -> new ClientWolf(PetsInitializer.WOLF, world))
                    .notSummonable()
                    .build("clientwolf")
    );

    public static final EntityType<ClientBlaze> BLAZE = register(
            new Identifier(MOD_ID, "clientblaze"),
            EntityType.Builder.of(ClientBlaze.class, (world) -> new ClientBlaze(PetsInitializer.BLAZE, world))
                    .notSummonable()
                    .build("clientblaze")
    );

    public static final EntityType<ClientCreeper> CREEPER = register(
            new Identifier(MOD_ID, "clientcreeper"),
            EntityType.Builder.of(ClientCreeper.class, (world) -> new ClientCreeper(PetsInitializer.CREEPER, world))
                    .notSummonable()
                    .build("clientcreeper")
    );

    public static final EntityType<ClientElderGuardian> ELDER_GUARDIAN_COOKIE = register(
            new Identifier(MOD_ID, "clientelderguardian"),
            EntityType.Builder.of(ClientElderGuardian.class, (world) -> new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE, world))
                    .notSummonable()
                    .build("clientelderguardian")
    );

    public static final EntityType<ClientEndermite> ENDERMITE = register(
            new Identifier(MOD_ID, "clientendermite"),
            EntityType.Builder.of(ClientEndermite.class, (world) -> new ClientEndermite(PetsInitializer.ENDERMITE, world))
                    .notSummonable()
                    .build("clientendermite")
    );

    public static final EntityType<ClientEvoker> EVOKER = register(
            new Identifier(MOD_ID, "clientevoker"),
            EntityType.Builder.of(ClientEvoker.class, (world) -> new ClientEvoker(PetsInitializer.EVOKER, world))
                    .notSummonable()
                    .build("clientevoker")
    );

    public static final EntityType<ClientGhast> GHAST = register(
            new Identifier(MOD_ID, "clientghast"),
            EntityType.Builder.of(ClientGhast.class, (world) -> new ClientGhast(PetsInitializer.GHAST, world))
                    .notSummonable()
                    .build("clientghast")
    );

    public static final EntityType<ClientGuardian> GUARDIAN = register(
            new Identifier(MOD_ID, "clientguardian"),
            EntityType.Builder.of(ClientGuardian.class, (world) -> new ClientGuardian(PetsInitializer.GUARDIAN, world))
                    .notSummonable()
                    .build("clientguardian")
    );

    public static final EntityType<ClientMagmaCube> MAGMA_CUBE = register(
            new Identifier(MOD_ID, "clientmagmacube"),
            EntityType.Builder.of(ClientMagmaCube.class, (world) -> new ClientMagmaCube(PetsInitializer.MAGMA_CUBE, world))
                    .notSummonable()
                    .build("clientmagmacube")
    );

    public static final EntityType<ClientPhantom> PHANTOM = register(
            new Identifier(MOD_ID, "clientphantom"),
            EntityType.Builder.of(ClientPhantom.class, (world) -> new ClientPhantom(PetsInitializer.PHANTOM, world))
                    .notSummonable()
                    .build("clientphantom")
    );

    public static final EntityType<ClientShulker> SHULKER = register(
            new Identifier(MOD_ID, "clientshulker"),
            EntityType.Builder.of(ClientShulker.class, (world) -> new ClientShulker(PetsInitializer.SHULKER, world))
                    .notSummonable()
                    .build("clientshulker")
    );

    public static final EntityType<ClientSilverfish> SILVERFISH = register(
            new Identifier(MOD_ID, "clientsilverfish"),
            EntityType.Builder.of(ClientSilverfish.class, (world) -> new ClientSilverfish(PetsInitializer.SILVERFISH, world))
                    .notSummonable()
                    .build("clientsilverfish")
    );

    public static final EntityType<ClientSkeleton> SKELETON = register(
            new Identifier(MOD_ID, "clientskeleton"),
            EntityType.Builder.of(ClientSkeleton.class, (world) -> new ClientSkeleton(PetsInitializer.SKELETON, world))
                    .notSummonable()
                    .build("clientskeleton")
    );

    public static final EntityType<ClientSlime> SLIME = register(
            new Identifier(MOD_ID, "clientslime"),
            EntityType.Builder.of(ClientSlime.class, (world) -> new ClientSlime(PetsInitializer.SLIME, world))
                    .notSummonable()
                    .build("clientslime")
    );

    public static final EntityType<ClientVex> VEX = register(
            new Identifier(MOD_ID, "clientvex"),
            EntityType.Builder.of(ClientVex.class, (world) -> new ClientVex(PetsInitializer.VEX, world))
                    .notSummonable()
                    .build("clientvex")
    );

    public static final EntityType<ClientVindicator> VINDICATOR = register(
            new Identifier(MOD_ID, "clientvindicator"),
            EntityType.Builder.of(ClientVindicator.class, (world) -> new ClientVindicator(PetsInitializer.VINDICATOR, world))
                    .notSummonable()
                    .build("clientvindicator")
    );

    public static final EntityType<ClientWitch> WITCH = register(
            new Identifier(MOD_ID, "clientwitch"),
            EntityType.Builder.of(ClientWitch.class, (world) -> new ClientWitch(PetsInitializer.WITCH, world))
                    .notSummonable()
                    .build("clientwitch")
    );

    public static final EntityType<ClientZombie> ZOMBIE = register(
            new Identifier(MOD_ID, "clientzombie"),
            EntityType.Builder.of(ClientZombie.class, (world) -> new ClientZombie(PetsInitializer.ZOMBIE, world))
                    .notSummonable()
                    .build("clientzombie")
    );

    public static final EntityType<ClientZombieVillager> ZOMBIE_VILLAGER = register(
            new Identifier(MOD_ID, "clientzombievillager"),
            EntityType.Builder.of(ClientZombieVillager.class, (world) -> new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER, world))
                    .notSummonable()
                    .build("clientzombievillager")
    );

    public static final EntityType<ClientHusk> HUSK = register(
            new Identifier(MOD_ID, "clienthusk"),
            EntityType.Builder.of(ClientHusk.class, (world) -> new ClientHusk(PetsInitializer.HUSK, world))
                    .notSummonable()
                    .build("clienthusk")
    );

    public static final EntityType<ClientDrowned> DROWNED = register(
            new Identifier(MOD_ID, "clientdrowned"),
            EntityType.Builder.of(ClientDrowned.class, (world) -> new ClientDrowned(PetsInitializer.DROWNED, world))
                    .notSummonable()
                    .build("clientdrowned")
    );

    public static final EntityType<ClientStray> STRAY = register(
            new Identifier(MOD_ID, "clientstray"),
            EntityType.Builder.of(ClientStray.class, (world) -> new ClientStray(PetsInitializer.STRAY, world))
                    .notSummonable()
                    .build("clientstray")
    );

    public static final EntityType<ClientWitherSkeleton> WITHER_SKELETON = register(
            new Identifier(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.of(ClientWitherSkeleton.class, (world) -> new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON, world))
                    .notSummonable()
                    .build("clientwitherskeleton")
    );

    public static final EntityType<ClientEnderDragon> ENDER_DRAGON = register(
            new Identifier(MOD_ID, "clientenderdragon"),
            EntityType.Builder.of(ClientEnderDragon.class, (world) -> new ClientEnderDragon(PetsInitializer.ENDER_DRAGON, world))
                    .notSummonable()
                    .build("clientenderdragon")
    );

    public static final EntityType<ClientWither> WITHER = register(
            new Identifier(MOD_ID, "clientwither"),
            EntityType.Builder.of(ClientWither.class, (world) -> new ClientWither(PetsInitializer.WITHER, world))
                    .notSummonable()
                    .build("clientwither")
    );

    public static final EntityType<Head> HEAD = register(
            new Identifier(MOD_ID, "head"),
            EntityType.Builder.of(Head.class, (world) -> new Head(PetsInitializer.HEAD, world))
                    .build("head")
    );

    public static final EntityType<DumboOctopus> DUMBO_OCTOPUS = register(
            new Identifier(MOD_ID, "dumbo_octopus"),
            EntityType.Builder.of(DumboOctopus.class, (world) -> new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS, world))
                    .build("dumbo_octopus")
    );

    public static final EntityType<Koi> KOI = register(
            new Identifier(MOD_ID, "koi"),
            EntityType.Builder.of(Koi.class, (world) -> new Koi(PetsInitializer.KOI, world))
                    .build("koi")
    );

    public static final EntityType<Stingray> STINGRAY = register(
            new Identifier(MOD_ID, "stringray"),
            EntityType.Builder.of(Stingray.class, (world) -> new Stingray(PetsInitializer.STINGRAY, world))
                    .build("stingray")
    );

    public static final EntityType<ClientZombiePigman> ZOMBIE_PIGMAN = register(
            new Identifier(MOD_ID, "zombie_pigman"),
            EntityType.Builder.of(ClientZombiePigman.class, (world) -> new ClientZombiePigman(PetsInitializer.ZOMBIE_PIGMAN, world))
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

    private static <T extends Entity> EntityType<T> register(Identifier id, EntityType<T> type) {
        EntityType.REGISTRY.put(id, type);
        return type;
    }
}