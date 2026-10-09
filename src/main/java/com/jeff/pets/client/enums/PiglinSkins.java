package com.jeff.pets.client.enums;


public enum PiglinSkins implements NameableEnum {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
