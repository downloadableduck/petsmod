package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.SoundCategory;

import java.util.Objects;

/**
 * Abstract class representing any pet tht can fly (ghasts, vexes, etc). Contains custom movement
 * logic that allow the pets to move on the client side.
 *
 * @see AbstractPet
 * @see GroundPet
 */
public abstract class FlyingPet extends AbstractPet {

    protected FlyingPet(EntityType<? extends TameableEntity> type, net.minecraft.world.World level) {
        super(type, level);
    }

    /**
     * Custom ticking logic.
     *
     * @see GroundPet#tick()
     */
    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isCrouching() && owner.jumping) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1, 0));
                } else {
                    this.setOrderedToSit(true);
                }
            }

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();
            net.minecraft.util.math.vector.Vector3d ownerPos = owner.position().add(0, owner.getEyeHeight() * 0.8, 0);
            net.minecraft.util.math.vector.Vector3d vecToOwner = ownerPos.subtract(this.position());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationVector().x;
            float rotationToOwner = rotation + this.getOwner().getRotationVector().x;
            float bodyYawDiff = net.minecraft.util.math.MathHelper.wrapDegrees(this.getYHeadRot() - this.yBodyRot);

            if (rotationToOwner >= 50) {
                this.yBodyRot = this.getYHeadRot() - (net.minecraft.util.math.MathHelper.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > this.stopDistance()) {

                this.animationSpeed = (0.5F);

                net.minecraft.util.math.vector.Vector3d dir = vecToOwner.normalize();
                double speed = owner.getSpeed() * 1.5;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                this.setDeltaMovement(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jumpFromGround();
            }

            if (!this.onGround) {
                //this.processFlappingMovement();
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
                this.yBodyRot = this.getYHeadRot() - (net.minecraft.util.math.MathHelper.sign(bodyYawDiff) * 50);
            } else {
                this.yBodyRot = net.minecraft.util.math.MathHelper.rotateIfNecessary(this.yBodyRot, this.getYHeadRot(), 10);
            }

            this.move(MoverType.SELF, this.getDeltaMovement());

            //if (!this.onGround) {
            //  this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            //}
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.teleportTo(owner.getX(), owner.getY(), owner.getZ());
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level.playLocalSound(this.getX(), this.getY(), this.getZ(), Objects.requireNonNull(this.getAmbientSound()), SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
