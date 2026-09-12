package com.jeff.pets.mob.custom.first;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.EntityData;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataAttribute;
import net.minecraft.entity.data.DataSerializers;
import net.minecraft.entity.data.SyncedData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableSet;

public class Duck extends AbstractPet {

    public static final DataAttribute<@NotNull Boolean> IS_SERVER_ENTITY =
            SyncedData.registerSerializer(Duck.class, DataSerializers.BOOLEAN);
    public static final DataAttribute<@NotNull Integer> DUCK_SKIN =
            SyncedData.registerSerializer(Duck.class, DataSerializers.INTEGER);
    private final float flyDist = 0;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public boolean isOnHead;
    public ServerPlayerEntity owner = (ServerPlayerEntity) this.getOwner();
    private float nextFlap = 1.0F;

    public Duck(final World level) {
        super(level);
    }

    public static float rotlerp(float start, float end) {
        float f = MathHelper.wrapDegrees(end - start);
        if (f > 10.0f) f = 10.0f;
        if (f < -10.0f) f = -10.0f;
        return start + f;
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(0.25);
    }

    @Override
    protected void registerSyncedData() {
        super.registerSyncedData();
        this.syncedData.register(DUCK_SKIN, 1);
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
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3d movement = this.getVelocity();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.lerpVelocity(this.velocityX, this.velocityY, this.velocityZ);
        }

        this.flap += this.flapping * 2.0F;
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    protected SoundEvent getAmbientSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected SoundEvent getHurtSound(final @NotNull DamageSource source) {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final @NotNull BlockPos pos, final @NotNull BlockState blockState) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    public @Nullable Duck makeChild(final @NotNull PassiveEntity partner) {
        Duck duck = new Duck(world);
        duck.setServerEntity(true);
        return duck;
    }

    public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData) {
        this.setServerEntity(true);
        this.syncedData.set(DUCK_SKIN, this.random.nextInt(2));
        return super.initialize(difficulty, groupData);
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return itemStack.matchesItemIgnoreDamage(new ItemStack(Items.FISH, 1, 0)) || itemStack.matchesItemIgnoreDamage(new ItemStack(Items.FISH, 1, 1)) || itemStack.matchesItemIgnoreDamage(new ItemStack(Items.FISH, 1, 2));
    }

    @Override
    public void initGoals() {

        this.goalSelector.addGoal(1, new WanderAroundGoal(this, 1.0D));

        this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goalSelector.addGoal(9, new AnimalBreedGoal(this, 1));
        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new EscapeDangerGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0d, false, ImmutableSet.of(Items.SKULL)));

        this.goalSelector.addGoal(5, new LookAroundGoal(this));
        this.goalSelector.addGoal(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new EatGrassGoal(this));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void tick() {
        super.tick();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = net.minecraft.util.math.MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        net.minecraft.util.math.Vec3d movement = new Vec3d(this.velocityX, this.velocityY, this.velocityZ);
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.lerpVelocity(movement.x * 1.0F, movement.y * 0.6, movement.z * 1.0F);
        }

        this.flap += this.flapping * 2.0F;

        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
                    this.isOnHead = false;
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
                this.bodyYaw /*bodyYaw*/ = this.headYaw - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtract(this.getPosVec()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));

                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -50, 50);

                double speed = owner.getSpeed() * 2;
                this.lerpVelocity(new Vec3d(dir.x * speed, this.getVelocity().y, dir.z * speed));
            } else {
                this.lookAt(this.getOwner(), 5, 0);
                this.lerpVelocity(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.collidingHorizontally && this.onGround) {
                this.jump();
                //this.processFlappingMovement();
            }


            if (yHeightToOwner > -1) {
                this.lerpVelocity(this.getVelocity().add(0, -0.01, 0));
                //this.processFlappingMovement();
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

            //
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            //

            this.move(this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);

            if (!this.onGround) {
                this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, PetsSounds.DUCK_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }

    @Override
    public void writeCustomNbt(@NotNull NbtCompound output) {
        super.writeCustomNbt(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("variant", this.syncedData.get(DUCK_SKIN));
    }

    @Override
    public void readCustomNbt(@NotNull NbtCompound input) {
        super.readCustomNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.syncedData.set(DUCK_SKIN, input.getInt("variant"));
    }

    @Override
    public void onDataValueChanged(@NotNull DataAttribute<?> key) {
        if (!this.world.isClient) {
            super.onDataValueChanged(key);
        }
    }
}