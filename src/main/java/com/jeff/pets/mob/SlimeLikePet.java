package com.jeff.pets.mob;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.custom.first.Duck;

import net.minecraft.entity.EntityLiving;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;

/**
 * Abstract class representing slimes or any animal that bounces up and down repeatedly,
 * including rabbits.
 */
public abstract class SlimeLikePet extends AbstractPet {
    public SlimeLikePet(World level) {
        super(level);
    }

    @Override
    public void onLivingUpdate() {
        this.prevLimbSwingAmount = this.limbSwingAmount;
        this.limbSwing += this.limbSwingAmount;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.ridingEntity == owner) {
                if (owner.isSneaking() && owner.isJumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().addVector(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;


            double distance = MathHelper.sqrt_double(this.getDistanceSqToEntity(owner));
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + -this.getOwner().rotationPitch;
            float bodyYawDiff = net.minecraft.util.MathHelper.wrapAngleTo180_float(this.headYaw - this.bodyYaw);

            if (rotationToOwner >= 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > 4.0) {

                this.setLimbDistance(0.5F);

                Vec3 targetPos = Vec3.createVectorHelper(owner.posX, owner.posY, owner.posZ);
                Vec3 dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp_float(this.headYaw - this.bodyYaw /*bodyYaw*/, -50, 50); //m_82141949

                double speed = owner.getAIMoveSpeed() * 2;
                this.setVelocity(-dir.xCoord * speed, this.getVelocity().yCoord, -dir.zCoord * speed);
            } else {
                //this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1 && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().addVector(0, -0.02, 0));
            }

            if (Utils.squaredDistanceToOrigin(Vec3.createVectorHelper(owner.motionX, owner.motionY, owner.motionZ)) < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp_float(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().addVector(0, -0.02, 0));
            }
        }
        if (owner != null) {
            if (getDistanceToEntity(owner) >= 10) {
                this.requestTeleport(owner.posX, owner.posY, owner.posZ);
            }
        }
        if (this.getLimbDistance() > 0 && this.onGround) {
            this.jump();
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.worldObj.playSound(this.posX, this.posY, this.posZ, this.getAmbientSound(), 1.0f, 1.0f, true);
        }
    }
}