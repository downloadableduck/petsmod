//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.enums;

public enum HoglinSkins implements NameableEnum {
    hoglin,
    zoglin;

    private HoglinSkins() {
    }

    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
