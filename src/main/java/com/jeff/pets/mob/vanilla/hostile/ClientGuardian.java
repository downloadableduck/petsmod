package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

@CanFly
public class ClientGuardian extends GroundPet {
    public ClientGuardian(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.85f, 0.85f);
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
        return SoundEvents.ENTITY_GUARDIAN_AMBIENT;
    }
}
