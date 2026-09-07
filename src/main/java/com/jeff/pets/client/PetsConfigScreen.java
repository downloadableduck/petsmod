package com.jeff.pets.client;

import com.jeff.pets.client.enums.*;
import com.jeff.pets.client.mixin.client.SplashManagerMixin;
import com.jeff.pets.client.mixin.client.TitleScreenRenderingMixin;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.forge.clothconfig2.api.ConfigBuilder;
import me.shedaniel.forge.clothconfig2.api.ConfigCategory;
import me.shedaniel.forge.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.DropdownMenuBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.EnumSelectorBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.StringFieldBuilder;
import net.minecraft.client.gui.GuiScreen;

import java.util.Objects;

import static com.jeff.pets.client.Central.MOD_ID;

/**
 * Uses both the <a href="https://modrinth.com/mod/yacl">YACL</a> and <a href="https://modrinth.com/mod/modmenu">Mod Menu</a> APIs to create a screen and hook it into the Mod Menu.
 * Uses the config options available in {@link PetsConfig}.
 *
 * @see Central
 */
//@Mod(modid = MOD_ID)
public class PetsConfigScreen<T extends Enum & NameableEnum> {


    static {
        PetsConfigScreen this_ = new PetsConfigScreen();
        //ModContainer modContainer = ForgeModContainer.getInstance();
        /*modContainer.bindMetadata(ExtensionPoint.CONFIGGUIFACTORY, () -> {
            PetsConfig CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();
            return ((minecraft, s) -> {
                ConfigBuilder builder = ConfigBuilder.create()
                        .setTitle("Config")
                        .setSavingRunnable(() -> {
                            AutoConfig.getConfigHolder(PetsConfig.class).save();
                        })
                        .setTransparentBackground(true);
                ConfigCategory general = builder.getOrCreateCategory("Config");
                ConfigEntryBuilder entryBuilder = builder.entryBuilder();
                general.addEntry(this_.createPetOnOption(entryBuilder, CONFIG).build());
                general.addEntry(this_.createPetSpeciesOption(entryBuilder, CONFIG).build());
                general.addEntry(this_.createPetNameOption(entryBuilder, CONFIG).build());
                general.addEntry(this_.createPetSkinOption(entryBuilder, CONFIG).build());
                general.addEntry(this_.createBabyOption(entryBuilder, CONFIG).build());

                return builder.build();
            });
        });*/
    }

    /**
     * This is one of the most important values in this class. It allows the easy swapping out
     * of the enums that store the petskins, in turn allowing the PetSkins option below to
     * carry and assign different values based on what the user's currently active pet is.
     */
    public Class<T> enumClass = (Class<T>) DuckSkins.class;

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
    public PetsConfigScreen() {
        /*ModContainer modContainer = ModLoadingContext.get().getActiveContainer();
        modContainer.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> {
            PetsConfig CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();
            return ((minecraft, s) -> {
                ConfigBuilder builder = ConfigBuilder.create()
                        .setTitle("Config")
                        .setSavingRunnable(() -> {
                            AutoConfig.getConfigHolder(PetsConfig.class).save();
                        })
                        .setTransparentBackground(true);
                ConfigCategory general = builder.getOrCreateCategory("Config");
                ConfigEntryBuilder entryBuilder = builder.entryBuilder();
                general.addEntry(this.createPetOnOption(entryBuilder, CONFIG).build());
                general.addEntry(this.createPetSpeciesOption(entryBuilder, CONFIG).build());
                general.addEntry(this.createPetNameOption(entryBuilder, CONFIG).build());
                general.addEntry(this.createPetSkinOption(entryBuilder, CONFIG).build());
                general.addEntry(this.createBabyOption(entryBuilder, CONFIG).build());

                return builder.build();
            });
        });*/
    }

    private static String getInstalledAddons() {
        //if (Central.ADDONS.isEmpty()) {
        return "none";
        //}
        //return String.join(", \n", Central.ADDONS);
    }

