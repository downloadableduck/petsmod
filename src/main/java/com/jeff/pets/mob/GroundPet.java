package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;

/**
 * Abstract representing any pet that cannot fly (ducks, chickens, etc).
 *
 * @see AbstractPet
 * @see FlyingPet
 */
public abstract class GroundPet extends AbstractPet {
    private int waitingTime;

    public GroundPet(World level) {
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
                    this.addVelocity(0, -0.04, 0);
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;


            double distance = MathHelper.sqrt_double(this.getDistanceSqToEntity(owner));
            float bodyYawDiff = net.minecraft.util.MathHelper.wrapAngleTo180_float(this.headYaw - this.bodyYaw);

            if (distance > this.stopDistance()) {
                this.setLimbDistance(0.5F);

                Vec3 targetPos = Vec3.createVectorHelper(owner.posX, owner.posY, owner.posZ);
                Vec3 dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.bodyYaw = this.bodyYaw + MathHelper.clamp_float(this.headYaw - this.bodyYaw, -50, 50);

                double speed = owner.getAIMoveSpeed() * 2.0;
                this.setVelocity(-dir.xCoord * speed, this.getVelocity().yCoord, -dir.zCoord * speed);
            } else if (distance >= 1.5) {
                ////this.lookAtEntity(owner, 5, 0);
                this.motionX *= 0.8;
                this.motionZ *= 0.8;
            }

            if (this.isCollidedHorizontally && this.onGround) {
                this.jump();
            }

            if (!this.onGround) {
                this.addVelocity(0, -0.04, 0);
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - Math.signum(bodyYawDiff) * 50;
            } else {
                this.bodyYaw = this.bodyYaw + MathHelper.clamp_float(this.headYaw - this.bodyYaw, -10, 10);
            }

            if (getDistanceToEntity(owner) >= 10) {
                this.setPosition(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.worldObj.playSound(this.posX, this.posY, this.posZ, this.getAmbientSound(), 1.0f, 1.0f, true);
        }
    }
}