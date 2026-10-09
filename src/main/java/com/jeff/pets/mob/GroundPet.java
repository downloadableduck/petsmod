package com.jeff.pets.mob;

import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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
        System.out.println(this.getOwner());
        System.out.println(MinecraftClient.getInstance().field_3805.getName().computeValue());
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.stopRiding();
                    this.addVelocity(0, -0.04, 0);
                } else {
                    this.setSitting(true);
                    return;
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;


            double distance = MathHelper.sqrt(this.squaredDistanceTo(owner));
            float bodyYawDiff = MathHelper.wrapDegrees(this.headYaw - this.bodyYaw);

            if (distance > this.stopDistance()) {
                this.setLimbDistance(0.5F);

                Vec3d targetPos = Vec3d.of(owner.x, owner.y, owner.z);
                Vec3d dir = targetPos.reverseSubtract(this.getPos()).normalize();

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setHeadYaw(this.getYRot());
                this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.headYaw - this.bodyYaw, -50, 50);

                double speed = owner.getMovementSpeed() * 2.0;
                this.setVelocityClient(-dir.x * speed, this.getVelocity().y, -dir.z * speed);
            } else if (distance >= 1.5) {
                ////this.lookAtEntity(owner, 5, 0);
                this.velocityX *= 0.8;
                this.velocityZ *= 0.8;
            }

            if (this.horizontalCollision && this.onGround) {
                this.jump();
            }

            if (!this.onGround) {
                this.addVelocity(0, -0.04, 0);
            }

            this.move(this.velocityX, this.velocityY, this.velocityZ);

            this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
            this.setHeadYaw(this.getYRot());

            if (Math.abs(bodyYawDiff) > 50) {
                this.bodyYaw = this.headYaw - Math.signum(bodyYawDiff) * 50;
            } else {
                this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.headYaw - this.bodyYaw, -10, 10);
            }

            if (distanceTo(owner) >= 10) {
                this.updatePosition(owner.x, owner.y, owner.z);
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            this.world.playSound(this.x, this.y, this.z, this.getAmbientSound(), 1.0f, 1.0f, true);
        }
    }
}