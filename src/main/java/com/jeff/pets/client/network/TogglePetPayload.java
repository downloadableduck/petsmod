package com.jeff.pets.network;

public class TogglePetPayload extends Payload {

    public boolean on;
    public String petName;

    public TogglePetPayload(String uuid, boolean on, String petName) {
        super(uuid);
        this.on = on;
        this.petName = petName;
    }
}
