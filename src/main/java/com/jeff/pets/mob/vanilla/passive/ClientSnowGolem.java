package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientSnowGolem extends GroundPet {

    public ClientSnowGolem(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
        super(entityType, level);
        this.setBounds(0.7F, 1.9F);
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
        return Sounds.ENTITY_SNOW_GOLEM_AMBIENT;
    }
}
