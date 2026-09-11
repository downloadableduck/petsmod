package com.jeff.pets.client.enums;

import net.minecraft.text.LiteralText;

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
    evoker,
    ghast,
    guardian,
    happy_ghast,
    head,
    horse,
    husk,
    iron_golem,
    koi,
    llama,
    magma_cube,
    mooshroom,
    penguin,
    pig,
    polar_bear,
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
    stray,
    wither_skeleton,
    vex,
    villager,
    vindicator,
    witch,
    wither,
    wolf,
    zombie,
    zombie_villager,
    zombie_pigman;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }
}
