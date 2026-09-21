package com.jeff.pets.client.network.payload;

public class ToggleBabyPayload extends Payload {

    public boolean isBaby;

    public ToggleBabyPayload(String uuid, boolean isBaby) {
        super(uuid);
        this.isBaby = isBaby;
    }
}
