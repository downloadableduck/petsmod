//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientDonkey extends GroundPet {
    public ClientDonkey(World level) {
        super(level);
        this.setBoundingBoxSpacing(1.3965F, 1.5F);
        this.texture = "/mob/donkey.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 3.0F;
    }

    protected String getRandomSound() {
        return "mob.horse.donkey.idle";
    }
}
