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

public abstract class FlyingPet extends AbstractPet {
    protected FlyingPet(World level) {
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
                    this.lerpVelocity(this.getVelocity().add(0.0, 0.1, 0.0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = Vec3d.create(owner.x, owner.y, owner.z).add(0.0, (double)owner.getEyeHeight() * 0.8, 0.0);
            Vec3d vecToOwner = ownerPos.relativize(this.getPosition(1.0F));
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
                Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;
                System.out.println(speed);
                this.bodyYaw += Math.max(-50.0F, Math.min(50.0F, this.yaw - this.bodyYaw));
                this.lerpVelocity(Vec3d.create(-dir.x * speed, dir.y * speed, -dir.z * speed));
            } else {
                this.lerpVelocity(Vec3d.create(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
            }

            int yHeightToOwner = (int)(owner.y - this.y);
            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jump();
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
