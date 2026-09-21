package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum DumboOctopusSkins implements NameableEnum, EnumImpl {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(blue, green, orange, pink, red, yellow);
    }
}
