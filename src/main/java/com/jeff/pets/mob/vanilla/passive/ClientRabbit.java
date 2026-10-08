package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.SlimeLikePet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;


public class ClientRabbit extends SlimeLikePet {

    public ClientRabbit(World level) {
        super(level);
        this.setSize(0.4F, 0.5F);
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
    protected String getAmbientSound() {
        return "mob.rabbit.idle";
    }
}
