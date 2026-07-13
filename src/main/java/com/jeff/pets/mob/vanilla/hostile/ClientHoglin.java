package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.IFlinging;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ClientHoglin extends GroundPet implements IFlinging {
    public ClientHoglin(EntityType<? extends net.minecraft.entity.passive.TameableEntity> entityType, net.minecraft.world.World level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 3;
    }

    @Override
    protected float heartHeight() {
        return 3;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HOGLIN_AMBIENT;
    }

    @Override
    public int getAttackAnimationRemainingTicks() {
        return 0;
    }
}
