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
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
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

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * A utility class used mainly in {@link Central} and misc rendering classes. Contains various
 * shortcuts and utilities for spawning, despawning, and avoiding {@code NullPointerExceptions},
 * as well as a shortcut to {@link Identifier#fromNamespaceAndPath}.
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

    public static void summonPet(AbstractPet entity, String entityName) {
        summonPet(entity, entityName, Minecraft.getInstance().player);
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

    public static AbstractPet getPet(String string) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;

        return switch (string) {
            case "duck" -> new Duck(PetsInitializer.DUCK.get(), world);
            case "racoon" -> new Racoon(PetsInitializer.RACOON.get(), world);
            case "penguin" -> new Penguin(PetsInitializer.PENGUIN.get(), world);
            case "sheep" -> new ClientSheep(PetsInitializer.SHEEP.get(), world);
            case "cat" -> new ClientCat(PetsInitializer.CAT.get(), world);
            case "allay" -> new ClientAllay(PetsInitializer.ALLAY.get(), world);
            case "armadillo" -> new ClientArmadillo(PetsInitializer.ARMADILLO.get(), world);
            case "axolotl" -> new ClientAxolotl(PetsInitializer.AXOLOTL.get(), world);
            case "bat" -> new ClientBat(PetsInitializer.BAT.get(), world);
            case "camel" -> new ClientCamel(PetsInitializer.CAMEL.get(), world);
            case "chicken" -> new ClientChicken(PetsInitializer.CHICKEN.get(), world);
            case "cod" -> new ClientCod(PetsInitializer.COD.get(), world);
            case "copper_golem" -> new ClientCopperGolem(PetsInitializer.COPPER_GOLEM.get(), world);
            case "cow" -> new ClientCow(PetsInitializer.COW.get(), world);
            case "donkey" -> new ClientDonkey(PetsInitializer.DONKEY.get(), world);
            case "frog" -> new ClientFrog(PetsInitializer.FROG.get(), world);
            case "horse" -> new ClientHorse(PetsInitializer.HORSE.get(), world);
            case "mooshroom" -> new ClientMooshroom(PetsInitializer.MOOSHROOM.get(), world);
            case "parrot" -> new ClientParrot(PetsInitializer.PARROT.get(), world);
            case "pig" -> new ClientPig(PetsInitializer.PIG.get(), world);
            case "rabbit" -> new ClientRabbit(PetsInitializer.RABBIT.get(), world);
            case "salmon" -> new ClientSalmon(PetsInitializer.SALMON.get(), world);
            case "sniffer" -> new ClientSniffer(PetsInitializer.SNIFFER.get(), world);
            case "snow_golem" -> new ClientSnowGolem(PetsInitializer.SNOW_GOLEM.get(), world);
            case "squid" -> new ClientSquid(PetsInitializer.SQUID.get(), world);
            case "strider" -> new ClientStrider(PetsInitializer.STRIDER.get(), world);
            case "tadpole" -> new ClientTadpole(PetsInitializer.TADPOLE.get(), world);
            case "turtle" -> new ClientTurtle(PetsInitializer.TURTLE.get(), world);
            case "villager" -> new ClientVillager(PetsInitializer.VILLAGER.get(), world);
            case "wandering_trader" -> new ClientWanderingTrader(PetsInitializer.WANDERING_TRADER.get(), world);
            case "bee" -> new ClientBee(PetsInitializer.BEE.get(), world);
            case "cave_spider" -> new ClientCaveSpider(PetsInitializer.CAVE_SPIDER.get(), world);
            case "dolphin" -> new ClientDolphin(PetsInitializer.DOLPHIN.get(), world);
            case "enderman" -> new ClientEnderman(PetsInitializer.ENDERMAN.get(), world);
            case "fox" -> new ClientFox(PetsInitializer.FOX.get(), world);
            case "goat" -> new ClientGoat(PetsInitializer.GOAT.get(), world);
            case "iron_golem" -> new ClientIronGolem(PetsInitializer.IRON_GOLEM.get(), world);
            case "llama" -> new ClientLlama(PetsInitializer.LLAMA.get(), world);
            case "nautilus" -> new ClientNautilus(PetsInitializer.NAUTILUS.get(), world);
            case "panda" -> new ClientPanda(PetsInitializer.PANDA.get(), world);
            case "piglin" -> new ClientPiglin(PetsInitializer.PIGLIN.get(), world);
            case "polar_bear" -> new ClientPolarBear(PetsInitializer.POLAR_BEAR.get(), world);
            case "pufferfish" -> new ClientPufferFish(PetsInitializer.PUFFERFISH.get(), world);
            case "spider" -> new ClientSpider(PetsInitializer.SPIDER.get(), world);
            case "wolf" -> new ClientWolf(PetsInitializer.WOLF.get(), world);
            case "blaze" -> new ClientBlaze(PetsInitializer.BLAZE.get(), world);
            case "breeze" -> new ClientBreeze(PetsInitializer.BREEZE.get(), world);
            case "creaking" -> new ClientCreaking(PetsInitializer.CREAKING.get(), world);
            case "creeper" -> new ClientCreeper(PetsInitializer.CREEPER.get(), world);
            case "elder_guardian" -> new ClientElderGuardian(PetsInitializer.ELDER_GUARDIAN_COOKIE.get(), world);
            case "endermite" -> new ClientEndermite(PetsInitializer.ENDERMITE.get(), world);
            case "evoker" -> new ClientEvoker(PetsInitializer.EVOKER.get(), world);
            case "ghast" -> new ClientGhast(PetsInitializer.GHAST.get(), world);
            case "happy_ghast" -> new ClientHappyGhast(PetsInitializer.HAPPY_GHAST.get(), world);
            case "guardian" -> new ClientGuardian(PetsInitializer.GUARDIAN.get(), world);
            case "hoglin" -> new ClientHoglin(PetsInitializer.HOGLIN.get(), world);
            case "magma_cube" -> new ClientMagmaCube(PetsInitializer.MAGMA_CUBE.get(), world);
            case "phantom" -> new ClientPhantom(PetsInitializer.PHANTOM.get(), world);
            case "pillager" -> new ClientPillager(PetsInitializer.PILLAGER.get(), world);
            case "ravager" -> new ClientRavager(PetsInitializer.RAVAGER.get(), world);
            case "shulker" -> new ClientShulker(PetsInitializer.SHULKER.get(), world);
            case "silverfish" -> new ClientSilverfish(PetsInitializer.SILVERFISH.get(), world);
            case "skeleton" -> new ClientSkeleton(PetsInitializer.SKELETON.get(), world);
            case "slime" -> new ClientSlime(PetsInitializer.SLIME.get(), world);
            case "vex" -> new ClientVex(PetsInitializer.VEX.get(), world);
            case "vindicator" -> new ClientVindicator(PetsInitializer.VINDICATOR.get(), world);
            case "warden" -> new ClientWarden(PetsInitializer.WARDEN.get(), world);
            case "witch" -> new ClientWitch(PetsInitializer.WITCH.get(), world);
            case "zombie" -> new ClientZombie(PetsInitializer.ZOMBIE.get(), world);
            case "zombie_villager" -> new ClientZombieVillager(PetsInitializer.ZOMBIE_VILLAGER.get(), world);
            case "husk" -> new ClientHusk(PetsInitializer.HUSK.get(), world);
            case "drowned" -> new ClientDrowned(PetsInitializer.DROWNED.get(), world);
            case "bogged" -> new ClientBogged(PetsInitializer.BOGGED.get(), world);
            case "parched" -> new ClientParched(PetsInitializer.PARCHED.get(), world);
            case "stray" -> new ClientStray(PetsInitializer.STRAY.get(), world);
            case "wither_skeleton" -> new ClientWitherSkeleton(PetsInitializer.WITHER_SKELETON.get(), world);
            case "ender_dragon" -> new ClientEnderDragon(PetsInitializer.ENDER_DRAGON.get(), world);
            case "wither" -> new ClientWither(PetsInitializer.WITHER.get(), world);
            case "angry_ghast" -> new AngryGhast(PetsInitializer.ANGRY_GHAST.get(), world);
            case "batato" -> new Batato(PetsInitializer.BATATO.get(), world);
            case "diamond_chicken" -> new DiamondChicken(PetsInitializer.DIAMOND_CHICKEN.get(), world);
            case "love_golem" -> new LoveGolem(PetsInitializer.LOVE_GOLEM.get(), world);
            case "mega_spud" -> new MegaSpud(PetsInitializer.MEGA_SPUD.get(), world);
            case "moon_cow" -> new MoonCow(PetsInitializer.MOON_COW.get(), world);
            case "nerd_creeper" -> new NerdCreeper(PetsInitializer.NERD_CREEPER.get(), world);
            case "pink_wither" -> new PinkWither(PetsInitializer.PINK_WITHER.get(), world);
            case "plaguewhale_slab" -> new PlaguewhaleSlab(PetsInitializer.PLAGUEWHALE_SLAB.get(), world);
            case "poisonous_potato_zombie" -> new PoisonousPotatoZombie(PetsInitializer.POISONOUS_POTATO_ZOMBIE.get(), world);
            case "ray_tracing" -> new RayTracing(PetsInitializer.RAY_TRACING.get(), world);
            case "redstone_bug" -> new RedstoneBug(PetsInitializer.REDSTONE_BUG.get(), world);
            case "smiling_creeper" -> new SmilingCreeper(PetsInitializer.SMILING_CREEPER.get(), world);
            case "toxifin_slab" -> new ToxifinSlab(PetsInitializer.TOXIFIN_SLAB.get(), world);
            case "potato_husk" -> new PotatoHusk(PetsInitializer.POTATO_HUSK.get(), world);
            case "head" -> new Head(PetsInitializer.HEAD.get(), world);
            case "traitor" -> new Traitor(PetsInitializer.TRAITOR.get(), world);
            case "dumbo_octopus" -> new DumboOctopus(PetsInitializer.DUMBO_OCTOPUS.get(), world);
            case "koi" -> new Koi(PetsInitializer.KOI.get(), world);
            case "stingray" -> new Stingray(PetsInitializer.STINGRAY.get(), world);
            case "sulfur_cube" -> new ClientSulfurCube(PetsInitializer.SULFUR_CUBE.get(), world);
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
        }
        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastChangePetName(Minecraft.getInstance().player.getStringUUID(), Utils.getActivePetName());
        }
        AutoConfig.getConfigHolder(PetsConfig.class).save();
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

    public static void setActivePetSkin(String val) {
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
            case "traitor" -> {
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
            }
            case "sulfur_cube" -> {
                CONFIG.sulfurCubeSkin = val.replace(" ", "_");
            }
        }
        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastChangePetSkin(Minecraft.getInstance().player.getStringUUID(), getActivePetSkin());
        }
    }

    //ripped this from fabrics StringUtil
    public static String capitalize(String s) {
        if (s.isEmpty()) {
            return s;
        } else {
            int pos;
            for(pos = 0; pos < s.length() && !Character.isLetterOrDigit(s.codePointAt(pos)); ++pos) {
            }

            if (pos == s.length()) {
                return s;
            } else {
                int cp = s.codePointAt(pos);
                int cpUpper = Character.toUpperCase(cp);
                if (cpUpper == cp) {
                    return s;
                } else {
                    StringBuilder ret = new StringBuilder(s.length());
                    ret.append(s, 0, pos);
                    ret.appendCodePoint(cpUpper);
                    ret.append(s, pos + Character.charCount(cp), s.length());
                    return ret.toString();
                }
            }
        }
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
            e.printStackTrace();
        }
        return Blocks.AIR;
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
}
