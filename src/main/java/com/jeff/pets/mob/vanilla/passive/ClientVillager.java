package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientVillager extends GroundPet {

    public ClientVillager(World level) {
        super(level);
        this.setSize(0.6F, 1.95F);
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
        return "mob.villager.idle";
    }
}
