package com.jeff.pets.client.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum DuckSkins implements NameableEnum {

    mallard,
    pekin,
    rubber,
    bronze;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
