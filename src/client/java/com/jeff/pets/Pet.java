/**
 * so this file got a little messed up and I had to restore it from an earlier save, but that was
 * inside of a JAR, so this was re-created from the de-compiled bytecode, which is why it is
 * a little wierd.
 */

package com.jeff.pets;

import com.jeff.pets.custom.Duck;
import com.jeff.pets.custom.Penguin;
import com.jeff.pets.custom.Racoon;
import com.jeff.pets.mixin.client.ChatAccessor;
import com.jeff.pets.vanilla.boss.ClientEnderDragon;
import com.jeff.pets.vanilla.boss.ClientWither;
import com.jeff.pets.vanilla.hostile.*;
import com.jeff.pets.vanilla.neutral.*;
import com.jeff.pets.vanilla.passive.*;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.RootCommandNode;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Environment(EnvType.CLIENT)
public class Pet implements ClientModInitializer {
    public static final List<Entity> summonedEntity = new ArrayList();
    public static final CopyOnWriteArrayList<String> currentSuggestions = new CopyOnWriteArrayList();
    public static final List<String> BEE_SKINS = List.of("happy", "angry");
    public static final List<String> FOX_SKINS = List.of("red", "snow");
    public static final List<String> LLAMA_SKINS = List.of("brown", "creamy", "gray", "white");
    public static final List<String> NAUTILUS_SKINS = List.of("nautilus", "zombie", "coral zombie");
    public static final List<String> PANDA_SKINS = List.of("normal", "lazy", "agressive", "worried", "playful", "weak", "brown");
    public static final List<String> PIGLIN_SKINS = List.of("piglin", "zombified", "brute");
    public static final List<String> WOLF_SKINS = List.of("pale", "ashen", "black", "chestnut", "rusty", "snowy", "spotted", "striped", "woods");
    private static final SuggestionProvider<SharedSuggestionProvider> ON_OFF = (context, builder) -> SharedSuggestionProvider.suggest(new String[]{"off", "on"}, builder);
    private static final SuggestionProvider<FabricClientCommandSource> PETS = (context, builder) -> SharedSuggestionProvider.suggest(new String[]{"penguin", "duck", "racoon", "allay", "zombie villager", "armadillo", "axolotl", "happy ghast", "bat", "husk", "drowned", "bogged", "parched", "stray", "wither skeleton", "camel", "cat", "chicken", "cod", "copper golem", "cow", "donkey", "frog", "horse", "mooshroom", "parrot", "pig", "rabbit", "salmon", "sheep", "sniffer", "snow golem", "squid", "strider", "tadpole", "turtle", "villager", "wandering trader", "bee", "cave spider", "dolphin", "drowned", "enderman", "fox", "goat", "iron golem", "llama", "nautilus", "panda", "piglin", "polar bear", "pufferfish", "spider", "wolf", "blaze", "breeze", "creaking", "creeper", "elder guardian", "endermite", "evoker", "ghast", "guardian", "hoglin", "magma cube", "phantom", "pillager", "ravager", "shulker", "silverfish", "skeleton", "slime", "vex", "vindicator", "warden", "witch", "zombie", "ender dragon", "wither"}, builder);
    private static final List<String> DUCK_SKINS = List.of("mallard", "pekin", "rubber");
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
    private static final List<String> EMPTY_LIST = List.of();
    public static PetsConfig CONFIG;
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

    private final SuggestionProvider<FabricClientCommandSource> SKINS = (context, builder) -> {
        String remaining = builder.getRemainingLowerCase();

        for (String s : currentSuggestions) {
            if (s.toLowerCase().startsWith(remaining)) {
                builder.suggest(s);
            }
        }

        return builder.buildFuture();
    };
    int i = 1;

    public static void despawnPet() {
        if (penguin != null) {
            penguin.discard();
        }
        if (racoon != null) {
            racoon.discard();
        }
        if (duck != null) {
            duck.discard();
        }
        if (sheep != null) {
            sheep.discard();
        }
        if (cat != null) {
            cat.discard();
        }
        if (allay != null) {
            allay.discard();
        } if (happyGhast != null) {
            happyGhast.discard();
        }
        if (armadillo != null) {
            armadillo.discard();
        }
        if (axolotl != null) {
            axolotl.discard();
        }
        if (bat != null) {
            bat.discard();
        }
        if (camel != null) {
            camel.discard();
        }
        if (chicken != null) {
            chicken.discard();
        }
        if (cod != null) {
            cod.discard();
        }
        if (copperGolem != null) {
            copperGolem.discard();
        }
        if (cow != null) {
            cow.discard();
        }
        if (donkey != null) {
            donkey.discard();
        }
        if (frog != null) {
            frog.discard();
        }
        if (horse != null) {
            horse.discard();
        }
        if (mooshroom != null) {
            mooshroom.discard();
        }
        if (parrot != null) {
            parrot.discard();
        }
        if (pig != null) {
            pig.discard();
        }
        if (rabbit != null) {
            rabbit.discard();
        }
        if (salmon != null) {
            salmon.discard();
        }
        if (sniffer != null) {
            sniffer.discard();
        }
        if (snowGolem != null) {
            snowGolem.discard();
        }
        if (squid != null) {
            squid.discard();
        }
        if (strider != null) {
            strider.discard();
        }
        if (tadpole != null) {
            tadpole.discard();
        }
        if (turtle != null) {
            turtle.discard();
        }
        if (villager != null) {
            villager.discard();
        }
        if (wanderingTrader != null) {
            wanderingTrader.discard();
        }
        if (bee != null) {
            bee.discard();
        }
        if (caveSpider != null) {
            caveSpider.discard();
        }
        if (dolphin != null) {
            dolphin.discard();
        }
        if (enderman != null) {
            enderman.discard();
        }
        if (fox != null) {
            fox.discard();
        }
        if (goat != null) {
            goat.discard();
        }
        if (ironGolem != null) {
            ironGolem.discard();
        }
        if (llama != null) {
            llama.discard();
        }
        if (nautilus != null) {
            nautilus.discard();
        }
        if (panda != null) {
            panda.discard();
        }
        if (piglin != null) {
            piglin.discard();
        }
        if (polarBear != null) {
            polarBear.discard();
        }
        if (pufferFish != null) {
            pufferFish.discard();
        }
        if (spider != null) {
            spider.discard();
        }
        if (wolf != null) {
            wolf.discard();
        }
        if (blaze != null) {
            blaze.discard();
        }
        if (breeze != null) {
            breeze.discard();
        }
        if (creaking != null) {
            creaking.discard();
        }
        if (creeper != null) {
            creeper.discard();
        }
        if (elderGuardian != null) {
            elderGuardian.discard();
        }
        if (endermite != null) {
            endermite.discard();
        }
        if (evoker != null) {
            evoker.discard();
        }
        if (ghast != null) {
            ghast.discard();
        }
        if (guardian != null) {
            guardian.discard();
        }
        if (hoglin != null) {
            hoglin.discard();
        }
        if (magmaCube != null) {
            magmaCube.discard();
        }
        if (phantom != null) {
            phantom.discard();
        }
        if (pillager != null) {
            pillager.discard();
        }
        if (ravager != null) {
            ravager.discard();
        }
        if (shulker != null) {
            shulker.discard();
        }
        if (silverfish != null) {
            silverfish.discard();
        }
        if (skeleton != null) {
            skeleton.discard();
        }
        if (slime != null) {
            slime.discard();
        }
        if (vex != null) {
            vex.discard();
        }
        if (vindicator != null) {
            vindicator.discard();
        }
        if (warden != null) {
            warden.discard();
        }
        if (witch != null) {
            witch.discard();
        }
        if (zombie != null) {
            zombie.discard();
        }
        if (zombieVillager != null) {
            zombieVillager.discard();
        }
        if (husk != null) {
            husk.discard();
        } if (drowned != null) {
            drowned.discard();
        } if (bogged != null) {
            bogged.discard();
        } if (parched != null) {
            parched.discard();
        } if (stray != null) {
            stray.discard();
        } if (witherSkeleton != null) {
            witherSkeleton.discard();
        } if (enderDragon != null) {
            enderDragon.discard();
        } if (wither != null) {
            wither.discard();
        }
    }

