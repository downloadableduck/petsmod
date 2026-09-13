package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientMooshroom extends GroundPet {

    public ClientMooshroom(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.9f, 1.4f);
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
        return "mob.cow.say";
    }
}
