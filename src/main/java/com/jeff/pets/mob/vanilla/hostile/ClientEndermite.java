package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;

public class ClientEndermite extends GroundPet {
    public ClientEndermite(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.4f, 0.3f);
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
        return "mob.endermite.hit";
    }
}
