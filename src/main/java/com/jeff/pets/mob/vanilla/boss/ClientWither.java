package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.init.SoundEvents;

@CanFly
public class ClientWither extends FlyingPet {
    public ClientWither(net.minecraft.world.World level) {
        super(level);
        this.setSize(2f, 3f);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_STEP;
    }
}
