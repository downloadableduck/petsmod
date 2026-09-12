package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeleton extends GroundPet implements RangedAttackMob {


    public ClientWitherSkeleton(World level) {
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
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    public void rangedAttack(LivingEntity target, float pullProgress) {

    }
}
