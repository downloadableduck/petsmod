package com.jeff.pets.client.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum DumboOctopusSkins implements NameableEnum {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
