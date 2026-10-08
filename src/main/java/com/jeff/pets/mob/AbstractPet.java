package com.jeff.pets.mob;

import com.jeff.pets.PetsInitializer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.Sys;
import net.minecraft.util.Vec3;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.EntityAgeable;

/**
 * Abstract class that extends {@link EntityTameable}, providing multiple utilities
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
public abstract class AbstractPet extends EntityTameable {

    protected int waitingTime = 0;
    private boolean isReturningToOwner = false;
    private float randomX = (float) (Math.random() - 1f);
    private float randomZ = (float) (Math.random() - 1);

    /**
     * Minecraft 1.8.9 tracked the body and head yaw as separate
     * {@link net.minecraft.entity.EntityLivingBase} fields, but 1.7.10 folds both into the
     * single {@code rotationYawHead} field. The pet mob code drives the two axes
     * independently (body follows the movement yaw, head is clamped separately), so both are
     * kept as fields here and the body yaw is mirrored into {@code rotationYawHead}, which is
     * what the 1.7.10 living renderer actually interpolates.
     */
    public float bodyYaw = this.rotationYaw;
    public float headYaw = this.rotationYaw;

protected AbstractPet(World level) {
        super(level);
        this.setAIMoveSpeed(0.5f);
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
    public boolean interact(EntityPlayer player) {
        ItemStack itemStack = player.getCurrentEquippedItem();

        if (this.isTamed() && itemStack == null && !player.isSneaking()) {
            this.worldObj.spawnParticle("heart", this.posX, this.posY + this.heartHeight(), this.posZ, 0.0, 0.0, 0.0);
            return true;
        }

        if (this.isTamed() && itemStack == null && player.isSneaking()) {
            if (!this.hasMount()) {
                this.mountEntity(player);
                //this.lookAtEntity(player, 0f, 0f);
            } else {
                this.stopRiding();
            }
            return true;
        }
        return super.interact(player);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        // the walk cycle is driven by the limb distance, so fade it out when nothing
        // sets it again - otherwise the animation keeps running (and speeding up) forever
        this.setLimbDistance(this.getLimbDistance() * 0.7F);
        super.onUpdate();
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
     * Easier way to call the super's custom name method that takes a String rather than a {@link net.minecraft.util.IChatComponent}.
     */
public void setName(String string) {
        this.setCustomNameTag(string);
        this.setAlwaysRenderNameTag(true);
    }

    public void wander() {
        float speed = (float) (this.getAIMoveSpeed() - 0.35);
        Vec3 lookDir;
        float z = speed * this.randomZ;

        float distance = this.getDistanceToEntity(this.getOwner());
        float yVelo = (float) this.motionY;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.motionX = speed;
            this.motionY = yVelo;
            this.motionZ = z;
        } else {
            this.motionX = -speed;
            this.motionY = yVelo;
            this.motionZ = -z;
        }

        double moveX = this.motionX;
        double moveZ = this.motionZ;

        lookDir = Vec3.createVectorHelper(
                this.posX + (moveX * 2),
                this.posY + this.getEyeHeight(),
                this.posZ + (moveZ * 2)
        );
        this.getLookHelper().setLookPosition(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord, 1.0F, (float) this.getVerticalFaceSpeed());

        if (this.isCollidedHorizontally && this.onGround) {
            this.jump();
        }
        if (!this.onGround) {
            this.addVelocity(0, -0.04, 0);
        }

        double dx = lookDir.xCoord - this.posX;
        double dz = lookDir.zCoord - this.posZ;
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));

        this.setYRot(targetYaw);
        this.setLimbDistance(0.4F);
    }

    public float getYRot() {
        return this.bodyYaw;
    }

    /**
     * Turns the pet. {@link net.minecraft.entity.EntityLiving#bodyYaw} and
     * {@link net.minecraft.entity.EntityLiving#headYaw} are the fields the renderer actually
     * interpolates when it draws the model, so they have to be written alongside the entity yaw,
     * otherwise the model never turns.
     */
    public void setYRot(float targetYaw) {
        this.renderYawOffset = targetYaw;
        this.headYaw = targetYaw;
    }

    private void reCalcPos() {
        this.randomX = (float) (Math.random() - 1);
        this.randomZ = (float) (Math.random() - 1);
    }

    public double horizontalDistance(Vec3 vec3) {
        return Math.sqrt(vec3.xCoord * vec3.xCoord + vec3.zCoord * vec3.zCoord);
    }

    public boolean isPassenger() {
        return this.hasMount();
    }

    public boolean hasVehicle() {
        return this.hasMount();
    }

    public Vec3 getPos() {
        return Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
    }

    public Vec3 getVelocity() {
        return Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ);
    }

    public void setVelocity(Vec3 velocity) {
        this.motionX = velocity.xCoord;
        this.motionY = velocity.yCoord;
        this.motionZ = velocity.zCoord;
    }

    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }

    public void requestTeleport(double x, double y, double z) {
        this.setPosition(x, y, z);
    }

    public void stopRiding() {
        this.mountEntity(null);
    }

    public boolean hasMount() {
        return this.ridingEntity != null;
    }

    public void lookAtEntity(Entity target, float maxYawChange, float maxPitchChange) {
        double dx = target.posX - this.posX;
        double dy = (target.posY + (double) target.getEyeHeight()) - (this.posY + (double) this.getEyeHeight());
        double dz = target.posZ - this.posZ;
        double dist = Math.sqrt(dx * dx + dz * dz);
        this.rotationPitch = (float) (-(Math.atan2(dy, dist) * (180D / Math.PI)));
        float targetYaw = (float) (Math.atan2(dz, dx) * (180D / Math.PI)) - 90.0F;

        // step the body and the head towards the target instead of snapping them, so the
        // model actually turns towards whatever the pet is looking at
        this.bodyYaw = this.bodyYaw + MathHelper.clamp_float(MathHelper.wrapAngleTo180_float(targetYaw - this.bodyYaw), -maxYawChange, maxYawChange);
        //this.headYaw = this.headYaw + MathHelper.clamp_float(MathHelper.wrapAngleTo180_float(targetYaw - this.headYaw), -maxYawChange, maxYawChange);
        this.rotationYaw = this.bodyYaw;
    }

    public float getLimbDistance() {
        return this.limbSwingAmount;
    }

    public void setLimbDistance(float limbDistance) {
        this.limbSwingAmount = limbDistance;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entity) {
        return null;
    }

    @Override
    public boolean isTamed() {
        return true;
    }

    @Override
    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23);
    }
}