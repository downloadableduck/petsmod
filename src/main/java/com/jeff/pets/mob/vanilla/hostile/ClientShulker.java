//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientShulker extends GroundPet {
    public ClientShulker(World world) {
        super(world);
        this.setBoundingBoxSpacing(1.0F, 2.0F);
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "";
    }
}
