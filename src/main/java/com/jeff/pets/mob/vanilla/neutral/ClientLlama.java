package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientLlama extends GroundPet {
    public ClientLlama(World level) {
        super(level);
        this.setBounds(0.9F, 1.87F);
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
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_LLAMA_AMBIENT;
    }
}
