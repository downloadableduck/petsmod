package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientCat extends GroundPet {

    public boolean isOnHead;

    public ClientCat(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 0.7f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CAT_AMBIENT;
    }
}
