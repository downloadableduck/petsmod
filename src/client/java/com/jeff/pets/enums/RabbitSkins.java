package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum RabbitSkins implements NameableEnum {
    black,
    brown,
    gold,
    killer,
    salt,
    splotched,
    toast,
    white;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
