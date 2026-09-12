package com.jeff.pets.client.enums;


public enum PetList implements NameableEnum {
    bat,
    blaze,
    cat,
    cave_spider,
    chicken,
    cow,
    creeper,
    donkey,
    duck,
    dumbo_octopus,
    elder_guardian,
    ender_dragon,
    enderman,
    endermite,
    ghast,
    guardian,
    happy_ghast,
    head,
    horse,
    iron_golem,
    koi,
    magma_cube,
    mooshroom,
    penguin,
    pig,
    rabbit,
    racoon,
    sheep,
    shulker,
    silverfish,
    skeleton,
    slime,
    snow_golem,
    spider,
    squid,
    stingray,
    wither_skeleton,
    villager,
    witch,
    wither,
    wolf,
    zombie,
    zombie_villager,
    zombie_pigman;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.TextComponentString(String.valueOf(this).replace("_", " "));
    }
}
