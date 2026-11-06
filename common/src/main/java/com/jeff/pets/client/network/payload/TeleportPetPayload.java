package com.jeff.pets.client.network.payload;

public class TeleportPetPayload extends Payload {

    public final double x;
    public final double y;
    public final double z;

    public TeleportPetPayload(String uuid, double x, double y, double z) {
        super(uuid);
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
