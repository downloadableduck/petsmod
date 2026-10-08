package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;

import net.minecraft.entity.EntityLiving;

import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;

public class DumboOctopus extends FlyingPet {

    public static final int IS_SERVER_ENTITY = 20;
    public static final int OCTOPUS_SKIN = 21;
    private final float nextFlap = 1.0F;
    public float tentacleAngle = 0;
    public EntityPlayerMP owner = (EntityPlayerMP) this.getOwner();

    public DumboOctopus(final World level) {
        super(level);
        this.setSize(0.5F, 0.5F);
        this.setAIMoveSpeed(0.3f);
        this.initGoals();
    }

    @Override
    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(OCTOPUS_SKIN, 1);
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
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    protected String getAmbientSound() {
        return "mob.squid.ambient";
    }

    protected String getHurtSound(final @NotNull DamageSource source) {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected String getDeathSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final int x, final int y, final int z, final Block blockState) {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    public @Nullable DumboOctopus createChild(final @NotNull EntityAgeable partner) {
        return null;
    }

public DataWatcher initialize(final @NotNull EnumDifficulty difficulty, final @Nullable DataWatcher groupData) {
        this.setServerEntity(true);
        this.dataWatcher.updateObject(OCTOPUS_SKIN, this.rand.nextInt(6));
        return groupData;
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return ItemStack.areItemStacksEqual(itemStack, new ItemStack(Items.fish, 1, 2)) || ItemStack.areItemStacksEqual(itemStack, new ItemStack(Items.fish, 1, 0)) || ItemStack.areItemStacksEqual(itemStack, new ItemStack(Items.fish, 1, 1));
    }

public void initGoals() {

        this.tasks.addTask(2, new EntityAISwimming(this));

        this.tasks.addTask(0, new EntityAIFollowOwner(this, 1, 2, 10));
        this.tasks.addTask(9, new EntityAIMate(this, 1));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.4d));
        // this.tasks.addTask(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        // this.tasks.addTask(8, new EntityAIFollowOwner(this, 1, 2, 10));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.ridingEntity == owner) {
                if (owner.isSneaking() && owner.isJumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().addVector(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;
            Vec3 ownerPos = Vec3.createVectorHelper(owner.posX, owner.posY, owner.posZ).addVector(0, owner.getEyeHeight() * 0.8, 0);
            Vec3 vecToOwner = ownerPos.subtract(this.getPos());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;


            double distance = MathHelper.sqrt_double(this.getDistanceSqToEntity(owner));
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + -this.getOwner().rotationPitch;
            float bodyYawDiff = MathHelper.wrapAngleTo180_float(this.headYaw - this.bodyYaw);

            if (rotationToOwner >= 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > 2.0) {

                this.setLimbDistance(0.5F);

                Vec3 dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp_float(this.headYaw - this.bodyYaw /*bodyYaw*/, -50, 50);

                this.setVelocity(-dir.xCoord * speed, dir.yCoord * speed, -dir.zCoord * speed);
            } else {
                //this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1 || this.isCollidedHorizontally) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().addVector(0, -0.01, 0));
            }

            if (Utils.squaredDistanceToOrigin(Vec3.createVectorHelper(owner.motionX, owner.motionY, owner.motionZ)) < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp_float(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
        }
        if (owner != null) {
            if (getDistanceToEntity(owner) >= 10) {
                this.requestTeleport(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.squid.ambient", 1.0f, 1.0f, true);
        }
    }

    @Override
    public void writeToNBT(@NotNull NBTTagCompound output) {
        super.writeToNBT(output);
        output.setBoolean("isServerEntity", true);
        output.setInteger("variant", this.dataWatcher.getWatchableObjectInt(OCTOPUS_SKIN));
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound input) {
        super.readFromNBT(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.dataWatcher.updateObject(OCTOPUS_SKIN, input.getInteger("variant"));
    }

    
    public boolean canBreatheInWater() {
        return true;
    }
}
