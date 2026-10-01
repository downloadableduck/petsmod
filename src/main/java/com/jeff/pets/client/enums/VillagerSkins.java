package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum VillagerSkins implements NameableEnum, EnumImpl {
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
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
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
