package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientDonkey extends GroundPet {

    public ClientDonkey(World level) {
        super(level);
        this.setSize(1.3965F, 1.5F);
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
    protected String getAmbientSound() {
        return "mob.horse.donkey.idle";
    }
}
