package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientGhast extends FlyingPet {
    public ClientGhast(World level) {
        super(level);
        this.setBounds(4.0F, 4.0F);
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 6;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.ghast.moan";
    }
}
