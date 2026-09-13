package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientSheep extends GroundPet {

    public ClientSheep(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.9f, 1.3f);
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
        return "mob.sheep.say";
    }
}
