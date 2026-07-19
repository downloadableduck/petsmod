package com.jeff.pets.mob.custom.first;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.data.DataAttribute;
import net.minecraft.entity.data.DataSerializers;
import net.minecraft.entity.data.SyncedData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.AddEntityS2CPacket;
import net.minecraft.crafting.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.unmapped.C_31453009;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff.pets.PetsInitializer.RACOON;

public class Racoon extends AbstractPet {

    public static final DataAttribute<@NotNull Boolean> IS_SERVER_ENTITY =
            SyncedData.registerSerializer(Racoon.class, DataSerializers.BOOLEAN);
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
    public @Nullable EntityData initialize(@NotNull WorldAccess level, LocalDifficulty difficulty, C_31453009 spawnReason, @Nullable EntityData groupData, NbtCompound NbtCompound) {
        this.setServerEntity(true);
        return super.initialize(level, difficulty, spawnReason, groupData, NbtCompound);
    }

    @Override
    public void initGoals() {

        this.goalSelector.addGoal(1, new AnimalBreedGoal(this, 1));
        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new EscapeDangerGoal(this, 1.4d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0f, Ingredient.of(Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL), false));

        this.goalSelector.addGoal(5, new LookAroundGoal(this));
        this.goalSelector.addGoal(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1, 2, 10));
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
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.m_28162558(this.m_94091929().add(0, -0.04, 0));
                    this.isOnHead = false;
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotation().x;
            float rotationToOwner = rotation + this.getOwner().getRotation().x;
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_06800284 /*sign*/(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_82141949(this.bodyYaw /*bodyYaw*/, this.headYaw, 50.0f);

                double speed = owner.getSpeed() * 2;
                this.m_28162558(new Vec3d(dir.x * speed, this.m_94091929().y, dir.z * speed));
            } else {
                this.lookAt(owner, 5, 0);
                this.m_28162558(this.m_94091929().m_17023014(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.collidingHorizontally && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.m_28162558(this.m_94091929().add(0, -0.01, 0));
            }

            if (owner.m_94091929().squaredDistanceToOrigin() < 0.01) {
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
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_06800284 /*sign*/(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_82141949(this.bodyYaw /*bodyYaw*/, this.getHeadYaw(), 10);
            }

            this.move(MoverType.SELF, this.m_94091929());

            if (!this.onGround) {
                this.m_28162558(this.m_94091929().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }

        /*int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this, SoundEvents.BOGGED_AMBIENT, SoundSource.AMBIENT, 1.0f, 1.0f);
        }*/
    }

    @Override
    public @Nullable PassiveEntity makeChild(@NotNull PassiveEntity AgableMob) {
        Racoon racoon = RACOON.create(world);
        racoon.setServerEntity(false);
        return racoon;
    }

    @Override
    public void onDataValueChanged(@NotNull DataAttribute<?> key) {
        if (!this.world.isClient()) {
            super.onDataValueChanged(key);
        }
    }

    @Override
    public @NotNull Packet<?> m_00781305() {
        if (this.world.isClient()) {
            return new AddEntityS2CPacket(this);
        } else {
            return super.m_00781305();
        }
    }
}