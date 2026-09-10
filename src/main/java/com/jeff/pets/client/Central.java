/**
 * so this file got a little messed up and I had to restore it from an earlier save, but that was
 * inside of a JAR, so this was re-created from the de-compiled bytecode, which is why it is
 * a little wierd.
 */

package com.jeff.pets.client;

import com.jeff.pets.Agent;
import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.mixin.client.ChatAccessor;
import com.jeff.pets.client.network.NetworkManager;
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
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.RootCommandNode;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.Panorama;
import net.minecraft.client.renderer.state.gui.PanoramaRenderState;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.jeff.pets.PetsInitializer.LOGGER;
import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * This class is the "central" of the logic that despawns, spawns, and swaps out pets, as well as creating all of the commands.
 * It contains important logic and methods to manage the pets, additionally
 * implementing {@link ClientModInitializer}
 * to run a large amount of logic in the {@link #onInitializeClient()} method.
 * <p>
 *
 * @see com.jeff.pets.Utils
 */
@SuppressWarnings("unchecked")
public class Central {

    public static final List<Entity> summonedEntity = new ArrayList();
    public static final CopyOnWriteArrayList<String> currentSuggestions = new CopyOnWriteArrayList()
            ;
    public static final List<String> BEE_SKINS = List.of("happy", "angry");
    public static final List<String> FOX_SKINS = List.of("red", "snow");
    public static final List<String> LLAMA_SKINS = List.of("brown", "creamy", "gray", "white");
    public static final List<String> NAUTILUS_SKINS = List.of("nautilus", "zombie", "coral zombie");
    public static final List<String> PANDA_SKINS = List.of("normal", "lazy", "agressive", "worried", "playful", "weak", "brown");
    public static final List<String> PIGLIN_SKINS = List.of("piglin", "zombified", "brute");
    public static final List<String> WOLF_SKINS = List.of("pale", "ashen", "black", "chestnut", "rusty", "snowy", "spotted", "striped", "woods");
    public static final List<String> PETS_LIST = new ArrayList<>();
    public static final SuggestionProvider PETS = (context, builder) ->
            SharedSuggestionProvider.suggest(List.of("allay", "angry ghast", "armadillo",
                    "axolotl", "bat", "batato", "bee", "blaze", "bogged",
                    "breeze", "camel", "cat", "cave spider", "chicken",
                    "cod",  "copper golem", "cow",
                    "creaking", "creeper", "diamond chicken",
                    "dolphin", "donkey", "drowned", "duck", "dumbo octopus",
                    "elder guardian", "ender dragon", "enderman", "endermite", "evoker",
                    "fox",  "frog",
                    "ghast", "goat", "guardian",
                    "happy ghast", "head", "hoglin",  "horse",
                    "husk",  "iron golem",
                    "koi", "llama",
                    "love golem", "magma cube", "mega spud",
                    "moon cow", "mooshroom",
                    "nautilus", "nerd creeper",
                    "panda", "parched", "parrot",  "penguin", "phantom",
                    "pig", "piglin", "pillager",
                    "pink wither", "plaguewhale slab", "poisonous potato zombie", "polar bear",
                    "potato husk", "pufferfish", "rabbit",
                    "racoon",
                    "ravager",
                    "ray tracing",
                    "redstone bug",
                    "salmon",
                    "sheep",
                    "shulker",
                    "silverfish", "skeleton", "slime", "smiling creeper", "sniffer", "snow golem",
                    "spider", "squid", "stingray",  "stray", "strider",  "tadpole", "toxifin slab",
                    "traitor", "turtle",
                    "vex", "villager", "vindicator", "wandering trader", "warden", "witch", "wither",
                    "wither skeleton", "wolf", "zombie", "zombie villager", "sulfur cube"), builder);
    private static final SuggestionProvider ON_OFF = (context, builder) -> SharedSuggestionProvider.suggest(new String[]{"off", "on"}, builder);
    private static final List<String> DUCK_SKINS = List.of("mallard", "pekin", "rubber", "bronze");
    private static final List<String> CAT_SKINS = List.of("black", "tuxedo", "british shorthair", "calico", "jellie", "ocelot", "persian", "ragdoll", "red", "siamese", "tabby", "white");
    private static final List<String> AXOLOTL_SKINS = List.of("pink", "brown", "gold", "cyan", "blue");
    private static final List<String> CAMEL_SKINS = List.of("camel", "husk");
    private static final List<String> TEMPERATE_COLD_WARM = List.of("temperate", "cold", "warm");
    private static final List<String> COPPER_GOLEM_SKINS = List.of("unoxidized", "exposed", "weathered", "oxidized");
    private static final List<String> HORSE_SKINS = List.of("white", "creamy", "chestnut", "brown", "black", "gray", "dark_brown", "zombie", "skeleton");
    private static final List<String> PARROT_SKINS = List.of("red", "blue", "green", "cyan", "gray");
    private static final List<String> RABBIT_SKINS = List.of("brown", "white", "black", "splotched", "gold", "salt", "killer", "toast");
    private static final List<String> SHEEP_SKINS = List.of("white", "orange", "magenta", "light blue", "yellow", "lime", "pink", "gray", "light gray", "cyan", "purple", "blue", "brown", "green", "red", "black");
    private static final List<String> SNOW_GOLEM_KINS = List.of("pumpkin on", "pumpkin off");
    private static final List<String> SQUID_SKINS = List.of("squid", "glow squid");
    private static final List<String> STRIDER_SKINS = List.of("warm", "cold");
    private static final List<String> VILLAGER_SKINS = List.of("farmer", "fisherman", "shepherd", "fletcher", "cleric", "weaponsmith", "armorer", "toolsmith", "librarian", "cartographer", "leatherworker", "butcher", "mason", "nitwit", "unemployed");
    private static final List<String> HOGLIN_SKINS = List.of("hoglin", "zoglin");
    private static final List<String> SLIME_LIKE_SKINS = List.of("small", "medium", "large");
    private static final List<String> RACOON_SKINS = List.of("normal", "albino");
    private static final List<String> CREEPER_SKINS = List.of("normal", "charged");
    private static final List<String> SHULKER_SKINS = List.of("normal", "black", "brown", "cyan", "gray", "green", "light blue", "light gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
    private static final List<String> WITHER_SKINS = List.of("normal", "invulnerable");
    private static final List<String> HEAD_SKINS = List.of("Use any player's name here.");
    private static final List<String> TRAITOR_SKINS = List.of("desert", "jungle", "plains", "savanna", "snowy", "swamp", "taiga");
    private static final List<String> DUMBO_OCTOPUS_SKINS = List.of("yellow", "red", "blue", "green", "orange", "pink");
    private static final List<String> EMPTY_LIST = List.of();
    public static PetsConfig CONFIG = AutoConfig.register(PetsConfig.class, GsonConfigSerializer::new).getConfig();;
    public static int petSkin;
    public static Duck duck;
    public static Racoon racoon;
    public static Penguin penguin;
    public static ClientSheep sheep;
    public static ClientCat cat;
    public static ClientAllay allay;
    public static ClientArmadillo armadillo;
    public static ClientAxolotl axolotl;
    public static ClientBat bat;
    public static ClientCamel camel;
    public static ClientChicken chicken;
    public static ClientCod cod;
    public static ClientCopperGolem copperGolem;
    public static ClientCow cow;
    public static ClientDonkey donkey;
    public static ClientFrog frog;
    public static ClientHorse horse;
    public static ClientMooshroom mooshroom;
    public static ClientParrot parrot;
    public static ClientHappyGhast happyGhast;
    public static ClientPig pig;
    public static ClientRabbit rabbit;
    public static ClientSalmon salmon;
    public static ClientSniffer sniffer;
    public static ClientSnowGolem snowGolem;
    public static ClientSquid squid;
    public static ClientStrider strider;
    public static ClientTadpole tadpole;
    public static ClientTurtle turtle;
    public static ClientVillager villager;
    public static ClientWanderingTrader wanderingTrader;
    public static ClientBee bee;
    public static ClientCaveSpider caveSpider;
    public static ClientDolphin dolphin;
    public static ClientEnderman enderman;
    public static ClientFox fox;
    public static ClientGoat goat;
    public static ClientIronGolem ironGolem;
    public static ClientLlama llama;
    public static ClientNautilus nautilus;
    public static ClientPanda panda;
    public static ClientPiglin piglin;
    public static ClientPolarBear polarBear;
    public static ClientPufferFish pufferFish;
    public static ClientSpider spider;
    public static ClientWolf wolf;
    public static ClientBlaze blaze;
    public static ClientBreeze breeze;
    public static ClientCreaking creaking;
    public static ClientCreeper creeper;
    public static ClientElderGuardian elderGuardian;
    public static ClientEndermite endermite;
    public static ClientEvoker evoker;
    public static ClientGhast ghast;
    public static ClientGuardian guardian;
    public static ClientHoglin hoglin;
    public static ClientMagmaCube magmaCube;
    public static ClientPhantom phantom;
    public static ClientPillager pillager;
    public static ClientRavager ravager;
    public static ClientShulker shulker;
    public static ClientSilverfish silverfish;
    public static ClientSkeleton skeleton;
    public static ClientSlime slime;
    public static ClientVex vex;
    public static ClientVindicator vindicator;
    public static ClientWarden warden;
    public static ClientWitch witch;
    public static ClientZombie zombie;
    public static ClientZombieVillager zombieVillager;
    public static ClientHusk husk;
    public static ClientDrowned drowned;
    public static ClientBogged bogged;
    public static ClientParched parched;
    public static ClientStray stray;
    public static ClientWitherSkeleton witherSkeleton;
    public static ClientEnderDragon enderDragon;
    public static ClientWither wither;
    public static AngryGhast angryGhast;
    public static Batato batato;
    public static DiamondChicken diamondChicken;
    public static LoveGolem loveGolem;
    public static MegaSpud megaSpud;
    public static MoonCow moonCow;
    public static NerdCreeper nerdCreeper;
    public static PinkWither pinkWither;
    public static PlaguewhaleSlab plaguewhaleSlab;
    public static PoisonousPotatoZombie poisonousPotatoZombie;
    public static RayTracing rayTracing;
    public static RedstoneBug redstoneBug;
    public static SmilingCreeper smilingCreeper;
    public static ToxifinSlab toxifinSlab;
    public static PotatoHusk potatoHusk;
    public static Head head;
    public static Traitor traitor;
    public static DumboOctopus dumboOctopus;
    public static Koi koi;
    public static Stingray stingray;
    public static ClientSulfurCube sulfurCube;

    private final SuggestionProvider SKINS = (context, builder) -> {
        String remaining = builder.getRemainingLowerCase();

        for (String s : currentSuggestions) {
            if (s.toLowerCase().startsWith(remaining)) {
                builder.suggest(s);
            }
        }

        return builder.buildFuture();
    };
    int i = 1;

    /**
     * Despawns the entity to ensure that only one is present at a time. For the helper method used,
     * see {@link Utils#despawnEntity}
     */
    public static void despawnPet() {
        Utils.despawnEntity(penguin);
        Utils.despawnEntity(racoon);
        Utils.despawnEntity(duck);
        Utils.despawnEntity(sheep);
        Utils.despawnEntity(cat);
        Utils.despawnEntity(allay);
        Utils.despawnEntity(happyGhast);
        Utils.despawnEntity(armadillo);
        Utils.despawnEntity(axolotl);
        Utils.despawnEntity(bat);
        Utils.despawnEntity(camel);
        Utils.despawnEntity(chicken);
        Utils.despawnEntity(cod);
        Utils.despawnEntity(copperGolem);
        Utils.despawnEntity(cow);
        Utils.despawnEntity(donkey);
        Utils.despawnEntity(frog);
        Utils.despawnEntity(horse);
        Utils.despawnEntity(mooshroom);
        Utils.despawnEntity(parrot);
        Utils.despawnEntity(pig);
        Utils.despawnEntity(rabbit);
        Utils.despawnEntity(salmon);
        Utils.despawnEntity(sniffer);
        Utils.despawnEntity(snowGolem);
        Utils.despawnEntity(squid);
        Utils.despawnEntity(strider);
        Utils.despawnEntity(tadpole);
        Utils.despawnEntity(turtle);
        Utils.despawnEntity(villager);
        Utils.despawnEntity(wanderingTrader);
        Utils.despawnEntity(bee);
        Utils.despawnEntity(caveSpider);
        Utils.despawnEntity(dolphin);
        Utils.despawnEntity(enderman);
        Utils.despawnEntity(fox);
        Utils.despawnEntity(goat);
        Utils.despawnEntity(ironGolem);
        Utils.despawnEntity(llama);
        Utils.despawnEntity(nautilus);
        Utils.despawnEntity(panda);
        Utils.despawnEntity(piglin);
        Utils.despawnEntity(polarBear);
        Utils.despawnEntity(pufferFish);
        Utils.despawnEntity(spider);
        Utils.despawnEntity(wolf);
        Utils.despawnEntity(blaze);
        Utils.despawnEntity(breeze);
        Utils.despawnEntity(creaking);
        Utils.despawnEntity(creeper);
        Utils.despawnEntity(elderGuardian);
        Utils.despawnEntity(endermite);
        Utils.despawnEntity(evoker);
        Utils.despawnEntity(ghast);
        Utils.despawnEntity(guardian);
        Utils.despawnEntity(hoglin);
        Utils.despawnEntity(magmaCube);
        Utils.despawnEntity(phantom);
        Utils.despawnEntity(pillager);
        Utils.despawnEntity(ravager);
        Utils.despawnEntity(shulker);
        Utils.despawnEntity(silverfish);
        Utils.despawnEntity(skeleton);
        Utils.despawnEntity(slime);
        Utils.despawnEntity(vex);
        Utils.despawnEntity(vindicator);
        Utils.despawnEntity(warden);
        Utils.despawnEntity(witch);
        Utils.despawnEntity(zombie);
        Utils.despawnEntity(zombieVillager);
        Utils.despawnEntity(husk);
        Utils.despawnEntity(drowned);
        Utils.despawnEntity(bogged);
        Utils.despawnEntity(parched);
        Utils.despawnEntity(stray);
        Utils.despawnEntity(witherSkeleton);
        Utils.despawnEntity(enderDragon);
        Utils.despawnEntity(wither);
        Utils.despawnEntity(angryGhast);
        Utils.despawnEntity(batato);
        Utils.despawnEntity(diamondChicken);
        Utils.despawnEntity(loveGolem);
        Utils.despawnEntity(megaSpud);
        Utils.despawnEntity(moonCow);
        Utils.despawnEntity(nerdCreeper);
        Utils.despawnEntity(pinkWither);
        Utils.despawnEntity(plaguewhaleSlab);
        Utils.despawnEntity(poisonousPotatoZombie);
        Utils.despawnEntity(rayTracing);
        Utils.despawnEntity(redstoneBug);
        Utils.despawnEntity(smilingCreeper);
        Utils.despawnEntity(toxifinSlab);
        Utils.despawnEntity(potatoHusk);
        Utils.despawnEntity(head);
        Utils.despawnEntity(traitor);
        Utils.despawnEntity(dumboOctopus);
        Utils.despawnEntity(koi);
        Utils.despawnEntity(stingray);
        Utils.despawnEntity(sulfurCube);
    }

    /**
     * Controls the base logic for how and when to summon the pets. For the helper methods used,
     * see {@link Utils#summonPet}
     */
    public static void summonPet() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;
        duck = new Duck(PetsInitializer.DUCK, world);
        racoon = new Racoon(PetsInitializer.RACOON, world);
        penguin = new Penguin(PetsInitializer.PENGUIN, world);
        sheep = new ClientSheep(PetsInitializer.SHEEP, world);
        cat = new ClientCat(PetsInitializer.CAT, world);
        allay = new ClientAllay(PetsInitializer.ALLAY, world);
        armadillo = new ClientArmadillo(PetsInitializer.ARMADILLO, world);
        axolotl = new ClientAxolotl(PetsInitializer.AXOLOTL, world);
        bat = new ClientBat(PetsInitializer.BAT, world);
        camel = new ClientCamel(PetsInitializer.CAMEL, world);
        chicken = new ClientChicken(PetsInitializer.CHICKEN, world);
        cod = new ClientCod(PetsInitializer.COD, world);
        copperGolem = new ClientCopperGolem(PetsInitializer.COPPER_GOLEM, world);
        cow = new ClientCow(PetsInitializer.COW, world);
        donkey = new ClientDonkey(PetsInitializer.DONKEY, world);
        frog = new ClientFrog(PetsInitializer.FROG, world);
        horse = new ClientHorse(PetsInitializer.HORSE, world);
        mooshroom = new ClientMooshroom(PetsInitializer.MOOSHROOM, world);
        parrot = new ClientParrot(PetsInitializer.PARROT, world);
        pig = new ClientPig(PetsInitializer.PIG, world);
        rabbit = new ClientRabbit(PetsInitializer.RABBIT, world);
        salmon = new ClientSalmon(PetsInitializer.SALMON, world);
        sniffer = new ClientSniffer(PetsInitializer.SNIFFER, world);
        snowGolem = new ClientSnowGolem(PetsInitializer.SNOW_GOLEM, world);
        squid = new ClientSquid(PetsInitializer.SQUID, world);
        strider = new ClientStrider(PetsInitializer.STRIDER, world);
        tadpole = new ClientTadpole(PetsInitializer.TADPOLE, world);
        turtle = new ClientTurtle(PetsInitializer.TURTLE, world);
        villager = new ClientVillager(PetsInitializer.VILLAGER, world);
        wanderingTrader = new ClientWanderingTrader(PetsInitializer.WANDERING_TRADER, world);
        bee = new ClientBee(PetsInitializer.BEE, world);
        caveSpider = new ClientCaveSpider(PetsInitializer.CAVE_SPIDER, world);
        dolphin = new ClientDolphin(PetsInitializer.DOLPHIN, world);
        enderman = new ClientEnderman(PetsInitializer.ENDERMAN, world);
        fox = new ClientFox(PetsInitializer.FOX, world);
        goat = new ClientGoat(PetsInitializer.GOAT, world);
        ironGolem = new ClientIronGolem(PetsInitializer.IRON_GOLEM, world);
        llama = new ClientLlama(PetsInitializer.LLAMA, world);
        nautilus = new ClientNautilus(PetsInitializer.NAUTILUS, world);
        panda = new ClientPanda(PetsInitializer.PANDA, world);
        piglin = new ClientPiglin(PetsInitializer.PIGLIN, world);
        polarBear = new ClientPolarBear(PetsInitializer.POLAR_BEAR, world);
        pufferFish = new ClientPufferFish(PetsInitializer.PUFFERFISH, world);
        spider = new ClientSpider(PetsInitializer.SPIDER, world);
        wolf = new ClientWolf(PetsInitializer.WOLF, world);
        blaze = new ClientBlaze(PetsInitializer.BLAZE, world);
        breeze = new ClientBreeze(PetsInitializer.BREEZE, world);
        creaking = new ClientCreaking(PetsInitializer.CREAKING, world);
        creeper = new ClientCreeper(PetsInitializer.CREEPER, world);
        elderGuardian = new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE, world);
        endermite = new ClientEndermite(PetsInitializer.ENDERMITE, world);
        evoker = new ClientEvoker(PetsInitializer.EVOKER, world);
        ghast = new ClientGhast(PetsInitializer.GHAST, world);
        happyGhast = new ClientHappyGhast(PetsInitializer.HAPPY_GHAST, world);
        guardian = new ClientGuardian(PetsInitializer.GUARDIAN, world);
        hoglin = new ClientHoglin(PetsInitializer.HOGLIN, world);
        magmaCube = new ClientMagmaCube(PetsInitializer.MAGMA_CUBE, world);
        phantom = new ClientPhantom(PetsInitializer.PHANTOM, world);
        pillager = new ClientPillager(PetsInitializer.PILLAGER, world);
        ravager = new ClientRavager(PetsInitializer.RAVAGER, world);
        shulker = new ClientShulker(PetsInitializer.SHULKER, world);
        silverfish = new ClientSilverfish(PetsInitializer.SILVERFISH, world);
        skeleton = new ClientSkeleton(PetsInitializer.SKELETON, world);
        slime = new ClientSlime(PetsInitializer.SLIME, world);
        vex = new ClientVex(PetsInitializer.VEX, world);
        vindicator = new ClientVindicator(PetsInitializer.VINDICATOR, world);
        warden = new ClientWarden(PetsInitializer.WARDEN, world);
        witch = new ClientWitch(PetsInitializer.WITCH, world);
        zombie = new ClientZombie(PetsInitializer.ZOMBIE, world);
        zombieVillager = new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER, world);
        husk = new ClientHusk(PetsInitializer.HUSK, world);
        drowned = new ClientDrowned(PetsInitializer.DROWNED, world);
        bogged = new ClientBogged(PetsInitializer.BOGGED, world);
        parched = new ClientParched(PetsInitializer.PARCHED, world);
        stray = new ClientStray(PetsInitializer.STRAY, world);
        witherSkeleton = new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON, world);
        enderDragon = new ClientEnderDragon(PetsInitializer.ENDER_DRAGON, world);
        wither = new ClientWither(PetsInitializer.WITHER, world);
        angryGhast = new AngryGhast(PetsInitializer.ANGRY_GHAST, world);
        batato = new Batato(PetsInitializer.BATATO, world);
        diamondChicken = new DiamondChicken(PetsInitializer.DIAMOND_CHICKEN, world);
        loveGolem = new LoveGolem(PetsInitializer.LOVE_GOLEM, world);
        megaSpud = new MegaSpud(PetsInitializer.MEGA_SPUD, world);
        moonCow = new MoonCow(PetsInitializer.MOON_COW, world);
        nerdCreeper = new NerdCreeper(PetsInitializer.NERD_CREEPER, world);
        pinkWither = new PinkWither(PetsInitializer.PINK_WITHER, world);
        plaguewhaleSlab = new PlaguewhaleSlab(PetsInitializer.PLAGUEWHALE_SLAB, world);
        poisonousPotatoZombie = new PoisonousPotatoZombie(PetsInitializer.POISONOUS_POTATO_ZOMBIE, world);
        rayTracing = new RayTracing(PetsInitializer.RAY_TRACING, world);
        redstoneBug = new RedstoneBug(PetsInitializer.REDSTONE_BUG, world);
        smilingCreeper = new SmilingCreeper(PetsInitializer.SMILING_CREEPER, world);
        toxifinSlab = new ToxifinSlab(PetsInitializer.TOXIFIN_SLAB, world);
        potatoHusk = new PotatoHusk(PetsInitializer.POTATO_HUSK, world);
        head = new Head(PetsInitializer.HEAD, world);
        traitor = new Traitor(PetsInitializer.TRAITOR, world);
        dumboOctopus = new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS, world);
        koi = new Koi(PetsInitializer.KOI, world);
        stingray = new Stingray(PetsInitializer.STINGRAY, world);
        sulfurCube = new ClientSulfurCube(PetsInitializer.SULFUR_CUBE, world);

        if (world != null) {
            if (Objects.equals(CONFIG.activePet, "duck")) {
                Utils.summonPet(duck, CONFIG.duckName);
            } else if (Objects.equals(CONFIG.activePet, "racoon")) {
                Utils.summonPet(racoon, CONFIG.racoonName);
            } else if (Objects.equals(CONFIG.activePet, "penguin")) {
                Utils.summonPet(penguin, CONFIG.penguinName);
            } else if (Objects.equals(CONFIG.activePet, "sheep")) {
                Utils.summonPet(sheep, CONFIG.sheepName);
            } else if (Objects.equals(CONFIG.activePet, "cat")) {
                Utils.summonPet(cat, CONFIG.catName);
            } else if (Objects.equals(CONFIG.activePet, "allay")) {
                Utils.summonPet(allay, CONFIG.allayName);
            } else if (Objects.equals(CONFIG.activePet, "armadillo")) {
                Utils.summonPet(armadillo, CONFIG.armadilloName);
            } else if (Objects.equals(CONFIG.activePet, "axolotl")) {
                Utils.summonPet(axolotl, CONFIG.axolotlName);
            } else if (Objects.equals(CONFIG.activePet, "bat")) {
                Utils.summonPet(bat, CONFIG.batName);
            } else if (Objects.equals(CONFIG.activePet, "camel")) {
                Utils.summonPet(camel, CONFIG.camelName);
            } else if (Objects.equals(CONFIG.activePet, "chicken")) {
                Utils.summonPet(chicken, CONFIG.chickenName);
            } else if (Objects.equals(CONFIG.activePet, "cod")) {
                Utils.summonPet(cod, CONFIG.codName);
            } else if (Objects.equals(CONFIG.activePet, "copper_golem")) {
                Utils.summonPet(copperGolem, CONFIG.copperGolemName);
            } else if (Objects.equals(CONFIG.activePet, "cow")) {
                Utils.summonPet(cow, CONFIG.cowName);
            } else if (Objects.equals(CONFIG.activePet, "donkey")) {
                Utils.summonPet(donkey, CONFIG.donkeyName);
            } else if (Objects.equals(CONFIG.activePet, "frog")) {
                Utils.summonPet(frog, CONFIG.frogName);
            } else if (Objects.equals(CONFIG.activePet, "horse")) {
                Utils.summonPet(horse, CONFIG.horseName);
            } else if (Objects.equals(CONFIG.activePet, "mooshroom")) {
                Utils.summonPet(mooshroom, CONFIG.mooshroomName);
            } else if (Objects.equals(CONFIG.activePet, "parrot")) {
                Utils.summonPet(parrot, CONFIG.parrotName);
            } else if (Objects.equals(CONFIG.activePet, "pig")) {
                Utils.summonPet(pig, CONFIG.pigName);
            } else if (Objects.equals(CONFIG.activePet, "rabbit")) {
                Utils.summonPet(rabbit, CONFIG.rabbitName);
            } else if (Objects.equals(CONFIG.activePet, "salmon")) {
                Utils.summonPet(salmon, CONFIG.salmonName);
            } else if (Objects.equals(CONFIG.activePet, "sniffer")) {
                Utils.summonPet(sniffer, CONFIG.snifferName);
            } else if (Objects.equals(CONFIG.activePet, "snow_golem")) {
                Utils.summonPet(snowGolem, CONFIG.snowGolemName);
            } else if (Objects.equals(CONFIG.activePet, "squid")) {
                Utils.summonPet(squid, CONFIG.squidName);
            } else if (Objects.equals(CONFIG.activePet, "strider")) {
                Utils.summonPet(strider, CONFIG.striderName);
            } else if (Objects.equals(CONFIG.activePet, "tadpole")) {
                Utils.summonPet(tadpole, CONFIG.tadpoleName);
            } else if (Objects.equals(CONFIG.activePet, "turtle")) {
                Utils.summonPet(turtle, CONFIG.turtleName);
            } else if (Objects.equals(CONFIG.activePet, "villager")) {
                Utils.summonPet(villager, CONFIG.villagerName);
            } else if (Objects.equals(CONFIG.activePet, "wandering_trader")) {
                Utils.summonPet(wanderingTrader, CONFIG.wanderingTraderName);
            } else if (Objects.equals(CONFIG.activePet, "bee")) {
                Utils.summonPet(bee, CONFIG.beeName);
            } else if (Objects.equals(CONFIG.activePet, "cave_spider")) {
                Utils.summonPet(caveSpider, CONFIG.caveSpiderName);
            } else if (Objects.equals(CONFIG.activePet, "dolphin")) {
                Utils.summonPet(dolphin, CONFIG.dolphinName);
            } else if (Objects.equals(CONFIG.activePet, "enderman")) {
                Utils.summonPet(enderman, CONFIG.endermanName);
            } else if (Objects.equals(CONFIG.activePet, "fox")) {
                Utils.summonPet(fox, CONFIG.foxName);
            } else if (Objects.equals(CONFIG.activePet, "goat")) {
                Utils.summonPet(goat, CONFIG.goatName);
            } else if (Objects.equals(CONFIG.activePet, "iron_golem")) {
                Utils.summonPet(ironGolem, CONFIG.ironGolemName);
            } else if (Objects.equals(CONFIG.activePet, "llama")) {
                Utils.summonPet(llama, CONFIG.llamaName);
            } else if (Objects.equals(CONFIG.activePet, "nautilus")) {
                Utils.summonPet(nautilus, CONFIG.nautilusName);
            } else if (Objects.equals(CONFIG.activePet, "panda")) {
                Utils.summonPet(panda, CONFIG.pandaName);
            } else if (Objects.equals(CONFIG.activePet, "piglin")) {
                Utils.summonPet(piglin, CONFIG.piglinName);
            } else if (Objects.equals(CONFIG.activePet, "polar_bear")) {
                Utils.summonPet(polarBear, CONFIG.polarBearName);
            } else if (Objects.equals(CONFIG.activePet, "pufferfish")) {
                Utils.summonPet(pufferFish, CONFIG.pufferFishName);
            } else if (Objects.equals(CONFIG.activePet, "spider")) {
                Utils.summonPet(spider, CONFIG.spiderName);
            } else if (Objects.equals(CONFIG.activePet, "wolf")) {
                Utils.summonPet(wolf, CONFIG.wolfName);
            } else if (Objects.equals(CONFIG.activePet, "blaze")) {
                Utils.summonPet(blaze, CONFIG.blazeName);
            } else if (Objects.equals(CONFIG.activePet, "breeze")) {
                Utils.summonPet(breeze, CONFIG.breezeName);
            } else if (Objects.equals(CONFIG.activePet, "creaking")) {
                Utils.summonPet(creaking, CONFIG.creakingName);
            } else if (Objects.equals(CONFIG.activePet, "creeper")) {
                Utils.summonPet(creeper, CONFIG.creeperName);
            } else if (Objects.equals(CONFIG.activePet, "elder_guardian")) {
                Utils.summonPet(elderGuardian, CONFIG.elderGuardianName);
            } else if (Objects.equals(CONFIG.activePet, "endermite")) {
                Utils.summonPet(endermite, CONFIG.endermiteName);
            } else if (Objects.equals(CONFIG.activePet, "evoker")) {
                Utils.summonPet(evoker, CONFIG.evokerName);
            } else if (Objects.equals(CONFIG.activePet, "happy_ghast")) {
                Utils.summonPet(happyGhast, CONFIG.happyGhastName);
            } else if (Objects.equals(CONFIG.activePet, "ghast")) {
                Utils.summonPet(ghast, CONFIG.ghastName);
            } else if (Objects.equals(CONFIG.activePet, "guardian")) {
                Utils.summonPet(guardian, CONFIG.guardianName);
            } else if (Objects.equals(CONFIG.activePet, "hoglin")) {
                Utils.summonPet(hoglin, CONFIG.hoglinName);
            } else if (Objects.equals(CONFIG.activePet, "magma_cube")) {
                Utils.summonPet(magmaCube, CONFIG.magmaCubeName);
            } else if (Objects.equals(CONFIG.activePet, "phantom")) {
                Utils.summonPet(phantom, CONFIG.phantomName);
            } else if (Objects.equals(CONFIG.activePet, "pillager")) {
                Utils.summonPet(pillager, CONFIG.pillagerName);
            } else if (Objects.equals(CONFIG.activePet, "ravager")) {
                Utils.summonPet(ravager, CONFIG.ravagerName);
            } else if (Objects.equals(CONFIG.activePet, "shulker")) {
                Utils.summonPet(shulker, CONFIG.shulkerName);
            } else if (Objects.equals(CONFIG.activePet, "silverfish")) {
                Utils.summonPet(silverfish, CONFIG.silverfishName);
            } else if (Objects.equals(CONFIG.activePet, "skeleton")) {
                Utils.summonPet(skeleton, CONFIG.skeletonName);
            } else if (Objects.equals(CONFIG.activePet, "slime")) {
                Utils.summonPet(slime, CONFIG.slimeName);
            } else if (Objects.equals(CONFIG.activePet, "vex")) {
                Utils.summonPet(vex, CONFIG.vexName);
            } else if (Objects.equals(CONFIG.activePet, "vindicator")) {
                Utils.summonPet(vindicator, CONFIG.vindicatorName);
            } else if (Objects.equals(CONFIG.activePet, "warden")) {
                Utils.summonPet(warden, CONFIG.wardenName);
            } else if (Objects.equals(CONFIG.activePet, "witch")) {
                Utils.summonPet(witch, CONFIG.witchName);
            } else if (Objects.equals(CONFIG.activePet, "zombie")) {
                Utils.summonPet(zombie, CONFIG.zombieName);
            } else if (Objects.equals(CONFIG.activePet, "zombie_villager")) {
                Utils.summonPet(zombieVillager, CONFIG.zombieVillagerName);
            } else if (Objects.equals(CONFIG.activePet, "husk")) {
                Utils.summonPet(husk, CONFIG.huskName);
            } else if (Objects.equals(CONFIG.activePet, "drowned")) {
                Utils.summonPet(drowned, CONFIG.drownedName);
            } else if (Objects.equals(CONFIG.activePet, "bogged")) {
                Utils.summonPet(bogged, CONFIG.boggedName);
            } else if (Objects.equals(CONFIG.activePet, "parched")) {
                Utils.summonPet(parched, CONFIG.parchedName);
            } else if (Objects.equals(CONFIG.activePet, "stray")) {
                Utils.summonPet(stray, CONFIG.strayName);
            } else if (Objects.equals(CONFIG.activePet, "wither_skeleton")) {
                Utils.summonPet(witherSkeleton, CONFIG.witherSkeletonName);
            } else if (Objects.equals(CONFIG.activePet, "ender_dragon")) {
                Utils.summonPet(enderDragon, CONFIG.enderDragonName);
            } else if (Objects.equals(CONFIG.activePet, "wither")) {
                Utils.summonPet(wither, CONFIG.witherName);
            } else if (Objects.equals(CONFIG.activePet, "angry_ghast")) {
                Utils.summonPet(angryGhast, CONFIG.angryGhastName);
            } else if (Objects.equals(CONFIG.activePet, "batato")) {
                Utils.summonPet(batato, CONFIG.batatoName);
            } else if (Objects.equals(CONFIG.activePet, "diamond_chicken")) {
                Utils.summonPet(diamondChicken, CONFIG.diamondChickenName);
            } else if (Objects.equals(CONFIG.activePet, "love_golem")) {
                Utils.summonPet(loveGolem, CONFIG.loveGolemName);
            } else if (Objects.equals(CONFIG.activePet, "mega_spud")) {
                Utils.summonPet(megaSpud, CONFIG.megaSpudName);
            } else if (Objects.equals(CONFIG.activePet, "moon_cow")) {
                Utils.summonPet(moonCow, CONFIG.moonCowName);
            } else if (Objects.equals(CONFIG.activePet, "nerd_creeper")) {
                Utils.summonPet(nerdCreeper, CONFIG.nerdCreeperName);
            } else if (Objects.equals(CONFIG.activePet, "pink_wither")) {
                Utils.summonPet(pinkWither, CONFIG.pinkWitherName);
            } else if (Objects.equals(CONFIG.activePet, "plaguewhale_slab")) {
                Utils.summonPet(plaguewhaleSlab, CONFIG.plaguewhaleSlabName);
            } else if (Objects.equals(CONFIG.activePet, "poisonous_potato_zombie")) {
                Utils.summonPet(poisonousPotatoZombie, CONFIG.poisonousPotatoZombieName);
            } else if (Objects.equals(CONFIG.activePet, "ray_tracing")) {
                Utils.summonPet(rayTracing, CONFIG.rayTracingName);
            } else if (Objects.equals(CONFIG.activePet, "redstone_bug")) {
                Utils.summonPet(redstoneBug, CONFIG.redstoneBugName);
            } else if (Objects.equals(CONFIG.activePet, "smiling_creeper")) {
                Utils.summonPet(smilingCreeper, CONFIG.smilingCreeperName);
            } else if (Objects.equals(CONFIG.activePet, "toxifin_slab")) {
                Utils.summonPet(toxifinSlab, CONFIG.toxfinSlabName);
            } else if (Objects.equals(CONFIG.activePet, "potato_husk")) {
                Utils.summonPet(potatoHusk, CONFIG.potatoHuskName);
            } else if (Objects.equals(CONFIG.activePet, "head")) {
                Utils.summonPet(head, CONFIG.headName);
            } else if (Objects.equals(CONFIG.activePet, "traitor")) {
                Utils.summonPet(traitor, CONFIG.traitorName);
            } else if (Objects.equals(CONFIG.activePet, "dumbo_octopus")) {
                Utils.summonPet(dumboOctopus, CONFIG.dumboOctopusName);
            } else if (Objects.equals(CONFIG.activePet, "koi")) {
                Utils.summonPet(koi, CONFIG.koiName);
            } else if (Objects.equals(CONFIG.activePet, "stingray")) {
                Utils.summonPet(stingray, CONFIG.stingrayName);
            } else if (Objects.equals(CONFIG.activePet, "sulfur_cube")) {
                Utils.summonPet(sulfurCube, CONFIG.sulfurCubeName);
            }
        }
        NetworkManager.get().broadcastGeneral(minecraft.player.getStringUUID(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
    }

    /**
     * Runs every tick in the {@link Central#createTickWatcher()} below. Checks if the
     * entities' names match the name defined in the config, and if they don't, assings
     * them the correct name. For the helper methods used, see {@link Utils#checkName}
     */
    public static void refreshPetNames() {
        Utils.checkName("cat", cat, CONFIG.catName);
        Utils.checkName("duck", duck, CONFIG.duckName);
        Utils.checkName("racoon", racoon, CONFIG.racoonName);
        Utils.checkName("penguin", penguin, CONFIG.penguinName);
        Utils.checkName("sheep", sheep, CONFIG.sheepName);
        Utils.checkName("allay", allay, CONFIG.allayName);
        Utils.checkName("armadillo", armadillo, CONFIG.armadilloName);
        Utils.checkName("axolotl", axolotl, CONFIG.axolotlName);
        Utils.checkName("bat", bat, CONFIG.batName);
        Utils.checkName("camel", camel, CONFIG.camelName);
        Utils.checkName("chicken", chicken, CONFIG.chickenName);
        Utils.checkName("cod", cod, CONFIG.codName);
        Utils.checkName("copper_golem", copperGolem, CONFIG.copperGolemName);
        Utils.checkName("cow", cow, CONFIG.cowName);
        Utils.checkName("donkey", donkey, CONFIG.donkeyName);
        Utils.checkName("frog", frog, CONFIG.frogName);
        Utils.checkName("horse", horse, CONFIG.horseName);
        Utils.checkName("mooshroom", mooshroom, CONFIG.mooshroomName);
        Utils.checkName("parrot", parrot, CONFIG.parrotName);
        Utils.checkName("pig", pig, CONFIG.pigName);
        Utils.checkName("rabbit", rabbit, CONFIG.rabbitName);
        Utils.checkName("salmon", salmon, CONFIG.salmonName);
        Utils.checkName("sniffer", sniffer, CONFIG.snifferName);
        Utils.checkName("snow_golem", snowGolem, CONFIG.snowGolemName);
        Utils.checkName("squid", squid, CONFIG.squidName);
        Utils.checkName("strider", strider, CONFIG.striderName);
        Utils.checkName("tadpole", tadpole, CONFIG.tadpoleName);
        Utils.checkName("turtle", turtle, CONFIG.turtleName);
        Utils.checkName("villager", villager, CONFIG.villagerName);
        Utils.checkName("wandering_trader", wanderingTrader, CONFIG.wanderingTraderName);
        Utils.checkName("bee", bee, CONFIG.beeName);
        Utils.checkName("cave_spider", caveSpider, CONFIG.caveSpiderName);
        Utils.checkName("dolphin", dolphin, CONFIG.dolphinName);
        Utils.checkName("enderman", enderman, CONFIG.endermanName);
        Utils.checkName("fox", fox, CONFIG.foxName);
        Utils.checkName("goat", goat, CONFIG.goatName);
        Utils.checkName("iron_golem", ironGolem, CONFIG.ironGolemName);
        Utils.checkName("llama", llama, CONFIG.llamaName);
        Utils.checkName("nautilus", nautilus, CONFIG.nautilusName);
        Utils.checkName("panda", panda, CONFIG.pandaName);
        Utils.checkName("piglin", piglin, CONFIG.piglinName);
        Utils.checkName("polar_bear", polarBear, CONFIG.polarBearName);
        Utils.checkName("pufferfish", pufferFish, CONFIG.pufferFishName);
        Utils.checkName("spider", spider, CONFIG.spiderName);
        Utils.checkName("wolf", wolf, CONFIG.wolfName);
        Utils.checkName("blaze", blaze, CONFIG.blazeName);
        Utils.checkName("breeze", breeze, CONFIG.breezeName);
        Utils.checkName("creaking", creaking, CONFIG.creakingName);
        Utils.checkName("creeper", creeper, CONFIG.creeperName);
        Utils.checkName("elder_guardian", elderGuardian, CONFIG.elderGuardianName);
        Utils.checkName("endermite", endermite, CONFIG.endermiteName);
        Utils.checkName("evoker", evoker, CONFIG.evokerName);
        Utils.checkName("happy_ghast", happyGhast, CONFIG.happyGhastName);
        Utils.checkName("ghast", ghast, CONFIG.ghastName);
        Utils.checkName("guardian", guardian, CONFIG.guardianName);
        Utils.checkName("hoglin", hoglin, CONFIG.hoglinName);
        Utils.checkName("magma_cube", magmaCube, CONFIG.magmaCubeName);
        Utils.checkName("phantom", phantom, CONFIG.phantomName);
        Utils.checkName("pillager", pillager, CONFIG.pillagerName);
        Utils.checkName("ravager", ravager, CONFIG.ravagerName);
        Utils.checkName("shulker", shulker, CONFIG.shulkerName);
        Utils.checkName("silverfish", silverfish, CONFIG.silverfishName);
        Utils.checkName("skeleton", skeleton, CONFIG.skeletonName);
        Utils.checkName("slime", slime, CONFIG.slimeName);
        Utils.checkName("vex", vex, CONFIG.vexName);
        Utils.checkName("vindicator", vindicator, CONFIG.vindicatorName);
        Utils.checkName("husk", husk, CONFIG.huskName);
        Utils.checkName("drowned", drowned, CONFIG.drownedName);
        Utils.checkName("bogged", bogged, CONFIG.boggedName);
        Utils.checkName("parched", parched, CONFIG.parchedName);
        Utils.checkName("stray", stray, CONFIG.strayName);
        Utils.checkName("wither_skeleton", witherSkeleton, CONFIG.witherSkeletonName);
        Utils.checkName("ender_dragon", enderDragon, CONFIG.enderDragonName);
        Utils.checkName("wither", wither, CONFIG.witherName);
        Utils.checkName("angry_ghast", angryGhast, CONFIG.angryGhastName);
        Utils.checkName("batato", batato, CONFIG.batatoName);
        Utils.checkName("diamond_chicken", diamondChicken, CONFIG.diamondChickenName);
        Utils.checkName("love_golem", loveGolem, CONFIG.loveGolemName);
        Utils.checkName("mega_spud", megaSpud, CONFIG.megaSpudName);
        Utils.checkName("moon_cow", moonCow, CONFIG.moonCowName);
        Utils.checkName("nerd_creeper", nerdCreeper, CONFIG.nerdCreeperName);
        Utils.checkName("pink_wither", pinkWither, CONFIG.pinkWitherName);
        Utils.checkName("plaguewhale_slab", plaguewhaleSlab, CONFIG.plaguewhaleSlabName);
        Utils.checkName("poisonous_potato_zombie", poisonousPotatoZombie, CONFIG.poisonousPotatoZombieName);
        Utils.checkName("ray_tracing", rayTracing, CONFIG.rayTracingName);
        Utils.checkName("redstone_bug", redstoneBug, CONFIG.redstoneBugName);
        Utils.checkName("smiling_creeper", smilingCreeper, CONFIG.smilingCreeperName);
        Utils.checkName("toxifin_slab", toxifinSlab, CONFIG.toxfinSlabName);
        Utils.checkName("potato_husk", potatoHusk, CONFIG.potatoHuskName);
        Utils.checkName("head", head, CONFIG.headName);
        Utils.checkName("traitor", traitor, CONFIG.traitorName);
        Utils.checkName("dumbo_octopus", dumboOctopus, CONFIG.dumboOctopusName);
        Utils.checkName("koi", koi, CONFIG.koiName);
        Utils.checkName("stingray", stingray, CONFIG.stingrayName);
        Utils.checkName("sulfur_cube", sulfurCube, CONFIG.sulfurCubeName);
    }

    /**
     * Assigns the correct matching suggestions depending on what the current active pet is.
     *
     * @see ChatAccessor
     */
    public static void updateSuggestions(Minecraft client) {
        List<String> skinSuggestions = switch (CONFIG.activePet) {
            case "duck" -> DUCK_SKINS;
            case "racoon" -> RACOON_SKINS;
            case "cat" -> CAT_SKINS;
            case "axolotl" -> AXOLOTL_SKINS;
            case "camel" -> CAMEL_SKINS;
            case "chicken", "cow", "frog", "pig" -> TEMPERATE_COLD_WARM;
            case "creeper", "nerd_creeper", "smiling_creeper" -> CREEPER_SKINS;
            case "copper_golem" -> COPPER_GOLEM_SKINS;
            case "horse" -> HORSE_SKINS;
            case "parrot" -> PARROT_SKINS;
            case "rabbit" -> RABBIT_SKINS;
            case "sheep" -> SHEEP_SKINS;
            case "snow_golem" -> SNOW_GOLEM_KINS;
            case "squid" -> SQUID_SKINS;
            case "strider" -> STRIDER_SKINS;
            case "villager" -> VILLAGER_SKINS;
            case "bee" -> BEE_SKINS;
            case "fox" -> FOX_SKINS;
            case "llama" -> LLAMA_SKINS;
            case "nautilus" -> NAUTILUS_SKINS;
            case "panda" -> PANDA_SKINS;
            case "piglin" -> PIGLIN_SKINS;
            case "wolf" -> WOLF_SKINS;
            case "hoglin" -> HOGLIN_SKINS;
            case "magma_cube", "slime", "tropical_slime" -> SLIME_LIKE_SKINS;
            case "zombie_villager" -> VILLAGER_SKINS;
            case "shulker" -> SHULKER_SKINS;
            case "wither" -> WITHER_SKINS;
            case "head" -> HEAD_SKINS;
            case "traitor" -> TRAITOR_SKINS;
            case "dumbo_octopus" -> DUMBO_OCTOPUS_SKINS;
            case "sulfur_cube" -> Utils.getAllBlocks();
            case null, default -> EMPTY_LIST;
        };

        currentSuggestions.clear();
        currentSuggestions.add("baby");
        currentSuggestions.add("adult");
        currentSuggestions.addAll(skinSuggestions);
        refreshChatSuggestor(client);
    }

    /**
     * Refreshes the chat suggestions so that the user sees a fresh set of suggestions
     * when changing their pet.
     *
     * @see ChatAccessor
     */
    public static void refreshChatSuggestor(Minecraft client) {
        Screen screen = client.gui.screen();
        if ((screen instanceof ChatScreen chatScreen)) {
            CommandSuggestions suggestions = (CommandSuggestions) ChatAccessor.getChatInputSuggestor(chatScreen);
            suggestions.updateCommandInfo();
        }
    }

    /**
     * Adds the custom resourcepack required for the head into the resource pack respository.
     * More about this custom pack can be seen in {@link HeadSkin}.
     */
    public static void checkForHeadResourcePack() {
        Minecraft client = Minecraft.getInstance();
        Options options = client.options;
        List<String> resourcePacks = new ArrayList<>(options.resourcePacks);

        /*if (!resourcePacks.contains("file/headpack") && Objects.equals(CONFIG.activePet, "head")) {
            resourcePacks.add("file/headpack");
            client.getResourcePackRepository().addPack("file/headpack");
            options.save();
            client.reloadResourcePacks();
            //client.player.sendSystemMessage(Component.literal("§b[PetsMod] §aSorry for the interruption, the head pet requires a custom resource pack to work correctly and we loaded a pack for you. This will not affect anything except the head texture."));
        }*/
    }

    /**
     * Used to re-assign the logo, edition texts, and splashes in {@link SplashManagerMixin} and {@link TitleScreenRenderingMixin}.
     *
     * @param bl: Whether to re-assign the logo or use the default ones.
     */
    public static void reassignLogo(Boolean bl) {
        if (bl) {
            //LogoRenderer.MINECRAFT_LOGO = Identifier.fromNamespaceAndPath(MOD_ID, "textures/title/petsmod.png");
           // LogoRenderer.EASTER_EGG_LOGO = Identifier.fromNamespaceAndPath(MOD_ID, "textures/title/modpets.png");
           // LogoRenderer.MINECRAFT_EDITION = Identifier.fromNamespaceAndPath(MOD_ID, "textures/title/version.png");
          // SplashManager.SPLASHES_LOCATION = Identifier.fromNamespaceAndPath(MOD_ID, "texts/splashes.txt");
        } else {
          //  LogoRenderer.MINECRAFT_LOGO = Identifier.withDefaultNamespace("textures/gui/title/minecraft.png");
          //  LogoRenderer.EASTER_EGG_LOGO = Identifier.withDefaultNamespace("textures/gui/title/minceraft.png");
          //  LogoRenderer.MINECRAFT_EDITION = Identifier.withDefaultNamespace("textures/gui/title/edition.png");
            //SplashManager.SPLASHES_LOCATION = Identifier.withDefaultNamespace("texts/splashes.txt");
        }
    }

    /**
     * Required call to {@link ClientModInitializer#onInitializeClient()} that calls the initial code.
     * <p>- Initializes all of the commands
     * <p>- Gets the default head skin
     * <p>- Registers the config
     * <p>Do NOT ever call CONFIG before it is called here or in any other {@link ClientModInitializer#onInitializeClient()}
     * implementation, as it will cause a {@code RuntimeException}.
     */
    public static void agentmain(String string, Instrumentation instrumentation) {

    }

    public static Central get() {
        return new Central();
    }

    /**
     * Creates the command that allows users to use {@code /petskin}. Some of the code is
     * slightly malformed (specifically the {@code switch} statements) as this file was
     * re-created from an bytecode after a change messed it up around version {@code 0.6.0}
     */
    public void createPetSkinCommand(Object obj) {
        if (Minecraft.getInstance().getConnection() != null) {
            ClientPacketListener connection = Minecraft.getInstance().getConnection();
            RootCommandNode<ClientSuggestionProvider> commandRoot = connection.getCommands().getRoot();
            commandRoot.getExamples().clear();
        }

        CommandDispatcher dispatcher = (CommandDispatcher) obj;

        dispatcher.register(LiteralArgumentBuilder.literal("petskin").then(RequiredArgumentBuilder.argument("skin", StringArgumentType.greedyString())
                .suggests(this.SKINS)
                .executes((context) -> {
                    Minecraft.getInstance().execute(() -> {
                    boolean isValid = true;
                    String skin = StringArgumentType.getString(context, "skin");

                    if (Objects.equals(skin, "baby")) {
                        CONFIG.isBaby = true;
                        NetworkManager.get().broadcastToggleBaby(Minecraft.getInstance().player.getStringUUID(), CONFIG.isBaby);
                    } else if (Objects.equals(skin, "adult")) {
                        CONFIG.isBaby = false;
                        NetworkManager.get().broadcastToggleBaby(Minecraft.getInstance().player.getStringUUID(), CONFIG.isBaby);
                    } else {
                        if (Objects.equals(CONFIG.activePet, "duck")) {
                            switch (skin) {
                                case "mallard":
                                    CONFIG.duckSkin = "mallard";
                                    break;
                                case "pekin":
                                    CONFIG.duckSkin = "pekin";
                                    break;
                                case "rubber":
                                    CONFIG.duckSkin = "rubber";
                                    break;
                                case "bronze": CONFIG.duckSkin = "bronze";
                                break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "racoon")) {
                            switch (skin) {
                                case "normal" -> CONFIG.racoonSkin = "normal";
                                case "albino" -> CONFIG.racoonSkin = "albino";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "cat")) {
                            switch (skin) {
                                case "black":
                                    CONFIG.catSkin = "all_black";
                                    break;
                                case "tuxedo":
                                    CONFIG.catSkin = "tuxedo";
                                    break;
                                case "tabby":
                                    CONFIG.catSkin = "tabby";
                                    break;
                                case "red":
                                    CONFIG.catSkin = "red";
                                    break;
                                case "siamese":
                                    CONFIG.catSkin = "siamese";
                                    break;
                                case "calico":
                                    CONFIG.catSkin = "calico";
                                    break;
                                case "british_shorthair":
                                case "british shorthair":
                                    CONFIG.catSkin = "british_shorthair";
                                    break;
                                case "persian":
                                    CONFIG.catSkin = "persian";
                                    break;
                                case "ragdoll":
                                    CONFIG.catSkin = "ragdoll";
                                    break;
                                case "white":
                                    CONFIG.catSkin = "white";
                                    break;
                                case "jellie":
                                    CONFIG.catSkin = "jellie";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "sheep")) {
                            switch (skin) {
                                case "white":
                                    CONFIG.sheepSkin = "white";
                                    break;
                                case "orange":
                                    CONFIG.sheepSkin = "orange";
                                    break;
                                case "magenta":
                                    CONFIG.sheepSkin = "magenta";
                                    break;
                                case "light_blue":
                                case "light blue":
                                    CONFIG.sheepSkin = "light_blue";
                                    break;
                                case "yellow":
                                    CONFIG.sheepSkin = "yellow";
                                    break;
                                case "lime":
                                    CONFIG.sheepSkin = "lime";
                                    break;
                                case "pink":
                                    CONFIG.sheepSkin = "pink";
                                    break;
                                case "gray":
                                    CONFIG.sheepSkin = "gray";
                                    break;
                                case "light_gray":
                                case "light gray":
                                    CONFIG.sheepSkin = "light_gray";
                                    break;
                                case "cyan":
                                    CONFIG.sheepSkin = "cyan";
                                    break;
                                case "purple":
                                    CONFIG.sheepSkin = "purple";
                                    break;
                                case "blue":
                                    CONFIG.sheepSkin = "blue";
                                    break;
                                case "brown":
                                    CONFIG.sheepSkin = "brown";
                                    break;
                                case "green":
                                    CONFIG.sheepSkin = "green";
                                    break;
                                case "red":
                                    CONFIG.sheepSkin = "red";
                                    break;
                                case "black":
                                    CONFIG.sheepSkin = "black";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "chicken")) {
                            switch (skin) {
                                case "temperate":
                                    CONFIG.chickenSkin = "temperate";
                                    break;
                                case "cold":
                                    CONFIG.chickenSkin = "cold";
                                    break;
                                case "warm":
                                    CONFIG.chickenSkin = "warm";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "axolotl")) {
                            switch (skin) {
                                case "pink":
                                    CONFIG.axolotlSkin = "pink";
                                    break;
                                case "brown":
                                    CONFIG.axolotlSkin = "brown";
                                    break;
                                case "gold":
                                    CONFIG.axolotlSkin = "gold";
                                    break;
                                case "cyan":
                                    CONFIG.axolotlSkin = "cyan";
                                    break;
                                case "blue":
                                    CONFIG.axolotlSkin = "blue";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "camel")) {
                            if (Objects.equals(skin, "camel")) {
                                CONFIG.camelSkin = "camel";
                            } else if (Objects.equals(skin, "husk")) {
                                CONFIG.camelSkin = "husk";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "copper_golem")) {
                            switch (skin) {
                                case "unoxidized":
                                    CONFIG.copperGolemSkin = "unoxidized";
                                    break;
                                case "exposed":
                                    CONFIG.copperGolemSkin = "exposed";
                                    break;
                                case "weathered":
                                    CONFIG.copperGolemSkin = "weathered";
                                    break;
                                case "oxidized":
                                    CONFIG.copperGolemSkin = "oxidized";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "cow")) {
                            switch (skin) {
                                case "temperate":
                                    CONFIG.cowSkin = "temperate";
                                    break;
                                case "cold":
                                    CONFIG.cowSkin = "cold";
                                    break;
                                case "warm":
                                    CONFIG.cowSkin = "warm";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "frog")) {
                            switch (skin) {
                                case "temperate":
                                    CONFIG.frogSkin = "temperate";
                                    break;
                                case "cold":
                                    CONFIG.frogSkin = "cold";
                                    break;
                                case "warm":
                                    CONFIG.frogSkin = "warm";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "horse")) {
                            switch (skin) {
                                case "white":
                                    CONFIG.horseSkin = "white";
                                    break;
                                case "creamy":
                                    CONFIG.horseSkin = "creamy";
                                    break;
                                case "chestnut":
                                    CONFIG.horseSkin = "chestnut";
                                    break;
                                case "brown":
                                    CONFIG.horseSkin = "brown";
                                    break;
                                case "black":
                                    CONFIG.horseSkin = "black";
                                    break;
                                case "gray":
                                    CONFIG.horseSkin = "gray";
                                    break;
                                case "dark_brown":
                                    CONFIG.horseSkin = "dark_brown";
                                    break;
                                case "skeleton":
                                    CONFIG.horseSkin = "skeleton";
                                    break;
                                case "zombie":
                                    CONFIG.horseSkin = "zombie";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "parrot")) {
                            switch (skin) {
                                case "red":
                                    CONFIG.parrotSkin = "red";
                                    break;
                                case "blue":
                                    CONFIG.parrotSkin = "blue";
                                    break;
                                case "green":
                                    CONFIG.parrotSkin = "green";
                                    break;
                                case "cyan":
                                    CONFIG.parrotSkin = "cyan";
                                    break;
                                case "gray":
                                    CONFIG.parrotSkin = "gray";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "pig")) {
                            switch (skin) {
                                case "temperate":
                                    CONFIG.pigSkin = "temperate";
                                    break;
                                case "warm":
                                    CONFIG.pigSkin = "warm";
                                    break;
                                case "cold":
                                    CONFIG.pigSkin = "cold";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "rabbit")) {
                            switch (skin) {
                                case "brown":
                                    CONFIG.rabbitSkin = "brown";
                                    break;
                                case "white":
                                    CONFIG.rabbitSkin = "white";
                                    break;
                                case "black":
                                    CONFIG.rabbitSkin = "black";
                                    break;
                                case "splotched":
                                    CONFIG.rabbitSkin = "splotched";
                                    break;
                                case "gold":
                                    CONFIG.rabbitSkin = "gold";
                                    break;
                                case "salt":
                                    CONFIG.rabbitSkin = "salt";
                                    break;
                                case "killer":
                                    CONFIG.rabbitSkin = "killer";
                                    break;
                                case "toast":
                                    CONFIG.rabbitSkin = "toast";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "snow_golem")) {
                            if (!Objects.equals(skin, "pumpkin_on") && !Objects.equals(skin, "pumpkin on")) {
                                if (!Objects.equals(skin, "pumpkin_off") && !Objects.equals(skin, "pumpkin off")) {
                                    isValid = false;
                                } else {
                                    CONFIG.snowGolemSkin = "pumpkin_off";
                                }
                            } else {
                                CONFIG.snowGolemSkin = "pumpkin_on";
                            }
                        } else if (Objects.equals(CONFIG.activePet, "squid")) {
                            if (Objects.equals(skin, "squid")) {
                                CONFIG.squidSkin = "squid";
                            } else if (!Objects.equals(skin, "glow_squid") && !Objects.equals(skin, "glow squid")) {
                                isValid = false;
                            } else {
                                CONFIG.squidSkin = "glow_squid";
                            }
                        } else if (Objects.equals(CONFIG.activePet, "villager")) {
                            switch (skin) {
                                case "farmer":
                                    CONFIG.villagerSkin = "farmer";
                                    break;
                                case "fisherman":
                                    CONFIG.villagerSkin = "fisherman";
                                    break;
                                case "shepherd":
                                    CONFIG.villagerSkin = "shepherd";
                                    break;
                                case "fletcher":
                                    CONFIG.villagerSkin = "fletcher";
                                    break;
                                case "cleric":
                                    CONFIG.villagerSkin = "cleric";
                                    break;
                                case "weaponsmith":
                                    CONFIG.villagerSkin = "weaponsmith";
                                    break;
                                case "armorer":
                                    CONFIG.villagerSkin = "armorer";
                                    break;
                                case "toolsmith":
                                    CONFIG.villagerSkin = "toolsmith";
                                    break;
                                case "librarian":
                                    CONFIG.villagerSkin = "librarian";
                                    break;
                                case "cartographer":
                                    CONFIG.villagerSkin = "cartographer";
                                    break;
                                case "leatherworker":
                                    CONFIG.villagerSkin = "leatherworker";
                                    break;
                                case "butcher":
                                    CONFIG.villagerSkin = "butcher";
                                    break;
                                case "mason":
                                    CONFIG.villagerSkin = "mason";
                                    break;
                                case "nitwit":
                                    CONFIG.villagerSkin = "nitwit";
                                    break;
                                case "unemployed":
                                    CONFIG.villagerSkin = "unemployed";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "mooshroom")) {
                            if (Objects.equals(skin, "red")) {
                                CONFIG.mooshroomSkin = "red";
                            } else if (Objects.equals(skin, "brown")) {
                                CONFIG.mooshroomSkin = "brown";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "strider")) {
                            if (Objects.equals(skin, "warm")) {
                                CONFIG.striderSkin = "warm";
                            } else if (Objects.equals(skin, "cold")) {
                                CONFIG.striderSkin = "cold";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "bee")) {
                            switch (skin) {
                                case "happy":
                                    CONFIG.beeSkin = "happy";
                                    break;
                                case "angry":
                                    CONFIG.beeSkin = "angry";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "fox")) {
                            switch (skin) {
                                case "red":
                                    CONFIG.foxSkin = "red";
                                    break;
                                case "snow":
                                    CONFIG.foxSkin = "snow";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "llama")) {
                            switch (skin) {
                                case "brown":
                                    CONFIG.llamaSkin = "brown";
                                    break;
                                case "creamy":
                                    CONFIG.llamaSkin = "creamy";
                                    break;
                                case "gray":
                                    CONFIG.llamaSkin = "gray";
                                    break;
                                case "white":
                                    CONFIG.llamaSkin = "white";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "nautilus")) {
                            switch (skin) {
                                case "nautilus":
                                    CONFIG.nautilusSkin = "nautilus";
                                    break;
                                case "zombie":
                                    CONFIG.nautilusSkin = "zombie";
                                    break;
                                case "coral_zombie":
                                case "coral zombie":
                                    CONFIG.nautilusSkin = "coral_zombie";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "panda")) {
                            switch (skin) {
                                case "normal":
                                    CONFIG.pandaSkin = "normal";
                                    break;
                                case "lazy":
                                    CONFIG.pandaSkin = "lazy";
                                    break;
                                case "agressive":
                                    CONFIG.pandaSkin = "agressive";
                                    break;
                                case "worried":
                                    CONFIG.pandaSkin = "worried";
                                    break;
                                case "playful":
                                    CONFIG.pandaSkin = "playful";
                                    break;
                                case "weak":
                                    CONFIG.pandaSkin = "weak";
                                    break;
                                case "brown":
                                    CONFIG.pandaSkin = "brown";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "piglin")) {
                            switch (skin) {
                                case "piglin":
                                    CONFIG.piglinSkin = "piglin";
                                    break;
                                case "zombified_piglin":
                                case "zombified piglin":
                                case "zombified":
                                    CONFIG.piglinSkin = "zombified";
                                    break;
                                case "piglin_brute":
                                case "piglin brute":
                                case "brute":
                                    CONFIG.piglinSkin = "brute";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "wolf")) {
                            switch (skin) {
                                case "pale":
                                    CONFIG.wolfSkin = "pale";
                                    break;
                                case "ashen":
                                    CONFIG.wolfSkin = "ashen";
                                    break;
                                case "black":
                                    CONFIG.wolfSkin = "black";
                                    break;
                                case "chestnut":
                                    CONFIG.wolfSkin = "chestnut";
                                    break;
                                case "rusty":
                                    CONFIG.wolfSkin = "rusty";
                                    break;
                                case "snowy":
                                    CONFIG.wolfSkin = "spotty";
                                    break;
                                case "spotted":
                                    CONFIG.wolfSkin = "spotted";
                                    break;
                                case "striped":
                                    CONFIG.wolfSkin = "striped";
                                    break;
                                case "woods":
                                    CONFIG.wolfSkin = "woods";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "hoglin")) {
                            switch (skin) {
                                case "hoglin", "normal" -> CONFIG.hoglinSkin = "hoglin";
                                case "zoglin" -> CONFIG.hoglinSkin = "zoglin";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "magma_cube")) {
                            switch (skin) {
                                case "small" -> CONFIG.magmaCubeSkin = "small";
                                case "medium" -> CONFIG.magmaCubeSkin = "medium";
                                case "large" -> CONFIG.magmaCubeSkin = "large";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "slime") || Objects.equals(CONFIG.activePet, "tropical_slime")) {
                            switch (skin) {
                                case "small" -> CONFIG.slimeSkin = "small";
                                case "medium" -> CONFIG.slimeSkin = "medium";
                                case "large" -> CONFIG.slimeSkin = "large";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "shulker")) {
                            switch (skin) {
                                case "normal" -> CONFIG.shulkerSkin = "normal";
                                case "black" -> CONFIG.shulkerSkin = "black";
                                case "brown" -> CONFIG.shulkerSkin = "brown";
                                case "cyan" -> CONFIG.shulkerSkin = "cyan";
                                case "gray" -> CONFIG.shulkerSkin = "gray";
                                case "green" -> CONFIG.shulkerSkin = "green";
                                case "light_blue", "light blue" -> CONFIG.shulkerSkin = "light_blue";
                                case "light_gray", "light gray" -> CONFIG.shulkerSkin = "light_gray";
                                case "lime" -> CONFIG.shulkerSkin = "lime";
                                case "magenta" -> CONFIG.shulkerSkin = "magenta";
                                case "orange" -> CONFIG.shulkerSkin = "orange";
                                case "pink" -> CONFIG.shulkerSkin = "pink";
                                case "purple" -> CONFIG.shulkerSkin = "purple";
                                case "red" -> CONFIG.shulkerSkin = "red";
                                case "white" -> CONFIG.shulkerSkin = "white";
                                case "yellow" -> CONFIG.shulkerSkin = "yellow";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "zombie_villager")) {
                            switch (skin) {
                                case "farmer":
                                    CONFIG.zombieVillagerSkin = "farmer";
                                    break;
                                case "fisherman":
                                    CONFIG.zombieVillagerSkin = "fisherman";
                                    break;
                                case "shepherd":
                                    CONFIG.zombieVillagerSkin = "shepherd";
                                    break;
                                case "fletcher":
                                    CONFIG.zombieVillagerSkin = "fletcher";
                                    break;
                                case "cleric":
                                    CONFIG.zombieVillagerSkin = "cleric";
                                    break;
                                case "weaponsmith":
                                    CONFIG.zombieVillagerSkin = "weaponsmith";
                                    break;
                                case "armorer":
                                    CONFIG.zombieVillagerSkin = "armorer";
                                    break;
                                case "toolsmith":
                                    CONFIG.zombieVillagerSkin = "toolsmith";
                                    break;
                                case "librarian":
                                    CONFIG.zombieVillagerSkin = "librarian";
                                    break;
                                case "cartographer":
                                    CONFIG.zombieVillagerSkin = "cartographer";
                                    break;
                                case "leatherworker":
                                    CONFIG.zombieVillagerSkin = "leatherworker";
                                    break;
                                case "butcher":
                                    CONFIG.zombieVillagerSkin = "butcher";
                                    break;
                                case "mason":
                                    CONFIG.zombieVillagerSkin = "mason";
                                    break;
                                case "nitwit":
                                    CONFIG.zombieVillagerSkin = "nitwit";
                                    break;
                                case "unemployed":
                                    CONFIG.zombieVillagerSkin = "unemployed";
                                    break;
                                case null:
                                default:
                                    isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "creeper") || Objects.equals(CONFIG.activePet, "nerd_creeper") || Objects.equals(CONFIG.activePet, "smiling_creeper")) {
                            switch (skin) {
                                case "normal" -> CONFIG.creeperSkin = "normal";
                                case "charged" -> CONFIG.creeperSkin = "charged";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "wither")) {
                            switch (skin) {
                                case "normal" -> CONFIG.witherSkin = "normal";
                                case "invulnerable" -> CONFIG.witherSkin = "invulnerable";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "head")) {
                            CONFIG.headSkin = skin.toLowerCase();
                        } else if (Objects.equals(CONFIG.activePet, "traitor")) {
                            switch (skin) {
                                case "desert" -> CONFIG.traitorSkin = "desert";
                                case "jungle" -> CONFIG.traitorSkin = "jungle";
                                case "plains" -> CONFIG.traitorSkin = "plains";
                                case "savanna" -> CONFIG.traitorSkin = "savanna";
                                case "snow", "snowy" -> CONFIG.traitorSkin = "snow";
                                case "swamp" -> CONFIG.traitorSkin = "swamp";
                                case "taiga" -> CONFIG.traitorSkin = "taiga";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "dumbo_octopus")) {
                            switch (skin) {
                                case "yellow" -> CONFIG.dumboOctopusSkin = "yellow";
                                case "red" -> CONFIG.dumboOctopusSkin = "red";
                                case "blue" -> CONFIG.dumboOctopusSkin = "blue";
                                case "green" -> CONFIG.dumboOctopusSkin = "green";
                                case "orange" -> CONFIG.dumboOctopusSkin = "orange";
                                case "pink" -> CONFIG.dumboOctopusSkin = "pink";
                                case null, default -> isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "sulfur_cube")) {
                            CONFIG.sulfurCubeSkin = skin.replace("_", " ");
                        }
                    }

                    LocalPlayer player = Minecraft.getInstance().player;
                    if (isValid) {
                        player.sendSystemMessage(Component.literal("§b[PetsMod] §aYour pet's skin has been updated."));
                        NetworkManager.get().broadcastChangePetSkin(Minecraft.getInstance().player.getUUID().toString(), skin);
                    } else {
                        player.sendSystemMessage(Component.literal("§b[PetsMod] §cEither your currently selected pet doesn't support multiple skins, or that is not a valid skin. Try something else."));
                    }
                    AutoConfig.getConfigHolder(PetsConfig.class).save();
                });
                    return 1;
                })));
    }

    /**
     * Utilizes the {@link Utils} class to set any {@code null} Strings that might appear
     * to a default value before they invoke a {@code NullPointerException}.
     */
    public static void checkForNullObjects() {

        if (CONFIG.petOn == null) {
            CONFIG.petOn = true;
        }
        CONFIG.activePet = (CONFIG.activePet == null) ? "duck" : CONFIG.activePet;
        if (CONFIG.customTitleEnabled == null) {
            CONFIG.customTitleEnabled = true;
        }
        CONFIG.duckName = Utils.checkNullString(CONFIG.duckName);
        CONFIG.duckSkin = Utils.checkNullString(CONFIG.duckSkin, "mallard");

        CONFIG.racoonName = Utils.checkNullString(CONFIG.racoonName);
        CONFIG.racoonSkin = Utils.checkNullString(CONFIG.racoonSkin, "normal");

        CONFIG.penguinName = Utils.checkNullString(CONFIG.penguinName);

        CONFIG.sheepName = Utils.checkNullString(CONFIG.sheepName);
        CONFIG.sheepSkin = Utils.checkNullString(CONFIG.sheepSkin, "white");

        CONFIG.catName = Utils.checkNullString(CONFIG.catName);
        CONFIG.catSkin = Utils.checkNullString(CONFIG.catSkin, "tuxedo");

        CONFIG.allayName = Utils.checkNullString(CONFIG.allayName);
        CONFIG.armadilloName = Utils.checkNullString(CONFIG.armadilloName);

        CONFIG.axolotlName = Utils.checkNullString(CONFIG.axolotlName);
        CONFIG.axolotlSkin = Utils.checkNullString(CONFIG.axolotlSkin, "pink");

        CONFIG.batName = Utils.checkNullString(CONFIG.batName);

        CONFIG.camelName = Utils.checkNullString(CONFIG.camelName);
        CONFIG.camelSkin = Utils.checkNullString(CONFIG.camelSkin, "camel");

        CONFIG.chickenName = Utils.checkNullString(CONFIG.chickenName);
        CONFIG.chickenSkin = Utils.checkNullString(CONFIG.chickenSkin, "temperate");

        CONFIG.codName = Utils.checkNullString(CONFIG.codName);

        CONFIG.copperGolemName = Utils.checkNullString(CONFIG.copperGolemName);
        CONFIG.copperGolemSkin = Utils.checkNullString(CONFIG.copperGolemSkin, "unoxidized");

        CONFIG.cowName = Utils.checkNullString(CONFIG.cowName);
        CONFIG.cowSkin = Utils.checkNullString(CONFIG.cowSkin, "temperate");

        CONFIG.donkeyName = Utils.checkNullString(CONFIG.donkeyName);

        CONFIG.frogName = Utils.checkNullString(CONFIG.frogName);
        CONFIG.frogSkin = Utils.checkNullString(CONFIG.frogSkin, "temperate");

        CONFIG.horseName = Utils.checkNullString(CONFIG.horseName);
        CONFIG.horseSkin = Utils.checkNullString(CONFIG.horseSkin, "white");

        CONFIG.mooshroomName = Utils.checkNullString(CONFIG.mooshroomName);
        CONFIG.mooshroomSkin = Utils.checkNullString(CONFIG.mooshroomSkin, "red");

        CONFIG.parrotName = Utils.checkNullString(CONFIG.parrotName);
        CONFIG.parrotSkin = Utils.checkNullString(CONFIG.parrotSkin, "red");

        CONFIG.pigName = Utils.checkNullString(CONFIG.pigName);
        CONFIG.pigSkin = Utils.checkNullString(CONFIG.pigSkin, "temperate");

        CONFIG.rabbitName = Utils.checkNullString(CONFIG.rabbitName);
        CONFIG.rabbitSkin = Utils.checkNullString(CONFIG.rabbitSkin, "brown");

        CONFIG.salmonName = Utils.checkNullString(CONFIG.salmonName);
        CONFIG.snifferName = Utils.checkNullString(CONFIG.snifferName);

        CONFIG.snowGolemName = Utils.checkNullString(CONFIG.snowGolemName);
        CONFIG.snowGolemSkin = Utils.checkNullString(CONFIG.snowGolemSkin, "pumpkin_on");

        CONFIG.squidName = Utils.checkNullString(CONFIG.squidName);
        CONFIG.squidSkin = Utils.checkNullString(CONFIG.squidSkin, "squid");

        CONFIG.striderName = Utils.checkNullString(CONFIG.striderName);
        CONFIG.striderSkin = Utils.checkNullString(CONFIG.striderSkin, "warm");

        CONFIG.tadpoleName = Utils.checkNullString(CONFIG.tadpoleName);
        CONFIG.turtleName = Utils.checkNullString(CONFIG.turtleName);

        CONFIG.villagerName = Utils.checkNullString(CONFIG.villagerName);
        CONFIG.villagerSkin = Utils.checkNullString(CONFIG.villagerSkin, "nitwit");

        CONFIG.wanderingTraderName = Utils.checkNullString(CONFIG.wanderingTraderName);

        CONFIG.beeName = Utils.checkNullString(CONFIG.beeName);
        CONFIG.beeSkin = Utils.checkNullString(CONFIG.beeSkin, "happy");

        CONFIG.caveSpiderName = Utils.checkNullString(CONFIG.caveSpiderName);
        CONFIG.dolphinName = Utils.checkNullString(CONFIG.dolphinName);
        CONFIG.endermanName = Utils.checkNullString(CONFIG.endermanName);

        CONFIG.foxName = Utils.checkNullString(CONFIG.foxName);
        CONFIG.foxSkin = Utils.checkNullString(CONFIG.foxSkin, "red");

        CONFIG.goatName = Utils.checkNullString(CONFIG.goatName);
        CONFIG.ironGolemName = Utils.checkNullString(CONFIG.ironGolemName);

        CONFIG.llamaName = Utils.checkNullString(CONFIG.llamaName);
        CONFIG.llamaSkin = Utils.checkNullString(CONFIG.llamaSkin, "brown");

        CONFIG.nautilusName = Utils.checkNullString(CONFIG.nautilusName);
        CONFIG.nautilusSkin = Utils.checkNullString(CONFIG.nautilusSkin, "nautilus");

        CONFIG.pandaName = Utils.checkNullString(CONFIG.pandaName);
        CONFIG.pandaSkin = Utils.checkNullString(CONFIG.pandaSkin, "normal");

        CONFIG.piglinName = Utils.checkNullString(CONFIG.piglinName);
        CONFIG.piglinSkin = Utils.checkNullString(CONFIG.piglinSkin, "piglin");

        CONFIG.polarBearName = Utils.checkNullString(CONFIG.polarBearName);
        CONFIG.pufferFishName = Utils.checkNullString(CONFIG.pufferFishName);
        CONFIG.spiderName = Utils.checkNullString(CONFIG.spiderName);
        CONFIG.wolfName = Utils.checkNullString(CONFIG.wolfName);
        CONFIG.blazeName = Utils.checkNullString(CONFIG.blazeName);
        CONFIG.boggedName = Utils.checkNullString(CONFIG.boggedName);
        CONFIG.breezeName = Utils.checkNullString(CONFIG.breezeName);
        CONFIG.creakingName = Utils.checkNullString(CONFIG.creakingName);

        CONFIG.creeperName = Utils.checkNullString(CONFIG.creeperName);
        CONFIG.creeperSkin = Utils.checkNullString(CONFIG.creeperSkin, "normal");

        CONFIG.drownedName = Utils.checkNullString(CONFIG.drownedName);
        CONFIG.elderGuardianName = Utils.checkNullString(CONFIG.elderGuardianName);
        CONFIG.endermiteName = Utils.checkNullString(CONFIG.endermiteName);
        CONFIG.evokerName = Utils.checkNullString(CONFIG.evokerName);
        CONFIG.ghastName = Utils.checkNullString(CONFIG.ghastName);
        CONFIG.guardianName = Utils.checkNullString(CONFIG.guardianName);

        CONFIG.hoglinName = Utils.checkNullString(CONFIG.hoglinName);
        CONFIG.hoglinSkin = Utils.checkNullString(CONFIG.hoglinSkin, "hoglin");

        CONFIG.huskName = Utils.checkNullString(CONFIG.huskName);
        CONFIG.happyGhastName = Utils.checkNullString(CONFIG.happyGhastName);

        CONFIG.magmaCubeName = Utils.checkNullString(CONFIG.magmaCubeName);
        CONFIG.magmaCubeSkin = Utils.checkNullString(CONFIG.magmaCubeSkin, "small");

        CONFIG.parchedName = Utils.checkNullString(CONFIG.parchedName);
        CONFIG.phantomName = Utils.checkNullString(CONFIG.phantomName);
        CONFIG.pillagerName = Utils.checkNullString(CONFIG.pillagerName);
        CONFIG.ravagerName = Utils.checkNullString(CONFIG.ravagerName);
        CONFIG.shulkerName = Utils.checkNullString(CONFIG.shulkerName);
        CONFIG.silverfishName = Utils.checkNullString(CONFIG.silverfishName);
        CONFIG.skeletonName = Utils.checkNullString(CONFIG.skeletonName);

        CONFIG.slimeName = Utils.checkNullString(CONFIG.slimeName);
        CONFIG.slimeSkin = Utils.checkNullString(CONFIG.slimeSkin, "small");

        CONFIG.strayName = Utils.checkNullString(CONFIG.strayName);
        CONFIG.vexName = Utils.checkNullString(CONFIG.vexName);
        CONFIG.vindicatorName = Utils.checkNullString(CONFIG.vindicatorName);
        CONFIG.wardenName = Utils.checkNullString(CONFIG.wardenName);
        CONFIG.witchName = Utils.checkNullString(CONFIG.witchName);
        CONFIG.witherSkeletonName = Utils.checkNullString(CONFIG.witherSkeletonName);
        CONFIG.zombieName = Utils.checkNullString(CONFIG.zombieName);

        CONFIG.zombieVillagerName = Utils.checkNullString(CONFIG.zombieVillagerName);
        CONFIG.zombieVillagerSkin = Utils.checkNullString(CONFIG.zombieVillagerSkin, "nitwit");

        CONFIG.enderDragonName = Utils.checkNullString(CONFIG.enderDragonName);
        CONFIG.witherName = Utils.checkNullString(CONFIG.witherName);
        CONFIG.witherSkin = Utils.checkNullString(CONFIG.witherSkin, "normal");

        CONFIG.angryGhastName = Utils.checkNullString(CONFIG.angryGhastName);
        CONFIG.batatoName = Utils.checkNullString(CONFIG.batatoName);
        CONFIG.diamondChickenName = Utils.checkNullString(CONFIG.diamondChickenName);
        CONFIG.loveGolemName = Utils.checkNullString(CONFIG.loveGolemName);
        CONFIG.megaSpudName = Utils.checkNullString(CONFIG.megaSpudName);
        CONFIG.moonCowName = Utils.checkNullString(CONFIG.moonCowName);
        CONFIG.nerdCreeperName = Utils.checkNullString(CONFIG.nerdCreeperName);
        CONFIG.pinkWitherName = Utils.checkNullString(CONFIG.pinkWitherName);
        CONFIG.plaguewhaleSlabName = Utils.checkNullString(CONFIG.plaguewhaleSlabName);
        CONFIG.poisonousPotatoZombieName = Utils.checkNullString(CONFIG.poisonousPotatoZombieName);
        CONFIG.rayTracingName = Utils.checkNullString(CONFIG.rayTracingName);
        CONFIG.redstoneBugName = Utils.checkNullString(CONFIG.redstoneBugName);
        CONFIG.smilingCreeperName = Utils.checkNullString(CONFIG.smilingCreeperName);
        CONFIG.toxfinSlabName = Utils.checkNullString(CONFIG.toxfinSlabName);
        CONFIG.potatoHuskName = Utils.checkNullString(CONFIG.potatoHuskName);

        if (CONFIG.headSkin == null && Minecraft.getInstance().player != null) {
            CONFIG.headSkin = Minecraft.getInstance().player.getName().getString();
        }
        CONFIG.headName = Utils.checkNullString(CONFIG.headName);

        CONFIG.traitorName = Utils.checkNullString(CONFIG.traitorName);
        CONFIG.traitorSkin = Utils.checkNullString(CONFIG.traitorSkin, "plains");

        CONFIG.dumboOctopusName = Utils.checkNullString(CONFIG.dumboOctopusName);
        CONFIG.dumboOctopusSkin = Utils.checkNullString(CONFIG.dumboOctopusSkin, "yellow");

        CONFIG.koiName = Utils.checkNullString(CONFIG.koiName);
        CONFIG.stingrayName = Utils.checkNullString(CONFIG.stingrayName);

        CONFIG.headSkin = Utils.checkNullString(CONFIG.headSkin, "downloadableduck");

        CONFIG.sulfurCubeName = Utils.checkNullString(CONFIG.sulfurCubeName);
        CONFIG.sulfurCubeSkin = Utils.checkNullString(CONFIG.sulfurCubeSkin, "air");
    }

    /**
     * Creates the command that allows the user to use {@code /teleportpet}.
     */
    public void createPetTeleportCommand(Object obj) {
        CommandDispatcher dispatcher = (CommandDispatcher) obj;
        dispatcher.register(LiteralArgumentBuilder.literal("teleportpet").executes((context) -> {
            Minecraft.getInstance().execute(() -> {
                despawnPet();
                Player player = Minecraft.getInstance().player;
                NetworkManager.get().broadcastTeleportPet(player.getStringUUID(), player.getX(), player.getY(), player.getZ());
                summonPet();
            });
            return 1;
        }));
    }

    /**
     * Creates the command that allows the user to use {@code /petspecies}.
     */
    public void createPetSpeciesCommand(Object obj) {
        CommandDispatcher dispatcher = (CommandDispatcher) obj;
        dispatcher.register(LiteralArgumentBuilder.literal("petspecies").then(RequiredArgumentBuilder.argument("species", StringArgumentType.greedyString()).suggests(PETS).executes((context) -> {
            Minecraft.getInstance().execute(() -> {
                boolean isValid = true;
                String species = StringArgumentType.getString(context, "species");

                if (Objects.equals(species, "duck")) {
                    Utils.setActivePet(duck, "duck");
                } else if (Objects.equals(species, "racoon")) {
                    Utils.setActivePet(racoon, "racoon");
                } else if (Objects.equals(species, "penguin")) {
                    Utils.setActivePet(penguin, "penguin");
                } else if (Objects.equals(species, "sheep")) {
                    Utils.setActivePet(sheep, "sheep");
                } else if (Objects.equals(species, "cat")) {
                    Utils.setActivePet(cat, "cat");
                } else if (Objects.equals(species, "allay")) {
                    Utils.setActivePet(allay, "allay");
                } else if (Objects.equals(species, "armadillo")) {
                    Utils.setActivePet(armadillo, "armadillo");
                } else if (Objects.equals(species, "axolotl")) {
                    Utils.setActivePet(axolotl, "axolotl");
                } else if (Objects.equals(species, "bat")) {
                    Utils.setActivePet(bat, "bat");
                } else if (Objects.equals(species, "camel")) {
                    Utils.setActivePet(camel, "camel");
                } else if (Objects.equals(species, "chicken")) {
                    Utils.setActivePet(chicken, "chicken");
                } else if (Objects.equals(species, "cod")) {
                    Utils.setActivePet(cod, "cod");
                } else if (Objects.equals(species, "copper_golem") || Objects.equals(species, "copper golem")) {
                    Utils.setActivePet(copperGolem, "copper_golem");
                } else if (Objects.equals(species, "cow")) {
                    Utils.setActivePet(cow, "cow");
                } else if (Objects.equals(species, "donkey")) {
                    Utils.setActivePet(donkey, "donkey");
                } else if (Objects.equals(species, "frog")) {
                    Utils.setActivePet(frog, "frog");
                } else if (Objects.equals(species, "horse")) {
                    Utils.setActivePet(horse, "horse");
                } else if (Objects.equals(species, "mooshroom")) {
                    Utils.setActivePet(mooshroom, "mooshroom");
                } else if (Objects.equals(species, "parrot")) {
                    Utils.setActivePet(parrot, "parrot");
                } else if (Objects.equals(species, "pig")) {
                    Utils.setActivePet(pig, "pig");
                } else if (Objects.equals(species, "rabbit")) {
                    Utils.setActivePet(rabbit, "rabbit");
                } else if (Objects.equals(species, "salmon")) {
                    Utils.setActivePet(salmon, "salmon");
                } else if (Objects.equals(species, "sniffer")) {
                    Utils.setActivePet(sniffer, "sniffer");
                } else if (Objects.equals(species, "snow_golem") || Objects.equals(species, "snow golem")) {
                    Utils.setActivePet(snowGolem, "snow_golem");
                } else if (Objects.equals(species, "squid")) {
                    Utils.setActivePet(squid, "squid");
                } else if (Objects.equals(species, "strider")) {
                    Utils.setActivePet(strider, "strider");
                } else if (Objects.equals(species, "tadpole")) {
                    Utils.setActivePet(tadpole, "tadpole");
                } else if (Objects.equals(species, "turtle")) {
                    Utils.setActivePet(turtle, "turtle");
                } else if (Objects.equals(species, "villager")) {
                    Utils.setActivePet(villager, "villager");
                } else if (Objects.equals(species, "wandering_trader") || Objects.equals(species, "wandering trader")) {
                    Utils.setActivePet(wanderingTrader, "wandering_trader");
                } else if (Objects.equals(species, "bee")) {
                    Utils.setActivePet(bee, "bee");
                } else if (Objects.equals(species, "cave_spider") || Objects.equals(species, "cave spider")) {
                    Utils.setActivePet(caveSpider, "cave_spider");
                } else if (Objects.equals(species, "dolphin")) {
                    Utils.setActivePet(dolphin, "dolphin");
                } else if (Objects.equals(species, "enderman")) {
                    Utils.setActivePet(enderman, "enderman");
                } else if (Objects.equals(species, "fox")) {
                    Utils.setActivePet(fox, "fox");
                } else if (Objects.equals(species, "goat")) {
                    Utils.setActivePet(goat, "goat");
                } else if (Objects.equals(species, "iron_golem") || Objects.equals(species, "iron golem")) {
                    Utils.setActivePet(ironGolem, "iron_golem");
                } else if (Objects.equals(species, "llama")) {
                    Utils.setActivePet(llama, "llama");
                } else if (Objects.equals(species, "nautilus")) {
                    Utils.setActivePet(nautilus, "nautilus");
                } else if (Objects.equals(species, "panda")) {
                    Utils.setActivePet(panda, "panda");
                } else if (Objects.equals(species, "piglin")) {
                    Utils.setActivePet(piglin, "piglin");
                } else if (Objects.equals(species, "polar_bear") || Objects.equals(species, "polar bear")) {
                    Utils.setActivePet(polarBear, "polar_bear");
                } else if (Objects.equals(species, "pufferfish")) {
                    Utils.setActivePet(pufferFish, "pufferfish");
                } else if (Objects.equals(species, "spider")) {
                    Utils.setActivePet(spider, "spider");
                } else if (Objects.equals(species, "wolf")) {
                    Utils.setActivePet(wolf, "wolf");
                } else if (Objects.equals(species, "blaze")) {
                    Utils.setActivePet(blaze, "blaze");
                } else if (Objects.equals(species, "breeze")) {
                    Utils.setActivePet(breeze, "breeze");
                } else if (Objects.equals(species, "creaking")) {
                    Utils.setActivePet(creaking, "creaking");
                } else if (Objects.equals(species, "creeper")) {
                    Utils.setActivePet(creeper, "creeper");
                } else if (Objects.equals(species, "elder_guardian") || Objects.equals(species, "elder guardian")) {
                    Utils.setActivePet(elderGuardian, "elder_guardian");
                } else if (Objects.equals(species, "endermite")) {
                    Utils.setActivePet(endermite, "endermite");
                } else if (Objects.equals(species, "evoker")) {
                    Utils.setActivePet(evoker, "evoker");
                } else if (Objects.equals(species, "ghast")) {
                    Utils.setActivePet(ghast, "ghast");
                } else if (Objects.equals(species, "happy_ghast") || Objects.equals(species, "happy ghast")) {
                    Utils.setActivePet(happyGhast, "happy_ghast");
                } else if (Objects.equals(species, "guardian")) {
                    Utils.setActivePet(guardian, "guardian");
                } else if (Objects.equals(species, "hoglin")) {
                    Utils.setActivePet(hoglin, "hoglin");
                } else if (Objects.equals(species, "magma_cube") || Objects.equals(species, "magma cube")) {
                    Utils.setActivePet(magmaCube, "magma_cube");
                } else if (Objects.equals(species, "phantom")) {
                    Utils.setActivePet(phantom, "phantom");
                } else if (Objects.equals(species, "pillager")) {
                    Utils.setActivePet(pillager, "pillager");
                } else if (Objects.equals(species, "ravager")) {
                    Utils.setActivePet(ravager, "ravager");
                } else if (Objects.equals(species, "shulker")) {
                    Utils.setActivePet(shulker, "shulker");
                } else if (Objects.equals(species, "silverfish")) {
                    Utils.setActivePet(silverfish, "silverfish");
                } else if (Objects.equals(species, "skeleton")) {
                    Utils.setActivePet(skeleton, "skeleton");
                } else if (Objects.equals(species, "slime")) {
                    Utils.setActivePet(slime, "slime");
                } else if (Objects.equals(species, "vex")) {
                    Utils.setActivePet(vex, "vex");
                } else if (Objects.equals(species, "vindicator")) {
                    Utils.setActivePet(vindicator, "vindicator");
                } else if (Objects.equals(species, "warden")) {
                    Utils.setActivePet(warden, "warden");
                } else if (Objects.equals(species, "witch")) {
                    Utils.setActivePet(witch, "witch");
                } else if (Objects.equals(species, "zombie")) {
                    Utils.setActivePet(zombie, "zombie");
                } else if (Objects.equals(species, "zombie_villager") || Objects.equals(species, "zombie villager")) {
                    Utils.setActivePet(zombieVillager, "zombie_villager");
                } else if (Objects.equals(species, "husk")) {
                    Utils.setActivePet(husk, "husk");
                } else if (Objects.equals(species, "drowned")) {
                    Utils.setActivePet(drowned, "drowned");
                } else if (Objects.equals(species, "bogged")) {
                    Utils.setActivePet(bogged, "bogged");
                } else if (Objects.equals(species, "parched")) {
                    Utils.setActivePet(parched, "parched");
                } else if (Objects.equals(species, "stray")) {
                    Utils.setActivePet(stray, "stray");
                } else if (Objects.equals(species, "wither_skeleton") || Objects.equals(species, "wither skeleton")) {
                    Utils.setActivePet(witherSkeleton, "wither_skeleton");
                } else if (Objects.equals(species, "wither")) {
                    Utils.setActivePet(wither, "wither");
                } else if (Objects.equals(species, "ender dragon") || Objects.equals(species, "ender_dragon")) {
                    Utils.setActivePet(enderDragon, "ender_dragon");
                } else if (Objects.equals(species, "angry_ghast") || Objects.equals(species, "angry ghast")) {
                    Utils.setActivePet(angryGhast, "angry_ghast");
                } else if (Objects.equals(species, "batato")) {
                    Utils.setActivePet(batato, "batato");
                } else if (Objects.equals(species, "diamond_chicken") || Objects.equals(species, "diamond chicken")) {
                    Utils.setActivePet(diamondChicken, "diamond_chicken");
                } else if (Objects.equals(species, "love_golem") || Objects.equals(species, "love golem")) {
                    Utils.setActivePet(loveGolem, "love_golem");
                } else if (Objects.equals(species, "mega_spud") || Objects.equals(species, "mega spud")) {
                    Utils.setActivePet(megaSpud, "mega_spud");
                } else if (Objects.equals(species, "moon_cow") || Objects.equals(species, "moon cow")) {
                    Utils.setActivePet(moonCow, "moon_cow");
                } else if (Objects.equals(species, "nerd_creeper") || Objects.equals(species, "nerd creeper")) {
                    Utils.setActivePet(nerdCreeper, "nerd_creeper");
                } else if (Objects.equals(species, "pink_wither") || Objects.equals(species, "pink wither")) {
                    Utils.setActivePet(pinkWither, "pink_wither");
                } else if (Objects.equals(species, "plaguewhale_slab") || Objects.equals(species, "plaguewhale slab")) {
                    Utils.setActivePet(plaguewhaleSlab, "plaguewhale_slab");
                } else if (Objects.equals(species, "poisonous_potato_zombie") || Objects.equals(species, "poisonous potato zombie")) {
                    Utils.setActivePet(poisonousPotatoZombie, "poisonous_potato_zombie");
                } else if (Objects.equals(species, "ray_tracing") || Objects.equals(species, "ray tracing")) {
                    Utils.setActivePet(rayTracing, "ray_tracing");
                } else if (Objects.equals(species, "redstone_bug") || Objects.equals(species, "redstone bug")) {
                    Utils.setActivePet(redstoneBug, "redstone_bug");
                } else if (Objects.equals(species, "smiling_creeper") || Objects.equals(species, "smiling creeper")) {
                    Utils.setActivePet(smilingCreeper, "smiling_creeper");
                } else if (Objects.equals(species, "toxifin_slab") || Objects.equals(species, "toxifin slab")) {
                    Utils.setActivePet(toxifinSlab, "toxifin_slab");
                } else if (Objects.equals(species, "potato_husk") || Objects.equals(species, "potato husk")) {
                    Utils.setActivePet(potatoHusk, "potato_husk");
                } else if (Objects.equals(species, "head")) {
                    Utils.setActivePet(head, "head");
                } else if (Objects.equals(species, "traitor")) {
                    Utils.setActivePet(traitor, "traitor");
                } else if (Objects.equals(species, "dumbo_octopus") || Objects.equals(species, "dumbo octopus")) {
                    Utils.setActivePet(dumboOctopus, "dumbo_octopus");
                } else if (Objects.equals(species, "koi")) {
                    Utils.setActivePet(koi, "koi");
                } else if (Objects.equals(species, "stingray")) {
                    Utils.setActivePet(stingray, "stingray");
                } else if (Objects.equals(species, "sulfur cube") || Objects.equals(species, "sulfur_cube")) {
                    Utils.setActivePet(sulfurCube, "sulfur_cube");
                } else {
                    isValid = false;
                }

                this.checkValidPet(isValid, context, species);

                AutoConfig.getConfigHolder(PetsConfig.class).save();
                updateSuggestions(Minecraft.getInstance());
            });
            return 1;
        })));
    }

    /**
     * Creates the {@code END_CLIENT_TICK} event, which monitors a couple things:
     * - If the user's pet name is not matching the name declared in the config (via {@link #refreshPetNames()})
     * - If the user's pet preference is set to {@code on} but no pet exists in the world, and vice versa
     * - Generates a random number for {@link #petSkin}, which used to be used for <a href="https://modrinth.com/mod/pets-natural">Pets Natural</a> and <a href="https://modrinth.com/mod/duck--mod">DuckMod</a>.
     */
    public static void createTickWatcher() {
        //ClientTickEvents.END_CLIENT_TICK.register((client) -> client.execute(() -> {
        //++this.i;
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;
        petSkin = (int) (Math.random() * (double) 3.0F);
        if (minecraft.player != null && CONFIG.petOn && summonedEntity.isEmpty()) {
            summonPet();
        }

        if (!CONFIG.petOn && !summonedEntity.isEmpty()) {
            assert world != null;

            despawnPet();
            summonedEntity.clear();
        }
        refreshPetNames();
        if (PetsClientInitializer.keyMapping.consumeClick()) {
            minecraft.gui.setScreen(new PetsConfigScreen());
        }
    }
    public void createPetHelpCommand(Object obj) {
        CommandDispatcher dispatcher = (CommandDispatcher) obj;
        dispatcher.register(LiteralArgumentBuilder.literal("pethelp").executes(context -> {
        Minecraft.getInstance().execute(() -> {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("""
                    §b[PetsMod] §aPossible commands:\
                    
                    §a/pethelp: §rdisplays a list of commands\
                    
                    §a/pet <on/off> §rtoggles whether your pet will appear or not\
                    
                    §a/petspecies <species>: §rchanges the species of your pet\
                    
                    §a/petskin <skin>: §rchanges the skin of your selected pet\
                    
                    §a/teleportpet: §rteleports your pet to you. will not work if you are not on the ground.\
                    
                    §a/petname: §rchanges the name of your currently selected pet\
                    
                    """));
        });
        return 1;
        }));
    }

    /**
     * Creates the command that allows the user to change their pet's name.
     */
    public void createPetNameCommand(Object obj) {
        CommandDispatcher dispatcher = (CommandDispatcher) obj;
        dispatcher.register(LiteralArgumentBuilder.literal("petname").then(RequiredArgumentBuilder.argument("name", StringArgumentType.greedyString()).executes((context) -> {
            Minecraft.getInstance().execute(() -> {
                String name = StringArgumentType.getString(context, "name");
            if (!summonedEntity.isEmpty()) {
                Utils.setActivePetName(name);
            }
                AutoConfig.getConfigHolder(PetsConfig.class).save();
            });
            return 1;
        })));
    }

    /**
     * Creates the command that allows the user to toggle their pet on and off.
     */
    public void createToggleCommand(Object obj) {
        CommandDispatcher dispatcher = (CommandDispatcher) obj;
        dispatcher.register(LiteralArgumentBuilder.literal("pet").then(RequiredArgumentBuilder.argument("preference", StringArgumentType.string()).suggests(SuggestionProviders.cast(ON_OFF)).executes((context) -> {
            String preference = StringArgumentType.getString(context, "preference");
            LocalPlayer player = Minecraft.getInstance().player;
            String uuid = player.getStringUUID();
            if (Objects.equals(preference, "off")) {
                CONFIG.petOn = false;
                player.sendSystemMessage(Component.literal("§b[PetsMod] §7Pet §coff."));
                AutoConfig.getConfigHolder(PetsConfig.class).save();
                NetworkManager.get().broadcastTogglePet(uuid, Utils.getActivePetName(), CONFIG.petOn);
            } else if (Objects.equals(preference, "on")) {
                CONFIG.petOn = true;
                AutoConfig.getConfigHolder(PetsConfig.class).save();
                player.sendSystemMessage(Component.literal("§b[PetsMod] §7Pet §aon."));
                NetworkManager.get().broadcastTogglePet(uuid, Utils.getActivePetName(), CONFIG.petOn);
            } else {
                player.sendSystemMessage(Component.literal("§b[PetsMod] §c§lUnknown value " + preference + "! Possible values: §r§aon, §6off"));
            }

            return 1;
        })));
    }

    /**
     * Clears the summon entities when the player joins a world so they are re-summoned
     */
    public static void createJoinHandler() {

        //ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            List var10001 = summonedEntity;
            Objects.requireNonNull(var10001);
            Minecraft.getInstance().execute(var10001::clear);
        //});
    }

    public static void createPetsList() {
        String[] stuffs = new String[]{"allay", "angry ghast", "armadillo",
                "axolotl", "bat", "batato", "bee", "blaze", "bogged",
                "breeze", "camel", "cat", "cave spider", "chicken",
                 "cod",  "copper golem", "cow",
                "creaking", "creeper", "diamond chicken",
                "dolphin", "donkey", "drowned", "duck", "dumbo octopus",
                "elder guardian", "ender dragon", "enderman", "endermite", "evoker",
                "fox",  "frog",
                 "ghast", "goat", "guardian",
                "happy ghast", "head", "hoglin",  "horse",
                "husk",  "iron golem",
                "koi", "llama",
                 "love golem", "magma cube", "mega spud",
                "moon cow", "mooshroom",
                 "nautilus", "nerd creeper",
                "panda", "parched", "parrot",  "penguin", "phantom",
               "pig", "piglin", "pillager",
                "pink wither", "plaguewhale slab", "poisonous potato zombie", "polar bear",
                "potato husk", "pufferfish", "rabbit",
                "racoon",
                "ravager",
                "ray tracing",
                "redstone bug",
                "salmon",
                "sheep",
                "shulker",
                "silverfish", "skeleton", "slime", "smiling creeper", "sniffer", "snow golem",
                 "spider", "squid", "stingray",  "stray", "strider",  "tadpole", "toxifin slab",
                "traitor", "turtle",
                 "vex", "villager", "vindicator", "wandering trader", "warden", "witch", "wither",
                "wither skeleton", "wolf", "zombie", "zombie villager", "sulfur cube"};
        PETS_LIST.addAll(List.of(stuffs));
    }

    public void checkValidPet(boolean isValid, CommandContext context, String species) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (!isValid) {
            player.sendSystemMessage(Component.literal("§b[PetsMod] §cThat's not a pet that's currently supported. Try something else. (Unknown input \"" + species + "\")"));
        } else if (isValid && CONFIG.petOn) {
            despawnPet();
            player.sendSystemMessage(Component.literal("§b[PetsMod] §aYour active pet has been switched to " + CONFIG.activePet.replace("_", " ") + "."));
            NetworkManager.get().broadcastGeneral(Minecraft.getInstance().player.getStringUUID(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
            summonPet();
        } else if (isValid && !CONFIG.petOn) {
            player.sendSystemMessage(Component.literal("§b[PetsMod] §cYour pet has been switched to " + CONFIG.activePet.replace("_", " ") + ", but you currently do not have your pet enabled. Run §l/pet on§r§c to change this."));
        }
    }
}
