package com.jeff.pets.custom;

import com.jeff.pets.PetsSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class Racoon extends TamableAnimal {
    public static final EntityDataAccessor<@NotNull Boolean> IS_SERVER_ENTITY =
            SynchedEntityData.defineId(Racoon.class, EntityDataSerializers.BOOLEAN);

    public Racoon(final EntityType<? extends @NotNull Racoon> type, final Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.entityData.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.entityData.set(IS_SERVER_ENTITY, value);
    }

    public void aiStep() {
        super.aiStep();

        Vec3 movement = this.getDeltaMovement();
        if (!this.onGround() && movement.y < (double) 0.0F) {
            this.setDeltaMovement(movement.multiply(1.0F, 0.6, 1.0F));
        }
    }

    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return itemStack.is(Items.CAKE);
    }

    @Override
    public SpawnGroupData finalizeSpawn(final @NotNull ServerLevelAccessor level, final @NotNull DifficultyInstance difficulty, final @NotNull EntitySpawnReason spawnReason, final @Nullable SpawnGroupData groupData) {
        this.setServerEntity(true);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    @Override
    public void registerGoals() {

        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, stack -> stack.is(Items.CAKE), false));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        var x = this.getX();
        var y = this.getY();
        var z = this.getZ();

        if (!this.isTame() && this.isFood(itemStack)) {
            if (this.random.nextInt(3) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.level().addParticle(
                        ParticleTypes.HEART,

                        x + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        y + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        z + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        0, 5, 0
                );
            }
        }

        if (this.isTame() && itemStack.isEmpty()) {
            this.level().addParticle(
                    ParticleTypes.HEART,
                    this.getX(),
                    this.getY() + 1,
                    this.getZ(),
                    5, 5, 5
            );
        }

        if (this.isTame() && itemStack.isEmpty() && player.isShiftKeyDown()) {
            if (!this.isPassenger()) {
                this.startRiding(player);
                this.lookAt(player, 1f, 1f);
                this.setOrderedToSit(true);
            } else {
                this.stopRiding();
                System.out.println("hi");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isCrouching() && owner.isJumping()) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
                } else {
                    this.setOrderedToSit(true);
                }
            }

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationVector().x;
            var rotationToOwner = rotation + this.getOwner().getRotationVector().x;
            float bodyYawDiff = Mth.wrapDegrees(this.getYHeadRot() - this.yBodyRot);

            if (rotationToOwner >= 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.walkAnimation.setSpeed(0.5F);

                Vec3 targetPos = owner.position();
                Vec3 dir = targetPos.subtract(this.position()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                double speed = 0.15;
                this.setDeltaMovement(dir.x * speed, this.getDeltaMovement().y, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1) {
                this.jumpFromGround();
                this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
                this.processFlappingMovement();
            }

            if (!this.onGround()) {
                this.processFlappingMovement();
            }
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setYHeadRot(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50);
            } else {
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.getYHeadRot(), 10);
            }

            this.move(MoverType.SELF, this.getDeltaMovement());

            if (!this.onGround()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.tryToTeleportToOwner();
            }
        }

        if (this.walkAnimation.isMoving()) {
            level().playLocalSound(this, SoundEvents.CHICKEN_STEP, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level().playLocalSound(this, PetsSounds.PENGUIN_AMBIENT, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        if (!this.level().isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
    }

    @Override
    public void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBooleanOr("isServerEntity", true));
    }
}
