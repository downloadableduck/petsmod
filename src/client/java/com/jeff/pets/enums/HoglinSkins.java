package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum HoglinSkins implements NameableEnum {
    hoglin,
    zoglin;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
