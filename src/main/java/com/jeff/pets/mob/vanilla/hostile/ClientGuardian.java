package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientGuardian extends GroundPet {
    public ClientGuardian(World level) {
        super(level);
        this.setBounds(0.85F, 0.85F);
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
        return Sounds.ENTITY_GUARDIAN_AMBIENT;
    }
}
