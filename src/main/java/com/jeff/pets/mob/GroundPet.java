package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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

    public GroundPet(EntityType<? extends @NotNull TamableAnimal> type, Level level) {
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
                if (owner.isCrouching() && owner.isJumping()) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
                } else {
                    this.setOrderedToSit(true);
                }
            }

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationVector().x;
            var rotationToOwner = rotation + this.getOwner().getRotationVector().x;
            float bodyYawDiff = Mth.wrapDegrees(this.getYHeadRot() - this.yBodyRot);

            if (rotationToOwner >= 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > this.stopDistance()) {

                this.walkAnimation.setSpeed(0.5F);

                Vec3 targetPos = owner.position();
                Vec3 dir = targetPos.subtract(this.position()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                double speed = owner.getSpeed() * 2.0;
                this.setDeltaMovement(dir.x * speed, this.getDeltaMovement().y, dir.z * speed);
            } else if (distance < 1.5) {
                this.walkAnimation.setSpeed(0);
            } else {
                this.lookAt(owner, 5, 0);
                this.walkAnimation.setSpeed(this.walkAnimation.speed() + 0.1f);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (this.horizontalCollision && this.onGround()) {
                this.jumpFromGround();
            }

            if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
            }

            if (!this.onGround()) {
                this.processFlappingMovement();
            }

            if (owner.getDeltaMovement().lengthSqr() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setYHeadRot(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50);
            } else {
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.getYHeadRot(), 10);
            }

            this.move(MoverType.SELF, this.getDeltaMovement());

            if (!this.onGround()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            }
        } else {
            super.tick();
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.tryToTeleportToOwner();
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level().playLocalSound(this, Objects.requireNonNull(this.getAmbientSound()), SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }
}
