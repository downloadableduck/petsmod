//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.custom.first;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
    public ServerPlayerEntity owner = (ServerPlayerEntity)this.getOwner();
    public boolean isOnHead;
    private boolean isFlapping;

    public Penguin(World level) {
        super(level);
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

    protected void initDataTracker() {
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
        Vec3d movement = this.getVelocity();
        if (!this.onGround && movement.y < 0.0) {
            this.setVelocityClient(this.velocityX * 1.0, this.velocityY * 0.6, this.velocityZ * 1.0);
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

    public @Nullable Penguin makeChild(@NotNull AnimalEntity partner) {
        Penguin penguin = new Penguin(this.world);
        penguin.setServerEntity(true);
        return penguin;
    }

    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return itemStack.isItemEqual(new ItemStack(Item.RAW_FISH, 1, 0)) || itemStack.isItemEqual(new ItemStack(Item.RAW_FISH, 1, 1)) || itemStack.isItemEqual(new ItemStack(Item.RAW_FISH, 1, 2));
    }

    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            this.yaw = this.getOwner().yaw + 180.0F;
            if (this.vehicle == owner) {
                this.isFlapping = false;
                if (owner.isSneaking() && owner.jumping) {
                    this.dismountFromVehicle();
                    this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
                    this.isOnHead = false;
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

            if (distance > 2.0) {
                this.walkAnimationSpeed = 0.5F;
                Vec3d targetPos = Vec3d.create(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.relativize(this.getPosVec()).normalize();
                this.bodyYaw += Math.max(-50.0F, Math.min(50.0F, this.yaw - this.bodyYaw));
                double speed = Math.min(0.23, 1.28);
                double var10001 = -dir.x * speed;
                double var10003 = -dir.z;
                this.lerpVelocity(Vec3d.create(var10001, this.getVelocity().y, var10003 * speed));
            } else {
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
            this.world.playSound(this.x, this.y, this.z, "penguin_ambient", 1.0F, 1.0F);
        }

    }

    public void writeNbt(@NotNull NbtCompound output) {
        super.writeNbt(output);
        output.putBoolean("isServerEntity", true);
    }

    public void readNbt(@NotNull NbtCompound input) {
        super.readNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}
