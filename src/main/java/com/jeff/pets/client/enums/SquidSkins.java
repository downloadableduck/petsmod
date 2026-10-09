package com.jeff.pets.client.enums;


public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
