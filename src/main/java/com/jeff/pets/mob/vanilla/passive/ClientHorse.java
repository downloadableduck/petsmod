package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientHorse extends GroundPet {

    public ClientHorse(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.3965f, 1.6f);
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
        return "mob.horse.idle";
    }
}
