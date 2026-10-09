package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientElderGuardian extends FlyingPet {
    public ClientElderGuardian(World level) {
        super(level);
        this.setBounds(1.9975F, 1.9975F);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.elderguardian.idle";
    }
}
