//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.FlyingPet;

public class ClientBat extends FlyingPet {
    public boolean isOnHead;

    public ClientBat(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.5F, 0.9F);
        this.texture = "/mob/bat.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.bat.idle";
    }
}
