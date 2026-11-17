//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.mob.custom.first.Duck;

public abstract class AbstractPet extends AnimalEntity {
    protected int waitingTime = 0;
    private boolean isReturningToOwner = false;
    private float randomX = (float)(Math.random() - 1.0);
    private float randomZ = (float)(Math.random() - 1.0);
    private String ownerName;
    private boolean sitting;

    protected AbstractPet(World level) {
        super(level);
        this.setSpeed(0.4F);
        this.health = 8;
    }

    protected boolean aiEnabled() {
        return false;
    }

    public void tickLiving() {
        if (!this.aiEnabled()) {
            super.tickLiving();
        }

    }

    protected abstract int stopDistance();

    protected abstract float heartHeight();

    protected abstract String getRandomSound();

    public boolean interact(@NotNull PlayerEntity player) {
        ItemStack itemStack = player.getHand();
        if (this.isTamed() && itemStack == null && !player.isSneaking()) {
            this.world.addParticle("heart", this.x, this.y + (double)this.heartHeight(), this.z, 0.0, 0.0, 0.0);
            return true;
        } else if (this.isTamed() && itemStack == null && player.isSneaking()) {
            if (!this.hasVehicle()) {
                this.setVehicle(player);
                this.lookAt(player, 1.0F, 1.0F);
                return true;
            } else {
                this.dismountFromVehicle();
                return true;
            }
        } else {
            return super.interact(player);
        }
    }

    protected boolean isBreedingItem(ItemStack itemStack) {
        return false;
    }

    protected AnimalEntity makeChild(AnimalEntity mate) {
        return null;
    }

    public void setName(String string) {
    }

    public void wander() {
        float speed = 0.08F;
        float z = speed * this.randomZ;
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            float distance = this.getDistance(owner);
            float yVelo = (float)this.getVelocity().y;
            if (distance > 5.0F) {
                this.reCalcPos();
                this.isReturningToOwner = true;
            } else if (distance < 2.0F) {
                this.reCalcPos();
                this.isReturningToOwner = false;
            }

            if (!this.isReturningToOwner) {
                this.lerpVelocity(Vec3d.create((double)speed, (double)yVelo, (double)z));
            } else {
                this.lerpVelocity(Vec3d.create((double)(-speed), (double)yVelo, (double)(-z)));
            }

            double moveX = this.getVelocity().x;
            double moveZ = this.getVelocity().z;
            Vec3d lookDir = Vec3d.create(this.x + moveX * 2.0, this.y + (double)this.getEyeHeight(), this.z + moveZ * 2.0);
            if (moveX * moveX + moveZ * moveZ > 0.001) {
                float targetYaw = (float)(Math.atan2(-moveX, moveZ) * 57.29577951308232);
                this.setYRot(Duck.rotlerp(this.getYRot(), targetYaw));
            }

            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (!this.onGround) {
                this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
            }

            double dx = lookDir.x - this.x;
            double dz = lookDir.z - this.z;
            float targetYaw = (float)(Math.atan2(-dx, dz) * 57.29577951308232);
            this.setYRot(targetYaw);
        }
    }

    public float getYRot() {
        return this.yaw;
    }

    public void setYRot(float targetYaw) {
        this.yaw = targetYaw;
        this.setyaw(targetYaw);
        this.bodyYaw = targetYaw;
    }

    private void reCalcPos() {
        this.randomX = (float)(Math.random() - 1.0);
        this.randomZ = (float)(Math.random() - 1.0);
    }

    public double horizontalDistance(Vec3d vec3) {
        return Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
    }

    public boolean isPassenger() {
        return this.hasVehicle();
    }

    public void dismountFromVehicle() {
        this.vehicle = null;
    }

    public Vec3d getPosVec() {
        return Vec3d.create(this.x, this.y, this.z);
    }

    public void lerpVelocity(Vec3d vec3d) {
        this.setVelocityClient(vec3d.x, vec3d.y, vec3d.z);
    }

    public Vec3d getVelocity() {
        return Vec3d.create(this.velocityX, this.velocityY, this.velocityZ);
    }

    public boolean isTamed() {
        return true;
    }

    public void setTamed(boolean tamed) {
    }

    public boolean isSitting() {
        return this.sitting;
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public LivingEntity getOwner() {
        return this.ownerName != null && this.world != null ? this.world.getPlayer(this.ownerName) : null;
    }

    public void setSpeed(float speed) {
        this.movementSpeed = speed;
        this.forwardSpeed = speed;
    }

    public float getSpeed() {
        return this.movementSpeed;
    }

    public void setyaw(float yaw) {
        this.yaw = yaw;
    }

    public float getyaw() {
        return this.yaw;
    }

    public void tick() {
        this.bodyTrackingIncrements = 0;
        this.sidewaysSpeed = 0.0F;
        this.forwardSpeed = 0.0F;
        super.tick();
    }

    public void travel(float strafe, float forward) {
        if (!this.isSubmergedInWater() && !this.isTouchingLava()) {
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.lastWalkAnimationSpeed = this.walkAnimationSpeed;
            double dx = this.x - this.prevX;
            double dz = this.z - this.prevZ;
            float dist = (float)Math.sqrt(dx * dx + dz * dz) * 4.0F;
            if (dist > 1.0F) {
                dist = 1.0F;
            }

            this.walkAnimationSpeed += (dist - this.walkAnimationSpeed) * 0.4F;
            this.walkAnimationProgress += this.walkAnimationSpeed;
        } else {
            super.travel(strafe, forward);
        }
    }
}
