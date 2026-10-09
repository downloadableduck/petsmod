package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientSpider extends GroundPet {
    public ClientSpider(World level) {
        super(level);
        this.setBounds(1.4F, 0.9F);
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
    protected String getAmbientSound() {
        return "mob.spider.say";
    }
}
