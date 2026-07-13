package com.jeff.pets.client.enums;


public enum RabbitSkins implements NameableEnum {
    black,
    brown,
    gold,
    killer,
    salt,
    splotched,
    toast,
    white;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
