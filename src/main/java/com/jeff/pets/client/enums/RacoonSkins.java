package com.jeff.pets.client.enums;


public enum RacoonSkins implements NameableEnum {
    normal,
    albino;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
