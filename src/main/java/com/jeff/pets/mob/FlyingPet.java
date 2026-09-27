package com.jeff.pets.mob;

import com.jeff.pets.client.network.NetworkManager;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.Minecraft;
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
 * Abstract class representing any pet tht can fly (ghasts, vexes, etc.). Contains custom movement
 * logic that allow the pets to move on the client side.
 *
 * @see AbstractPet
 * @see GroundPet
 */
public abstract class FlyingPet extends AbstractPet {

    protected FlyingPet(EntityType<? extends @NotNull TamableAnimal> type, Level level) {
        super(type, level);
    }

    /**
     * Custom ticking logic.
     *
     * @see GroundPet#tick()
     */
    @Override
    public void tick() {
        try {
            super.tick();
        } catch (Exception _) {
        }
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isCrouching() && owner.isJumping()) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1, 0));
                    NetworkManager.get().broadcastHeadPayload(Objects.requireNonNull(Minecraft.getInstance().player).getStringUUID(), false);
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
                if (this.waitingTime < 30 && !this.isReturningToOwner) this.lookAt(owner, 5, 5);
                else this.lookAt(this.dummy, 5, 5);
            } else {
                this.lookAt(owner, 5, 5);
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jumpFromGround();
            }

            if (!this.onGround()) {
                this.processFlappingMovement();
            }

            if (owner.getDeltaMovement().lengthSqr() < 0.01) {
                this.waitingTime++;
                if (this.waitingTime > 30) this.wander();
            } else {
                this.waitingTime = 0;
                this.isReturningToOwner = false;
            }


            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setYHeadRot(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50);
            } else {
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.getYHeadRot(), 10);
            }

            if (!this.sitting) {
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10 && !this.sitting) {
                this.tryToTeleportToOwner();
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        try (Level level = this.level()) {
            if (ambient == 1) {
                level.playLocalSound(this, Objects.requireNonNull(this.getAmbientSound()), SoundSource.AMBIENT, 1.0f, 1.0f);
            }
        } catch (Exception ignored) {
        }
    }
}
