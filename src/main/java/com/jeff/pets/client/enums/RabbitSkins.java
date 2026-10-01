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
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
