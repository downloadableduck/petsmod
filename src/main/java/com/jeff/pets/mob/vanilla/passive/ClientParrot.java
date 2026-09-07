package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

@CanFly
public class ClientParrot extends FlyingPet {

    public float flap;
    public float flapSpeed = 1.0f;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;

    public boolean isOnHead;

    public ClientParrot(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.5f, 0.9f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PARROT_AMBIENT;
    }

    @Override
    public void tick() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = net.minecraft.util.math.MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }
    }
}
