//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.neutral;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientIronGolem extends GroundPet {
    public ClientIronGolem(World world) {
        super(world);
        this.setBoundingBoxSpacing(1.4F, 2.7F);
        this.texture = "/mob/irongolem.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 3.0F;
    }

    protected String getRandomSound() {
        return "mob.irongolem.walk";
    }
}
