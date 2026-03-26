package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum BeeSkins implements NameableEnum {
    happy,
    angry;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
