package com.jeff.pets.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.network.NetworkManager;
import com.jeff.pets.mob.AbstractPet;
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
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.Central.MOD_ID;

/**
 * A utility class used mainly in {@link Central} and misc rendering classes. Contains various
 * shortcuts and utilities for spawning, despawning, and avoiding {@code NullPointerExceptions},
 * as well as a shortcut to {@link ResourceLocation#fromNamespaceAndPath}.
 *
 * @author downloadableduck
 * @see Central
 * @since 0.8.0 (Minecraft Earth Mob Pack)
 */
public class Utils {

    /**
     * Used to summon a pet.
     *
     * @param entity     the entity to be summoned.
     * @param entityName the name to set the custom entity to. (Technically not required since
     *                   we use a method to refresh the names in {@link Central}, but still.
     * @return If the {@code entity}, {@code player}, or {@code world} is {@code null}
     */

    public static void summonPet(AbstractPet entity, String enttiyName) {
        summonPet(entity, enttiyName, Minecraft.getInstance().player);
    }

    public static void summonPet(AbstractPet entity, String entityName, EntityPlayer player) {

        Minecraft minecraft = Minecraft.getInstance();
        WorldClient world = minecraft.world;

        if (entity == null || world == null || player == null) return;

        Vec3 lookAngle = player.getLook(1.0f);

        double x = player.posX - lookAngle.x * (double) 0.5F;
        double y = player.posY + (double) 0.5F;
        double z = player.posZ - lookAngle.z * (double) 0.5F;

        entity.setPosition(x, y, z);
        entity.setName(entityName);
        world.addEntityToWorld(entity.getEntityId(), entity);
        entity.func_152115_b(EntityPlayer.getUUID(player.getGameProfile()).toString());
        Central.summonedEntity.add(entity);
    }

    /**
     * The method used in
     * {@link Central#refreshPetNames()}. Checks whether the entities' name is equal to the name
     * in the config, and assigns it the correct name if not.. Additionally, this method provides a layer of safety that ensures that Minecraft will not
     * throw a {@code NullPointerException} if {@code entity} is {@code null}.
     *
     * @param activePet The {@code activePet} value that matches {@code entity}
     * @param entity    The pet of which the name is checked.
     * @param petName   The {@code CONFIG.x} name that matches {@code entity} that the entities'
     *                  current name is checked off of.
     */
    public static void checkName(String activePet, AbstractPet entity, String petName) {
        if (Objects.equals(CONFIG.activePet, activePet) && entity != null && !entity.func_95999_t().equals(petName)) {
            entity.setName(petName);
        }
    }

    /**
     * Used to teleport a pet if the pet is valid and not {@code null}.
     *
     * @param activePet The {@code String} that represents a possible active {@code pet}.
     * @param entity    The entity to teleport if {@code activePet} matches the currently active pet.
     * @return True if the {@code activePet} inputted is the current active pet, and the
     * {@code entity} is not {@code null}.
     */
    public static boolean checkTeleport(String activePet, AbstractPet entity) {
        return Objects.equals(CONFIG.activePet, activePet) && entity != null;
    }

    /**
     * Checks if the string is {@code null} and sets it to an empty - but not {@code null} String - if it is.
     *
     * @param s The String to check if it is {@code null}.
     * @return If the string is {@code null}, the new empty String is returned. If the string is not {@code null},
     * simply returns {@code s}.
     */
    public static String checkNullString(String s) {
        return s == null ? "" : s;
    }

    /**
     * Similar to the checkNullString method above, but takes a default value instead of
     * setting the String to an empty String. Used to set the {@code CONFIG.xSkin} values
     * to a default value and avoid {@code NullPointerExceptions}.
     *
     * @param s           The string to check if it {@code null}.
     * @param defaultSkin The default value to set {@code s} to if {@code s} is {@code null}.
     * @return If the string is {@code null}, {@code defaultSkin} is returned. If the string is not {@code null},
     * simply returns {@code s}.
     */
    public static String checkNullString(String s, String defaultSkin) {
        return s == null ? defaultSkin : s;
    }

