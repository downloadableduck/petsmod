package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;

public class ClientCreeper extends GroundPet {

    public boolean isPowered = false;

    public ClientCreeper(net.minecraft.src.World level) {
        super(level);
        this.setSize(0.6f, 1.7f);
        this.texture = "/mob/creeper.png";
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
    protected String getLivingSound() {
        return "creeper.primed";
    }
}