    public static void summonPet() {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
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

        assert player != null;

        Vec3 lookAngle = player.getLookAngle();
        double x = player.getX() - lookAngle.x * (double) 0.5F;
        double y = player.getY() + (double) 0.5F;
        double z = player.getZ() - lookAngle.z * (double) 0.5F;
        if (world != null) {
            if (Objects.equals(CONFIG.activePet, "duck")) {
                duck.setPos(x, y, z);
                if (CONFIG.duckName == null) {
                    CONFIG.duckName = "";
                }

                if (CONFIG.duckSkin == null) {
                    CONFIG.duckSkin = "mallard";
                }

                duck.setCustomName(Component.literal(CONFIG.duckName));
                duck.setServerEntity(false);
                world.addEntity(duck);
                duck.tame(player);
                summonedEntity.add(duck);
            } else if (Objects.equals(CONFIG.activePet, "racoon")) {
                racoon.setPos(x, y, z);
                racoon.setServerEntity(false);
                world.addEntity(racoon);
                racoon.setCustomName(Component.literal(CONFIG.racoonName));
                racoon.tame(player);
                summonedEntity.add(racoon);
            } else if (Objects.equals(CONFIG.activePet, "penguin")) {
                penguin.setPos(x, y, z);
                if (CONFIG.penguinName == null) {
                    penguin.setCustomName(Component.literal(""));
                }

                penguin.setServerEntity(false);
                world.addEntity(penguin);
                penguin.tame(player);
                summonedEntity.add(penguin);
            } else if (Objects.equals(CONFIG.activePet, "sheep")) {
                sheep.setPos(x, y, z);
                if (CONFIG.sheepName == null) {
                    CONFIG.sheepName = "";
                }

                if (CONFIG.sheepSkin == null) {
                    CONFIG.sheepSkin = "white";
                }

                sheep.setCustomName(Component.literal(CONFIG.sheepName));
                world.addEntity(sheep);
                sheep.tame(player);
                summonedEntity.add(sheep);
            } else if (Objects.equals(CONFIG.activePet, "cat")) {
                cat.setPos(x, y, z);
                if (CONFIG.catName == null) {
                    CONFIG.catName = "";
                }

                if (CONFIG.catSkin == null) {
                    CONFIG.catSkin = "tuxedo";
                }

                cat.setCustomName(Component.literal(CONFIG.catName));
                world.addEntity(cat);
                cat.tame(player);
                summonedEntity.add(cat);
            } else if (Objects.equals(CONFIG.activePet, "allay")) {
                allay.setPos(x, y, z);
                if (CONFIG.allayName == null) {
                    CONFIG.allayName = "";
                }

                allay.setCustomName(Component.literal(CONFIG.allayName));
                world.addEntity(allay);
                allay.tame(player);
                summonedEntity.add(allay);
            } else if (Objects.equals(CONFIG.activePet, "armadillo")) {
                armadillo.setPos(x, y, z);
                if (CONFIG.armadilloName == null) {
                    CONFIG.armadilloName = "";
                }

                armadillo.setCustomName(Component.literal(CONFIG.armadilloName));
                world.addEntity(armadillo);
                armadillo.tame(player);
                summonedEntity.add(armadillo);
            } else if (Objects.equals(CONFIG.activePet, "axolotl")) {
                axolotl.setPos(x, y, z);
                if (CONFIG.axolotlName == null) {
                    CONFIG.axolotlName = "";
                }

                if (CONFIG.axolotlSkin == null) {
                    CONFIG.axolotlSkin = "pink";
                }

                axolotl.setCustomName(Component.literal(CONFIG.axolotlName));
                world.addEntity(axolotl);
                axolotl.tame(player);
                summonedEntity.add(axolotl);
            } else if (Objects.equals(CONFIG.activePet, "bat")) {
                bat.setPos(x, y, z);
                if (CONFIG.batName == null) {
                    CONFIG.batName = "";
                }

                bat.setCustomName(Component.literal(CONFIG.batName));
                world.addEntity(bat);
                bat.tame(player);
                summonedEntity.add(bat);
            } else if (Objects.equals(CONFIG.activePet, "camel")) {
                camel.setPos(x, y, z);
                if (CONFIG.camelName == null) {
                    CONFIG.camelName = "";
                }

                if (CONFIG.camelSkin == null) {
                    CONFIG.camelSkin = "camel";
                }

                camel.setCustomName(Component.literal(CONFIG.camelName));
                world.addEntity(camel);
                camel.tame(player);
                summonedEntity.add(camel);
            } else if (Objects.equals(CONFIG.activePet, "chicken")) {
                chicken.setPos(x, y, z);
                if (CONFIG.chickenName == null) {
                    CONFIG.chickenName = "";
                }

                if (CONFIG.chickenSkin == null) {
                    CONFIG.chickenSkin = "temperate";
                }

                chicken.setCustomName(Component.literal(CONFIG.chickenName));
                world.addEntity(chicken);
                chicken.tame(player);
                summonedEntity.add(chicken);
            } else if (Objects.equals(CONFIG.activePet, "cod")) {
                cod.setPos(x, y, z);
                if (CONFIG.codName == null) {
                    CONFIG.codName = "";
                }

                cod.setCustomName(Component.literal(CONFIG.codName));
                world.addEntity(cod);
                cod.tame(player);
                summonedEntity.add(cod);
            } else if (Objects.equals(CONFIG.activePet, "copper_golem")) {
                copperGolem.setPos(x, y, z);
                if (CONFIG.copperGolemName == null) {
                    CONFIG.copperGolemName = "";
                }

                if (CONFIG.copperGolemSkin == null) {
                    CONFIG.copperGolemSkin = "unoxidized";
                }

                copperGolem.setCustomName(Component.literal(CONFIG.copperGolemName));
                world.addEntity(copperGolem);
                copperGolem.tame(player);
                summonedEntity.add(copperGolem);
            } else if (Objects.equals(CONFIG.activePet, "cow")) {
                cow.setPos(x, y, z);
                if (CONFIG.cowName == null) {
                    CONFIG.cowName = "";
                }

                if (CONFIG.cowSkin == null) {
                    CONFIG.cowSkin = "temperate";
                }

                cow.setCustomName(Component.literal(CONFIG.cowName));
                world.addEntity(cow);
                cow.tame(player);
                summonedEntity.add(cow);
            } else if (Objects.equals(CONFIG.activePet, "donkey")) {
                donkey.setPos(x, y, z);
                if (CONFIG.donkeyName == null) {
                    CONFIG.donkeyName = "";
                }

                camel.setCustomName(Component.literal(CONFIG.donkeyName));
                world.addEntity(donkey);
                donkey.tame(player);
                summonedEntity.add(donkey);
            } else if (Objects.equals(CONFIG.activePet, "frog")) {
                frog.setPos(x, y, z);
                if (CONFIG.frogName == null) {
                    CONFIG.frogName = "";
                }

                if (CONFIG.frogSkin == null) {
                    CONFIG.frogSkin = "temperate";
                }

                frog.setCustomName(Component.literal(CONFIG.frogName));
                world.addEntity(frog);
                frog.tame(player);
                summonedEntity.add(frog);
            } else if (Objects.equals(CONFIG.activePet, "horse")) {
                horse.setPos(x, y, z);
                if (CONFIG.horseName == null) {
                    CONFIG.horseName = "";
                }

                if (CONFIG.horseSkin == null) {
                    CONFIG.horseSkin = "white";
                }

                horse.setCustomName(Component.literal(CONFIG.horseName));
                world.addEntity(horse);
                horse.tame(player);
                summonedEntity.add(horse);
            } else if (Objects.equals(CONFIG.activePet, "mooshroom")) {
                mooshroom.setPos(x, y, z);
                if (CONFIG.mooshroomName == null) {
                    CONFIG.mooshroomName = "";
                }

                if (CONFIG.mooshroomSkin == null) {
                    CONFIG.mooshroomSkin = "red";
                }

                mooshroom.setCustomName(Component.literal(CONFIG.mooshroomName));
                world.addEntity(mooshroom);
                mooshroom.tame(player);
                summonedEntity.add(mooshroom);
            } else if (Objects.equals(CONFIG.activePet, "parrot")) {
                parrot.setPos(x, y, z);
                if (CONFIG.parrotName == null) {
                    CONFIG.parrotName = "";
                }

                if (CONFIG.parrotSkin == null) {
                    CONFIG.parrotSkin = "red";
                }

                parrot.setCustomName(Component.literal(CONFIG.parrotName));
                world.addEntity(parrot);
                parrot.tame(player);
                summonedEntity.add(parrot);
            } else if (Objects.equals(CONFIG.activePet, "pig")) {
                pig.setPos(x, y, z);
                if (CONFIG.pigName == null) {
                    CONFIG.pigName = "";
                }

                if (CONFIG.pigSkin == null) {
                    CONFIG.pigSkin = "temperate";
                }

                pig.setCustomName(Component.literal(CONFIG.pigName));
                world.addEntity(pig);
                pig.tame(player);
                summonedEntity.add(pig);
            } else if (Objects.equals(CONFIG.activePet, "rabbit")) {
                rabbit.setPos(x, y, z);
                if (CONFIG.rabbitName == null) {
                    CONFIG.rabbitName = "";
                }

                if (CONFIG.rabbitSkin == null) {
                    CONFIG.rabbitSkin = "brown";
                }

                rabbit.setCustomName(Component.literal(CONFIG.rabbitName));
                world.addEntity(rabbit);
                rabbit.tame(player);
                summonedEntity.add(rabbit);
            } else if (Objects.equals(CONFIG.activePet, "salmon")) {
                salmon.setPos(x, y, z);
                if (CONFIG.salmonName == null) {
                    CONFIG.salmonName = "";
                }

                salmon.setCustomName(Component.literal(CONFIG.salmonName));
                world.addEntity(salmon);
                salmon.tame(player);
                summonedEntity.add(salmon);
            } else if (Objects.equals(CONFIG.activePet, "sniffer")) {
                sniffer.setPos(x, y, z);
                if (CONFIG.snifferName == null) {
                    CONFIG.snifferName = "";
                }

                sniffer.setCustomName(Component.literal(CONFIG.snifferName));
                world.addEntity(sniffer);
                sniffer.tame(player);
                summonedEntity.add(sniffer);
            } else if (Objects.equals(CONFIG.activePet, "snow_golem")) {
                snowGolem.setPos(x, y, z);
                if (CONFIG.snowGolemName == null) {
                    CONFIG.snowGolemName = "";
                }

                if (CONFIG.snowGolemSkin == null) {
                    CONFIG.snowGolemSkin = "pumpkin_on";
                }

                snowGolem.setCustomName(Component.literal(CONFIG.snowGolemName));
                world.addEntity(snowGolem);
                snowGolem.tame(player);
                summonedEntity.add(snowGolem);
            } else if (Objects.equals(CONFIG.activePet, "squid")) {
                squid.setPos(x, y, z);
                if (CONFIG.squidName == null) {
                    CONFIG.squidName = "";
                }

                if (CONFIG.squidSkin == null) {
                    CONFIG.squidSkin = "squid";
                }

                squid.setCustomName(Component.literal(CONFIG.squidName));
                world.addEntity(squid);
                squid.tame(player);
                summonedEntity.add(squid);
            } else if (Objects.equals(CONFIG.activePet, "strider")) {
                strider.setPos(x, y, z);
                if (CONFIG.striderName == null) {
                    CONFIG.striderName = "";
                }

                if (CONFIG.striderSkin == null) {
                    CONFIG.striderSkin = "warm";
                }

                strider.setCustomName(Component.literal(CONFIG.striderName));
                world.addEntity(strider);
                strider.tame(player);
                summonedEntity.add(strider);
            } else if (Objects.equals(CONFIG.activePet, "tadpole")) {
                tadpole.setPos(x, y, z);
                if (CONFIG.tadpoleName == null) {
                    CONFIG.tadpoleName = "";
                }

                tadpole.setCustomName(Component.literal(CONFIG.tadpoleName));
                world.addEntity(tadpole);
                tadpole.tame(player);
                summonedEntity.add(tadpole);
            } else if (Objects.equals(CONFIG.activePet, "turtle")) {
                turtle.setPos(x, y, z);
                if (CONFIG.turtleName == null) {
                    CONFIG.turtleName = "";
                }

                turtle.setCustomName(Component.literal(CONFIG.turtleName));
                world.addEntity(turtle);
                turtle.tame(player);
                summonedEntity.add(turtle);
            } else if (Objects.equals(CONFIG.activePet, "villager")) {
                villager.setPos(x, y, z);
                if (CONFIG.villagerName == null) {
                    CONFIG.villagerName = "";
                }

                if (CONFIG.villagerSkin == null) {
                    CONFIG.villagerSkin = "nitwit";
                }

                villager.setCustomName(Component.literal(CONFIG.villagerName));
                world.addEntity(villager);
                villager.tame(player);
                summonedEntity.add(villager);
            } else if (Objects.equals(CONFIG.activePet, "wandering_trader")) {
                wanderingTrader.setPos(x, y, z);
                if (CONFIG.wanderingTraderName == null) {
                    CONFIG.wanderingTraderName = "";
                }

                wanderingTrader.setCustomName(Component.literal(CONFIG.wanderingTraderName));
                world.addEntity(wanderingTrader);
                wanderingTrader.tame(player);
                summonedEntity.add(wanderingTrader);
            } else if (Objects.equals(CONFIG.activePet, "bee")) {
                bee.setPos(x, y, z);
                if (CONFIG.beeName == null) {
                    CONFIG.beeName = "";
                }

                if (CONFIG.beeSkin == null) {
                    CONFIG.beeSkin = "happy";
                }

                bee.setCustomName(Component.literal(CONFIG.beeName));
                world.addEntity(bee);
                bee.tame(player);
                summonedEntity.add(bee);
            } else if (Objects.equals(CONFIG.activePet, "cave_spider")) {
                caveSpider.setPos(x, y, z);
                if (CONFIG.caveSpiderName == null) {
                    CONFIG.caveSpiderName = "";
                }

                caveSpider.setCustomName(Component.literal(CONFIG.caveSpiderName));
                world.addEntity(caveSpider);
                caveSpider.tame(player);
                summonedEntity.add(caveSpider);
            } else if (Objects.equals(CONFIG.activePet, "dolphin")) {
                dolphin.setPos(x, y, z);
                if (CONFIG.dolphinName == null) {
                    CONFIG.dolphinName = "";
                }

                dolphin.setCustomName(Component.literal(CONFIG.dolphinName));
                world.addEntity(dolphin);
                dolphin.tame(player);
                summonedEntity.add(dolphin);
            } else if (Objects.equals(CONFIG.activePet, "enderman")) {
                enderman.setPos(x, y, z);
                if (CONFIG.endermanName == null) {
                    CONFIG.endermanName = "";
                }

                enderman.setCustomName(Component.literal(CONFIG.endermanName));
                world.addEntity(enderman);
                enderman.tame(player);
                summonedEntity.add(enderman);
            } else if (Objects.equals(CONFIG.activePet, "fox")) {
                fox.setPos(x, y, z);
                if (CONFIG.foxName == null) {
                    CONFIG.foxName = "";
                }

                if (CONFIG.foxSkin == null) {
                    CONFIG.foxSkin = "red";
                }

                fox.setCustomName(Component.literal(CONFIG.foxName));
                world.addEntity(fox);
                fox.tame(player);
                summonedEntity.add(fox);
            } else if (Objects.equals(CONFIG.activePet, "goat")) {
                goat.setPos(x, y, z);
                if (CONFIG.goatName == null) {
                    CONFIG.goatName = "";
                }

                goat.setCustomName(Component.literal(CONFIG.goatName));
                world.addEntity(goat);
                goat.tame(player);
                summonedEntity.add(goat);
            } else if (Objects.equals(CONFIG.activePet, "iron_golem")) {
                ironGolem.setPos(x, y, z);
                if (CONFIG.ironGolemName == null) {
                    CONFIG.ironGolemName = "";
                }

                ironGolem.setCustomName(Component.literal(CONFIG.ironGolemName));
                world.addEntity(ironGolem);
                ironGolem.tame(player);
                summonedEntity.add(ironGolem);
            } else if (Objects.equals(CONFIG.activePet, "llama")) {
                llama.setPos(x, y, z);
                if (CONFIG.llamaName == null) {
                    CONFIG.llamaName = "";
                }

                if (CONFIG.llamaSkin == null) {
                    CONFIG.llamaSkin = "brown";
                }

                llama.setCustomName(Component.literal(CONFIG.llamaName));
                world.addEntity(llama);
                llama.tame(player);
                summonedEntity.add(llama);
            } else if (Objects.equals(CONFIG.activePet, "nautilus")) {
                nautilus.setPos(x, y, z);
                if (CONFIG.nautilusName == null) {
                    CONFIG.nautilusName = "";
                }

                if (CONFIG.nautilusSkin == null) {
                    CONFIG.nautilusSkin = "nautilus";
                }

                nautilus.setCustomName(Component.literal(CONFIG.nautilusName));
                world.addEntity(nautilus);
                nautilus.tame(player);
                summonedEntity.add(nautilus);
            } else if (Objects.equals(CONFIG.activePet, "panda")) {
                panda.setPos(x, y, z);
                if (CONFIG.pandaName == null) {
                    CONFIG.pandaName = "";
                }

                if (CONFIG.pandaSkin == null) {
                    CONFIG.pandaSkin = "normal";
                }

                panda.setCustomName(Component.literal(CONFIG.pandaName));
                world.addEntity(panda);
                panda.tame(player);
                summonedEntity.add(panda);
            } else if (Objects.equals(CONFIG.activePet, "piglin")) {
                piglin.setPos(x, y, z);
                if (CONFIG.piglinName == null) {
                    CONFIG.piglinName = "";
                }

                if (CONFIG.piglinSkin == null) {
                    CONFIG.piglinSkin = "piglin";
                }

                piglin.setCustomName(Component.literal(CONFIG.piglinName));
                world.addEntity(piglin);
                piglin.tame(player);
                summonedEntity.add(piglin);
            } else if (Objects.equals(CONFIG.activePet, "polar_bear")) {
                polarBear.setPos(x, y, z);
                if (CONFIG.polarBearName == null) {
                    CONFIG.polarBearName = "";
                }

                polarBear.setCustomName(Component.literal(CONFIG.polarBearName));
                world.addEntity(polarBear);
                polarBear.tame(player);
                summonedEntity.add(polarBear);
            } else if (Objects.equals(CONFIG.activePet, "pufferfish")) {
                pufferFish.setPos(x, y, z);
                if (CONFIG.pufferFishName == null) {
                    CONFIG.pufferFishName = "";
                }

                pufferFish.setCustomName(Component.literal(CONFIG.pufferFishName));
                world.addEntity(pufferFish);
                pufferFish.tame(player);
                summonedEntity.add(pufferFish);
            } else if (Objects.equals(CONFIG.activePet, "spider")) {
                spider.setPos(x, y, z);
                if (CONFIG.spiderName == null) {
                    CONFIG.spiderName = "";
                }

                spider.setCustomName(Component.literal(CONFIG.spiderName));
                world.addEntity(spider);
                spider.tame(player);
                summonedEntity.add(spider);
            } else if (Objects.equals(CONFIG.activePet, "wolf")) {
                wolf.setPos(x, y, z);
                if (CONFIG.wolfName == null) {
                    CONFIG.wolfName = "";
                }

                if (CONFIG.wolfSkin == null) {
                    CONFIG.foxSkin = "pale";
                }

                wolf.setCustomName(Component.literal(CONFIG.wolfName));
                world.addEntity(wolf);
                wolf.tame(player);
                summonedEntity.add(wolf);
            } else if (Objects.equals(CONFIG.activePet, "blaze")) {
                blaze.setPos(x, y, z);
                blaze.setCustomName(Component.literal(CONFIG.blazeName));
                world.addEntity(blaze);
                blaze.tame(player);
                summonedEntity.add(blaze);
            } else if (Objects.equals(CONFIG.activePet, "breeze")) {
                breeze.setPos(x, y, z);
                breeze.setCustomName(Component.literal(CONFIG.breezeName));
                world.addEntity(breeze);
                breeze.tame(player);
                summonedEntity.add(breeze);
            } else if (Objects.equals(CONFIG.activePet, "creaking")) {
                creaking.setPos(x, y, z);
                creaking.setCustomName(Component.literal(CONFIG.creakingName));
                world.addEntity(creaking);
                creaking.tame(player);
                summonedEntity.add(creaking);
            } else if (Objects.equals(CONFIG.activePet, "creeper")) {
                creeper.setPos(x, y, z);
                creeper.setCustomName(Component.literal(CONFIG.creeperName));
                world.addEntity(creeper);
                creeper.tame(player);
                summonedEntity.add(creeper);
            } else if (Objects.equals(CONFIG.activePet, "elder_guardian")) {
                elderGuardian.setPos(x, y, z);
                elderGuardian.setCustomName(Component.literal(CONFIG.elderGuardianName));
                world.addEntity(elderGuardian);
                elderGuardian.tame(player);
                summonedEntity.add(elderGuardian);
            } else if (Objects.equals(CONFIG.activePet, "endermite")) {
                endermite.setPos(x, y, z);
                endermite.setCustomName(Component.literal(CONFIG.endermiteName));
                world.addEntity(endermite);
                endermite.tame(player);
                summonedEntity.add(enderman);
            } else if (Objects.equals(CONFIG.activePet, "evoker")) {
                evoker.setPos(x, y, z);
                evoker.setCustomName(Component.literal(CONFIG.evokerName));
                world.addEntity(evoker);
                evoker.tame(player);
                summonedEntity.add(evoker);
            } else if (Objects.equals(CONFIG.activePet, "happy_ghast")) {
                happyGhast.setPos(x, y, z);
                happyGhast.setCustomName(Component.literal(CONFIG.happyGhastName));
                world.addEntity(happyGhast);
                happyGhast.tame(player);
                summonedEntity.add(happyGhast);
            } else if (Objects.equals(CONFIG.activePet, "ghast")) {
                ghast.setPos(x, y, z);
                ghast.setCustomName(Component.literal(CONFIG.ghastName));
                world.addEntity(ghast);
                ghast.tame(player);
                summonedEntity.add(ghast);
            } else if (Objects.equals(CONFIG.activePet, "guardian")) {
                guardian.setPos(x, y, z);
                guardian.setCustomName(Component.literal(CONFIG.guardianName));
                world.addEntity(guardian);
                guardian.tame(player);
                summonedEntity.add(guardian);
            } else if (Objects.equals(CONFIG.activePet, "hoglin")) {
                hoglin.setPos(x, y, z);
                hoglin.setCustomName(Component.literal(CONFIG.hoglinName));
                world.addEntity(hoglin);
                hoglin.tame(player);
                summonedEntity.add(hoglin);
            } else if (Objects.equals(CONFIG.activePet, "magma_cube")) {
                magmaCube.setPos(x, y, z);
                magmaCube.setCustomName(Component.literal(CONFIG.magmaCubeName));
                world.addEntity(magmaCube);
                magmaCube.tame(player);
                summonedEntity.add(magmaCube);
            } else if (Objects.equals(CONFIG.activePet, "phantom")) {
                phantom.setPos(x, y, z);
                phantom.setCustomName(Component.literal(CONFIG.phantomName));
                world.addEntity(phantom);
                phantom.tame(player);
                summonedEntity.add(phantom);
            } else if (Objects.equals(CONFIG.activePet, "pillager")) {
                pillager.setPos(x, y, z);
                pillager.setCustomName(Component.literal(CONFIG.pillagerName));
                world.addEntity(pillager);
                pillager.tame(player);
                summonedEntity.add(pillager);
            } else if (Objects.equals(CONFIG.activePet, "ravager")) {
                ravager.setPos(x, y, z);
                ravager.setCustomName(Component.literal(CONFIG.ravagerName));
                world.addEntity(ravager);
                ravager.tame(player);
                summonedEntity.add(ravager);
            } else if (Objects.equals(CONFIG.activePet, "shulker")) {
                shulker.setPos(x, y, z);
                shulker.setCustomName(Component.literal(CONFIG.shulkerName));
                world.addEntity(shulker);
                shulker.tame(player);
                summonedEntity.add(shulker);
            } else if (Objects.equals(CONFIG.activePet, "silverfish")) {
                silverfish.setPos(x, y, z);
                silverfish.setCustomName(Component.literal(CONFIG.silverfishName));
                world.addEntity(silverfish);
                silverfish.tame(player);
                summonedEntity.add(silverfish);
            } else if (Objects.equals(CONFIG.activePet, "skeleton")) {
                skeleton.setPos(x, y, z);
                skeleton.setCustomName(Component.literal(CONFIG.skeletonName));
                world.addEntity(skeleton);
                skeleton.tame(player);
                summonedEntity.add(skeleton);
            } else if (Objects.equals(CONFIG.activePet, "slime")) {
                slime.setPos(x, y, z);
                slime.setCustomName(Component.literal(CONFIG.slimeName));
                world.addEntity(slime);
                slime.tame(player);
                summonedEntity.add(slime);
            } else if (Objects.equals(CONFIG.activePet, "vex")) {
                vex.setPos(x, y, z);
                vex.setCustomName(Component.literal(CONFIG.vexName));
                world.addEntity(vex);
                vex.tame(player);
                summonedEntity.add(vex);
            } else if (Objects.equals(CONFIG.activePet, "vindicator")) {
                vindicator.setPos(x, y, z);
                vindicator.setCustomName(Component.literal(CONFIG.vindicatorName));
                world.addEntity(vindicator);
                vindicator.tame(player);
                summonedEntity.add(vindicator);
            } else if (Objects.equals(CONFIG.activePet, "warden")) {
                warden.setPos(x, y, z);
                warden.setCustomName(Component.literal(CONFIG.wardenName));
                world.addEntity(warden);
                warden.tame(player);
                summonedEntity.add(warden);
            } else if (Objects.equals(CONFIG.activePet, "witch")) {
                witch.setPos(x, y, z);
                witch.setCustomName(Component.literal(CONFIG.witchName));
                world.addEntity(witch);
                witch.tame(player);
                summonedEntity.add(witch);
            } else if (Objects.equals(CONFIG.activePet, "zombie")) {
                zombie.setPos(x, y, z);
                zombie.setCustomName(Component.literal(CONFIG.zombieName));
                world.addEntity(zombie);
                zombie.tame(player);
                summonedEntity.add(zombie);
            } else if (Objects.equals(CONFIG.activePet, "zombie_villager")) {
                zombieVillager.setPos(x, y, z);
                zombieVillager.setCustomName(Component.literal(CONFIG.zombieVillagerName));
                world.addEntity(zombieVillager);
                zombieVillager.tame(player);
                summonedEntity.add(zombieVillager);
            } else if (Objects.equals(CONFIG.activePet, "husk")) {
                husk.setPos(x, y ,z);
                husk.setCustomName(Component.literal(CONFIG.huskName));
                world.addEntity(husk);
                husk.tame(player);
                summonedEntity.add(husk);
            } else if (Objects.equals(CONFIG.activePet, "drowned")) {
                drowned.setPos(x, y, z);
                drowned.setCustomName(Component.literal(CONFIG.drownedName));
                world.addEntity(drowned);
                drowned.tame(player);
                summonedEntity.add(drowned);
            } else if (Objects.equals(CONFIG.activePet, "bogged")) {
                bogged.setPos(x, y, z);
                bogged.setCustomName(Component.literal(CONFIG.boggedName));
                world.addEntity(bogged);
                bogged.tame(player);
                summonedEntity.add(bogged);
            } else if (Objects.equals(CONFIG.activePet, "parched")) {
                parched.setPos(x, y, z);
                parched.setCustomName(Component.literal(CONFIG.parchedName));
                world.addEntity(parched);
                parched.tame(player);
                summonedEntity.add(parched);
            } else if (Objects.equals(CONFIG.activePet, "stray")) {
                stray.setPos(x, y, z);
                stray.setCustomName(Component.literal(CONFIG.strayName));
                world.addEntity(stray);
                stray.tame(player);
                summonedEntity.add(stray);
            } else if (Objects.equals(CONFIG.activePet, "wither_skeleton")) {
                witherSkeleton.setPos(x, y, z);
                witherSkeleton.setCustomName(Component.literal(CONFIG.witherSkeletonName));
                world.addEntity(witherSkeleton);
                witherSkeleton.tame(player);
                summonedEntity.add(witherSkeleton);
            } else if (Objects.equals(CONFIG.activePet, "ender_dragon")) {
                enderDragon.setPos(x, y, z);
                enderDragon.setCustomName(Component.literal(CONFIG.enderDragonName));
                world.addEntity(enderDragon);
                enderDragon.tame(player);
                summonedEntity.add(enderDragon);
            } else if (Objects.equals(CONFIG.activePet, "wither")) {
                wither.setPos(x, y, z);
                wither.setCustomName(Component.literal(CONFIG.witherName));
                world.addEntity(wither);
                wither.tame(player);
                summonedEntity.add(wither);
            }
        }

    }

