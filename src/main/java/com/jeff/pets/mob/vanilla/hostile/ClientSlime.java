package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;


public class ClientSlime extends SlimeLikePet {
    public ClientSlime(EntityType<? extends  TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SLIME_JUMP;
    }
}
