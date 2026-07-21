package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.TameableEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Abstract class representing slimes or any animal that bounces up and down repeatedly,
 * including rabbits.
 */
public abstract class SlimeLikePet extends AbstractPet {
    public SlimeLikePet(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
        super(entityType, level);
    }

    /**
     * Custom ticking logic. Note this logic: <pre>
     * {@code if (this.walkAnimation.isMoving() && this.onGround) {
     *     this.jumpFromGround();
     * }}</pre>
     */
    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.m_5189207(this.m_9899189().add(0, -0.04, 0));
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
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_4835134 /*sign*/(bodyYawDiff) * 50.0F);
            }

            if (distance > 4.0) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_6033138(this.bodyYaw /*bodyYaw*/, this.headYaw, 50.0f);

                double speed = owner.getSpeed() * 2;
                this.m_5189207(new Vec3d(dir.x * speed, this.m_9899189().y, dir.z * speed));
            } else {
                this.lookAt(owner, 5, 0);
                this.m_5189207(this.m_9899189().m_7068480(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.m_5189207(this.m_9899189().add(0, -0.02, 0));
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (owner.m_9899189().squaredDistanceToOrigin() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_4835134 /*sign*/(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_6033138(this.bodyYaw /*bodyYaw*/, this.getHeadYaw(), 10);
            }

            this.move(MoverType.SELF, this.m_9899189());

            if (!this.onGround) {
                this.m_5189207(this.m_9899189().add(0, -0.02, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }
        if (this.walkAnimationSpeed > 0 && this.onGround) {
            this.jump();
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, Objects.requireNonNull(this.getAmbientSound()), SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}