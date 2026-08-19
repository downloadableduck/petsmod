package com.jeff.pets.enums;

import com.jeff.pets.client.enums.EnumImpl;
import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

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
