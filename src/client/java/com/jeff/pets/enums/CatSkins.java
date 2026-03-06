package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum CatSkins implements NameableEnum {
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
        return Component.literal(String.valueOf(this));
    }
}
