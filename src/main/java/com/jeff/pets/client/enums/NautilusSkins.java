package com.jeff.pets.client.enums;


public enum NautilusSkins implements NameableEnum {
    nautilus,
    coral_zombie,
    zombie;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
