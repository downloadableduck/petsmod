package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientSkeleton extends GroundPet {

    public ClientSkeleton(World level) {
        super(level);
        this.setBounds(0.6F, 1.95F);
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

        public void rangedAttack(MobEntity livingEntity, float f) {

    }
}