    public static void refreshPetNames() {
        if (Objects.equals(CONFIG.activePet, "cat") && cat != null && !cat.getPlainTextName().equals(CONFIG.catName)) {
            cat.setCustomName(Component.literal(CONFIG.catName));
        } else if (Objects.equals(CONFIG.activePet, "duck") && duck != null && !duck.getPlainTextName().equals(CONFIG.duckName)) {
            duck.setCustomName(Component.literal(CONFIG.duckName));
        } else if (Objects.equals(CONFIG.activePet, "racoon") && racoon != null && !racoon.getPlainTextName().equals(CONFIG.racoonName)) {
            racoon.setCustomName(Component.literal(CONFIG.racoonName));
        } else if (Objects.equals(CONFIG.activePet, "penguin") && penguin != null && !penguin.getPlainTextName().equals(CONFIG.penguinName)) {
            penguin.setCustomName(Component.literal(CONFIG.penguinName));
        } else if (Objects.equals(CONFIG.activePet, "sheep") && sheep != null && !sheep.getPlainTextName().equals(CONFIG.sheepName)) {
            sheep.setCustomName(Component.literal(CONFIG.sheepName));
        } else if (Objects.equals(CONFIG.activePet, "allay") && allay != null && !allay.getPlainTextName().equals(CONFIG.allayName)) {
            allay.setCustomName(Component.literal(CONFIG.allayName));
        } else if (Objects.equals(CONFIG.activePet, "armadillo") && armadillo != null && !armadillo.getPlainTextName().equals(CONFIG.armadilloName)) {
            armadillo.setCustomName(Component.literal(CONFIG.armadilloName));
        } else if (Objects.equals(CONFIG.activePet, "axolotl") && axolotl != null && !axolotl.getPlainTextName().equals(CONFIG.axolotlName)) {
            axolotl.setCustomName(Component.literal(CONFIG.axolotlName));
        } else if (Objects.equals(CONFIG.activePet, "bat") && bat != null && !bat.getPlainTextName().equals(CONFIG.batName)) {
            bat.setCustomName(Component.literal(CONFIG.batName));
        } else if (Objects.equals(CONFIG.activePet, "camel") && camel != null && !camel.getPlainTextName().equals(CONFIG.camelName)) {
            camel.setCustomName(Component.literal(CONFIG.camelName));
        } else if (Objects.equals(CONFIG.activePet, "chicken") && chicken != null && !chicken.getPlainTextName().equals(CONFIG.chickenName)) {
            chicken.setCustomName(Component.literal(CONFIG.chickenName));
        } else if (Objects.equals(CONFIG.activePet, "cod") && cod != null && !cod.getPlainTextName().equals(CONFIG.codName)) {
            cod.setCustomName(Component.literal(CONFIG.codName));
        } else if (Objects.equals(CONFIG.activePet, "copper_golem") && copperGolem != null && !copperGolem.getPlainTextName().equals(CONFIG.copperGolemName)) {
            copperGolem.setCustomName(Component.literal(CONFIG.copperGolemName));
        } else if (Objects.equals(CONFIG.activePet, "cow") && cow != null && !cow.getPlainTextName().equals(CONFIG.cowName)) {
            cow.setCustomName(Component.literal(CONFIG.cowName));
        } else if (Objects.equals(CONFIG.activePet, "donkey") && donkey != null && !donkey.getPlainTextName().equals(CONFIG.donkeyName)) {
            donkey.setCustomName(Component.literal(CONFIG.donkeyName));
        } else if (Objects.equals(CONFIG.activePet, "frog") && frog != null && !frog.getPlainTextName().equals(CONFIG.frogName)) {
            frog.setCustomName(Component.literal(CONFIG.frogName));
        } else if (Objects.equals(CONFIG.activePet, "horse") && horse != null && !horse.getPlainTextName().equals(CONFIG.horseName)) {
            horse.setCustomName(Component.literal(CONFIG.horseName));
        } else if (Objects.equals(CONFIG.activePet, "mooshroom") && mooshroom != null && !mooshroom.getPlainTextName().equals(CONFIG.mooshroomName)) {
            mooshroom.setCustomName(Component.literal(CONFIG.mooshroomName));
        } else if (Objects.equals(CONFIG.activePet, "parrot") && parrot != null && !parrot.getPlainTextName().equals(CONFIG.parrotName)) {
            parrot.setCustomName(Component.literal(CONFIG.parrotName));
        } else if (Objects.equals(CONFIG.activePet, "pig") && pig != null && !pig.getPlainTextName().equals(CONFIG.pigName)) {
            pig.setCustomName(Component.literal(CONFIG.pigName));
        } else if (Objects.equals(CONFIG.activePet, "rabbit") && rabbit != null && !rabbit.getPlainTextName().equals(CONFIG.rabbitName)) {
            rabbit.setCustomName(Component.literal(CONFIG.rabbitName));
        } else if (Objects.equals(CONFIG.activePet, "salmon") && salmon != null && !salmon.getPlainTextName().equals(CONFIG.salmonName)) {
            salmon.setCustomName(Component.literal(CONFIG.salmonName));
        } else if (Objects.equals(CONFIG.activePet, "sniffer") && sniffer != null && !sniffer.getPlainTextName().equals(CONFIG.snifferName)) {
            sniffer.setCustomName(Component.literal(CONFIG.snifferName));
        } else if (Objects.equals(CONFIG.activePet, "snow_golem") && snowGolem != null && !snowGolem.getPlainTextName().equals(CONFIG.snowGolemName)) {
            snowGolem.setCustomName(Component.literal(CONFIG.snowGolemName));
        } else if (Objects.equals(CONFIG.activePet, "squid") && squid != null && !squid.getPlainTextName().equals(CONFIG.squidName)) {
            squid.setCustomName(Component.literal(CONFIG.squidName));
        } else if (Objects.equals(CONFIG.activePet, "strider") && strider != null && !strider.getPlainTextName().equals(CONFIG.striderName)) {
            strider.setCustomName(Component.literal(CONFIG.striderName));
        } else if (Objects.equals(CONFIG.activePet, "tadpole") && tadpole != null && !tadpole.getPlainTextName().equals(CONFIG.tadpoleName)) {
            tadpole.setCustomName(Component.literal(CONFIG.tadpoleName));
        } else if (Objects.equals(CONFIG.activePet, "turtle") && turtle != null && !turtle.getPlainTextName().equals(CONFIG.turtleName)) {
            turtle.setCustomName(Component.literal(CONFIG.turtleName));
        } else if (Objects.equals(CONFIG.activePet, "villager") && villager != null && !villager.getPlainTextName().equals(CONFIG.villagerName)) {
            villager.setCustomName(Component.literal(CONFIG.villagerName));
        } else if (Objects.equals(CONFIG.activePet, "wandering_trader") && wanderingTrader != null && !wanderingTrader.getPlainTextName().equals(CONFIG.wanderingTraderName)) {
            wanderingTrader.setCustomName(Component.literal(CONFIG.wanderingTraderName));
        } else if (Objects.equals(CONFIG.activePet, "bee") && bee != null && !bee.getPlainTextName().equals(CONFIG.beeName)) {
            bee.setCustomName(Component.literal(CONFIG.beeName));
        } else if (Objects.equals(CONFIG.activePet, "cave_spider") && caveSpider != null && !caveSpider.getPlainTextName().equals(CONFIG.dolphinName)) {
            caveSpider.setCustomName(Component.literal(CONFIG.caveSpiderName));
        } else if (Objects.equals(CONFIG.activePet, "dolphin") && dolphin != null && !dolphin.getPlainTextName().equals(CONFIG.dolphinName)) {
            dolphin.setCustomName(Component.literal(CONFIG.dolphinName));
        } else if (Objects.equals(CONFIG.activePet, "enderman") && enderman != null && !enderman.getPlainTextName().equals(CONFIG.endermanName)) {
            enderman.setCustomName(Component.literal(CONFIG.endermanName));
        } else if (Objects.equals(CONFIG.activePet, "fox") && fox != null && !fox.getPlainTextName().equals(CONFIG.foxName)) {
            fox.setCustomName(Component.literal(CONFIG.foxName));
        } else if (Objects.equals(CONFIG.activePet, "goat") && goat != null && !goat.getPlainTextName().equals(CONFIG.goatName)) {
            goat.setCustomName(Component.literal(CONFIG.goatName));
        } else if (Objects.equals(CONFIG.activePet, "iron_golem") && ironGolem != null && !ironGolem.getPlainTextName().equals(CONFIG.ironGolemName)) {
            ironGolem.setCustomName(Component.literal(CONFIG.ironGolemName));
        } else if (Objects.equals(CONFIG.activePet, "llama") && llama != null && !llama.getPlainTextName().equals(CONFIG.llamaName)) {
            llama.setCustomName(Component.literal(CONFIG.llamaName));
        } else if (Objects.equals(CONFIG.activePet, "nautilus") && nautilus != null && !nautilus.getPlainTextName().equals(CONFIG.nautilusName)) {
            nautilus.setCustomName(Component.literal(CONFIG.nautilusName));
        } else if (Objects.equals(CONFIG.activePet, "panda") && panda != null && !panda.getPlainTextName().equals(CONFIG.pandaName)) {
            panda.setCustomName(Component.literal(CONFIG.pandaName));
        } else if (Objects.equals(CONFIG.activePet, "piglin") && piglin != null && !piglin.getPlainTextName().equals(CONFIG.piglinName)) {
            piglin.setCustomName(Component.literal(CONFIG.piglinName));
        } else if (Objects.equals(CONFIG.activePet, "polar_bear") && polarBear != null && !polarBear.getPlainTextName().equals(CONFIG.polarBearName)) {
            polarBear.setCustomName(Component.literal(CONFIG.polarBearName));
        } else if (Objects.equals(CONFIG.activePet, "pufferfish") && pufferFish != null && !pufferFish.getPlainTextName().equals(CONFIG.pufferFishName)) {
            pufferFish.setCustomName(Component.literal(CONFIG.pufferFishName));
        } else if (Objects.equals(CONFIG.activePet, "spider") && spider != null && !spider.getPlainTextName().equals(CONFIG.spiderName)) {
            spider.setCustomName(Component.literal(CONFIG.spiderName));
        } else if (Objects.equals(CONFIG.activePet, "wolf") && wolf != null && !wolf.getPlainTextName().equals(CONFIG.wolfName)) {
            wolf.setCustomName(Component.literal(CONFIG.wolfName));
        } else if (Objects.equals(CONFIG.activePet, "blaze") && blaze != null && !blaze.getPlainTextName().equals(CONFIG.blazeName)) {
            blaze.setCustomName(Component.literal(CONFIG.blazeName));
        } else if (Objects.equals(CONFIG.activePet, "breeze") && breeze != null && !breeze.getPlainTextName().equals(CONFIG.breezeName)) {
            breeze.setCustomName(Component.literal(CONFIG.breezeName));
        } else if (Objects.equals(CONFIG.activePet, "creaking") && creaking != null && !creaking.getPlainTextName().equals(CONFIG.creakingName)) {
            creaking.setCustomName(Component.literal(CONFIG.creakingName));
        } else if (Objects.equals(CONFIG.activePet, "creeper") && creeper != null && !creeper.getPlainTextName().equals(CONFIG.creeperName)) {
            creeper.setCustomName(Component.literal(CONFIG.creeperName));
        } else if (Objects.equals(CONFIG.activePet, "elder_guardian") && elderGuardian != null && !elderGuardian.getPlainTextName().equals(CONFIG.elderGuardianName)) {
            elderGuardian.setCustomName(Component.literal(CONFIG.elderGuardianName));
        } else if (Objects.equals(CONFIG.activePet, "endermite") && endermite != null && !endermite.getPlainTextName().equals(CONFIG.endermiteName)) {
            endermite.setCustomName(Component.literal(CONFIG.endermiteName));
        } else if (Objects.equals(CONFIG.activePet, "evoker") && evoker != null && !evoker.getPlainTextName().equals(CONFIG.evokerName)) {
            evoker.setCustomName(Component.literal(CONFIG.evokerName));
        } else if (Objects.equals(CONFIG.activePet, "happy_ghast") && happyGhast != null && !happyGhast.getPlainTextName().equals(CONFIG.happyGhastName)) {
            happyGhast.setCustomName(Component.literal(CONFIG.activePet));
        } else if (Objects.equals(CONFIG.activePet, "ghast") && ghast != null && !ghast.getPlainTextName().equals(CONFIG.ghastName)) {
            ghast.setCustomName(Component.literal(CONFIG.ghastName));
        } else if (Objects.equals(CONFIG.activePet, "guardian") && guardian != null && !guardian.getPlainTextName().equals(CONFIG.guardianName)) {
            guardian.setCustomName(Component.literal(CONFIG.guardianName));
        } else if (Objects.equals(CONFIG.activePet, "hoglin") && hoglin != null && !hoglin.getPlainTextName().equals(CONFIG.hoglinName)) {
            hoglin.setCustomName(Component.literal(CONFIG.hoglinName));
        } else if (Objects.equals(CONFIG.activePet, "magma_cube") && magmaCube != null && !magmaCube.getPlainTextName().equals(CONFIG.magmaCubeName)) {
            magmaCube.setCustomName(Component.literal(CONFIG.magmaCubeName));
        } else if (Objects.equals(CONFIG.activePet, "phantom") && phantom != null && !phantom.getPlainTextName().equals(CONFIG.phantomName)) {
            phantom.setCustomName(Component.literal(CONFIG.phantomName));
        } else if (Objects.equals(CONFIG.activePet, "pillager") && pillager != null && !pillager.getPlainTextName().equals(CONFIG.pillagerName)) {
            pillager.setCustomName(Component.literal(CONFIG.pillagerName));
        } else if (Objects.equals(CONFIG.activePet, "ravager") && ravager != null && !ravager.getPlainTextName().equals(CONFIG.ravagerName)) {
            ravager.setCustomName(Component.literal(CONFIG.ravagerName));
        } else if (Objects.equals(CONFIG.activePet, "shulker") && shulker != null && !shulker.getPlainTextName().equals(CONFIG.shulkerName)) {
            shulker.setCustomName(Component.literal(CONFIG.shulkerName));
        } else if (Objects.equals(CONFIG.activePet, "silverfish") && silverfish != null && !silverfish.getPlainTextName().equals(CONFIG.silverfishName)) {
            silverfish.setCustomName(Component.literal(CONFIG.silverfishName));
        } else if (Objects.equals(CONFIG.activePet, "skeleton") && skeleton != null && !skeleton.getPlainTextName().equals(CONFIG.skeletonName)) {
            skeleton.setCustomName(Component.literal(CONFIG.skeletonName));
        } else if (Objects.equals(CONFIG.activePet, "slime") && slime != null && !slime.getPlainTextName().equals(CONFIG.slimeName)) {
            slime.setCustomName(Component.literal(CONFIG.slimeName));
        } else if (Objects.equals(CONFIG.activePet, "vex") && vex != null && !vex.getPlainTextName().equals(CONFIG.vexName)) {
            vex.setCustomName(Component.literal(CONFIG.vexName));
        } else if (Objects.equals(CONFIG.activePet, "vindicator") && vindicator != null && !vindicator.getPlainTextName().equals(CONFIG.vindicatorName)) {
            vindicator.setCustomName(Component.literal(CONFIG.vindicatorName));
        } else if (Objects.equals(CONFIG.activePet, "husk") && husk != null && !husk.getPlainTextName().equals(CONFIG.huskName)) {
            husk.setCustomName(Component.literal(CONFIG.huskName));
        } else if (Objects.equals(CONFIG.activePet, "drowned") && drowned != null && !drowned.getPlainTextName().equals(CONFIG.drownedName)) {
            drowned.setCustomName(Component.literal(CONFIG.drownedName));
        } else if (Objects.equals(CONFIG.activePet, "bogged") && bogged != null && !bogged.getPlainTextName().equals(CONFIG.boggedName)) {
            bogged.setCustomName(Component.literal(CONFIG.boggedName));
        } else if (Objects.equals(CONFIG.activePet, "parched") && parched != null && !parched.getPlainTextName().equals(CONFIG.parchedName)) {
            parched.setCustomName(Component.literal(CONFIG.parchedName));
        } else if (Objects.equals(CONFIG.activePet, "stray") && stray != null && !stray.getPlainTextName().equals(CONFIG.strayName)) {
            stray.setCustomName(Component.literal(CONFIG.strayName));
        } else if (Objects.equals(CONFIG.activePet, "wither_skeleton") && witherSkeleton != null && !witherSkeleton.getPlainTextName().equals(CONFIG.witherSkeletonName)) {
            witherSkeleton.setCustomName(Component.literal(CONFIG.witherSkeletonName));
        } else if (Objects.equals(CONFIG.activePet, "ender_dragon") && enderDragon != null && !enderDragon.getPlainTextName().equals(CONFIG.enderDragonName)) {
            enderDragon.setCustomName(Component.literal(CONFIG.enderDragonName));
        } else if (Objects.equals(CONFIG.activePet, "wither") && wither != null && !wither.getPlainTextName().equals(CONFIG.witherName)) {
            wither.setCustomName(Component.literal(CONFIG.witherName));
        }
    }

