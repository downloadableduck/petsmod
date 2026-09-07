package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientPolarBear extends GroundPet {
    public ClientPolarBear(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.4f, 1.4f);
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
        return SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    }
}
