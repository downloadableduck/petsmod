package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

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
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
