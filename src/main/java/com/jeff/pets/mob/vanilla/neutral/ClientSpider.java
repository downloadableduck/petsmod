//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.neutral;

import net.minecraft.core.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientSpider extends GroundPet {
    public ClientSpider(World world) {
        super(world);
        this.setBoundingBoxSpacing(1.4F, 0.9F);
        this.texture = "/mob/spider.png";
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
