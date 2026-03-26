package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

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
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
