package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientShulker extends GroundPet {
    public ClientShulker(World level) {
        super(level);
        this.setSize(1.0F, 2.0F);
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
    protected String getAmbientSound() {
        return "mob.shulker.idle";
    }
}
