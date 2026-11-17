//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.FlyingPet;

public class ClientGhast extends FlyingPet {
    public ClientGhast(World level) {
        super(level);
        this.setBoundingBoxSpacing(4.0F, 4.0F);
        this.texture = "/mob/ghast.png";
    }

    protected int stopDistance() {
        return 6;
    }

    protected float heartHeight() {
        return 6.0F;
    }

    protected String getRandomSound() {
        return "mob.ghast.moan";
    }
}
