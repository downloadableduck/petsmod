package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

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
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(agressive, brown, lazy, normal, playful, weak, worried);
    }
}
