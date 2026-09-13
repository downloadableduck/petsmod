package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

@CanFly
public class ClientWither extends FlyingPet {
    public ClientWither(net.minecraft.world.World level) {
        super(level);
        this.setSize(2f, 3f);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.chicken.step";
    }
}
