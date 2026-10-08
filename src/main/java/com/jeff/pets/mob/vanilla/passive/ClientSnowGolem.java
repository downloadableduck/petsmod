package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientSnowGolem extends GroundPet {

    public ClientSnowGolem(World level) {
        super(level);
        this.setSize(0.7F, 1.9F);
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
        return "mob.snowman.say";
    }
}
