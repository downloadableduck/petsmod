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
    dolphin,
    donkey,
    drowned,
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
    pufferfish,
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
    public net.minecraft.text.LiteralText getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }
}
