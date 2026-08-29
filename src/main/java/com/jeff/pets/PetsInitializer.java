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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
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
 * Registers all create the blocks and entities used in this mod, as well as providing the {@link #MOD_ID}.
 */
@Mod(MOD_ID)
@Mod.EventBusSubscriber
public class PetsInitializer {

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

    public static final EntityType<Racoon> RACOON = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "racoon"),
            EntityType.Builder.create(Racoon.class, (world) -> new Racoon(PetsInitializer.RACOON, world))
    );

    public static final EntityType<ClientBat> BAT = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientbat"),
            EntityType.Builder.create(ClientBat.class, (world) -> new ClientBat(PetsInitializer.BAT, world))
                    .disableSummoning()
    );

    public static final EntityType<Duck> DUCK = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "duck"),
            EntityType.Builder.create(Duck.class, (world) -> new Duck(PetsInitializer.DUCK, world))
    );

    public static final EntityType<Penguin> PENGUIN = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "penguin"),
            EntityType.Builder.create(Penguin.class, (world) -> new Penguin(PetsInitializer.PENGUIN, world))
    );

    public static final EntityType<ClientSheep> SHEEP = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientsheep"),
            EntityType.Builder.create(ClientSheep.class, (world) -> new ClientSheep(PetsInitializer.SHEEP, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientCat> CAT = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientcat"),
            EntityType.Builder.create(ClientCat.class, (world) -> new ClientCat(PetsInitializer.CAT, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientChicken> CHICKEN = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientchicken"),
            EntityType.Builder.create(ClientChicken.class, (world) -> new ClientChicken(PetsInitializer.CHICKEN, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientCod> COD = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientcod"),
            EntityType.Builder.create(ClientCod.class, (world) -> new ClientCod(PetsInitializer.COD, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientCow> COW = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientcow"),
            EntityType.Builder.create(ClientCow.class, (world) -> new ClientCow(PetsInitializer.COW, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientDonkey> DONKEY = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientdonkey"),
            EntityType.Builder.create(ClientDonkey.class, (world) -> new ClientDonkey(PetsInitializer.DONKEY, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientHorse> HORSE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clienthorse"),
            EntityType.Builder.create(ClientHorse.class, (world) -> new ClientHorse(PetsInitializer.HORSE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientMooshroom> MOOSHROOM = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientmooshroom"),
            EntityType.Builder.create(ClientMooshroom.class, (world) -> new ClientMooshroom(PetsInitializer.MOOSHROOM, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientParrot> PARROT = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientparrot"),
            EntityType.Builder.create(ClientParrot.class, (world) -> new ClientParrot(PetsInitializer.PARROT, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientPig> PIG = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientpig"),
            EntityType.Builder.create(ClientPig.class, (world) -> new ClientPig(PetsInitializer.PIG, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientRabbit> RABBIT = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientrabbit"),
            EntityType.Builder.create(ClientRabbit.class, (world) -> new ClientRabbit(PetsInitializer.RABBIT, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSalmon> SALMON = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientsalmon"),
            EntityType.Builder.create(ClientSalmon.class, (world) -> new ClientSalmon(PetsInitializer.SALMON, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSnowGolem> SNOW_GOLEM = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientsnowgolem"),
            EntityType.Builder.create(ClientSnowGolem.class, (world) -> new ClientSnowGolem(PetsInitializer.SNOW_GOLEM, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSquid> SQUID = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientsquid"),
            EntityType.Builder.create(ClientSquid.class, (world) -> new ClientSquid(PetsInitializer.SQUID, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientTurtle> TURTLE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientturtle"),
            EntityType.Builder.create(ClientTurtle.class, (world) -> new ClientTurtle(PetsInitializer.TURTLE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientVillager> VILLAGER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientvillager"),
            EntityType.Builder.create(ClientVillager.class, (world) -> new ClientVillager(PetsInitializer.VILLAGER, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientCaveSpider> CAVE_SPIDER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientcavespider"),
            EntityType.Builder.create(ClientCaveSpider.class, (world) -> new ClientCaveSpider(PetsInitializer.CAVE_SPIDER, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientDolphin> DOLPHIN = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientdolphin"),
            EntityType.Builder.create(ClientDolphin.class, (world) -> new ClientDolphin(PetsInitializer.DOLPHIN, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientEnderman> ENDERMAN = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientenderman"),
            EntityType.Builder.create(ClientEnderman.class, (world) -> new ClientEnderman(PetsInitializer.ENDERMAN, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientIronGolem> IRON_GOLEM = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientirongolem"),
            EntityType.Builder.create(ClientIronGolem.class, (world) -> new ClientIronGolem(PetsInitializer.IRON_GOLEM, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientLlama> LLAMA = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientllama"),
            EntityType.Builder.create(ClientLlama.class, (world) -> new ClientLlama(PetsInitializer.LLAMA, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientPolarBear> POLAR_BEAR = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientpolarbear"),
            EntityType.Builder.create(ClientPolarBear.class, (world) -> new ClientPolarBear(PetsInitializer.POLAR_BEAR, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientPufferFish> PUFFERFISH = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientpufferfish"),
            EntityType.Builder.create(ClientPufferFish.class, (world) -> new ClientPufferFish(PetsInitializer.PUFFERFISH, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSpider> SPIDER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientspider"),
            EntityType.Builder.create(ClientSpider.class, (world) -> new ClientSpider(PetsInitializer.SPIDER, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientWolf> WOLF = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientwolf"),
            EntityType.Builder.create(ClientWolf.class, (world) -> new ClientWolf(PetsInitializer.WOLF, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientBlaze> BLAZE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientblaze"),
            EntityType.Builder.create(ClientBlaze.class, (world) -> new ClientBlaze(PetsInitializer.BLAZE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientCreeper> CREEPER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientcreeper"),
            EntityType.Builder.create(ClientCreeper.class, (world) -> new ClientCreeper(PetsInitializer.CREEPER, world))
                    .disableSummoning()
    );
    public static final EntityType<ClientElderGuardian> ELDER_GUARDIAN_COOKIE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientelderguardian"),
            EntityType.Builder.create(ClientElderGuardian.class, (world) -> new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientEndermite> ENDERMITE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientendermite"),
            EntityType.Builder.create(ClientEndermite.class, (world) -> new ClientEndermite(PetsInitializer.ENDERMITE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientEvoker> EVOKER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientevoker"),
            EntityType.Builder.create(ClientEvoker.class, (world) -> new ClientEvoker(PetsInitializer.EVOKER, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientGhast> GHAST = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientghast"),
            EntityType.Builder.create(ClientGhast.class, (world) -> new ClientGhast(PetsInitializer.GHAST, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientGuardian> GUARDIAN = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientguardian"),
            EntityType.Builder.create(ClientGuardian.class, (world) -> new ClientGuardian(PetsInitializer.GUARDIAN, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientMagmaCube> MAGMA_CUBE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientmagmacube"),
            EntityType.Builder.create(ClientMagmaCube.class, (world) -> new ClientMagmaCube(PetsInitializer.MAGMA_CUBE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientPhantom> PHANTOM = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientphantom"),
            EntityType.Builder.create(ClientPhantom.class, (world) -> new ClientPhantom(PetsInitializer.PHANTOM, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientShulker> SHULKER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientshulker"),
            EntityType.Builder.create(ClientShulker.class, (world) -> new ClientShulker(PetsInitializer.SHULKER, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSilverfish> SILVERFISH = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientsilverfish"),
            EntityType.Builder.create(ClientSilverfish.class, (world) -> new ClientSilverfish(PetsInitializer.SILVERFISH, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSkeleton> SKELETON = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientskeleton"),
            EntityType.Builder.create(ClientSkeleton.class, (world) -> new ClientSkeleton(PetsInitializer.SKELETON, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientSlime> SLIME = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientslime"),
            EntityType.Builder.create(ClientSlime.class, (world) -> new ClientSlime(PetsInitializer.SLIME, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientVex> VEX = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientvex"),
            EntityType.Builder.create(ClientVex.class, (world) -> new ClientVex(PetsInitializer.VEX, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientVindicator> VINDICATOR = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientvindicator"),
            EntityType.Builder.create(ClientVindicator.class, (world) -> new ClientVindicator(PetsInitializer.VINDICATOR, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientWitch> WITCH = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientwitch"),
            EntityType.Builder.create(ClientWitch.class, (world) -> new ClientWitch(PetsInitializer.WITCH, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientZombie> ZOMBIE = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientzombie"),
            EntityType.Builder.create(ClientZombie.class, (world) -> new ClientZombie(PetsInitializer.ZOMBIE, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientZombieVillager> ZOMBIE_VILLAGER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientzombievillager"),
            EntityType.Builder.create(ClientZombieVillager.class, (world) -> new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientHusk> HUSK = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clienthusk"),
            EntityType.Builder.create(ClientHusk.class, (world) -> new ClientHusk(PetsInitializer.HUSK, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientDrowned> DROWNED = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientdrowned"),
            EntityType.Builder.create(ClientDrowned.class, (world) -> new ClientDrowned(PetsInitializer.DROWNED, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientStray> STRAY = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientstray"),
            EntityType.Builder.create(ClientStray.class, (world) -> new ClientStray(PetsInitializer.STRAY, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientWitherSkeleton> WITHER_SKELETON = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientwitherskeleton"),
            EntityType.Builder.create(ClientWitherSkeleton.class, (world) -> new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientEnderDragon> ENDER_DRAGON = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientenderdragon"),
            EntityType.Builder.create(ClientEnderDragon.class, (world) -> new ClientEnderDragon(PetsInitializer.ENDER_DRAGON, world))
                    .disableSummoning()
    );

    public static final EntityType<ClientWither> WITHER = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "clientwither"),
            EntityType.Builder.create(ClientWither.class, (world) -> new ClientWither(PetsInitializer.WITHER, world))
                    .disableSummoning()
    );

    public static final EntityType<Head> HEAD = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "head"),
            EntityType.Builder.create(Head.class, (world) -> new Head(PetsInitializer.HEAD, world))
    );

    public static final EntityType<DumboOctopus> DUMBO_OCTOPUS = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "dumbo_octopus"),
            EntityType.Builder.create(DumboOctopus.class, (world) -> new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS, world))
    );

    public static final EntityType<Koi> KOI = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "koi"),
            EntityType.Builder.create(Koi.class, (world) -> new Koi(PetsInitializer.KOI, world))
    );

    public static final EntityType<Stingray> STINGRAY = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "stringray"),
            EntityType.Builder.create(Stingray.class, (world) -> new Stingray(PetsInitializer.STINGRAY, world))
    );

    public static final EntityType<ClientZombiePigman> ZOMBIE_PIGMAN = register(
            IRegistry.field_212629_r,
            new ResourceLocation(MOD_ID, "zombie_pigman"),
            EntityType.Builder.create(ClientZombiePigman.class, (world) -> new ClientZombiePigman(PetsInitializer.ZOMBIE_PIGMAN, world))
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
    
    public static <T extends Entity> EntityType<T> register(IRegistry<EntityType<?>> registry, ResourceLocation location, EntityType.Builder<T> builder) {
        EntityType<T> type = builder.build(location.getPath());
        registry.put(location, type);
        return type;
    }

    static {
        PetsClientInitializer.register();
        MinecraftForge.EVENT_BUS.register(PetsInitializer.class);
        IEventBus bus = MinecraftForge.EVENT_BUS;
        PetsSounds.initialize(bus);
        bus.register(PetsInitializer.class);
        PetsSounds.initialize(bus);
        EntityType<?> ignored = DUCK;
    }
}
