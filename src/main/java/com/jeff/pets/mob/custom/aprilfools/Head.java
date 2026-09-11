package com.jeff.pets.mob.custom.aprilfools;

import com.jeff.pets.mob.AbstractPet;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.data.DataAttribute;
import net.minecraft.entity.data.DataSerializers;
import net.minecraft.entity.data.SyncedData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.particle.ParticleType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Head extends AbstractPet {
    public static final DataAttribute<@NotNull Boolean> IS_SERVER_ENTITY =
            SyncedData.registerSerializer(Head.class, DataSerializers.BOOLEAN);

    public Head(final World level) {
        super(level);
    }

    @Override
    public @Nullable PassiveEntity makeChild(@NotNull PassiveEntity AgableMob) {
        return new Head(world);
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

        Vec3d movement = this.getVelocity();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.lerpVelocity(this.velocityX * 1.0F, this.velocityY * 0.6, this.velocityZ * 1.0F);
        }
    }

    @Override
    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return itemStack.matchesItemIgnoreDamage(new ItemStack(Items.CARROT));
    }

    @Override
    public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData) {
        this.setServerEntity(true);
        return super.initialize(difficulty, groupData);
    }

    @Override
    public void initGoals() {

        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new EscapeDangerGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0d, false, ImmutableSet.of(Items.CARROT)));

        this.goalSelector.addGoal(5, new LookAroundGoal(this));
        this.goalSelector.addGoal(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    protected int stopDistance() {
        return 0;
    }

    @Override
    protected float heartHeight() {
        return 0;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_STEP;
    }

    @Override
    public boolean interactMob(@NotNull PlayerEntity player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        double x = this.x;
        double y = this.y;
        double z = this.z;

        if (!this.isTamed() && this.isBreedingItem(itemStack)) {
            if (this.random.nextInt(3) == 0) {
                this.getNavigation().stop();
                this.world.addParticle(
                        ParticleType.HEART,

                        x + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        y + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        z + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        0, 5, 0
                );
            }
        }

        if (this.isTamed() && itemStack.isEmpty()) {
            this.world.addParticle(
                    ParticleType.HEART,
                    this.x,
                    this.y + 1,
                    this.z,
                    5, 5, 5
            );
        }

        if (this.isTamed() && itemStack.isEmpty() && player.isSneaking()) {
            if (!this.isRiding()) {
                this.startRiding(player);
                this.lookAt(player, 1f, 1f);
                this.setSitting(true);
            } else {
                this.stopRiding();
            }
        }
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
                    this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

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

                Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtract(this.getPosVec()).normalize();

                
                
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -50, 50);

                double speed = 0.15;
                this.lerpVelocity(new Vec3d(dir.x * speed, this.getVelocity().y, dir.z * speed));
            } else {
                                this.lerpVelocity(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityY* 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1) {
                this.jump();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.lerpVelocity(this.getVelocity().add(0, -0.01, 0));
                // t/his.processFlappingMovement();
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }
            
            

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.move(MoverType.SELF, this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);

            if (!this.onGround) {
                this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }

        /*if (this.walkAnimation.isMoving()) {
            level.playLocalSound(this, SoundEvents., SoundSource.NEUTRAL, 1.0f, 1.0f);
        }*/

        /*int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this, PetsSounds.PENGUIN_AMBIENT, SoundSource.NEUTRAL, 1.0f, 1.0f);
        }*/
    }

    @Override
    public void onDataValueChanged(@NotNull DataAttribute<?> key) {
        if (!this.world.isClient) {
            super.onDataValueChanged(key);
        }
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
}