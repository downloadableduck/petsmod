package com.jeff.pets.enums;


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
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
