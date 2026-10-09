package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientMooshroom extends GroundPet {

    public ClientMooshroom(World level) {
        super(level);
        this.setBounds(0.9F, 1.4F);
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
        return "mob.cow.say";
    }
}
