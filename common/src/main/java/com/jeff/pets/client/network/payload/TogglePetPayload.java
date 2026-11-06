package com.jeff.pets.client.network.payload;

public class TogglePetPayload extends Payload {

    public final boolean on;
    public final String petName;

    public TogglePetPayload(String uuid, boolean on, String petName) {
        super(uuid);
        this.on = on;
        this.petName = petName;
    }
}
