package com.jeff.pets.mob.custom.aprilfools;

import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import static com.jeff.pets.PetsInitializer.HEAD;

public class Head extends AbstractPet {
    public static final net.minecraft.network.datasync.DataParameter<Boolean> IS_SERVER_ENTITY =
            EntityDataManager.defineId(Head.class, net.minecraft.network.datasync.DataSerializers.BOOLEAN);

    public Head(final EntityType<? extends Head> type, final World level) {
        super(type, level);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    public AgeableEntity getBreedOffspring(AgeableEntity AgableMob) {
        return HEAD.create(AgableMob.level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
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

        Vec3d movement = this.getDeltaMovement();
        if (!this.onGround && movement.y < (double) 0.0F) {
            this.setDeltaMovement(movement.multiply(1.0F, 0.6, 1.0F));
        }
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.sameItem(new ItemStack(Items.CAKE));
    }

    @Override
    public ILivingEntityData finalizeSpawn(final IWorld level, final DifficultyInstance difficulty, SpawnReason mobSpawnType, final ILivingEntityData groupData, CompoundNBT compoundTag) {
        this.setServerEntity(true);
        return super.finalizeSpawn(level, difficulty, mobSpawnType, groupData, compoundTag);
    }

    @Override
    public void registerGoals() {

        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, Ingredient.of(Items.CAKE), false));

        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10, false));
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
        return SoundEvents.CHICKEN_STEP;
    }

    @Override
    public boolean mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        if (!this.isTame() && this.isFood(itemStack)) {
            if (this.random.nextInt(3) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.level.addParticle(
                        ParticleTypes.HEART,

                        x + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        y + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        z + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        0, 5, 0
                );
            }
        }

        if (this.isTame() && itemStack.isEmpty()) {
            this.level.addParticle(
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
                if (owner.isCrouching() && owner.jumping) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();

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

                Vec3d targetPos = owner.position();
                Vec3d dir = targetPos.subtract(this.position()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                double speed = 0.15;
                this.setDeltaMovement(dir.x * speed, this.getDeltaMovement().y, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1) {
                this.jumpFromGround();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
                // t/his.processFlappingMovement();
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }
            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setYHeadRot(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.yBodyRot = this.getYHeadRot() - (net.minecraft.util.math.MathHelper.sign(bodyYawDiff) * 50);
            } else {
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.getYHeadRot(), 10);
            }

            this.move(MoverType.SELF, this.getDeltaMovement());

            if (!this.onGround) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleportTo(owner.getX(), owner.getY(), owner.getZ());
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
    public void onSyncedDataUpdated(net.minecraft.network.datasync.DataParameter<?> key) {
        if (!this.level.isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("isServerEntity", true);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT input) {
        super.readAdditionalSaveData(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
    }
}