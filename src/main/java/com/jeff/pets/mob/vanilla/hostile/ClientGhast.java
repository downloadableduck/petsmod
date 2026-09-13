package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

@CanFly
public class ClientGhast extends FlyingPet {
    public ClientGhast(net.minecraft.world.World level) {
        super(level);
        this.setSize(4f, 4f);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 6;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.ghast.moan";
    }
}
