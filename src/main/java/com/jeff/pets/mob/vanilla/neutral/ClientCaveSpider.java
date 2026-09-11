package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpider extends GroundPet {
    public ClientCaveSpider(World level) {
        super(level);
        this.setBounds(0.7F, 0.5F);
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
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_SPIDER_AMBIENT;
    }
}
