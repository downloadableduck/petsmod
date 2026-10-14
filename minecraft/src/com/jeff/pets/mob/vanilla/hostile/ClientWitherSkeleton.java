package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.IRangedAttackMob;
import com.jeff.pets.mob.GroundPet;

public class ClientWitherSkeleton extends GroundPet implements IRangedAttackMob {


    public ClientWitherSkeleton(net.minecraft.src.World level) {
        super(level);
        this.setSize(0.6f, 1.95f);
        this.texture = "/mob/skeleton_wither.png";
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
    protected String getLivingSound() {
        return "mob.skeleton.say";
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLiving livingEntity, float f) {

    }
}
