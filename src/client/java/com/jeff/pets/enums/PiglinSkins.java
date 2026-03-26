package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum PiglinSkins implements NameableEnum {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
