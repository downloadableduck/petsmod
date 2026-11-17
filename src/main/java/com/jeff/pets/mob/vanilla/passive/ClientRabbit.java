//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.world.World;
import com.jeff.pets.mob.SlimeLikePet;

public class ClientRabbit extends SlimeLikePet {
    public ClientRabbit(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.4F, 0.5F);
        this.texture = "/mob/rabbit.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.rabbit.idle";
    }
}
