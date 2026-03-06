package com.jeff.pets;

import com.jeff.pets.enums.*;
import com.mojang.brigadier.context.CommandContext;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumDropdownControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class PetsConfigScreen implements ModMenuApi {

    private static final Logger log = LoggerFactory.getLogger(PetsConfigScreen.class);

    public Class<? extends Enum<?>> enumClass = DuckSkins.class;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return parentScreen -> {
                PetsConfig CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();
                String activePet = CONFIG.activePet;
                return YetAnotherConfigLib.createBuilder()
                        .title(Component.literal("Pets Config"))
                        .save(() -> {
                            Screen currentScreen = Minecraft.getInstance().screen;
                            AutoConfig.getConfigHolder(PetsConfig.class).save();
                            if (currentScreen != null) {
                                Minecraft.getInstance().setScreen(currentScreen);
                            }
                        })
                        .category(ConfigCategory.createBuilder()
                                .name(Component.literal("Pets Config"))
                                .tooltip(Component.literal("The configuration menu for the Pets mod."))
                                .group(OptionGroup.createBuilder()
                                        .name(Component.literal("Settings"))
                                        .description(OptionDescription.of(Component.literal("All of the settings for the mod, conveniently in one place. Try clicking on the arrows to collapse the groups until you find the setting you want.")))
                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.literal("Pet Toggle"))
                                                .description(OptionDescription.of(Component.literal("A toggle for whether your pet will appear.")))
                                                .binding(true, () -> CONFIG.petSummonedPreference, newVal -> {
                                                    CONFIG.petSummonedPreference = newVal;
                                                    if (newVal) {
                                                        Pet.summonPet();
                                                    } else {
                                                        Pet.despawnPet();
                                                    }
                                                })
                                                .controller(TickBoxControllerBuilder::create)
                                                .build())
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Component.literal("Active Pet"))
                                        .description(OptionDescription.of(Component.literal("Your currently selected pet is: " + CONFIG.activePet)))
                                        .option(Option.<PetList>createBuilder()
                                                .name(Component.literal("Pet Species"))
                                                .description(OptionDescription.of(Component.literal("The species of your pet. MAKE SURE to save this after it has changed before you change any other values, as they will edit the previous pet.")))
                                                .binding(
                                                        PetList.valueOf("racoon"),
                                                        () -> {
                                                            boolean hasPrintedMessage = false;
                                                            try {
                                                                return PetList.valueOf(CONFIG.activePet);
                                                            } catch (IllegalArgumentException e) {
                                                                assert Minecraft.getInstance().player != null;
                                                                if (!hasPrintedMessage) {
                                                                    Minecraft.getInstance().player.displayClientMessage(Component.literal("§b[PetsMod] §cWe ran into an error and couldn't generate your config screen. Please report this stacktrace on my GitHub§r: " + e.getMessage() + ". §cThis is usually caused by tampering with the config JSON or a logic error in the code. §aIn the" +
                                                                            " meantime, try using the /pet commands."), false);
                                                                    Minecraft.getInstance().player.displayClientMessage(Component.literal("§aRecommended course of action: run /petspecies and choose a valid option from the suggestions."), false);
                                                                    hasPrintedMessage = true;
                                                                }
                                                                e.printStackTrace();
                                                                throw new IllegalArgumentException();
                                                            }
                                                        },
                                                        newVal -> {
                                                            Pet.despawnPet();
                                                            CONFIG.activePet = String.valueOf(newVal);
                                                            Pet.summonPet();
                                                        }
                                                )
                                                .controller(EnumDropdownControllerBuilder::create)
                                                .build())
                                        .option(Option.<String>createBuilder()
                                                .name(Component.literal("Pet Name"))
                                                .description(OptionDescription.of(Component.literal("The name for your pet.")))
                                                .binding(
                                                        "",
                                                        () ->
                                                                switch (CONFIG.activePet) {
                                                                    case "penguin" -> CONFIG.penguinName;
                                                                    case "racoon" -> CONFIG.racoonName;
                                                                    case "duck" -> CONFIG.duckName;
                                                                    case "cat" -> CONFIG.catName;
                                                                    case "sheep" -> CONFIG.sheepName;
                                                                    case "allay" -> CONFIG.allayName;
                                                                    case "armadillo" -> CONFIG.armadilloName;
                                                                    case "bat" -> CONFIG.batName;
                                                                    case "camel" -> CONFIG.camelName;
                                                                    case "chicken" -> CONFIG.chickenSkin;
                                                                    case "cod" -> CONFIG.codName;
                                                                    case "copper_golem" -> CONFIG.copperGolemName;
                                                                    case "cow" -> CONFIG.cowName;
                                                                    case "donkey" -> CONFIG.donkeyName;
                                                                    case "frog" -> CONFIG.frogName;
                                                                    case "horse" -> CONFIG.horseName;
                                                                    case "mooshroom" -> CONFIG.mooshroomName;
                                                                    case "mule" -> CONFIG.muleName;
                                                                    case "parrot" -> CONFIG.parrotName;
                                                                    case "pig" -> CONFIG.pigName;
                                                                    case "rabbit" -> CONFIG.rabbitName;
                                                                    case "salmon" -> CONFIG.salmonName;
                                                                    case "sniffer" -> CONFIG.snifferName;
                                                                    case "snow_golem" -> CONFIG.snowGolemName;
                                                                    case "squid" -> CONFIG.squidName;
                                                                    case "strider" -> CONFIG.striderName;
                                                                    case "tadpole" -> CONFIG.tadpoleName;
                                                                    case "tropical_fish" -> CONFIG.tropicalFishName;
                                                                    case "turtle" -> CONFIG.turtleName;
                                                                    case "villager" -> CONFIG.villagerName;
                                                                    case "wandering_trader" ->
                                                                            CONFIG.wanderingTraderName;
                                                                    case "bee" -> CONFIG.beeName;
                                                                    case "cave_spider" -> CONFIG.caveSpiderName;
                                                                    case "dolphin" -> CONFIG.dolphinName;
                                                                    case "enderman" -> CONFIG.endermanName;
                                                                    case "fox" -> CONFIG.foxName;
                                                                    case "goat" -> CONFIG.goatName;
                                                                    case "iron_golem" -> CONFIG.ironGolemName;
                                                                    case "llama" -> CONFIG.llamaName;
                                                                    case "nautilus" -> CONFIG.nautilusName;
                                                                    case "panda" -> CONFIG.pandaName;
                                                                    case "piglin" -> CONFIG.piglinName;
                                                                    case "polar_bear" -> CONFIG.polarBearName;
                                                                    case "pufferfish" -> CONFIG.pufferFishName;
                                                                    case "spider" -> CONFIG.spiderName;
                                                                    case "wolf" -> CONFIG.wolfName;
                                                                    case "blaze" -> CONFIG.blazeName;
                                                                    case "breeze" -> CONFIG.breezeName;
                                                                    case "creaking" -> CONFIG.creakingName;
                                                                    case "creeper" -> CONFIG.creeperName;
                                                                    case "elder_guardian" -> CONFIG.elderGuardianName;
                                                                    case "endermite" -> CONFIG.endermiteName;
                                                                    case "evoker" -> CONFIG.evokerName;
                                                                    case "ghast" -> CONFIG.ghastName;
                                                                    case "guardian" -> CONFIG.guardianName;
                                                                    case "hoglin" -> CONFIG.hoglinName;
                                                                    case "magma_cube" -> CONFIG.magmaCubeName;
                                                                    case "phantom" -> CONFIG.phantomName;
                                                                    case "pillager" -> CONFIG.pillagerName;
                                                                    case "ravager" -> CONFIG.ravagerName;
                                                                    case "shulker" -> CONFIG.shulkerName;
                                                                    case "silverfish" -> CONFIG.silverfishName;
                                                                    case "skeleton" -> CONFIG.skeletonName;
                                                                    case "slime" -> CONFIG.slimeName;
                                                                    case "vex" -> CONFIG.vexName;
                                                                    case "vindicator" -> CONFIG.vindicatorName;
                                                                    case "warden" -> CONFIG.wardenName;
                                                                    case "witch" -> CONFIG.witchName;
                                                                    case "zombie" -> CONFIG.zombieName;
                                                                    case "zombie_villager" -> CONFIG.zombieVillagerName;
                                                                    default -> "";
                                                                },
                                                        name -> {
                                                            switch (activePet) {
                                                                case "penguin" -> CONFIG.penguinName = name;
                                                                case "racoon" -> CONFIG.racoonName = name;
                                                                case "duck" -> CONFIG.duckName = name;
                                                                case "cat" -> CONFIG.catName = name;
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
                                                                case "wandering_trader" ->
                                                                        CONFIG.wanderingTraderName = name;
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
                                                                case "elder_guardian" ->
                                                                        CONFIG.elderGuardianName = name;
                                                                case "endermite" -> CONFIG.endermiteName = name;
                                                                case "evoker" -> CONFIG.evokerName = name;
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
                                                                case "zombie_villager" ->
                                                                        CONFIG.zombieVillagerName = name;
                                                                case "ender_dragon" -> CONFIG.enderDragonName = name;
                                                                case "wither" -> CONFIG.witherName = name;

                                                            }
                                                            Pet.refreshPetNames();
                                                        }
                                                )

                                                .controller(StringControllerBuilder::create)
                                                .build())
                                        .option(Option.<Enum<?>>createBuilder()
                                                .name(Component.literal("Pet Skin"))
                                                .description(OptionDescription.of(Component.literal("The skin for your pet.")))
                                                .binding(BlankEnum.valueOf("no_skins_are_available"), () -> {
                                                    boolean hasPrintedMessage = false;
                                                    try {
                                                        return switch (CONFIG.activePet) {
                                                            case "duck" -> DuckSkins.valueOf(CONFIG.duckSkin);
                                                            case "cat" -> CatSkins.valueOf(CONFIG.catSkin);
                                                            case "racoon" -> RacoonSkins.valueOf(CONFIG.racoonSkin);
                                                            case "sheep" -> SheepSkins.valueOf(CONFIG.sheepSkin);
                                                            case "axolotl" -> AxolotlSkins.valueOf(CONFIG.axolotlSkin);
                                                            case "camel" -> CamelSkins.valueOf(CONFIG.camelSkin);
                                                            case "chicken" -> ChickenSkins.valueOf(CONFIG.chickenSkin);
                                                            case "copper_golem" ->
                                                                    CopperGolemSkins.valueOf(CONFIG.copperGolemSkin);
                                                            case "cow" -> CowSkins.valueOf(CONFIG.cowSkin);
                                                            case "frog" -> FrogSkins.valueOf(CONFIG.frogSkin);
                                                            case "horse" -> HorseSkins.valueOf(CONFIG.horseSkin);
                                                            case "parrot" -> ParrotSkins.valueOf(CONFIG.parrotSkin);
                                                            case "pig" -> PigSkins.valueOf(CONFIG.pigSkin);
                                                            case "rabbit" -> RabbitSkins.valueOf(CONFIG.rabbitSkin);
                                                            case "snow_golem" ->
                                                                    SnowGolemSkins.valueOf(CONFIG.snowGolemSkin);
                                                            case "squid" -> SquidSkins.valueOf(CONFIG.squidSkin);
                                                            case "strider" -> StriderSkins.valueOf(CONFIG.striderSkin);
                                                            case "tropical_fish" ->
                                                                    TropicalFishSkins.valueOf(CONFIG.tropicalFishSkin);
                                                            case "villager" ->
                                                                    VillagerSkins.valueOf(CONFIG.villagerSkin);
                                                            case "mooshroom" ->
                                                                    MooshroomSkins.valueOf(CONFIG.mooshroomSkin);
                                                            case "bee" -> BeeSkins.valueOf(CONFIG.beeSkin);
                                                            case "fox" -> FoxSkins.valueOf(CONFIG.foxSkin);
                                                            case "llama" -> LlamaSkins.valueOf(CONFIG.llamaSkin);
                                                            case "nautilus" ->
                                                                    NautilusSkins.valueOf(CONFIG.nautilusSkin);
                                                            case "panda" -> PandaSkins.valueOf(CONFIG.pandaSkin);
                                                            case "piglin" -> PiglinSkins.valueOf(CONFIG.piglinSkin);
                                                            case "wolf" -> WolfSkins.valueOf(CONFIG.wolfSkin);
                                                            case "hoglin" -> HoglinSkins.valueOf(CONFIG.hoglinSkin);
                                                            case "magma_cube" ->
                                                                    SlimeLikeSkins.valueOf(CONFIG.magmaCubeSkin);
                                                            case "slime" -> SlimeLikeSkins.valueOf(CONFIG.slimeSkin);
                                                            case "zombie_villager" ->
                                                                    ZombieVillagerSkins.valueOf(CONFIG.zombieVillagerSkin);
                                                            case "wither" -> WitherSkins.valueOf(CONFIG.witherSkin);
                                                            case null, default -> PetList.valueOf(CONFIG.activePet);
                                                        };
                                                    } catch (IllegalArgumentException e) {
                                                        assert Minecraft.getInstance().player != null;
                                                        if (!hasPrintedMessage) {
                                                            Minecraft.getInstance().player.displayClientMessage(Component.literal("§b[PetsMod] §cWe ran into an error and couldn't generate your config screen. Please report this stacktrace on my GitHub§r: " + e.getMessage() + ". §cThis is usually caused by tampering with the config JSON or a logic error in the code. §aIn the" +
                                                                    " meantime, try using the /pet commands."), false);
                                                            Minecraft.getInstance().player.displayClientMessage(Component.literal("§aRecommended course of action: run /petskin and choose a valid option from the suggestions."), false);
                                                            hasPrintedMessage = true;
                                                        }
                                                        e.printStackTrace();
                                                        throw new IllegalArgumentException();
                                                    }
                                                }, value -> {
                                                    String val = value.toString();
                                                    /**Do NOT replace this with dynamic checking! It will cause a mismatch
                                                     * between the enums and crash. Use manual checking instead.*/
                                                    switch (CONFIG.activePet) {
                                                        case "duck" -> {
                                                            if (Objects.equals(val, "mallard")) {
                                                                CONFIG.duckSkin = "mallard";
                                                            } else if (Objects.equals(val, "pekin")) {
                                                                CONFIG.duckSkin = "pekin";
                                                            } else if (Objects.equals(val, "rubber")) {
                                                                CONFIG.duckSkin = "rubber";
                                                            }
                                                        }
                                                        case "racoon" -> {
                                                            if (Objects.equals(val, "normal")) {
                                                                CONFIG.racoonSkin = "normal";
                                                            } else if (Objects.equals(val, "albino")) {
                                                                CONFIG.racoonSkin = "albino";
                                                            }
                                                        }
                                                        case "sheep" -> {
                                                            if (Objects.equals(val, "white")) {
                                                                CONFIG.sheepSkin = "white";
                                                            } else if (Objects.equals(val, "orange")) {
                                                                CONFIG.sheepSkin = "orange";
                                                            } else if (Objects.equals(val, "magenta")) {
                                                                CONFIG.sheepSkin = "magenta";
                                                            } else if (Objects.equals(val, "light_blue")) {
                                                                CONFIG.sheepSkin = "light_blue";
                                                            } else if (Objects.equals(val, "yellow")) {
                                                                CONFIG.sheepSkin = "yellow";
                                                            } else if (Objects.equals(val, "lime")) {
                                                                CONFIG.sheepSkin = "lime";
                                                            } else if (Objects.equals(val, "pink")) {
                                                                CONFIG.sheepSkin = "pink";
                                                            } else if (Objects.equals(val, "gray")) {
                                                                CONFIG.sheepSkin = "gray";
                                                            } else if (Objects.equals(val, "light_gray")) {
                                                                CONFIG.sheepSkin = "light_gray";
                                                            } else if (Objects.equals(val, "cyan")) {
                                                                CONFIG.sheepSkin = "cyan";
                                                            } else if (Objects.equals(val, "purple")) {
                                                                CONFIG.sheepSkin = "purple";
                                                            } else if (Objects.equals(val, "blue")) {
                                                                CONFIG.sheepSkin = "blue";
                                                            } else if (Objects.equals(val, "brown")) {
                                                                CONFIG.sheepSkin = "brown";
                                                            } else if (Objects.equals(val, "green")) {
                                                                CONFIG.sheepSkin = "green";
                                                            } else if (Objects.equals(val, "red")) {
                                                                CONFIG.sheepSkin = "red";
                                                            } else if (Objects.equals(val, "black")) {
                                                                CONFIG.sheepSkin = "black";
                                                            }
                                                        }
                                                        case "cat" -> {
                                                            if (Objects.equals(val, "black")) {
                                                                CONFIG.catSkin = "all_black";
                                                            } else if (Objects.equals(val, "tuxedo")) {
                                                                CONFIG.catSkin = "tuxedo";
                                                            } else if (Objects.equals(val, "tabby")) {
                                                                CONFIG.catSkin = "tabby";
                                                            } else if (Objects.equals(val, "red")) {
                                                                CONFIG.catSkin = "red";
                                                            } else if (Objects.equals(val, "siamese")) {
                                                                CONFIG.catSkin = "siamese";
                                                            } else if (Objects.equals(val, "calico")) {
                                                                CONFIG.catSkin = "calico";
                                                            } else if (Objects.equals(val, "british_shorthair")) {
                                                                CONFIG.catSkin = "british_shorthair";
                                                            } else if (Objects.equals(val, "persian")) {
                                                                CONFIG.catSkin = "persian";
                                                            } else if (Objects.equals(val, "ragdoll")) {
                                                                CONFIG.catSkin = "ragdoll";
                                                            } else if (Objects.equals(val, "white")) {
                                                                CONFIG.catSkin = "white";
                                                            } else if (Objects.equals(val, "jellie")) {
                                                                CONFIG.catSkin = "jellie";
                                                            }
                                                        }
                                                        case "chicken" -> {
                                                            if (Objects.equals(val, "temperate")) {
                                                                CONFIG.chickenSkin = "temperate";
                                                            } else if (Objects.equals(val, "cold")) {
                                                                CONFIG.chickenSkin = "cold";
                                                            } else if (Objects.equals(val, "warm")) {
                                                                CONFIG.chickenSkin = "warm";
                                                            }
                                                        }
                                                        case "axolotl" -> {
                                                            if (Objects.equals(val, "pink")) {
                                                                CONFIG.axolotlSkin = "pink";
                                                            } else if (Objects.equals(val, "brown")) {
                                                                CONFIG.axolotlSkin = "brown";
                                                            } else if (Objects.equals(val, "gold")) {
                                                                CONFIG.axolotlSkin = "gold";
                                                            } else if (Objects.equals(val, "cyan")) {
                                                                CONFIG.axolotlSkin = "cyan";
                                                            } else if (Objects.equals(val, "blue")) {
                                                                CONFIG.axolotlSkin = "blue";
                                                            }
                                                        }
                                                        case "camel" -> {
                                                            if (Objects.equals(val, "camel")) {
                                                                CONFIG.camelSkin = "camel";
                                                            } else if (Objects.equals(val, "husk")) {
                                                                CONFIG.camelSkin = "husk";
                                                            }
                                                        }
                                                        case "copper_golem" -> {
                                                            if (Objects.equals(val, "unoxidized")) {
                                                                CONFIG.copperGolemSkin = "unoxidized";
                                                            } else if (Objects.equals(val, "exposed")) {
                                                                CONFIG.copperGolemSkin = "exposed";
                                                            } else if (Objects.equals(val, "weathered")) {
                                                                CONFIG.copperGolemSkin = "weathered";
                                                            } else if (Objects.equals(val, "oxidized")) {
                                                                CONFIG.copperGolemSkin = "oxidized";
                                                            }
                                                        }
                                                        case "cow" -> {
                                                            if (Objects.equals(val, "temperate")) {
                                                                CONFIG.cowSkin = "temperate";
                                                            } else if (Objects.equals(val, "cold")) {
                                                                CONFIG.cowSkin = "cold";
                                                            } else if (Objects.equals(val, "warm")) {
                                                                CONFIG.cowSkin = "warm";
                                                            }
                                                        }
                                                        case "frog" -> {
                                                            if (Objects.equals(val, "temperate")) {
                                                                CONFIG.frogSkin = "temperate";
                                                            } else if (Objects.equals(val, "cold")) {
                                                                CONFIG.frogSkin = "cold";
                                                            } else if (Objects.equals(val, "warm")) {
                                                                CONFIG.frogSkin = "warm";
                                                            }
                                                        }
                                                        case "horse" -> {
                                                            if (Objects.equals(val, "white")) {
                                                                CONFIG.horseSkin = "white";
                                                            } else if (Objects.equals(val, "creamy")) {
                                                                CONFIG.horseSkin = "creamy";
                                                            } else if (Objects.equals(val, "chestnut")) {
                                                                CONFIG.horseSkin = "chestnut";
                                                            } else if (Objects.equals(val, "brown")) {
                                                                CONFIG.horseSkin = "brown";
                                                            } else if (Objects.equals(val, "black")) {
                                                                CONFIG.horseSkin = "black";
                                                            } else if (Objects.equals(val, "gray")) {
                                                                CONFIG.horseSkin = "gray";
                                                            } else if (Objects.equals(val, "dark_brown")) {
                                                                CONFIG.horseSkin = "dark_brown";
                                                            }
                                                        }
                                                        case "parrot" -> {
                                                            if (Objects.equals(val, "red")) {
                                                                CONFIG.parrotSkin = "red";
                                                            } else if (Objects.equals(val, "blue")) {
                                                                CONFIG.parrotSkin = "blue";
                                                            } else if (Objects.equals(val, "green")) {
                                                                CONFIG.parrotSkin = "green";
                                                            } else if (Objects.equals(val, "cyan")) {
                                                                CONFIG.parrotSkin = "cyan";
                                                            } else if (Objects.equals(val, "gray")) {
                                                                CONFIG.parrotSkin = "gray";
                                                            }
                                                        }
                                                        case "pig" -> {
                                                            if (Objects.equals(val, "temperate")) {
                                                                CONFIG.pigSkin = "temperate";
                                                            } else if (Objects.equals(val, "warm")) {
                                                                CONFIG.pigSkin = "warm";
                                                            } else if (Objects.equals(val, "cold")) {
                                                                CONFIG.pigSkin = "cold";
                                                            }
                                                        }
                                                        case "rabbit" -> {
                                                            if (Objects.equals(val, "brown")) {
                                                                CONFIG.rabbitSkin = "brown";
                                                            } else if (Objects.equals(val, "white")) {
                                                                CONFIG.rabbitSkin = "white";
                                                            } else if (Objects.equals(val, "black")) {
                                                                CONFIG.rabbitSkin = "black";
                                                            } else if (Objects.equals(val, "splotched")) {
                                                                CONFIG.rabbitSkin = "splotched";
                                                            } else if (Objects.equals(val, "gold")) {
                                                                CONFIG.rabbitSkin = "gold";
                                                            } else if (Objects.equals(val, "salt")) {
                                                                CONFIG.rabbitSkin = "salt";
                                                            } else if (Objects.equals(val, "killer")) {
                                                                CONFIG.rabbitSkin = "killer";
                                                            } else if (Objects.equals(val, "toast")) {
                                                                CONFIG.rabbitSkin = "toast";
                                                            }
                                                        }
                                                        case "snow_golem" -> {
                                                            if (Objects.equals(val, "pumpkin_on")) {
                                                                CONFIG.snowGolemSkin = "pumpkin_on";
                                                            } else if (Objects.equals(val, "pumpkin_off")) {
                                                                CONFIG.snowGolemSkin = "pumpkin_off";
                                                            }
                                                        }
                                                        case "squid" -> {
                                                            if (Objects.equals(val, "squid")) {
                                                                CONFIG.squidSkin = "squid";
                                                            } else if (Objects.equals(val, "glow_squid")) {
                                                                CONFIG.squidSkin = "glow_squid";
                                                            }
                                                        }
                                                        case "tropical_fish" -> {
                                                            if (Objects.equals(val, "cichlid")) {
                                                                CONFIG.tropicalFishSkin = "cichlid";
                                                            } else if (Objects.equals(val, "clownfish")) {
                                                                CONFIG.tropicalFishSkin = "clownfish";
                                                            } else if (Objects.equals(val, "cotton_candy_betta")) {
                                                                CONFIG.tropicalFishSkin = "cotton_candy_betta";
                                                            } else if (Objects.equals(val, "goatfish")) {
                                                                CONFIG.tropicalFishSkin = "goatfish";
                                                            } else if (Objects.equals(val, "parrotfish")) {
                                                                CONFIG.tropicalFishSkin = "parrotfish";
                                                            } else if (Objects.equals(val, "queen_angelfish")) {
                                                                CONFIG.tropicalFishSkin = "queen_angelfish";
                                                            } else if (Objects.equals(val, "red_lipped_blenny")) {
                                                                CONFIG.tropicalFishSkin = "red_lipped_blenny";
                                                            } else if (Objects.equals(val, "tomato_clownfish")) {
                                                                CONFIG.tropicalFishSkin = "tomato_clownfish";
                                                            } else if (Objects.equals(val, "triggerfish")) {
                                                                CONFIG.tropicalFishSkin = "triggerfish";
                                                            } else if (Objects.equals(val, "yellowtail_parrotfish")) {
                                                                CONFIG.tropicalFishSkin = "yellowtail_parrotfish";
                                                            }
                                                        }
                                                        case "villager" -> {
                                                            if (Objects.equals(val, "farmer")) {
                                                                CONFIG.villagerSkin = "farmer";
                                                            } else if (Objects.equals(val, "fisherman")) {
                                                                CONFIG.villagerSkin = "fisherman";
                                                            } else if (Objects.equals(val, "shepherd")) {
                                                                CONFIG.villagerSkin = "shepherd";
                                                            } else if (Objects.equals(val, "fletcher")) {
                                                                CONFIG.villagerSkin = "fletcher";
                                                            } else if (Objects.equals(val, "cleric")) {
                                                                CONFIG.villagerSkin = "cleric";
                                                            } else if (Objects.equals(val, "weaponsmith")) {
                                                                CONFIG.villagerSkin = "weaponsmith";
                                                            } else if (Objects.equals(val, "armorer")) {
                                                                CONFIG.villagerSkin = "armorer";
                                                            } else if (Objects.equals(val, "toolsmith")) {
                                                                CONFIG.villagerSkin = "toolsmith";
                                                            } else if (Objects.equals(val, "librarian")) {
                                                                CONFIG.villagerSkin = "librarian";
                                                            } else if (Objects.equals(val, "cartographer")) {
                                                                CONFIG.villagerSkin = "cartographer";
                                                            } else if (Objects.equals(val, "leatherworker")) {
                                                                CONFIG.villagerSkin = "leatherworker";
                                                            } else if (Objects.equals(val, "butcher")) {
                                                                CONFIG.villagerSkin = "butcher";
                                                            } else if (Objects.equals(val, "mason")) {
                                                                CONFIG.villagerSkin = "mason";
                                                            } else if (Objects.equals(val, "nitwit")) {
                                                                CONFIG.villagerSkin = "nitwit";
                                                            } else if (Objects.equals(val, "unemployed")) {
                                                                CONFIG.villagerSkin = "unemployed";
                                                            }
                                                        }
                                                        case "mooshroom" -> {
                                                            if (Objects.equals(val, "red")) {
                                                                CONFIG.mooshroomSkin = "red";
                                                            } else if (Objects.equals(val, "brown")) {
                                                                CONFIG.mooshroomSkin = "brown";
                                                            }
                                                        }
                                                        case "strider" -> {
                                                            if (Objects.equals(val, "warm")) {
                                                                CONFIG.striderSkin = "warm";
                                                            } else if (Objects.equals(val, "cold")) {
                                                                CONFIG.striderSkin = "cold";
                                                            }
                                                        }
                                                        case "bee" -> {
                                                            if (Objects.equals(val, "happy")) {
                                                                CONFIG.beeSkin = "happy";
                                                            } else if (Objects.equals(val, "angry")) {
                                                                CONFIG.beeSkin = "angry";
                                                            }
                                                        }
                                                        case "fox" -> {
                                                            if (Objects.equals(val, "red")) {
                                                                CONFIG.foxSkin = "red";
                                                            } else if (Objects.equals(val, "snow")) {
                                                                CONFIG.foxSkin = "snow";
                                                            }
                                                        }
                                                        case "llama" -> {
                                                            if (Objects.equals(val, "brown")) {
                                                                CONFIG.llamaSkin = "brown";
                                                            } else if (Objects.equals(val, "creamy")) {
                                                                CONFIG.llamaSkin = "creamy";
                                                            } else if (Objects.equals(val, "gray")) {
                                                                CONFIG.llamaSkin = "gray";
                                                            } else if (Objects.equals(val, "white")) {
                                                                CONFIG.llamaSkin = "white";
                                                            }
                                                        }
                                                        case "nautilus" -> {
                                                            if (Objects.equals(val, "nautilus")) {
                                                                CONFIG.nautilusSkin = "nautilus";
                                                            } else if (Objects.equals(val, "zombie")) {
                                                                CONFIG.nautilusSkin = "zombie";
                                                            } else if (Objects.equals(val, "coral_zombie")) {
                                                                CONFIG.nautilusSkin = "coral_zombie";
                                                            }
                                                        }
                                                        case "panda" -> {
                                                            if (Objects.equals(val, "normal")) {
                                                                CONFIG.pandaSkin = "normal";
                                                            } else if (Objects.equals(val, "lazy")) {
                                                                CONFIG.pandaSkin = "lazy";
                                                            } else if (Objects.equals(val, "agressive")) {
                                                                CONFIG.pandaSkin = "agressive";
                                                            } else if (Objects.equals(val, "worried")) {
                                                                CONFIG.pandaSkin = "worried";
                                                            } else if (Objects.equals(val, "playful")) {
                                                                CONFIG.pandaSkin = "playful";
                                                            } else if (Objects.equals(val, "weak")) {
                                                                CONFIG.pandaSkin = "weak";
                                                            } else if (Objects.equals(val, "brown")) {
                                                                CONFIG.pandaSkin = "brown";
                                                            }
                                                        }
                                                        case "piglin" -> {
                                                            if (Objects.equals(val, "piglin")) {
                                                                CONFIG.piglinSkin = "piglin";
                                                            } else if (Objects.equals(val, "zombified")) {
                                                                CONFIG.piglinSkin = "zombified";
                                                            } else if (Objects.equals(val, "brute")) {
                                                                CONFIG.piglinSkin = "brute";
                                                            }
                                                        }
                                                        case "wolf" -> {
                                                            if (Objects.equals(val, "pale")) {
                                                                CONFIG.wolfSkin = "pale";
                                                            }
                                                        }
                                                        case "hoglin" -> {
                                                            if (Objects.equals(val, "hoglin")) {
                                                                CONFIG.hoglinSkin = "hoglin";
                                                            } else if (Objects.equals(val, "zoglin")) {
                                                                CONFIG.hoglinSkin = "zoglin";
                                                            }
                                                        }
                                                        case "magma_cube" -> {
                                                            if (Objects.equals(val, "small")) {
                                                                CONFIG.magmaCubeSkin = "small";
                                                            } else if (Objects.equals(val, "medium")) {
                                                                CONFIG.magmaCubeSkin = "medium";
                                                            } else if (Objects.equals(val, "large")) {
                                                                CONFIG.magmaCubeSkin = "large";
                                                            }
                                                        }
                                                        case "slime" -> {
                                                            if (Objects.equals(val, "small")) {
                                                                CONFIG.slimeSkin = "small";
                                                            } else if (Objects.equals(val, "medium")) {
                                                                CONFIG.slimeSkin = "medium";
                                                            } else if (Objects.equals(val, "large")) {
                                                                CONFIG.slimeSkin = "large";
                                                            }
                                                        }
                                                        case "zombie_villager" -> {
                                                            if (Objects.equals(val, "farmer")) {
                                                                CONFIG.zombieVillagerSkin = "farmer";
                                                            } else if (Objects.equals(val, "fisherman")) {
                                                                CONFIG.zombieVillagerSkin = "fisherman";
                                                            } else if (Objects.equals(val, "shepherd")) {
                                                                CONFIG.zombieVillagerSkin = "shepherd";
                                                            } else if (Objects.equals(val, "fletcher")) {
                                                                CONFIG.zombieVillagerSkin = "fletcher";
                                                            } else if (Objects.equals(val, "cleric")) {
                                                                CONFIG.zombieVillagerSkin = "cleric";
                                                            } else if (Objects.equals(val, "weaponsmith")) {
                                                                CONFIG.zombieVillagerSkin = "weaponsmith";
                                                            } else if (Objects.equals(val, "armorer")) {
                                                                CONFIG.zombieVillagerSkin = "armorer";
                                                            } else if (Objects.equals(val, "toolsmith")) {
                                                                CONFIG.zombieVillagerSkin = "toolsmith";
                                                            } else if (Objects.equals(val, "librarian")) {
                                                                CONFIG.zombieVillagerSkin = "librarian";
                                                            } else if (Objects.equals(val, "cartographer")) {
                                                                CONFIG.zombieVillagerSkin = "cartographer";
                                                            } else if (Objects.equals(val, "leatherworker")) {
                                                                CONFIG.zombieVillagerSkin = "leatherworker";
                                                            } else if (Objects.equals(val, "butcher")) {
                                                                CONFIG.zombieVillagerSkin = "butcher";
                                                            } else if (Objects.equals(val, "mason")) {
                                                                CONFIG.zombieVillagerSkin = "mason";
                                                            } else if (Objects.equals(val, "nitwit")) {
                                                                CONFIG.zombieVillagerSkin = "nitwit";
                                                            } else if (Objects.equals(val, "unemployed")) {
                                                                CONFIG.zombieVillagerSkin = "unemployed";
                                                            }
                                                        }
                                                        case "shulker" -> {
                                                            if (Objects.equals(val, "normal")) {
                                                                CONFIG.shulkerSkin = "normal";
                                                            } else if (Objects.equals(val, "black")) {
                                                                CONFIG.shulkerSkin = "black";
                                                            } else if (Objects.equals(val, "blue")) {
                                                                CONFIG.shulkerSkin = "blue";
                                                            } else if (Objects.equals(val, "brown")) {
                                                                CONFIG.shulkerSkin = "brown";
                                                            } else if (Objects.equals(val, "cyan")) {
                                                                CONFIG.shulkerSkin = "cyan";
                                                            } else if (Objects.equals(val, "gray")) {
                                                                CONFIG.shulkerSkin = "gray";
                                                            } else if (Objects.equals(val, "green")) {
                                                                CONFIG.shulkerSkin = "green";
                                                            } else if (Objects.equals(val, "light_blue")) {
                                                                CONFIG.shulkerSkin = "light_blue";
                                                            } else if (Objects.equals(val, "light_gray")) {
                                                                CONFIG.shulkerSkin = "light_gray";
                                                            } else if (Objects.equals(val, "lime")) {
                                                                CONFIG.shulkerSkin = "lime";
                                                            } else if (Objects.equals(val, "magenta")) {
                                                                CONFIG.shulkerSkin = "magenta";
                                                            } else if (Objects.equals(val, "orange")) {
                                                                CONFIG.shulkerSkin = "orange";
                                                            } else if (Objects.equals(val, "pink")) {
                                                                CONFIG.shulkerSkin = "pink";
                                                            } else if (Objects.equals(val, "purple")) {
                                                                CONFIG.shulkerSkin = "purple";
                                                            } else if (Objects.equals(val, "red")) {
                                                                CONFIG.shulkerSkin = "red";
                                                            } else if (Objects.equals(val, "white")) {
                                                                CONFIG.shulkerSkin = "white";
                                                            } else if (Objects.equals(val, "yellow")) {
                                                                CONFIG.shulkerSkin = "yellow";
                                                            }
                                                        } case "creeper" -> {
                                                            if (Objects.equals(val, "normal")) {
                                                                CONFIG.creeperSkin = "normal";
                                                            } else if (Objects.equals(val, "charged")) {
                                                                CONFIG.creeperSkin = "charged";
                                                            }
                                                        } case "wither" -> {
                                                            if (Objects.equals(val, "normal")) {
                                                                CONFIG.witherSkin = "normal";
                                                            } else if (Objects.equals(val, "invulnerable")) {
                                                                CONFIG.witherSkin = "invulnerable";
                                                            }
                                                        }


                                                        case null, default -> {
                                                            try {

                                                            } catch (NullPointerException ignored) {

                                                            }
                                                        }
                                                    }
                                                })
                                                .controller(opt -> {
                                                    switch (CONFIG.activePet) {
                                                        case "duck" -> enumClass = DuckSkins.class;
                                                        case "racoon" -> enumClass = RacoonSkins.class;
                                                        case "strider" -> enumClass = StriderSkins.class;
                                                        case "sheep" -> enumClass = SheepSkins.class;
                                                        case "cat" -> enumClass = CatSkins.class;
                                                        case "axolotl" -> enumClass = AxolotlSkins.class;
                                                        case "camel" -> enumClass = CamelSkins.class;
                                                        case "chicken" -> enumClass = ChickenSkins.class;
                                                        case "copper_golem" -> enumClass = CopperGolemSkins.class;
                                                        case "cow" -> enumClass = CowSkins.class;
                                                        case "frog" -> enumClass = FrogSkins.class;
                                                        case "horse" -> enumClass = HorseSkins.class;
                                                        case "parrot" -> enumClass = ParrotSkins.class;
                                                        case "pig" -> enumClass = PigSkins.class;
                                                        case "rabbit" -> enumClass = RabbitSkins.class;
                                                        case "snow_golem" -> enumClass = SnowGolemSkins.class;
                                                        case "squid" -> enumClass = SquidSkins.class;
                                                        case "tropical_fish" -> enumClass = TropicalFishSkins.class;
                                                        case "villager" -> enumClass = VillagerSkins.class;
                                                        case "mooshroom" -> enumClass = MooshroomSkins.class;
                                                        case "bee" -> enumClass = BeeSkins.class;
                                                        case "fox" -> enumClass = FoxSkins.class;
                                                        case "llama" -> enumClass = LlamaSkins.class;
                                                        case "nautilus" -> enumClass = NautilusSkins.class;
                                                        case "panda" -> enumClass = PandaSkins.class;
                                                        case "piglin" -> enumClass = PiglinSkins.class;
                                                        case "wolf" -> enumClass = WolfSkins.class;
                                                        case "hoglin" -> enumClass = HoglinSkins.class;
                                                        case "magma_cube", "slime" -> enumClass = SlimeLikeSkins.class;
                                                        case "zombie_villager" -> enumClass = ZombieVillagerSkins.class;
                                                        case "shulker" -> enumClass = ShulkerSkins.class;
                                                        case "wither" -> enumClass = WitherSkins.class;
                                                        case null, default -> {
                                                        }
                                                    }
                                                    return EnumControllerBuilder.create((Option) opt).enumClass(enumClass);
                                                })
                                                //.available(!Objects.equals(CONFIG.activePet, "mule") && !Objects.equals(CONFIG.activePet, "donkey") && !Objects.equals(CONFIG.activePet, "allay") && !Objects.equals(CONFIG.activePet, "armadillo") && !Objects.equals(CONFIG.activePet, "bat") && !Objects.equals(CONFIG.activePet, "cod") && !Objects.equals(CONFIG.activePet, "salmon") && !Objects.equals(CONFIG.activePet, "sniffer") && !Objects.equals(CONFIG.activePet, "strider") && !Objects.equals(CONFIG.activePet, "tadpole") && !Objects.equals(CONFIG.activePet, "turtle") && !Objects.equals(CONFIG.activePet, "wandering_trader"))
                                                .build())
                                        .build())
                                .build())
                        .build()
                        .generateScreen(parentScreen);
            };
        }
    }
