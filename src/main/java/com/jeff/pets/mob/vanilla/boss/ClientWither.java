package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientWither extends FlyingPet {
    public ClientWither(World level) {
        super(level);
        this.setBounds(2.0F, 3.0F);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.chicken.step";
    }
}
