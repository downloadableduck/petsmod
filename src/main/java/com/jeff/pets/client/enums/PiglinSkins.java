package com.jeff.pets.enums;

import com.jeff.pets.client.enums.EnumImpl;
import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

import java.util.List;

public enum PiglinSkins implements NameableEnum, EnumImpl {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(piglin, piglin_brute, zombified_piglin);
    }
}
