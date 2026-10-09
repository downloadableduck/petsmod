package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientTropicalFish extends FlyingPet {

    public ClientTropicalFish(World level) {
        super(level);
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
        return "mob.squid.ambient";
    }
}
