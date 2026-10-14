package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;

public class ClientIronGolem extends GroundPet {
    public ClientIronGolem(net.minecraft.src.World level) {
        super(level);
        this.setSize(1.4f, 2.7f);
        this.texture = "/mob/irongolem.png";
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
    protected String getLivingSound() {
        return "mob.irongolem.walk";
    }
}
