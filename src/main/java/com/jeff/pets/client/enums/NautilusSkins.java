package com.jeff.pets.client.enums;


public enum NautilusSkins implements NameableEnum {
    nautilus,
    coral_zombie,
    zombie;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
