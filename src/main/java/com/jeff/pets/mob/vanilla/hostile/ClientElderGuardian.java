package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

@CanFly
public class ClientElderGuardian extends FlyingPet {
    public ClientElderGuardian(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.9975f, 1.9975f);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT;
    }
}
