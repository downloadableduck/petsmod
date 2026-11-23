//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.neutral;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientZombiePigman extends GroundPet {
    public ClientZombiePigman(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.6F, 1.95F);
        this.texture = "/mob/pigzombie.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.zombiepig.zpig";
    }
}
