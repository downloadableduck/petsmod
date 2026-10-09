package com.jeff.pets.mob;

import com.jeff.pets.PetsInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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

    /**
     * Minecraft 1.8.9 tracked the body and head yaw as separate
     * {@link net.minecraft.entity.LivingEntity} fields, but 1.7.10 folds both into the
     * single {@code rotationYawHead} field. The pet mob code drives the two axes
     * independently (body follows the movement yaw, head is clamped separately), so both are
     * kept as fields here and the body yaw is mirrored into {@code rotationYawHead}, which is
     * what the 1.7.10 living renderer actually interpolates.
     */
    public float bodyYaw = this.yaw;
    public float headYaw = this.yaw;

protected AbstractPet(World level) {
        super(level);
        this.setMovementSpeed(0.5f);
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
     *     {@code if (this.isTamed() && itemStack.isEmpty() && !player.isSneaking()) {
     *         this.worldObj.method_16343(
     *                 this.getParticleEffect("heart"),
     *                 this.posX,
     *                 this.posY + this.heartHeight(),
     *                 this.posZ,
     *                 5, 5, 5
     *         );
     *         return true;
     *     }}
     * </pre>
     * - Shifting and right clicking on a pet with an empty hand will pick it up:
     * <pre>
     *     {@code if (this.isTamed() && itemStack.isEmpty() && player.isSneaking()) {
     *         if (!this.hasMount()) {
     *             this.mountEntity(player);
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
    public boolean method_2537(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();

        if (this.isTamed() && itemStack == null && !player.isSneaking()) {
            this.world.spawnParticle("heart", this.x, this.y + this.heartHeight(), this.z, 0.0, 0.0, 0.0);
            return true;
        }

        if (this.isTamed() && itemStack == null && player.isSneaking()) {
            if (!this.hasMount()) {
                this.startRiding(player);
                //this.lookAtEntity(player, 0f, 0f);
            } else {
                this.stopRiding();
            }
            return true;
        }
        return super.method_2537(player);
    }

    @Override
    public void tick() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        this.prevTickX = this.x;
        this.prevTickY = this.y;
        this.prevTickZ = this.z;
        // the walk cycle is driven by the limb distance, so fade it out when nothing
        // sets it again - otherwise the animation keeps running (and speeding up) forever
        this.setLimbDistance(this.getLimbDistance() * 0.7F);
        super.tick();
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
        this.method_5397(string);
        this.setAiDisabled(true);
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

        lookDir = Vec3d.of(
                this.x + (moveX * 2),
                this.y + this.getEyeHeight(),
                this.z + (moveZ * 2)
        );
        this.getLookControl().lookAt(lookDir.x, lookDir.y, lookDir.z, 1.0F, (float) this.getLookPitchSpeed());

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
        this.setLimbDistance(0.4F);
    }

    public float getYRot() {
        return this.bodyYaw;
    }

    /**
     * Turns the pet. {@link net.minecraft.entity.mob.MobEntity#bodyYaw} and
     * {@link net.minecraft.entity.mob.MobEntity#headYaw} are the fields the renderer actually
     * interpolates when it draws the model, so they have to be written alongside the entity yaw,
     * otherwise the model never turns.
     */
    public void setYRot(float targetYaw) {
        this.yaw = targetYaw;
        this.bodyYaw = targetYaw;
        this.headYaw = targetYaw;
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
        return Vec3d.of(this.x, this.y, this.z);
    }

    public Vec3d getVelocity() {
        return Vec3d.of(this.velocityX, this.velocityY, this.velocityZ);
    }

    public void setVelocity(Vec3d velocity) {
        this.velocityX = velocity.x;
        this.velocityY = velocity.y;
        this.velocityZ = velocity.z;
    }

    public void setVelocityClient(double x, double y, double z) {
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
    }

    public void requestTeleport(double x, double y, double z) {
        this.updatePosition(x, y, z);
    }

    public void stopRiding() {
        this.startRiding(null);
    }

    public boolean hasMount() {
        return this.vehicle != null;
    }

    public void lookAtEntity(Entity target, float maxYawChange, float maxPitchChange) {
        double dx = target.x - this.x;
        double dy = (target.y + (double) target.getEyeHeight()) - (this.y + (double) this.getEyeHeight());
        double dz = target.z - this.z;
        double dist = Math.sqrt(dx * dx + dz * dz);
        this.pitch = (float) (-(Math.atan2(dy, dist) * (180D / Math.PI)));
        float targetYaw = (float) (Math.atan2(dz, dx) * (180D / Math.PI)) - 90.0F;

        // step the body and the head towards the target instead of snapping them, so the
        // model actually turns towards whatever the pet is looking at
        this.bodyYaw = this.bodyYaw + MathHelper.clamp(MathHelper.wrapDegrees(targetYaw - this.bodyYaw), -maxYawChange, maxYawChange);
        //this.headYaw = this.headYaw + MathHelper.clamp_float(MathHelper.wrapAngleTo180_float(targetYaw - this.headYaw), -maxYawChange, maxYawChange);
        this.yaw = this.bodyYaw;
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

    @Override
    public boolean isTamed() {
        return true;
    }

    @Override
    public void initializeAttributes() {
        super.initializeAttributes();
        this.initializeAttribute(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(8);
        this.initializeAttribute(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23);
    }
}