package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum CopperGolemSkins implements NameableEnum, EnumImpl {
    exposed,
    oxidized,
    unoxidized,
    weathered;

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(exposed, oxidized, unoxidized, weathered);
    }
}
