package com.jeff.pets.client.network.payload;

public abstract class Payload {

    public String uuid;

    public Payload(String uuid) {
        this.uuid = uuid;
    }
}
