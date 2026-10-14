package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientPig extends GroundPet {

    public ClientPig(net.minecraft.src.World level) {
        super(level);
        this.setSize(0.9f, 0.9f);
        this.texture = "/mob/pig.png";
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
    protected String getLivingSound() {
        return "mob.pig.say";
    }
}
