package com.jeff.pets.network;

public class ChangePetNamePayload extends Payload {
    String petName;

    public ChangePetNamePayload(String uuid, String petName) {
        super(uuid);
        this.petName = petName;
    }
}
