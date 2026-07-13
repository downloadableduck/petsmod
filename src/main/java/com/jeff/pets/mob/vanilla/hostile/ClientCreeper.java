package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ClientCreeper extends GroundPet implements IChargeableMob {

    public boolean isPowered = false;

    public ClientCreeper(EntityType<? extends net.minecraft.entity.passive.TameableEntity> entityType, net.minecraft.world.World level) {
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
        return SoundEvents.CREEPER_PRIMED;
    }

    @Override
    public boolean isPowered() {
        return isPowered;
    }
}
