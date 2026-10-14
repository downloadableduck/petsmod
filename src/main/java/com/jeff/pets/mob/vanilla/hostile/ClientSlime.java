package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.SlimeLikePet;

public class ClientSlime extends SlimeLikePet {
    public ClientSlime(net.minecraft.world.World level) {
        super(level);
        this.setSize(2f, 2f);
        this.texture = "/mob/slime.png";
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.slime.small";
    }
}
