//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.custom.first;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.ItemStack;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;

public class Penguin extends AbstractPet {
    public static final int IS_SERVER_ENTITY = 20;
    private final float nextFlap = 1.0F;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public Player owner = (Player)this.getOwner();
    public boolean isOnHead;
    private boolean isFlapping;

    public Penguin(World world) {
        super(world);
        this.isFlapping = !this.onGround;
        this.setBoundingBoxSpacing(0.7F, 1.0F);
        this.texture = "/mob/penguin/penguin.png";
    }

    protected int stopDistance() {
        return 0;
    }

    protected float heartHeight() {
        return 1.3F;
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
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Math.max(0.0F, Math.min(1.0F, this.flapSpeed));
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

    protected boolean isFlapping() {
        return this.isFlapping;
    }

    protected void onFlap() {
    }

    protected String getRandomSound() {
        return "penguin_ambient";
    }

    protected String getHurtSound() {
        return "penguin_ambient";
    }

    protected String getDeathSound() {
        return "penguin_ambient";
    }

    public @Nullable Penguin makeChild(@NotNull MobAnimal partner) {
        Penguin penguin = new Penguin(this.world);
        penguin.setServerEntity(true);
        return penguin;
    }

    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return itemStack.isItemEqual(new ItemStack(Items.FOOD_FISH_RAW, 1, 0)) || itemStack.isItemEqual(new ItemStack(Items.FOOD_FISH_RAW, 1, 1)) || itemStack.isItemEqual(new ItemStack(Items.FOOD_FISH_RAW, 1, 2));
    }

    public void tick() {
        super.tick();
        Mob owner = this.getOwner();
        if (owner != null) {
            this.yRot = this.getOwner().yRot + 180.0F;
            if (this.vehicle == owner) {
                this.isFlapping = false;
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
            this.world.playSoundEffect(null, null, this.x, this.y, this.z, "penguin_ambient", 1.0F, 1.0F);
        }

    }

    public void addAdditionalSaveData(@NotNull CompoundTag output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}
