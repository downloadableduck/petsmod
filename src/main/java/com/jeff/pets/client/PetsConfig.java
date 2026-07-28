package com.jeff.pets.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

/**
 * Stores all the values that are serialized into a JSON config located at {@code ./minecraft/config/petsconfig.json}
 * These values include whether the pet is on, whether the pet is a baby,
 * whether the custom title is enabled, and the names
 * and skins for each pet. For the actual rendering of the config screen, see {@link PetsConfigScreen}.
 *
 * @see Central
 * @see Central#CONFIG
 * @see me.shedaniel.autoconfig.ConfigData
 */
@Config(name = "petsconfig")
public class PetsConfig implements ConfigData {
    public Boolean petOn;
    public Boolean customTitleEnabled;
    public String activePet;
    public String penguinName;
    public String duckName;
    public String racoonName;
    public String racoonSkin;
    public String duckSkin;
    public String sheepName;
    public String catSkin;
    public String catName;
    public String allayName;
    public String armadilloName;
    public String axolotlName;
    public String axolotlSkin;
    public String batName;
    public String camelName;
    public String camelSkin;
    public String chickenName;
    public String chickenSkin;
    public String codName;
    public String copperGolemName;
    public String copperGolemSkin;
    public String cowName;
    public String cowSkin;
    public String donkeyName;
    public String frogName;
    public String frogSkin;
    public String horseName;
    public String horseSkin;
    public String mooshroomName;
    public String mooshroomSkin;
    public String muleName;
    public String parrotName;
    public String parrotSkin;
    public String pigName;
    public String pigSkin;
    public String rabbitName;
    public String rabbitSkin;
    public String salmonName;
    public String sheepSkin;
    public String snifferName;
    public String snowGolemName;
    public String snowGolemSkin;
    public String squidName;
    public String squidSkin;
    public String striderName;
    public String striderSkin;
    public String tadpoleName;
    public String tropicalFishName;
    public String tropicalFishSkin;
    public String turtleName;
    public String villagerName;
    public String villagerSkin;
    public String wanderingTraderName;

    public String beeName;
    public String beeSkin;
    public String caveSpiderName;
    public String dolphinName;
    public String endermanName;
    public String foxName;
    public String foxSkin;
    public String goatName;
    public String ironGolemName;
    public String llamaName;
    public String llamaSkin;
    public String nautilusName;
    public String nautilusSkin;
    public String pandaName;
    public String pandaSkin;
    public String piglinName;
    public String piglinSkin;
    public String polarBearName;
    public String pufferFishName;
    public String spiderName;
    public String wolfName;
    public String wolfSkin;

    public String blazeName;
    public String breezeName;
    public String creakingName;
    public String creeperName;
    public String creeperSkin;
    public String elderGuardianName;
    public String endermiteName;
    public String evokerName;
    public String ghastName;
    public String happyGhastName;
    public String guardianName;
    public String hoglinName;
    public String hoglinSkin;
    public String magmaCubeName;
    public String magmaCubeSkin;
    public String phantomName;
    public String pillagerName;
    public String ravagerName;
    public String shulkerName;
    public String shulkerSkin;
    public String silverfishName;
    public String skeletonName;
    public String boggedName;
    public String parchedName;
    public String strayName;
    public String witherSkeletonName;
    public String slimeName;
    public String slimeSkin;
    public String vexName;
    public String vindicatorName;
    public String wardenName;
    public String witchName;
    public String zombieName;
    public String huskName;
    public String drownedName;
    public String zombieVillagerName;
    public String zombieVillagerSkin;

    public String enderDragonName;
    public String witherName;
    public String witherSkin;

    public String angryGhastName;
    public String batatoName;
    public String diamondChickenName;
    public String loveGolemName;
    public String megaSpudName;
    public String moonCowName;
    public String nerdCreeperName;
    public String pinkWitherName;
    public String plaguewhaleSlabName;
    public String poisonousPotatoZombieName;
    public String potatoHuskName;
    public String rayTracingName;
    public String redstoneBugName;
    public String smilingCreeperName;
    public String toxfinSlabName;
    public String traitorSkin;
    public String traitorName;
    public String dumboOctopusName;
    public String dumboOctopusSkin;
    public String koiName;
    public String stingrayName;

    public String headSkin;
    public String headName;

    public boolean isBaby;
    public String zombiePigmanName;
}
