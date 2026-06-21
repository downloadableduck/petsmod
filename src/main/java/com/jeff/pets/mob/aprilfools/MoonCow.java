package com.jeff.pets.mob.aprilfools;

import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class MoonCow extends AbstractPet {
    public MoonCow(EntityType<? extends @NotNull TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 8.0F).add(Attributes.MOVEMENT_SPEED, 0.23F);
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {


            if (owner.hasPassenger(this)) {
                if (owner.isCrouching() && owner.jumping) {
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

            if (distance > 2.0) {

                this.walkAnimation.setSpeed(0.5F);

                Vec3 targetPos = owner.position();
                Vec3 dir = targetPos.subtract(this.position()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                double speed = 0.15;
                this.setDeltaMovement(dir.x * speed, this.getDeltaMovement().y, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 1.0, 0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1) {
                this.jumpFromGround();
            }

            if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
            }

            if (!this.onGround()) {
                this.processFlappingMovement();
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
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 15) {
                this.tryToTeleportToOwner();
            }
        }
        if (this.walkAnimation.isMoving() && this.onGround()) {
            this.jumpFromGround();
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level().playLocalSound(this, SoundEvents.COW_AMBIENT, SoundSource.AMBIENT, 1.0f, 1.0f);
        }
    }
}
