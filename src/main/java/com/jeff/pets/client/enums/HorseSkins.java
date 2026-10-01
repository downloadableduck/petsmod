package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum HorseSkins implements NameableEnum, EnumImpl {
    black,
    brown,
    chestnut,
    creamy,
    dark_brown,
    gray,
    skeleton,
    white,
    zombie;

    @Override
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(black, brown, chestnut, creamy, dark_brown, gray, skeleton, white, zombie);
    }
}
