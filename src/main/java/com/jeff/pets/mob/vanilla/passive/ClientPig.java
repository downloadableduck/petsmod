package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientPig extends GroundPet {

    public ClientPig(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.9f, 0.9f);
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
        return "mob.pig.say";
    }
}
