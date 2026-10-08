package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;


import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientDolphin extends FlyingPet {
    public ClientDolphin(World level) {
        super(level);
        this.setSize(0.9F, 0.6F);
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
        return "game.player.swim";
    }
}
