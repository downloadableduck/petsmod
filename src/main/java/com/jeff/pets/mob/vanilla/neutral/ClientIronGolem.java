package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientIronGolem extends GroundPet {
    public ClientIronGolem(World level) {
        super(level);
        this.setSize(1.4F, 2.7F);
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
        return "mob.irongolem.walk";
    }
}
