package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientCat extends GroundPet {

    public boolean isOnHead;

    public ClientCat(World level) {
        super(level);
        this.setSize(0.6F, 0.7F);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.cat.meow";
    }
}
