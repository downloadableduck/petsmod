package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;

import net.minecraft.util.MathHelper;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import net.minecraft.util.Vec3;
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
        this.setSize(0.8F, -0.8F);
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
    public void onUpdate() {
        super.onUpdate();
        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;
        if ((double) this.tentacleMovement > (Math.PI * 2D)) {
            if (this.worldObj.isRemote) {
                this.tentacleMovement = ((float) Math.PI * 2F);
            } else {
                this.tentacleMovement -= ((float) Math.PI * 2F);
                if (this.rand.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.worldObj.setEntityState(this, (byte) 19);
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

            if (!this.worldObj.isRemote) {
                this.setVelocity(this.tx * this.speed, this.ty * this.speed, this.tz * this.speed);
            }

            Vec3 vec3 = this.getVelocity();
            double d = this.horizontalDistance(vec3);
            this.bodyYaw += (-((float) Math.atan2(vec3.xCoord, vec3.zCoord)) * (180F / (float) Math.PI) - this.bodyYaw) * 0.1F;
            this.setYRot(this.bodyYaw);
            this.zBodyRot += (float) Math.PI * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float) Math.atan2(d, vec3.yCoord)) * (180F / (float) Math.PI) - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.tentacleMovement)) * (float) Math.PI * 0.25F;
            if (!this.worldObj.isRemote) {
                double e = this.getVelocity().yCoord;
                e -= 1;

                this.setVelocity(0.0F, e * (double) 0.98F, 0.0F);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        this.setVelocity(Vec3.createVectorHelper(x, y, z));
        this.tx = (float) x;
        this.ty = (float) y;
        this.tz = (float) z;
    }

    @Override
    public void setVelocity(Vec3 vec3) {
        super.setVelocity(vec3);
        double x = vec3.xCoord;
        double y = vec3.yCoord;
        double z = vec3.zCoord;
    }
}
