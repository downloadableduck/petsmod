package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

@CanFly
public class ClientBat extends FlyingPet {

    public boolean isOnHead;

    public ClientBat(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.5f, 0.9f);
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
    protected String func_70639_aQ() {
        return "mob.bat.idle";
    }
}
