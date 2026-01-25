package com.jeff.duck;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.parrot.ShoulderRidingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import static com.jeff.duck.DuckInitializer.DUCK;

public class Duck extends ShoulderRidingEntity {

    public static final EntityDataAccessor<@NotNull Boolean> IS_SERVER_ENTITY =
            SynchedEntityData.defineId(Duck.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<@NotNull Integer> DUCK_SKIN =
            SynchedEntityData.defineId(Duck.class, EntityDataSerializers.INT);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;
    public Duck(final EntityType<? extends @NotNull Duck> type, final Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 500.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DUCK_SKIN, 1);
        builder.define(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.entityData.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.entityData.set(IS_SERVER_ENTITY, value);
    }

    public float rotlerp(float start, float end) {
        float f = Mth.wrapDegrees(end - start);
        if (f > 10.0f) f = 10.0f;
        if (f < -10.0f) f = -10.0f;
        return start + f;
    }

    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 movement = this.getDeltaMovement();
        if (!this.onGround() && movement.y < (double) 0.0F) {
            this.setDeltaMovement(movement.multiply(1.0F, 0.6, 1.0F));
        }

        this.flap += this.flapping * 2.0F;
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return DuckSounds.DUCK_AMBIENT;
    }

    protected SoundEvent getHurtSound(final @NotNull DamageSource source) {
        return DuckSounds.DUCK_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return DuckSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final @NotNull BlockPos pos, final @NotNull BlockState blockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    public @Nullable Duck getBreedOffspring(final @NotNull ServerLevel level, final @NotNull AgeableMob partner) {
        return DUCK.create(level, EntitySpawnReason.BREEDING);
    }

    public SpawnGroupData finalizeSpawn(final @NotNull ServerLevelAccessor level, final @NotNull DifficultyInstance difficulty, final @NotNull EntitySpawnReason spawnReason, final @Nullable SpawnGroupData groupData) {
        this.setServerEntity(true);
        this.entityData.set(DUCK_SKIN, this.random.nextInt(2));
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(ItemTags.CHICKEN_FOOD);
    }

    @Override
    public void registerGoals() {

        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.SKULLS), false));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new EatBlockGoal(this));
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
                        0, 1, 0
                );
            }
        }

        if (this.isTame() && this.isOwnedBy(player)) {
            CompoundTag duckTag = new CompoundTag();

            if (!this.level().isClientSide()) {

                if (((net.minecraft.server.level.ServerPlayer) player).setEntityOnShoulder(duckTag)) {
                    this.tame(player);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationVector().x;
            var rotationToOwner = rotation + this.getOwner().getRotationVector().x;
            float horizontalDistance = (float) this.getDeltaMovement().horizontalDistance();
            float animationSpeed = 1.0f;
            float bodyYawDiff = Mth.wrapDegrees(this.getYHeadRot() - this.yBodyRot);

            if (rotationToOwner >= 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.walkAnimation.update(animationSpeed, horizontalDistance, 0.7f);
                this.walkAnimation.setSpeed(0.7F);

                Vec3 targetPos = owner.position();
                Vec3 dir = targetPos.subtract(this.position()).normalize();

                this.setYRot(rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                double speed = 0.15;
                this.setDeltaMovement(dir.x * speed, this.getDeltaMovement().y, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.walkAnimation.update(horizontalDistance, animationSpeed, 0f);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1) {
                this.jumpFromGround();
            }

            if (!this.onGround()) {
                this.processFlappingMovement();
            }
            this.setYRot(rotlerp(this.getYRot(), (float) targetYaw));
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
            level().playSound(this, this.blockPosition(), SoundEvents.CHICKEN_STEP, SoundSource.NEUTRAL);
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level().playLocalSound(this, DuckSounds.DUCK_AMBIENT, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        if (!this.level().isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    @Override
    public @NotNull Packet<@NotNull ClientGamePacketListener> getAddEntityPacket(@NotNull ServerEntity serverEntity) {
        if (this.level().isClientSide()) {
            return new ClientboundAddEntityPacket(this, serverEntity);
        } else {
            return super.getAddEntityPacket(serverEntity);
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("variant", this.entityData.get(DUCK_SKIN));
    }

    @Override
    public void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBooleanOr("isServerEntity", true));
        this.entityData.set(DUCK_SKIN, input.getIntOr("variant", 1));

    }
}
