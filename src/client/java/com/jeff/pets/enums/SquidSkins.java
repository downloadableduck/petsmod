package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
