package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;


public class ClientCreeper extends GroundPet implements PowerableMob {

    public boolean isPowered = false;

    public ClientCreeper(EntityType<? extends  TamableAnimal> entityType, Level level) {
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
