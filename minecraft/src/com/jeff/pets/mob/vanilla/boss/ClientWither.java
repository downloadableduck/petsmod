package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

import static com.jeff.pets.client.Central.CONFIG;

@CanFly
public class ClientWither extends FlyingPet {
    public ClientWither(net.minecraft.src.World level) {
        super(level);
        this.setSize(2f, 3f);
        this.texture = CONFIG.witherSkin.equals("invulnerable") ? "/mob/wither_invul.png" : "/mob/wither.png";
    }

    @Override
    protected int stopDistance() {
        return 6;
    }

    @Override
    protected float heartHeight() {
        return 4;
    }

    @Override
    protected String getLivingSound() {
        return "mob.chicken.step";
    }
}
