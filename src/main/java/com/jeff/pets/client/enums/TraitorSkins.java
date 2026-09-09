package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

import java.util.List;

public enum TraitorSkins implements NameableEnum, EnumImpl {
    plains,
    desert,
    savanna,
    taiga,
    snowy,
    jungle,
    swamp;

    @Override
    public List<Enum> getAllValues() {
        return List.of(plains,
                desert,
                savanna,
                taiga,
                snowy,
                jungle,
                swamp);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
