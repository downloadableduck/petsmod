package com.jeff.pets.mob;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;

/**
 * Abstract class representing slimes or any animal that bounces up and down repeatedly,
 * including rabbits.
 */
public abstract class SlimeLikePet extends AbstractPet {
    public SlimeLikePet(net.minecraft.world.World level) {
        super(level);
    }

    @Override
    public void tick() {
        super.tick();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (owner.isRidingOrBeingRiddenBy(this)) {
                if (owner.isSneaking() && Utils.isJumping(owner)) {
                    this.stopRiding();
                    this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.getDistance(owner);
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + (-this.getOwner().rotationPitch);
            float bodyYawDiff = net.minecraft.util.math.MathHelper.wrapDegrees(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 4.0) {

                this.limbSwingAmount = (0.5F);

                net.minecraft.util.math.Vec3d targetPos = owner.getPositionVector();
                net.minecraft.util.math.Vec3d dir = targetPos.subtract(this.getPositionVector()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + net.minecraft.util.math.MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                double speed = owner.getAIMoveSpeed() * 2;
                this.setVelocity(dir.x * speed, this.motionY, dir.z * speed);
            } else {

                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1 && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.motionX, this.motionY - 0.02, this.motionZ);
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
                this.renderYawOffset = this.renderYawOffset + net.minecraft.util.math.MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -10, 10);
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

            if (!this.onGround) {
                this.setVelocity(this.motionX, this.motionY - 0.02, this.motionZ);
            }
        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }
        if (this.limbSwingAmount > 0 && this.onGround) {
            this.jump();
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            //this.world.playLocalSound(this.posX, this.posY, this.posZ, Objects.requireNonNull(this.getAmbientSound()), SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
