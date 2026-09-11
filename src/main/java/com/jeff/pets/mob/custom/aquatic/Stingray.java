package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.BlockState;
import net.minecraft.client.sound.SoundCategory;
import net.minecraft.entity.EntityData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Stingray extends FlyingPet {
    public static final TrackedData<@NotNull Boolean> IS_SERVER_ENTITY =
            DataTracker.registerData(Stingray.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final float nextFlap = 1.0F;
    public float oFlap;
    public float flap;
    public float flapping = 1.0F;

    public Stingray(World level) {
        super(level);
        this.setBounds(1.0F, 0.4F);
    }

    @Override
    public void initializeAttributes() {
        super.initializeAttributes();
        this.getAttributeContainer().get(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
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
            this.setVelocity(this.velocityX, this.velocityY * 0.6, this.velocityZ);
        }

        this.flap += this.flapping * 2.0F;
    }

    protected Sound getAmbientSound() {
        return Sounds.ENTITY_SQUID_AMBIENT;
    }

    protected Sound getHurtSound(final @NotNull DamageSource source) {
        return Sounds.ENTITY_SQUID_HURT;
    }

    protected Sound getDeathSound() {
        return Sounds.ENTITY_SQUID_DEATH;
    }

    protected void playStepSound(final @NotNull BlockPos pos, final @NotNull BlockState blockState) {
        this.playSound(Sounds.ENTITY_GENERIC_SWIM, 0.15F, 1.0F);
    }

    public @Nullable Stingray createChild(final @NotNull PassiveEntity partner) {
        return null;
    }

public @NotNull EntityData initialize(final @NotNull LocalDifficulty difficulty, final @Nullable EntityData groupData) {
        this.setServerEntity(true);
        return super.initialize(difficulty, groupData);
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return ItemStack.equalsIgnoreDamage(itemStack, new ItemStack(Items.RAW_FISH, 1, 2)) || ItemStack.equalsIgnoreDamage(itemStack, new ItemStack(Items.RAW_FISH, 1, 0)) || ItemStack.equalsIgnoreDamage(itemStack, new ItemStack(Items.RAW_FISH, 1, 1));
    }

@Override
    public void initGoals() {

        this.goals.add(2, new SwimGoal(this));

        this.goals.add(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goals.add(9, new BreedGoal(this, 1));
        this.goals.add(3, new EscapeDangerGoal(this, 1.4d));
        // this.goals.add(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.goals.add(5, new LookAroundGoal(this));
        // this.goals.add(8, new FollowOwnerGoal(this, 1, 2, 10));
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

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

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
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = new Vec3d(owner.x, owner.y, owner.z).add(0, owner.getEyeHeight() * 0.8, 0);
            Vec3d vecToOwner = ownerPos.subtract(this.getPos());
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

                Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -50, 50);

                this.setVelocity(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {
                this.lookAtEntity(owner, 5, 0);
                this.setVelocity(this.velocityX * 0.8, this.velocityY, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (new Vec3d(owner.velocityX, owner.velocityY, owner.velocityZ).squaredLength() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.move(MovementType.SELF, this.velocityX, this.velocityY, this.velocityZ);
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, Sounds.ENTITY_SQUID_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
