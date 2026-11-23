//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.FlyingPet;

public class ClientElderGuardian extends FlyingPet {
    public ClientElderGuardian(World world) {
        super(world);
        this.setBoundingBoxSpacing(1.9975F, 1.9975F);
    }

    protected int stopDistance() {
        return 4;
    }

    protected float heartHeight() {
        return 4.0F;
    }

    protected String getRandomSound() {
        return "mob.guardian.elder.idle";
    }
}