    /**
     * Used as a shortcut to {@link ResourceLocation#fromNamespaceAndPath}, and sets the parameter
     * {@code namespace} with {@link Central#MOD_ID}.
     *
     * @param path The String that goes in the {@code path} parameter.
     * @return {@link ResourceLocation#fromNamespaceAndPath}, with the parameter {@code namespace} set to
     * {@link Central#MOD_ID} and the parameter {@code path} set to the user's input
     */
    public static ResourceLocation withModNamespace(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    /**
     * Despawns the inputted pet if {@code e} is not {@code null}.
     *
     * @param e The entity to despawn
     */
    public static void despawnEntity(Entity e) {
        if (e != null) {
            e.remove();
        }
    }

    /**
     * Sets the active pet in {@link PetsConfig#activePet} as well as in {@link Central#summonedEntity}
     *
     * @param e The entity to be summoned and added to {@link Central#summonedEntity}
     * @param s The value to set {@link PetsConfig#activePet} to that matches {@code e}
     */
    public static void setActivePet(Entity e, String s) {
        Central.summonedEntity.clear();
        Central.summonedEntity.add(e);
        CONFIG.activePet = s;
    }

    public static float triangleWave(float p_78172_1_, float p_78172_2_) {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }

    public static AbstractPet getPet(String string) {
        Minecraft minecraft = Minecraft.getInstance();
        WorldClient world = minecraft.world;

        switch (string) {
            case "duck":
                return new Duck(world);
            case "racoon":
                return new Racoon(world);
            case "penguin":
                return new Penguin(world);
            case "sheep":
                return new ClientSheep(world);
            case "cat":
                return new ClientCat(world);
            case "bat":
                return new ClientBat(world);
            case "chicken":
                return new ClientChicken(world);
            case "cow":
                return new ClientCow(world);
            case "donkey":
                return new ClientDonkey(world);
            case "horse":
                return new ClientHorse(world);
            case "mooshroom":
                return new ClientMooshroom(world);
            case "pig":
                return new ClientPig(world);
            case "rabbit":
                return new ClientRabbit(world);
            case "snow_golem":
                return new ClientSnowGolem(world);
            case "squid":
                return new ClientSquid(world);
            case "villager":
                return new ClientVillager(world);
            case "cave_spider":
                return new ClientCaveSpider(world);
            case "enderman":
                return new ClientEnderman(world);
            case "iron_golem":
                return new ClientIronGolem(world);
            case "spider":
                return new ClientSpider(world);
            case "wolf":
                return new ClientWolf(world);
            case "blaze":
                return new ClientBlaze(world);
            case "creeper":
                return new ClientCreeper(world);
            case "elder_guardian":
                return new ClientElderGuardian(world);
            case "endermite":
                return new ClientEndermite(world);
            case "ghast":
                return new ClientGhast(world);
            case "guardian":
                return new ClientGuardian(world);
            case "magma_cube":
                return new ClientMagmaCube(world);
            case "silverfish":
                return new ClientSilverfish(world);
            case "skeleton":
                return new ClientSkeleton(world);
            case "slime":
                return new ClientSlime(world);
            case "witch":
                return new ClientWitch(world);
            case "zombie":
                return new ClientZombie(world);
            case "zombie_villager":
                return new ClientZombieVillager(world);
            case "wither_skeleton":
                return new ClientWitherSkeleton(world);
            case "ender_dragon":
                return new ClientEnderDragon(world);
            case "wither":
                return new ClientWither(world);
            case "head":
                return new Head(world);
            case "dumbo_octopus":
                return new DumboOctopus(world);
            case "koi":
                return new Koi(world);
            case "stingray":
                return new Stingray(world);
            default:
                return null;
        }
    }

    public static String getActivePetName() {
        switch (CONFIG.activePet) {
            case "penguin":
                return CONFIG.penguinName;
            case "racoon":
                return CONFIG.racoonName;
            case "duck":
                return CONFIG.duckName;
            case "cat":
                return CONFIG.catName;
            case "sheep":
                return CONFIG.sheepName;
            case "allay":
                return CONFIG.allayName;
            case "axolotl":
                return CONFIG.axolotlName;
            case "armadillo":
                return CONFIG.armadilloName;
            case "bat":
                return CONFIG.batName;
            case "camel":
                return CONFIG.camelName;
            case "chicken":
                return CONFIG.chickenName;
            case "cod":
                return CONFIG.codName;
            case "copper_golem":
                return CONFIG.copperGolemName;
            case "cow":
                return CONFIG.cowName;
            case "donkey":
                return CONFIG.donkeyName;
            case "frog":
                return CONFIG.frogName;
            case "horse":
                return CONFIG.horseName;
            case "mooshroom":
                return CONFIG.mooshroomName;
            case "mule":
                return CONFIG.muleName;
            case "parrot":
                return CONFIG.parrotName;
            case "pig":
                return CONFIG.pigName;
            case "rabbit":
                return CONFIG.rabbitName;
            case "salmon":
                return CONFIG.salmonName;
            case "sniffer":
                return CONFIG.snifferName;
            case "snow_golem":
                return CONFIG.snowGolemName;
            case "squid":
                return CONFIG.squidName;
            case "strider":
                return CONFIG.striderName;
            case "tadpole":
                return CONFIG.tadpoleName;
            case "tropical_fish":
                return CONFIG.tropicalFishName;
            case "turtle":
                return CONFIG.turtleName;
            case "villager":
                return CONFIG.villagerName;
            case "wandering_trader":
                return CONFIG.wanderingTraderName;
            case "bee":
                return CONFIG.beeName;
            case "cave_spider":
                return CONFIG.caveSpiderName;
            case "dolphin":
                return CONFIG.dolphinName;
            case "enderman":
                return CONFIG.endermanName;
            case "fox":
                return CONFIG.foxName;
            case "goat":
                return CONFIG.goatName;
            case "iron_golem":
                return CONFIG.ironGolemName;
            case "llama":
                return CONFIG.llamaName;
            case "nautilus":
                return CONFIG.nautilusName;
            case "panda":
                return CONFIG.pandaName;
            case "piglin":
                return CONFIG.piglinName;
            case "polar_bear":
                return CONFIG.polarBearName;
            case "pufferfish":
                return CONFIG.pufferFishName;
            case "spider":
                return CONFIG.spiderName;
            case "wolf":
                return CONFIG.wolfName;
            case "blaze":
                return CONFIG.blazeName;
            case "breeze":
                return CONFIG.breezeName;
            case "creaking":
                return CONFIG.creakingName;
            case "creeper":
                return CONFIG.creeperName;
            case "elder_guardian":
                return CONFIG.elderGuardianName;
            case "endermite":
                return CONFIG.endermiteName;
            case "evoker":
                return CONFIG.evokerName;
            case "ghast":
                return CONFIG.ghastName;
            case "guardian":
                return CONFIG.guardianName;
            case "hoglin":
                return CONFIG.hoglinName;
            case "magma_cube":
                return CONFIG.magmaCubeName;
            case "phantom":
                return CONFIG.phantomName;
            case "pillager":
                return CONFIG.pillagerName;
            case "ravager":
                return CONFIG.ravagerName;
            case "shulker":
                return CONFIG.shulkerName;
            case "silverfish":
                return CONFIG.silverfishName;
            case "skeleton":
                return CONFIG.skeletonName;
            case "slime":
                return CONFIG.slimeName;
            case "vex":
                return CONFIG.vexName;
            case "vindicator":
                return CONFIG.vindicatorName;
            case "warden":
                return CONFIG.wardenName;
            case "witch":
                return CONFIG.witchName;
            case "zombie":
                return CONFIG.zombieName;
            case "zombie_villager":
                return CONFIG.zombieVillagerName;
            case "angry_ghast":
                return CONFIG.angryGhastName;
            case "batato":
                return CONFIG.batatoName;
            case "diamond_chicken":
                return CONFIG.diamondChickenName;
            case "love_golem":
                return CONFIG.loveGolemName;
            case "mega_spud":
                return CONFIG.megaSpudName;
            case "moon_cow":
                return CONFIG.moonCowName;
            case "nerd_creeper":
                return CONFIG.nerdCreeperName;
            case "pink_wither":
                return CONFIG.pinkWitherName;
            case "plaguewhale_slab":
                return CONFIG.plaguewhaleSlabName;
            case "poisonous_potato_zombie":
                return CONFIG.poisonousPotatoZombieName;
            case "ray_tracing":
                return CONFIG.rayTracingName;
            case "redstone_bug":
                return CONFIG.redstoneBugName;
            case "smiling_creeper":
                return CONFIG.smilingCreeperName;
            case "toxifin_slab":
                return CONFIG.toxfinSlabName;
            case "potato_husk":
                return CONFIG.potatoHuskName;
            case "head":
                return CONFIG.headName;
            case "dumbo_octopus":
                return CONFIG.dumboOctopusName;
            case "koi":
                return CONFIG.koiName;
            case "stingray":
                return CONFIG.stingrayName;
            case "traitor":
                return CONFIG.traitorName;
            default:
                return "";
        }
    }

    public static void setActivePetName(String name) {
        switch (CONFIG.activePet) {
            case "penguin":
                CONFIG.penguinName = name;
                break;
            case "duck":
                CONFIG.duckName = name;
                break;
            case "racoon":
                CONFIG.racoonName = name;
                break;
            case "cat":
                CONFIG.catName = name;
                break;
            case "sheep":
                CONFIG.sheepName = name;
                break;
            case "allay":
                CONFIG.allayName = name;
                break;
            case "axolotl":
                CONFIG.axolotlName = name;
                break;
            case "armadillo":
                CONFIG.armadilloName = name;
                break;
            case "bat":
                CONFIG.batName = name;
                break;
            case "camel":
                CONFIG.camelName = name;
                break;
            case "chicken":
                CONFIG.chickenName = name;
                break;
            case "cod":
                CONFIG.codName = name;
                break;
            case "copper_golem":
                CONFIG.copperGolemName = name;
                break;
            case "cow":
                CONFIG.cowName = name;
                break;
            case "donkey":
                CONFIG.donkeyName = name;
                break;
            case "frog":
                CONFIG.frogName = name;
                break;
            case "horse":
                CONFIG.horseName = name;
                break;
            case "mooshroom":
                CONFIG.mooshroomName = name;
                break;
            case "mule":
                CONFIG.muleName = name;
                break;
            case "parrot":
                CONFIG.parrotName = name;
                break;
            case "pig":
                CONFIG.pigName = name;
                break;
            case "rabbit":
                CONFIG.rabbitName = name;
                break;
            case "salmon":
                CONFIG.salmonName = name;
                break;
            case "sniffer":
                CONFIG.snifferName = name;
                break;
            case "snow_golem":
                CONFIG.snowGolemName = name;
                break;
            case "squid":
                CONFIG.squidName = name;
                break;
            case "strider":
                CONFIG.striderName = name;
                break;
            case "tadpole":
                CONFIG.tadpoleName = name;
                break;
            case "tropical_fish":
                CONFIG.tropicalFishName = name;
                break;
            case "turtle":
                CONFIG.turtleName = name;
                break;
            case "villager":
                CONFIG.villagerName = name;
                break;
            case "wandering_trader":
                CONFIG.wanderingTraderName = name;
                break;
            case "bee":
                CONFIG.beeName = name;
                break;
            case "cave_spider":
                CONFIG.caveSpiderName = name;
                break;
            case "dolphin":
                CONFIG.dolphinName = name;
                break;
            case "enderman":
                CONFIG.endermanName = name;
                break;
            case "fox":
                CONFIG.foxName = name;
                break;
            case "goat":
                CONFIG.goatName = name;
                break;
            case "iron_golem":
                CONFIG.ironGolemName = name;
                break;
            case "llama":
                CONFIG.llamaName = name;
                break;
            case "nautilus":
                CONFIG.nautilusName = name;
                break;
            case "panda":
                CONFIG.pandaName = name;
                break;
            case "piglin":
                CONFIG.piglinName = name;
                break;
            case "polar_bear":
                CONFIG.polarBearName = name;
                break;
            case "pufferfish":
                CONFIG.pufferFishName = name;
                break;
            case "spider":
                CONFIG.spiderName = name;
                break;
            case "wolf":
                CONFIG.wolfName = name;
                break;
            case "blaze":
                CONFIG.blazeName = name;
                break;
            case "breeze":
                CONFIG.breezeName = name;
                break;
            case "creaking":
                CONFIG.creakingName = name;
                break;
            case "creeper":
                CONFIG.creeperName = name;
                break;
            case "elder_guardian":
                CONFIG.elderGuardianName = name;
                break;
            case "endermite":
                CONFIG.endermiteName = name;
                break;
            case "evoker":
                CONFIG.evokerName = name;
                break;
            case "happy_ghast":
                CONFIG.happyGhastName = name;
                break;
            case "ghast":
                CONFIG.ghastName = name;
                break;
            case "guardian":
                CONFIG.guardianName = name;
                break;
            case "hoglin":
                CONFIG.hoglinName = name;
                break;
            case "magma_cube":
                CONFIG.magmaCubeName = name;
                break;
            case "phantom":
                CONFIG.phantomName = name;
                break;
            case "pillager":
                CONFIG.pillagerName = name;
                break;
            case "ravager":
                CONFIG.ravagerName = name;
                break;
            case "shulker":
                CONFIG.shulkerName = name;
                break;
            case "silverfish":
                CONFIG.silverfishName = name;
                break;
            case "skeleton":
                CONFIG.skeletonName = name;
                break;
            case "slime":
                CONFIG.slimeName = name;
                break;
            case "vex":
                CONFIG.vexName = name;
                break;
            case "vindicator":
                CONFIG.vindicatorName = name;
                break;
            case "warden":
                CONFIG.wardenName = name;
                break;
            case "witch":
                CONFIG.witchName = name;
                break;
            case "zombie":
                CONFIG.zombieName = name;
                break;
            case "zombie_villager":
                CONFIG.zombieVillagerName = name;
                break;
            case "husk":
                CONFIG.huskName = name;
                break;
            case "drowned":
                CONFIG.drownedName = name;
                break;
            case "bogged":
                CONFIG.boggedName = name;
                break;
            case "parched":
                CONFIG.parchedName = name;
                break;
            case "stray":
                CONFIG.strayName = name;
                break;
            case "wither_skeleton":
                CONFIG.witherSkeletonName = name;
                break;
            case "ender_dragon":
                CONFIG.enderDragonName = name;
                break;
            case "wither":
                CONFIG.witherName = name;
                break;
            case "angry_ghast":
                CONFIG.angryGhastName = name;
                break;
            case "batato":
                CONFIG.batatoName = name;
                break;
            case "diamond_chicken":
                CONFIG.diamondChickenName = name;
                break;
            case "love_golem":
                CONFIG.loveGolemName = name;
                break;
            case "mega_spud":
                CONFIG.megaSpudName = name;
                break;
            case "moon_cow":
                CONFIG.moonCowName = name;
                break;
            case "nerd_creeper":
                CONFIG.nerdCreeperName = name;
                break;
            case "pink_wither":
                CONFIG.pinkWitherName = name;
                break;
            case "plaguewhale_slab":
                CONFIG.plaguewhaleSlabName = name;
                break;
            case "poisonous_potato_zombie":
                CONFIG.poisonousPotatoZombieName = name;
                break;
            case "ray_tracing":
                CONFIG.rayTracingName = name;
                break;
            case "redstone_bug":
                CONFIG.redstoneBugName = name;
                break;
            case "smiling_creeper":
                CONFIG.smilingCreeperName = name;
                break;
            case "toxifin_slab":
                CONFIG.toxfinSlabName = name;
                break;
            case "potato_husk":
                CONFIG.potatoHuskName = name;
                break;
            case "head":
                CONFIG.headName = name;
                break;
            case "traitor":
                CONFIG.traitorName = name;
                break;
            case "dumbo_octopus":
                CONFIG.dumboOctopusName = name;
                break;
            case "koi":
                CONFIG.koiName = name;
                break;
            case "stingray":
                CONFIG.stingrayName = name;
                break;
        }
        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastChangePetName(Minecraft.getInstance().player.getGameProfile().getId().toString(), Utils.getActivePetName());
        }
        AutoConfig.getConfigHolder(PetsConfig.class).save();
    }

