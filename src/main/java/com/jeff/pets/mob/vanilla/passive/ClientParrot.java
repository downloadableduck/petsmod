package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.util.math.MathHelper;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientParrot extends FlyingPet {

    public float flap;
    public float flapSpeed = 1.0f;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;

    public boolean isOnHead;

    public ClientParrot(World level) {
        super(level);
        this.setBounds(0.5F, 0.9F);
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
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_PARROT_AMBIENT;
    }

    @Override
    public void tickMovement() {
        this.field_6748 = this.field_6749;
        this.field_6750 += this.field_6749;
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }
    }
}
