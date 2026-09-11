package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;


public class ClientWither extends FlyingPet {
    public ClientWither(World world) {
        super(world);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_STEP;
    }
}
