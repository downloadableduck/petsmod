package com.jeff.pets.client.network.payload;

public class TeleportPetPayload extends Payload {

    public double x;
    public double y;
    public double z;

    public TeleportPetPayload(String uuid, double x, double y, double z) {
        super(uuid);
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
