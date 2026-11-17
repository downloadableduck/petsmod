//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.neutral;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientCaveSpider extends GroundPet {
    public ClientCaveSpider(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.7F, 0.5F);
        this.texture = "/mob/cavespider.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "mob.spider.say";
    }
}
