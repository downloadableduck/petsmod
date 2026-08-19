package com.jeff.pets;

import com.jeff.pets.enums.*;
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
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * A utility class used mainly in {@link Central} and misc rendering classes. Contains various
 * shortcuts and utilities for spawning, despawning, and avoiding {@code NullPointerExceptions},
 * as well as a shortcut to {@link Identifier#fromNamespaceAndPath}.
 *
 * @author downloadableduck
 * @see com.jeff.pets.Central
 * @since 0.8.0 (Minecraft Earth Mob Pack)
 */
public class Utils {

    public static AbstractPet pet;

    /**
     * Used to summon a pet.
     *
     * @param entity     the entity to be summoned.
     * @param entityName the name to set the custom entity to. (Technically not required since
     *                   we use a method to refresh the names in {@link Central}, but still.
     * @return If the {@code entity}, {@code player}, or {@code world} is {@code null}
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
        entity.setCustomName(Component.literal(entityName));
        world.addEntity(entity);
        entity.tame(player);
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
     *                  current name is checked off of. */
    public static void checkName(String activePet, AbstractPet entity, String petName) {
        if (Objects.equals(CONFIG.activePet, activePet) && entity != null && !entity.getPlainTextName().equals(petName)) {
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

    public static ModelLayerLocation createModelLayer(String string) {
        return new ModelLayerLocation(withModNamespace(string), "main");
    }

    public static Block getBlockFromString(String string) {
        try {
            Field[] fields = Blocks.class.getDeclaredFields();

            for (Field field : fields) {
                if (!Block.class.isAssignableFrom(field.getType())) continue;
                if (Objects.equals(string, field.getName())) {
                    return (Block) field.get(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Blocks.AIR;
    }

    public static AbstractPet getPet(String string) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;

        AbstractPet abstractPet = switch (string) {
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
            default -> null;
        };
        pet = abstractPet;
        return pet;
    }

    public static String getActivePetName() {
        return switch (CONFIG.activePet) {
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
            case "poisonous_potato_zombie" ->
                    CONFIG.poisonousPotatoZombieName;
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
            default -> "not_a_skin";
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
        }
        com.jeff.pets.network.NetworkManager.get().broadcastChangePetName(Minecraft.getInstance().player.getStringUUID(), Utils.getActivePetName());
        AutoConfig.getConfigHolder(PetsConfig.class).save();
    }
}
