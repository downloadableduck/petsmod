package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathBlockingType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataAttribute;
import net.minecraft.entity.data.DataSerializers;
import net.minecraft.entity.data.SyncedData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff.pets.PetsInitializer.KOI;

public class Koi extends FlyingPet {
    public static final DataAttribute<@NotNull Boolean> IS_SERVER_ENTITY =
            SyncedData.registerSerializer(Koi.class, DataSerializers.BOOLEAN);

    public Koi(EntityType<? extends @NotNull TameableEntity> type, World level) {
        super(type, level);
        this.setPathfindingPenalty(PathBlockingType.WATER, 0);
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(1);
    }

    @Override
    protected void registerSyncedData() {
        super.registerSyncedData();
        this.syncedData.register(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.syncedData.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.syncedData.set(IS_SERVER_ENTITY, value);
    }

    public void mobTick() {
        super.mobTick();
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(final @NotNull DamageSource source) {
        return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_DEATH;
    }

    protected void playStepSound(final @NotNull BlockPos pos, final @NotNull BlockState blockState) {
        this.playSound(SoundEvents.ENTITY_FISH_SWIM, 0.15F, 1.0F);
    }

    public @Nullable Koi makeChild(final @NotNull PassiveEntity partner) {
        Koi koi = KOI.create(world);
        koi.setServerEntity(true);
        return koi;
    }

    public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData, NbtCompound NbtCompound) {
        this.setServerEntity(true);
        return super.initialize(difficulty, groupData, NbtCompound);
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return itemStack.matchesItemIgnoreDamage(new ItemStack(Items.TROPICAL_FISH)) || itemStack.matchesItemIgnoreDamage(new ItemStack(Items.COD)) || itemStack.matchesItemIgnoreDamage(new ItemStack(Items.SALMON));
    }

    @Override
    public void initGoals() {

        /**Using false in this statement causes the mob to sink to the bottom and reptitively spin.*/
        //this.moveControl = new SmoothSwimmingMoveControl(this, 10, 10, 1, 1, true);
        this.getNavigation().setCanFloat(true);
        this.goalSelector.addGoal(1, new SwimAroundGoal(this, 1, 1));
        this.goalSelector.addGoal(2, new TryFindWaterGoal(this));

        this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goalSelector.addGoal(9, new AnimalBreedGoal(this, 1));
        this.goalSelector.addGoal(3, new EscapeDangerGoal(this, 1.4d));
        // this.goalSelector.addGoalGoal(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.goalSelector.addGoal(5, new LookAroundGoal(this));
        // this.goalSelector.addGoalGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void writeCustomNbt(@NotNull NbtCompound output) {
        super.writeCustomNbt(output);
        output.putBoolean("isServerEntity", true);
    }

    @Override
    public void readCustomNbt(@NotNull NbtCompound input) {
        super.readCustomNbt(input);
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
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.lerpVelocity(this.getVelocity().add(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = new Vec3d(owner.x, owner.y, owner.z).add(0, owner.getEyeHeight() * 0.8, 0);
            Vec3d vecToOwner = ownerPos.subtract(this.getPosVec());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = -this.pitch;
            float rotationToOwner = rotation + (-this.getOwner().pitch);
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;

                
                
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -50, 50);

                this.lerpVelocity(new Vec3d(dir.x * speed, dir.y * speed, dir.z * speed));
            } else {
                                this.lerpVelocity(this.getVelocity().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1) {
                this.jump();
            }

            if (yHeightToOwner > -1 || this.collidingHorizontally) {
                this.lerpVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (!this.onGround) {
                // this.processFlappingMovement();
            }

            if (new Vec3d(owner.velocityX, owner.velocityY, owner.velocityZ).squaredDistanceToOrigin() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.move(MoverType.SELF, this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, SoundEvents.ENTITY_SQUID_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}