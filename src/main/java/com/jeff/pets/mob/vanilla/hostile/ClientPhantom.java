package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.init.SoundEvents;

@CanFly
public class ClientPhantom extends FlyingPet {
    public ClientPhantom(EntityType<? extends net.minecraft.entity.passive.EntityTameable> entityType, net.minecraft.world.World level) {
        super(entityType, level);
        this.setSize(0.9f, 0.5f);
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
        return SoundEvents.ENTITY_PHANTOM_AMBIENT;
    }
}
