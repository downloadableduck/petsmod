//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.custom.first.Duck;

import java.util.Objects;

public abstract class GroundPet extends AbstractPet {
    private int waitingTime;

    public GroundPet(World level) {
        super(level);
    }

    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            this.yaw = this.getOwner().yaw + 180.0F;
            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.dismountFromVehicle();
                    this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            double targetYaw = Math.atan2(dz, dx) * 57.29577951308232 - 90.0;
            double distance = (double)this.getDistance(owner);
            float rotation = -this.pitch;
            float rotationToOwner = rotation + -this.getOwner().pitch;
            float bodyYawDiff = Utils.wrapDegrees(this.yaw - this.bodyYaw);
            if (rotationToOwner >= 50.0F) {
                this.bodyYaw = this.yaw - Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > (double)this.stopDistance()) {
                this.walkAnimationSpeed = 0.5F;
                Vec3d targetPos = Vec3d.create(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.relativize(this.getPosVec()).normalize();
                this.bodyYaw += Math.max(-50.0F, Math.min(50.0F, this.yaw - this.bodyYaw));
                double speed = Math.min(0.23, 1.28);
                double var10001 = -dir.x * speed;
                double var10003 = -dir.z;
                this.lerpVelocity(Vec3d.create(var10001, this.getVelocity().y, var10003 * speed));
            } else if (distance < 1.5) {
                this.walkAnimationSpeed = 0.0F;
            } else {
                this.walkAnimationSpeed += 0.1F;
                this.setVelocityClient(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int)(owner.y - this.y);
            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.lerpVelocity(this.getVelocity().add(0.0, -0.01, 0.0));
            }

            if (!this.onGround) {
            }

            if (Utils.squaredDistanceToOrigin(Vec3d.create(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
                ++this.waitingTime;
                if (this.waitingTime > 30) {
                    this.wander();
                }
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float)targetYaw));
            if (Math.abs(bodyYawDiff) > 50.0F) {
                this.bodyYaw = this.yaw - Math.signum(bodyYawDiff) * 50.0F;
            } else {
                this.bodyYaw += Math.max(-10.0F, Math.min(10.0F, this.yaw - this.bodyYaw));
            }

            if (!this.onGround) {
                this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
            }
        }

        if (owner != null && this.getDistance(owner) >= 10.0F) {
            this.setPosition(owner.x, owner.y, owner.z);
        }

        int ambient = (int)(Math.random() * 1200.0);
        if (ambient == 1) {
            this.world.playSound(this.x, this.y, this.z, (String)Objects.requireNonNull(this.getRandomSound()), 1.0F, 1.0F);
        }

    }
}
