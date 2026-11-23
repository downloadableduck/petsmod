//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientHorse extends GroundPet {
    public ClientHorse(World world) {
        super(world);
        this.setBoundingBoxSpacing(1.3965F, 1.6F);
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 3.0F;
    }

    protected String getRandomSound() {
        return "mob.horse.idle";
    }
}
