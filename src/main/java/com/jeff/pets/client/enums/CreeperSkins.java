package com.jeff.pets.client.enums;

import net.minecraft.text.LiteralText;
import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum CreeperSkins implements NameableEnum, EnumImpl {
    normal,
    charged;

    @Override
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(normal, charged);
    }
}
