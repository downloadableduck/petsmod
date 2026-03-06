package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum HorseSkins implements NameableEnum {
    black,
    brown,
    chestnut,
    creamy,
    dark_brown,
    gray,
    skeleton,
    white,
    zombie;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
