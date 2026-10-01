package com.jeff.pets.client.enums;


import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

public enum TropicalFishSkins implements NameableEnum {
    chichlid,
    clownfish,
    cotten_candy_betta,
    goatfish,
    parrotfish,
    queen_angelfish,
    red_lipped_blenny,
    tomato_clownfish,
    triggerfish,
    yellowtail_parrotfish;

    @Override
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }
}
