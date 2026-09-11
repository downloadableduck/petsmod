package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.sound.SoundCategory;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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

    @Override
    public void tickMovement() {
        this.field_6748 = this.field_6749;
        this.field_6750 += this.field_6749;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            if (owner.hasPassenger(this)) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.addVelocity(0, -0.04, 0);
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float bodyYawDiff = net.minecraft.util.math.MathHelper.wrapDegrees(this.headYaw - this.bodyYaw);

            if (distance > this.stopDistance()) {
                this.setLimbDistance(0.5F);

                Vec3d dir = new Vec3d(owner.x - this.x, owner.y - this.y, owner.z - this.z).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.headYaw - this.bodyYaw, -50, 50);

                double speed = owner.getMovementSpeed() * 2.0;
                this.velocityX = dir.x * speed;
                this.velocityZ = dir.z * speed;
            } else if (distance < 1.5) {
                this.setLimbDistance(0);
            } else {
                this.lookAtEntity(owner, 5, 0);
                this.setLimbDistance(this.getLimbDistance() + 0.1f);
                this.velocityX *= 0.8;
                this.velocityZ *= 0.8;
            }

            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (!this.onGround) {
                this.addVelocity(0, -0.04, 0);
            }

            this.move(MovementType.SELF, this.velocityX, this.velocityY, this.velocityZ);

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - (float) Math.signum(bodyYawDiff) * 50;
            } else {
                this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.headYaw - this.bodyYaw, -10, 10);
            }

            if (distanceTo(owner) >= 10) {
                this.updatePosition(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            world.playSound(this.x, this.y, this.z, Objects.requireNonNull(this.getAmbientSound()), SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}