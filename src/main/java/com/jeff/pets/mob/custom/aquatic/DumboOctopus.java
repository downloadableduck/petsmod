package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.PetsSounds;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.AddEntityS2CPacket;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.unmapped.C_31453009;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff.pets.PetsInitializer.DUMBO_OCTOPUS;

public class DumboOctopus extends FlyingPet {

    public static final DataAttribute<@NotNull Boolean> IS_SERVER_ENTITY =
            SyncedData.registerSerializer(DumboOctopus.class, DataSerializers.BOOLEAN);
    public static final DataAttribute<@NotNull Integer> OCTOPUS_SKIN =
            SyncedData.registerSerializer(DumboOctopus.class, DataSerializers.INTEGER);
    private final float nextFlap = 1.0F;
    public float tentacleAngle = 0;
    public ServerPlayerEntity owner = (ServerPlayerEntity) this.getOwner();

    public DumboOctopus(final EntityType<? extends @NotNull DumboOctopus> type, final World level) {
        super(type, level);
        this.setPathfindingPenalty(PathBlockingType.WATER, 0.0f);
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(0.25);
    }

    @Override
    protected void registerSyncedData() {
        super.registerSyncedData();
        this.syncedData.register(OCTOPUS_SKIN, 1);
        this.syncedData.register(IS_SERVER_ENTITY, false);
    }

    public boolean isServerEntity() {
        return this.syncedData.get(IS_SERVER_ENTITY);
    }

    public void setServerEntity(Boolean value) {
        this.syncedData.set(IS_SERVER_ENTITY, value);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
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

    public @Nullable DumboOctopus makeChild(final @NotNull PassiveEntity partner) {
        DumboOctopus octopus = DUMBO_OCTOPUS.create(world);
        octopus.setServerEntity(true);
        return octopus;
    }

    public EntityData initialize(final @NotNull WorldAccess level, final @NotNull LocalDifficulty difficulty, final @NotNull C_31453009 spawnReason, final @Nullable EntityData groupData, NbtCompound NbtCompound) {
        this.setServerEntity(true);
        this.syncedData.set(OCTOPUS_SKIN, this.random.nextInt(6));
        return super.initialize(level, difficulty, spawnReason, groupData, NbtCompound);
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
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.m_28162558(this.m_94091929().add(0, 0.1, 0));
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
            float rotation = this.getRotation().x;
            float rotationToOwner = rotation + this.getOwner().getRotation().x;
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_06800284 /*sign*/(bodyYawDiff) * 50.0F);
            }

            if (distance > 2.0) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setBodyYaw(Duck.rotlerp(this.bodyYaw /*bodyYaw*/, (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_82141949(this.bodyYaw /*bodyYaw*/, this.headYaw, 50.0f);

                this.m_28162558(new Vec3d(dir.x * speed, dir.y * speed, dir.z * speed));
            } else {
                this.lookAt(owner, 5, 0);
                this.m_28162558(this.m_94091929().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 || this.collidingHorizontally) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.m_28162558(this.m_94091929().add(0, -0.01, 0));
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (owner.m_94091929().squaredDistanceToOrigin() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_06800284 /*sign*/(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_82141949(this.bodyYaw /*bodyYaw*/, this.getHeadYaw(), 10);
            }

            this.move(MoverType.SELF, this.m_94091929());
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

    @Override
    public void writeCustomNbt(@NotNull NbtCompound output) {
        super.writeCustomNbt(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("variant", this.syncedData.get(OCTOPUS_SKIN));
    }

    @Override
    public void readCustomNbt(@NotNull NbtCompound input) {
        super.readCustomNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.syncedData.set(OCTOPUS_SKIN, input.getInt("variant"));
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

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}