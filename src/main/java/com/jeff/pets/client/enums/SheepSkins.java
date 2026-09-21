package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum SheepSkins implements NameableEnum, EnumImpl {
    black,
    blue,
    brown,
    cyan,
    gray,
    green,
    light_blue,
    light_gray,
    lime,
    magenta,
    orange,
    pink,
    purple,
    red,
    white,
    yellow;


    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(black,
                blue,
                brown,
                cyan,
                gray,
                green,
                light_blue,
                light_gray,
                lime,
                magenta,
                orange,
                pink,
                purple,
                red,
                white,
                yellow);
    }
}
