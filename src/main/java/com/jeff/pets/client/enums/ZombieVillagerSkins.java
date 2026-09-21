package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

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
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(armorer,
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
