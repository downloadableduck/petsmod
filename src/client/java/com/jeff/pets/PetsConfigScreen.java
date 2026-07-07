package com.jeff.pets;

import com.jeff.pets.enums.*;
import com.jeff.pets.mixin.client.SplashManagerMixin;
import com.jeff.pets.mixin.client.TitleScreenRenderingMixin;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.builders.*;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Uses both the <a href="https://modrinth.com/mod/yacl">YACL</a> and <a href="https://modrinth.com/mod/modmenu">Mod Menu</a> APIs to create a screen and hook it into the Mod Menu.
 * Uses the config options available in {@link PetsConfig}.
 *
 * @see Central
 */
@SuppressWarnings({"unchecked", ""})
public class PetsConfigScreen<T extends Enum & NameableEnum> implements ModMenuApi {

    private static final PetsConfigScreen INSTANCE = new PetsConfigScreen();

    /**
     * This is one of the most important values in this class. It allows the easy swapping out
     * of the enums that store the petskins, in turn allowing the PetSkins option below to
     * carry and assign different values based on what the user's currently active pet is.
     */
    public Class<T> enumClass = (Class<T>) DuckSkins.class;

    /**
     * Returns this class for easy access to the non-static methods
     */
    public static PetsConfigScreen getInstance() {
        return INSTANCE;
    }

    private static String getInstalledAddons() {
        if (PetsClientInitializer.ADDONS.isEmpty()) {
            return "none";
        }
        return String.join(", \n", PetsClientInitializer.ADDONS);
    }

