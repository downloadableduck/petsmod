package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

@CanFly
public class ClientBat extends FlyingPet {

    public boolean isOnHead;

    public ClientBat(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.5f, 0.9f);
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
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }
}
