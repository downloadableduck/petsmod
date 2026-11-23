//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.custom.aquatic;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.ItemStack;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;

public class Stingray extends FlyingPet {
    public static final int IS_SERVER_ENTITY = 20;
    private final float nextFlap = 1.0F;
    public float oFlap;
    public float flap;
    public float flapping = 1.0F;

    public Stingray(World world) {
        super(world);
        this.setBoundingBoxSpacing(1.0F, 0.4F);
        this.texture = "/mob/stingray/stingray.png";
    }

    public void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(20, (byte)0);
    }

    public boolean isServerEntity() {
        return this.dataTracker.getByte(10) == 1;
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.set(10, (byte)(value ? 1 : 0));
    }

    public void tickMovement() {
        super.tickMovement();
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 movement = this.getVelocity();
        if (!this.onGround && movement.y < 0.0) {
            this.setVelocity(this.xd * 1.0, this.yd * 0.6, this.zd * 1.0);
        }

        this.flap += this.flapping * 2.0F;
    }

    protected String getRandomSound() {
        return "";
    }

    protected String getHurtSound() {
        return "";
    }

    protected String getDeathSound() {
        return "";
    }

    public @Nullable Stingray makeChild(@NotNull MobAnimal partner) {
        Stingray stringray = new Stingray(this.world);
        stringray.setServerEntity(true);
        return stringray;
    }

    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return itemStack.isItemEqual(new ItemStack(Items.FOOD_FISH_RAW, 1, 0)) || itemStack.isItemEqual(new ItemStack(Items.FOOD_FISH_RAW, 1, 1)) || itemStack.isItemEqual(new ItemStack(Items.FOOD_FISH_RAW, 1, 2));
    }

    public void addAdditionalSaveData(@NotNull CompoundTag output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 0.5F;
    }

    public boolean canBreatheInWater() {
        return true;
    }

    public void tick() {
        super.tick();
        this.oFlap = this.flap;
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        this.flap += this.flapping * 2.0F;
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
            Vec3 ownerPos = Vec3.getPermanentVec3(owner.x, owner.y, owner.z).add(0.0, (double)owner.getHeadHeight() * 0.8, 0.0);
            Vec3 vecToOwner = ownerPos.vectorTo(this.getPosVec());
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
                Vec3 dir = vecToOwner.normalize();
                double speed = 0.2;
                this.yBodyRot += Math.max(-50.0F, Math.min(50.0F, this.yRot - this.yBodyRot));
                this.lerpVelocity(Vec3.getPermanentVec3(-dir.x * speed, dir.y * speed, -dir.z * speed));
            } else {
                this.lerpVelocity(Vec3.getPermanentVec3(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
            }

            int yHeightToOwner = (int)(owner.y - this.y);
            if (yHeightToOwner > 1 || this.horizontalCollision) {
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
        }

        if (owner != null && this.distanceTo(owner) >= 10.0F) {
            this.setPos(owner.x, owner.y, owner.z);
        }

        int ambient = (int)(Math.random() * 1200.0);
        if (ambient == 1) {
            this.world.playSoundEffect(null, null, this.x, this.y, this.z, "", 1.0F, 1.0F);
        }

    }
}
