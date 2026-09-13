package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;

public class ClientIronGolem extends GroundPet {
    public ClientIronGolem(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.4f, 2.7f);
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
        return "mob.irongolem.walk";
    }
}
