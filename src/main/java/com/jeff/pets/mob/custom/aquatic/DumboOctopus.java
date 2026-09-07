package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class DumboOctopus extends FlyingPet {

    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            net.minecraft.network.datasync.EntityDataManager.createKey(DumboOctopus.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);
    public static final net.minecraft.network.datasync.DataParameter<Integer> OCTOPUS_SKIN =
            net.minecraft.network.datasync.EntityDataManager.createKey(DumboOctopus.class, net.minecraft.network.datasync.DataSerializers.VARINT);
    private final float nextFlap = 1.0F;
    public float tentacleAngle = 0;
    public EntityPlayerMP owner = (EntityPlayerMP) this.getOwner();

    public DumboOctopus(final World level) {
        super(level);
        this.setSize(0.5f, 0.5f);
        this.setPathPriority(PathNodeType.WATER, 0.0f);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(OCTOPUS_SKIN, 1);
        this.dataManager.register(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.dataManager.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.dataManager.set(IS_SERVER_ENTITY, value);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final BlockPos pos, final IBlockState blockState) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    public DumboOctopus createChild(final EntityAgeable partner) {
        DumboOctopus octopus = new DumboOctopus(this.world);
        octopus.setServerEntity(true);
        return octopus;
    }

    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData groupData) {
        this.setServerEntity(true);
        this.dataManager.set(OCTOPUS_SKIN, this.rand.nextInt(6));
        return super.func_180482_a(difficulty, groupData);
    }

    public boolean isBreedingItem(final ItemStack itemStack) {
        return false;
    }

    @Override
    public void initEntityAI() {

        /**Using false in this statement causes the mob to sink to the bottom and reptitively spin.*/
        //this.moveControl = new SmoothSwimmingMoveControl(this, 10, 10, 1, 1, true);
        //this.getNavigator().setCanSwim(true);
        //this.tasks.addTask(1, new EntityAIWanderSwim(this, 1, 1));
       // this.tasks.addTask(2, new EntityAIFindWater(this));

        this.tasks.addTask(0, new EntityAIFollowOwner(this, 1, 2, 10));
        this.tasks.addTask(9, new EntityAIMate(this, 1));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.4d));
        // this.tasks.addTask(4, new EntityAITempt(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        // this.tasks.addTask(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void tick() {
        super.tick();
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (owner.isRidingOrBeingRiddenBy(this)) {
                if (owner.isSneaking() && Utils.isJumping(owner)) {
                    this.stopRiding();
                    this.setVelocity(this.motionX, this.motionY + 0.1, this.motionZ);
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.posX - this.posX;
            double dz = owner.posZ - this.posZ;
            net.minecraft.util.math.Vec3d ownerPos = owner.getPositionVector().add(0, owner.getEyeHeight() * 0.8, 0);
            net.minecraft.util.math.Vec3d vecToOwner = ownerPos.subtract(this.getPositionVector());
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

                net.minecraft.util.math.Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setRenderYawOffset(Duck.rotlerp(this.renderYawOffset, (float) targetYaw));
                this.setRotationYawHead(this.getYRot());
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -50.0f, 50.0f);

                this.setVelocity(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {

                this.setVelocity(this.motionX * 0.8, this.motionY * 0.8, this.motionZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.posY - this.posY);

            if (yHeightToOwner > 1 || this.collidedHorizontally) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.motionX, this.motionY - 0.01, this.motionZ);
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
                this.renderYawOffset = this.renderYawOffset + MathHelper.clamp(this.rotationYawHead - this.renderYawOffset, -10, 10);
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        }
        if (owner != null) {
            if (getDistance(owner) >= 10) {
                this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.playSound(SoundEvents.ENTITY_SQUID_AMBIENT, 1.0f, 1.0f);
        }
    }

    @Override
    public void writeAdditional(NBTTagCompound output) {
        super.writeAdditional(output);
        output.setBoolean("isServerEntity", true);
        output.setInt("floatiant", this.dataManager.get(OCTOPUS_SKIN));
    }

    @Override
    public void readAdditional(NBTTagCompound input) {
        super.readAdditional(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.dataManager.set(OCTOPUS_SKIN, input.getInt("floatiant"));
    }

    @Override
    public void notifyDataManagerChange(net.minecraft.network.datasync.DataParameter<?> key) {
        if (!this.world.isRemote) {
            super.notifyDataManagerChange(key);
        }
    }

    /*
    @Override
    public Packet<?> getAddEntityPacket() {
        if (this.world.isRemote()) {
            return new SPacketSpawnObject(this);
        } else {
            return super.getAddEntityPacket();
        }
    }
    */

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}
