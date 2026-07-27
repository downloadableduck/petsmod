package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

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


    public ClientSquid(EntityType<? extends net.minecraft.entity.passive.TameableEntity> entityType, net.minecraft.world.World level) {
        super(entityType, level);
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SQUID_AMBIENT;
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
            if (this.level.isClientSide) {
                this.tentacleMovement = ((float) Math.PI * 2F);
            } else {
                this.tentacleMovement -= ((float) Math.PI * 2F);
                if (this.random.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.level.broadcastEntityEvent(this, (byte) 19);
            }
        }

        if (this.isInWaterOrBubble()) {
            if (this.tentacleMovement < (float) Math.PI) {
                float f = this.tentacleMovement / (float) Math.PI;
                this.tentacleAngle = net.minecraft.util.math.MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
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

            if (!this.level.isClientSide) {
                this.setDeltaMovement(this.tx * this.speed, this.ty * this.speed, this.tz * this.speed);
            }

            net.minecraft.util.math.Vec3d vec3 = this.getDeltaMovement();
            double d = this.horizontalDistance(vec3);
            this.yBodyRot += (-((float) net.minecraft.util.math.MathHelper.atan2(vec3.x, vec3.z)) * (180F / (float) Math.PI) - this.yBodyRot) * 0.1F;
            this.setYRot(this.yBodyRot);
            this.zBodyRot += (float) Math.PI * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float) net.minecraft.util.math.MathHelper.atan2(d, vec3.y)) * (180F / (float) Math.PI) - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = net.minecraft.util.math.MathHelper.abs(net.minecraft.util.math.MathHelper.sin(this.tentacleMovement)) * (float) Math.PI * 0.25F;
            if (!this.level.isClientSide) {
                double e = this.getDeltaMovement().y;
                if (this.hasEffect(Effects.LEVITATION)) {
                    e = 0.05 * (double) (this.getEffect(Effects.LEVITATION).getAmplifier() + 1);
                } else {
                    e -= 1;
                }

                this.setDeltaMovement(0.0F, e * (double) 0.98F, 0.0F);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }
    }

    @Override
    public void setDeltaMovement(double x, double y, double z) {
        this.setDeltaMovement(new net.minecraft.util.math.Vec3d(x, y, z));
        this.tx = (float) x;
        this.ty = (float) y;
        this.tz = (float) z;
    }

    @Override
    public void setDeltaMovement(net.minecraft.util.math.Vec3d vec3) {
        super.setDeltaMovement(vec3);
        double x = vec3.x;
        double y = vec3.y;
        double z = vec3.z;
    }
}
