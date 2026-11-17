//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.enums;

public enum BlankEnum implements NameableEnum {
    no_skins_are_available;

    private BlankEnum() {
    }

    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
