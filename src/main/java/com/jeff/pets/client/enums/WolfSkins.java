package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum WolfSkins implements NameableEnum, EnumImpl {
    ashen,
    black,
    chestnut,
    pale,
    rusty,
    snowy,
    spotted,
    striped,
    woods;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(ashen,
                black,
                chestnut,
                pale,
                rusty,
                snowy,
                spotted,
                striped,
                woods);
    }
}
