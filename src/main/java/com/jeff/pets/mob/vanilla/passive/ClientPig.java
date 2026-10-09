package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientPig extends GroundPet {

    public ClientPig(World level) {
        super(level);
        this.setBounds(0.9F, 0.9F);
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
    protected String getAmbientSound() {
        return "mob.pig.say";
    }
}
