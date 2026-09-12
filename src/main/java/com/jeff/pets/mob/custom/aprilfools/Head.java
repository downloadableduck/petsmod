package com.jeff.pets.mob.custom.aprilfools;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class Head extends AbstractPet {
    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            EntityDataManager.createKey(Head.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);

    public Head(final World level) {
        super(level);
        this.setSize(0.5f, 0.5f);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable AgableMob) {
        return new Head(AgableMob.world);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.dataManager.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.dataManager.set(IS_SERVER_ENTITY, value);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!this.onGround && this.motionY < (double) 0.0F) {
            this.setVelocity(this.motionX * 1.0F, this.motionY * 0.6, this.motionZ * 1.0F);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.isItemEqual(new ItemStack(Items.BREAD));
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData groupData) {
        this.setServerEntity(true);
        return super.func_180482_a(difficulty, groupData);
    }

    @Override
    public void initEntityAI() {

        this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.4d));
        this.tasks.addTask(4, new EntityAITempt(this, 1.0f, Items.BREAD, false));

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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_STEP;
    }

    @Override
    public void tick() {
        super.tick();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (owner.isRidingOrBeingRiddenBy(this)) {
                if (owner.isSneaking() && Utils.isJumping(owner)) {
                    this.stopRiding();
                    this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
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
            float bodyYawDiff = MathHelper.wrapDegrees(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.limbSwingAmount = (0.5F);

                Vec3d targetPos = owner.getPositionVector();
                Vec3d dir = targetPos.subtract(this.getPositionVector()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                double speed = 0.15;
                this.setVelocity(dir.x * speed, this.motionY, dir.z * speed);
            } else {

                this.setVelocity(this.motionX * 0.8, this.motionY, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1) {
                this.jump();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.motionX, this.motionY - 0.01, this.motionZ);
                // t/his.processFlappingMovement();
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setRotationYawHead(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -10, 10);
            }

            this.move(this.motionX, this.motionY, this.motionZ);

            if (!this.onGround) {
                this.setVelocity(this.motionX, this.motionY - 0.04, this.motionZ);
            }
        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }

        /*if (this.walkAnimation.isMoving()) {
            level.playLocalSound(this, SoundEvents., SoundCategory.NEUTRAL, 1.0f, 1.0f);
        }*/

        /*int ambient = (int) (Math2.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this, PetsSounds.PENGUIN_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        }*/
    }

    @Override
    public void notifyDataManagerChange(net.minecraft.network.datasync.DataParameter<?> key) {
        if (!this.world.isRemote) {
            super.notifyDataManagerChange(key);
        }
    }

    @Override
    public void writeAdditional(NBTTagCompound output) {
        super.writeAdditional(output);
        output.setBoolean("isServerEntity", true);
    }

    @Override
    public void readAdditional(NBTTagCompound input) {
        super.readAdditional(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}
