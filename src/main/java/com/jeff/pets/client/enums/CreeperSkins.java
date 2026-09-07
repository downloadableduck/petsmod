package com.jeff.pets.client.enums;


public enum CreeperSkins implements NameableEnum {
    normal,
    charged;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.TextComponentString(String.valueOf(this).replace("_", " "));
    }
}
