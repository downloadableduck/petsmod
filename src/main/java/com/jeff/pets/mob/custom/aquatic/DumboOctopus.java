package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DumboOctopus extends FlyingPet {

    public static final int IS_SERVER_ENTITY = 20;
    public static final int OCTOPUS_SKIN = 21;
    private final float nextFlap = 1.0F;
    public float tentacleAngle = 0;
    public ServerPlayerEntity owner = (ServerPlayerEntity) this.getOwner();

    public DumboOctopus(final World level) {
        super(level);
        this.setBounds(0.5F, 0.5F);
        this.setMovementSpeed(0.3f);
        this.initGoals();
    }

    @Override
    public void initializeAttributes() {
        super.initializeAttributes();
        this.initializeAttribute(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.track(OCTOPUS_SKIN, 1);
        this.dataTracker.track(IS_SERVER_ENTITY, (byte) 0);
    }

    public boolean isServerEntity() {
        return this.dataTracker.getByte(IS_SERVER_ENTITY) != 0;
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.setProperty(IS_SERVER_ENTITY, (byte) (value ? 1 : 0));
    }

    public void tickMovement() {
        super.tickMovement();
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    protected String getAmbientSound() {
        return "mob.squid.ambient";
    }

    protected String getHurtSound(final @NotNull DamageSource source) {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected String getDeathSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final int x, final int y, final int z, final Block blockState) {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    public @Nullable DumboOctopus breed(final @NotNull PassiveEntity partner) {
        return null;
    }

public DataTracker initialize(final @NotNull Difficulty difficulty, final @Nullable DataTracker groupData) {
        this.setServerEntity(true);
        this.dataTracker.setProperty(OCTOPUS_SKIN, this.random.nextInt(6));
        return groupData;
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return ItemStack.equalsAll(itemStack, new ItemStack(Items.RAW_FISH, 1, 2)) || ItemStack.equalsAll(itemStack, new ItemStack(Items.RAW_FISH, 1, 0)) || ItemStack.equalsAll(itemStack, new ItemStack(Items.RAW_FISH, 1, 1));
    }

public void initGoals() {

        this.goals.add(2, new SwimGoal(this));

        this.goals.add(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goals.add(9, new BreedGoal(this, 1));
        this.goals.add(3, new EscapeSunlightGoal(this, 1.4d));
        // this.tasks.addTask(4, new TemptGoal(this, 1.0f, stack -> stack.is(ItemTags.FISHES), false));

        this.goals.add(5, new LookAroundGoal(this));
        // this.tasks.addTask(8, new EntityAIFollowOwner(this, 1, 2, 10));
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = Vec3d.of(owner.x, owner.y, owner.z).add(0, owner.getEyeHeight() * 0.8, 0);
            Vec3d vecToOwner = ownerPos.reverseSubtract(this.getPos());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;


            double distance = MathHelper.sqrt(this.squaredDistanceTo(owner));
            float rotation = -this.pitch;
            float rotationToOwner = rotation + -this.getOwner().pitch;
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

                this.setVelocityClient(-dir.x * speed, dir.y * speed, -dir.z * speed);
            } else {
                //this.lookAtEntity(owner, 5, 0);
                this.setVelocityClient(this.velocityX * 0.8, this.velocityY, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (Utils.squaredDistanceToOrigin(Vec3d.of(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
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

            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.world.playSound(this.x, this.y, this.z, "mob.squid.ambient", 1.0f, 1.0f, true);
        }
    }

    @Override
    public void writePlayerData(@NotNull NbtCompound output) {
        super.writePlayerData(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("variant", this.dataTracker.getInt(OCTOPUS_SKIN));
    }

    @Override
    public void fromNbt(@NotNull NbtCompound input) {
        super.fromNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.dataTracker.setProperty(OCTOPUS_SKIN, input.getInt("variant"));
    }

    
    public boolean canBreatheInWater() {
        return true;
    }

    public float getViewBobThing() {
        return this.field_6749;
    }
}
