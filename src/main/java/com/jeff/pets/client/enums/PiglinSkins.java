package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum PiglinSkins implements NameableEnum, EnumImpl {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(piglin, piglin_brute, zombified_piglin);
    }
}
