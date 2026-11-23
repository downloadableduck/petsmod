//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.custom.first.Duck;

import java.util.Objects;

public abstract class FlyingPet extends AbstractPet {
    protected FlyingPet(World world) {
        super(world);
    }

    public void tick() {
        super.tick();
        Mob owner = this.getOwner();
        if (owner != null) {
            this.yRot = this.getOwner().yRot + 180.0F;
            if (this.vehicle == owner) {
                if (owner.isSneaking() && false) {
                    this.ejectRider();
                    this.lerpVelocity(this.getVelocity().add(0.0, 0.1, 0.0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3 ownerPos = Vec3.getPermanentVec3(owner.x, owner.y + (double)owner.getHeadHeight() * 0.8, owner.z);
            Vec3 vecToOwner = Vec3.getPermanentVec3(ownerPos.x - this.x, ownerPos.y - this.y, ownerPos.z - this.z);
            double targetYaw = Math.atan2(dz, dx) * 57.29577951308232 - 90.0;
            double distance = (double)this.distanceTo(owner);
            float rotation = -this.xRot;
            float rotationToOwner = rotation + -this.getOwner().xRot;
            float bodyYawDiff = Utils.wrapDegrees(this.yRot - this.yBodyRot);
            if (rotationToOwner >= 50.0F) {
                this.yBodyRot = this.yRot - Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > (double)this.stopDistance()) {
                this.walkAnimSpeed = 0.5F;
                Vec3 dir = vecToOwner.normalize();
                double speed = 0.2;
                System.out.println(speed);
                this.yBodyRot += Math.max(-50.0F, Math.min(50.0F, this.yRot - this.yBodyRot));
                this.lerpVelocity(Vec3.getPermanentVec3(-dir.x * speed, dir.y * speed, -dir.z * speed));
            } else {
                this.lerpVelocity(Vec3.getPermanentVec3(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
            }

            int yHeightToOwner = (int)(owner.y - this.y);
            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jump();
            }

            if (!this.onGround) {
            }

            if (Utils.squaredDistanceToOrigin(Vec3.getPermanentVec3(owner.xd, owner.yd, owner.zd)) < 0.01) {
                ++this.waitingTime;
                if (this.waitingTime > 30) {
                    this.wander();
                }
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float)targetYaw));
            if (Math.abs(bodyYawDiff) > 50.0F) {
                this.yBodyRot = this.yRot - Math.signum(bodyYawDiff) * 50.0F;
            } else {
                this.yBodyRot += Math.max(-10.0F, Math.min(10.0F, this.yRot - this.yBodyRot));
            }
        }

        if (owner != null && this.distanceTo(owner) >= 10.0F) {
            this.setPos(owner.x, owner.y, owner.z);
        }

        int ambient = (int)(Math.random() * 1200.0);
        if (ambient == 1) {
            this.world.playSoundEffect(null, null, this.x, this.y, this.z, (String)Objects.requireNonNull(this.getRandomSound()), 1.0F, 1.0F);
        }

    }
}
