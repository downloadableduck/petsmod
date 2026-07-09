package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.level.Level;


public class ClientHoglin extends GroundPet implements HoglinBase {
    public ClientHoglin(EntityType<? extends  TamableAnimal> entityType, Level level) {
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
