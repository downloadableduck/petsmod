package com.jeff.pets.enums;

import com.jeff.pets.client.enums.EnumImpl;
import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

import java.util.List;

public enum RacoonSkins implements NameableEnum, EnumImpl {
    normal,
    albino;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(normal, albino);
    }
}
