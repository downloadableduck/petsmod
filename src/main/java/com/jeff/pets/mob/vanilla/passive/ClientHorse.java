package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientHorse extends GroundPet {

    public ClientHorse(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.3965f, 1.6f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 3;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }
}
