package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientSheep extends GroundPet {

    public ClientSheep(World level) {
        super(level);
        this.setSize(0.9F, 1.3F);
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
        return "mob.sheep.say";
    }
}