    private static void updateSuggestions(Minecraft client) {
        List<String> skinSuggestions = switch (CONFIG.activePet) {
            case "duck" -> DUCK_SKINS;
            case "racoon" -> RACOON_SKINS;
            case "cat" -> CAT_SKINS;
            case "axolotl" -> AXOLOTL_SKINS;
            case "camel" -> CAMEL_SKINS;
            case "chicken", "cow", "frog", "pig" -> TEMPERATE_COLD_WARM;
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
            case "magma_cube", "slime" -> SLIME_LIKE_SKINS;
            case "zombie_villager" -> VILLAGER_SKINS;
            case "shulker" -> SHULKER_SKINS;
            case "wither" -> WITHER_SKINS;
            case null, default -> EMPTY_LIST;
        };

        currentSuggestions.clear();
        currentSuggestions.addAll(skinSuggestions);
        refreshChatSuggestor(client);
    }

    private static void refreshChatSuggestor(Minecraft client) {
        Screen var2 = client.screen;
        if (var2 instanceof ChatScreen chatScreen) {
            ((ChatAccessor) chatScreen).getChatInputSuggestor().updateCommandInfo();
        }

    }

    public void onInitializeClient() {
        AutoConfig.register(PetsConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();
        this.createSkinCommand();
        this.checkForNullObjects();
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("pet").then(ClientCommandManager.argument("preference", StringArgumentType.string()).suggests(SuggestionProviders.cast(ON_OFF)).executes((context) -> {
            String preference = StringArgumentType.getString(context, "preference");
            if (Objects.equals(preference, "off")) {
                CONFIG.petSummonedPreference = false;
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §7Pet §coff."));
                AutoConfig.getConfigHolder(PetsConfig.class).save();
            } else if (Objects.equals(preference, "on")) {
                CONFIG.petSummonedPreference = true;
                AutoConfig.getConfigHolder(PetsConfig.class).save();
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §7Pet §aon."));
            } else {
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §c§lUnknown value " + preference + "! Possible values: §r§aon, §6off"));
            }

            return 1;
        }))));
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("petname").then(ClientCommandManager.argument("name", StringArgumentType.greedyString()).executes((context) -> {
            String name = StringArgumentType.getString(context, "name");
            if (!summonedEntity.isEmpty()) {
                switch (CONFIG.activePet) {
                    case "penguin" -> CONFIG.penguinName = name;
                    case "duck" -> CONFIG.duckName = name;
                    case "racoon" -> CONFIG.racoonName = name;
                    case "cat" -> CONFIG.catName = name;
                    case "sheep" -> CONFIG.sheepName = name;
                    case "allay" -> CONFIG.allayName = name;
                    case "armadillo" -> CONFIG.armadilloName = name;
                    case "bat" -> CONFIG.batName = name;
                    case "camel" -> CONFIG.camelName = name;
                    case "chicken" -> CONFIG.chickenSkin = name;
                    case "cod" -> CONFIG.codName = name;
                    case "copper_golem" -> CONFIG.copperGolemName = name;
                    case "cow" -> CONFIG.cowName = name;
                    case "donkey" -> CONFIG.donkeyName = name;
                    case "frog" -> CONFIG.frogName = name;
                    case "horse" -> CONFIG.horseName = name;
                    case "mooshroom" -> CONFIG.mooshroomName = name;
                    case "mule" -> CONFIG.muleName = name;
                    case "parrot" -> CONFIG.parrotName = name;
                    case "pig" -> CONFIG.pigName = name;
                    case "rabbit" -> CONFIG.rabbitName = name;
                    case "salmon" -> CONFIG.salmonName = name;
                    case "sniffer" -> CONFIG.snifferName = name;
                    case "snow_golem" -> CONFIG.snowGolemName = name;
                    case "squid" -> CONFIG.squidName = name;
                    case "strider" -> CONFIG.striderName = name;
                    case "tadpole" -> CONFIG.tadpoleName = name;
                    case "tropical_fish" -> CONFIG.tropicalFishName = name;
                    case "turtle" -> CONFIG.turtleName = name;
                    case "villager" -> CONFIG.villagerName = name;
                    case "wandering_trader" -> CONFIG.wanderingTraderName = name;
                    case "bee" -> CONFIG.beeName = name;
                    case "cave_spider" -> CONFIG.caveSpiderName = name;
                    case "dolphin" -> CONFIG.dolphinName = name;
                    case "enderman" -> CONFIG.endermanName = name;
                    case "fox" -> CONFIG.foxName = name;
                    case "goat" -> CONFIG.goatName = name;
                    case "iron_golem" -> CONFIG.ironGolemName = name;
                    case "llama" -> CONFIG.llamaName = name;
                    case "nautilus" -> CONFIG.nautilusName = name;
                    case "panda" -> CONFIG.pandaName = name;
                    case "piglin" -> CONFIG.piglinName = name;
                    case "polar_bear" -> CONFIG.polarBearName = name;
                    case "pufferfish" -> CONFIG.pufferFishName = name;
                    case "spider" -> CONFIG.spiderName = name;
                    case "wolf" -> CONFIG.wolfName = name;
                    case "blaze" -> CONFIG.blazeName = name;
                    case "breeze" -> CONFIG.breezeName = name;
                    case "creaking" -> CONFIG.creakingName = name;
                    case "creeper" -> CONFIG.creeperName = name;
                    case "elder_guardian" -> CONFIG.elderGuardianName = name;
                    case "endermite" -> CONFIG.endermiteName = name;
                    case "evoker" -> CONFIG.evokerName = name;
                    case "happy_ghast" -> CONFIG.happyGhastName = name;
                    case "ghast" -> CONFIG.ghastName = name;
                    case "guardian" -> CONFIG.guardianName = name;
                    case "hoglin" -> CONFIG.hoglinName = name;
                    case "magma_cube" -> CONFIG.magmaCubeName = name;
                    case "phantom" -> CONFIG.phantomName = name;
                    case "pillager" -> CONFIG.pillagerName = name;
                    case "ravager" -> CONFIG.ravagerName = name;
                    case "shulker" -> CONFIG.shulkerName = name;
                    case "silverfish" -> CONFIG.silverfishName = name;
                    case "skeleton" -> CONFIG.skeletonName = name;
                    case "slime" -> CONFIG.slimeName = name;
                    case "vex" -> CONFIG.vexName = name;
                    case "vindicator" -> CONFIG.vindicatorName = name;
                    case "warden" -> CONFIG.wardenName = name;
                    case "witch" -> CONFIG.witchName = name;
                    case "zombie" -> CONFIG.zombieName = name;
                    case "zombie_villager" -> CONFIG.zombieVillagerName = name;
                    case "husk" -> CONFIG.huskName = name;
                    case "drowned" -> CONFIG.drownedName = name;
                    case "bogged" -> CONFIG.boggedName = name;
                    case "parched" -> CONFIG.parchedName = name;
                    case "stray" -> CONFIG.strayName = name;
                    case "wither_skeleton" -> CONFIG.witherSkeletonName = name;
                    case "ender_dragon" -> CONFIG.enderDragonName = name;
                    case "wither" -> CONFIG.witherName = name;
                }
                AutoConfig.getConfigHolder(PetsConfig.class).save();
            }

            return 1;
        }))));

        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("pethelp").executes(context -> {
            context.getSource().getPlayer().displayClientMessage(Component.literal("""
                    §b[PetsMod] §aPossible commands: \
                    
                    §a/pethelp: §rdisplays a list of commands\
                    
                    §a/pet <on/off> §rtoggles whether your pet will appear or not\
                    
                    §a/petspecies <species>: §rchanges the species of your pet\
                    
                    §a/petskin <skin>: §rchanges the skin of your selected pet\
                    
                    §a/teleportpet: §rteleports your pet to you. will not work if you are not on the ground.\
                    
                    §a/petname: §rchanges the name of your currently selected pet\
                    
                    """), false);
            return 1;
        }))));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("teleportpet").executes((context) -> {
            if (duck != null && Objects.equals(CONFIG.activePet, "duck")) {
                duck.tryToTeleportToOwner();
            } else if (racoon != null && Objects.equals(CONFIG.activePet, "racoon")) {
                racoon.tryToTeleportToOwner();
            } else if (penguin != null && Objects.equals(CONFIG.activePet, "penguin")) {
                penguin.tryToTeleportToOwner();
            } else if (sheep != null && Objects.equals(CONFIG.activePet, "sheep")) {
                sheep.tryToTeleportToOwner();
            } else if (cat != null && Objects.equals(CONFIG.activePet, "cat")) {
                cat.tryToTeleportToOwner();
            } else if (allay != null && Objects.equals(CONFIG.activePet, "allay")) {
                allay.tryToTeleportToOwner();
            } else if (armadillo != null && Objects.equals(CONFIG.activePet, "armadillo")) {
                armadillo.tryToTeleportToOwner();
            } else if (axolotl != null && Objects.equals(CONFIG.activePet, "axolotl")) {
                axolotl.tryToTeleportToOwner();
            } else if (bat != null && Objects.equals(CONFIG.activePet, "bat")) {
                bat.tryToTeleportToOwner();
            } else if (camel != null && Objects.equals(CONFIG.activePet, "camel")) {
                camel.tryToTeleportToOwner();
            } else if (chicken != null && Objects.equals(CONFIG.activePet, "chicken")) {
                chicken.tryToTeleportToOwner();
            } else if (cod != null && Objects.equals(CONFIG.activePet, "cod")) {
                cod.tryToTeleportToOwner();
            } else if (copperGolem != null && Objects.equals(CONFIG.activePet, "copper_golem")) {
                copperGolem.tryToTeleportToOwner();
            } else if (cow != null && Objects.equals(CONFIG.activePet, "cow")) {
                cow.tryToTeleportToOwner();
            } else if (donkey != null && Objects.equals(CONFIG.activePet, "donkey")) {
                donkey.tryToTeleportToOwner();
            } else if (frog != null && Objects.equals(CONFIG.activePet, "frog")) {
                frog.tryToTeleportToOwner();
            } else if (horse != null && Objects.equals(CONFIG.activePet, "horse")) {
                horse.tryToTeleportToOwner();
            } else if (mooshroom != null && Objects.equals(CONFIG.activePet, "mooshroom")) {
                mooshroom.tryToTeleportToOwner();
            } else if (parrot != null && Objects.equals(CONFIG.activePet, "parrot")) {
                parrot.tryToTeleportToOwner();
            } else if (pig != null && Objects.equals(CONFIG.activePet, "pig")) {
                pig.tryToTeleportToOwner();
            } else if (rabbit != null && Objects.equals(CONFIG.activePet, "rabbit")) {
                rabbit.tryToTeleportToOwner();
            } else if (salmon != null && Objects.equals(CONFIG.activePet, "salmon")) {
                salmon.tryToTeleportToOwner();
            } else if (sniffer != null && Objects.equals(CONFIG.activePet, "sniffer")) {
                sniffer.tryToTeleportToOwner();
            } else if (snowGolem != null && Objects.equals(CONFIG.activePet, "snow_golem")) {
                snowGolem.tryToTeleportToOwner();
            } else if (squid != null && Objects.equals(CONFIG.activePet, "squid")) {
                squid.tryToTeleportToOwner();
            } else if (strider != null && Objects.equals(CONFIG.activePet, "strider")) {
                strider.tryToTeleportToOwner();
            } else if (tadpole != null && Objects.equals(CONFIG.activePet, "tadpole")) {
                tadpole.tryToTeleportToOwner();
            } else if (turtle != null && Objects.equals(CONFIG.activePet, "turtle")) {
                turtle.tryToTeleportToOwner();
            } else if (villager != null && Objects.equals(CONFIG.activePet, "villager")) {
                villager.tryToTeleportToOwner();
            } else if (wanderingTrader != null && Objects.equals(CONFIG.activePet, "wandering_trader")) {
                wanderingTrader.tryToTeleportToOwner();
            } else if (bee != null && Objects.equals(CONFIG.activePet, "bee")) {
                bee.tryToTeleportToOwner();
            } else if (caveSpider != null && Objects.equals(CONFIG.activePet, "cave_spider")) {
                caveSpider.tryToTeleportToOwner();
            } else if (dolphin != null && Objects.equals(CONFIG.activePet, "dolphin")) {
                dolphin.tryToTeleportToOwner();
            } else if (enderman != null && Objects.equals(CONFIG.activePet, "enderman")) {
                enderman.tryToTeleportToOwner();
            } else if (fox != null && Objects.equals(CONFIG.activePet, "fox")) {
                fox.tryToTeleportToOwner();
            } else if (goat != null && Objects.equals(CONFIG.activePet, "goat")) {
                goat.tryToTeleportToOwner();
            } else if (ironGolem != null && Objects.equals(CONFIG.activePet, "iron_golem")) {
                ironGolem.tryToTeleportToOwner();
            } else if (llama != null && Objects.equals(CONFIG.activePet, "llama")) {
                llama.tryToTeleportToOwner();
            } else if (nautilus != null && Objects.equals(CONFIG.activePet, "nautilus")) {
                nautilus.tryToTeleportToOwner();
            } else if (panda != null && Objects.equals(CONFIG.activePet, "panda")) {
                panda.tryToTeleportToOwner();
            } else if (piglin != null && Objects.equals(CONFIG.activePet, "piglin")) {
                piglin.tryToTeleportToOwner();
            } else if (polarBear != null && Objects.equals(CONFIG.activePet, "polar_bear")) {
                polarBear.tryToTeleportToOwner();
            } else if (pufferFish != null && Objects.equals(CONFIG.activePet, "pufferfish")) {
                pufferFish.tryToTeleportToOwner();
            } else if (spider != null && Objects.equals(CONFIG.activePet, "spider")) {
                spider.tryToTeleportToOwner();
            } else if (wolf != null && Objects.equals(CONFIG.activePet, "wolf")) {
                wolf.tryToTeleportToOwner();
            } else if (blaze != null && Objects.equals(CONFIG.activePet, "blaze")) {
                blaze.tryToTeleportToOwner();
            } else if (breeze != null && Objects.equals(CONFIG.activePet, "breeze")) {
                breeze.tryToTeleportToOwner();
            } else if (creaking != null && Objects.equals(CONFIG.activePet, "creaking")) {
                creaking.tryToTeleportToOwner();
            } else if (creeper != null && Objects.equals(CONFIG.activePet, "creeper")) {
                creeper.tryToTeleportToOwner();
            } else if (elderGuardian != null && Objects.equals(CONFIG.activePet, "elder_guardian")) {
                elderGuardian.tryToTeleportToOwner();
            } else if (endermite != null && Objects.equals(CONFIG.activePet, "endermite")) {
                endermite.tryToTeleportToOwner();
            } else if (evoker != null && Objects.equals(CONFIG.activePet, "evoker")) {
                evoker.tryToTeleportToOwner();
            } else if (happyGhast != null && Objects.equals(CONFIG.activePet, "happy_ghast")) {
                happyGhast.tryToTeleportToOwner();
            } else if (ghast != null && Objects.equals(CONFIG.activePet, "ghast")) {
                ghast.tryToTeleportToOwner();
            } else if (guardian != null && Objects.equals(CONFIG.activePet, "guardian")) {
                guardian.tryToTeleportToOwner();
            } else if (hoglin != null && Objects.equals(CONFIG.activePet, "hoglin")) {
                hoglin.tryToTeleportToOwner();
            } else if (magmaCube != null && Objects.equals(CONFIG.activePet, "magma_cube")) {
                magmaCube.tryToTeleportToOwner();
            } else if (phantom != null && Objects.equals(CONFIG.activePet, "phantom")) {
                phantom.tryToTeleportToOwner();
            } else if (pillager != null && Objects.equals(CONFIG.activePet, "pillager")) {
                pillager.tryToTeleportToOwner();
            } else if (ravager != null && Objects.equals(CONFIG.activePet, "ravager")) {
                ravager.tryToTeleportToOwner();
            } else if (shulker != null && Objects.equals(CONFIG.activePet, "shulker")) {
                shulker.tryToTeleportToOwner();
            } else if (silverfish != null && Objects.equals(CONFIG.activePet, "silverfish")) {
                silverfish.tryToTeleportToOwner();
            } else if (skeleton != null && Objects.equals(CONFIG.activePet, "skeleton")) {
                skeleton.tryToTeleportToOwner();
            } else if (slime != null && Objects.equals(CONFIG.activePet, "slime")) {
                slime.tryToTeleportToOwner();
            } else if (vex != null && Objects.equals(CONFIG.activePet, "vex")) {
                vex.tryToTeleportToOwner();
            } else if (vindicator != null && Objects.equals(CONFIG.activePet, "vindicator")) {
                vindicator.tryToTeleportToOwner();
            } else if (warden != null && Objects.equals(CONFIG.activePet, "warden")) {
                warden.tryToTeleportToOwner();
            } else if (witch != null && Objects.equals(CONFIG.activePet, "witch")) {
                witch.tryToTeleportToOwner();
            } else if (zombie != null && Objects.equals(CONFIG.activePet, "zombie")) {
                zombie.tryToTeleportToOwner();
            } else if (zombieVillager != null && Objects.equals(CONFIG.activePet, "zombie_villager")) {
                zombieVillager.tryToTeleportToOwner();
            } else if (husk != null && Objects.equals(CONFIG.activePet, "husk")) {
                husk.tryToTeleportToOwner();
            } else if (drowned != null && Objects.equals(CONFIG.activePet, "drowned")) {
                drowned.tryToTeleportToOwner();
            } else if (bogged != null && Objects.equals(CONFIG.activePet, "bogged")) {
                bogged.tryToTeleportToOwner();
            } else if (parched != null && Objects.equals(CONFIG.activePet, "parched")) {
                parched.tryToTeleportToOwner();
            } else if (stray != null && Objects.equals(CONFIG.activePet, "stray")) {
                stray.tryToTeleportToOwner();
            } else if (witherSkeleton != null && Objects.equals(CONFIG.activePet, "wither_skeleton")) {
                witherSkeleton.tryToTeleportToOwner();
            } else if (enderDragon != null && Objects.equals(CONFIG.activePet, "ender_dragon")) {
                enderDragon.tryToTeleportToOwner();
            } else if (wither != null && Objects.equals(CONFIG.activePet, "wither")) {
                wither.tryToTeleportToOwner();
            }

            return 1;
        })));
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryaccess) -> dispatcher.register(ClientCommandManager.literal("petspecies").then(ClientCommandManager.argument("species", StringArgumentType.greedyString()).suggests(PETS).executes((context) -> {
            boolean isValid = true;
            String species = StringArgumentType.getString(context, "species");
            if (Objects.equals(species, "duck")) {
                summonedEntity.clear();
                summonedEntity.add(duck);
                CONFIG.activePet = "duck";
            } else if (Objects.equals(species, "racoon")) {
                summonedEntity.clear();
                summonedEntity.add(racoon);
                CONFIG.activePet = "racoon";
            } else if (Objects.equals(species, "penguin")) {
                summonedEntity.clear();
                summonedEntity.add(penguin);
                CONFIG.activePet = "penguin";
            } else if (Objects.equals(species, "sheep")) {
                summonedEntity.clear();
                summonedEntity.add(sheep);
                CONFIG.activePet = "sheep";
            } else if (Objects.equals(species, "cat")) {
                summonedEntity.clear();
                summonedEntity.add(cat);
                CONFIG.activePet = "cat";
            } else if (Objects.equals(species, "allay")) {
                summonedEntity.clear();
                summonedEntity.add(allay);
                CONFIG.activePet = "allay";
            } else if (Objects.equals(species, "armadillo")) {
                summonedEntity.clear();
                summonedEntity.add(armadillo);
                CONFIG.activePet = "armadillo";
            } else if (Objects.equals(species, "axolotl")) {
                summonedEntity.clear();
                summonedEntity.add(axolotl);
                CONFIG.activePet = "axolotl";
            } else if (Objects.equals(species, "bat")) {
                summonedEntity.clear();
                summonedEntity.add(bat);
                CONFIG.activePet = "bat";
            } else if (Objects.equals(species, "camel")) {
                summonedEntity.clear();
                summonedEntity.add(camel);
                CONFIG.activePet = "camel";
            } else if (Objects.equals(species, "chicken")) {
                summonedEntity.clear();
                summonedEntity.add(chicken);
                CONFIG.activePet = "chicken";
            } else if (Objects.equals(species, "cod")) {
                summonedEntity.clear();
                summonedEntity.add(cod);
                CONFIG.activePet = "cod";
            }
            else if (Objects.equals(species, "copper_golem") || Objects.equals(species, "copper golem")) {
                summonedEntity.clear();
                summonedEntity.add(copperGolem);
                CONFIG.activePet = "copper_golem";
            } else if (Objects.equals(species, "cow")) {
                summonedEntity.clear();
                summonedEntity.add(cow);
                CONFIG.activePet = "cow";
            } else if (Objects.equals(species, "donkey")) {
                summonedEntity.clear();
                summonedEntity.add(donkey);
                CONFIG.activePet = "donkey";
            } else if (Objects.equals(species, "frog")) {
                summonedEntity.clear();
                summonedEntity.add(frog);
                CONFIG.activePet = "frog";
            } else if (Objects.equals(species, "horse")) {
                summonedEntity.clear();
                summonedEntity.add(horse);
                CONFIG.activePet = "horse";
            } else if (Objects.equals(species, "mooshroom")) {
                summonedEntity.clear();
                summonedEntity.add(mooshroom);
                CONFIG.activePet = "mooshroom";
            } else if (Objects.equals(species, "parrot")) {
                summonedEntity.clear();
                summonedEntity.add(parrot);
                CONFIG.activePet = "parrot";
            } else if (Objects.equals(species, "pig")) {
                summonedEntity.clear();
                summonedEntity.add(pig);
                CONFIG.activePet = "pig";
            } else if (Objects.equals(species, "rabbit")) {
                summonedEntity.clear();
                summonedEntity.add(rabbit);
                CONFIG.activePet = "rabbit";
            } else if (Objects.equals(species, "salmon")) {
                summonedEntity.clear();
                summonedEntity.add(salmon);
                CONFIG.activePet = "salmon";
            } else if (Objects.equals(species, "sniffer")) {
                summonedEntity.clear();
                summonedEntity.add(sniffer);
                CONFIG.activePet = "sniffer";
            } else if (Objects.equals(species, "snow_golem") || Objects.equals(species, "snow golem")) {
                summonedEntity.clear();
                summonedEntity.add(snowGolem);
                CONFIG.activePet = "snow_golem";
            } else if (Objects.equals(species, "squid")) {
                summonedEntity.clear();
                summonedEntity.add(squid);
                CONFIG.activePet = "squid";
            } else if (Objects.equals(species, "strider")) {
                summonedEntity.clear();
                summonedEntity.add(strider);
                CONFIG.activePet = "strider";
            } else if (Objects.equals(species, "tadpole")) {
                summonedEntity.clear();
                summonedEntity.add(tadpole);
                CONFIG.activePet = "tadpole";
            } else if (Objects.equals(species, "turtle")) {
                summonedEntity.clear();
                summonedEntity.add(turtle);
                CONFIG.activePet = "turtle";
            } else if (Objects.equals(species, "villager")) {
                summonedEntity.clear();
                summonedEntity.add(villager);
                CONFIG.activePet = "villager";
            } else if (Objects.equals(species, "wandering_trader") || Objects.equals(species, "wandering trader")) {
                summonedEntity.clear();
                summonedEntity.add(wanderingTrader);
                CONFIG.activePet = "wandering_trader";
            } else if (Objects.equals(species, "bee")) {
                summonedEntity.clear();
                summonedEntity.add(bee);
                CONFIG.activePet = "bee";
            } else if (Objects.equals(species, "cave_spider") || Objects.equals(species, "cave spider")) {
                summonedEntity.clear();
                summonedEntity.add(caveSpider);
                CONFIG.activePet = "cave_spider";
            } else if (Objects.equals(species, "dolphin")) {
                summonedEntity.clear();
                summonedEntity.add(dolphin);
                CONFIG.activePet = "dolphin";
            } else if (Objects.equals(species, "enderman")) {
                summonedEntity.clear();
                summonedEntity.add(enderman);
                CONFIG.activePet = "enderman";
            } else if (Objects.equals(species, "fox")) {
                summonedEntity.clear();
                summonedEntity.add(fox);
                CONFIG.activePet = "fox";
            } else if (Objects.equals(species, "goat")) {
                summonedEntity.clear();
                summonedEntity.add(goat);
                CONFIG.activePet = "goat";
            } else if (Objects.equals(species, "iron_golem") || Objects.equals(species, "iron golem")) {
                summonedEntity.clear();
                summonedEntity.add(ironGolem);
                CONFIG.activePet = "iron_golem";
            } else if (Objects.equals(species, "llama")) {
                summonedEntity.clear();
                summonedEntity.add(llama);
                CONFIG.activePet = "llama";
            } else if (Objects.equals(species, "nautilus")) {
                summonedEntity.clear();
                summonedEntity.add(nautilus);
                CONFIG.activePet = "nautilus";
            } else if (Objects.equals(species, "panda")) {
                summonedEntity.clear();
                summonedEntity.add(panda);
                CONFIG.activePet = "panda";
            } else if (Objects.equals(species, "piglin")) {
                summonedEntity.clear();
                summonedEntity.add(piglin);
                CONFIG.activePet = "piglin";
            } else if (Objects.equals(species, "polar_bear") || Objects.equals(species, "polar bear")) {
                summonedEntity.clear();
                summonedEntity.add(polarBear);
                CONFIG.activePet = "polar_bear";
            } else if (Objects.equals(species, "pufferfish")) {
                summonedEntity.clear();
                summonedEntity.add(pufferFish);
                CONFIG.activePet = "pufferfish";
            } else if (Objects.equals(species, "spider")) {
                summonedEntity.clear();
                summonedEntity.add(spider);
                CONFIG.activePet = "spider";
            } else if (Objects.equals(species, "wolf")) {
                summonedEntity.clear();
                summonedEntity.add(wolf);
                CONFIG.activePet = "wolf";
            } else if (Objects.equals(species, "blaze")) {
                summonedEntity.clear();
                summonedEntity.add(blaze);
                CONFIG.activePet = "blaze";
            } else if (Objects.equals(species, "breeze")) {
                summonedEntity.clear();
                summonedEntity.add(breeze);
                CONFIG.activePet = "breeze";
            } else if (Objects.equals(species, "creaking")) {
                summonedEntity.clear();
                summonedEntity.add(creaking);
                CONFIG.activePet = "creaking";
            } else if (Objects.equals(species, "creeper")) {
                summonedEntity.clear();
                summonedEntity.add(creeper);
                CONFIG.activePet = "creeper";
            } else if (Objects.equals(species, "elder_guardian") || Objects.equals(species, "elder guardian")) {
                summonedEntity.clear();
                summonedEntity.add(elderGuardian);
                CONFIG.activePet = "elder_guardian";
            } else if (Objects.equals(species, "endermite")) {
                summonedEntity.clear();
                summonedEntity.add(endermite);
                CONFIG.activePet = "endermite";
            } else if (Objects.equals(species, "evoker")) {
                summonedEntity.clear();
                summonedEntity.add(evoker);
                CONFIG.activePet = "evoker";
            } else if (Objects.equals(species, "ghast")) {
                summonedEntity.clear();
                summonedEntity.add(ghast);
                CONFIG.activePet = "ghast";
            } else if (Objects.equals(species, "happy_ghast") || Objects.equals(species, "happy ghast")) {
                summonedEntity.clear();
                summonedEntity.add(happyGhast);
                CONFIG.activePet = "happy_ghast";
            } else if (Objects.equals(species, "guardian")) {
                summonedEntity.clear();
                summonedEntity.add(guardian);
                CONFIG.activePet = "guardian";
            } else if (Objects.equals(species, "hoglin")) {
                summonedEntity.clear();
                summonedEntity.add(hoglin);
                CONFIG.activePet = "hoglin";
            } else if (Objects.equals(species, "magma_cube") || Objects.equals(species, "magma cube")) {
                summonedEntity.clear();
                summonedEntity.add(magmaCube);
                CONFIG.activePet = "magma_cube";
            } else if (Objects.equals(species, "phantom")) {
                summonedEntity.clear();
                summonedEntity.add(phantom);
                CONFIG.activePet = "phantom";
            } else if (Objects.equals(species, "pillager")) {
                summonedEntity.clear();
                summonedEntity.add(pillager);
                CONFIG.activePet = "pillager";
            } else if (Objects.equals(species, "ravager")) {
                summonedEntity.clear();
                summonedEntity.add(ravager);
                CONFIG.activePet = "ravager";
            } else if (Objects.equals(species, "shulker")) {
                summonedEntity.clear();
                summonedEntity.add(shulker);
                CONFIG.activePet = "shulker";
            } else if (Objects.equals(species, "silverfish")) {
                summonedEntity.clear();
                summonedEntity.add(silverfish);
                CONFIG.activePet = "silverfish";
            } else if (Objects.equals(species, "skeleton")) {
                summonedEntity.clear();
                summonedEntity.add(skeleton);
                CONFIG.activePet = "skeleton";
            } else if (Objects.equals(species, "slime")) {
                summonedEntity.clear();
                summonedEntity.add(slime);
                CONFIG.activePet = "slime";
            } else if (Objects.equals(species, "vex")) {
                summonedEntity.clear();
                summonedEntity.add(vex);
                CONFIG.activePet = "vex";
            } else if (Objects.equals(species, "vindicator")) {
                summonedEntity.clear();
                summonedEntity.add(vindicator);
                CONFIG.activePet = "vindicator";
            } else if (Objects.equals(species, "warden")) {
                summonedEntity.clear();
                summonedEntity.add(warden);
                CONFIG.activePet = "warden";
            } else if (Objects.equals(species, "witch")) {
                summonedEntity.clear();
                summonedEntity.add(witch);
                CONFIG.activePet = "witch";
            } else if (Objects.equals(species, "zombie")) {
                summonedEntity.clear();
                summonedEntity.add(zombie);
                CONFIG.activePet = "zombie";
            } else if (Objects.equals(species, "zombie_villager") || Objects.equals(species, "zombie villager")) {
                summonedEntity.clear();
                summonedEntity.add(zombieVillager);
                CONFIG.activePet = "zombie_villager";
            } else if (Objects.equals(species, "husk")) {
                summonedEntity.clear();
                summonedEntity.add(husk);
                CONFIG.activePet = "husk";
            } else if (Objects.equals(species, "drowned")) {
                summonedEntity.clear();
                summonedEntity.add(drowned);
                CONFIG.activePet = "drowned";
            } else if (Objects.equals(species, "bogged")) {
                summonedEntity.clear();
                summonedEntity.add(bogged);
                CONFIG.activePet = "bogged";
            } else if (Objects.equals(species, "parched")) {
                summonedEntity.clear();
                summonedEntity.add(parched);
                CONFIG.activePet = "parched";
            } else if (Objects.equals(species, "stray")) {
                summonedEntity.clear();
                summonedEntity.add(stray);
                CONFIG.activePet = "stray";
            } else if (Objects.equals(species, "wither_skeleton") || Objects.equals(species, "wither skeleton")) {
                summonedEntity.clear();
                summonedEntity.add(witherSkeleton);
                CONFIG.activePet = "wither_skeleton";
            } else if (Objects.equals(species, "wither")) {
                summonedEntity.clear();
                summonedEntity.add(wither);
                CONFIG.activePet = "wither";
            } else if (Objects.equals(species, "ender dragon") || Objects.equals(species, "ender_dragon")) {
                summonedEntity.clear();
                summonedEntity.add(enderDragon);
                CONFIG.activePet = "ender_dragon";
            } else {
                isValid = false;
            }

            if (!isValid) {
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §cThat's not a pet that's currently supported. Try something else."));
            } else if (isValid && CONFIG.petSummonedPreference) {
                despawnPet();
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §aYour active pet has been switched to " + CONFIG.activePet + "."));
                summonPet();
            } else if (isValid && !CONFIG.petSummonedPreference) {
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §cYour pet has been switched to " + CONFIG.activePet + ", but you currently do not have your pet enabled. Run §l/pet on§r§c to change this."));
            }

            AutoConfig.getConfigHolder(PetsConfig.class).save();
            return 1;
        }))));
        ClientTickEvents.END_CLIENT_TICK.register((client) -> client.execute(() -> {
            ++this.i;
            Minecraft minecraft = Minecraft.getInstance();
            ClientLevel world = minecraft.level;
            petSkin = (int) (Math.random() * (double) 3.0F);
            if (client.player != null && CONFIG.petSummonedPreference && summonedEntity.isEmpty()) {
                summonPet();
            }

            if (!CONFIG.petSummonedPreference && !summonedEntity.isEmpty()) {
                assert world != null;

                despawnPet();
                summonedEntity.clear();
            }

            refreshPetNames();
            if (this.i == 20) {
                updateSuggestions(client);
                this.i = 0;
            }

        }));
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            List var10001 = summonedEntity;
            Objects.requireNonNull(var10001);
            client.execute(var10001::clear);
        });
    }

    public void createSkinCommand() {
        if (Minecraft.getInstance().getConnection() != null) {
            ClientPacketListener connection = Minecraft.getInstance().getConnection();
            RootCommandNode<ClientSuggestionProvider> commandRoot = connection.getCommands().getRoot();
            commandRoot.getExamples().clear();
        }

        ClientCommandRegistrationCallback.EVENT.register((dispatcher2, registryAccess) -> dispatcher2.register(ClientCommandManager.literal("petskin").then(ClientCommandManager.argument("skin", StringArgumentType.greedyString()).suggests(this.SKINS).executes((context) -> {
            boolean isValid = true;
            AutoConfig.getConfigHolder(PetsConfig.class).save();
            String skin = StringArgumentType.getString(context, "skin");
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
                    case null:
                    default:
                        isValid = false;
                }
            } else if (Objects.equals(CONFIG.activePet, "racoon")) {
                switch(skin) {
                    case "normal" -> CONFIG.racoonSkin = "normal";
                    case "albino" -> CONFIG.racoonSkin = "albino";
                    case null, default -> isValid = false;
                }
            }

            else if (Objects.equals(CONFIG.activePet, "cat")) {
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
            } else if (Objects.equals(CONFIG.activePet, "slime")) {
                switch (skin) {
                    case "small" -> CONFIG.slimeSkin = "small";
                    case "medium" -> CONFIG.slimeSkin = "medium";
                    case "large" -> CONFIG.slimeSkin = "large";
                    case null, default -> isValid = false;
                }
            } else if (Objects.equals(CONFIG.activePet, "shulker")) {
                switch(skin) {
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
            } else if (Objects.equals(CONFIG.activePet, "creeper")) {
                switch (skin) {
                    case "normal" -> CONFIG.creeperSkin = "normal";
                    case "charged" -> CONFIG.creeperSkin = "charged";
                    case null, default -> isValid = false;
                }
            } else if (Objects.equals(CONFIG.activePet, "wither")) {
                switch (skin) {
                    case "normal" -> CONFIG.witherSkin = "normal";
                    case "invulnerable" -> CONFIG.witherSkin = "invulnerable";
                }
            }

            if (isValid) {
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §aYour pet's skin has been updated."));
            } else {
                context.getSource().sendFeedback(Component.literal("§b[PetsMod] §cEither your currently selected pet doesn't support multiple skins, or that is not a valid skin. Try something else."));
            }

            return 1;
        }))));
    }

    public void checkForNullObjects() {

        if (CONFIG.activePet == null) {
            CONFIG.activePet = "duck";
        }

        if (CONFIG.duckName == null) {
            CONFIG.duckName = "";
        }
        if (CONFIG.duckSkin == null) {
            CONFIG.duckSkin = "mallard";
        } if (CONFIG.racoonName == null) {
            CONFIG.racoonName = "";
        } if (CONFIG.racoonSkin == null) {
            CONFIG.racoonSkin = "normal";
        }
        if (CONFIG.penguinName == null) {
            CONFIG.penguinName = "";
        }
        if (CONFIG.sheepName == null) {
            CONFIG.sheepName = "";
        }

        if (CONFIG.sheepSkin == null) {
            CONFIG.sheepSkin = "white";
        }
        if (CONFIG.catName == null) {
            CONFIG.catName = "";
        }
        if (CONFIG.catSkin == null) {
            CONFIG.catSkin = "tuxedo";
        }
        if (CONFIG.allayName == null) {
            CONFIG.allayName = "";
        }
        if (CONFIG.armadilloName == null) {
            CONFIG.armadilloName = "";
        }
        if (CONFIG.axolotlName == null) {
            CONFIG.axolotlName = "";
        }
        if (CONFIG.axolotlSkin == null) {
            CONFIG.axolotlSkin = "pink";
        }
        if (CONFIG.batName == null) {
            CONFIG.batName = "";
        }

        if (CONFIG.camelName == null) {
            CONFIG.camelName = "";
        }

        if (CONFIG.camelSkin == null) {
            CONFIG.camelSkin = "camel";
        }
        if (CONFIG.chickenName == null) {
            CONFIG.chickenName = "";
        }

        if (CONFIG.chickenSkin == null) {
            CONFIG.chickenSkin = "temperate";
        }
        if (CONFIG.codName == null) {
            CONFIG.codName = "";
        }
        if (CONFIG.copperGolemName == null) {
            CONFIG.copperGolemName = "";
        }
        if (CONFIG.copperGolemSkin == null) {
            CONFIG.copperGolemSkin = "unoxidized";
        }
        if (CONFIG.cowName == null) {
            CONFIG.cowName = "";
        }
        if (CONFIG.cowSkin == null) {
            CONFIG.cowSkin = "temperate";
        }
        if (CONFIG.donkeyName == null) {
            CONFIG.donkeyName = "";
        }
        if (CONFIG.frogName == null) {
            CONFIG.frogName = "";
        }
        if (CONFIG.frogSkin == null) {
            CONFIG.frogSkin = "temperate";
        }
        if (CONFIG.horseName == null) {
            CONFIG.horseName = "";
        }
        if (CONFIG.horseSkin == null) {
            CONFIG.horseSkin = "white";
        }
        if (CONFIG.mooshroomName == null) {
            CONFIG.mooshroomName = "";
        }
        if (CONFIG.mooshroomSkin == null) {
            CONFIG.mooshroomSkin = "red";
        }
        if (CONFIG.parrotName == null) {
            CONFIG.parrotName = "";
        }

        if (CONFIG.parrotSkin == null) {
            CONFIG.parrotSkin = "red";
        }
        if (CONFIG.pigName == null) {
            CONFIG.pigName = "";
        }
        if (CONFIG.pigSkin == null) {
            CONFIG.pigSkin = "temperate";
        }
        if (CONFIG.rabbitName == null) {
            CONFIG.rabbitName = "";
        }

        if (CONFIG.rabbitSkin == null) {
            CONFIG.rabbitSkin = "brown";
        }
        if (CONFIG.salmonName == null) {
            CONFIG.salmonName = "";
        }
        if (CONFIG.snifferName == null) {
            CONFIG.snifferName = "";
        }
        if (CONFIG.snowGolemName == null) {
            CONFIG.snowGolemName = "";
        }
        if (CONFIG.snowGolemSkin == null) {
            CONFIG.snowGolemSkin = "pumpkin_on";
        }
        if (CONFIG.squidName == null) {
            CONFIG.squidName = "";
        }

        if (CONFIG.squidSkin == null) {
            CONFIG.squidSkin = "squid";
        }
        if (CONFIG.striderName == null) {
            CONFIG.striderName = "";
        }

        if (CONFIG.striderSkin == null) {
            CONFIG.striderSkin = "warm";
        }
        if (CONFIG.tadpoleName == null) {
            CONFIG.tadpoleName = "";
        }
        if (CONFIG.turtleName == null) {
            CONFIG.turtleName = "";
        }
        if (CONFIG.villagerName == null) {
            CONFIG.villagerName = "";
        }
        if (CONFIG.villagerSkin == null) {
            CONFIG.villagerSkin = "nitwit";
        }
        if (CONFIG.wanderingTraderName == null) {
            CONFIG.wanderingTraderName = "";
        }
        if (CONFIG.beeName == null) {
            CONFIG.beeName = "";
        }
        if (CONFIG.beeSkin == null) {
            CONFIG.beeSkin = "happy";
        }
        if (CONFIG.caveSpiderName == null) {
            CONFIG.caveSpiderName = "";
        }
        if (CONFIG.dolphinName == null) {
            CONFIG.dolphinName = "";
        }
        if (CONFIG.endermanName == null) {
            CONFIG.endermanName = "";
        }
        if (CONFIG.foxName == null) {
            CONFIG.foxName = "";
        }
        if (CONFIG.foxSkin == null) {
            CONFIG.foxSkin = "red";
        }
        if (CONFIG.goatName == null) {
            CONFIG.goatName = "";
        }
        if (CONFIG.ironGolemName == null) {
            CONFIG.ironGolemName = "";
        }
        if (CONFIG.llamaName == null) {
            CONFIG.llamaName = "";
        }
        if (CONFIG.llamaSkin == null) {
            CONFIG.llamaSkin = "brown";
        }
        if (CONFIG.nautilusName == null) {
            CONFIG.nautilusName = "";
        }
        if (CONFIG.nautilusSkin == null) {
            CONFIG.nautilusSkin = "nautilus";
        }
        if (CONFIG.pandaName == null) {
            CONFIG.pandaName = "";
        }
        if (CONFIG.pandaSkin == null) {
            CONFIG.pandaSkin = "normal";
        }
        if (CONFIG.piglinName == null) {
            CONFIG.piglinName = "";
        }
        if (CONFIG.piglinSkin == null) {
            CONFIG.piglinSkin = "piglin";
        }
        if (CONFIG.polarBearName == null) {
            CONFIG.polarBearName = "";
        }
        if (CONFIG.pufferFishName == null) {
            CONFIG.pufferFishName = "";
        }
        if (CONFIG.spiderName == null) {
            CONFIG.spiderName = "";
        }
        if (CONFIG.wolfName == null) {
            CONFIG.wolfName = "";
        }
        if (CONFIG.blazeName == null) {
            CONFIG.blazeName = "";
        } if (CONFIG.boggedName == null) {
            CONFIG.boggedName = "";
        } if (CONFIG.breezeName == null) {
            CONFIG.breezeName = "";
        } if (CONFIG.creakingName == null) {
            CONFIG.creakingName = "";
        } if (CONFIG.creeperName == null) {
            CONFIG.creeperName = "";
        } if (CONFIG.creeperSkin == null) {
            CONFIG.creeperSkin = "normal";
        } if (CONFIG.drownedName == null) {
            CONFIG.drownedName = "";
        } if (CONFIG.elderGuardianName == null) {
            CONFIG.elderGuardianName = "";
        } if (CONFIG.endermiteName == null) {
            CONFIG.endermiteName = "";
        } if (CONFIG.evokerName == null) {
            CONFIG.evokerName = "";
        } if (CONFIG.ghastName == null) {
            CONFIG.ghastName = "";
        } if (CONFIG.guardianName == null) {
            CONFIG.guardianName = "";
        } if (CONFIG.hoglinName == null) {
            CONFIG.hoglinName = "";
        } if (CONFIG.hoglinSkin == null) {
            CONFIG.hoglinSkin = "hoglin";
        } if (CONFIG.huskName == null) {
            CONFIG.huskName = "";
        } if (CONFIG.happyGhastName == null) {
            CONFIG.happyGhastName = "";
        } if (CONFIG.magmaCubeName == null) {
            CONFIG.magmaCubeName = "";
        } if (CONFIG.magmaCubeSkin == null) {
            CONFIG.magmaCubeSkin = "small";
        } if (CONFIG.parchedName == null) {
            CONFIG.parchedName = "";
        } if (CONFIG.phantomName == null) {
            CONFIG.phantomName = "";
        } if (CONFIG.pillagerName == null) {
            CONFIG.pillagerName = "";
        } if (CONFIG.ravagerName == null) {
            CONFIG.ravagerName = "";
        }  if (CONFIG.shulkerName == null) {
            CONFIG.shulkerName = "";
        } if (CONFIG.silverfishName == null) {
            CONFIG.silverfishName = "";
        } if (CONFIG.skeletonName == null) {
            CONFIG.skeletonName = "";
        } if (CONFIG.slimeName == null) {
            CONFIG.slimeName = "";
        } if (CONFIG.slimeSkin == null) {
            CONFIG.slimeSkin = "small";
        } if (CONFIG.strayName == null) {
            CONFIG.strayName = "";
        } if (CONFIG.vexName == null) {
            CONFIG.vexName = "";
        } if (CONFIG.vindicatorName == null) {
            CONFIG.vindicatorName = "";
        } if (CONFIG.wardenName == null) {
            CONFIG.wardenName = "";
        } if (CONFIG.witchName == null) {
            CONFIG.witchName = "";
        } if (CONFIG.witherSkeletonName == null) {
            CONFIG.witherSkeletonName = "";
        } if (CONFIG.zombieName == null) {
            CONFIG.zombieName = "";
        } if (CONFIG.zombieVillagerSkin == null) {
            CONFIG.zombieVillagerSkin = "nitwit";
        } if (CONFIG.zombieVillagerName == null) {
            CONFIG.zombieVillagerName = "";
        } if (CONFIG.enderDragonName == null) {
            CONFIG.enderDragonName = "";
        } if (CONFIG.witherName == null) {
            CONFIG.witherName = "";
        } if (CONFIG.witherSkin == null) {
            CONFIG.witherSkin = "normal";
        }
    }
}
