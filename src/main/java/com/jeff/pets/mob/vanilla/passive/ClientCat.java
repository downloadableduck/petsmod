package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientCat extends GroundPet {

    public boolean isOnHead;

    public ClientCat(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 0.7f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.cat.meow";
    }
}
