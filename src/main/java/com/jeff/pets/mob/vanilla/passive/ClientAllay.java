package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientAllay extends FlyingPet {
    public ClientAllay(EntityType<? extends @NotNull TamableAnimal> entityType, Level level) {
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
        return SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM;
    }
}
