package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientVindicator extends GroundPet {

    public ClientVindicator(World level) {
        super(level);
        this.setBounds(0.6F, 1.95F);
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
        return Sounds.ENTITY_VINDICATION_ILLAGER_AMBIENT;
    }
}
