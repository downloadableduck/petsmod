package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.init.SoundEvents;

public class ClientSnowGolem extends GroundPet {

    public ClientSnowGolem(EntityType<? extends net.minecraft.entity.passive.EntityTameable> entityType, net.minecraft.world.World level) {
        super(entityType, level);
        this.setSize(0.7f, 1.9f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 3;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }
}
