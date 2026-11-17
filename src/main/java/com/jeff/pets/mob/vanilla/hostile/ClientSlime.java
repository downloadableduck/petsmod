//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.SlimeLikePet;

public class ClientSlime extends SlimeLikePet {
    public ClientSlime(World level) {
        super(level);
        this.setBoundingBoxSpacing(2.0F, 2.0F);
        this.texture = "/mob/slime.png";
    }

    protected int stopDistance() {
        return 4;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "mob.slime.small";
    }
}