    private BooleanToggleBuilder createPetOnOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startBooleanToggle("Pet On", CONFIG.petOn)
                .setSaveConsumer((newVal) -> CONFIG.petOn = newVal);
    }

    private DropdownMenuBuilder<String> createPetSpeciesOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startStringDropdownMenu("Pet Species", CONFIG.activePet)
                .setSaveConsumer((newVal) -> {
                    CONFIG.activePet = newVal;
                    Central.despawnPet();
                    Central.summonPet();
                });
    }

    private StringFieldBuilder createPetNameOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        String activePet = CONFIG.activePet;
        String defaultVal;
        switch (CONFIG.activePet) {
            case "penguin":
                defaultVal = CONFIG.penguinName;
                break;
            case "racoon":
                defaultVal = CONFIG.racoonName;
                break;
            case "duck":
                defaultVal = CONFIG.duckName;
                break;
            case "cat":
                defaultVal = CONFIG.catName;
                break;
            case "sheep":
                defaultVal = CONFIG.sheepName;
                break;
            case "allay":
                defaultVal = CONFIG.allayName;
                break;
            case "armadillo":
                defaultVal = CONFIG.armadilloName;
                break;
            case "bat":
                defaultVal = CONFIG.batName;
                break;
            case "camel":
                defaultVal = CONFIG.camelName;
                break;
            case "chicken":
                defaultVal = CONFIG.chickenSkin;
                break;
            case "cod":
                defaultVal = CONFIG.codName;
                break;
            case "copper_golem":
                defaultVal = CONFIG.copperGolemName;
                break;
            case "cow":
                defaultVal = CONFIG.cowName;
                break;
            case "donkey":
                defaultVal = CONFIG.donkeyName;
                break;
            case "frog":
                defaultVal = CONFIG.frogName;
                break;
            case "horse":
                defaultVal = CONFIG.horseName;
                break;
            case "mooshroom":
                defaultVal = CONFIG.mooshroomName;
                break;
            case "mule":
                defaultVal = CONFIG.muleName;
                break;
            case "parrot":
                defaultVal = CONFIG.parrotName;
                break;
            case "pig":
                defaultVal = CONFIG.pigName;
                break;
            case "rabbit":
                defaultVal = CONFIG.rabbitName;
                break;
            case "salmon":
                defaultVal = CONFIG.salmonName;
                break;
            case "sniffer":
                defaultVal = CONFIG.snifferName;
                break;
            case "snow_golem":
                defaultVal = CONFIG.snowGolemName;
                break;
            case "squid":
                defaultVal = CONFIG.squidName;
                break;
            case "strider":
                defaultVal = CONFIG.striderName;
                break;
            case "tadpole":
                defaultVal = CONFIG.tadpoleName;
                break;
            case "tropical_fish":
                defaultVal = CONFIG.tropicalFishName;
                break;
            case "turtle":
                defaultVal = CONFIG.turtleName;
                break;
            case "villager":
                defaultVal = CONFIG.villagerName;
                break;
            case "wandering_trader":
                defaultVal = CONFIG.wanderingTraderName;
                break;
            case "bee":
                defaultVal = CONFIG.beeName;
                break;
            case "cave_spider":
                defaultVal = CONFIG.caveSpiderName;
                break;
            case "dolphin":
                defaultVal = CONFIG.dolphinName;
                break;
            case "enderman":
                defaultVal = CONFIG.endermanName;
                break;
            case "fox":
                defaultVal = CONFIG.foxName;
                break;
            case "goat":
                defaultVal = CONFIG.goatName;
                break;
            case "iron_golem":
                defaultVal = CONFIG.ironGolemName;
                break;
            case "llama":
                defaultVal = CONFIG.llamaName;
                break;
            case "nautilus":
                defaultVal = CONFIG.nautilusName;
                break;
            case "panda":
                defaultVal = CONFIG.pandaName;
                break;
            case "piglin":
                defaultVal = CONFIG.piglinName;
                break;
            case "polar_bear":
                defaultVal = CONFIG.polarBearName;
                break;
            case "pufferfish":
                defaultVal = CONFIG.pufferFishName;
                break;
            case "spider":
                defaultVal = CONFIG.spiderName;
                break;
            case "wolf":
                defaultVal = CONFIG.wolfName;
                break;
            case "blaze":
                defaultVal = CONFIG.blazeName;
                break;
            case "breeze":
                defaultVal = CONFIG.breezeName;
                break;
            case "creaking":
                defaultVal = CONFIG.creakingName;
                break;
            case "creeper":
                defaultVal = CONFIG.creeperName;
                break;
            case "elder_guardian":
                defaultVal = CONFIG.elderGuardianName;
                break;
            case "endermite":
                defaultVal = CONFIG.endermiteName;
                break;
            case "evoker":
                defaultVal = CONFIG.evokerName;
                break;
            case "ghast":
                defaultVal = CONFIG.ghastName;
                break;
            case "guardian":
                defaultVal = CONFIG.guardianName;
                break;
            case "hoglin":
                defaultVal = CONFIG.hoglinName;
                break;
            case "magma_cube":
                defaultVal = CONFIG.magmaCubeName;
                break;
            case "phantom":
                defaultVal = CONFIG.phantomName;
                break;
            case "pillager":
                defaultVal = CONFIG.pillagerName;
                break;
            case "ravager":
                defaultVal = CONFIG.ravagerName;
                break;
            case "shulker":
                defaultVal = CONFIG.shulkerName;
                break;
            case "silverfish":
                defaultVal = CONFIG.silverfishName;
                break;
            case "skeleton":
                defaultVal = CONFIG.skeletonName;
                break;
            case "slime":
                defaultVal = CONFIG.slimeName;
                break;
            case "vex":
                defaultVal = CONFIG.vexName;
                break;
            case "vindicator":
                defaultVal = CONFIG.vindicatorName;
                break;
            case "warden":
                defaultVal = CONFIG.wardenName;
                break;
            case "witch":
                defaultVal = CONFIG.witchName;
                break;
            case "zombie":
                defaultVal = CONFIG.zombieName;
                break;
            case "zombie_villager":
                defaultVal = CONFIG.zombieVillagerName;
                break;
            case "angry_ghast":
                defaultVal = CONFIG.angryGhastName;
                break;
            case "batato":
                defaultVal = CONFIG.batatoName;
                break;
            case "diamond_chicken":
                defaultVal = CONFIG.diamondChickenName;
                break;
            case "love_golem":
                defaultVal = CONFIG.loveGolemName;
                break;
            case "mega_spud":
                defaultVal = CONFIG.megaSpudName;
                break;
            case "moon_cow":
                defaultVal = CONFIG.moonCowName;
                break;
            case "nerd_creeper":
                defaultVal = CONFIG.nerdCreeperName;
                break;
            case "pink_wither":
                defaultVal = CONFIG.pinkWitherName;
                break;
            case "plaguewhale_slab":
                defaultVal = CONFIG.plaguewhaleSlabName;
                break;
            case "poisonous_potato_zombie":
                defaultVal = CONFIG.poisonousPotatoZombieName;
                break;
            case "ray_tracing":
                defaultVal = CONFIG.rayTracingName;
                break;
            case "redstone_bug":
                defaultVal = CONFIG.redstoneBugName;
                break;
            case "smiling_creeper":
                defaultVal = CONFIG.smilingCreeperName;
                break;
            case "toxifin_slab":
                defaultVal = CONFIG.toxfinSlabName;
                break;
            case "potato_husk":
                defaultVal = CONFIG.potatoHuskName;
                break;
            case "head":
                defaultVal = CONFIG.headName;
                break;
            case "dumbo_octopus":
                defaultVal = CONFIG.dumboOctopusName;
                break;
            case "koi":
                defaultVal = CONFIG.koiName;
                break;
            case "stingray":
                defaultVal = CONFIG.stingrayName;
                break;
            case "zombie_pigman":
                defaultVal = CONFIG.zombiePigmanName;
                break;
            default:
                defaultVal = "";
                break;
        }
        return builder.startStrField("Pet Name", defaultVal)
                .setSaveConsumer((name) -> {
                    if (activePet.equals("penguin")) {
                        CONFIG.penguinName = name;
                    } else if (activePet.equals("racoon")) {
                        CONFIG.racoonName = name;
                    } else if (activePet.equals("duck")) {
                        CONFIG.duckName = name;
                    } else if (activePet.equals("cat")) {
                        CONFIG.catName = name;
                    } else if (activePet.equals("allay")) {
                        CONFIG.allayName = name;
                    } else if (activePet.equals("armadillo")) {
                        CONFIG.armadilloName = name;
                    } else if (activePet.equals("bat")) {
                        CONFIG.batName = name;
                    } else if (activePet.equals("camel")) {
                        CONFIG.camelName = name;
                    } else if (activePet.equals("chicken")) {
                        CONFIG.chickenSkin = name;
                    } else if (activePet.equals("cod")) {
                        CONFIG.codName = name;
                    } else if (activePet.equals("copper_golem")) {
                        CONFIG.copperGolemName = name;
                    } else if (activePet.equals("cow")) {
                        CONFIG.cowName = name;
                    } else if (activePet.equals("donkey")) {
                        CONFIG.donkeyName = name;
                    } else if (activePet.equals("frog")) {
                        CONFIG.frogName = name;
                    } else if (activePet.equals("horse")) {
                        CONFIG.horseName = name;
                    } else if (activePet.equals("mooshroom")) {
                        CONFIG.mooshroomName = name;
                    } else if (activePet.equals("mule")) {
                        CONFIG.muleName = name;
                    } else if (activePet.equals("parrot")) {
                        CONFIG.parrotName = name;
                    } else if (activePet.equals("pig")) {
                        CONFIG.pigName = name;
                    } else if (activePet.equals("rabbit")) {
                        CONFIG.rabbitName = name;
                    } else if (activePet.equals("salmon")) {
                        CONFIG.salmonName = name;
                    } else if (activePet.equals("sniffer")) {
                        CONFIG.snifferName = name;
                    } else if (activePet.equals("snow_golem")) {
                        CONFIG.snowGolemName = name;
                    } else if (activePet.equals("squid")) {
                        CONFIG.squidName = name;
                    } else if (activePet.equals("strider")) {
                        CONFIG.striderName = name;
                    } else if (activePet.equals("tadpole")) {
                        CONFIG.tadpoleName = name;
                    } else if (activePet.equals("tropical_fish")) {
                        CONFIG.tropicalFishName = name;
                    } else if (activePet.equals("turtle")) {
                        CONFIG.turtleName = name;
                    } else if (activePet.equals("villager")) {
                        CONFIG.villagerName = name;
                    } else if (activePet.equals("wandering_trader")) {
                        CONFIG.wanderingTraderName = name;
                    } else if (activePet.equals("bee")) {
                        CONFIG.beeName = name;
                    } else if (activePet.equals("cave_spider")) {
                        CONFIG.caveSpiderName = name;
                    } else if (activePet.equals("dolphin")) {
                        CONFIG.dolphinName = name;
                    } else if (activePet.equals("enderman")) {
                        CONFIG.endermanName = name;
                    } else if (activePet.equals("fox")) {
                        CONFIG.foxName = name;
                    } else if (activePet.equals("goat")) {
                        CONFIG.goatName = name;
                    } else if (activePet.equals("iron_golem")) {
                        CONFIG.ironGolemName = name;
                    } else if (activePet.equals("llama")) {
                        CONFIG.llamaName = name;
                    } else if (activePet.equals("nautilus")) {
                        CONFIG.nautilusName = name;
                    } else if (activePet.equals("panda")) {
                        CONFIG.pandaName = name;
                    } else if (activePet.equals("piglin")) {
                        CONFIG.piglinName = name;
                    } else if (activePet.equals("polar_bear")) {
                        CONFIG.polarBearName = name;
                    } else if (activePet.equals("pufferfish")) {
                        CONFIG.pufferFishName = name;
                    } else if (activePet.equals("spider")) {
                        CONFIG.spiderName = name;
                    } else if (activePet.equals("wolf")) {
                        CONFIG.wolfName = name;
                    } else if (activePet.equals("blaze")) {
                        CONFIG.blazeName = name;
                    } else if (activePet.equals("breeze")) {
                        CONFIG.breezeName = name;
                    } else if (activePet.equals("creaking")) {
                        CONFIG.creakingName = name;
                    } else if (activePet.equals("creeper")) {
                        CONFIG.creeperName = name;
                    } else if (activePet.equals("elder_guardian")) {
                        CONFIG.elderGuardianName = name;
                    } else if (activePet.equals("endermite")) {
                        CONFIG.endermiteName = name;
                    } else if (activePet.equals("evoker")) {
                        CONFIG.evokerName = name;
                    } else if (activePet.equals("ghast")) {
                        CONFIG.ghastName = name;
                    } else if (activePet.equals("guardian")) {
                        CONFIG.guardianName = name;
                    } else if (activePet.equals("hoglin")) {
                        CONFIG.hoglinName = name;
                    } else if (activePet.equals("magma_cube")) {
                        CONFIG.magmaCubeName = name;
                    } else if (activePet.equals("phantom")) {
                        CONFIG.phantomName = name;
                    } else if (activePet.equals("pillager")) {
                        CONFIG.pillagerName = name;
                    } else if (activePet.equals("ravager")) {
                        CONFIG.ravagerName = name;
                    } else if (activePet.equals("shulker")) {
                        CONFIG.shulkerName = name;
                    } else if (activePet.equals("silverfish")) {
                        CONFIG.silverfishName = name;
                    } else if (activePet.equals("skeleton")) {
                        CONFIG.skeletonName = name;
                    } else if (activePet.equals("slime")) {
                        CONFIG.slimeName = name;
                    } else if (activePet.equals("vex")) {
                        CONFIG.vexName = name;
                    } else if (activePet.equals("vindicator")) {
                        CONFIG.vindicatorName = name;
                    } else if (activePet.equals("warden")) {
                        CONFIG.wardenName = name;
                    } else if (activePet.equals("witch")) {
                        CONFIG.witchName = name;
                    } else if (activePet.equals("zombie")) {
                        CONFIG.zombieName = name;
                    } else if (activePet.equals("zombie_villager")) {
                        CONFIG.zombieVillagerName = name;
                    } else if (activePet.equals("ender_dragon")) {
                        CONFIG.enderDragonName = name;
                    } else if (activePet.equals("wither")) {
                        CONFIG.witherName = name;
                    } else if (activePet.equals("angry_ghast")) {
                        CONFIG.angryGhastName = name;
                    } else if (activePet.equals("batato")) {
                        CONFIG.batatoName = name;
                    } else if (activePet.equals("diamond_chicken")) {
                        CONFIG.diamondChickenName = name;
                    } else if (activePet.equals("love_golem")) {
                        CONFIG.loveGolemName = name;
                    } else if (activePet.equals("mega_spud")) {
                        CONFIG.megaSpudName = name;
                    } else if (activePet.equals("moon_cow")) {
                        CONFIG.moonCowName = name;
                    } else if (activePet.equals("nerd_creeper")) {
                        CONFIG.nerdCreeperName = name;
                    } else if (activePet.equals("pink_wither")) {
                        CONFIG.pinkWitherName = name;
                    } else if (activePet.equals("plaguewhale_slab")) {
                        CONFIG.plaguewhaleSlabName = name;
                    } else if (activePet.equals("poisonous_potato_zombie")) {
                        CONFIG.poisonousPotatoZombieName = name;
                    } else if (activePet.equals("ray_tracing")) {
                        CONFIG.rayTracingName = name;
                    } else if (activePet.equals("redstone_bug")) {
                        CONFIG.redstoneBugName = name;
                    } else if (activePet.equals("smiling_creeper")) {
                        CONFIG.smilingCreeperName = name;
                    } else if (activePet.equals("toxifin_slab")) {
                        CONFIG.toxfinSlabName = name;
                    } else if (activePet.equals("potato_husk")) {
                        CONFIG.potatoHuskName = name;
                    } else if (activePet.equals("head")) {
                        CONFIG.headName = name;
                    } else if (activePet.equals("dumbo_octopus")) {
                        CONFIG.dumboOctopusName = name;
                    } else if (activePet.equals("koi")) {
                        CONFIG.koiName = name;
                    } else if (activePet.equals("stingray")) {
                        CONFIG.stingrayName = name;
                    } else if (activePet.equals("zombie_pigman")) {
                        CONFIG.zombiePigmanName = name;
                    }
                    Central.refreshPetNames();
                });
    }

    private EnumSelectorBuilder<?> createPetSkinOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        T initialValue;
        switch (CONFIG.activePet) {
            case "duck":
                initialValue = (T) DuckSkins.valueOf(CONFIG.duckSkin.replaceAll(" ", "_"));
                break;
            case "cat":
                initialValue = (T) CatSkins.valueOf(CONFIG.catSkin.replaceAll(" ", "_"));
                break;
            case "racoon":
                initialValue = (T) RacoonSkins.valueOf(CONFIG.racoonSkin.replaceAll(" ", "_"));
                break;
            case "sheep":
                initialValue = (T) SheepSkins.valueOf(CONFIG.sheepSkin.replaceAll(" ", "_"));
                break;
            case "axolotl":
                initialValue = (T) AxolotlSkins.valueOf(CONFIG.axolotlSkin.replaceAll(" ", "_"));
                break;
            case "camel":
                initialValue = (T) CamelSkins.valueOf(CONFIG.camelSkin.replaceAll(" ", "_"));
                break;
            case "chicken":
                initialValue = (T) ChickenSkins.valueOf(CONFIG.chickenSkin.replaceAll(" ", "_"));
                break;
            case "creeper":
            case "nerd_creeper":
            case "smiling_creeper":
                initialValue = (T) CreeperSkins.valueOf(CONFIG.creeperSkin.replaceAll(" ", "_"));
                break;
            case "copper_golem":
                initialValue = (T) CopperGolemSkins.valueOf(CONFIG.copperGolemSkin.replaceAll(" ", "_"));
                break;
            case "cow":
                initialValue = (T) CowSkins.valueOf(CONFIG.cowSkin.replaceAll(" ", "_"));
                break;
            case "frog":
                initialValue = (T) FrogSkins.valueOf(CONFIG.frogSkin.replaceAll(" ", "_"));
                break;
            case "horse":
                initialValue = (T) HorseSkins.valueOf(CONFIG.horseSkin.replaceAll(" ", "_"));
                break;
            case "parrot":
                initialValue = (T) ParrotSkins.valueOf(CONFIG.parrotSkin.replaceAll(" ", "_"));
                break;
            case "pig":
                initialValue = (T) PigSkins.valueOf(CONFIG.pigSkin.replaceAll(" ", "_"));
                break;
            case "rabbit":
                initialValue = (T) RabbitSkins.valueOf(CONFIG.rabbitSkin.replaceAll(" ", "_"));
                break;
            case "snow_golem":
                initialValue = (T) SnowGolemSkins.valueOf(CONFIG.snowGolemSkin.replaceAll(" ", "_"));
                break;
            case "squid":
                initialValue = (T) SquidSkins.valueOf(CONFIG.squidSkin.replaceAll(" ", "_"));
                break;
            case "strider":
                initialValue = (T) StriderSkins.valueOf(CONFIG.striderSkin.replaceAll(" ", "_"));
                break;
            case "tropical_fish":
                initialValue = (T) TropicalFishSkins.valueOf(CONFIG.tropicalFishSkin.replaceAll(" ", "_"));
                break;
            case "villager":
                initialValue = (T) VillagerSkins.valueOf(CONFIG.villagerSkin.replaceAll(" ", "_"));
                break;
            case "mooshroom":
                initialValue = (T) MooshroomSkins.valueOf(CONFIG.mooshroomSkin.replaceAll(" ", "_"));
                break;
            case "bee":
                initialValue = (T) BeeSkins.valueOf(CONFIG.beeSkin.replaceAll(" ", "_"));
                break;
            case "fox":
                initialValue = (T) FoxSkins.valueOf(CONFIG.foxSkin.replaceAll(" ", "_"));
                break;
            case "llama":
                initialValue = (T) LlamaSkins.valueOf(CONFIG.llamaSkin.replaceAll(" ", "_"));
                break;
            case "nautilus":
                initialValue = (T) NautilusSkins.valueOf(CONFIG.nautilusSkin.replaceAll(" ", "_"));
                break;
            case "panda":
                initialValue = (T) PandaSkins.valueOf(CONFIG.pandaSkin.replaceAll(" ", "_"));
                break;
            case "piglin":
                initialValue = (T) PiglinSkins.valueOf(CONFIG.piglinSkin.replaceAll(" ", "_"));
                break;
            case "wolf":
                initialValue = (T) WolfSkins.valueOf(CONFIG.wolfSkin.replaceAll(" ", "_"));
                break;
            case "hoglin":
                initialValue = (T) HoglinSkins.valueOf(CONFIG.hoglinSkin.replaceAll(" ", "_"));
                break;
            case "magma_cube":
                initialValue = (T) SlimeLikeSkins.valueOf(CONFIG.magmaCubeSkin.replaceAll(" ", "_"));
                break;
            case "slime":
            case "tropical_slime":
                initialValue = (T) SlimeLikeSkins.valueOf(CONFIG.slimeSkin.replaceAll(" ", "_"));
                break;
            case "zombie_villager":
                initialValue = (T) ZombieVillagerSkins.valueOf(CONFIG.zombieVillagerSkin.replaceAll(" ", "_"));
                break;
            case "wither":
                initialValue = (T) WitherSkins.valueOf(CONFIG.witherSkin.replaceAll(" ", "_"));
                break;
            case "dumbo_octopus":
                initialValue = (T) DumboOctopusSkins.valueOf(CONFIG.dumboOctopusSkin.replaceAll(" ", "_"));
                break;
            default:
                initialValue = (T) PetList.valueOf(CONFIG.activePet.replaceAll(" ", "_"));
                break;
        }
        return builder.startEnumSelector("Pet Skin", enumClass, initialValue)
                .setSaveConsumer((value) -> {
                    String val = value.getDisplayName().func_150260_c().replace(" ", "_");
                    switch (CONFIG.activePet) {
                        case "duck":
                            enumClass = (Class<T>) DuckSkins.class;
                            break;
                        case "racoon":
                            enumClass = (Class<T>) RacoonSkins.class;
                            break;
                        case "strider":
                            enumClass = (Class<T>) StriderSkins.class;
                            break;
                        case "sheep":
                            enumClass = (Class<T>) SheepSkins.class;
                            break;
                        case "cat":
                            enumClass = (Class<T>) CatSkins.class;
                            break;
                        case "axolotl":
                            enumClass = (Class<T>) AxolotlSkins.class;
                            break;
                        case "camel":
                            enumClass = (Class<T>) CamelSkins.class;
                            break;
                        case "chicken":
                            enumClass = (Class<T>) ChickenSkins.class;
                            break;
                        case "creeper":
                        case "nerd_creeper":
                        case "smiling_creeper":
                            enumClass = (Class<T>) CreeperSkins.class;
                            break;
                        case "copper_golem":
                            enumClass = (Class<T>) CopperGolemSkins.class;
                            break;
                        case "cow":
                            enumClass = (Class<T>) CowSkins.class;
                            break;
                        case "frog":
                            enumClass = (Class<T>) FrogSkins.class;
                            break;
                        case "horse":
                            enumClass = (Class<T>) HorseSkins.class;
                            break;
                        case "parrot":
                            enumClass = (Class<T>) ParrotSkins.class;
                            break;
                        case "pig":
                            enumClass = (Class<T>) PigSkins.class;
                            break;
                        case "rabbit":
                            enumClass = (Class<T>) RabbitSkins.class;
                            break;
                        case "snow_golem":
                            enumClass = (Class<T>) SnowGolemSkins.class;
                            break;
                        case "squid":
                            enumClass = (Class<T>) SquidSkins.class;
                            break;
                        case "tropical_fish":
                            enumClass = (Class<T>) TropicalFishSkins.class;
                            break;
                        case "villager":
                            enumClass = (Class<T>) VillagerSkins.class;
                            break;
                        case "mooshroom":
                            enumClass = (Class<T>) MooshroomSkins.class;
                            break;
                        case "bee":
                            enumClass = (Class<T>) BeeSkins.class;
                            break;
                        case "fox":
                            enumClass = (Class<T>) FoxSkins.class;
                            break;
                        case "llama":
                            enumClass = (Class<T>) LlamaSkins.class;
                            break;
                        case "nautilus":
                            enumClass = (Class<T>) NautilusSkins.class;
                            break;
                        case "panda":
                            enumClass = (Class<T>) PandaSkins.class;
                            break;
                        case "piglin":
                            enumClass = (Class<T>) PiglinSkins.class;
                            break;
                        case "wolf":
                            enumClass = (Class<T>) WolfSkins.class;
                            break;
                        case "hoglin":
                            enumClass = (Class<T>) HoglinSkins.class;
                            break;
                        case "magma_cube":
                        case "slime":
                            enumClass = (Class<T>) SlimeLikeSkins.class;
                            break;
                        case "zombie_villager":
                            enumClass = (Class<T>) ZombieVillagerSkins.class;
                            break;
                        case "shulker":
                            enumClass = (Class<T>) ShulkerSkins.class;
                            break;
                        case "wither":
                            enumClass = (Class<T>) WitherSkins.class;
                            break;
                        case "dumbo_octopus":
                            enumClass = (Class<T>) DumboOctopusSkins.class;
                            break;
                        default:
                            enumClass = (Class<T>) BlankEnum.class;
                            break;
                    }
                    /**Do NOT replace this with dynamic checking! It will cause a mismatch
                     * between the enums and crash. Use manual checking instead.*/
                    if (CONFIG.activePet.equals("duck")) {
                        if (Objects.equals(val, "mallard")) {
                            CONFIG.duckSkin = "mallard";
                        } else if (Objects.equals(val, "pekin")) {
                            CONFIG.duckSkin = "pekin";
                        } else if (Objects.equals(val, "rubber")) {
                            CONFIG.duckSkin = "rubber";
                        } else if (Objects.equals(val, "bronze")) {
                            CONFIG.duckSkin = "bronze";
                        }
                    } else if (CONFIG.activePet.equals("racoon")) {
                        if (Objects.equals(val, "normal")) {
                            CONFIG.racoonSkin = "normal";
                        } else if (Objects.equals(val, "albino")) {
                            CONFIG.racoonSkin = "albino";
                        }
                    } else if (CONFIG.activePet.equals("sheep")) {
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
                    } else if (CONFIG.activePet.equals("cat")) {
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
                    } else if (CONFIG.activePet.equals("chicken")) {
                        if (Objects.equals(val, "temperate")) {
                            CONFIG.chickenSkin = "temperate";
                        } else if (Objects.equals(val, "cold")) {
                            CONFIG.chickenSkin = "cold";
                        } else if (Objects.equals(val, "warm")) {
                            CONFIG.chickenSkin = "warm";
                        }
                    } else if (CONFIG.activePet.equals("axolotl")) {
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
                    } else if (CONFIG.activePet.equals("camel")) {
                        if (Objects.equals(val, "camel")) {
                            CONFIG.camelSkin = "camel";
                        } else if (Objects.equals(val, "husk")) {
                            CONFIG.camelSkin = "husk";
                        }
                    } else if (CONFIG.activePet.equals("copper_golem")) {
                        if (Objects.equals(val, "unoxidized")) {
                            CONFIG.copperGolemSkin = "unoxidized";
                        } else if (Objects.equals(val, "exposed")) {
                            CONFIG.copperGolemSkin = "exposed";
                        } else if (Objects.equals(val, "weathered")) {
                            CONFIG.copperGolemSkin = "weathered";
                        } else if (Objects.equals(val, "oxidized")) {
                            CONFIG.copperGolemSkin = "oxidized";
                        }
                    } else if (CONFIG.activePet.equals("cow")) {
                        if (Objects.equals(val, "temperate")) {
                            CONFIG.cowSkin = "temperate";
                        } else if (Objects.equals(val, "cold")) {
                            CONFIG.cowSkin = "cold";
                        } else if (Objects.equals(val, "warm")) {
                            CONFIG.cowSkin = "warm";
                        }
                    } else if (CONFIG.activePet.equals("frog")) {
                        if (Objects.equals(val, "temperate")) {
                            CONFIG.frogSkin = "temperate";
                        } else if (Objects.equals(val, "cold")) {
                            CONFIG.frogSkin = "cold";
                        } else if (Objects.equals(val, "warm")) {
                            CONFIG.frogSkin = "warm";
                        }
                    } else if (CONFIG.activePet.equals("horse")) {
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
                    } else if (CONFIG.activePet.equals("parrot")) {
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
                    } else if (CONFIG.activePet.equals("pig")) {
                        if (Objects.equals(val, "temperate")) {
                            CONFIG.pigSkin = "temperate";
                        } else if (Objects.equals(val, "warm")) {
                            CONFIG.pigSkin = "warm";
                        } else if (Objects.equals(val, "cold")) {
                            CONFIG.pigSkin = "cold";
                        }
                    } else if (CONFIG.activePet.equals("rabbit")) {
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
                    } else if (CONFIG.activePet.equals("snow_golem")) {
                        if (Objects.equals(val, "pumpkin_on")) {
                            CONFIG.snowGolemSkin = "pumpkin_on";
                        } else if (Objects.equals(val, "pumpkin_off")) {
                            CONFIG.snowGolemSkin = "pumpkin_off";
                        }
                    } else if (CONFIG.activePet.equals("squid")) {
                        if (Objects.equals(val, "squid")) {
                            CONFIG.squidSkin = "squid";
                        } else if (Objects.equals(val, "glow_squid")) {
                            CONFIG.squidSkin = "glow_squid";
                        }
                    } else if (CONFIG.activePet.equals("tropical_fish")) {
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
                    } else if (CONFIG.activePet.equals("villager")) {
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
                    } else if (CONFIG.activePet.equals("mooshroom")) {
                        if (Objects.equals(val, "red")) {
                            CONFIG.mooshroomSkin = "red";
                        } else if (Objects.equals(val, "brown")) {
                            CONFIG.mooshroomSkin = "brown";
                        }
                    } else if (CONFIG.activePet.equals("strider")) {
                        if (Objects.equals(val, "warm")) {
                            CONFIG.striderSkin = "warm";
                        } else if (Objects.equals(val, "cold")) {
                            CONFIG.striderSkin = "cold";
                        }
                    } else if (CONFIG.activePet.equals("bee")) {
                        if (Objects.equals(val, "happy")) {
                            CONFIG.beeSkin = "happy";
                        } else if (Objects.equals(val, "angry")) {
                            CONFIG.beeSkin = "angry";
                        }
                    } else if (CONFIG.activePet.equals("fox")) {
                        if (Objects.equals(val, "red")) {
                            CONFIG.foxSkin = "red";
                        } else if (Objects.equals(val, "snow")) {
                            CONFIG.foxSkin = "snow";
                        }
                    } else if (CONFIG.activePet.equals("llama")) {
                        if (Objects.equals(val, "brown")) {
                            CONFIG.llamaSkin = "brown";
                        } else if (Objects.equals(val, "creamy")) {
                            CONFIG.llamaSkin = "creamy";
                        } else if (Objects.equals(val, "gray")) {
                            CONFIG.llamaSkin = "gray";
                        } else if (Objects.equals(val, "white")) {
                            CONFIG.llamaSkin = "white";
                        }
                    } else if (CONFIG.activePet.equals("nautilus")) {
                        if (Objects.equals(val, "nautilus")) {
                            CONFIG.nautilusSkin = "nautilus";
                        } else if (Objects.equals(val, "zombie")) {
                            CONFIG.nautilusSkin = "zombie";
                        } else if (Objects.equals(val, "coral_zombie")) {
                            CONFIG.nautilusSkin = "coral_zombie";
                        }
                    } else if (CONFIG.activePet.equals("panda")) {
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
                    } else if (CONFIG.activePet.equals("piglin")) {
                        if (Objects.equals(val, "piglin")) {
                            CONFIG.piglinSkin = "piglin";
                        } else if (Objects.equals(val, "zombified_piglin")) {
                            CONFIG.piglinSkin = "zombified_piglin";
                        } else if (Objects.equals(val, "piglin_brute")) {
                            CONFIG.piglinSkin = "piglin_brute";
                        }
                    } else if (CONFIG.activePet.equals("wolf")) {
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
                    } else if (CONFIG.activePet.equals("hoglin")) {
                        if (Objects.equals(val, "hoglin")) {
                            CONFIG.hoglinSkin = "hoglin";
                        } else if (Objects.equals(val, "zoglin")) {
                            CONFIG.hoglinSkin = "zoglin";
                        }
                    } else if (CONFIG.activePet.equals("magma_cube")) {
                        if (Objects.equals(val, "small")) {
                            CONFIG.magmaCubeSkin = "small";
                        } else if (Objects.equals(val, "medium")) {
                            CONFIG.magmaCubeSkin = "medium";
                        } else if (Objects.equals(val, "large")) {
                            CONFIG.magmaCubeSkin = "large";
                        }
                    } else if (CONFIG.activePet.equals("slime") || CONFIG.activePet.equals("tropical_slime")) {
                        if (Objects.equals(val, "small")) {
                            CONFIG.slimeSkin = "small";
                        } else if (Objects.equals(val, "medium")) {
                            CONFIG.slimeSkin = "medium";
                        } else if (Objects.equals(val, "large")) {
                            CONFIG.slimeSkin = "large";
                        }
                    } else if (CONFIG.activePet.equals("zombie_villager")) {
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
                    } else if (CONFIG.activePet.equals("shulker")) {
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
                    } else if (CONFIG.activePet.equals("creeper") || CONFIG.activePet.equals("nerd_creeper") || CONFIG.activePet.equals("smiling_creeper")) {
                        if (Objects.equals(val, "normal")) {
                            CONFIG.creeperSkin = "normal";
                        } else if (Objects.equals(val, "charged")) {
                            CONFIG.creeperSkin = "charged";
                        }
                    } else if (CONFIG.activePet.equals("wither")) {
                        if (Objects.equals(val, "normal")) {
                            CONFIG.witherSkin = "normal";
                        } else if (Objects.equals(val, "invulnerable")) {
                            CONFIG.witherSkin = "invulnerable";
                        }
                    } else if (CONFIG.activePet.equals("dumbo_octopus")) {
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
                });
    }

    private BooleanToggleBuilder createBabyOption(ConfigEntryBuilder builder, PetsConfig CONFIG) {
        return builder.startBooleanToggle("Baby?", CONFIG.isBaby)
                .setSaveConsumer((newVal) -> CONFIG.isBaby = newVal);
    }

    public GuiScreen build() {
        PetsConfig CONFIG = AutoConfig.getConfigHolder(PetsConfig.class).getConfig();
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle("Config")
                .setSavingRunnable(() -> {
                    AutoConfig.getConfigHolder(PetsConfig.class).save();
                })
                .setTransparentBackground(true);
        ConfigCategory general = builder.getOrCreateCategory("Config");
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        general.addEntry(this.createPetOnOption(entryBuilder, CONFIG).build());
        general.addEntry(this.createPetSpeciesOption(entryBuilder, CONFIG).build());
        general.addEntry(this.createPetNameOption(entryBuilder, CONFIG).build());
        general.addEntry(this.createPetSkinOption(entryBuilder, CONFIG).build());
        general.addEntry(this.createBabyOption(entryBuilder, CONFIG).build());

        return builder.build();
    }
}