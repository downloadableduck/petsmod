package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientElderGuardian extends FlyingPet {
    public ClientElderGuardian(EntityType<? extends @NotNull TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }
}
