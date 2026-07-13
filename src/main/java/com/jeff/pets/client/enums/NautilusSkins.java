package com.jeff.pets.client.enums;


public enum NautilusSkins implements NameableEnum {
    nautilus,
    coral_zombie,
    zombie;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
