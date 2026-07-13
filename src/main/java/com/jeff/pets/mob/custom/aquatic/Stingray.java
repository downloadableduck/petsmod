package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

import static com.jeff.pets.PetsInitializer.Entities.STINGRAY;

public class Stingray extends FlyingPet {
    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            net.minecraft.network.datasync.EntityDataManager.defineId(Stingray.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);
    private final float nextFlap = 1.0F;
    public float oFlap;
    public float flap;
    public float flapping = 1.0F;

    public Stingray(EntityType<? extends net.minecraft.entity.passive.TameableEntity> type, net.minecraft.world.World level) {
        super(type, level);
        this.setPathfindingMalus(PathNodeType.WATER, 0);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return AnimalEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.25F);
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
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        net.minecraft.util.math.vector.Vector3d movement = this.getDeltaMovement();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.setDeltaMovement(movement.multiply(1.0F, 0.6, 1.0F));
        }

        this.flap += this.flapping * 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SQUID_DEATH;
    }

    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        this.playSound(SoundEvents.FISH_SWIM, 0.15F, 1.0F);
    }

    public Stingray getBreedOffspring(final ServerWorld level, final AgeableEntity partner) {
        Stingray stringray = STINGRAY.get().create(level);
        stringray.setServerEntity(true);
        return stringray;
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

        /**Using false in this statement causes the mob to sink to the bottom and reptitively spin.*/
        //this.moveControl = new SmoothSwimmingMoveControl(this, 10, 10, 1, 1, true);
        this.getNavigation().setCanFloat(true);
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1, 1));
        this.goalSelector.addGoal(2, new FindWaterGoal(this));

        this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1, 2, 10, false));
        this.goalSelector.addGoal(9, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4d));
        // this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        // this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
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
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isCrouching() && owner.jumping) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1, 0));
                } else {
                    this.setOrderedToSit(true);
                }
            }

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();
            net.minecraft.util.math.vector.Vector3d ownerPos = owner.position().add(0, owner.getEyeHeight() * 0.8, 0);
            net.minecraft.util.math.vector.Vector3d vecToOwner = ownerPos.subtract(this.position());
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

                net.minecraft.util.math.vector.Vector3d dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                this.setDeltaMovement(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jumpFromGround();
            }

            if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
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
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleportTo(owner.getX(), owner.getY(), owner.getZ());
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.SQUID_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
