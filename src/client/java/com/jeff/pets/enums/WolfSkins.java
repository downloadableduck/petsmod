package com.jeff.pets.enums;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum WolfSkins implements NameableEnum {
    ashen,
    black,
    chestnut,
    pale,
    rusty,
    snowy,
    spotted,
    striped,
    woods;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
