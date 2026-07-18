package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
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
                if (owner.isInSneakingPose() && owner.jumping) {
                    this.stopRiding();
                    this.setVelocity(this.getVelocity().add(0, -0.04, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;

            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationClient().x;
            float rotationToOwner = rotation + this.getOwner().getRotationClient().x;
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.field_6283 /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.field_6283 /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > this.stopDistance()) {

                this.limbDistance = (0.5F);

                Vec3d targetPos = owner.getPos();
                Vec3d dir = targetPos.subtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.field_6283 /*bodyYaw*/ = MathHelper.method_20306(this.field_6283 /*bodyYaw*/, this.headYaw, 50.0f);

                double speed = owner.getMovementSpeed() * 2.0;
                this.setVelocity(dir.x * speed, this.getVelocity().y, dir.z * speed);
            } else if (distance < 1.5) {
                this.limbDistance = (0);
            } else {
                this.lookAtEntity(owner, 5, 0);
                this.limbDistance = (this.limbDistance + 0.1f);
                this.setVelocity(this.getVelocity().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.setVelocity(this.getVelocity().add(0, -0.01, 0));
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
            }

            if (owner.getVelocity().lengthSquared() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.field_6283 /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.sign(bodyYawDiff) * 50);
            } else {
                this.field_6283 /*bodyYaw*/ = MathHelper.method_20306(this.field_6283 /*bodyYaw*/, this.getHeadYaw(), 10);
            }

            this.move(MovementType.SELF, this.getVelocity());

            if (!this.onGround) {
                this.setVelocity(this.getVelocity().add(0, -0.04, 0));
            }
        } else {
            super.tick();
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.requestTeleport(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, Objects.requireNonNull(this.getAmbientSound()), SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
