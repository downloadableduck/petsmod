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

public abstract class GroundPet extends AbstractPet {
    private int waitingTime;

    public GroundPet(World world) {
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
                    this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
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
                Vec3 targetPos = Vec3.getPermanentVec3(owner.x, owner.y, owner.z);
                Vec3 dir = targetPos.vectorTo(this.getPosVec()).normalize();
                this.yBodyRot += Math.max(-50.0F, Math.min(50.0F, this.yRot - this.yBodyRot));
                double speed = Math.min(0.23, 1.28);
                double var10001 = -dir.x * speed;
                double var10003 = -dir.z;
                this.lerpVelocity(Vec3.getPermanentVec3(var10001, this.getVelocity().y, var10003 * speed));
            } else if (distance < 1.5) {
                this.walkAnimSpeed = 0.0F;
            } else {
                this.walkAnimSpeed += 0.1F;
                this.setVelocity(this.xd * 0.8, this.yd * 1.0, this.zd * 0.8);
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

            if (!this.onGround) {
                this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
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
