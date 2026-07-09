package com.jeff.pets.mob.aprilfools;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;


@CanFly
public class AngryGhast extends FlyingPet {
    public AngryGhast(EntityType<? extends  TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 6;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GHAST_AMBIENT;
    }
}
