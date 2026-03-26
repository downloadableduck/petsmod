package com.jeff.pets.vanilla.neutral;

import com.jeff.pets.CanFly;
import com.jeff.pets.custom.Duck;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

@CanFly
public class ClientNautilus extends TamableAnimal {
    public ClientNautilus(EntityType<? extends @NotNull TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 8.0F).add(Attributes.MOVEMENT_SPEED, 0.23F);
    }

    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);


        var x = this.getX();
        var y = this.getY();
        var z = this.getZ();

        if (!this.isTame() && this.isFood(itemStack)) {
            if (this.random.nextInt(3) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.level().addParticle(
                        ParticleTypes.HEART,

                        x + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        y + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        z + (player.getRandom().nextFloat() * 0.4 - 0.25),
                        0, 5, 0
                );
            }
        }

        if (this.isTame() && itemStack.isEmpty()) {
            this.level().addParticle(
                    ParticleTypes.HEART,
                    this.getX(),
                    this.getY() + 1,
                    this.getZ(),
                    5, 5, 5
            );
        }

        if (this.isTame() && itemStack.isEmpty() && player.isShiftKeyDown()) {
            if (!this.isPassenger()) {
                this.startRiding(player);
                this.lookAt(player, 1f, 1f);
                this.setOrderedToSit(true);
            } else {
                this.stopRiding();
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {

            if (owner.hasPassenger(this)) {
                if (owner.isCrouching() && owner.isJumping()) {
                    this.stopRiding();
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1, 0));
                } else {
                    this.setOrderedToSit(true);
                }
            }

            this.setYRot(owner.getYRot());

            double dx = owner.getX() - this.getX();
            double dz = owner.getZ() - this.getZ();
            Vec3 ownerPos = owner.position().add(0, owner.getEyeHeight() * 0.8, 0);
            Vec3 vecToOwner = ownerPos.subtract(this.position());
            double targetYaw = Math.atan2(dz, dx) * (180 / Math.PI) - 90f;

            double distance = this.distanceTo(owner);
            float rotation = this.getRotationVector().x;
            var rotationToOwner = rotation + this.getOwner().getRotationVector().x;
            float bodyYawDiff = Mth.wrapDegrees(this.getYHeadRot() - this.yBodyRot);

            if (rotationToOwner >= 50) {
                this.yBodyRot = this.getYHeadRot() - (Mth.sign(bodyYawDiff) * 50.0F);
            }

            if (distance > 3.0) {

                this.walkAnimation.setSpeed(0.5F);

                Vec3 targetPos = owner.position();
                Vec3 dir = vecToOwner.normalize();
                double speed = 0.2;

                this.setYRot(Duck.rotlerp(this.getYRot(), (float) targetYaw));
                this.setYHeadRot(this.getYRot());
                this.yBodyRot = Mth.rotateIfNecessary(this.yBodyRot, this.yHeadRot, 50.0f);

                this.setDeltaMovement(dir.x * speed, dir.y * speed, dir.z * speed);
            } else {
                this.lookAt(owner, 5, 0);
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
            }

            int yHeightToOwner = (int) (owner.getY() - this.getY());

            if (yHeightToOwner > 1) {
                this.jumpFromGround();
            }

            /*if (yHeightToOwner > -1) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01, 0));
            }*/

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
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            }
        }
        if (owner != null) {
            if (distanceTo(owner) >= 10) {
                this.tryToTeleportToOwner();
            }
        }

        int ambient = (int) (Math.random() * (60 * 20));
        if (ambient == 1) {
            level().playLocalSound(this, SoundEvents.NAUTILUS_AMBIENT, SoundSource.AMBIENT, 1.0f, 1.0f);
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        if (!this.level().isClientSide()) {
            super.onSyncedDataUpdated(key);
        }
    }

    @Override
    public @NotNull Packet<@NotNull ClientGamePacketListener> getAddEntityPacket(@NotNull ServerEntity serverEntity) {
        if (this.level().isClientSide()) {
            return new ClientboundAddEntityPacket(this, serverEntity);
        } else {
            return super.getAddEntityPacket(serverEntity);
        }
    }
}
