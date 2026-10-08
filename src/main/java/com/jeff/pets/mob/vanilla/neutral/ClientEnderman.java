package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientEnderman extends GroundPet {
    public ClientEnderman(World level) {
        super(level);
        this.setSize(0.6F, 2.9F);
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
        return "mob.endermen.idle";
    }
}
