package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

/**
 * Abstract class representing any pet tht can fly (ghasts, vexes, etc). Contains custom movement
 * logic that allow the pets to move on the client side.
 *
 * @see AbstractPet
 * @see GroundPet
 */
public abstract class FlyingPet extends AbstractPet {

    protected FlyingPet(net.minecraft.world.World level) {
        super(level);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.ridingEntity == owner) {
                if (owner.isSneaking() && !owner.onGround) {
                    this.ridingEntity = null;
                    this.setVelocity(this.motionX, this.motionY + 0.1, this.motionZ);
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;
            net.minecraft.util.Vec3 ownerPos = owner.getPosition(1.0F).addVector(0, owner.getEyeHeight() * 0.8, 0);
            net.minecraft.util.Vec3 vecToOwner = ownerPos.subtract(this.getPosition(1.0F));
            Vec3 dir = vecToOwner.normalize();

            float targetYaw = (float) (Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;

            double distance = this.getDistance(owner);
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + (-this.getOwner().rotationPitch);
            float bodyYawDiff = net.minecraft.util.MathHelper.wrapAngleTo180_float(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.limbSwingAmount = (0.5F);

                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp_float(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                this.setVelocity(-dir.xCoord * speed, dir.yCoord * speed, -dir.zCoord * speed);
            } else {

                this.setVelocity(this.motionX * 0.8, this.motionY * 0.8, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1 || this.isCollidedHorizontally) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.motionX, this.motionY - 0.01, this.motionZ);
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (owner.motionX * owner.motionX + owner.motionY * owner.motionY + owner.motionZ * owner.motionZ < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp_float(this.rotationYawHead - this.renderYawOffset, -10, 10);
            }

        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.playSound("mob.squid.ambient", 1.0f, 1.0f);
        }
    }
}
