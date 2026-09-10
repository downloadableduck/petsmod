package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientCreeper extends GroundPet {

    public boolean isPowered = false;

    public ClientCreeper(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
        super(entityType, level);
        this.setBounds(0.6F, 1.7F);
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
        return Sounds.ENTITY_CREEPER_PRIMED;
    }
}
