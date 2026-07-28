package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ClientZombiePigman extends GroundPet {
    public ClientZombiePigman(EntityType<? extends TameableEntity> type, World level) {
        super(type, level);
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
        return SoundEvents.ZOMBIE_PIGMAN_AMBIENT;
    }
}
