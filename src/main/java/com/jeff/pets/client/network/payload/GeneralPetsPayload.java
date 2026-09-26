package com.jeff.pets.client.network.payload;

import java.util.UUID;

public class GeneralPetsPayload extends Payload {
    public final UUID playerUUID;
    public final String petSpecies;
    public final String petName;
    public final String petSkin;
    public final boolean petOn;
    public final boolean isBaby;

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
