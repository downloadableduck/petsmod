/**
 * so this file got a little messed up and I had to restore it from an earlier save, but that was
 * inside of a JAR, so this was re-created from the de-compiled bytecode, which is why it is
 * a little wierd.
 */

package com.jeff.pets.client;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.mixin.client.ChatAccessor;
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
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.RootCommandNode;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.command.ClientSuggestionProvider;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.source.CommandSourceStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is the "central" of the logic that despawns, spawns, and swaps out pets, as well as creating all of the commands.
 * It contains important logic and methods to manage the pets, additionally
 * implementing {@link ClientModInitializer}
 * to run a large amount of logic in the {@link #onInitializeClient()} method.
 * <p>
 *
 * @see com.jeff.pets.Utils
 */
public class Central implements ClientModInitializer {

    public static final List<Entity> summonedEntity = new ArrayList();
    public static final CopyOnWriteArrayList<String> currentSuggestions = new CopyOnWriteArrayList();
    public static final List<String> BEE_SKINS = ImmutableList.of("happy", "angry");
    public static final List<String> FOX_SKINS = ImmutableList.of("red", "snow");
    public static final List<String> LLAMA_SKINS = ImmutableList.of("brown", "creamy", "gray", "white");
    public static final List<String> NAUTILUS_SKINS = ImmutableList.of("nautilus", "zombie", "coral zombie");
    public static final List<String> PANDA_SKINS = ImmutableList.of("normal", "lazy", "agressive", "worried", "playful", "weak", "brown");
    public static final List<String> PIGLIN_SKINS = ImmutableList.of("piglin", "zombified", "brute");
    //public static final List<String> WOLF_SKINS = ImmutableList.of("pale", "ashen", "black", "chestnut", "rusty", "snowy", "spotted", "striped", "woods");
    public static final List<String> PETS_LIST = new ArrayList<>();
    public static final SuggestionProvider<Object> PETS = (context, builder) ->
            net.minecraft.command.SuggestionProvider.suggestMatching(PETS_LIST, builder);
    private static final SuggestionProvider<Object> ON_OFF = (context, builder) -> net.minecraft.command.SuggestionProvider.suggestMatching(new String[]{"off", "on"}, builder);
    private static final List<String> DUCK_SKINS = ImmutableList.of("mallard", "pekin", "rubber", "bronze");
    private static final List<String> CAT_SKINS = ImmutableList.of("black", "tuxedo", "british shorthair", "calico", "jellie", "ocelot", "persian", "ragdoll", "red", "siamese", "tabby", "white");
    private static final List<String> AXOLOTL_SKINS = ImmutableList.of("pink", "brown", "gold", "cyan", "blue");
    private static final List<String> CAMEL_SKINS = ImmutableList.of("camel", "husk");
    private static final List<String> TEMPERATE_COLD_WARM = ImmutableList.of("temperate", "cold", "warm");
    private static final List<String> COPPER_GOLEM_SKINS = ImmutableList.of("unoxidized", "exposed", "weathered", "oxidized");
    private static final List<String> HORSE_SKINS = ImmutableList.of("white", "creamy", "chestnut", "brown", "black", "gray", "dark_brown", "zombie", "skeleton");
    private static final List<String> PARROT_SKINS = ImmutableList.of("red", "blue", "green", "cyan", "gray");
    private static final List<String> RABBIT_SKINS = ImmutableList.of("brown", "white", "black", "splotched", "gold", "salt", "killer", "toast");
    private static final List<String> SHEEP_SKINS = ImmutableList.of("white", "orange", "magenta", "light blue", "yellow", "lime", "pink", "gray", "light gray", "cyan", "purple", "blue", "brown", "green", "red", "black");
    private static final List<String> SNOW_GOLEM_KINS = ImmutableList.of("pumpkin on", "pumpkin off");
    private static final List<String> SQUID_SKINS = ImmutableList.of("squid", "glow squid");
    private static final List<String> STRIDER_SKINS = ImmutableList.of("warm", "cold");
    private static final List<String> VILLAGER_SKINS = ImmutableList.of("farmer", "fisherman", "shepherd", "fletcher", "cleric", "weaponsmith", "armorer", "toolsmith", "librarian", "cartographer", "leatherworker", "butcher", "mason", "nitwit", "unemployed");
    private static final List<String> HOGLIN_SKINS = ImmutableList.of("hoglin", "zoglin");
    private static final List<String> SLIME_LIKE_SKINS = ImmutableList.of("small", "medium", "large");
    private static final List<String> RACOON_SKINS = ImmutableList.of("normal", "albino");
    private static final List<String> CREEPER_SKINS = ImmutableList.of("normal", "charged");
    private static final List<String> SHULKER_SKINS = ImmutableList.of("normal", "black", "brown", "cyan", "gray", "green", "light blue", "light gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
    private static final List<String> WITHER_SKINS = ImmutableList.of("normal", "invulnerable");
    private static final List<String> HEAD_SKINS = ImmutableList.of("Use any player's name here.");
    private static final List<String> TRAITOR_SKINS = ImmutableList.of("desert", "jungle", "plains", "savanna", "snowy", "swamp", "taiga");
    private static final List<String> DUMBO_OCTOPUS_SKINS = ImmutableList.of("yellow", "red", "blue", "green", "orange", "pink");
    private static final List<String> EMPTY_LIST = ImmutableList.of();
    public static PetsConfig CONFIG;
    public static int petSkin;
    public static Duck duck;
    public static Racoon racoon;
    public static Penguin penguin;
    public static ClientSheep sheep;
    public static ClientCat cat;
    public static ClientBat bat;
    public static ClientChicken chicken;
    public static ClientCod cod;
    public static ClientCow cow;
    public static ClientDonkey donkey;
    public static ClientHorse horse;
    public static ClientMooshroom mooshroom;
    public static ClientParrot parrot;
    public static ClientPig pig;
    public static ClientRabbit rabbit;
    public static ClientSalmon salmon;
    public static ClientSnowGolem snowGolem;
    public static ClientSquid squid;
    public static ClientTurtle turtle;
    public static ClientVillager villager;
    public static ClientCaveSpider caveSpider;
    public static ClientDolphin dolphin;
    public static ClientEnderman enderman;
    public static ClientIronGolem ironGolem;
    public static ClientLlama llama;
    public static ClientPolarBear polarBear;
    public static ClientPufferFish pufferFish;
    public static ClientSpider spider;
    public static ClientWolf wolf;
    public static ClientBlaze blaze;
    public static ClientCreeper creeper;
    public static ClientElderGuardian elderGuardian;
    public static ClientEndermite endermite;
    public static ClientEvoker evoker;
    public static ClientGhast ghast;
    public static ClientGuardian guardian;
    public static ClientMagmaCube magmaCube;
    public static ClientPhantom phantom;
    public static ClientShulker shulker;
    public static ClientSilverfish silverfish;
    public static ClientSkeleton skeleton;
    public static ClientSlime slime;
    public static ClientVex vex;
    public static ClientVindicator vindicator;
    public static ClientWitch witch;
    public static ClientZombie zombie;
    public static ClientZombieVillager zombieVillager;
    public static ClientHusk husk;
    public static ClientDrowned drowned;
    public static ClientStray stray;
    public static ClientWitherSkeleton witherSkeleton;
    public static ClientEnderDragon enderDragon;
    public static ClientWither wither;
    public static Head head;
    public static DumboOctopus dumboOctopus;
    public static Koi koi;
    public static Stingray stingray;
    public static ClientZombiePigman zombiePigman;

    private final SuggestionProvider<Object> SKINS = (context, builder) -> {
        String remaining = builder.getRemaining().toLowerCase();

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
        Utils.despawnEntity(bat);
        Utils.despawnEntity(chicken);
        Utils.despawnEntity(cod);
        Utils.despawnEntity(cow);
        Utils.despawnEntity(donkey);
        Utils.despawnEntity(horse);
        Utils.despawnEntity(mooshroom);
        Utils.despawnEntity(parrot);
        Utils.despawnEntity(pig);
        Utils.despawnEntity(rabbit);
        Utils.despawnEntity(salmon);
        Utils.despawnEntity(snowGolem);
        Utils.despawnEntity(squid);
        Utils.despawnEntity(turtle);
        Utils.despawnEntity(villager);
        Utils.despawnEntity(caveSpider);
        Utils.despawnEntity(dolphin);
        Utils.despawnEntity(enderman);
        Utils.despawnEntity(ironGolem);
        Utils.despawnEntity(llama);
        Utils.despawnEntity(polarBear);
        Utils.despawnEntity(pufferFish);
        Utils.despawnEntity(spider);
        Utils.despawnEntity(wolf);
        Utils.despawnEntity(blaze);
        Utils.despawnEntity(creeper);
        Utils.despawnEntity(elderGuardian);
        Utils.despawnEntity(endermite);
        Utils.despawnEntity(evoker);
        Utils.despawnEntity(ghast);
        Utils.despawnEntity(guardian);
        Utils.despawnEntity(magmaCube);
        Utils.despawnEntity(phantom);
        Utils.despawnEntity(shulker);
        Utils.despawnEntity(silverfish);
        Utils.despawnEntity(skeleton);
        Utils.despawnEntity(slime);
        Utils.despawnEntity(vex);
        Utils.despawnEntity(vindicator);
        Utils.despawnEntity(witch);
        Utils.despawnEntity(zombie);
        Utils.despawnEntity(zombieVillager);
        Utils.despawnEntity(husk);
        Utils.despawnEntity(drowned);
        Utils.despawnEntity(stray);
        Utils.despawnEntity(witherSkeleton);
        Utils.despawnEntity(enderDragon);
        Utils.despawnEntity(wither);
        Utils.despawnEntity(head);
        Utils.despawnEntity(dumboOctopus);
        Utils.despawnEntity(koi);
        Utils.despawnEntity(stingray);
        Utils.despawnEntity(zombiePigman);
    }

    /**
     * Controls the base logic for how and when to summon the pets. For the helper methods used,
     * see {@link Utils#summonPet}
     */
    public static void summonPet() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientWorld world = minecraft.world;
        duck = new Duck(PetsInitializer.DUCK, world);
        racoon = new Racoon(PetsInitializer.RACOON, world);
        penguin = new Penguin(PetsInitializer.PENGUIN, world);
        sheep = new ClientSheep(PetsInitializer.SHEEP, world);
        cat = new ClientCat(PetsInitializer.CAT, world);
        bat = new ClientBat(PetsInitializer.BAT, world);
        chicken = new ClientChicken(PetsInitializer.CHICKEN, world);
        cod = new ClientCod(PetsInitializer.COD, world);
        cow = new ClientCow(PetsInitializer.COW, world);
        donkey = new ClientDonkey(PetsInitializer.DONKEY, world);
        horse = new ClientHorse(PetsInitializer.HORSE, world);
        mooshroom = new ClientMooshroom(PetsInitializer.MOOSHROOM, world);
        parrot = new ClientParrot(PetsInitializer.PARROT, world);
        pig = new ClientPig(PetsInitializer.PIG, world);
        rabbit = new ClientRabbit(PetsInitializer.RABBIT, world);
        salmon = new ClientSalmon(PetsInitializer.SALMON, world);
        snowGolem = new ClientSnowGolem(PetsInitializer.SNOW_GOLEM, world);
        squid = new ClientSquid(PetsInitializer.SQUID, world);
        turtle = new ClientTurtle(PetsInitializer.TURTLE, world);
        villager = new ClientVillager(PetsInitializer.VILLAGER, world);
        caveSpider = new ClientCaveSpider(PetsInitializer.CAVE_SPIDER, world);
        dolphin = new ClientDolphin(PetsInitializer.DOLPHIN, world);
        enderman = new ClientEnderman(PetsInitializer.ENDERMAN, world);
        ironGolem = new ClientIronGolem(PetsInitializer.IRON_GOLEM, world);
        llama = new ClientLlama(PetsInitializer.LLAMA, world);
        polarBear = new ClientPolarBear(PetsInitializer.POLAR_BEAR, world);
        pufferFish = new ClientPufferFish(PetsInitializer.PUFFERFISH, world);
        spider = new ClientSpider(PetsInitializer.SPIDER, world);
        wolf = new ClientWolf(PetsInitializer.WOLF, world);
        blaze = new ClientBlaze(PetsInitializer.BLAZE, world);
        creeper = new ClientCreeper(PetsInitializer.CREEPER, world);
        elderGuardian = new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE, world);
        endermite = new ClientEndermite(PetsInitializer.ENDERMITE, world);
        evoker = new ClientEvoker(PetsInitializer.EVOKER, world);
        ghast = new ClientGhast(PetsInitializer.GHAST, world);
        guardian = new ClientGuardian(PetsInitializer.GUARDIAN, world);
        magmaCube = new ClientMagmaCube(PetsInitializer.MAGMA_CUBE, world);
        phantom = new ClientPhantom(PetsInitializer.PHANTOM, world);
        shulker = new ClientShulker(PetsInitializer.SHULKER, world);
        silverfish = new ClientSilverfish(PetsInitializer.SILVERFISH, world);
        skeleton = new ClientSkeleton(PetsInitializer.SKELETON, world);
        slime = new ClientSlime(PetsInitializer.SLIME, world);
        vex = new ClientVex(PetsInitializer.VEX, world);
        vindicator = new ClientVindicator(PetsInitializer.VINDICATOR, world);
        witch = new ClientWitch(PetsInitializer.WITCH, world);
        zombie = new ClientZombie(PetsInitializer.ZOMBIE, world);
        zombieVillager = new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER, world);
        husk = new ClientHusk(PetsInitializer.HUSK, world);
        drowned = new ClientDrowned(PetsInitializer.DROWNED, world);
        stray = new ClientStray(PetsInitializer.STRAY, world);
        witherSkeleton = new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON, world);
        enderDragon = new ClientEnderDragon(PetsInitializer.ENDER_DRAGON, world);
        wither = new ClientWither(PetsInitializer.WITHER, world);
        head = new Head(PetsInitializer.HEAD, world);
        dumboOctopus = new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS, world);
        koi = new Koi(PetsInitializer.KOI, world);
        stingray = new Stingray(PetsInitializer.STINGRAY, world);
        zombiePigman = new ClientZombiePigman(PetsInitializer.ZOMBIE_PIGMAN, world);

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
            } else if (Objects.equals(CONFIG.activePet, "bat")) {
                Utils.summonPet(bat, CONFIG.batName);
            } else if (Objects.equals(CONFIG.activePet, "chicken")) {
                Utils.summonPet(chicken, CONFIG.chickenName);
            } else if (Objects.equals(CONFIG.activePet, "cod")) {
                Utils.summonPet(cod, CONFIG.codName);
            } else if (Objects.equals(CONFIG.activePet, "cow")) {
                Utils.summonPet(cow, CONFIG.cowName);
            } else if (Objects.equals(CONFIG.activePet, "donkey")) {
                Utils.summonPet(donkey, CONFIG.donkeyName);
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
            } else if (Objects.equals(CONFIG.activePet, "snow_golem")) {
                Utils.summonPet(snowGolem, CONFIG.snowGolemName);
            } else if (Objects.equals(CONFIG.activePet, "squid")) {
                Utils.summonPet(squid, CONFIG.squidName);
            } else if (Objects.equals(CONFIG.activePet, "turtle")) {
                Utils.summonPet(turtle, CONFIG.turtleName);
            } else if (Objects.equals(CONFIG.activePet, "villager")) {
                Utils.summonPet(villager, CONFIG.villagerName);
            } else if (Objects.equals(CONFIG.activePet, "cave_spider")) {
                Utils.summonPet(caveSpider, CONFIG.caveSpiderName);
            } else if (Objects.equals(CONFIG.activePet, "dolphin")) {
                Utils.summonPet(dolphin, CONFIG.dolphinName);
            } else if (Objects.equals(CONFIG.activePet, "enderman")) {
                Utils.summonPet(enderman, CONFIG.endermanName);
            } else if (Objects.equals(CONFIG.activePet, "iron_golem")) {
                Utils.summonPet(ironGolem, CONFIG.ironGolemName);
            } else if (Objects.equals(CONFIG.activePet, "llama")) {
                Utils.summonPet(llama, CONFIG.llamaName);
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
            } else if (Objects.equals(CONFIG.activePet, "creeper")) {
                Utils.summonPet(creeper, CONFIG.creeperName);
            } else if (Objects.equals(CONFIG.activePet, "elder_guardian")) {
                Utils.summonPet(elderGuardian, CONFIG.elderGuardianName);
            } else if (Objects.equals(CONFIG.activePet, "endermite")) {
                Utils.summonPet(endermite, CONFIG.endermiteName);
            } else if (Objects.equals(CONFIG.activePet, "evoker")) {
                Utils.summonPet(evoker, CONFIG.evokerName);
            } else if (Objects.equals(CONFIG.activePet, "ghast")) {
                Utils.summonPet(ghast, CONFIG.ghastName);
            } else if (Objects.equals(CONFIG.activePet, "guardian")) {
                Utils.summonPet(guardian, CONFIG.guardianName);
            } else if (Objects.equals(CONFIG.activePet, "magma_cube")) {
                Utils.summonPet(magmaCube, CONFIG.magmaCubeName);
            } else if (Objects.equals(CONFIG.activePet, "phantom")) {
                Utils.summonPet(phantom, CONFIG.phantomName);
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
            } else if (Objects.equals(CONFIG.activePet, "stray")) {
                Utils.summonPet(stray, CONFIG.strayName);
            } else if (Objects.equals(CONFIG.activePet, "wither_skeleton")) {
                Utils.summonPet(witherSkeleton, CONFIG.witherSkeletonName);
            } else if (Objects.equals(CONFIG.activePet, "ender_dragon")) {
                Utils.summonPet(enderDragon, CONFIG.enderDragonName);
            } else if (Objects.equals(CONFIG.activePet, "wither")) {
                Utils.summonPet(wither, CONFIG.witherName);
            } else if (Objects.equals(CONFIG.activePet, "head")) {
                Utils.summonPet(head, CONFIG.headName);
            } else if (Objects.equals(CONFIG.activePet, "dumbo_octopus")) {
                Utils.summonPet(dumboOctopus, CONFIG.dumboOctopusName);
            } else if (Objects.equals(CONFIG.activePet, "koi")) {
                Utils.summonPet(koi, CONFIG.koiName);
            } else if (Objects.equals(CONFIG.activePet, "stingray")) {
                Utils.summonPet(stingray, CONFIG.stingrayName);
            } else if (Objects.equals(CONFIG.activePet, "zombie_pigman")) {
                Utils.summonPet(zombiePigman, CONFIG.zombiePigmanName);
            }
        }
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
        Utils.checkName("bat", bat, CONFIG.batName);
        Utils.checkName("chicken", chicken, CONFIG.chickenName);
        Utils.checkName("cod", cod, CONFIG.codName);
        Utils.checkName("cow", cow, CONFIG.cowName);
        Utils.checkName("donkey", donkey, CONFIG.donkeyName);
        Utils.checkName("horse", horse, CONFIG.horseName);
        Utils.checkName("mooshroom", mooshroom, CONFIG.mooshroomName);
        Utils.checkName("parrot", parrot, CONFIG.parrotName);
        Utils.checkName("pig", pig, CONFIG.pigName);
        Utils.checkName("rabbit", rabbit, CONFIG.rabbitName);
        Utils.checkName("salmon", salmon, CONFIG.salmonName);
        Utils.checkName("snow_golem", snowGolem, CONFIG.snowGolemName);
        Utils.checkName("squid", squid, CONFIG.squidName);
        Utils.checkName("turtle", turtle, CONFIG.turtleName);
        Utils.checkName("villager", villager, CONFIG.villagerName);
        Utils.checkName("cave_spider", caveSpider, CONFIG.caveSpiderName);
        Utils.checkName("dolphin", dolphin, CONFIG.dolphinName);
        Utils.checkName("enderman", enderman, CONFIG.endermanName);
        Utils.checkName("iron_golem", ironGolem, CONFIG.ironGolemName);
        Utils.checkName("llama", llama, CONFIG.llamaName);
        Utils.checkName("polar_bear", polarBear, CONFIG.polarBearName);
        Utils.checkName("pufferfish", pufferFish, CONFIG.pufferFishName);
        Utils.checkName("spider", spider, CONFIG.spiderName);
        Utils.checkName("wolf", wolf, CONFIG.wolfName);
        Utils.checkName("blaze", blaze, CONFIG.blazeName);
        Utils.checkName("creeper", creeper, CONFIG.creeperName);
        Utils.checkName("elder_guardian", elderGuardian, CONFIG.elderGuardianName);
        Utils.checkName("endermite", endermite, CONFIG.endermiteName);
        Utils.checkName("evoker", evoker, CONFIG.evokerName);
        Utils.checkName("ghast", ghast, CONFIG.ghastName);
        Utils.checkName("guardian", guardian, CONFIG.guardianName);
        Utils.checkName("magma_cube", magmaCube, CONFIG.magmaCubeName);
        Utils.checkName("phantom", phantom, CONFIG.phantomName);
        Utils.checkName("shulker", shulker, CONFIG.shulkerName);
        Utils.checkName("silverfish", silverfish, CONFIG.silverfishName);
        Utils.checkName("skeleton", skeleton, CONFIG.skeletonName);
        Utils.checkName("slime", slime, CONFIG.slimeName);
        Utils.checkName("vex", vex, CONFIG.vexName);
        Utils.checkName("vindicator", vindicator, CONFIG.vindicatorName);
        Utils.checkName("husk", husk, CONFIG.huskName);
        Utils.checkName("drowned", drowned, CONFIG.drownedName);
        Utils.checkName("stray", stray, CONFIG.strayName);
        Utils.checkName("wither_skeleton", witherSkeleton, CONFIG.witherSkeletonName);
        Utils.checkName("ender_dragon", enderDragon, CONFIG.enderDragonName);
        Utils.checkName("wither", wither, CONFIG.witherName);
        Utils.checkName("head", head, CONFIG.headName);
        Utils.checkName("dumbo_octopus", dumboOctopus, CONFIG.dumboOctopusName);
        Utils.checkName("koi", koi, CONFIG.koiName);
        Utils.checkName("stingray", stingray, CONFIG.stingrayName);
        Utils.checkName("zombie_pigman", zombiePigman, CONFIG.zombiePigmanName);
    }

    /**
     * Assigns the correct matching suggestions depending on what the current active pet is.
     *
     * @see ChatAccessor
     */
    private static void updateSuggestions(Minecraft client) {
        List<String> skinSuggestions;
        switch (CONFIG.activePet) {
            case "duck":
                skinSuggestions = DUCK_SKINS;
                break;
            case "racoon":
                skinSuggestions = RACOON_SKINS;
                break;
            case "cat":
                skinSuggestions = CAT_SKINS;
                break;
            case "axolotl":
                skinSuggestions = AXOLOTL_SKINS;
                break;
            case "camel":
                skinSuggestions = CAMEL_SKINS;
                break;
            case "frog":
                skinSuggestions = TEMPERATE_COLD_WARM;
                break;
            case "creeper":
            case "nerd_creeper":
            case "smiling_creeper":
                skinSuggestions = CREEPER_SKINS;
                break;
            case "copper_golem":
                skinSuggestions = COPPER_GOLEM_SKINS;
                break;
            case "horse":
                skinSuggestions = HORSE_SKINS;
                break;
            case "parrot":
                skinSuggestions = PARROT_SKINS;
                break;
            case "rabbit":
                skinSuggestions = RABBIT_SKINS;
                break;
            case "sheep":
                skinSuggestions = SHEEP_SKINS;
                break;
            case "snow_golem":
                skinSuggestions = SNOW_GOLEM_KINS;
                break;
            case "squid":
                skinSuggestions = SQUID_SKINS;
                break;
            case "strider":
                skinSuggestions = STRIDER_SKINS;
                break;
            case "villager":
                skinSuggestions = VILLAGER_SKINS;
                break;
            case "bee":
                skinSuggestions = BEE_SKINS;
                break;
            case "fox":
                skinSuggestions = FOX_SKINS;
                break;
            case "llama":
                skinSuggestions = LLAMA_SKINS;
                break;
            case "nautilus":
                skinSuggestions = NAUTILUS_SKINS;
                break;
            case "panda":
                skinSuggestions = PANDA_SKINS;
                break;
            case "piglin":
                skinSuggestions = PIGLIN_SKINS;
                break;
            //case "wolf" -> WOLF_SKINS;
            case "hoglin":
                skinSuggestions = HOGLIN_SKINS;
                break;
            case "magma_cube":
            case "slime":
            case "tropical_slime":
                skinSuggestions = SLIME_LIKE_SKINS;
                break;
            case "zombie_villager":
                skinSuggestions = VILLAGER_SKINS;
                break;
            case "shulker":
                skinSuggestions = SHULKER_SKINS;
                break;
            case "wither":
                skinSuggestions = WITHER_SKINS;
                break;
            case "head":
                skinSuggestions = HEAD_SKINS;
                break;
            case "traitor":
                skinSuggestions = TRAITOR_SKINS;
                break;
            case "dumbo_octopus":
                skinSuggestions = DUMBO_OCTOPUS_SKINS;
                break;
            default:
                skinSuggestions = EMPTY_LIST;
                break;
        }

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
        Screen screen = client.screen;
        if ((screen instanceof ChatScreen)) {
            ChatScreen chatScreen = (ChatScreen) screen;
            //((ChatAccessor) chatScreen).getChatInputSuggestor().refresh();
        }
    }

    /**
     * Adds the custom resourcepack required for the head into the resource pack respository.
     * More about this custom pack can be seen in {@link HeadSkin}.
     */
    public static void checkForHeadResourcePack() {
        Minecraft client = Minecraft.getInstance();
        GameOptions options = client.options;
        List<String> resourcePacks = new ArrayList<>(options.resourcePacks);

        /*if (!resourcePacks.contains("file/headpack") && Objects.equals(CONFIG.activePet, "head")) {
            resourcePacks.add("file/headpack");
            client.getResourcePackRepository().addPack("file/headpack");
            options.save();
            client.reloadResourcePacks();
            //client.player.sendSystemMessage(new LiteralText("§b[PetsMod] §aSorry for the interruption, the head pet requires a custom resource pack to work correctly and we loaded a pack for you. This will not affect anything except the head texture."));
        }*/
    }

    /**
     * Used to re-assign the logo, edition texts, and splashes in {@link SplashManagerMixin} and {@link TitleScreenRenderingMixin}.
     *
     * @param bl: Whether to re-assign the logo or use the default ones.
     */
    public static void reassignLogo(Boolean bl) {
        if (bl) {
            //LogoRenderer.MINECRAFT_LOGO = new ResourceLocation(MOD_ID, "textures/title/petsmod.png");
            //LogoRenderer.EASTER_EGG_LOGO = new ResourceLocation(MOD_ID, "textures/title/modpets.png");
            //LogoRenderer.MINECRAFT_EDITION = new ResourceLocation(MOD_ID, "textures/title/version.png");
            //SplashManager.SPLASHES_LOCATION = new ResourceLocation(MOD_ID, "texts/splashes.txt");
        } else {
            //LogoRenderer.MINECRAFT_LOGO = new ResourceLocation("minecraft", "textures/gui/title/minecraft.png");
            //LogoRenderer.EASTER_EGG_LOGO = new ResourceLocation("minecraft", "textures/gui/title/minceraft.png");
            //LogoRenderer.MINECRAFT_EDITION = new ResourceLocation("minecraft", "textures/gui/title/edition.png");
            //SplashManager.SPLASHES_LOCATION = new ResourceLocation("minecraft", "texts/splashes.txt");
        }
    }

    /**
     * Creates the {@code END_CLIENT_TICK} event, which monitors a couple things:
     * - If the user's pet name is not matching the name declared in the config (via {@link #refreshPetNames()})
     * - If the user's pet preference is set to {@code on} but no pet exists in the world, and vice versa
     * - Generates a random number for {@link #petSkin}, which used to be used for <a href="https://modrinth.com/mod/pets-natural">Pets Natural</a> and <a href="https://modrinth.com/mod/duck--mod">DuckMod</a>.
     */
    public static void createTickWatcher() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientWorld world = minecraft.world;
        minecraft.execute(() -> {
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
        });
    }

    /**
     * Clears the summon entities when the player joins a world so they are re-summoned
     */
    public static void createJoinHandler() {
        List var10001 = summonedEntity;
        Objects.requireNonNull(var10001);
        Minecraft.getInstance().execute(var10001::clear);
    }

    /**
     * Required call to {@link ClientModInitializer#onInitializeClient()} that calls the initial code.
     * <p>- Initializes all of the commands
     * <p>- Gets the default head skin
     * <p>- Registers the config
     * <p>Do NOT ever call CONFIG before it is called here or in any other {@link ClientModInitializer#onInitializeClient()}
     * implementation, as it will cause a {@code RuntimeException}.
     */
    public void onInitializeClient() {
        AutoConfig.register(PetsConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();

        this.checkForNullObjects();
        createJoinHandler();
        createTickWatcher();
        this.createPetsList();
        updateSuggestions(Minecraft.getInstance());
    }

    /**
     * Creates the command that allows users to use {@code /petskin}. Some of the code is
     * slightly malformed (specifically the {@code switch} statements) as this file was
     * re-created from an bytecode after a change messed it up around version {@code 0.6.0}
     */
    public void createPetSkinCommand(CommandDispatcher dispatcher) {
        if (Minecraft.getInstance().getNetworkHandler() != null) {
            ClientPlayNetworkHandler connection = Minecraft.getInstance().getNetworkHandler();
            RootCommandNode<net.minecraft.command.SuggestionProvider> commandRoot = connection.getCommands().getRoot();
            commandRoot.getExamples().clear();
        }

        dispatcher.register(LiteralArgumentBuilder.literal("petskin").then(RequiredArgumentBuilder.argument("skin", StringArgumentType.greedyString())
                .suggests(this.SKINS)
                .executes((context) -> {
                    Minecraft.getInstance().execute(() -> {
                    boolean isValid = true;
                    String skin = StringArgumentType.getString(context, "skin");

                    if (Objects.equals(skin, "baby")) {
                        CONFIG.isBaby = true;
                    } else if (Objects.equals(skin, "adult")) {
                        CONFIG.isBaby = false;
                    } else {
                        if (Objects.equals(CONFIG.activePet, "duck")) {
                            if (skin.equals("mallard")) {
                                CONFIG.duckSkin = "mallard";
                            } else if (skin.equals("pekin")) {
                                CONFIG.duckSkin = "pekin";
                            } else if (skin.equals("rubber")) {
                                CONFIG.duckSkin = "rubber";
                            } else if (skin.equals("bronze")) {
                                CONFIG.duckSkin = "bronze";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "racoon")) {
                            if (skin.equals("normal")) {
                                CONFIG.racoonSkin = "normal";
                            } else if (skin.equals("albino")) {
                                CONFIG.racoonSkin = "albino";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "cat")) {
                            if (skin.equals("black")) {
                                CONFIG.catSkin = "all_black";
                            } else if (skin.equals("tuxedo")) {
                                CONFIG.catSkin = "tuxedo";
                            } else if (skin.equals("tabby")) {
                                CONFIG.catSkin = "tabby";
                            } else if (skin.equals("red")) {
                                CONFIG.catSkin = "red";
                            } else if (skin.equals("siamese")) {
                                CONFIG.catSkin = "siamese";
                            } else if (skin.equals("calico")) {
                                CONFIG.catSkin = "calico";
                            } else if (skin.equals("british_shorthair") || skin.equals("british shorthair")) {
                                CONFIG.catSkin = "british_shorthair";
                            } else if (skin.equals("persian")) {
                                CONFIG.catSkin = "persian";
                            } else if (skin.equals("ragdoll")) {
                                CONFIG.catSkin = "ragdoll";
                            } else if (skin.equals("white")) {
                                CONFIG.catSkin = "white";
                            } else if (skin.equals("jellie")) {
                                CONFIG.catSkin = "jellie";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "sheep")) {
                            if (skin.equals("white")) {
                                CONFIG.sheepSkin = "white";
                            } else if (skin.equals("orange")) {
                                CONFIG.sheepSkin = "orange";
                            } else if (skin.equals("magenta")) {
                                CONFIG.sheepSkin = "magenta";
                            } else if (skin.equals("light_blue") || skin.equals("light blue")) {
                                CONFIG.sheepSkin = "light_blue";
                            } else if (skin.equals("yellow")) {
                                CONFIG.sheepSkin = "yellow";
                            } else if (skin.equals("lime")) {
                                CONFIG.sheepSkin = "lime";
                            } else if (skin.equals("pink")) {
                                CONFIG.sheepSkin = "pink";
                            } else if (skin.equals("gray")) {
                                CONFIG.sheepSkin = "gray";
                            } else if (skin.equals("light_gray") || skin.equals("light gray")) {
                                CONFIG.sheepSkin = "light_gray";
                            } else if (skin.equals("cyan")) {
                                CONFIG.sheepSkin = "cyan";
                            } else if (skin.equals("purple")) {
                                CONFIG.sheepSkin = "purple";
                            } else if (skin.equals("blue")) {
                                CONFIG.sheepSkin = "blue";
                            } else if (skin.equals("brown")) {
                                CONFIG.sheepSkin = "brown";
                            } else if (skin.equals("green")) {
                                CONFIG.sheepSkin = "green";
                            } else if (skin.equals("red")) {
                                CONFIG.sheepSkin = "red";
                            } else if (skin.equals("black")) {
                                CONFIG.sheepSkin = "black";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "chicken")) {
                            if (skin.equals("temperate")) {
                                CONFIG.chickenSkin = "temperate";
                            } else if (skin.equals("cold")) {
                                CONFIG.chickenSkin = "cold";
                            } else if (skin.equals("warm")) {
                                CONFIG.chickenSkin = "warm";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "axolotl")) {
                            if (skin.equals("pink")) {
                                CONFIG.axolotlSkin = "pink";
                            } else if (skin.equals("brown")) {
                                CONFIG.axolotlSkin = "brown";
                            } else if (skin.equals("gold")) {
                                CONFIG.axolotlSkin = "gold";
                            } else if (skin.equals("cyan")) {
                                CONFIG.axolotlSkin = "cyan";
                            } else if (skin.equals("blue")) {
                                CONFIG.axolotlSkin = "blue";
                            } else {
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
                            if (skin.equals("unoxidized")) {
                                CONFIG.copperGolemSkin = "unoxidized";
                            } else if (skin.equals("exposed")) {
                                CONFIG.copperGolemSkin = "exposed";
                            } else if (skin.equals("weathered")) {
                                CONFIG.copperGolemSkin = "weathered";
                            } else if (skin.equals("oxidized")) {
                                CONFIG.copperGolemSkin = "oxidized";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "cow")) {
                            if (skin.equals("temperate")) {
                                CONFIG.cowSkin = "temperate";
                            } else if (skin.equals("cold")) {
                                CONFIG.cowSkin = "cold";
                            } else if (skin.equals("warm")) {
                                CONFIG.cowSkin = "warm";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "frog")) {
                            if (skin.equals("temperate")) {
                                CONFIG.frogSkin = "temperate";
                            } else if (skin.equals("cold")) {
                                CONFIG.frogSkin = "cold";
                            } else if (skin.equals("warm")) {
                                CONFIG.frogSkin = "warm";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "horse")) {
                            if (skin.equals("white")) {
                                CONFIG.horseSkin = "white";
                            } else if (skin.equals("creamy")) {
                                CONFIG.horseSkin = "creamy";
                            } else if (skin.equals("chestnut")) {
                                CONFIG.horseSkin = "chestnut";
                            } else if (skin.equals("brown")) {
                                CONFIG.horseSkin = "brown";
                            } else if (skin.equals("black")) {
                                CONFIG.horseSkin = "black";
                            } else if (skin.equals("gray")) {
                                CONFIG.horseSkin = "gray";
                            } else if (skin.equals("dark_brown")) {
                                CONFIG.horseSkin = "dark_brown";
                            } else if (skin.equals("skeleton")) {
                                CONFIG.horseSkin = "skeleton";
                            } else if (skin.equals("zombie")) {
                                CONFIG.horseSkin = "zombie";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "parrot")) {
                            if (skin.equals("red")) {
                                CONFIG.parrotSkin = "red";
                            } else if (skin.equals("blue")) {
                                CONFIG.parrotSkin = "blue";
                            } else if (skin.equals("green")) {
                                CONFIG.parrotSkin = "green";
                            } else if (skin.equals("cyan")) {
                                CONFIG.parrotSkin = "cyan";
                            } else if (skin.equals("gray")) {
                                CONFIG.parrotSkin = "gray";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "pig")) {
                            if (skin.equals("temperate")) {
                                CONFIG.pigSkin = "temperate";
                            } else if (skin.equals("warm")) {
                                CONFIG.pigSkin = "warm";
                            } else if (skin.equals("cold")) {
                                CONFIG.pigSkin = "cold";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "rabbit")) {
                            if (skin.equals("brown")) {
                                CONFIG.rabbitSkin = "brown";
                            } else if (skin.equals("white")) {
                                CONFIG.rabbitSkin = "white";
                            } else if (skin.equals("black")) {
                                CONFIG.rabbitSkin = "black";
                            } else if (skin.equals("splotched")) {
                                CONFIG.rabbitSkin = "splotched";
                            } else if (skin.equals("gold")) {
                                CONFIG.rabbitSkin = "gold";
                            } else if (skin.equals("salt")) {
                                CONFIG.rabbitSkin = "salt";
                            } else if (skin.equals("killer")) {
                                CONFIG.rabbitSkin = "killer";
                            } else if (skin.equals("toast")) {
                                CONFIG.rabbitSkin = "toast";
                            } else {
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
                            if (skin.equals("farmer")) {
                                CONFIG.villagerSkin = "farmer";
                            } else if (skin.equals("fisherman")) {
                                CONFIG.villagerSkin = "fisherman";
                            } else if (skin.equals("shepherd")) {
                                CONFIG.villagerSkin = "shepherd";
                            } else if (skin.equals("fletcher")) {
                                CONFIG.villagerSkin = "fletcher";
                            } else if (skin.equals("cleric")) {
                                CONFIG.villagerSkin = "cleric";
                            } else if (skin.equals("weaponsmith")) {
                                CONFIG.villagerSkin = "weaponsmith";
                            } else if (skin.equals("armorer")) {
                                CONFIG.villagerSkin = "armorer";
                            } else if (skin.equals("toolsmith")) {
                                CONFIG.villagerSkin = "toolsmith";
                            } else if (skin.equals("librarian")) {
                                CONFIG.villagerSkin = "librarian";
                            } else if (skin.equals("cartographer")) {
                                CONFIG.villagerSkin = "cartographer";
                            } else if (skin.equals("leatherworker")) {
                                CONFIG.villagerSkin = "leatherworker";
                            } else if (skin.equals("butcher")) {
                                CONFIG.villagerSkin = "butcher";
                            } else if (skin.equals("mason")) {
                                CONFIG.villagerSkin = "mason";
                            } else if (skin.equals("nitwit")) {
                                CONFIG.villagerSkin = "nitwit";
                            } else if (skin.equals("unemployed")) {
                                CONFIG.villagerSkin = "unemployed";
                            } else {
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
                            if (skin.equals("happy")) {
                                CONFIG.beeSkin = "happy";
                            } else if (skin.equals("angry")) {
                                CONFIG.beeSkin = "angry";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "fox")) {
                            if (skin.equals("red")) {
                                CONFIG.foxSkin = "red";
                            } else if (skin.equals("snow")) {
                                CONFIG.foxSkin = "snow";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "llama")) {
                            if (skin.equals("brown")) {
                                CONFIG.llamaSkin = "brown";
                            } else if (skin.equals("creamy")) {
                                CONFIG.llamaSkin = "creamy";
                            } else if (skin.equals("gray")) {
                                CONFIG.llamaSkin = "gray";
                            } else if (skin.equals("white")) {
                                CONFIG.llamaSkin = "white";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "nautilus")) {
                            if (skin.equals("nautilus")) {
                                CONFIG.nautilusSkin = "nautilus";
                            } else if (skin.equals("zombie")) {
                                CONFIG.nautilusSkin = "zombie";
                            } else if (skin.equals("coral_zombie") || skin.equals("coral zombie")) {
                                CONFIG.nautilusSkin = "coral_zombie";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "panda")) {
                            if (skin.equals("normal")) {
                                CONFIG.pandaSkin = "normal";
                            } else if (skin.equals("lazy")) {
                                CONFIG.pandaSkin = "lazy";
                            } else if (skin.equals("agressive")) {
                                CONFIG.pandaSkin = "agressive";
                            } else if (skin.equals("worried")) {
                                CONFIG.pandaSkin = "worried";
                            } else if (skin.equals("playful")) {
                                CONFIG.pandaSkin = "playful";
                            } else if (skin.equals("weak")) {
                                CONFIG.pandaSkin = "weak";
                            } else if (skin.equals("brown")) {
                                CONFIG.pandaSkin = "brown";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "piglin")) {
                            if (skin.equals("piglin")) {
                                CONFIG.piglinSkin = "piglin";
                            } else if (skin.equals("zombified_piglin") || skin.equals("zombified piglin") || skin.equals("zombified")) {
                                CONFIG.piglinSkin = "zombified";
                            } else if (skin.equals("piglin_brute") || skin.equals("piglin brute") || skin.equals("brute")) {
                                CONFIG.piglinSkin = "brute";
                            } else {
                                isValid = false;
                            }
                        } /*else if (Objects.equals(CONFIG.activePet, "wolf")) {
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

                                default:
                                    isValid = false;
                            }
                        }*/ else if (Objects.equals(CONFIG.activePet, "hoglin")) {
                            if (skin.equals("hoglin") || skin.equals("normal")) {
                                CONFIG.hoglinSkin = "hoglin";
                            } else if (skin.equals("zoglin")) {
                                CONFIG.hoglinSkin = "zoglin";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "magma_cube")) {
                            if (skin.equals("small")) {
                                CONFIG.magmaCubeSkin = "small";
                            } else if (skin.equals("medium")) {
                                CONFIG.magmaCubeSkin = "medium";
                            } else if (skin.equals("large")) {
                                CONFIG.magmaCubeSkin = "large";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "slime") || Objects.equals(CONFIG.activePet, "tropical_slime")) {
                            if (skin.equals("small")) {
                                CONFIG.slimeSkin = "small";
                            } else if (skin.equals("medium")) {
                                CONFIG.slimeSkin = "medium";
                            } else if (skin.equals("large")) {
                                CONFIG.slimeSkin = "large";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "shulker")) {
                            if (skin.equals("normal")) {
                                CONFIG.shulkerSkin = "normal";
                            } else if (skin.equals("black")) {
                                CONFIG.shulkerSkin = "black";
                            } else if (skin.equals("brown")) {
                                CONFIG.shulkerSkin = "brown";
                            } else if (skin.equals("cyan")) {
                                CONFIG.shulkerSkin = "cyan";
                            } else if (skin.equals("gray")) {
                                CONFIG.shulkerSkin = "gray";
                            } else if (skin.equals("green")) {
                                CONFIG.shulkerSkin = "green";
                            } else if (skin.equals("light_blue") || skin.equals("light blue")) {
                                CONFIG.shulkerSkin = "light_blue";
                            } else if (skin.equals("light_gray") || skin.equals("light gray")) {
                                CONFIG.shulkerSkin = "light_gray";
                            } else if (skin.equals("lime")) {
                                CONFIG.shulkerSkin = "lime";
                            } else if (skin.equals("magenta")) {
                                CONFIG.shulkerSkin = "magenta";
                            } else if (skin.equals("orange")) {
                                CONFIG.shulkerSkin = "orange";
                            } else if (skin.equals("pink")) {
                                CONFIG.shulkerSkin = "pink";
                            } else if (skin.equals("purple")) {
                                CONFIG.shulkerSkin = "purple";
                            } else if (skin.equals("red")) {
                                CONFIG.shulkerSkin = "red";
                            } else if (skin.equals("white")) {
                                CONFIG.shulkerSkin = "white";
                            } else if (skin.equals("yellow")) {
                                CONFIG.shulkerSkin = "yellow";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "zombie_villager")) {
                            if (skin.equals("farmer")) {
                                CONFIG.zombieVillagerSkin = "farmer";
                            } else if (skin.equals("fisherman")) {
                                CONFIG.zombieVillagerSkin = "fisherman";
                            } else if (skin.equals("shepherd")) {
                                CONFIG.zombieVillagerSkin = "shepherd";
                            } else if (skin.equals("fletcher")) {
                                CONFIG.zombieVillagerSkin = "fletcher";
                            } else if (skin.equals("cleric")) {
                                CONFIG.zombieVillagerSkin = "cleric";
                            } else if (skin.equals("weaponsmith")) {
                                CONFIG.zombieVillagerSkin = "weaponsmith";
                            } else if (skin.equals("armorer")) {
                                CONFIG.zombieVillagerSkin = "armorer";
                            } else if (skin.equals("toolsmith")) {
                                CONFIG.zombieVillagerSkin = "toolsmith";
                            } else if (skin.equals("librarian")) {
                                CONFIG.zombieVillagerSkin = "librarian";
                            } else if (skin.equals("cartographer")) {
                                CONFIG.zombieVillagerSkin = "cartographer";
                            } else if (skin.equals("leatherworker")) {
                                CONFIG.zombieVillagerSkin = "leatherworker";
                            } else if (skin.equals("butcher")) {
                                CONFIG.zombieVillagerSkin = "butcher";
                            } else if (skin.equals("mason")) {
                                CONFIG.zombieVillagerSkin = "mason";
                            } else if (skin.equals("nitwit")) {
                                CONFIG.zombieVillagerSkin = "nitwit";
                            } else if (skin.equals("unemployed")) {
                                CONFIG.zombieVillagerSkin = "unemployed";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "creeper") || Objects.equals(CONFIG.activePet, "nerd_creeper") || Objects.equals(CONFIG.activePet, "smiling_creeper")) {
                            if (skin.equals("normal")) {
                                CONFIG.creeperSkin = "normal";
                            } else if (skin.equals("charged")) {
                                CONFIG.creeperSkin = "charged";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "wither")) {
                            if (skin.equals("normal")) {
                                CONFIG.witherSkin = "normal";
                            } else if (skin.equals("invulnerable")) {
                                CONFIG.witherSkin = "invulnerable";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "head")) {
                            CONFIG.headSkin = skin.toLowerCase();
                        } else if (Objects.equals(CONFIG.activePet, "traitor")) {
                            if (skin.equals("desert")) {
                                CONFIG.traitorSkin = "desert";
                            } else if (skin.equals("jungle")) {
                                CONFIG.traitorSkin = "jungle";
                            } else if (skin.equals("plains")) {
                                CONFIG.traitorSkin = "plains";
                            } else if (skin.equals("savanna")) {
                                CONFIG.traitorSkin = "savanna";
                            } else if (skin.equals("snow") || skin.equals("snowy")) {
                                CONFIG.traitorSkin = "snow";
                            } else if (skin.equals("swamp")) {
                                CONFIG.traitorSkin = "swamp";
                            } else if (skin.equals("taiga")) {
                                CONFIG.traitorSkin = "taiga";
                            } else {
                                isValid = false;
                            }
                        } else if (Objects.equals(CONFIG.activePet, "dumbo_octopus")) {
                            if (skin.equals("yellow")) {
                                CONFIG.dumboOctopusSkin = "yellow";
                            } else if (skin.equals("red")) {
                                CONFIG.dumboOctopusSkin = "red";
                            } else if (skin.equals("blue")) {
                                CONFIG.dumboOctopusSkin = "blue";
                            } else if (skin.equals("green")) {
                                CONFIG.dumboOctopusSkin = "green";
                            } else if (skin.equals("orange")) {
                                CONFIG.dumboOctopusSkin = "orange";
                            } else if (skin.equals("pink")) {
                                CONFIG.dumboOctopusSkin = "pink";
                            } else {
                                isValid = false;
                            }
                        }
                    }

                    if (isValid) {
                        Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §aYour pet's skin has been updated."), false);
                    } else {
                        Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §cEither your currently selected pet doesn't support multiple skins, or that is not a valid skin. Try something else."), false);
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
    public void checkForNullObjects() {

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
        CONFIG.shulkerSkin = Utils.checkNullString(CONFIG.shulkerSkin, "normal");
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
        CONFIG.zombiePigmanName = Utils.checkNullString(CONFIG.zombiePigmanName);
    }

    /**
     * Creates the command that allows the user to use {@code /teleportpet}.
     */
    public void createPetTeleportCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("teleportpet").executes((context) -> {
            Minecraft.getInstance().execute(() -> {
                despawnPet();
                summonPet();
            });
            return 1;
        }));
    }

    /**
     * Creates the command that allows the user to use {@code /petspecies}.
     */
    public void createPetSpeciesCommand(CommandDispatcher dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.literal("petspecies").then(RequiredArgumentBuilder.argument("species", StringArgumentType.greedyString()).suggests(PETS).executes((context) -> {
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
            } else if (Objects.equals(species, "bat")) {
                Utils.setActivePet(bat, "bat");
            } else if (Objects.equals(species, "chicken")) {
                Utils.setActivePet(chicken, "chicken");
            } else if (Objects.equals(species, "cod")) {
                Utils.setActivePet(cod, "cod");
            } else if (Objects.equals(species, "cow")) {
                Utils.setActivePet(cow, "cow");
            } else if (Objects.equals(species, "donkey")) {
                Utils.setActivePet(donkey, "donkey");
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
            } else if (Objects.equals(species, "snow_golem") || Objects.equals(species, "snow golem")) {
                Utils.setActivePet(snowGolem, "snow_golem");
            } else if (Objects.equals(species, "squid")) {
                Utils.setActivePet(squid, "squid");
            } else if (Objects.equals(species, "turtle")) {
                Utils.setActivePet(turtle, "turtle");
            } else if (Objects.equals(species, "villager")) {
                Utils.setActivePet(villager, "villager");
            } else if (Objects.equals(species, "cave_spider") || Objects.equals(species, "cave spider")) {
                Utils.setActivePet(caveSpider, "cave_spider");
            } else if (Objects.equals(species, "dolphin")) {
                Utils.setActivePet(dolphin, "dolphin");
            } else if (Objects.equals(species, "enderman")) {
                Utils.setActivePet(enderman, "enderman");
            } else if (Objects.equals(species, "iron_golem") || Objects.equals(species, "iron golem")) {
                Utils.setActivePet(ironGolem, "iron_golem");
            } else if (Objects.equals(species, "llama")) {
                Utils.setActivePet(llama, "llama");
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
            } else if (Objects.equals(species, "guardian")) {
                Utils.setActivePet(guardian, "guardian");
            } else if (Objects.equals(species, "magma_cube") || Objects.equals(species, "magma cube")) {
                Utils.setActivePet(magmaCube, "magma_cube");
            } else if (Objects.equals(species, "phantom")) {
                Utils.setActivePet(phantom, "phantom");
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
            } else if (Objects.equals(species, "stray")) {
                Utils.setActivePet(stray, "stray");
            } else if (Objects.equals(species, "wither_skeleton") || Objects.equals(species, "wither skeleton")) {
                Utils.setActivePet(witherSkeleton, "wither_skeleton");
            } else if (Objects.equals(species, "wither")) {
                Utils.setActivePet(wither, "wither");
            } else if (Objects.equals(species, "ender dragon") || Objects.equals(species, "ender_dragon")) {
                Utils.setActivePet(enderDragon, "ender_dragon");
            } else if (Objects.equals(species, "head")) {
                Utils.setActivePet(head, "head");
            } else if (Objects.equals(species, "dumbo_octopus") || Objects.equals(species, "dumbo octopus")) {
                Utils.setActivePet(dumboOctopus, "dumbo_octopus");
            } else if (Objects.equals(species, "koi")) {
                Utils.setActivePet(koi, "koi");
            } else if (Objects.equals(species, "stingray")) {
                Utils.setActivePet(stingray, "stingray");
            } else if (Objects.equals(species, "zombie_pigman") || Objects.equals(species, "zombie pigman")) {
                Utils.setActivePet(zombiePigman, "zombie_pigman");
            } else {
                isValid = false;
            }

            this.checkValidPet(isValid, context, species);

            AutoConfig.getConfigHolder(PetsConfig.class).save();
            updateSuggestions(Minecraft.getInstance());
            return 1;
        })));
    }

    /**
     * Creates a help command to let the user easily view the commands at their disposal.
     */
    public void createPetHelpCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("pethelp").executes(context -> {
            Minecraft.getInstance().execute(() -> {
                Minecraft.getInstance().player.addMessage(new LiteralText(
                        "§b[PetsMod] §aPossible commands: §a/pethelp: §rdisplays a list of commands §a/pet <on/off> §rtoggles whether your pet will appear or not§a/petspecies <species>: §rchanges the species of your pet§a/petskin <skin>: §rchanges the skin of your selected pet§a/teleportpet: §rteleports your pet to you. will not work if you are not on the ground.§a/petname: §rchanges the name of your currently selected pet"
                ), false);
            });
            return 1;
        }));
    }

    /**
     * Creates the command that allows the user to change their pet's name.
     */
    public void createPetNameCommand(CommandDispatcher dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<ClientSuggestionProvider>literal("petname").then(RequiredArgumentBuilder.<ClientSuggestionProvider, String>argument("name", StringArgumentType.greedyString()).executes((context) -> {
            Minecraft.getInstance().execute(() -> {
            String name = StringArgumentType.getString(context, "name");
            if (!summonedEntity.isEmpty()) {
                if (CONFIG.activePet.equals("penguin")) {
                    CONFIG.penguinName = name;
                } else if (CONFIG.activePet.equals("duck")) {
                    CONFIG.duckName = name;
                } else if (CONFIG.activePet.equals("racoon")) {
                    CONFIG.racoonName = name;
                } else if (CONFIG.activePet.equals("cat")) {
                    CONFIG.catName = name;
                } else if (CONFIG.activePet.equals("sheep")) {
                    CONFIG.sheepName = name;
                } else if (CONFIG.activePet.equals("allay")) {
                    CONFIG.allayName = name;
                } else if (CONFIG.activePet.equals("armadillo")) {
                    CONFIG.armadilloName = name;
                } else if (CONFIG.activePet.equals("bat")) {
                    CONFIG.batName = name;
                } else if (CONFIG.activePet.equals("camel")) {
                    CONFIG.camelName = name;
                } else if (CONFIG.activePet.equals("chicken")) {
                    CONFIG.chickenSkin = name;
                } else if (CONFIG.activePet.equals("cod")) {
                    CONFIG.codName = name;
                } else if (CONFIG.activePet.equals("copper_golem")) {
                    CONFIG.copperGolemName = name;
                } else if (CONFIG.activePet.equals("cow")) {
                    CONFIG.cowName = name;
                } else if (CONFIG.activePet.equals("donkey")) {
                    CONFIG.donkeyName = name;
                } else if (CONFIG.activePet.equals("frog")) {
                    CONFIG.frogName = name;
                } else if (CONFIG.activePet.equals("horse")) {
                    CONFIG.horseName = name;
                } else if (CONFIG.activePet.equals("mooshroom")) {
                    CONFIG.mooshroomName = name;
                } else if (CONFIG.activePet.equals("mule")) {
                    CONFIG.muleName = name;
                } else if (CONFIG.activePet.equals("parrot")) {
                    CONFIG.parrotName = name;
                } else if (CONFIG.activePet.equals("pig")) {
                    CONFIG.pigName = name;
                } else if (CONFIG.activePet.equals("rabbit")) {
                    CONFIG.rabbitName = name;
                } else if (CONFIG.activePet.equals("salmon")) {
                    CONFIG.salmonName = name;
                } else if (CONFIG.activePet.equals("sniffer")) {
                    CONFIG.snifferName = name;
                } else if (CONFIG.activePet.equals("snow_golem")) {
                    CONFIG.snowGolemName = name;
                } else if (CONFIG.activePet.equals("squid")) {
                    CONFIG.squidName = name;
                } else if (CONFIG.activePet.equals("strider")) {
                    CONFIG.striderName = name;
                } else if (CONFIG.activePet.equals("tadpole")) {
                    CONFIG.tadpoleName = name;
                } else if (CONFIG.activePet.equals("tropical_fish")) {
                    CONFIG.tropicalFishName = name;
                } else if (CONFIG.activePet.equals("turtle")) {
                    CONFIG.turtleName = name;
                } else if (CONFIG.activePet.equals("villager")) {
                    CONFIG.villagerName = name;
                } else if (CONFIG.activePet.equals("wandering_trader")) {
                    CONFIG.wanderingTraderName = name;
                } else if (CONFIG.activePet.equals("bee")) {
                    CONFIG.beeName = name;
                } else if (CONFIG.activePet.equals("cave_spider")) {
                    CONFIG.caveSpiderName = name;
                } else if (CONFIG.activePet.equals("dolphin")) {
                    CONFIG.dolphinName = name;
                } else if (CONFIG.activePet.equals("enderman")) {
                    CONFIG.endermanName = name;
                } else if (CONFIG.activePet.equals("fox")) {
                    CONFIG.foxName = name;
                } else if (CONFIG.activePet.equals("goat")) {
                    CONFIG.goatName = name;
                } else if (CONFIG.activePet.equals("iron_golem")) {
                    CONFIG.ironGolemName = name;
                } else if (CONFIG.activePet.equals("llama")) {
                    CONFIG.llamaName = name;
                } else if (CONFIG.activePet.equals("nautilus")) {
                    CONFIG.nautilusName = name;
                } else if (CONFIG.activePet.equals("panda")) {
                    CONFIG.pandaName = name;
                } else if (CONFIG.activePet.equals("piglin")) {
                    CONFIG.piglinName = name;
                } else if (CONFIG.activePet.equals("polar_bear")) {
                    CONFIG.polarBearName = name;
                } else if (CONFIG.activePet.equals("pufferfish")) {
                    CONFIG.pufferFishName = name;
                } else if (CONFIG.activePet.equals("spider")) {
                    CONFIG.spiderName = name;
                } else if (CONFIG.activePet.equals("wolf")) {
                    CONFIG.wolfName = name;
                } else if (CONFIG.activePet.equals("blaze")) {
                    CONFIG.blazeName = name;
                } else if (CONFIG.activePet.equals("breeze")) {
                    CONFIG.breezeName = name;
                } else if (CONFIG.activePet.equals("creaking")) {
                    CONFIG.creakingName = name;
                } else if (CONFIG.activePet.equals("creeper")) {
                    CONFIG.creeperName = name;
                } else if (CONFIG.activePet.equals("elder_guardian")) {
                    CONFIG.elderGuardianName = name;
                } else if (CONFIG.activePet.equals("endermite")) {
                    CONFIG.endermiteName = name;
                } else if (CONFIG.activePet.equals("evoker")) {
                    CONFIG.evokerName = name;
                } else if (CONFIG.activePet.equals("happy_ghast")) {
                    CONFIG.happyGhastName = name;
                } else if (CONFIG.activePet.equals("ghast")) {
                    CONFIG.ghastName = name;
                } else if (CONFIG.activePet.equals("guardian")) {
                    CONFIG.guardianName = name;
                } else if (CONFIG.activePet.equals("hoglin")) {
                    CONFIG.hoglinName = name;
                } else if (CONFIG.activePet.equals("magma_cube")) {
                    CONFIG.magmaCubeName = name;
                } else if (CONFIG.activePet.equals("phantom")) {
                    CONFIG.phantomName = name;
                } else if (CONFIG.activePet.equals("pillager")) {
                    CONFIG.pillagerName = name;
                } else if (CONFIG.activePet.equals("ravager")) {
                    CONFIG.ravagerName = name;
                } else if (CONFIG.activePet.equals("shulker")) {
                    CONFIG.shulkerName = name;
                } else if (CONFIG.activePet.equals("silverfish")) {
                    CONFIG.silverfishName = name;
                } else if (CONFIG.activePet.equals("skeleton")) {
                    CONFIG.skeletonName = name;
                } else if (CONFIG.activePet.equals("slime")) {
                    CONFIG.slimeName = name;
                } else if (CONFIG.activePet.equals("vex")) {
                    CONFIG.vexName = name;
                } else if (CONFIG.activePet.equals("vindicator")) {
                    CONFIG.vindicatorName = name;
                } else if (CONFIG.activePet.equals("warden")) {
                    CONFIG.wardenName = name;
                } else if (CONFIG.activePet.equals("witch")) {
                    CONFIG.witchName = name;
                } else if (CONFIG.activePet.equals("zombie")) {
                    CONFIG.zombieName = name;
                } else if (CONFIG.activePet.equals("zombie_villager")) {
                    CONFIG.zombieVillagerName = name;
                } else if (CONFIG.activePet.equals("husk")) {
                    CONFIG.huskName = name;
                } else if (CONFIG.activePet.equals("drowned")) {
                    CONFIG.drownedName = name;
                } else if (CONFIG.activePet.equals("bogged")) {
                    CONFIG.boggedName = name;
                } else if (CONFIG.activePet.equals("parched")) {
                    CONFIG.parchedName = name;
                } else if (CONFIG.activePet.equals("stray")) {
                    CONFIG.strayName = name;
                } else if (CONFIG.activePet.equals("wither_skeleton")) {
                    CONFIG.witherSkeletonName = name;
                } else if (CONFIG.activePet.equals("ender_dragon")) {
                    CONFIG.enderDragonName = name;
                } else if (CONFIG.activePet.equals("wither")) {
                    CONFIG.witherName = name;
                } else if (CONFIG.activePet.equals("angry_ghast")) {
                    CONFIG.angryGhastName = name;
                } else if (CONFIG.activePet.equals("batato")) {
                    CONFIG.batatoName = name;
                } else if (CONFIG.activePet.equals("diamond_chicken")) {
                    CONFIG.diamondChickenName = name;
                } else if (CONFIG.activePet.equals("love_golem")) {
                    CONFIG.loveGolemName = name;
                } else if (CONFIG.activePet.equals("mega_spud")) {
                    CONFIG.megaSpudName = name;
                } else if (CONFIG.activePet.equals("moon_cow")) {
                    CONFIG.moonCowName = name;
                } else if (CONFIG.activePet.equals("nerd_creeper")) {
                    CONFIG.nerdCreeperName = name;
                } else if (CONFIG.activePet.equals("pink_wither")) {
                    CONFIG.pinkWitherName = name;
                } else if (CONFIG.activePet.equals("plaguewhale_slab")) {
                    CONFIG.plaguewhaleSlabName = name;
                } else if (CONFIG.activePet.equals("poisonous_potato_zombie")) {
                    CONFIG.poisonousPotatoZombieName = name;
                } else if (CONFIG.activePet.equals("ray_tracing")) {
                    CONFIG.rayTracingName = name;
                } else if (CONFIG.activePet.equals("redstone_bug")) {
                    CONFIG.redstoneBugName = name;
                } else if (CONFIG.activePet.equals("smiling_creeper")) {
                    CONFIG.smilingCreeperName = name;
                } else if (CONFIG.activePet.equals("toxifin_slab")) {
                    CONFIG.toxfinSlabName = name;
                } else if (CONFIG.activePet.equals("potato_husk")) {
                    CONFIG.potatoHuskName = name;
                } else if (CONFIG.activePet.equals("head")) {
                    CONFIG.headName = name;
                } else if (CONFIG.activePet.equals("traitor")) {
                    CONFIG.traitorName = name;
                } else if (CONFIG.activePet.equals("dumbo_octopus")) {
                    CONFIG.dumboOctopusName = name;
                } else if (CONFIG.activePet.equals("koi")) {
                    CONFIG.koiName = name;
                } else if (CONFIG.activePet.equals("stingray")) {
                    CONFIG.stingrayName = name;
                } else if (CONFIG.activePet.equals("zombie_pigman")) {
                    CONFIG.zombiePigmanName = name;
                }
                AutoConfig.getConfigHolder(PetsConfig.class).save();
            }
            });

            return 1;
        })));
    }

    /**
     * Creates the command that allows the user to toggle their pet on and off.
     */
    public void createToggleCommand(CommandDispatcher dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.literal("pet").then(RequiredArgumentBuilder.argument("preference", StringArgumentType.string()).suggests((CommandContext<Object> context, SuggestionsBuilder builder) -> ON_OFF.getSuggestions(context, builder)).executes((context) -> {
            String preference = StringArgumentType.getString(context, "preference");
            if (Objects.equals(preference, "off")) {
                CONFIG.petOn = false;
                Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §7Pet §coff."), false);
                AutoConfig.getConfigHolder(PetsConfig.class).save();
            } else if (Objects.equals(preference, "on")) {
                CONFIG.petOn = true;
                AutoConfig.getConfigHolder(PetsConfig.class).save();
                Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §7Pet §aon."), false);
            } else {
                Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §c§lUnknown value " + preference + "! Possible values: §r§aon, §6off"), false);
            }

            return 1;
        })));
    }

    void createPetsList() {
        String[] stuffs = new String[]{
                "bat", "blaze",
                "cat", "cave spider", "chicken",
                "cod", "cow",
                "creeper",
                "dolphin", "donkey", "drowned", "duck", "dumbo octopus",
                "elder guardian", "ender dragon", "enderman", "endermite", "evoker",
                "ghast", "guardian",
                "head", "horse",
                "husk", "iron golem",
                "koi", "llama",
                "magma cube",
                "mooshroom",
                "parrot", "penguin", "phantom",
                "pig",
                "polar bear",
                "pufferfish", "rabbit",
                "racoon",
                "salmon",
                "sheep",
                "shulker",
                "silverfish", "skeleton", "slime", "snow golem",
                "spider", "squid", "stingray", "stray",
                "turtle",
                "vex", "villager", "vindicator", "witch", "wither",
                "wither skeleton", "wolf", "zombie", "zombie villager", "zombie pigman"};
        Collections.addAll(PETS_LIST, stuffs);
    }

    public void checkValidPet(boolean isValid, CommandContext<Object> context, String species) {
        Minecraft.getInstance().execute(() -> {
            if (!isValid) {
                Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §cThat's not a pet that's currently supported. Try something else. (Unknown input \"" + species + "\")"), false);
            } else if (isValid && CONFIG.petOn) {
                    despawnPet();
                    Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §aYour active pet has been switched to " + CONFIG.activePet.replace("_", " ") + "."), false);
                    summonPet();
            } else if (isValid && !CONFIG.petOn) {
                Minecraft.getInstance().player.addMessage(new LiteralText("§b[PetsMod] §cYour pet has been switched to " + CONFIG.activePet.replace("_", " ") + ", but you currently do not have your pet enabled. Run §l/pet on§r§c to change this."), false);
            }
        });
    }

    public static Central get() {
        return new Central();
    }
}
