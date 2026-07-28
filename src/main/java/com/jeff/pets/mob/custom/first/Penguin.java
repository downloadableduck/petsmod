package com.jeff.pets.mob.custom.first;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;

import static com.jeff.pets.PetsInitializer.PENGUIN;

public class Penguin extends AbstractPet {
    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            net.minecraft.network.datasync.EntityDataManager.defineId(Penguin.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public ServerPlayerEntity owner = (ServerPlayerEntity) this.getOwner();
    public boolean isOnHead;
    private float nextFlap = 1.0F;
    private boolean isFlapping = !this.onGround;

    public Penguin(EntityType<? extends net.minecraft.entity.passive.TameableEntity> entityType, net.minecraft.world.World level) {
        super(entityType, level);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
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
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.entityData.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.entityData.set(IS_SERVER_ENTITY, value);
    }

    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = net.minecraft.util.math.MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        net.minecraft.util.math.Vec3d movement = this.getDeltaMovement();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.setDeltaMovement(movement.multiply(1.0F, 0.6, 1.0F));
        }

        this.flap += this.flapping * 2.0F;
    }

    protected boolean isFlapping() {
        return isFlapping;
    }

    protected void onFlap() {
        //this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return PetsSounds.PENGUIN_AMBIENT;
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return PetsSounds.PENGUIN_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return PetsSounds.PENGUIN_AMBIENT;
    }

    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    public Penguin getBreedOffspring(final AgeableEntity partner) {
        Penguin penguin = PENGUIN.create(level);
        penguin.setServerEntity(true);
        return penguin;
    }

    public ILivingEntityData finalizeSpawn(final IWorld level, final DifficultyInstance difficulty, SpawnReason mobSpawnType, final ILivingEntityData groupData, CompoundNBT compoundTag) {
        this.setServerEntity(true);
        return super.finalizeSpawn(level, difficulty, mobSpawnType, groupData, compoundTag);
    }

    public boolean isFood(final ItemStack itemStack) {
        return itemStack.sameItem(new ItemStack(Items.COD)) || itemStack.sameItem(new ItemStack(Items.SALMON)) || itemStack.sameItem(new ItemStack(Items.TROPICAL_FISH));
    }

    @Override
    public void registerGoals() {

        this.goalSelector.addGoal(1, new BreedGoal(this, 1));
        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, Ingredient.of(Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL), false));

        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                this.isFlapping = false;
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
                    this.isOnHead = false;
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationVector().x;
            float rotationToOwner = rotation + this.getOwner().getRotationVector().x;
            float bodyYawDiff = net.minecraft.util.math.MathHelper.wrapDegrees(this.getYHeadRot() - this.yBodyRot);

            if (rotationToOwner >= 50) {
                this.yBodyRot = this.getYHeadRot() - (net.minecraft.util.math.MathHelper.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.animationSpeed = (0.5F);

                net.minecraft.util.math.Vec3d targetPos = owner.position();
                net.minecraft.util.math.Vec3d dir = targetPos.subtract(this.position()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                double speed = owner.getSpeed() * 2;
                this.setDeltaMovement(dir.x * speed, this.getDeltaMovement().y, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.horizontalCollision && this.onGround) {
                this.jumpFromGround();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
                //this.processFlappingMovement();
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (owner.getDeltaMovement().lengthSqr() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setYHeadRot(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.yBodyRot = this.getYHeadRot() - (net.minecraft.util.math.MathHelper.sign(bodyYawDiff) * 50);
            } else {
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.getYHeadRot(), 10);
            }

            this.move(MoverType.SELF, this.getDeltaMovement());

            if (!this.onGround) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleportTo(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this.x, this.y, this.z, PetsSounds.PENGUIN_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 1.0f, true);
        }
    }

    @Override
    public void onSyncedDataUpdated(net.minecraft.network.datasync.DataParameter<?> key) {
        if (!this.level.isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}