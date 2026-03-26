package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum PandaSkins implements NameableEnum {
    agressive,
    brown,
    lazy,
    normal,
    playful,
    weak,
    worried;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
