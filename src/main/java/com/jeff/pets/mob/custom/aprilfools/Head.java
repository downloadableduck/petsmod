package com.jeff.pets.mob.custom.aprilfools;

import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityData;

import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Head extends AbstractPet {
    public static final TrackedData<@NotNull Boolean> IS_SERVER_ENTITY =
            DataTracker.registerData(Head.class, TrackedDataHandlerRegistry.BOOLEAN);

    public Head(World level) {
        super(level);
        this.setBounds(0.5F, 0.5F);
    }

    public @Nullable PassiveEntity createChild(@NotNull PassiveEntity AgableMob) {
        return null;
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

        Vec3d movement = this.getVelocity();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.setVelocity(this.velocityX, this.velocityY * 0.6, this.velocityZ);
        }
    }

    @Override
    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return ItemStack.equalsIgnoreDamage(itemStack, new ItemStack(Items.APPLE));
    }

public EntityData initialize(final @NotNull LocalDifficulty difficulty, final @Nullable EntityData groupData) {
        this.setServerEntity(true);
        return super.initialize(difficulty, groupData);
    }

    @Override
    public void initGoals() {

        this.goals.add(2, new SwimGoal(this));
        this.goals.add(3, new EscapeDangerGoal(this, 1.4d));
        this.goals.add(4, new TemptGoal(this, 1.0f, Items.APPLE, false));

        this.goals.add(5, new LookAroundGoal(this));
        this.goals.add(6, new WanderAroundGoal(this, 1.0D));
        this.goals.add(8, new FollowOwnerGoal(this, 1, 2, 10));
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
    protected Sound getAmbientSound() {
        return Sounds.ENTITY_CHICKEN_STEP;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, -0.04, 0));
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
            float bodyYawDiff = MathHelper.wrapDegrees(this.headYaw - this.bodyYaw);

            if (rotationToOwner >= 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > 2.0) {

                this.setLimbDistance(0.5F);

                Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
                double speed = 0.15;
                this.setVelocity(dir.x * speed, this.getVelocity().y, dir.z * speed);
            } else {
                this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.velocityX * 0.8, this.velocityY, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1) {
                this.jump();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
                //this.processFlappingMovement();
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.move(this.velocityX, this.velocityY, this.velocityZ);

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }
    }

    @Override
    public void onTrackedDataSet(@NotNull TrackedData<?> key) {
        if (!this.world.isClient) {
            super.onTrackedDataSet(key);
        }
    }

    @Override
    public void writeCustomDataToNbt(@NotNull NbtCompound output) {
        super.writeCustomDataToNbt(output);
        output.putBoolean("isServerEntity", true);
    }

    @Override
    public void readCustomDataFromNbt(@NotNull NbtCompound input) {
        super.readCustomDataFromNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}
