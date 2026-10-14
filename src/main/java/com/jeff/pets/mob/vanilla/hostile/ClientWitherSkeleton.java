package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.monster.RangedAttackMob;

public class ClientWitherSkeleton extends GroundPet implements RangedAttackMob {


    public ClientWitherSkeleton(net.minecraft.world.World level) {
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
    protected String getAmbientSound() {
        return "mob.skeleton.say";
    }

    @Override
    public void doRangedAttack(MobEntity livingEntity, float f) {

    }
}
