package com.jeff.pets.mob;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * Abstract class that extends {@link net.minecraft.entity.passive.TameableEntity}, providing multiple utilities
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

    protected AbstractPet(EntityType<? extends TameableEntity> type, World level) {
        super(type, level);
        this.setSpeed(0.5f);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23);
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
     *                 this.getX(),
     *                 this.getY() + this.heartHeight(),
     *                 this.getZ(),
     *                 5, 5, 5
     *         );
     *         return ActionResultType.SUCCESS;
     *     }}</pre>
     * - Shifting and right clicking on a pet with an empty hand will pick it up:
     * <pre>
     *     {@code if (this.isTame() && itemStack.isEmpty() && player.isShiftKeyDown()) {
     *         if (!this.isPassenger()) {
     *             this.startRiding(player);
     *             this.lookAt(player, 1f, 1f);
     *             return ActionResultType.SUCCESS;
     *         } else {
     *             this.stopRiding();
     *         }
     *         return ActionResultType.SUCCESS;
     *     }
     *     }
     * </pre>
     *
     * @return It's super method
     */
    @Override
    public boolean mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (this.isTame() && itemStack.isEmpty() && !player.isShiftKeyDown()) {
            this.level.addParticle(
                    ParticleTypes.HEART,
                    this.getX(),
                    this.getY() + this.heartHeight(),
                    this.getZ(),
                    5, 5, 5
            );
            return true;
        }

        if (this.isTame() && itemStack.isEmpty() && player.isShiftKeyDown()) {
            if (!this.isPassenger()) {
                this.startRiding(player);
                this.lookAt(player, 1f, 1f);
                return true;
            } else {
                this.stopRiding();
            }
            return true;
        }
        return super.mobInteract(player, hand);
    }

    /**
     * Custom method required for making the mob work on servers.
     * <p> Calls: It's super method, if the level is not client-sided.
     */
    @Override
    public void onSyncedDataUpdated(net.minecraft.network.datasync.DataParameter<?> key) {
        if (this.level != null && !this.level.isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    /**
     * IMPORTANT: Allows the entity to exist on servers, if only in the {@code ClientLevel}.
     * Never, under any circumstances, remove this method.
     */
    @Override
    public IPacket<?> getAddEntityPacket() {
        if (this.level.isClientSide()) {
            return new SSpawnObjectPacket(this);
        } else {
            return super.getAddEntityPacket();
        }
    }

    /**
     * Calls the previous abstract method so other classes extending this one don't have to.
     *
     * @return False, as breeding and taming is not needed for any {@code Client-} mobs.
     * Make sure to override this when using a custom-made mob.
     */
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    /**
     * These entities are not used in server worlds - as such, there is no reason to
     * return anything. However, this must be overridden when creating a custom entity.
     *
     * @return {@code null}
     */
    @Override
    public AgeableEntity getBreedOffspring(AgeableEntity AgableMob) {
        return null;
    }

    /**
     * Easier way to call {@link net.minecraft.entity.passive.TameableEntity#setCustomName} that takes a String rather than a {@link Component}
     */
    public void setName(String string) {
        this.setCustomName(new StringTextComponent(string));
    }

    public void wander() {
        float speed = (float) (this.getSpeed() - 0.35);
        float z = speed * this.randomZ;

        float distance = this.distanceTo(this.getOwner());
        float yVelo = (float) this.getDeltaMovement().y;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.setDeltaMovement(new Vec3d(speed, yVelo, z));
        } else {
            this.setDeltaMovement(new Vec3d(-speed, yVelo, -z));
        }

        //this.lookAt(EntityAnchorArgument.Anchor.EYES, lookDir);

        double moveX = this.getDeltaMovement().x;
        double moveZ = this.getDeltaMovement().z;

        Vec3d lookDir = new Vec3d(
                this.getX() + (moveX * 2),
                this.getY() + this.getEyeHeight(),
                this.getZ() + (moveZ * 2)
        );
        this.getLookControl().setLookAt(lookDir.x, lookDir.y, lookDir.z, 1.0F, (float) this.getMaxHeadXRot());

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            float smoothYaw = MathHelper.rotLerp(0.2f, this.getYRot(), targetYaw);

            this.setYRot(smoothYaw);
            this.setYHeadRot(smoothYaw);
            this.yBodyRot = smoothYaw;
        }

        if (this.horizontalCollision && this.onGround) {
            this.jumpFromGround();
        }
        if (!this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
        }
        double dx = lookDir.x - this.getX();
        double dz = lookDir.z - this.getZ();
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));

        this.setYRot(targetYaw);
        this.setYHeadRot(targetYaw);
        this.yBodyRot = targetYaw;
    }

    public float getYRot() {
        return this.yBodyRot;
    }

    public void setYRot(float targetYaw) {
        this.setYHeadRot(targetYaw);
        this.setYBodyRot(targetYaw);
    }

    private void reCalcPos() {
        this.randomX = (float) (Math.random() - 1);
        this.randomZ = (float) (Math.random() - 1);
    }

    public double horizontalDistance(Vec3d vec3) {
        return Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
    }
}
