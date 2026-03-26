package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum CamelSkins implements NameableEnum {
    camel,
    husk;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
