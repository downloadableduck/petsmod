package com.jeff.pets.client.enums;


public enum NautilusSkins implements NameableEnum {
    nautilus,
    coral_zombie,
    zombie;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
