//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientSheep extends GroundPet {
    public ClientSheep(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.9F, 1.3F);
        this.texture = "/mob/sheep.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.sheep.say";
    }
}
