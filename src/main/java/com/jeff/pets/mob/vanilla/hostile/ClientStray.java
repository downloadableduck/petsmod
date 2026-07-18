package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientStray extends GroundPet implements RangedAttackMob {

    public ClientStray(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
        super(entityType, level);
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_STRAY_AMBIENT;
    }

    @Override
    public void attack(LivingEntity livingEntity, float f) {

    }
}
