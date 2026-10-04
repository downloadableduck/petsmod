package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Abstract class that extends {@link net.minecraft.entity.passive.EntityTameable}, providing multiple utilities
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

    protected AbstractPet(World level) {
        super(level);
        this.setAIMoveSpeed(0.5f);
        // 1.7.10 registers entity attributes in the constructor; the overridable
        // registerAttributes() hook the 1.8 build overrode only arrived in 1.8.
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D);
        this.initEntityAI();
    }

    public void initEntityAI() {
    }

    public static float rotlerp(float pct, float start, float end) {
        float diff = end - start;

        while (diff < -180.0F) diff += 360.0F;
        while (diff >= 180.0F) diff -= 360.0F;

        return start + pct * diff;
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
    @Override
    protected String getLivingSound() {
        return null;
    }

    @Override
    public boolean interact(EntityPlayer player) {

        if (this.isTamed() && player.getHeldItem() == null && !player.isSneaking()) {
            this.worldObj.spawnParticle("heart",
                    this.posX,
                    this.posY + this.heartHeight(),
                    this.posZ,
                    0.0D, 0.0D, 0.0D
            );
            return true;
        }

        if (this.isTamed() && player.getHeldItem() == null && player.isSneaking()) {
            if (!this.isRiding()) {
                this.mountEntity(player);
                return true;
            } else {
                this.dismountEntity(this.getOwner());
            }
            return true;
        }
        return super.interact(player);
    }

    /**
     * Calls the previous abstract method so other classes extending this one don't have to.
     *
     * @return False, as breeding and taming is not needed for any {@code Client-} mobs.
     * Make sure to override this when using a custom-made mob.
     */
    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return false;
    }

    /**
     * These entities are not used in server worlds - as such, there is no reason to
     * return anything. However, this must be overridden when creating a custom entity.
     *
     * @return {@code null}
     */
    @Override
    public EntityAgeable createChild(EntityAgeable AgableMob) {
        return null;
    }

    /**
     * Easier way to call {@link net.minecraft.entity.passive.EntityTameable#setCustomNameTag} that takes a String.
     */
    public void setName(String string) {
        this.setCustomNameTag(string);
    }

    public void wander() {
        float speed = (float) (this.getAIMoveSpeed() - 0.35);
        float z = speed * this.randomZ;

        float distance = (float) this.getDistance(this.getOwnerEntity());
        float yVelo = (float) this.motionY;

        if (distance > 5) {
            this.reCalcPos();
            this.isReturningToOwner = true;
        } else if (distance < 2) {
            this.reCalcPos();
            this.isReturningToOwner = false;
        }

        if (!this.isReturningToOwner) {
            this.setVelocity(speed, yVelo, z);
        } else {
            this.setVelocity(-speed, yVelo, -z);
        }

        double moveX = this.motionX;
        double moveZ = this.motionZ;

        Vec3 lookDir = Vec3.createVectorHelper(
                this.posX + (moveX * 2),
                this.posY + this.getEyeHeight(),
                this.posZ + (moveZ * 2)
        );
        this.getLookHelper().setLookPosition(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord, 1.0F, 10.0F);

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            float smoothYaw = Duck.rotlerp(0.2f, this.getYRot(), targetYaw);

            this.setYRot(smoothYaw);
            this.setRotationYawHead(smoothYaw);
            this.renderYawOffset = smoothYaw;
        }

        if (this.isCollidedHorizontally && this.onGround) {
            this.jump();
        }
        if (!this.onGround) {
            this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
        }
        double dx = lookDir.xCoord- this.posX;
        double dz = lookDir.zCoord - this.posZ;
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));

        this.setYRot(targetYaw);
        this.setRotationYawHead(targetYaw);
        this.renderYawOffset = targetYaw;
    }

public float getYRot() {
        return this.renderYawOffset;
    }

    public void setYRot(float targetYaw) {
        this.setRotationYawHead(targetYaw);
        this.renderYawOffset = targetYaw;
    }

    private void reCalcPos() {
        this.randomX = (float) (Math.random() - 1);
        this.randomZ = (float) (Math.random() - 1);
    }

    /**
     * 1.8 added {@code Entity#getDistance(Entity)}; 1.7.10 only offers
     * {@code getDistanceSqToEntity(Entity)}, so take the root to keep the original semantics.
     */
    public double getDistance(Entity entity) {
        if (entity == null) {
            return 0.0D;
        }
        return Math.sqrt(this.getDistanceSqToEntity(entity));
    }

    public double horizontalDistance(Vec3 vec3) {
        return Math.sqrt(vec3.xCoord * vec3.xCoord + vec3.zCoord * vec3.zCoord);
    }

    public void lookAt(final Entity entity, final float yMax, final float xMax) {
        double xd = entity.posX - this.posX;
        double zd = entity.posZ - this.posZ;
        double yd;
        if (entity instanceof EntityLiving) {
            EntityLiving mob = (EntityLiving) entity;
            yd = mob.getEyeHeight() - this.getEyeHeight();
        } else {
            yd = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0F - this.getEyeHeight();
        }

        double sd = Math.sqrt(xd * xd + zd * zd);
        float yRotD = (float) (Math.atan2((double) zd, (double) xd) * (double) (180F / (float) Math.PI)) - 90.0F;
        float xRotD = (float) (-(Math.atan2((double) yd, (double) sd) * (double) (180F / (float) Math.PI)));
        this.rotationPitch = rotlerp(this.rotationPitch, xRotD, xMax);
        this.setYRot(rotlerp(this.getYRot(), yRotD, yMax));
    }

    @Override
    public boolean isTamed() {
        return true;
    }

    /**
     * 1.7.10 already exposes the owner through a readable {@code getOwner()}, so this simply
     * defers to the vanilla implementation instead of the SRG-only {@code func_180492_cm()}.
     */
    public EntityLivingBase getOwnerEntity() {
        return super.getOwner();
    }

    public static float wrapDegrees(float value) {
        return MathHelper.wrapAngleTo180_float(value);
    }
}