package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientHorse extends GroundPet {

    public ClientHorse(World level) {
        super(level);
        this.setBounds(1.3965F, 1.6F);
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
    protected String getAmbientSound() {
        return "mob.horse.idle";
    }
}
