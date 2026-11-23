package com.jeff.pets.mob;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.mob.custom.first.Duck;
import com.jeff.pets.compat.DataTrackerCompat;

public abstract class AbstractPet extends MobAnimal {
    protected int waitingTime = 0;
    private boolean isReturningToOwner = false;
    private float randomX = (float)(Math.random() - 1.0);
    private float randomZ = (float)(Math.random() - 1.0);
    private String ownerName;
    private boolean sitting;
    public DataTrackerCompat dataTracker = new DataTrackerCompat();
    public String texture;

    protected AbstractPet(World world) {
        super(world);
        this.setSpeed(0.4F);
        this.setHealthRaw(8);
    }

    protected boolean aiEnabled() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        if (!this.aiEnabled()) {
            super.onLivingUpdate();
        }
    }

    protected abstract int stopDistance();
    protected abstract float heartHeight();
    protected abstract String getRandomSound();

    @Override
    public boolean interact(@NotNull Player player) {
        ItemStack itemStack = player.getCurrentEquippedItem();
        if (itemStack == null && !player.isSneaking()) {
            if (this.world != null) {
                this.world.spawnParticle("heart", this.x, this.y + (double)this.heartHeight(), this.z, 0.0, 0.0, 0.0, 0, 0.0, false);
            }
            return true;
        } else if (itemStack == null && player.isSneaking()) {
            if (this.vehicle == null) {
                this.startRiding(player);
                return true;
            } else {
                this.ejectRider();
                return true;
            }
        } else {
            return super.interact(player);
        }
    }

    public void setName(String string) {
    }

    public void wander() {
        float speed = 0.08F;
        float z = speed * this.randomZ;
        Mob owner = this.getOwner();
        if (owner != null) {
            float distance = this.distanceTo(owner);
            float yVelo = (float)this.yd;
            if (distance > 5.0F) {
                this.reCalcPos();
                this.isReturningToOwner = true;
            } else if (distance < 2.0F) {
                this.reCalcPos();
                this.isReturningToOwner = false;
            }

            if (!this.isReturningToOwner) {
                this.lerpVelocity(Vec3.getPermanentVec3((double)speed, (double)yVelo, (double)z));
            } else {
                this.lerpVelocity(Vec3.getPermanentVec3((double)(-speed), (double)yVelo, (double)(-z)));
            }

            double moveX = this.xd;
            double moveZ = this.zd;
            if (moveX * moveX + moveZ * moveZ > 0.001) {
                float targetyRot = (float)(Math.atan2(-moveX, moveZ) * 57.29577951308232);
                this.setYRot(targetyRot);
            }

            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (!this.onGround) {
                this.lerpVelocity(Vec3.getPermanentVec3(this.xd, this.yd - 0.04, this.zd));
            }

            double dx = moveX * 2.0;
            double dz = moveZ * 2.0;
            float targetyRot = (float)(Math.atan2(-dx, dz) * 57.29577951308232);
            this.setYRot(targetyRot);
        }
    }

    public float getYRot() { return this.yRot; }
    public void setYRot(float targetyRot) { this.yRot = targetyRot; this.yBodyRot = targetyRot; }

    private void reCalcPos() {
        this.randomX = (float)(Math.random() - 1.0);
        this.randomZ = (float)(Math.random() - 1.0);
    }

    public double horizontalDistance(Vec3 vec3) {
        return Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
    }

    public boolean isPassenger() { return this.vehicle != null; }

    @Override
    public net.minecraft.core.entity.Entity ejectRider() {
        return super.ejectRider();
    }

    public Vec3 getPosVec() { return Vec3.getPermanentVec3(this.x, this.y, this.z); }
    public void lerpVelocity(Vec3 vec3d) { this.xd = vec3d.x; this.yd = vec3d.y; this.zd = vec3d.z; }
    public Vec3 getVelocity() { return Vec3.getPermanentVec3(this.xd, this.yd, this.zd); }

    public boolean isTamed() { return true; }
    public void setTamed(boolean tamed) { }
    public boolean isSitting() { return this.sitting; }
    public void setSitting(boolean sitting) { this.sitting = sitting; }
    public String getOwnerName() { return this.ownerName; }
    public void setOwnerName(String name) { this.ownerName = name; }

    public Mob getOwner() {
        if (this.ownerName != null && this.world != null) {
            Player p = this.world.getPlayerEntityByName(this.ownerName);
            return p;
        }
        return null;
    }

    public void setSpeed(float speed) { this.moveSpeed = speed; this.moveForward = speed; }
    public float getSpeed() { return this.moveSpeed; }

    public void setyRot(float yRot) { this.yRot = yRot; }
    public float getyRot() { return this.yRot; }

    @Override
    public void tick() {
        // this.lerpSteps = 0;
        this.moveStrafing = 0.0F;
        this.moveForward = 0.0F;
        super.tick();
    }

    public void travel(float strafe, float forward) {
        if (!this.isInWater() && !this.isInLava()) {
            this.move(this.xd, this.yd, this.zd);
            this.walkAnimSpeedO = this.walkAnimSpeed;
            double dx = this.x - this.xo;
            double dz = this.z - this.zo;
            float dist = (float)Math.sqrt(dx * dx + dz * dz) * 4.0F;
            if (dist > 1.0F) dist = 1.0F;
            this.walkAnimSpeed += (dist - this.walkAnimSpeed) * 0.4F;
            this.walkAnimPos += this.walkAnimSpeed;
        } else {
            this.move(this.xd, this.yd, this.zd);
        }
    }

    public void moveRelative(float strafe, float forward, float friction) {
        float f = strafe * strafe + forward * forward;
        if (f >= 1.0E-4F) {
            f = (float)Math.sqrt((double)f);
            if (f < 1.0F) f = 1.0F;
            f = friction / f;
            strafe *= f;
            forward *= f;
            float f1 = (float)Math.sin((double)this.yRot * Math.PI / 180.0);
            float f2 = (float)Math.cos((double)this.yRot * Math.PI / 180.0);
            this.xd += (double)(strafe * f2 - forward * f1);
            this.zd += (double)(forward * f2 + strafe * f1);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.ownerName != null) tag.putString("OwnerName", this.ownerName);
        tag.putBoolean("Sitting", this.sitting);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.containsKey("OwnerName")) this.ownerName = tag.getString("OwnerName");
        this.sitting = tag.getBoolean("Sitting");
    }

    // Compat helpers

    public void initDataTracker() {}
    public void defineSynchedData() {}
    public void tickMovement() { this.onLivingUpdate(); }
    public void setBoundingBoxSpacing(float w, float h) { this.setSizeCompat(w,h); }
    public void setVelocity(double x,double y,double z){ this.xd=x; this.yd=y; this.zd=z; }
    public void setPosition(double x,double y,double z){ this.setPos(x,y,z); }
    public void setSizeCompat(float w, float h) { try { this.bbWidth=w; this.bbHeight=h; } catch(Exception e){} }
    public net.minecraft.core.entity.Entity getVehicle(){ return (net.minecraft.core.entity.Entity)(Object)this.vehicle; }
    public boolean isBaby(){ return false; }
    public void setBaby(boolean b){}
    public void jump() { this.yd = 0.42; }
    public void lookAtCompat(net.minecraft.core.entity.Entity e, float a, float b) {}
    public net.minecraft.core.util.phys.Vec3 getLookAngle(){ return Vec3.getPermanentVec3(0,0,1); }
    public float getHeadHeight() { return this.bbHeight * 0.85f; }
}
