package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum MooshroomSkins implements NameableEnum {
    red,
    brown;

    @Override
    public Component getDisplayName() {
        return Component.literal(this.toString());
    }
}
