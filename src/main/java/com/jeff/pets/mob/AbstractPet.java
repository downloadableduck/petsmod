package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

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
    }

    public static float rotlerp(float pct, float start, float end) {
        float diff = end - start;

        while (diff < -180.0F) diff += 360.0F;
        while (diff >= 180.0F) diff -= 360.0F;

        return start + pct * diff;
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
     *     {@code if (this.isTamed() && itemStack.isEmpty() && !player.isSneaking()) {
     *         this.world.addParticle(
     *                 ParticleTypes.HEART,
     *                 this.x,
     *                 this.y + this.heartHeight(),
     *                 this.z,
     *                 5, 5, 5
     *         );
     *         return ActionResultType.SUCCESS;
     *     }}</pre>
     * - Shifting and right clicking on a pet with an empty hand will pick it up:
     * <pre>
     *     {@code if (this.isTamed() && itemStack.isEmpty() && player.isSneaking()) {
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
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.isTamed() && itemStack.isEmpty() && !player.isSneaking()) {
            this.world.func_175682_a(
                    EnumParticleTypes.HEART,
                    false,
                    this.posX,
                    this.posY + this.heartHeight(),
                    this.posZ,
                    0.0D, 0.0D, 0.0D
            );
            return true;
        }

        if (this.isTamed() && itemStack.isEmpty() && player.isSneaking()) {
            if (!this.isPassenger()) {
                this.startRiding(player);
                //this.lookAt(player, 1f, 1f);
                return true;
            } else {
                this.stopRiding();
            }
            return true;
        }
        return super.processInteract(player, hand);
    }

    /**
     * IMPORTANT: Allows the entity to exist on servers, if only in the {@code ClientLevel}.
     * Never, under any circumstances, remove this method.
     */
    /*@Override
    public Packet<?> getAddEntityPacket() {
        if (this.world.isRemote()) {
            return new SPacketSpawnObject(this);
        } else {
            return super.getAddEntityPacket();
        }
    }*/

    /**
     * Custom method required for making the mob work on servers.
     * <p> Calls: It's super method, if the level is not client-sided.
     */
    @Override
    public void notifyDataManagerChange(net.minecraft.network.datasync.DataParameter<?> key) {
        if (this.world != null && !this.world.isRemote) {
            super.notifyDataManagerChange(key);
        }
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
     * Easier way to call {@link net.minecraft.entity.passive.EntityTameable#setCustomName} that takes a String rather than a {@link Component}
     */
    public void setName(String string) {
        this.func_96094_a((string));
    }

    public void wander() {
        float speed = (float) (this.getAIMoveSpeed() - 0.35);
        float z = speed * this.randomZ;

        float distance = this.getDistance(this.getOwner());
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

        //this.lookAt(EntityAnchorArgument.Anchor.EYES, lookDir);

        double moveX = this.motionX;
        double moveZ = this.motionZ;

        Vec3d lookDir = new Vec3d(
                this.posX + (moveX * 2),
                this.posY + this.getEyeHeight(),
                this.posZ + (moveZ * 2)
        );
        this.getLookHelper().setLookPosition(lookDir.x, lookDir.y, lookDir.z, 1.0F, 10.0F);

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            float smoothYaw = Duck.rotlerp(0.2f, this.getYRot(), targetYaw);

            this.setYRot(smoothYaw);
            this.setRotationYawHead(smoothYaw);
            this.renderYawOffset = smoothYaw;
        }

        if (this.collidedHorizontally && this.onGround) {
            this.jump();
        }
        if (!this.onGround) {
            this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
        }
        double dx = lookDir.x - this.posX;
        double dz = lookDir.z - this.posZ;
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
        this.setRenderYawOffset(targetYaw);
    }

    private void reCalcPos() {
        this.randomX = (float) (Math.random() - 1);
        this.randomZ = (float) (Math.random() - 1);
    }

    public double horizontalDistance(Vec3d vec3) {
        return Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
    }

    public void lookAt(final Entity entity, final float yMax, final float xMax) {
        double xd = entity.posX - this.posX;
        double zd = entity.posZ - this.posZ;
        double yd;
        if (entity instanceof EntityLiving) {
            EntityLiving mob = (EntityLiving) entity;
            yd = mob.getEyeHeight() - this.getEyeHeight();
        } else {
            yd = (entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0F - this.getEyeHeight();
        }

        double sd = Math.sqrt(xd * xd + zd * zd);
        float yRotD = (float) (MathHelper.atan2(zd, xd) * (double) (180F / (float) Math.PI)) - 90.0F;
        float xRotD = (float) (-(MathHelper.atan2(yd, sd) * (double) (180F / (float) Math.PI)));
        this.rotationPitch = rotlerp(this.rotationPitch, xRotD, xMax);
        this.setYRot(rotlerp(this.getYRot(), yRotD, yMax));
    }
}
