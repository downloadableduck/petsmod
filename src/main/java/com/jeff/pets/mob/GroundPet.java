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
 * Abstract representing any pet that cannot fly (ducks, chickens, etc).
 *
 * @see AbstractPet
 * @see FlyingPet
 */
public abstract class GroundPet extends AbstractPet {
    private int waitingTime;

    public GroundPet(EntityType<? extends @NotNull TameableEntity> type, World level) {
        super(type, level);
    }

    /**
     * Custom ticking logic. Note this code: <pre>
     * {@code  if (yHeightToOwner > -1) {
     *       this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
     *  }
     * } </pre>
     * This ensures that the pet will fall down if it jumps up, meaning that it will not
     * be abele to fly.
     *
     * @see FlyingPet#tick()
     */
    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.m_28162558(this.m_94091929().add(0, -0.04, 0));
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

            if (distance > this.stopDistance()) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_82141949(this.bodyYaw /*bodyYaw*/, this.headYaw, 50.0f);

                double speed = owner.getSpeed() * 2.0;
                this.m_28162558(new Vec3d(dir.x * speed, this.m_94091929().y, dir.z * speed));
            } else if (distance < 1.5) {
                this.walkAnimationSpeed = (0);
            } else {
                this.lookAt(owner, 5, 0);
                this.walkAnimationSpeed = (this.walkAnimationSpeed + 0.1f);
                this.m_28162558(this.m_94091929().m_17023014(0.8, 1.0, 0.8)); //hopefully the right onw
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.collidingHorizontally && this.onGround) {
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

            if (!this.onGround) {
                this.m_28162558(this.m_94091929().add(0, -0.04, 0));
            }
        } else {
            super.tick();
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, Objects.requireNonNull(this.getAmbientSound()), SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
