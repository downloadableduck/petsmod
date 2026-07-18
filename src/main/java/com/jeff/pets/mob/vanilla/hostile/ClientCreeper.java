package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientCreeper extends GroundPet {

    public boolean isPowered = false;

    public ClientCreeper(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
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
        return SoundEvents.ENTITY_CREEPER_PRIMED;
    }
}
