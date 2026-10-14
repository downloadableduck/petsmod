package com.jeff.pets.mob.custom.first;

import net.minecraft.src.*;
import com.jeff.pets.PetsSounds;
import com.jeff.pets.mob.AbstractPet;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class Duck extends AbstractPet {

    private static final int IS_SERVER_ENTITY = 20;
    private static final int DUCK_SKIN = 21;
    private final float flyDist = 0;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public boolean isOnHead;
    public EntityPlayerMP owner = (EntityPlayerMP) this.getOwner();
    private final float nextFlap = 1.0F;

    public Duck(final World level) {
        super(level);
        this.setSize(0.4f, 0.7f);
        this.texture = "/mob/duck/mallard_male.png";
    }

    public static float rotlerp(float start, float end) {
        float f = net.minecraft.src.MathHelper.wrapAngleTo180_float(end - start);
        if (f > 10.0f) f = 10.0f;
        if (f < -10.0f) f = -10.0f;
        return start + f;
    }


    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(DUCK_SKIN, 1);
        this.dataWatcher.addObject(IS_SERVER_ENTITY, Byte.valueOf((byte) 0));
    }

    public boolean isServerEntity() {
        return this.dataWatcher.getWatchableObjectByte(IS_SERVER_ENTITY) != 0;
    }

    public void setServerEntity(Boolean value) {
        this.dataWatcher.updateObject(IS_SERVER_ENTITY, Byte.valueOf((byte) (value.booleanValue() ? 1 : 0)));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = net.minecraft.src.MathHelper.clamp_float(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        net.minecraft.src.Vec3 movement = Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ);
        if (!this.onGround && movement.yCoord < (double) 0.0F) {
            this.setVelocity(movement.xCoord * 1.0F, movement.yCoord * 0.6, movement.zCoord * 1.0F);
        }

        this.flap += this.flapping * 2.0F;

        EntityLiving owner = this.getOwner();
        if (owner != null) {

            if (this.ridingEntity == owner) {
                if (owner.isSneaking() && this.isOwnerJumping(owner)) {
                    this.ridingEntity = (null);
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
            float bodyYawDiff = net.minecraft.src.MathHelper.wrapAngleTo180_float(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.limbSwingAmount = (0.5F);

                net.minecraft.src.Vec3 targetPos = owner.getPosition(1);
                net.minecraft.src.Vec3 dir = targetPos.subtract(this.getPosition(1.0F)).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp_float(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                double speed = owner.getAIMoveSpeed() * 2;
                this.setVelocity(-dir.xCoord * speed, this.motionY, -dir.zCoord * speed);
            } else {
                this.limbSwingAmount = (this.limbSwingAmount + 0.1f);

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
            //this.worldObj.playLocalSound(this.posX, this.posY, this.posZ, PetsSounds.DUCK_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 1.0f, true);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound output) {
        super.writeEntityToNBT(output);
        output.setBoolean("isServerEntity", true);
        output.setInteger("floatiant", this.dataWatcher.getWatchableObjectInt(DUCK_SKIN));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound input) {
        super.readEntityFromNBT(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.dataWatcher.updateObject(DUCK_SKIN, input.getInteger("floatiant"));
    }

    // @Override - does not exist as override in 1.13
    // public Packet<?> getAddEntityPacket() {
    //     if (this.worldObj.isRemote()) {
    //         return new SPacketSpawnObject(this, 1);
    //     } else {
    //         return super.getAddEntityPacket();
    //     }
    // }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected String getLivingSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    @Override
    public String getTexture() {
        String duckTexturePath;
        if (Objects.equals(CONFIG.duckSkin, "pekin")) {
            duckTexturePath = "/mob/duck/pekin.png";
        } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
            duckTexturePath = "/mob/duck/mallard_male.png";
        } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
            duckTexturePath = "/mob/duck/rubber.png";
        } else if (CONFIG.duckSkin.equals("bronze")) {
            duckTexturePath = "/mob/duck/bronze.png";
        } else {
            duckTexturePath = "/mob/duck/mallard_male.png";
        }
        return duckTexturePath;
    }
}
