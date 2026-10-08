package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.FlyingPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFish extends FlyingPet {
    public ClientPufferFish(World level) {
        super(level);
        this.setSize(0.7F, 0.7F);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.squid.ambient";
    }
}
