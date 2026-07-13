package com.jeff.pets.client.enums;


public enum FrogSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
