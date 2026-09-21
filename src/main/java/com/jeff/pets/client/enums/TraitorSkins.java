package com.jeff.pets.client.enums;


import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum TraitorSkins implements NameableEnum, EnumImpl {
    plains,
    desert,
    savanna,
    taiga,
    snowy,
    jungle,
    swamp;

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(plains,
                desert,
                savanna,
                taiga,
                snowy,
                jungle,
                swamp);
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this));
    }
}
