package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientVillager extends GroundPet {

    public ClientVillager(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 1.95f);
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
    protected String func_70639_aQ() {
        return "mob.villager.idle";
    }
}
