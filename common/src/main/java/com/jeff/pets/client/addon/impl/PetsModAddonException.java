package com.jeff.pets.client.addon.impl;

import com.jeff.pets.client.addon.AddonStage;

public class PetsModAddonException extends RuntimeException {
    public PetsModAddonException(String id, AddonStage stage, String petValue, Exception e) {
        super("An error occurred processing Pets Mod addon " + id + "'s pet + " + petValue + "at stage " + stage.name(), e);
    }
}
