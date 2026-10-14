package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;


public class ClientBat extends FlyingPet {

    public boolean isOnHead;

    public ClientBat(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.5f, 0.9f);
        this.texture = "/mob/bat.png";
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.bat.idle";
    }
}
