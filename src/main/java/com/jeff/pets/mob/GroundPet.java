package com.jeff.pets.mob;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.custom.first.Duck;

import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.TameableEntity;
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

    public GroundPet(World level) {
        super(level);
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

            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.dismountFromVehicle();
                    this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = -this.pitch;
            float rotationToOwner = rotation + (-this.getOwner().pitch);
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (Math.signum(bodyYawDiff) * 50.0F);
            }

            if (distance > this.stopDistance()) {

                this.walkAnimationSpeed = (0.5F);

                Vec3d targetPos = Vec3d.of(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.subtractFrom(this.getPosVec()).normalize();

                
                
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -50, 50);

                double speed = owner.getSpeed() * 2.0;
                this.lerpVelocity(Vec3d.of(-dir.x * speed, this.getVelocity().y, -dir.z * speed));
            } else if (distance < 1.5) {
                this.walkAnimationSpeed = (0);
            } else {
                                this.walkAnimationSpeed = (this.walkAnimationSpeed + 0.1f);
                // Z component was reading velocityY - a copy/paste slip that fed vertical
                // velocity into horizontal motion, making pets skate or lunge when falling.
                this.lerpVelocity(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityZ * 0.8);
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.collidingHorizontally && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.lerpVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (Utils.squaredDistanceToOrigin(Vec3d.of(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (Math.signum(bodyYawDiff) * 50);
            } else {
                this.bodyYaw /*bodyYaw*/ = this.bodyYaw /*bodyYaw*/ + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/, -10, 10);
            }

            // No manual move() here: LivingEntity.mobTick() already calls moveRelative() ->
            // move(this.velocityX, ...) on the client, so calling move() again moved the pet
            // twice per tick and doubled its apparent speed.

            if (!this.onGround) {
                this.lerpVelocity(this.getVelocity().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, Objects.requireNonNull(this.getAmbientSound()), 1.0f, 1.0f, true);
        }
    }
}