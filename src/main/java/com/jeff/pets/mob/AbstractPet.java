package com.jeff.pets.mob;

import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.TameableEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
// import net.minecraft.world.InteractionHand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jeff.pets.mob.custom.first.Duck;

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
        this.setSpeed(0.5f);
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttribute(EntityAttributes.MAX_HEALTH).setBase(8);
        this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(0.23);
    }

    @Override
    protected boolean aiEnabled() {
        // LivingEntity.getSpeed() is `aiEnabled() ? speed : 0.1F`, and the base aiEnabled() is
        // false. That made wander() compute `0.1 - 0.35 == -0.25`, so idle wandering pushed pets
        // backwards, and it silently discarded every setSpeed() call (0.5 here, 0.3 in
        // DumboOctopus). Returning true restores the intended speeds.
        //
        // Safe because pets are client-only entities injected with ClientWorld.forceEntity():
        // the only aiEnabled() consumer that runs elsewhere, serverTickAi(), is gated behind
        // isLocallyControlled(), and the remaining uses are initAttributes() (whose MOVEMENT_SPEED
        // base this class immediately overwrites with 0.23) and a friction constant.
        return true;
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
    protected abstract String getAmbientSound();

    /**
     * Custom interactions.
     * - Right clicking on a pet with an empty hand will let make hearts appear above it:
     * <pre>
     *     {@code if (this.isTame() && itemStack == null && !player.isShiftKeyDown()) {
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
     *     {@code if (this.isTame() && itemStack == null && player.isShiftKeyDown()) {
     *         if (!this.isPassenger()) {
     *             this.startRiding(player);
     *             this.lookAt(player, 1f, 1f);
     *             return InteractionResult.SUCCESS;
     *         } else {
     *             this.dismountFromVehicle();
     *         }
     *         return InteractionResult.SUCCESS;
     *     }
     *     }
     * </pre>
     *
     * @return It's super method
     */
    @Override
    public boolean interactMob(@NotNull PlayerEntity player) {
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
                this.lookAt(player, 1f, 1f);
                return true;
            } else {
                this.dismountFromVehicle();
            }
            return true;
        }
        return super.interactMob(player);
    }

    /**
     * Custom method required for making the mob work on servers.
     * <p> Calls: It's super method, if the level is not client-sided.
     */
    @Override
    public void onDataValueChanged(int key) {
        if (!(this.world instanceof ClientWorld)) {
            super.onDataValueChanged(key);
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
    public @Nullable PassiveEntity makeChild(@NotNull PassiveEntity AgableMob) {
        return null;
    }

    /**
     * Easier way to call {@link TameableEntity#setCustomName} that takes a String rather than a {@link Text}
     */
    public void setName(String string) {
        this.setCustomName(string);
    }

    public void wander() {
        float speed = (float) (this.getSpeed() - 0.35);
        Vec3d lookDir;
        float z = speed * this.randomZ;

        float distance = this.distanceTo(this.getOwner());
        float yVelo = (float) this.getVelocity().y;//this.getVelocity().y;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.lerpVelocity(Vec3d.of(speed, yVelo, z)); //this.setVelocity()
        } else {
            this.lerpVelocity(Vec3d.of(-speed, yVelo, -z));
        }

        //this.lookAt(EntityAnchorArgument.Anchor.EYES, lookDir);

        double moveX = this.getVelocity().x;
        double moveZ = this.getVelocity().z;

        lookDir = Vec3d.of(
                this.x + (moveX * 2),
                this.y + this.getEyeHeight(),
                this.z + (moveZ * 2)
        );
        this.getLookControl().lookAt(lookDir.x, lookDir.y, lookDir.z, 1.0F, (float) this.getLookPitchSpeed());

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            // Was MathHelper.clamp(0.2f, getYRot(), targetYaw), which is not an angle lerp at
            // all - it just returns 0.2 whenever 0.2 lies between the two values. Use rotlerp
            // so the body actually eases toward the wander direction.
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

        // Snap (not lerp) here: this is the terminal assignment for the wander tick, and the
        // eased version above is intentionally superseded so the body ends up pointing exactly
        // along the look target rather than trailing behind it.
        this.setYRot(targetYaw);
    }

    /**
     * The authoritative facing field. Ornithe recomputes {@code bodyYaw} every tick from
     * {@link LivingEntity#bodyMovement(float, float)} as
     * {@code yaw - clamp(wrapDegrees(yaw - bodyYaw), -75, 75)}, so writing only
     * {@code bodyYaw}/{@code headYaw} leaves {@code yaw} pinned at 0 and vanilla drags the
     * body back toward 0 every tick. Every yaw write must therefore go through {@code yaw}.
     */
    public float getYRot() {
        return this.yaw;
    }

    public void setYRot(float targetYaw) {
        this.yaw = targetYaw;
        this.setHeadYaw(targetYaw);
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
        if (this.vehicle != null) {
            ((LivingEntity) this.vehicle).dismountRider(this);
        }
    }

    public Vec3d getPosVec() {
        return Vec3d.of(this.x, this.y, this.z);
    }

    public void lerpVelocity(Vec3d vec3d) {
        this.lerpVelocity(vec3d.x, vec3d.y, vec3d.z);
    }

    public Vec3d getVelocity() {
        return Vec3d.of(this.velocityX, this.velocityY, this.velocityZ);
    }

    @Override
    public boolean isTamed() {
        return true;
    }

    @Override
    public void aiTick() {

    }

    @Override
    public void mobAiTick() {
    }
}