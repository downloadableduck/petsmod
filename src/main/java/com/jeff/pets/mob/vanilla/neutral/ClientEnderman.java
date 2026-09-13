package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;

public class ClientEnderman extends GroundPet {
    public ClientEnderman(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 2.9f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 3;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.endermen.idle";
    }
}
