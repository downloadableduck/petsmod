//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientWitherSkeleton extends GroundPet {
    public ClientWitherSkeleton(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.6F, 1.95F);
        this.texture = "/mob/skeleton_wither.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "mob.skeleton.say";
    }
}
