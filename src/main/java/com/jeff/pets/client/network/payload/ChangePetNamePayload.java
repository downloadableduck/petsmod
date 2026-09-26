package com.jeff.pets.client.network.payload;

public class ChangePetNamePayload extends Payload {
    public final String petName;

    public ChangePetNamePayload(String uuid, String petName) {
        super(uuid);
        this.petName = petName;
    }
}
