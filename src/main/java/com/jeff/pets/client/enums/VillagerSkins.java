package com.jeff.pets.client.enums;


public enum VillagerSkins implements NameableEnum {
    armorer,
    butcher,
    cartographer,
    cleric,
    farmer,
    fisherman,
    fletcher,
    leatherworker,
    librarian,
    mason,
    nitwit,
    shepherd,
    toolsmith,
    unemployed,
    weaponsmith;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.StringTextComponent(String.valueOf(this).replace("_", " "));
    }
}
