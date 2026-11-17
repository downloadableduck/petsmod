//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.boss;

import net.minecraft.world.World;
import com.jeff.pets.client.Central;
import com.jeff.pets.mob.FlyingPet;

public class ClientWither extends FlyingPet {
    public ClientWither(World level) {
        super(level);
        this.setBoundingBoxSpacing(2.0F, 3.0F);
        this.texture = Central.CONFIG.witherSkin.equals("invulnerable") ? "/mob/wither_invul.png" : "/mob/wither.png";
    }

    protected int stopDistance() {
        return 6;
    }

    protected float heartHeight() {
        return 4.0F;
    }

    protected String getRandomSound() {
        return "mob.chicken.step";
    }
}