    public static String getActivePetSkin() {
        switch (CONFIG.activePet) {
            case "duck":
                return CONFIG.duckSkin;
            case "cat":
                return CONFIG.catSkin;
            case "racoon":
                return CONFIG.racoonSkin;
            case "sheep":
                return CONFIG.sheepSkin;
            case "axolotl":
                return CONFIG.axolotlSkin;
            case "camel":
                return CONFIG.camelSkin;
            case "chicken":
                return CONFIG.chickenSkin;
            case "creeper":
            case "nerd_creeper":
            case "smiling_creeper":
                return CONFIG.creeperSkin;
            case "copper_golem":
                return CONFIG.copperGolemSkin;
            case "cow":
                return CONFIG.cowSkin;
            case "frog":
                return CONFIG.frogSkin;
            case "horse":
                return CONFIG.horseSkin;
            case "parrot":
                return CONFIG.parrotSkin;
            case "pig":
                return CONFIG.pigSkin;
            case "rabbit":
                return CONFIG.rabbitSkin;
            case "snow_golem":
                return CONFIG.snowGolemSkin;
            case "squid":
                return CONFIG.squidSkin;
            case "strider":
                return CONFIG.striderSkin;
            case "tropical_fish":
                return CONFIG.tropicalFishSkin;
            case "villager":
                return CONFIG.villagerSkin;
            case "mooshroom":
                return CONFIG.mooshroomSkin;
            case "bee":
                return CONFIG.beeSkin;
            case "fox":
                return CONFIG.foxSkin;
            case "llama":
                return CONFIG.llamaSkin;
            case "nautilus":
                return CONFIG.nautilusSkin;
            case "panda":
                return CONFIG.pandaSkin;
            case "piglin":
                return CONFIG.piglinSkin;
            case "wolf":
                return CONFIG.wolfSkin;
            case "hoglin":
                return CONFIG.hoglinSkin;
            case "magma_cube":
                return CONFIG.magmaCubeSkin;
            case "slime":
            case "tropical_slime":
                return CONFIG.slimeSkin;
            case "zombie_villager":
                return CONFIG.zombieVillagerSkin;
            case "wither":
                return CONFIG.witherSkin;
            case "dumbo_octopus":
                return CONFIG.dumboOctopusSkin;
            case "traitor":
                return CONFIG.traitorSkin;
            default:
                return "not_a_skin";
        }
    }

