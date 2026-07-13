package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ClientMagmaCube extends SlimeLikePet {

    public ClientMagmaCube(EntityType<? extends net.minecraft.entity.passive.TameableEntity> entityType, net.minecraft.world.World level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.MAGMA_CUBE_SQUISH;
    }
}
