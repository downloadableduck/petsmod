package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum CatSkins implements NameableEnum, EnumImpl {
    black,
    british_shorthair,
    calico,
    jellie,
    ocelot,
    persian,
    ragdoll,
    red,
    siamese,
    tabby,
    tuxedo,
    white;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(black, british_shorthair, calico, jellie, ocelot, persian, ragdoll, red, siamese, tabby, tuxedo, white);
    }
}
