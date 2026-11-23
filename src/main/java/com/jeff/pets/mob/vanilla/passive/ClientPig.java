//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientPig extends GroundPet {
    public ClientPig(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.9F, 0.9F);
        this.texture = "/mob/pig.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "mob.pig.say";
    }
}
