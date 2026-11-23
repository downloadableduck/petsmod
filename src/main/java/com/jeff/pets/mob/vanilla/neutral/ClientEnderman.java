//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.neutral;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientEnderman extends GroundPet {
    public ClientEnderman(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.6F, 2.9F);
        this.texture = "/mob/enderman.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 3.0F;
    }

    protected String getRandomSound() {
        return "mob.endermen.idle";
    }
}
