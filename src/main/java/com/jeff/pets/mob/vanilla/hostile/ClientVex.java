package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientVex extends FlyingPet {
    public ClientVex(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.4f, 0.8f);
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
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }
}
