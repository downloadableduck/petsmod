package com.jeff.pets.sound;

/**
 * 1.8.9 backport shim: 1.9.4+ SoundEvents constants replaced with the plain event-name
 * strings expected by Minecraft 1.8.9's SoundManager. Keys that do not exist in 1.8.9
 * (squid, witch, snow golem, endermite, shulker, bogged) resolve to an empty string so
 * {@code playSound} simply plays nothing.
 */
public final class SoundEvents {
    public static final String BOGGED_AMBIENT = "";
    public static final String ENTITY_BAT_AMBIENT = "mob.bat.idle";
    public static final String ENTITY_BLAZE_AMBIENT = "mob.blaze.breathe";
    public static final String ENTITY_CAT_AMBIENT = "mob.cat.meow";
    public static final String ENTITY_CHICKEN_AMBIENT = "mob.chicken.say";
    public static final String ENTITY_CHICKEN_STEP = "mob.chicken.step";
    public static final String ENTITY_COW_AMBIENT = "mob.cow.say";
    public static final String ENTITY_CREEPER_PRIMED = "creeper.primed";
    public static final String ENTITY_DONKEY_AMBIENT = "mob.horse.donkey.idle";
    public static final String ENTITY_ELDER_GUARDIAN_AMBIENT = "mob.guardian.elder.idle";
    public static final String ENTITY_ENDERDRAGON_AMBIENT = "mob.enderdragon.growl";
    public static final String ENTITY_ENDERMEN_AMBIENT = "mob.endermen.idle";
    public static final String ENTITY_ENDERMITE_AMBIENT = "";
    public static final String ENTITY_GHAST_AMBIENT = "mob.ghast.moan";
    public static final String ENTITY_GUARDIAN_AMBIENT = "mob.guardian.idle";
    public static final String ENTITY_HORSE_AMBIENT = "mob.horse.idle";
    public static final String ENTITY_IRONGOLEM_STEP = "mob.irongolem.walk";
    public static final String ENTITY_MAGMACUBE_JUMP = "mob.magmacube.big";
    public static final String ENTITY_PIG_AMBIENT = "mob.pig.say";
    public static final String ENTITY_RABBIT_AMBIENT = "mob.rabbit.idle";
    public static final String ENTITY_SHEEP_AMBIENT = "mob.sheep.say";
    public static final String ENTITY_SHULKER_AMBIENT = "";
    public static final String ENTITY_SILVERFISH_AMBIENT = "mob.silverfish.say";
    public static final String ENTITY_SKELETON_AMBIENT = "mob.skeleton.say";
    public static final String ENTITY_SLIME_JUMP = "mob.slime.big";
    public static final String ENTITY_SNOWMAN_AMBIENT = "";
    public static final String ENTITY_SPIDER_AMBIENT = "mob.spider.say";
    public static final String ENTITY_SQUID_AMBIENT = "";
    public static final String ENTITY_SQUID_DEATH = "";
    public static final String ENTITY_SQUID_HURT = "";
    public static final String ENTITY_VILLAGER_AMBIENT = "mob.villager.idle";
    public static final String ENTITY_WITCH_AMBIENT = "";
    public static final String ENTITY_WOLF_AMBIENT = "mob.wolf.bark";
    public static final String ENTITY_ZOMBIE_AMBIENT = "mob.zombie.say";
    public static final String ENTITY_ZOMBIE_PIG_AMBIENT = "mob.zombiepig.zpigangry";
    public static final String ENTITY_ZOMBIE_VILLAGER_AMBIENT = "mob.zombie.say";
    public static final String UI_BUTTON_CLICK = "gui.button.press";

    private SoundEvents() {
    }
}