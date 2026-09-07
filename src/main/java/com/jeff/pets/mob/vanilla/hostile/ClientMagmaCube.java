package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientMagmaCube extends SlimeLikePet {

    public ClientMagmaCube(net.minecraft.world.World level) {
        super(level);
        this.setSize(2f, 2f);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_MAGMA_CUBE_SQUISH;
    }
}
