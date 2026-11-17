//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.enums;

public enum FrogSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    private FrogSkins() {
    }

    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
