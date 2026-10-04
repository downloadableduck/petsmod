package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Koi extends FlyingPet {
    private static final int IS_SERVER_ENTITY = 20;

    public Koi(World level) {
        super(level);
        this.setSize(0.6f, 0.6f);
    }


    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(IS_SERVER_ENTITY, Byte.valueOf((byte) 0));
    }

    public boolean isServerEntity() {
        return this.dataWatcher.getWatchableObjectByte(IS_SERVER_ENTITY) != 0;
    }

    public void setServerEntity(Boolean value) {
        this.dataWatcher.updateObject(IS_SERVER_ENTITY, Byte.valueOf((byte) (value.booleanValue() ? 1 : 0)));
    }

    protected String getLivingSound() {
        return "mob.squid.ambient";
    }

    protected String func_70621_aR() {
        return "mob.squid.hurt";
    }

    protected String func_70673_aS() {
        return "mob.squid.death";
    }


    public Koi createChild(final EntityAgeable partner) {
        Koi koi = new Koi(this.worldObj);
        koi.setServerEntity(true);
        return koi;
    }


    public boolean isBreedingItem(final ItemStack itemStack) {
        return false;
    }

    @Override
    public void initEntityAI() {

        /**Using false in this statement causes the mob to sink to the bottom and reptitively spin.*/
        //this.moveEntityControl = new SmoothSwimmingMoveControl(this, 10, 10, 1, 1, true);
        //this.navigator.setCanSwim(true);
       // this.tasks.addTask(1, new EntityAIWanderSwim(this, 1, 1));
        //this.tasks.addTask(2, new EntityAIFindWater(this));

        this.tasks.addTask(0, new EntityAIFollowOwner(this, 1, 2, 10));
        this.tasks.addTask(9, new EntityAIMate(this, 1));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.4d));
        // this.tasks.addTask(4, new EntityAITempt(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        // this.tasks.addTask(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound output) {
        super.writeEntityToNBT(output);
        output.setBoolean("isServerEntity", true);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound input) {
        super.readEntityFromNBT(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void updateAITick() {
        super.updateAITick();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.riddenByEntity == owner) {
                if (owner.isSneaking() && owner.isJumping) {
                    this.riddenByEntity = (null);
                    this.setVelocity(this.motionX, this.motionY + 0.1, this.motionZ);
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;
            net.minecraft.util.Vec3 ownerPos = owner.getPosition(1.0F).addVector(0, owner.getEyeHeight() * 0.8, 0);
            net.minecraft.util.Vec3 vecToOwner = ownerPos.subtract(this.getPosition(1.0F));
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.getDistance(owner);
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + (-this.getOwner().rotationPitch);
            float bodyYawDiff = net.minecraft.util.MathHelper.wrapAngleTo180_float(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.limbSwingAmount = (0.5F);

                net.minecraft.util.Vec3 dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp_float(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                this.setVelocity(dir.xCoord * speed, dir.yCoord * speed, dir.zCoord * speed);
            } else {

                this.setVelocity(this.motionX * 0.8, this.motionY * 0.8, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1) {
                this.jump();
            }

            if (yHeightToOwner > -1 || this.isCollidedHorizontally) {
                this.setVelocity(this.motionX, this.motionY - 0.01, this.motionZ);
            }

            if (!this.onGround) {
                // this.processFlappingMovement();
            }

            if (owner.motionX * owner.motionX + owner.motionY * owner.motionY + owner.motionZ * owner.motionZ < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp_float(this.rotationYawHead - this.renderYawOffset, -10, 10);
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.playSound("mob.squid.ambient", 1.0f, 1.0f);
        }
    }
}
