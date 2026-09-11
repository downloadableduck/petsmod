package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.effect.StatusEffects;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientSquid extends FlyingPet {

    public float xBodyRotO;
    public float xBodyRot;
    public float zBodyRotO;
    public float zBodyRot;
    public float tentacleMovement;
    public float oldTentacleMovement;
    public float tentacleAngle;
    public float oldTentacleAngle;
    private float speed;
    private float tentacleSpeed;
    private float rotateSpeed;
    private float tx;
    private float ty;
    private float tz;


    public ClientSquid(World level) {
        super(level);
        this.setBounds(0.8F, -0.8F);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_SQUID_AMBIENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;
        if ((double) this.tentacleMovement > (Math.PI * 2D)) {
            if (this.world.isClient) {
                this.tentacleMovement = ((float) Math.PI * 2F);
            } else {
                this.tentacleMovement -= ((float) Math.PI * 2F);
                if (this.random.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.sendEntityStatus(this, (byte) 19);
            }
        }

        if (this.isTouchingWater()) {
            if (this.tentacleMovement < (float) Math.PI) {
                float f = this.tentacleMovement / (float) Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
                if ((double) f > (double) 0.75F) {
                    this.speed = 1.0F;
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.speed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.world.isClient) {
                this.setVelocity(this.tx * this.speed, this.ty * this.speed, this.tz * this.speed);
            }

            Vec3d vec3 = this.getVelocity();
            double d = this.horizontalDistance(vec3);
            this.bodyYaw += (-((float) MathHelper.atan2(vec3.x, vec3.z)) * (180F / (float) Math.PI) - this.bodyYaw) * 0.1F;
            this.setYRot(this.bodyYaw);
            this.zBodyRot += (float) Math.PI * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float) MathHelper.atan2(d, vec3.y)) * (180F / (float) Math.PI) - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.tentacleMovement)) * (float) Math.PI * 0.25F;
            if (!this.world.isClient) {
                double e = this.getVelocity().y;
                if (this.hasStatusEffect(StatusEffects.LEVITATION)) {
                    e = 0.05 * (double) (this.getEffectInstance(StatusEffects.LEVITATION).getAmplifier() + 1);
                } else {
                    e -= 1;
                }

                this.setVelocity(0.0F, e * (double) 0.98F, 0.0F);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        this.setVelocity(new Vec3d(x, y, z));
        this.tx = (float) x;
        this.ty = (float) y;
        this.tz = (float) z;
    }

    @Override
    public void setVelocity(Vec3d vec3) {
        super.setVelocity(vec3);
        double x = vec3.x;
        double y = vec3.y;
        double z = vec3.z;
    }
}
