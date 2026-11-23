//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.custom.first;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;

import java.util.Objects;

public class Racoon extends AbstractPet {
    public static final int IS_SERVER_ENTITY = 20;
    public boolean isOnHead;

    public Racoon(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.6F, 0.7F);
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 0.8F;
    }

    protected String getRandomSound() {
        return "mob.chicken.step";
    }

    public void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(20, (byte)0);
    }

    public boolean isServerEntity() {
        return this.dataTracker.getByte(20) != 0;
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.set(20, (byte)(value ? 1 : 0));
    }

    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return false;
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
                    this.isOnHead = false;
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

            if (distance > 2.0) {
                this.walkAnimSpeed = 0.5F;
                Vec3 targetPos = Vec3.getPermanentVec3(owner.x, owner.y, owner.z);
                Vec3 dir = targetPos.vectorTo(this.getPosVec()).normalize();
                this.yBodyRot += Math.max(-50.0F, Math.min(50.0F, this.yRot - this.yBodyRot));
                double speed = Math.min(0.23, 1.28);
                double var10001 = -dir.x * speed;
                double var10003 = -dir.z;
                this.lerpVelocity(Vec3.getPermanentVec3(var10001, this.getVelocity().y, var10003 * speed));
            } else {
                this.setVelocity(this.xd * 0.8, this.yd * 1.0, this.zd * 0.8);
            }

            int yHeightToOwner = (int)(owner.y - this.y);
            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.lerpVelocity(this.getVelocity().add(0.0, -0.01, 0.0));
            }

            if (Utils.squaredDistanceToOrigin(Vec3.getPermanentVec3(owner.xd, owner.yd, owner.zd)) < 0.01) {
                ++this.waitingTime;
                if (this.waitingTime > 30) {
                    this.wander();
                }
            } else {
                this.waitingTime = 0;
            }

            if (!this.onGround) {
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

    }

    public MobAnimal makeChild(@NotNull MobAnimal AgableMob) {
        Racoon racoon = new Racoon(this.world);
        racoon.setServerEntity(false);
        return racoon;
    }

    public String getTexture() {
        String racoonTexturePath;
        if (!this.isServerEntity()) {
            if (Objects.equals(Central.CONFIG.racoonSkin, "normal")) {
                racoonTexturePath = "/mob/racoon/racoon.png";
            } else if (Objects.equals(Central.CONFIG.racoonSkin, "albino")) {
                racoonTexturePath = "/mob/racoon/albino.png";
            } else {
                racoonTexturePath = "/mob/racoon/racoon.png";
            }
        } else {
            racoonTexturePath = "/mob/racoon/racoon.png";
        }

        return racoonTexturePath;
    }
}
