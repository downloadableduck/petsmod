package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;


public class ClientPufferFish extends FlyingPet {
    public ClientPufferFish(EntityType<? extends  TamableAnimal> entityType, Level level) {
        super(entityType, level);
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PUFFER_FISH_BLOW_UP;
    }
}
