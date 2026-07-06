package com.jeff.pets.client.enums;

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
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
