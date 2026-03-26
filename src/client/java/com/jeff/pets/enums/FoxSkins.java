package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
