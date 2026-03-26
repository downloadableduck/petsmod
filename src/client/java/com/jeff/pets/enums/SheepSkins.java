package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum SheepSkins implements NameableEnum {
    black,
    blue,
    brown,
    cyan,
    gray,
    green,
    light_blue,
    light_gray,
    lime,
    magenta,
    orange,
    pink,
    purple,
    red,
    white,
    yellow;


    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
