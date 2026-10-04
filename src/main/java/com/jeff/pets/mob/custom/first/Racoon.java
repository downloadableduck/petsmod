package com.jeff.pets.mob.custom.first;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;

import net.minecraft.entity.ai.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Racoon extends AbstractPet {

    private static final int IS_SERVER_ENTITY = 20;
    public boolean isOnHead;

    public Racoon(World level) {
        super(level);
        this.setSize(1.0f, 1.0f);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.8f;
    }

    @Override
    protected String getLivingSound() {
        return "mob.chicken.step";
    }


    @Override
    public void initEntityAI() {

        this.tasks.addTask(1, new EntityAIMate(this, 1));
        this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.4d));
        this.tasks.addTask(4, new EntityAITempt(this, 1.0f, Items.beef, false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIFollowOwner(this, 1, 2, 10));
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

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.isItemEqual(new ItemStack(Items.beef));
    }

    @Override
    public void updateAITick() {
        super.updateAITick();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.riddenByEntity == owner) {
                if (owner.isSneaking() && owner.isJumping) {
                    this.riddenByEntity = (null);
                    this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
                    this.isOnHead = false;
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;

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

                net.minecraft.util.Vec3 targetPos = owner.getPosition(1.0F);
                net.minecraft.util.Vec3 dir = targetPos.subtract(this.getPosition(1.0F)).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp_float(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                double speed = owner.getAIMoveSpeed() * 2;
                this.setVelocity(dir.xCoord * speed, this.motionY, dir.zCoord * speed);
            } else {

                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (this.isCollidedHorizontally && this.onGround) {
                this.jump();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.motionX, this.motionY - 0.01, this.motionZ);
                //this.processFlappingMovement();
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

            if (!this.onGround) {
                this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
            }
        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable AgableMob) {
        Racoon racoon = new Racoon(this.worldObj);
        racoon.setServerEntity(false);
        return racoon;
    }

    /*@Override
    public Packet<?> getAddEntityPacket() {
        if (this.worldObj.isRemote()) {
            return new SPacketSpawnObject(this);
        } else {
            return super.getAddEntityPacket();
        }
    }*/
}
