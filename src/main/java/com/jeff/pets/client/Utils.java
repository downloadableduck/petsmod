package com.jeff.pets.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.network.NetworkManager;
import com.jeff.pets.mob.AbstractPet;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

/**
 * A utility class used mainly in {@link Central} and misc rendering classes. Contains various
 * shortcuts and utilities for spawning, despawning, and avoiding {@code NullPointerExceptions},
 * as well as a shortcut to {@link Identifier#fromNamespaceAndPath}.
 *
 * @author downloadableduck
 * @see com.jeff.pets.client.Central
 * @since 0.8.0 (Minecraft Earth Mob Pack)
 */
public class Utils {
    /**
     * Used to summon a pet.
     *
     * @param entity     the entity to be summoned.
     * @param entityName the name to set the custom entity to. (Technically not required since
     *                   we use a method to refresh the names in {@link Central}, but still).
     *                   returns if the {@code entity}, {@code player}, or {@code world} is {@code null}
     */

    public static void summonPet(AbstractPet entity, String entityName) {
        summonPet(entity, entityName, Minecraft.getInstance().player);
    }

    public static void summonPet(AbstractPet entity, String entityName, Player player) {

        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;

        if (entity == null || world == null || player == null) return;

        Vec3 lookAngle = player.getLookAngle();

        double x = player.getX() - lookAngle.x * (double) 0.5F;
        double y = player.getY() + (double) 0.5F;
        double z = player.getZ() - lookAngle.z * (double) 0.5F;

        entity.setPos(x, y, z);
        entity.setCustomName(Component.nullToEmpty(entityName));
        world.addEntity(entity);
        entity.tame(player);
        Central.summonedEntity.add(entity);
    }

