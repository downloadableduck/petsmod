package com.jeff.pets.mob.custom.first;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Penguin extends AbstractPet {
    private static final int IS_SERVER_ENTITY = 20;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public EntityPlayerMP owner = (EntityPlayerMP) this.getOwner();
    public boolean isOnHead;
    private final float nextFlap = 1.0F;
    private boolean isFlapping = !this.onGround;

    public Penguin(World level) {
        super(level);
        this.setSize(1.0f, 1.5f);
    }


    @Override
    protected int stopDistance() {
        return 0;
    }

    @Override
    protected float heartHeight() {
        return 1.3f;
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
    public void updateAITick() {
        super.updateAITick();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = net.minecraft.util.MathHelper.clamp_float(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        net.minecraft.util.Vec3 movement = Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ);
        if (!this.onGround && movement.yCoord < (double) 0.0F) {
            this.setVelocity(movement.xCoord * 1.0F, movement.yCoord * 0.6, movement.zCoord * 1.0F);
        }

        this.flap += this.flapping * 2.0F;
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (this.riddenByEntity == owner) {
                this.isFlapping = false;
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
                //this.processFlappingMovement();
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

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            //this.worldObj.playLocalSound(this.posX, this.posY, this.posZ, PetsSounds.PENGUIN_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 1.0f, true);
        }
    }

    protected boolean isFlapping() {
        return isFlapping;
    }

    protected void onFlap() {
        //this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected String getLivingSound() {
        return PetsSounds.PENGUIN_AMBIENT;
    }

    protected String func_70621_aR() {
        return PetsSounds.PENGUIN_AMBIENT;
    }

    protected String func_70673_aS() {
        return PetsSounds.PENGUIN_AMBIENT;
    }


    public Penguin createChild(final EntityAgeable partner) {
        Penguin penguin = new Penguin(this.worldObj);
        penguin.setServerEntity(true);
        return penguin;
    }


    public boolean isBreedingItem(final ItemStack itemStack) {
        return false;
    }

    @Override
    public void initEntityAI() {

        this.tasks.addTask(1, new EntityAIMate(this, 1));
        this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.4d));
        this.tasks.addTask(4, new EntityAITempt(this, 1.0D, Items.fish, false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIFollowOwner(this, 1, 2, 10));
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
}
