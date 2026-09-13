package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;

public class ClientWolf extends GroundPet {

    public ClientWolf(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 0.85f);
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
        return "mob.wolf.growl";
    }
}
