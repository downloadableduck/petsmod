package com.jeff.pets.client.enums;

import net.minecraft.util.ChatComponentText;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum CamelSkins implements NameableEnum, EnumImpl {
    camel,
    husk;

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(camel, husk);
    }
}
