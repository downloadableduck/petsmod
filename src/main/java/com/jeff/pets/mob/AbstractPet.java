package com.jeff.pets.mob;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.network.Packet;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    protected AbstractPet(EntityType<? extends @NotNull TameableEntity> type, World level) {
        super(type, level);
        this.setMovementSpeed(0.5f);
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(8);
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23);
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
    protected abstract SoundEvent getAmbientSound();

    /**
     * Custom interactions.
     * - Right clicking on a pet with an empty hand will let make hearts appear above it:
     * <pre>
     *     {@code if (this.isTame() && itemStack.isEmpty() && !player.isShiftKeyDown()) {
     *         this.level.addParticle(
     *                 ParticleTypes.HEART,
     *                 this.x,
     *                 this.y + this.heartHeight(),
     *                 this.z,
     *                 5, 5, 5
     *         );
     *         return InteractionResult.SUCCESS;
     *     }}</pre>
     * - Shifting and right clicking on a pet with an empty hand will pick it up:
     * <pre>
     *     {@code if (this.isTame() && itemStack.isEmpty() && player.isShiftKeyDown()) {
     *         if (!this.isPassenger()) {
     *             this.startRiding(player);
     *             this.lookAt(player, 1f, 1f);
     *             return InteractionResult.SUCCESS;
     *         } else {
     *             this.stopRiding();
     *         }
     *         return InteractionResult.SUCCESS;
     *     }
     *     }
     * </pre>
     *
     * @return It's super method
     */
    @Override
    public boolean interactMob(@NotNull PlayerEntity player, @NotNull Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isTamed() && itemStack.isEmpty() && !player.isSneaking()) {
            this.world.addParticle(
                    ParticleTypes.HEART,
                    this.x,
                    this.y + this.heartHeight(),
                    this.z,
                    5, 5, 5
            );
            return true;
        }

        if (this.isTamed() && itemStack.isEmpty() && player.isSneaking()) {
            if (!this.hasVehicle()) {
                this.startRiding(player);
                this.lookAtEntity(player, 1f, 1f);
                return true;
            } else {
                this.stopRiding();
            }
            return true;
        }
        return super.interactMob(player, hand);
    }

    /**
     * Custom method required for making the mob work on servers.
     * <p> Calls: It's super method, if the level is not client-sided.
     */
    @Override
    public void onTrackedDataSet(@NotNull TrackedData<?> key) {
        if (!this.world.isClient()) {
            super.onTrackedDataSet(key);
        }
    }

    /**
     * IMPORTANT: Allows the entity to exist on servers, if only in the {@code ClientLevel}.
     * Never, under any circumstances, remove this method.
     */
    @Override
    public @NotNull Packet<?> createSpawnPacket() {
        if (this.world.isClient()) {
            return new EntitySpawnS2CPacket(this);
        } else {
            return super.createSpawnPacket();
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
    @Override
    public @Nullable PassiveEntity createChild(@NotNull PassiveEntity AgableMob) {
        return null;
    }

    /**
     * Easier way to call {@link TameableEntity#setCustomName} that takes a String rather than a {@link Text}
     */
    public void setName(String string) {
        this.setCustomName(new TextComponent(string));
    }

    public void wander() {
        float speed = (float) (this.getMovementSpeed() - 0.35);
        Vec3d lookDir;
        float z = speed * this.randomZ;

        float distance = this.distanceTo(this.getOwner());
        float yVelo = (float) this.getVelocity().y;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.setVelocity(new Vec3d(speed, yVelo, z));
        } else {
            this.setVelocity(new Vec3d(-speed, yVelo, -z));
        }

        //this.lookAt(EntityAnchorArgument.Anchor.EYES, lookDir);

        double moveX = this.getVelocity().x;
        double moveZ = this.getVelocity().z;

        lookDir = new Vec3d(
                this.x + (moveX * 2),
                this.y + this.getStandingEyeHeight(),
                this.z + (moveZ * 2)
        );
        this.getLookControl().lookAt(lookDir.x, lookDir.y, lookDir.z, 1.0F, (float) this.getLookPitchSpeed());

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            float smoothYaw = MathHelper.lerpAngleDegrees(0.2f, this.getYRot(), targetYaw);

            this.setYRot(smoothYaw);
            this.setHeadYaw(smoothYaw);
            this.field_6283 /*bodyYaw*/ = smoothYaw;
        }

        if (this.horizontalCollision && this.onGround) {
            this.jump();
        }
        if (!this.onGround) {
            this.setVelocity(this.getVelocity().add(0, -0.04, 0));
        }
        double dx = lookDir.x - this.x;
        double dz = lookDir.z - this.z;
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));

        this.setYRot(targetYaw);
        this.setHeadYaw(targetYaw);
        this.field_6283 /*bodyYaw*/ = targetYaw;
    }

    public float getYRot() {
        return this.field_6283 /*bodyYaw*/;
    }

    public void setYRot(float targetYaw) {
        this.setHeadYaw(targetYaw);
        this.setYaw(targetYaw);
    }

    private void reCalcPos() {
        this.randomX = (float) (Math.random() - 1);
        this.randomZ = (float) (Math.random() - 1);
    }

    public double horizontalDistance(Vec3d vec3) {
        return Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
    }

    public boolean isPassenger() {
        return this.hasVehicle();
    }
}
