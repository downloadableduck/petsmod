package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientDonkey extends GroundPet {

    public ClientDonkey(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.3965f, 1.5f);
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
        return SoundEvents.ENTITY_DONKEY_AMBIENT;
    }
}
