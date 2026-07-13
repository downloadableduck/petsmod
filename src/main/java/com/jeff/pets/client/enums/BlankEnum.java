package com.jeff.pets.client.enums;


public enum BlankEnum implements NameableEnum {
    no_skins_are_available;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
