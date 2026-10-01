package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum PandaSkins implements NameableEnum, EnumImpl {
    agressive,
    brown,
    lazy,
    normal,
    playful,
    weak,
    worried;

    @Override
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(agressive, brown, lazy, normal, playful, weak, worried);
    }
}
