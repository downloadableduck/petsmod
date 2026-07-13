package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ClientTropicalFish extends FlyingPet {

    public ClientTropicalFish(EntityType<? extends net.minecraft.entity.passive.TameableEntity> entityType, net.minecraft.world.World level) {
        super(entityType, level);
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }
}
