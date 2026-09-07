package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientRabbit extends SlimeLikePet {

    public ClientRabbit(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.4f, 0.5f);
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_RABBIT_AMBIENT;
    }
}
