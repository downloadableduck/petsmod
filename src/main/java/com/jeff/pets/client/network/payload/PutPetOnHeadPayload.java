package com.jeff.pets.client.network.payload;

public class PutPetOnHeadPayload extends Payload{
    public boolean onHead;

    public PutPetOnHeadPayload(String uuid, boolean onHead) {
        super(uuid);
        this.onHead = onHead;
    }
}