    /**
     * The method used in
     * {@link Central#refreshPetNames()}. Checks whether the entities' name is equal to the name
     * in the config, and assigns it the correct name if not. Additionally, this method provides a layer of safety that ensures that Minecraft will not
     * throw a {@code NullPointerException} if {@code entity} is {@code null}.
     *
     * @param activePet The {@code activePet} value that matches {@code entity}
     * @param entity    The pet of which the name is checked.
     * @param petName   The {@code CONFIG.x} name that matches {@code entity} that the entities'
     *                  current name is checked off of.
     */
    public static void checkName(String activePet, AbstractPet entity, String petName) {
        if (Objects.equals(CONFIG.activePet, activePet) && entity != null && !entity.getPlainTextName().equals(petName)) {
            entity.setName(petName);
        }
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
     * Used as a shortcut to {@link Identifier#fromNamespaceAndPath}, and sets the parameter
     * {@code namespace} with {@link PetsInitializer#MOD_ID}.
     *
     * @param path The String that goes in the {@code path} parameter.
     * @return {@link Identifier#fromNamespaceAndPath}, with the parameter {@code namespace} set to
     * {@link PetsInitializer#MOD_ID} and the parameter {@code path} set to the user's input
     */
    public static Identifier withModNamespace(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    /**
     * Despawns the inputted pet if {@code e} is not {@code null}.
     *
     * @param e The entity to despawn
     */
    public static void despawnEntity(Entity e) {
        if (e != null) {
            e.discard();
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

    public static Block getBlockFromString(String string) {
        try {
            Field[] fields = Blocks.class.getDeclaredFields();

            for (Field field : fields) {
                if (!Block.class.isAssignableFrom(field.getType())) continue;
                if (Objects.equals(string.toLowerCase(), field.getName().toLowerCase())) {
                    return (Block) field.get(null);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Blocks.AIR;
    }

    public static AbstractPet getPet(String string) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;

        return switch (string) {
            case "duck" -> new Duck(PetsInitializer.DUCK, world);
            case "racoon" -> new Racoon(PetsInitializer.RACOON, world);
            case "penguin" -> new Penguin(PetsInitializer.PENGUIN, world);
            case "sheep" -> new ClientSheep(PetsInitializer.SHEEP, world);
            case "cat" -> new ClientCat(PetsInitializer.CAT, world);
            case "allay" -> new ClientAllay(PetsInitializer.ALLAY, world);
            case "armadillo" -> new ClientArmadillo(PetsInitializer.ARMADILLO, world);
            case "axolotl" -> new ClientAxolotl(PetsInitializer.AXOLOTL, world);
            case "bat" -> new ClientBat(PetsInitializer.BAT, world);
            case "camel" -> new ClientCamel(PetsInitializer.CAMEL, world);
            case "chicken" -> new ClientChicken(PetsInitializer.CHICKEN, world);
            case "cod" -> new ClientCod(PetsInitializer.COD, world);
            case "copper_golem" -> new ClientCopperGolem(PetsInitializer.COPPER_GOLEM, world);
            case "cow" -> new ClientCow(PetsInitializer.COW, world);
            case "donkey" -> new ClientDonkey(PetsInitializer.DONKEY, world);
            case "frog" -> new ClientFrog(PetsInitializer.FROG, world);
            case "horse" -> new ClientHorse(PetsInitializer.HORSE, world);
            case "mooshroom" -> new ClientMooshroom(PetsInitializer.MOOSHROOM, world);
            case "parrot" -> new ClientParrot(PetsInitializer.PARROT, world);
            case "pig" -> new ClientPig(PetsInitializer.PIG, world);
            case "rabbit" -> new ClientRabbit(PetsInitializer.RABBIT, world);
            case "salmon" -> new ClientSalmon(PetsInitializer.SALMON, world);
            case "sniffer" -> new ClientSniffer(PetsInitializer.SNIFFER, world);
            case "snow_golem" -> new ClientSnowGolem(PetsInitializer.SNOW_GOLEM, world);
            case "squid" -> new ClientSquid(PetsInitializer.SQUID, world);
            case "strider" -> new ClientStrider(PetsInitializer.STRIDER, world);
            case "tadpole" -> new ClientTadpole(PetsInitializer.TADPOLE, world);
            case "turtle" -> new ClientTurtle(PetsInitializer.TURTLE, world);
            case "villager" -> new ClientVillager(PetsInitializer.VILLAGER, world);
            case "wandering_trader" -> new ClientWanderingTrader(PetsInitializer.WANDERING_TRADER, world);
            case "bee" -> new ClientBee(PetsInitializer.BEE, world);
            case "cave_spider" -> new ClientCaveSpider(PetsInitializer.CAVE_SPIDER, world);
            case "dolphin" -> new ClientDolphin(PetsInitializer.DOLPHIN, world);
            case "enderman" -> new ClientEnderman(PetsInitializer.ENDERMAN, world);
            case "fox" -> new ClientFox(PetsInitializer.FOX, world);
            case "goat" -> new ClientGoat(PetsInitializer.GOAT, world);
            case "iron_golem" -> new ClientIronGolem(PetsInitializer.IRON_GOLEM, world);
            case "llama" -> new ClientLlama(PetsInitializer.LLAMA, world);
            case "nautilus" -> new ClientNautilus(PetsInitializer.NAUTILUS, world);
            case "panda" -> new ClientPanda(PetsInitializer.PANDA, world);
            case "piglin" -> new ClientPiglin(PetsInitializer.PIGLIN, world);
            case "polar_bear" -> new ClientPolarBear(PetsInitializer.POLAR_BEAR, world);
            case "pufferfish" -> new ClientPufferFish(PetsInitializer.PUFFERFISH, world);
            case "spider" -> new ClientSpider(PetsInitializer.SPIDER, world);
            case "wolf" -> new ClientWolf(PetsInitializer.WOLF, world);
            case "blaze" -> new ClientBlaze(PetsInitializer.BLAZE, world);
            case "breeze" -> new ClientBreeze(PetsInitializer.BREEZE, world);
            case "creaking" -> new ClientCreaking(PetsInitializer.CREAKING, world);
            case "creeper" -> new ClientCreeper(PetsInitializer.CREEPER, world);
            case "elder_guardian" -> new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE, world);
            case "endermite" -> new ClientEndermite(PetsInitializer.ENDERMITE, world);
            case "evoker" -> new ClientEvoker(PetsInitializer.EVOKER, world);
            case "ghast" -> new ClientGhast(PetsInitializer.GHAST, world);
            case "happy_ghast" -> new ClientHappyGhast(PetsInitializer.HAPPY_GHAST, world);
            case "guardian" -> new ClientGuardian(PetsInitializer.GUARDIAN, world);
            case "hoglin" -> new ClientHoglin(PetsInitializer.HOGLIN, world);
            case "magma_cube" -> new ClientMagmaCube(PetsInitializer.MAGMA_CUBE, world);
            case "phantom" -> new ClientPhantom(PetsInitializer.PHANTOM, world);
            case "pillager" -> new ClientPillager(PetsInitializer.PILLAGER, world);
            case "ravager" -> new ClientRavager(PetsInitializer.RAVAGER, world);
            case "shulker" -> new ClientShulker(PetsInitializer.SHULKER, world);
            case "silverfish" -> new ClientSilverfish(PetsInitializer.SILVERFISH, world);
            case "skeleton" -> new ClientSkeleton(PetsInitializer.SKELETON, world);
            case "slime" -> new ClientSlime(PetsInitializer.SLIME, world);
            case "vex" -> new ClientVex(PetsInitializer.VEX, world);
            case "vindicator" -> new ClientVindicator(PetsInitializer.VINDICATOR, world);
            case "warden" -> new ClientWarden(PetsInitializer.WARDEN, world);
            case "witch" -> new ClientWitch(PetsInitializer.WITCH, world);
            case "zombie" -> new ClientZombie(PetsInitializer.ZOMBIE, world);
            case "zombie_villager" -> new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER, world);
            case "husk" -> new ClientHusk(PetsInitializer.HUSK, world);
            case "drowned" -> new ClientDrowned(PetsInitializer.DROWNED, world);
            case "bogged" -> new ClientBogged(PetsInitializer.BOGGED, world);
            case "parched" -> new ClientParched(PetsInitializer.PARCHED, world);
            case "stray" -> new ClientStray(PetsInitializer.STRAY, world);
            case "wither_skeleton" -> new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON, world);
            case "ender_dragon" -> new ClientEnderDragon(PetsInitializer.ENDER_DRAGON, world);
            case "wither" -> new ClientWither(PetsInitializer.WITHER, world);
            case "angry_ghast" -> new AngryGhast(PetsInitializer.ANGRY_GHAST, world);
            case "batato" -> new Batato(PetsInitializer.BATATO, world);
            case "diamond_chicken" -> new DiamondChicken(PetsInitializer.DIAMOND_CHICKEN, world);
            case "love_golem" -> new LoveGolem(PetsInitializer.LOVE_GOLEM, world);
            case "mega_spud" -> new MegaSpud(PetsInitializer.MEGA_SPUD, world);
            case "moon_cow" -> new MoonCow(PetsInitializer.MOON_COW, world);
            case "nerd_creeper" -> new NerdCreeper(PetsInitializer.NERD_CREEPER, world);
            case "pink_wither" -> new PinkWither(PetsInitializer.PINK_WITHER, world);
            case "plaguewhale_slab" -> new PlaguewhaleSlab(PetsInitializer.PLAGUEWHALE_SLAB, world);
            case "poisonous_potato_zombie" -> new PoisonousPotatoZombie(PetsInitializer.POISONOUS_POTATO_ZOMBIE, world);
            case "ray_tracing" -> new RayTracing(PetsInitializer.RAY_TRACING, world);
            case "redstone_bug" -> new RedstoneBug(PetsInitializer.REDSTONE_BUG, world);
            case "smiling_creeper" -> new SmilingCreeper(PetsInitializer.SMILING_CREEPER, world);
            case "toxifin_slab" -> new ToxifinSlab(PetsInitializer.TOXIFIN_SLAB, world);
            case "potato_husk" -> new PotatoHusk(PetsInitializer.POTATO_HUSK, world);
            case "head" -> new Head(PetsInitializer.HEAD, world);
            case "traitor" -> new Traitor(PetsInitializer.TRAITOR, world);
            case "dumbo_octopus" -> new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS, world);
            case "koi" -> new Koi(PetsInitializer.KOI, world);
            case "stingray" -> new Stingray(PetsInitializer.STINGRAY, world);
            case "sulfur_cube" -> new ClientSulfurCube(PetsInitializer.SULFUR_CUBE, world);
            case "frostbite" -> new ClientFrostbite(PetsInitializer.FROSTBITE, world);
            default -> null;
        };
    }

    public static String getActivePetName() {
        return switch (CONFIG.activePet) {
            case "penguin" -> CONFIG.penguinName;
            case "racoon" -> CONFIG.racoonName;
            case "duck" -> CONFIG.duckName;
            case "cat" -> CONFIG.catName;
            case "sheep" -> CONFIG.sheepName;
            case "allay" -> CONFIG.allayName;
            case "axolotl" -> CONFIG.axolotlName;
            case "armadillo" -> CONFIG.armadilloName;
            case "bat" -> CONFIG.batName;
            case "camel" -> CONFIG.camelName;
            case "chicken" -> CONFIG.chickenName;
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
            case "traitor" -> CONFIG.traitorName;
            case "sulfur_cube" -> CONFIG.sulfurCubeName;
            case "frostbite" -> CONFIG.frostbiteName;
            default -> "";
        };
    }

    public static void setActivePetName(String name) {
        switch (CONFIG.activePet) {
            case "penguin" -> CONFIG.penguinName = name;
            case "duck" -> CONFIG.duckName = name;
            case "racoon" -> CONFIG.racoonName = name;
            case "cat" -> CONFIG.catName = name;
            case "sheep" -> CONFIG.sheepName = name;
            case "allay" -> CONFIG.allayName = name;
            case "axolotl" -> CONFIG.axolotlName = name;
            case "armadillo" -> CONFIG.armadilloName = name;
            case "bat" -> CONFIG.batName = name;
            case "camel" -> CONFIG.camelName = name;
            case "chicken" -> CONFIG.chickenName = name;
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
            case "traitor" -> CONFIG.traitorName = name;
            case "dumbo_octopus" -> CONFIG.dumboOctopusName = name;
            case "koi" -> CONFIG.koiName = name;
            case "stingray" -> CONFIG.stingrayName = name;
            case "sulfur_cube" -> CONFIG.sulfurCubeName = name;
            case "frostbite" -> CONFIG.frostbiteName = name;
        }
        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastChangePetName(Minecraft.getInstance().player.getStringUUID(), Utils.getActivePetName());
        }
        Central.saveConfig();
    }

    public static String getActivePetSkin() {

        return switch (CONFIG.activePet) {
            case "duck" -> CONFIG.duckSkin;
            case "cat" -> CONFIG.catSkin;
            case "racoon" -> CONFIG.racoonSkin;
            case "sheep" -> CONFIG.sheepSkin;
            case "axolotl" -> CONFIG.axolotlSkin;
            case "camel" -> CONFIG.camelSkin;
            case "chicken" -> CONFIG.chickenSkin;
            case "creeper", "nerd_creeper", "smiling_creeper" -> CONFIG.creeperSkin;
            case "copper_golem" -> CONFIG.copperGolemSkin;
            case "cow" -> CONFIG.cowSkin;
            case "frog" -> CONFIG.frogSkin;
            case "horse" -> CONFIG.horseSkin;
            case "parrot" -> CONFIG.parrotSkin;
            case "pig" -> CONFIG.pigSkin;
            case "rabbit" -> CONFIG.rabbitSkin;
            case "snow_golem" -> CONFIG.snowGolemSkin;
            case "squid" -> CONFIG.squidSkin;
            case "strider" -> CONFIG.striderSkin;
            case "tropical_fish" -> CONFIG.tropicalFishSkin;
            case "villager" -> CONFIG.villagerSkin;
            case "mooshroom" -> CONFIG.mooshroomSkin;
            case "bee" -> CONFIG.beeSkin;
            case "fox" -> CONFIG.foxSkin;
            case "llama" -> CONFIG.llamaSkin;
            case "nautilus" -> CONFIG.nautilusSkin;
            case "panda" -> CONFIG.pandaSkin;
            case "piglin" -> CONFIG.piglinSkin;
            case "wolf" -> CONFIG.wolfSkin;
            case "hoglin" -> CONFIG.hoglinSkin;
            case "magma_cube" -> CONFIG.magmaCubeSkin;
            case "slime", "tropical_slime" -> CONFIG.slimeSkin;
            case "zombie_villager" -> CONFIG.zombieVillagerSkin;
            case "wither" -> CONFIG.witherSkin;
            case "dumbo_octopus" -> CONFIG.dumboOctopusSkin;
            case "traitor" -> CONFIG.traitorSkin;
            case "sulfur_cube" -> CONFIG.sulfurCubeSkin;
            default -> "not_a_skin";
        };
    }

    public static boolean setActivePetSkin(String skin) {
        boolean isValid = true;
        if (Objects.equals(skin, "baby")) {
            CONFIG.isBaby = true;
            NetworkManager.get().broadcastToggleBaby(Objects.requireNonNull(Minecraft.getInstance().player).getStringUUID(), CONFIG.isBaby);
        } else if (Objects.equals(skin, "adult")) {
            CONFIG.isBaby = false;
            NetworkManager.get().broadcastToggleBaby(Objects.requireNonNull(Minecraft.getInstance().player).getStringUUID(), CONFIG.isBaby);
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
                    case "bronze":
                        CONFIG.duckSkin = "bronze";
                        break;
                    case "silver":
                        CONFIG.duckSkin = "silver";
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
                CONFIG.sulfurCubeSkin = skin;
            }
        }
        return isValid;
    }

    public static List<String> getAllBlocks() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Field[] fields = Blocks.class.getDeclaredFields();

            for (Field field : fields) {
                if (!Block.class.isAssignableFrom(field.getType())) continue;
                list.add(field.getName().replace("_", " ").toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static float clamp(float a, float b, float c) {
        return a < b ? b : Math.min(a, c);
    }
}
