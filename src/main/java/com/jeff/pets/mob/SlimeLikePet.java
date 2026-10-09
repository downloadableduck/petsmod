package com.jeff.pets.mob;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Abstract class representing slimes or any animal that bounces up and down repeatedly,
 * including rabbits.
 */
public abstract class SlimeLikePet extends AbstractPet {
    public SlimeLikePet(World level) {
        super(level);
    }

    @Override
    public void tickMovement() {
        this.field_6748 = this.field_6749;
        this.field_6750 += this.field_6749;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().offset(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;


            double distance = MathHelper.sqrt(this.squaredDistanceTo(owner));
            float rotation = -this.pitch;
            float rotationToOwner = rotation + -this.getOwner().pitch;
            float bodyYawDiff = MathHelper.wrapDegrees(this.headYaw - this.bodyYaw);

            if (rotationToOwner >= 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > 4.0) {

                this.setLimbDistance(0.5F);

                Vec3d targetPos = Vec3d.fromXYZ(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.copyOther(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -50, 50); //m_82141949

                double speed = owner.getMovementSpeed() * 2;
                this.setVelocityClient(-dir.x * speed, this.getVelocity().y, -dir.z * speed);
            } else {
                //this.lookAtEntity(owner, 5, 0);
                this.setVelocityClient(this.velocityX * 0.8, this.velocityY, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().offset(0, -0.02, 0));
            }

            if (Utils.squaredDistanceToOrigin(Vec3d.fromXYZ(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.move(this.velocityX, this.velocityY, this.velocityZ);

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().offset(0, -0.02, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }
        if (this.getLimbDistance() > 0 && this.onGround) {
            this.jump();
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.world.playSound(this.x, this.y, this.z, this.getAmbientSound(), 1.0f, 1.0f, true);
        }
    }
}