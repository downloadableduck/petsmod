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
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
