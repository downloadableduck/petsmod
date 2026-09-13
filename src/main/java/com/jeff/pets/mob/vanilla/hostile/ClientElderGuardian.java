package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

@CanFly
public class ClientElderGuardian extends FlyingPet {
    public ClientElderGuardian(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.9975f, 1.9975f);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.guardian.elder.idle";
    }
}
