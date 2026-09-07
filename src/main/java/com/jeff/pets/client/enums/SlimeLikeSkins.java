package com.jeff.pets.client.enums;


public enum SlimeLikeSkins implements NameableEnum {
    small,
    medium,
    large;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.TextComponentString(String.valueOf(this).replace("_", " "));
    }
}
