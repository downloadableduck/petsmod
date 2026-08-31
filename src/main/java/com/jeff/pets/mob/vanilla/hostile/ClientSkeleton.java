package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundEvent;
import net.minecraft.init.SoundEvents;

public class ClientSkeleton extends GroundPet implements IRangedAttackMob {

    public ClientSkeleton(EntityType<? extends net.minecraft.entity.passive.EntityTameable> entityType, net.minecraft.world.World level) {
        super(entityType, level);
        this.setSize(0.6f, 1.95f);
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
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase livingEntity, float f) {

    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }
}
