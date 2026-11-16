package com.jeff.pets.client.enums;

public enum PetList implements NameableEnum {
    bat,
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
    spider,
    squid,
    stingray,
    wither_skeleton,
    villager,
    witch,
    wither,
    zombie,
    zombie_pigman;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
