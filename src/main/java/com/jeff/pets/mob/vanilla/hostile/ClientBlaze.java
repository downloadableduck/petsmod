package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientBlaze extends FlyingPet {
    public ClientBlaze(World level) {
        super(level);
        this.setSize(0.6F, 1.8F);
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
        return "mob.blaze.breathe";
    }
}
