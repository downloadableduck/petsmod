package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.animal.AnimalEntity;
import net.minecraft.entity.mob.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Abstract base for client-side pets, adapted for 1.1's entity hierarchy.
 * <p> 1.1 has no {@code TameableEntity}; taming/owner/sitting/speed helpers that existed
 * in 1.2.5 are provided here as a compatibility layer.
 */
public abstract class AbstractPet extends AnimalEntity {

    protected int waitingTime = 0;
    private boolean isReturningToOwner = false;
    private float randomX = (float) (Math.random() - 1f);
    private float randomZ = (float) (Math.random() - 1);

    // 1.1 compatibility (TameableEntity API surface used by this mod)
    private String ownerName;
    private boolean sitting;

    protected AbstractPet(World level) {
        super(level);
        this.setSpeed(0.4f);
        this.health = 8;
    }

    protected boolean aiEnabled() {
        return false;
    }

    @Override
    public void aiTick() {
        if (!aiEnabled()) {
            super.aiTick();;
        }
    }

    protected abstract int stopDistance();

    protected abstract float heartHeight();

    protected abstract String getAmbientSound();

    @Override
    public boolean interact(@NotNull PlayerEntity player) {
        ItemStack itemStack = player.getItemInHand();

        if (this.isTamed() && itemStack == null && !player.isSneaking()) {
            this.world.addParticle(
                    "heart",
                    this.x,
                    this.y + this.heartHeight(),
                    this.z,
                    0, 0, 0
            );
            return true;
        }

        if (this.isTamed() && itemStack == null && player.isSneaking()) {
            if (!this.isRiding()) {
                this.startRiding(player);
                this.lookAt(player, 1f);
                return true;
            } else {
                this.dismountFromVehicle();
            }
            return true;
        }
        return super.interact(player);
    }

    protected boolean isBreedingItem(ItemStack itemStack) {
        return false;
    }

    protected AnimalEntity makeChild(AnimalEntity mate) {
        return null;
    }

    public void setName(String string) {
        //this.getName(string);
    }

    public void wander() {
        // Direct-velocity movement in 1.1: use a small but visible wander speed.
        float speed = 0.08F;
        Vec3d lookDir;
        float z = speed * this.randomZ;

        MobEntity owner = this.getOwner();
        if (owner == null) return;
        float distance = this.distanceTo(owner);
        float yVelo = (float) this.getVelocity().y;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.lerpVelocity(Vec3d.of(speed, yVelo, z));
        } else {
            this.lerpVelocity(Vec3d.of(-speed, yVelo, -z));
        }

        double moveX = this.getVelocity().x;
        double moveZ = this.getVelocity().z;

        lookDir = Vec3d.of(
                this.x + (moveX * 2),
                this.y + this.getEyeHeight(),
                this.z + (moveZ * 2)
        );

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            this.setYRot(Duck.rotlerp(this.getYRot(), targetYaw));
        }

        if (this.collidingHorizontally && this.onGround) {
            this.jump();
        }
        if (!this.onGround) {
            this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
        }
        double dx = lookDir.x - this.x;
        double dz = lookDir.z - this.z;
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));
        this.setYRot(targetYaw);
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
        this.randomX = (float) (Math.random() - 1);
        this.randomZ = (float) (Math.random() - 1);
    }

    public double horizontalDistance(Vec3d vec3) {
        return Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
    }

    public boolean isPassenger() {
        return this.isRiding();
    }

    public void dismountFromVehicle() {
        this.vehicle = null;
    }

    public Vec3d getPosVec() {
        return Vec3d.of(this.x, this.y, this.z);
    }

    public void lerpVelocity(Vec3d vec3d) {
        this.lerpVelocity(vec3d.x, vec3d.y, vec3d.z);
    }

    // Expose Entity.lerpVelocity(double,double,double) (already inherited, but keep signature clear)

    public Vec3d getVelocity() {
        return Vec3d.of(this.velocityX, this.velocityY, this.velocityZ);
    }

    // --- 1.1 TameableEntity compatibility ---

    public boolean isTamed() {
        return true;
    }

    public void setTamed(boolean tamed) {
        // client-side pets are always tamed
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

    public MobEntity getOwner() {
        if (this.ownerName == null || this.world == null) {
            return null;
        }
        return getPlayerByName(world, this.ownerName);
    }

    public void setSpeed(float speed) {
        this.walkSpeed = speed;
        this.forwardSpeed = (speed);
    }

    public float getSpeed() {
        // 1.2 semantics: stored speed value
        return this.walkSpeed;
    }

    public void setyaw(float yaw) {
        this.yaw = yaw;
    }

    public float getyaw() {
        return this.yaw;
    }

    @Override
    public void tick() {
        // Prevent server/client interpolation from snapping this locally-scripted pet.
        this.lerpSteps = 0;
        // Our movement is driven by explicit velocity updates in subclasses; zero AI inputs
        // so mobTick's moveRelative doesn't apply additional input-based acceleration.
        this.sidewaysSpeed = 0.0F;
        this.forwardSpeed = 0.0F;
        super.tick();
    }

    @Override
    public void moveRelative(float strafe, float forward) {
        if (this.inWater || this.isInLava()) {
            super.moveRelative(strafe, forward);
            return;
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);

        this.lastWalkAnimationSpeed = this.walkAnimationSpeed;
        double dx = this.x - this.lastX;
        double dz = this.z - this.lastZ;
        float dist = (float) Math.sqrt(dx * dx + dz * dz) * 4.0F;
        if (dist > 1.0F) dist = 1.0F;
        this.walkAnimationSpeed += (dist - this.walkAnimationSpeed) * 0.4F;
        this.walkAnimationProgress += this.walkAnimationSpeed;
    }

    public boolean isInWater() {
        return this.inWater;
    }

    public static PlayerEntity getPlayerByName(World world, String string) {
        AtomicReference<PlayerEntity> playerToReturn = new AtomicReference<>(null);
        world.players.forEach(obj -> {
            PlayerEntity player = (PlayerEntity) obj;
            if (player.name.equals(string)) playerToReturn.set(player);
        });
        return playerToReturn.get();
    }

    public boolean isRiding() {
        return this.hasVehicle;
    }
}
