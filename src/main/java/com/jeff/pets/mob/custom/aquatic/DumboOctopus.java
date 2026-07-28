package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import static com.jeff.pets.PetsInitializer.DUMBO_OCTOPUS;

public class DumboOctopus extends FlyingPet {

    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            net.minecraft.network.datasync.EntityDataManager.defineId(DumboOctopus.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);
    public static final net.minecraft.network.datasync.DataParameter<Integer> OCTOPUS_SKIN =
            net.minecraft.network.datasync.EntityDataManager.defineId(DumboOctopus.class, net.minecraft.network.datasync.DataSerializers.INT);
    private final float nextFlap = 1.0F;
    public float tentacleAngle = 0;
    public ServerPlayerEntity owner = (ServerPlayerEntity) this.getOwner();

    public DumboOctopus(final EntityType<? extends DumboOctopus> type, final World level) {
        super(type, level);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0f);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OCTOPUS_SKIN, 1);
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
        return SoundEvents.SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    public DumboOctopus getBreedOffspring(final ServerWorld level, final AgeableEntity partner) {
        DumboOctopus octopus = DUMBO_OCTOPUS.create(level);
        octopus.setServerEntity(true);
        return octopus;
    }

    public ILivingEntityData finalizeSpawn(final IWorld level, final DifficultyInstance difficulty, SpawnReason mobSpawnType, final ILivingEntityData groupData, CompoundNBT compoundTag) {
        this.setServerEntity(true);
        this.entityData.set(OCTOPUS_SKIN, this.random.nextInt(6));
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

        this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goalSelector.addGoal(9, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4d));
        // this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        // this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            net.minecraft.util.math.Vec3d ownerPos = owner.position().add(0, owner.getEyeHeight() * 0.8, 0);
            net.minecraft.util.math.Vec3d vecToOwner = ownerPos.subtract(this.position());
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

                net.minecraft.util.math.Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYBodyRot(Duck.rotlerp(this.yBodyRot, (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                this.setDeltaMovement(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

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
                this.teleportTo(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this.x, this.y, this.z, SoundEvents.SQUID_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("floatiant", this.entityData.get(OCTOPUS_SKIN));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.entityData.set(OCTOPUS_SKIN, input.getInt("floatiant"));
    }

    @Override
    public void onSyncedDataUpdated(net.minecraft.network.datasync.DataParameter<?> key) {
        if (!this.level.isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        if (this.level.isClientSide()) {
            return new SSpawnObjectPacket(this);
        } else {
            return super.getAddEntityPacket();
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}