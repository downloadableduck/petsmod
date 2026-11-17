//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientGuardian extends GroundPet {
    public ClientGuardian(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.85F, 0.85F);
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "mob.guardian.ambient";
    }
}
