package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

public class ClientChicken extends GroundPet {

    public float oFlap;
    public float flap;
    public float oFlapSpeed;
    public float flapSpeed;
    public float flapping = 1.0F;

    public ClientChicken(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.4f, 0.7f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected String func_70639_aQ() {
        return "mob.chicken.say";
    }

    @Override
    public void tick() {
        super.tick();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = net.minecraft.util.MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        this.flap += this.flapping * 2.0F;
    }
}
