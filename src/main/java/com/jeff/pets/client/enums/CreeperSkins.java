package com.jeff.pets.client.enums;

import net.minecraft.util.ChatComponentText;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum CreeperSkins implements NameableEnum, EnumImpl {
    normal,
    charged;

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(normal, charged);
    }
}
