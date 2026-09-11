package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientTropicalFish extends FlyingPet {

    public ClientTropicalFish(World level) {
        super(level);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    @Override
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_SQUID_AMBIENT;
    }
}
