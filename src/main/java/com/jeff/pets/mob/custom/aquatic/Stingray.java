package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff.pets.PetsInitializer.STINGRAY;

public class Stingray extends FlyingPet {
    public static final TrackedData<@NotNull Boolean> IS_SERVER_ENTITY =
            DataTracker.registerData(Stingray.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final float nextFlap = 1.0F;
    public float oFlap;
    public float flap;
    public float flapping = 1.0F;

    public Stingray(EntityType<? extends @NotNull TameableEntity> type, World level) {
        super(type, level);
        this.setPathNodeTypeWeight(PathNodeType.WATER, 0);
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.dataTracker.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.set(IS_SERVER_ENTITY, value);
    }

    public void tickMovement() {
        super.tickMovement();
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3d movement = this.getVelocity();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.setVelocity(movement.multiply(1.0F, 0.6, 1.0F));
        }

        this.flap += this.flapping * 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(final @NotNull DamageSource source) {
        return SoundEvents.ENTITY_SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    protected void playStepSound(final @NotNull BlockPos pos, final @NotNull BlockState blockState) {
        this.playSound(SoundEvents.ENTITY_FISH_SWIM, 0.15F, 1.0F);
    }

    public @Nullable Stingray createChild(final @NotNull PassiveEntity partner) {
        Stingray stringray = STINGRAY.create(world);
        stringray.setServerEntity(true);
        return stringray;
    }

    public @NotNull EntityData initialize(final @NotNull IWorld level, final @NotNull LocalDifficulty difficulty, final @NotNull SpawnType spawnReason, final @Nullable EntityData groupData, CompoundTag compoundTag) {
        this.setServerEntity(true);
        return super.initialize(level, difficulty, spawnReason, groupData, compoundTag);
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return itemStack.isEqualIgnoreDurability(new ItemStack(Items.TROPICAL_FISH)) || itemStack.isEqualIgnoreDurability(new ItemStack(Items.COD)) || itemStack.isEqualIgnoreDurability(new ItemStack(Items.SALMON));
    }

    @Override
    public void initGoals() {

        /**Using false in this statement causes the mob to sink to the bottom and reptitively spin.*/
        //this.moveControl = new SmoothSwimmingMoveControl(this, 10, 10, 1, 1, true);
        this.getNavigation().setCanSwim(true);
        this.goalSelector.add(1, new SwimAroundGoal(this, 1, 1));
        this.goalSelector.add(2, new MoveIntoWaterGoal(this));

        this.goalSelector.add(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goalSelector.add(9, new AnimalMateGoal(this, 1));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.4d));
        // this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.goalSelector.add(5, new LookAroundGoal(this));
        // this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void writeCustomDataToTag(@NotNull CompoundTag output) {
        super.writeCustomDataToTag(output);
        output.putBoolean("isServerEntity", true);
    }

    @Override
    public void readCustomDataFromTag(@NotNull CompoundTag input) {
        super.readCustomDataFromTag(input);
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
    public boolean canBreatheInWater() {
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
                if (owner.isInSneakingPose() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = owner.getPos().add(0, owner.getStandingEyeHeight() * 0.8, 0);
            Vec3d vecToOwner = ownerPos.subtract(this.getPos());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationClient().x;
            float rotationToOwner = rotation + this.getOwner().getRotationClient().x;
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.field_6283 /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.field_6283 /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.limbDistance = (0.5F);

                Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.field_6283 /*bodyYaw*/ = MathHelper.method_20306(this.field_6283 /*bodyYaw*/, this.headYaw, 50.0f);

                this.setVelocity(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {
                this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.getVelocity().multiply(0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (owner.getVelocity().lengthSquared() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.field_6283 /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.sign(bodyYawDiff) * 50);
            } else {
                this.field_6283 /*bodyYaw*/ = MathHelper.method_20306(this.field_6283 /*bodyYaw*/, this.getHeadYaw(), 10);
            }

            this.move(MovementType.SELF, this.getVelocity());
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, SoundEvents.ENTITY_SQUID_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
