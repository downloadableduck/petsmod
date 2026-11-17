//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.enums;

public enum HorseSkins implements NameableEnum {
    black,
    brown,
    chestnut,
    creamy,
    dark_brown,
    gray,
    skeleton,
    white,
    zombie;

    private HorseSkins() {
    }

    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
