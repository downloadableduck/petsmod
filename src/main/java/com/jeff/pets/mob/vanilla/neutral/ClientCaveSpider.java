package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ClientCaveSpider extends GroundPet {
    public ClientCaveSpider(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.7f, 0.5f);
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
        return SoundEvents.ENTITY_SPIDER_AMBIENT;
    }
}
