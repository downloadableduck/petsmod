package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientShulker extends GroundPet {
    public ClientShulker(World level) {
        super(level);
        this.setBounds(1.0F, 2.0F);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_SHULKER_AMBIENT;
    }
}
