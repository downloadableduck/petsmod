//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.hostile;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientEndermite extends GroundPet {
    public ClientEndermite(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.4F, 0.3F);
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 0.5F;
    }

    protected String getRandomSound() {
        return "";
    }
}
