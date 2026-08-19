package com.jeff.pets.network;

import java.util.UUID;

public class GeneralPetsPayload extends Payload{
    public UUID playerUUID;
    public String petSpecies;
    public String petName;
    public String petSkin;
    public boolean petOn;

    public GeneralPetsPayload(String uuid, boolean petOn, String petSpecies, String petName, String petSkin) {
        super(uuid);
        this.playerUUID = UUID.fromString(uuid);
        this.petOn = petOn;
        this.petSpecies = petSpecies;
        this.petName = petName;
        this.petSkin = petSkin;
    }
}
