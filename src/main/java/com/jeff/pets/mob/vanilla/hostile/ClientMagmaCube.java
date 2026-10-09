package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ClientMagmaCube extends SlimeLikePet {

    public ClientMagmaCube(World level) {
        super(level);
        this.setBounds(2.0F, 2.0F);
    }

    @Override
    protected int stopDistance() {
        return 4;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.magmacube.jump";
    }
}
