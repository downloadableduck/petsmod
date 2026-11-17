//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.enums;

public enum DuckSkins implements NameableEnum {
    mallard,
    pekin,
    rubber,
    bronze;

    private DuckSkins() {
    }

    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
