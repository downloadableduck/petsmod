package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum RabbitSkins implements NameableEnum, EnumImpl {
    black,
    brown,
    gold,
    killer,
    salt,
    splotched,
    toast,
    white;

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(black, brown, gold, killer, salt, splotched, toast, white);
    }
}
