//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientSilverfish extends GroundPet {
    public ClientSilverfish(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.4F, 0.3F);
        this.texture = "/mob/silverfish.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.silverfish.say";
    }
}
