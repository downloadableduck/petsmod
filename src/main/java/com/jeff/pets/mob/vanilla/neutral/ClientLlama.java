package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientLlama extends GroundPet {
    public ClientLlama(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.9f, 1.87f);
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
        return SoundEvents.ENTITY_LLAMA_AMBIENT;
    }
}
