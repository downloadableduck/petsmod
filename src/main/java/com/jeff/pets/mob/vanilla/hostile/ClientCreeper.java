//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientCreeper extends GroundPet {
    public boolean isPowered = false;

    public ClientCreeper(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.6F, 1.7F);
        this.texture = "/mob/creeper.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "creeper.primed";
    }
}
