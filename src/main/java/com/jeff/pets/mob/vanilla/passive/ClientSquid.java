//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import com.jeff.pets.mob.FlyingPet;

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

    public ClientSquid(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.8F, 0.8F);
        this.texture = "/mob/squid.png";
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 2.0F;
    }

    protected String getRandomSound() {
        return "mob.squid.ambient";
    }

    public void tick() {
        super.tick();
        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;
        if ((double)this.tentacleMovement > 6.283185307179586) {
            if (this.world.isClientSide) {
                this.tentacleMovement = 6.2831855F;
            } else {
                this.tentacleMovement -= 6.2831855F;
                if (this.random.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                // world.broadcastEntityEvent(this, (byte)19);
            }
        }

        double d;
        if (this.isInWater()) {
            if (this.tentacleMovement < 3.1415927F) {
                float f = this.tentacleMovement / 3.1415927F;
                this.tentacleAngle = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double)f > 0.75) {
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

            if (!this.world.isClientSide) {
                this.setVelocity((double)(this.tx * this.speed), (double)(this.ty * this.speed), (double)(this.tz * this.speed));
            }

            d = Math.sqrt(this.xd * this.xd + this.zd * this.zd);
            this.yRot += (-((float)Math.atan2(this.xd, this.zd)) * 57.295776F - this.yRot) * 0.1F;
            this.setYRot(this.yRot);
            this.zBodyRot += 3.1415927F * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float)Math.atan2(d, this.yd)) * 57.295776F - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.tentacleMovement)) * 3.1415927F * 0.25F;
            if (!this.world.isClientSide) {
                d = this.yd;
                --d;
                this.setVelocity(0.0, d * 0.9800000190734863, 0.0);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }

    }

    public void setVelocity(double x, double y, double z) {
        super.setVelocity(x, y, z);
        this.tx = (float)x;
        this.ty = (float)y;
        this.tz = (float)z;
    }
}
