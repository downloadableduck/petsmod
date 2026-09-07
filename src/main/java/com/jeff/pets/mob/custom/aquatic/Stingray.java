package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class Stingray extends AbstractPet {
    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            net.minecraft.network.datasync.EntityDataManager.createKey(Stingray.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);
    private final float nextFlap = 1.0F;
    public float oFlap;
    public float flap;
    public float flapping = 1.0F;

    public Stingray(World level) {
        super(level);
        this.setSize(1.0f, 0.4f);
        this.setPathPriority(PathNodeType.WATER, 0);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
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
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        if (!this.onGround && this.motionY < (double) 0.0F) {
            this.setVelocity(this.motionX * 1.0F, this.motionY * 0.6, this.motionZ * 1.0F);
        }

        this.flap += this.flapping * 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.ENTITY_SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    protected void playStepSound(final BlockPos pos, final IBlockState blockState) {
        this.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 0.15F, 1.0F);
    }

    public Stingray createChild(final EntityAgeable partner) {
        Stingray stringray = new Stingray(this.world);
        stringray.setServerEntity(true);
        return stringray;
    }

    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData groupData) {
        this.setServerEntity(true);
        return super.func_180482_a(difficulty, groupData);
    }

    public boolean isBreedingItem(final ItemStack itemStack) {
        return false;
    }

    @Override
    public void initEntityAI() {

        /**Using false in this statement causes the mob to sink to the bottom and reptitively spin.*/
        //this.moveControl = new SmoothSwimmingMoveControl(this, 10, 10, 1, 1, true);
        //this.navigator.setCanSwim(true);
        //this.tasks.addTask(1, new EntityAIWanderSwim(this, 1, 1));
        //this.tasks.addTask(2, new EntityAIFindWater(this));

        this.tasks.addTask(0, new EntityAIFollowOwner(this, 1, 2, 10));
        this.tasks.addTask(9, new EntityAIMate(this, 1));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.4d));
        // this.tasks.addTask(4, new EntityAITempt(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.tasks.addTask(5, new EntityAILookIdle(this));
        // this.tasks.addTask(8, new FollowOwnerGoal(this, 1, 2, 10));
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
    public void tick() {
        super.tick();
        this.oFlap = this.flap;
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;

        this.flap += this.flapping * 2.0F;
        EntityLivingBase owner = this.getOwner();
        if (owner != null) {

            if (owner.isRidingOrBeingRiddenBy(this)) {
                if (owner.isSneaking() && owner.isJumping) {
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
            Vec3d dir = vecToOwner.normalize();

            float targetYaw = (float) (Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;

            double distance = this.getDistance(owner);
            float rotation = -this.rotationPitch;
            float rotationToOwner = rotation + (-this.getOwner().rotationPitch);
            float bodyYawDiff = net.minecraft.util.math.MathHelper.wrapDegrees(this.rotationYawHead - this.renderYawOffset);

            if (rotationToOwner >= 50) {
                this.renderYawOffset = this.rotationYawHead - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.limbSwingAmount = (0.5F);

                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
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
}
