package com.jeff.pets.mob.custom.first;

import com.jeff.pets.PetsSounds;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
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
import org.lwjgl.Sys;

public class Duck extends AbstractPet {

    public static final int IS_SERVER_ENTITY = 20;
    public static final int DUCK_SKIN = 21;
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
        this.setBounds(0.4F, 0.7F);
        this.initGoals();
    }

    public static float rotlerp(float start, float end) {
        float f = MathHelper.wrapDegrees(end - start);
        if (f > 10.0f) f = 10.0f;
        if (f < -10.0f) f = -10.0f;
        return start + f;
    }

    @Override
    public void initializeAttributes() {
        super.initializeAttributes();
        this.initializeAttribute(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.track(DUCK_SKIN, 1);
        this.dataTracker.track(IS_SERVER_ENTITY, (byte) 0);
    }

    public boolean isServerEntity() {
        return this.dataTracker.getByte(IS_SERVER_ENTITY) == 1;
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.setProperty(IS_SERVER_ENTITY, (byte) (value ? 1 : 0));
    }

    public void tickMovement() {
        this.field_6748 = this.field_6749;
        //this.limbSwing += this.field_6749;
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
            this.setVelocityClient(this.velocityX, this.velocityY * 0.6, this.velocityZ);
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

    protected String getAmbientSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected String getHurtSound(final @NotNull DamageSource source) {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected String getDeathSound() {
        return PetsSounds.DUCK_AMBIENT;
    }

    protected void playStepSound(final int x, final int y, final int z, final Block block) {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    public @Nullable Duck breed(final @NotNull PassiveEntity partner) {
        return null;
    }

public DataTracker initialize(final @NotNull Difficulty difficulty, final @Nullable DataTracker groupData) {
        this.setServerEntity(true);
        this.dataTracker.setProperty(DUCK_SKIN, this.random.nextInt(2));
        return groupData;
    }

    public boolean isBreedingItem(final @NotNull ItemStack itemStack) {
        return ItemStack.equalsAll(itemStack, new ItemStack(Items.RAW_FISH, 1, 2)) || ItemStack.equalsAll(itemStack, new ItemStack(Items.RAW_FISH, 1, 0)) || ItemStack.equalsAll(itemStack, new ItemStack(Items.RAW_FISH, 1, 1));
    }

    public void initGoals() {

        this.goals.add(1, new WanderAroundGoal(this, 1.0D));

        this.goals.add(0, new FollowOwnerGoal(this, 1, 2, 10));
        this.goals.add(9, new BreedGoal(this, 1));
        this.goals.add(2, new SwimGoal(this));
        this.goals.add(3, new EscapeSunlightGoal(this, 1.4d));
        this.goals.add(4, new TemptGoal(this, 1.0f, Items.SKULL, false));

        this.goals.add(5, new LookAroundGoal(this));
        this.goals.add(6, new WanderAroundGoal(this, 1.0D));
        this.goals.add(7, new EatGrassGoal(this));
        this.goals.add(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            this.setRotation((float) (Math.random() * 360), (float) (Math.random() * 360));

            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, -0.04, 0));
                    this.isOnHead = false;
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

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

                Vec3d targetPos = Vec3d.of(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.reverseSubtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -50, 50);

                double speed = owner.getMovementSpeed() * 2;
                this.setVelocityClient(-dir.x * speed, this.getVelocity().y, -dir.z * speed);
            } else {
                //this.lookAtEntity(owner, 5, 0);
                this.setVelocityClient(this.velocityX * 0.8, this.velocityY, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.horizontalCollision && this.onGround) {
                this.jump();
                //this.processFlappingMovement();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
                //this.processFlappingMovement();
            }

            if (!this.onGround) {
                // this.processFlappingMovement();
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
                this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.headYaw - this.bodyYaw, -10, 10);
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

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.world.playSound(this.x, this.y, this.z, PetsSounds.DUCK_AMBIENT, 1.0f, 1.0f, true);
        }
    }

    @Override
    public void writePlayerData(@NotNull NbtCompound output) {
        super.writePlayerData(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("variant", this.dataTracker.getInt(DUCK_SKIN));
    }

    @Override
    public void fromNbt(@NotNull NbtCompound input) {
        super.fromNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.dataTracker.setProperty(DUCK_SKIN, input.getInt("variant"));
    }
}