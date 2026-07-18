package com.jeff.pets.mob.custom.first;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff.pets.PetsInitializer.RACOON;

public class Racoon extends AbstractPet {

    public static final TrackedData<@NotNull Boolean> IS_SERVER_ENTITY =
            DataTracker.registerData(Racoon.class, TrackedDataHandlerRegistry.BOOLEAN);
    public boolean isOnHead;

    public Racoon(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.8f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_STEP;
    }

    @Override
    public @Nullable EntityData initialize(@NotNull IWorld level, LocalDifficulty difficulty, SpawnType spawnReason, @Nullable EntityData groupData, CompoundTag compoundTag) {
        this.setServerEntity(true);
        return super.initialize(level, difficulty, spawnReason, groupData, compoundTag);
    }

    @Override
    public void initGoals() {

        this.goalSelector.add(1, new AnimalMateGoal(this, 1));
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.4d));
        this.goalSelector.add(4, new TemptGoal(this, 1.0f, Ingredient.ofItems(Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL), false));

        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(8, new FollowOwnerGoal(this, 1, 2, 10));
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

    @Override
    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {


            if (owner.hasPassenger(this)) {
                if (owner.isInSneakingPose() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, -0.04, 0));
                    this.isOnHead = false;
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

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

                Vec3d targetPos = owner.getPos();
                Vec3d dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.field_6283 /*bodyYaw*/ = MathHelper.method_20306(this.field_6283 /*bodyYaw*/, this.headYaw, 50.0f);

                double speed = owner.getMovementSpeed() * 2;
                this.setVelocity(dir.x * speed, this.getVelocity().y, dir.z * speed);
            } else {
                this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.getVelocity().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (owner.getVelocity().lengthSquared() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.field_6283 /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.sign(bodyYawDiff) * 50);
            } else {
                this.field_6283 /*bodyYaw*/ = MathHelper.method_20306(this.field_6283 /*bodyYaw*/, this.getHeadYaw(), 10);
            }

            this.move(MovementType.SELF, this.getVelocity());

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }

        /*int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this, SoundEvents.BOGGED_AMBIENT, SoundSource.AMBIENT, 1.0f, 1.0f);
        }*/
    }

    @Override
    public @Nullable PassiveEntity createChild(@NotNull PassiveEntity AgableMob) {
        Racoon racoon = RACOON.create(world);
        racoon.setServerEntity(false);
        return racoon;
    }

    @Override
    public void onTrackedDataSet(@NotNull TrackedData<?> key) {
        if (!this.world.isClient()) {
            super.onTrackedDataSet(key);
        }
    }

    @Override
    public @NotNull Packet<?> createSpawnPacket() {
        if (this.world.isClient()) {
            return new EntitySpawnS2CPacket(this);
        } else {
            return super.createSpawnPacket();
        }
    }
}