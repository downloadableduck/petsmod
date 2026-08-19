package com.jeff.pets.network;

import java.util.UUID;

public abstract class Payload {

    public String uuid;

    public Payload(String uuid) {
        this.uuid = uuid;
    }
}
