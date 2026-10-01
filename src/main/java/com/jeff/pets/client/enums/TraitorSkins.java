package com.jeff.pets.client.enums;


import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

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
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this));
    }
}
