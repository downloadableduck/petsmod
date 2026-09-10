package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum ZombieVillagerSkins implements NameableEnum, EnumImpl {
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
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(armorer,
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
                weaponsmith);
    }
}