    public static void setActivePetSkin(String val) {
        switch (CONFIG.activePet) {
            case "duck":
                if (Objects.equals(val, "mallard")) {
                    CONFIG.duckSkin = "mallard";
                } else if (Objects.equals(val, "pekin")) {
                    CONFIG.duckSkin = "pekin";
                } else if (Objects.equals(val, "rubber")) {
                    CONFIG.duckSkin = "rubber";
                } else if (Objects.equals(val, "bronze")) {
                    CONFIG.duckSkin = "bronze";
                }
                break;

            case "racoon":
                if (Objects.equals(val, "normal")) {
                    CONFIG.racoonSkin = "normal";
                } else if (Objects.equals(val, "albino")) {
                    CONFIG.racoonSkin = "albino";
                }
                break;

            case "sheep":
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
                break;

            case "cat":
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
                break;

            case "chicken":
                if (Objects.equals(val, "temperate")) {
                    CONFIG.chickenSkin = "temperate";
                } else if (Objects.equals(val, "cold")) {
                    CONFIG.chickenSkin = "cold";
                } else if (Objects.equals(val, "warm")) {
                    CONFIG.chickenSkin = "warm";
                }
                break;

            case "axolotl":
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
                break;

            case "camel":
                if (Objects.equals(val, "camel")) {
                    CONFIG.camelSkin = "camel";
                } else if (Objects.equals(val, "husk")) {
                    CONFIG.camelSkin = "husk";
                }
                break;

            case "copper_golem":
                if (Objects.equals(val, "unoxidized")) {
                    CONFIG.copperGolemSkin = "unoxidized";
                } else if (Objects.equals(val, "exposed")) {
                    CONFIG.copperGolemSkin = "exposed";
                } else if (Objects.equals(val, "weathered")) {
                    CONFIG.copperGolemSkin = "weathered";
                } else if (Objects.equals(val, "oxidized")) {
                    CONFIG.copperGolemSkin = "oxidized";
                }
                break;

            case "cow":
                if (Objects.equals(val, "temperate")) {
                    CONFIG.cowSkin = "temperate";
                } else if (Objects.equals(val, "cold")) {
                    CONFIG.cowSkin = "cold";
                } else if (Objects.equals(val, "warm")) {
                    CONFIG.cowSkin = "warm";
                }
                break;

            case "frog":
                if (Objects.equals(val, "temperate")) {
                    CONFIG.frogSkin = "temperate";
                } else if (Objects.equals(val, "cold")) {
                    CONFIG.frogSkin = "cold";
                } else if (Objects.equals(val, "warm")) {
                    CONFIG.frogSkin = "warm";
                }
                break;

            case "horse":
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
                break;

            case "parrot":
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
                break;

            case "pig":
                if (Objects.equals(val, "temperate")) {
                    CONFIG.pigSkin = "temperate";
                } else if (Objects.equals(val, "warm")) {
                    CONFIG.pigSkin = "warm";
                } else if (Objects.equals(val, "cold")) {
                    CONFIG.pigSkin = "cold";
                }
                break;

            case "rabbit":
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
                break;

            case "snow_golem":
                if (Objects.equals(val, "pumpkin_on")) {
                    CONFIG.snowGolemSkin = "pumpkin_on";
                } else if (Objects.equals(val, "pumpkin_off")) {
                    CONFIG.snowGolemSkin = "pumpkin_off";
                }
                break;

            case "squid":
                if (Objects.equals(val, "squid")) {
                    CONFIG.squidSkin = "squid";
                } else if (Objects.equals(val, "glow_squid")) {
                    CONFIG.squidSkin = "glow_squid";
                }
                break;

            case "tropical_fish":
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
                break;

            case "villager":
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
                break;

            case "mooshroom":
                if (Objects.equals(val, "red")) {
                    CONFIG.mooshroomSkin = "red";
                } else if (Objects.equals(val, "brown")) {
                    CONFIG.mooshroomSkin = "brown";
                }
                break;

            case "strider":
                if (Objects.equals(val, "warm")) {
                    CONFIG.striderSkin = "warm";
                } else if (Objects.equals(val, "cold")) {
                    CONFIG.striderSkin = "cold";
                }
                break;

            case "bee":
                if (Objects.equals(val, "happy")) {
                    CONFIG.beeSkin = "happy";
                } else if (Objects.equals(val, "angry")) {
                    CONFIG.beeSkin = "angry";
                }
                break;

            case "fox":
                if (Objects.equals(val, "red")) {
                    CONFIG.foxSkin = "red";
                } else if (Objects.equals(val, "snow")) {
                    CONFIG.foxSkin = "snow";
                }
                break;

            case "llama":
                if (Objects.equals(val, "brown")) {
                    CONFIG.llamaSkin = "brown";
                } else if (Objects.equals(val, "creamy")) {
                    CONFIG.llamaSkin = "creamy";
                } else if (Objects.equals(val, "gray")) {
                    CONFIG.llamaSkin = "gray";
                } else if (Objects.equals(val, "white")) {
                    CONFIG.llamaSkin = "white";
                }
                break;

            case "nautilus":
                if (Objects.equals(val, "nautilus")) {
                    CONFIG.nautilusSkin = "nautilus";
                } else if (Objects.equals(val, "zombie")) {
                    CONFIG.nautilusSkin = "zombie";
                } else if (Objects.equals(val, "coral_zombie")) {
                    CONFIG.nautilusSkin = "coral_zombie";
                }
                break;

            case "panda":
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
                break;

            case "piglin":
                if (Objects.equals(val, "piglin")) {
                    CONFIG.piglinSkin = "piglin";
                } else if (Objects.equals(val, "zombified_piglin")) {
                    CONFIG.piglinSkin = "zombified_piglin";
                } else if (Objects.equals(val, "piglin_brute")) {
                    CONFIG.piglinSkin = "piglin_brute";
                }
                break;

            case "wolf":
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
                break;

            case "hoglin":
                if (Objects.equals(val, "hoglin")) {
                    CONFIG.hoglinSkin = "hoglin";
                } else if (Objects.equals(val, "zoglin")) {
                    CONFIG.hoglinSkin = "zoglin";
                }
                break;

            case "magma_cube":
                if (Objects.equals(val, "small")) {
                    CONFIG.magmaCubeSkin = "small";
                } else if (Objects.equals(val, "medium")) {
                    CONFIG.magmaCubeSkin = "medium";
                } else if (Objects.equals(val, "large")) {
                    CONFIG.magmaCubeSkin = "large";
                }
                break;

            case "slime":
            case "tropical_slime":
                if (Objects.equals(val, "small")) {
                    CONFIG.slimeSkin = "small";
                } else if (Objects.equals(val, "medium")) {
                    CONFIG.slimeSkin = "medium";
                } else if (Objects.equals(val, "large")) {
                    CONFIG.slimeSkin = "large";
                }
                break;

            case "zombie_villager":
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
                break;

            case "shulker":
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
                break;

            case "creeper":
            case "nerd_creeper":
            case "smiling_creeper":
                if (Objects.equals(val, "normal")) {
                    CONFIG.creeperSkin = "normal";
                } else if (Objects.equals(val, "charged")) {
                    CONFIG.creeperSkin = "charged";
                }
                break;

            case "wither":
                if (Objects.equals(val, "normal")) {
                    CONFIG.witherSkin = "normal";
                } else if (Objects.equals(val, "invulnerable")) {
                    CONFIG.witherSkin = "invulnerable";
                }
                break;

            case "dumbo_octopus":
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
                break;

            case "traitor":
                if (Objects.equals(val, "plains")) {
                    CONFIG.traitorSkin = "plains";
                } else if (Objects.equals(val, "desert")) {
                    CONFIG.traitorSkin = "desert";
                } else if (Objects.equals(val, "savanna")) {
                    CONFIG.traitorSkin = "savanna";
                } else if (Objects.equals(val, "taiga")) {
                    CONFIG.traitorSkin = "taiga";
                } else if (Objects.equals(val, "snowy")) {
                    CONFIG.traitorSkin = "snowy";
                } else if (Objects.equals(val, "jungle")) {
                    CONFIG.traitorSkin = "jungle";
                } else if (Objects.equals(val, "swamp")) {
                    CONFIG.traitorSkin = "swamp";
                }
                break;
        }

        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastChangePetSkin(Minecraft.getInstance().player.getGameProfile().getId().toString(), getActivePetSkin());
        }
    }
}