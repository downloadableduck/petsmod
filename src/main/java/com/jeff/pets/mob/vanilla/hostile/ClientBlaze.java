package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

@CanFly
public class ClientBlaze extends FlyingPet {
    public ClientBlaze(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 1.8f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.blaze.breathe";
    }
}
