package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.init.SoundEvents;

@CanFly
public class ClientGhast extends FlyingPet {
    public ClientGhast(EntityType<? extends net.minecraft.entity.passive.EntityTameable> entityType, net.minecraft.world.World level) {
        super(entityType, level);
        this.setSize(4f, 4f);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 6;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_GHAST_AMBIENT;
    }
}
