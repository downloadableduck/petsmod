package com.jeff.pets.client.enums;


public enum PandaSkins implements NameableEnum {
    agressive,
    brown,
    lazy,
    normal,
    playful,
    weak,
    worried;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
