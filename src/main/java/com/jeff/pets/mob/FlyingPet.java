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
 * Abstract class representing any pet tht can fly (ghasts, vexes, etc). Contains custom movement
 * logic that allow the pets to move on the client side.
 *
 * @see AbstractPet
 * @see GroundPet
 */
public abstract class FlyingPet extends AbstractPet {

    protected FlyingPet(EntityType<? extends @NotNull TameableEntity> type, World level) {
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
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.m_5189207(this.m_9899189().add(0, 0.1, 0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = new Vec3d(owner.x, owner.y, owner.z).add(0, owner.getEyeHeight() * 0.8, 0);
            Vec3d vecToOwner = ownerPos.subtract(this.getPos());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotation().x;
            float rotationToOwner = rotation + this.getOwner().getRotation().x;
            float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.bodyYaw /*bodyYaw*/);

            if (rotationToOwner >= 50) {
                this.bodyYaw /*bodyYaw*/ = this.getHeadYaw() - (MathHelper.m_4835134 /*sign*/(bodyYawDiff) * 50.0F);
            }

            if (distance > this.stopDistance()) {

                this.walkAnimationSpeed = 0.5F;

                Vec3d dir = vecToOwner.normalize();
                double speed = owner.getSpeed() * 1.5;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw /*bodyYaw*/ = MathHelper.m_6033138(this.bodyYaw /*bodyYaw*/, this.headYaw, 50.0f); //m_6033138

                this.m_5189207(new Vec3d(dir.x * speed, dir.y * speed, dir.z * speed));
            } else {
                this.lookAt(owner, 5, 0);
                this.m_5189207(this.m_9899189().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.y - this.y);

            if (yHeightToOwner > 1 || this.collidingHorizontally) {
                this.jump();
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

            //if (!this.onGround) {
            //  this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            //}
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
