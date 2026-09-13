package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;

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
    protected String func_70639_aQ() {
        return "mob.spider.say";
    }
}
