package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.util.math.MathHelper;


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


    public ClientSquid(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.8f, 0.8f);
        this.texture = "/mob/squid.png";
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
    protected String getAmbientSound() {
        return "mob.squid.ambient";
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
            if (this.world.isMultiplayer) {
                this.tentacleMovement = ((float) Math.PI * 2F);
            } else {
                this.tentacleMovement -= ((float) Math.PI * 2F);
                if (this.random.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.doEntityEvent(this, (byte) 19);
            }
        }

        if (this.isInWater()) {
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

            if (!this.world.isMultiplayer) {
                this.lerpVelocity(this.tx * this.speed, this.ty * this.speed, this.tz * this.speed);
            }

            double d = Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            this.yaw += (-((float) Math.atan2(this.velocityX, this.velocityZ)) * (180F / (float) Math.PI) - this.yaw) * 0.1F;
            this.setYRot(this.yaw);
            this.zBodyRot += (float) Math.PI * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float) Math.atan2(d, this.velocityY)) * (180F / (float) Math.PI) - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.tentacleMovement)) * (float) Math.PI * 0.25F;
            if (!this.world.isMultiplayer) {
                double e = this.velocityY;
                e -= 1;

                this.lerpVelocity(0.0F, e * (double) 0.98F, 0.0F);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }
    }

    @Override
    public void lerpVelocity(double x, double y, double z) {
        super.lerpVelocity(x, y, z);
        this.tx = (float) x;
        this.ty = (float) y;
        this.tz = (float) z;
    }
}
