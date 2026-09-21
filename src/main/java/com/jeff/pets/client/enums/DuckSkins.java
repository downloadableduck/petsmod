package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.IChatComponent; import net.minecraft.util.ChatComponentText;

import java.util.List;

public enum DuckSkins implements NameableEnum, EnumImpl {

    mallard,
    pekin,
    rubber,
    bronze;

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(mallard, pekin, rubber, bronze);
    }
}
