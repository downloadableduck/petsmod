package com.jeff.pets.mob.custom.first;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Racoon extends AbstractPet {

    public static final int IS_SERVER_ENTITY = 20;
    public boolean isOnHead;

    public Racoon(World level) {
        super(level);
        this.setBounds(1.0F, 1.0F);
        this.initGoals();
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
    protected String getAmbientSound() {
        return "mob.chicken.step";
    }

/*public @Nullable DataTracker initialize(@NotNull Difficulty difficulty, @Nullable DataTracker groupData) {
        this.setServerEntity(true);
        return groupData;
    }*/

    public void initGoals() {

        this.goals.add(1, new BreedGoal(this, 1));
        this.goals.add(2, new SwimGoal(this));
        this.goals.add(3, new EscapeSunlightGoal(this, 1.4d));
//        this.goals.add(4, new TemptGoal(this, 1.0f, Items.SKULL, false));

        this.goals.add(5, new LookAroundGoal(this));
        this.goals.add(6, new WanderAroundGoal(this, 1.0D));
        this.goals.add(8, new FollowOwnerGoal(this, 1, 2, 10));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.track(IS_SERVER_ENTITY, (byte) 0);
    }

    public boolean isServerEntity() {
        return this.dataTracker.getByte(IS_SERVER_ENTITY) != 0;
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.setProperty(IS_SERVER_ENTITY, (byte) (value ? 1 : 0));
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


            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().offset(0, -0.04, 0));
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

                Vec3d targetPos = Vec3d.fromXYZ(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.copyOther(this.getPos()).normalize();

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
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().offset(0, -0.01, 0));
            }

            if (Utils.squaredDistanceToOrigin(Vec3d.fromXYZ(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
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
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.headYaw - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            this.move(this.velocityX, this.velocityY, this.velocityZ);

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().offset(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }

        /*int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this, Sounds.BOGGED_AMBIENT, SoundSource.AMBIENT, 1.0f, 1.0f);
        }*/
    }

    public @Nullable AnimalEntity breed(@NotNull PassiveEntity AgableMob) {
        return null;
    }

    }