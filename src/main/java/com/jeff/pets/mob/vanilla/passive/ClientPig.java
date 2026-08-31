package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.init.SoundEvents;

public class ClientPig extends GroundPet {

    public ClientPig(EntityType<? extends net.minecraft.entity.passive.EntityTameable> entityType, net.minecraft.world.World level) {
        super(entityType, level);
        this.setSize(0.9f, 0.9f);
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
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }
}
