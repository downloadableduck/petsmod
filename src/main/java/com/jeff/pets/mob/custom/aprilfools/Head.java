package com.jeff.pets.mob.custom.aprilfools;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.DataWatcher;

import net.minecraft.entity.EntityLiving;

import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;

public class Head extends AbstractPet {
    public static final int IS_SERVER_ENTITY = 20;
    public boolean isLoading = false;
    public ResourceLocation skin = new ResourceLocation("missingno");

    public Head(World level) {
        super(level);
        this.setSize(0.5F, 0.5F);
        this.initGoals();
    }

    public @Nullable EntityAnimal createChild(@NotNull EntityAgeable AgableMob) {
        return null;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(IS_SERVER_ENTITY, (byte) 0);
    }

    public boolean isServerEntity() {
        return this.dataWatcher.getWatchableObjectByte(IS_SERVER_ENTITY) != 0;
    }

    public void setServerEntity(Boolean value) {
        this.dataWatcher.updateObject(IS_SERVER_ENTITY, (byte) (value ? 1 : 0));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();

        Vec3 movement = this.getVelocity();
        if (!this.onGround && movement.yCoord < (double) 0.0F) {
            this.setVelocity(this.motionX, this.motionY * 0.6, this.motionZ);
        }
    }

    @Override
    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return ItemStack.areItemStacksEqual(itemStack, new ItemStack(Items.apple));
    }

public DataWatcher initialize(final @NotNull EnumDifficulty difficulty, final @Nullable DataWatcher groupData) {
        this.setServerEntity(true);
        return groupData;
    }

    public void initGoals() {

        this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.4d));
        this.tasks.addTask(4, new EntityAITempt(this, 1.0f, Items.apple, false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIFollowOwner(this, 1, 2, 10));
    }

    @Override
    protected int stopDistance() {
        return 0;
    }

    @Override
    protected float heartHeight() {
        return 0;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.chicken.step";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.ridingEntity == owner) {
                if (owner.isSneaking() && owner.isJumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().addVector(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;



            double distance = MathHelper.sqrt_double(this.getDistanceSqToEntity(owner));
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + -this.getOwner().rotationPitch;
            float bodyYawDiff = MathHelper.wrapAngleTo180_float(this.headYaw - this.bodyYaw);

            if (rotationToOwner >= 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > 2.0) {

                this.setLimbDistance(0.5F);

                Vec3 targetPos = Vec3.createVectorHelper(owner.posX, owner.posY, owner.posZ);
                Vec3 dir = targetPos.subtract(this.getPos()).normalize();

                double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;
                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp_float(this.headYaw - this.bodyYaw /*bodyYaw*/, -50, 50);

                double speed = owner.getAIMoveSpeed() * 2;
                this.setVelocity(-dir.xCoord * speed, this.getVelocity().yCoord, -dir.zCoord * speed);
            } else {
                //this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (this.isCollidedHorizontally && this.onGround) {
                this.jump();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().addVector(0, -0.01, 0));
                //this.processFlappingMovement();
            }

            if (!this.onGround) {
                // this.processFlappingMovement();
            }

            if (Utils.squaredDistanceToOrigin(Vec3.createVectorHelper(owner.motionX, owner.motionY, owner.motionZ)) < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                this.bodyYaw = this.bodyYaw + MathHelper.clamp_float(this.headYaw - this.bodyYaw, -10, 10);
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().addVector(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (getDistanceToEntity(owner) >= 10) {
                this.requestTeleport(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.worldObj.playSound(this.posX, this.posY, this.posZ, PetsSounds.DUCK_AMBIENT, 1.0f, 1.0f, true);
        }
    }

    
    @Override
    public void writeToNBT(@NotNull NBTTagCompound output) {
        super.writeToNBT(output);
        output.setBoolean("isServerEntity", true);
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound input) {
        super.readFromNBT(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}
