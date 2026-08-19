package com.jeff.pets.network;

public class ChangePetSkinPayload extends Payload {
    public String petSkin;

    public ChangePetSkinPayload(String uuid, String petskin) {
        super(uuid);
        this.petSkin = petskin;
    }
}
