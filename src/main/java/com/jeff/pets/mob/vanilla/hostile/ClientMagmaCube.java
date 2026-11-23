//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.SlimeLikePet;

public class ClientMagmaCube extends SlimeLikePet {
    public ClientMagmaCube(World world) {
        super(world);
        this.setBoundingBoxSpacing(2.0F, 2.0F);
        this.texture = "/mob/lava.png";
    }

    protected int stopDistance() {
        return 4;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "mob.magmacube.small";
    }
}
