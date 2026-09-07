package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientCreeper extends GroundPet {

    public boolean isPowered = false;

    public ClientCreeper(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 1.7f);
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
        return SoundEvents.ENTITY_CREEPER_PRIMED;
    }
}
