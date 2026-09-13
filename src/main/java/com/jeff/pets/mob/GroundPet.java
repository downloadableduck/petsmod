package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityLivingBase;

import net.minecraft.util.Vec3;

/**
 * Abstract representing any pet that cannot fly (ducks, chickens, etc).
 *
 * @see AbstractPet
 * @see FlyingPet
 */
public abstract class GroundPet extends AbstractPet {
    private int waitingTime;

    public GroundPet(net.minecraft.world.World level) {
        super(level);
    }

    @Override
    public void tick() {
        super.tick();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.field_70154_o == owner) {
                if (owner.isSneaking() && owner.isJumping) {
                    this.func_70078_a(null);
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
            float bodyYawDiff = net.minecraft.util.MathHelper.wrapDegrees(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > this.stopDistance()) {

                this.limbSwingAmount = (0.5F);

                Vec3 targetPos = owner.getPositionVector();
                Vec3 dir = targetPos.subtract(this.getPositionVector()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + net.minecraft.util.MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                double speed = owner.getAIMoveSpeed() * 2.0;
                this.setVelocity(dir.x * speed, this.motionY, dir.z * speed);
            } else if (distance < 1.5) {
                this.limbSwingAmount = (0);
            } else {

                this.limbSwingAmount = (this.limbSwingAmount + 0.1f);
                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (this.collidedHorizontally && this.onGround) {
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
                this.renderYawOffset = this.renderYawOffset + net.minecraft.util.MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -10, 10);
            }

            this.move(this.motionX, this.motionY, this.motionZ);

            if (!this.onGround) {
                this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
            }
        } else {
            super.tick();
        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            //this.world.playLocalSound(this.posX, this.posY, this.posZ, Objects.requireNonNull(this.func_70639_aQ()), SoundCategory.NEUTRAL, 1.0f, 1.0f, true);
        }
    }
}
