package com.jeff.pets.client.addon.impl;

public class PetsModAddonException extends RuntimeException {
    public PetsModAddonException(String id, Exception e) {
        super("An error occurred processing Pets Mod addon " + id + ", ", e);
    }
}
