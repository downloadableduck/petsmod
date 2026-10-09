package com.jeff.pets.client.enums;


public enum CatSkins implements NameableEnum {
    black,
    british_shorthair,
    calico,
    jellie,
    ocelot,
    persian,
    ragdoll,
    red,
    siamese,
    tabby,
    tuxedo,
    white;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
