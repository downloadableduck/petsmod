package com.jeff.pets.client.network.payload;

import java.util.UUID;

public class GeneralPetsPayload extends Payload {
    public UUID playerUUID;
    public String petSpecies;
    public String petName;
    public String petSkin;
    public boolean petOn;
    public boolean isBaby;

    public GeneralPetsPayload(String uuid, boolean petOn, String petSpecies, String petName, String petSkin, boolean isBaby) {
        super(uuid);
        this.playerUUID = UUID.fromString(uuid);
        this.petOn = petOn;
        this.petSpecies = petSpecies;
        this.petName = petName;
        this.petSkin = petSkin;
        this.isBaby = isBaby;
    }
}
