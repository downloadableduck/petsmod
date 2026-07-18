package com.jeff.pets.client.enums;


public enum BlankEnum implements NameableEnum {
    no_skins_are_available;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
