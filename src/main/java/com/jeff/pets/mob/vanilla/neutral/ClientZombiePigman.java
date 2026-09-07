package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientZombiePigman extends GroundPet {
    public ClientZombiePigman(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 1.95f);
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
        return SoundEvents.ENTITY_ZOMBIE_PIGMAN_AMBIENT;
    }
}