    /**
     * Creates the config screen.
     * Options: <p> {@code Pet Toggle} : Uses a {@code TickBoxControllerBuilder} to allow the
     * user to toggle the pet on or off from inside the config screen.
     * <p> {@code Pet Species}: Uses a {@code EnumDropDownControllerBuilder} to allow the user to
     * 'search' for pets, but keep them restricted from entering an invalid pet and crashing the game.
     * Uses the PetList enum to store its values.
     * <p> {@code Pet Name}: Uses a {@code StringControllerBuilder} to let the user choose any name they like.
     * Uses nearly the exact same logic as {@link Central#createPetNameCommand()}, and you can
     * simply copy-paste any name logic for new mobs over to this option.
     * <p> {@code PetSkins}: The most complex option to code and manage.
     * Uses a {@code EnumControllerBuilder} to let the user cycle through skins - this is important
     * because the user may not know what they are beforehand. Changes the {@link #enumClass} variable stored above
     * in accordance with the current active pet to display the correct skins. A mismatch can cause an {@code IllegalArgumentException},
     * so be very careful when editing and make sure to assign the correct values to each other.
     * The casting {@code Option} to {@code Option<T>} is extremely important, and the compiler
     * will generate an error without it. However, since {@code Option<T>} is always an instance of
     * {@code Option}, this will not generate a {@code ClassCastException}.
     * <p> {@code Baby?}: Uses a {@code TickBoxControllerBuilder} to let the user decide
     * whether their pet is a baby or not.
     * <p> {@code Custom Title Enabled}: Controls whether the game will use the custom PetsMod title, edition, and splash text.
     * Please note that the splash text is only initialized once, during the game launch, so
     * it will require a restart to change, but everything else will adjust instantly. (Note: a restart
     * is not forced upon the user, as it is only splash text and won't impact gameplay severely.)
     *
     * @see SplashManagerMixin
     * @see TitleScreenRenderingMixin
     */
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> {
            PetsConfig CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();
            ConfigBuilder builder = ConfigBuilder.create()
                    .setTitle(Component.literal("Config"))
                    .setSavingRunnable(() -> {
                        AutoConfig.getConfigHolder(PetsConfig.class).save();
                        Minecraft.getInstance().setScreen(this.getModConfigScreenFactory().create(null));
                    })
                    .setTransparentBackground(true);
                    ConfigCategory general = builder.getOrCreateCategory(Component.literal("Config"));
                    ConfigEntryBuilder entryBuilder = builder.entryBuilder();
                    general.addEntry(this.createPetOnOption(entryBuilder, CONFIG).build());
                    general.addEntry(this.createPetSpeciesOption(entryBuilder, CONFIG).build());
                    general.addEntry(this.createPetNameOption(entryBuilder, CONFIG).build());
                    general.addEntry(this.createPetSkinOption(entryBuilder, CONFIG).build());
                    general.addEntry(this.createBabyOption(entryBuilder, CONFIG).build());

                    return builder.build();
        };
    }

    private BooleanToggleBuilder createPetOnOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startBooleanToggle(Component.literal("Pet On"), CONFIG.petOn)
                .setSaveConsumer((newVal) -> CONFIG.petOn = newVal);
    }

    private DropdownMenuBuilder<String> createPetSpeciesOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startStringDropdownMenu(Component.literal("Pet Species"), CONFIG.activePet)
                .setSaveConsumer((newVal) -> {
                    CONFIG.activePet = newVal;
                    Central.despawnPet();
                    Central.summonPet();
                });
    }
    private StringFieldBuilder createPetNameOption(ConfigEntryBuilder builder,PetsConfig CONFIG) {
        String activePet = CONFIG.activePet;
        String defaultVal = switch (CONFIG.activePet) {
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
            case "wandering_trader" -> CONFIG.wanderingTraderName;
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
            case "angry_ghast" -> CONFIG.angryGhastName;
            case "batato" -> CONFIG.batatoName;
            case "diamond_chicken" -> CONFIG.diamondChickenName;
            case "love_golem" -> CONFIG.loveGolemName;
            case "mega_spud" -> CONFIG.megaSpudName;
            case "moon_cow" -> CONFIG.moonCowName;
            case "nerd_creeper" -> CONFIG.nerdCreeperName;
            case "pink_wither" -> CONFIG.pinkWitherName;
            case "plaguewhale_slab" -> CONFIG.plaguewhaleSlabName;
            case "poisonous_potato_zombie" -> CONFIG.poisonousPotatoZombieName;
            case "ray_tracing" -> CONFIG.rayTracingName;
            case "redstone_bug" -> CONFIG.redstoneBugName;
            case "smiling_creeper" -> CONFIG.smilingCreeperName;
            case "toxifin_slab" -> CONFIG.toxfinSlabName;
            case "potato_husk" -> CONFIG.potatoHuskName;
            case "head" -> CONFIG.headName;
            case "dumbo_octopus" -> CONFIG.dumboOctopusName;
            case "koi" -> CONFIG.koiName;
            case "stingray" -> CONFIG.stingrayName;
            default -> "";
        };
        return builder.startStrField(Component.literal("Pet Name"), defaultVal)
                .setSaveConsumer((name) -> {
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
                        case "ender_dragon" -> CONFIG.enderDragonName = name;
                        case "wither" -> CONFIG.witherName = name;
                        case "angry_ghast" -> CONFIG.angryGhastName = name;
                        case "batato" -> CONFIG.batatoName = name;
                        case "diamond_chicken" -> CONFIG.diamondChickenName = name;
                        case "love_golem" -> CONFIG.loveGolemName = name;
                        case "mega_spud" -> CONFIG.megaSpudName = name;
                        case "moon_cow" -> CONFIG.moonCowName = name;
                        case "nerd_creeper" -> CONFIG.nerdCreeperName = name;
                        case "pink_wither" -> CONFIG.pinkWitherName = name;
                        case "plaguewhale_slab" -> CONFIG.plaguewhaleSlabName = name;
                        case "poisonous_potato_zombie" -> CONFIG.poisonousPotatoZombieName = name;
                        case "ray_tracing" -> CONFIG.rayTracingName = name;
                        case "redstone_bug" -> CONFIG.redstoneBugName = name;
                        case "smiling_creeper" -> CONFIG.smilingCreeperName = name;
                        case "toxifin_slab" -> CONFIG.toxfinSlabName = name;
                        case "potato_husk" -> CONFIG.potatoHuskName = name;
                        case "head" -> CONFIG.headName = name;
                        case "dumbo_octopus" -> CONFIG.dumboOctopusName = name;
                        case "koi" -> CONFIG.koiName = name;
                        case "stingray" -> CONFIG.stingrayName = name;
                    }
                    Central.refreshPetNames();
                });
    }

    private EnumSelectorBuilder<?> createPetSkinOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        T initialValue = (T) switch (CONFIG.activePet) {
            case "duck" -> DuckSkins.valueOf(CONFIG.duckSkin.replaceAll(" ", "_"));
            case "cat" -> CatSkins.valueOf(CONFIG.catSkin.replaceAll(" ", "_"));
            case "racoon" -> RacoonSkins.valueOf(CONFIG.racoonSkin.replaceAll(" ", "_"));
            case "sheep" -> SheepSkins.valueOf(CONFIG.sheepSkin.replaceAll(" ", "_"));
            case "axolotl" -> AxolotlSkins.valueOf(CONFIG.axolotlSkin.replaceAll(" ", "_"));
            case "camel" -> CamelSkins.valueOf(CONFIG.camelSkin.replaceAll(" ", "_"));
            case "chicken" -> ChickenSkins.valueOf(CONFIG.chickenSkin.replaceAll(" ", "_"));
            case "creeper", "nerd_creeper", "smiling_creeper" ->
                    CreeperSkins.valueOf(CONFIG.creeperSkin.replaceAll(" ", "_"));
            case "copper_golem" -> CopperGolemSkins.valueOf(CONFIG.copperGolemSkin.replaceAll(" ", "_"));
            case "cow" -> CowSkins.valueOf(CONFIG.cowSkin.replaceAll(" ", "_"));
            case "frog" -> FrogSkins.valueOf(CONFIG.frogSkin.replaceAll(" ", "_"));
            case "horse" -> HorseSkins.valueOf(CONFIG.horseSkin.replaceAll(" ", "_"));
            case "parrot" -> ParrotSkins.valueOf(CONFIG.parrotSkin.replaceAll(" ", "_"));
            case "pig" -> PigSkins.valueOf(CONFIG.pigSkin.replaceAll(" ", "_"));
            case "rabbit" -> RabbitSkins.valueOf(CONFIG.rabbitSkin.replaceAll(" ", "_"));
            case "snow_golem" -> SnowGolemSkins.valueOf(CONFIG.snowGolemSkin.replaceAll(" ", "_"));
            case "squid" -> SquidSkins.valueOf(CONFIG.squidSkin.replaceAll(" ", "_"));
            case "strider" -> StriderSkins.valueOf(CONFIG.striderSkin.replaceAll(" ", "_"));
            case "tropical_fish" -> TropicalFishSkins.valueOf(CONFIG.tropicalFishSkin.replaceAll(" ", "_"));
            case "villager" -> VillagerSkins.valueOf(CONFIG.villagerSkin.replaceAll(" ", "_"));
            case "mooshroom" -> MooshroomSkins.valueOf(CONFIG.mooshroomSkin.replaceAll(" ", "_"));
            case "bee" -> BeeSkins.valueOf(CONFIG.beeSkin.replaceAll(" ", "_"));
            case "fox" -> FoxSkins.valueOf(CONFIG.foxSkin.replaceAll(" ", "_"));
            case "llama" -> LlamaSkins.valueOf(CONFIG.llamaSkin.replaceAll(" ", "_"));
            case "nautilus" -> NautilusSkins.valueOf(CONFIG.nautilusSkin.replaceAll(" ", "_"));
            case "panda" -> PandaSkins.valueOf(CONFIG.pandaSkin.replaceAll(" ", "_"));
            case "piglin" -> PiglinSkins.valueOf(CONFIG.piglinSkin.replaceAll(" ", "_"));
            case "wolf" -> WolfSkins.valueOf(CONFIG.wolfSkin.replaceAll(" ", "_"));
            case "hoglin" -> HoglinSkins.valueOf(CONFIG.hoglinSkin.replaceAll(" ", "_"));
            case "magma_cube" -> SlimeLikeSkins.valueOf(CONFIG.magmaCubeSkin.replaceAll(" ", "_"));
            case "slime", "tropical_slime" -> SlimeLikeSkins.valueOf(CONFIG.slimeSkin.replaceAll(" ", "_"));
            case "zombie_villager" -> ZombieVillagerSkins.valueOf(CONFIG.zombieVillagerSkin.replaceAll(" ", "_"));
            case "wither" -> WitherSkins.valueOf(CONFIG.witherSkin.replaceAll(" ", "_"));
            case "dumbo_octopus" -> DumboOctopusSkins.valueOf(CONFIG.dumboOctopusSkin.replaceAll(" ", "_"));
            default -> PetList.valueOf(CONFIG.activePet.replaceAll(" ", "_"));
        };
        return builder.startEnumSelector(Component.literal("Pet Skin"), enumClass, initialValue)
                .setSaveConsumer((value) -> {
                    String val = value.getDisplayName().getString().replace(" ", "_");
                    enumClass = (Class<T>) switch (CONFIG.activePet) {
                        case "duck" -> (Class<T>) DuckSkins.class;
                        case "racoon" -> (Class<T>) RacoonSkins.class;
                        case "strider" -> (Class<T>) StriderSkins.class;
                        case "sheep" -> (Class<T>) SheepSkins.class;
                        case "cat" ->  (Class<T>) CatSkins.class;
                        case "axolotl" ->  (Class<T>) AxolotlSkins.class;
                        case "camel" ->  (Class<T>) CamelSkins.class;
                        case "chicken" ->  (Class<T>) ChickenSkins.class;
                        case "creeper", "nerd_creeper", "smiling_creeper" ->  (Class<T>) CreeperSkins.class;
                        case "copper_golem" ->  (Class<T>) CopperGolemSkins.class;
                        case "cow" ->  (Class<T>) CowSkins.class;
                        case "frog" ->  (Class<T>) FrogSkins.class;
                        case "horse" ->  (Class<T>) HorseSkins.class;
                        case "parrot" ->  (Class<T>) ParrotSkins.class;
                        case "pig" ->  (Class<T>) PigSkins.class;
                        case "rabbit" ->  (Class<T>) RabbitSkins.class;
                        case "snow_golem" ->  (Class<T>) SnowGolemSkins.class;
                        case "squid" ->  (Class<T>) SquidSkins.class;
                        case "tropical_fish" ->  (Class<T>) TropicalFishSkins.class;
                        case "villager" ->  (Class<T>) VillagerSkins.class;
                        case "mooshroom" ->  (Class<T>) MooshroomSkins.class;
                        case "bee" ->  (Class<T>) BeeSkins.class;
                        case "fox" ->  (Class<T>) FoxSkins.class;
                        case "llama" ->  (Class<T>) LlamaSkins.class;
                        case "nautilus" ->  (Class<T>) NautilusSkins.class;
                        case "panda" ->  (Class<T>) PandaSkins.class;
                        case "piglin" ->  (Class<T>) PiglinSkins.class;
                        case "wolf" ->  (Class<T>) WolfSkins.class;
                        case "hoglin" ->  (Class<T>) HoglinSkins.class;
                        case "magma_cube", "slime" ->  (Class<T>) SlimeLikeSkins.class;
                        case "zombie_villager" ->  (Class<T>) ZombieVillagerSkins.class;
                        case "shulker" ->  (Class<T>) ShulkerSkins.class;
                        case "wither" ->  (Class<T>) WitherSkins.class;
                        case "dumbo_octopus" ->  (Class<T>) DumboOctopusSkins.class;
                        default -> BlankEnum.class;
                    };
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
                            } else if (Objects.equals(val, "bronze")) {
                                CONFIG.duckSkin = "bronze";
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
                            } else if (Objects.equals(val, "zombified_piglin")) {
                                CONFIG.piglinSkin = "zombified_piglin";
                            } else if (Objects.equals(val, "piglin_brute")) {
                                CONFIG.piglinSkin = "piglin_brute";
                            }
                        }
                        case "wolf" -> {
                            if (Objects.equals(val, "pale")) {
                                CONFIG.wolfSkin = "pale";
                            } else if (Objects.equals(val, "ashen")) {
                                CONFIG.wolfSkin = "ashen";
                            } else if (Objects.equals(val, "black")) {
                                CONFIG.wolfSkin = "black";
                            } else if (Objects.equals(val, "chestnut")) {
                                CONFIG.wolfSkin = "chestnut";
                            } else if (Objects.equals(val, "rusty")) {
                                CONFIG.wolfSkin = "rusty";
                            } else if (Objects.equals(val, "snowy")) {
                                CONFIG.wolfSkin = "snowy";
                            } else if (Objects.equals(val, "spotted")) {
                                CONFIG.wolfSkin = "spotted";
                            } else if (Objects.equals(val, "striped")) {
                                CONFIG.wolfSkin = "striped";
                            } else if (Objects.equals(val, "woods")) {
                                CONFIG.wolfSkin = "woody";
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
                        case "slime", "tropical_slime" -> {
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
                        }
                        case "creeper", "nerd_creeper", "smiling_creeper" -> {
                            if (Objects.equals(val, "normal")) {
                                CONFIG.creeperSkin = "normal";
                            } else if (Objects.equals(val, "charged")) {
                                CONFIG.creeperSkin = "charged";
                            }
                        }
                        case "wither" -> {
                            if (Objects.equals(val, "normal")) {
                                CONFIG.witherSkin = "normal";
                            } else if (Objects.equals(val, "invulnerable")) {
                                CONFIG.witherSkin = "invulnerable";
                            }
                        }
                        case "dumbo_octopus" -> {
                            if (Objects.equals(val, "yellow")) {
                                CONFIG.dumboOctopusSkin = "yellow";
                            } else if (Objects.equals(val, "red")) {
                                CONFIG.dumboOctopusSkin = "red";
                            } else if (Objects.equals(val, "blue")) {
                                CONFIG.dumboOctopusSkin = "blue";
                            } else if (Objects.equals(val, "green")) {
                                CONFIG.dumboOctopusSkin = "green";
                            } else if (Objects.equals(val, "orange")) {
                                CONFIG.dumboOctopusSkin = "orange";
                            } else if (Objects.equals(val, "pink")) {
                                CONFIG.dumboOctopusSkin = "pink";
                            }
                        }
                    }
                });
    }

    private BooleanToggleBuilder createBabyOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startBooleanToggle(Component.literal("Baby?"), CONFIG.isBaby)
                .setSaveConsumer((newVal) -> CONFIG.isBaby = newVal);
    }

    private TextDescriptionBuilder createAddonsOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startTextDescription(Component.literal("Installed addons: " + PetsClientInitializer.ADDONS.size()));
    }
}
