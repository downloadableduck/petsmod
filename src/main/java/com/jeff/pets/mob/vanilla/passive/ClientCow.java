//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientCow extends GroundPet {
    public ClientCow(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.9F, 1.4F);
        this.texture = "/mob/cow.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.cow.say";
    }
}
