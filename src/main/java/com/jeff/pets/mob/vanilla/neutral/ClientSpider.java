package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;

public class ClientSpider extends GroundPet {
    public ClientSpider(net.minecraft.world.World level) {
        super(level);
        this.setSize(1.4f, 0.9f);
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
    protected String func_70639_aQ() {
        return "mob.spider.say";
    }
}
