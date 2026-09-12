package com.jeff.pets.mob;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.sound.Sound;
import net.minecraft.util.Hand;
import net.minecraft.entity.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.Sys;

/**
 * Abstract class that extends {@link TameableEntity}, providing multiple utilities
 * so that each class doesn't have to define the same logic. <p> When creating a custom entity,
 * always extend either {@link GroundPet}, {@link FlyingPet}, or {@link SlimeLikePet},
 * unless adding custom movement logic,
 * as this file does not contain custom movement logic for the entities.
 * <p> When creating a custom mob, always extend this class, as using the built-in
 * {@link GroundPet} or {@link FlyingPet} logic breaks them when acting as normal mobs.
 *
 * @see FlyingPet
 * @see GroundPet
 */
public abstract class AbstractPet extends TameableEntity {

    protected int waitingTime = 0;
    private boolean isReturningToOwner = false;
    private float randomX = (float) (Math.random() - 1f);
    private float randomZ = (float) (Math.random() - 1);

protected AbstractPet(World level) {
        super(level);
        this.setMovementSpeed(0.5f);
    }

    @Override
    public void initializeAttributes() {
        super.initializeAttributes();
        this.getAttributeContainer().get(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(8);
        this.getAttributeContainer().get(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23);
    }

    /**
     * This method is used to determine when the entity stops moving, relative to the player.
     * For example, if we defined this:
     * <pre>{@code protected abstract int stopDistance() {
     *     return 2;
     * }}</pre>
     * The entity would stop moving towards the player when it is two blocks away from the player.
     */
    protected abstract int stopDistance();

    /**
     * Used to define how high the hearts that appear when you right-click on a pet will be.
     * Zero means it would appear right in the middle of the entity's hitbox.
     */
    protected abstract float heartHeight();

    /**
     * Used to define the entity's default ambient sound. For the uses of these last three
     * methods, please refer to {@link FlyingPet} and {@link GroundPet}.
     */
    protected abstract Sound getAmbientSound();

    /**
     * Custom interactions.
     * - Right clicking on a pet with an empty hand will let make hearts appear above it:
     * <pre>
     *     {@code if (this.isTamed() && itemStack.isEmpty() && !player.isSneaking()) {
     *         this.world.method_16343(
     *                 this.getParticleEffect("heart"),
     *                 this.x,
     *                 this.y + this.heartHeight(),
     *                 this.z,
     *                 5, 5, 5
     *         );
     *         return true;
     *     }}
     * </pre>
     * - Shifting and right clicking on a pet with an empty hand will pick it up:
     * <pre>
     *     {@code if (this.isTamed() && itemStack.isEmpty() && player.isSneaking()) {
     *         if (!this.hasMount()) {
     *             this.startRiding(player);
     *             this.lookAtEntity(player, 1f, 1f);
     *             return true;
     *         } else {
     *             this.stopRiding();
     *         }
     *         return true;
     *     }}
     * </pre>
     *
     * @return It's super method
     */
    @Override
    public boolean method_6100(@NotNull PlayerEntity player, ItemStack itemStack, @NotNull Hand hand) {

if (this.isTamed() && itemStack.getItem() == null && !player.isSneaking()) {
            this.world.addParticle(ParticleType.HEART, this.x, this.y + this.heartHeight(), this.z, 0.0, 0.0, 0.0);
            return true;
        }

        if (this.isTamed() && itemStack.getItem() == null && player.isSneaking()) {
            if (!this.hasMount()) {
                this.startRiding(player);
                this.lookAtEntity(player, 1f, 1f);
                return true;
            } else {
                this.stopRiding();
            }
            return true;
        }
        return super.method_6100(player, itemStack, hand);
    }

    @Override
    public void tick() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        this.prevTickX = this.x;
        this.prevTickY = this.y;
        this.prevTickZ = this.z;
        super.tick();
    }

    @Override
    public void onTrackedDataSet(@NotNull TrackedData<?> key) {
        if (!this.world.isClient) {
            super.onTrackedDataSet(key);
        }
    }

    /**
     * Calls the previous abstract method so other classes extending this one don't have to.
     *
     * @return False, as breeding and taming is not needed for any {@code Client-} mobs.
     * Make sure to override this when using a custom-made mob.
     */
    @Override
    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * These entities are not used in server worlds - as such, there is no reason to
     * return anything. However, this must be overridden when creating a custom entity.
     *
     * @return {@code null}
     */
    /**
     * Easier way to call the super's custom name method that takes a String rather than a {@link net.minecraft.text.Text}.
     */
public void setName(String string) {
        this.setCustomName(string);
        this.setCustomNameVisible(true);
    }

    public void wander() {
        float speed = (float) (this.getMovementSpeed() - 0.35);
        Vec3d lookDir;
        float z = speed * this.randomZ;

        float distance = this.distanceTo(this.getOwner());
        float yVelo = (float) this.velocityY;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.velocityX = speed;
            this.velocityY = yVelo;
            this.velocityZ = z;
        } else {
            this.velocityX = -speed;
            this.velocityY = yVelo;
            this.velocityZ = -z;
        }

        double moveX = this.velocityX;
        double moveZ = this.velocityZ;

        lookDir = new Vec3d(
                this.x + (moveX * 2),
                this.y + this.getEyeHeight(),
                this.z + (moveZ * 2)
        );
        this.getLookControl().lookAt(lookDir.x, lookDir.y, lookDir.z, 1.0F, (float) this.getLookPitchSpeed());

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            float smoothYaw = MathHelper.clamp(0.2f, this.getYRot(), targetYaw); //lerpAngleDegrees

            this.setYRot(smoothYaw);
            this.setHeadYaw(smoothYaw);
            this.bodyYaw = smoothYaw;
        }

        if (this.horizontalCollision && this.onGround) {
            this.jump();
        }
        if (!this.onGround) {
            this.addVelocity(0, -0.04, 0);
        }
        double dx = lookDir.x - this.x;
        double dz = lookDir.z - this.z;
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));

        this.setYRot(targetYaw);
        this.setHeadYaw(targetYaw);
        this.bodyYaw = targetYaw;
    }

    public float getYRot() {
        return this.bodyYaw;
    }

    public void setYRot(float targetYaw) {
        this.setHeadYaw(targetYaw);
        this.setYaw(targetYaw);
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
        return this.hasMount();
    }

    public boolean hasVehicle() {
        return this.hasMount();
    }

    public Vec3d getPos() {
        return new Vec3d(this.x, this.y, this.z);
    }

    public Vec3d getVelocity() {
        return new Vec3d(this.velocityX, this.velocityY, this.velocityZ);
    }

    public void setVelocity(Vec3d velocity) {
        this.velocityX = velocity.x;
        this.velocityY = velocity.y;
        this.velocityZ = velocity.z;
    }

    public void setVelocity(double x, double y, double z) {
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
    }

    public void requestTeleport(double x, double y, double z) {
        this.updatePosition(x, y, z);
    }

    public boolean startRiding(Entity entity) {
        return this.startRiding(entity, true);
    }

    public float getLimbDistance() {
        return this.field_6749;
    }

    public void setLimbDistance(float limbDistance) {
        this.field_6749 = limbDistance;
    }

    @Override
    public PassiveEntity breed(PassiveEntity entity) {
        return null;
    }
}