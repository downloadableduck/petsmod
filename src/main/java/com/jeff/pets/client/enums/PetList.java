package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum PetList implements NameableEnum, EnumImpl {
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
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of();
    }
}
