//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.world.World;
import com.jeff.pets.mob.GroundPet;

public class ClientChicken extends GroundPet {
    public float oFlap;
    public float flap;
    public float oFlapSpeed;
    public float flapSpeed;
    public float flapping = 1.0F;

    public ClientChicken(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.4F, 0.7F);
        this.texture = "/mob/chicken.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 0.5F;
    }

    protected String getRandomSound() {
        return "mob.chicken.say";
    }

    public void tick() {
        super.tick();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Math.max(0.0F, Math.min(1.0F, this.flapSpeed));
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        this.flap += this.flapping * 2.0F;
    }
}
