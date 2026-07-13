package com.jeff.pets.client.enums;


public enum MooshroomSkins implements NameableEnum {
    red,
    brown;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
