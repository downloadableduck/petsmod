package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientSnowGolem extends GroundPet {

    public ClientSnowGolem(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.7f, 1.9f);
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
        return "mob.snowman.say";
    }
}
