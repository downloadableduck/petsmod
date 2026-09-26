package com.jeff.pets.mob;

import com.jeff.pets.client.network.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Abstract class that extends {@link TamableAnimal}, providing multiple utilities
 * so that each class doesn't have to define the same logic. <p> When creating a custom entity,
 * always extend either {@link GroundPet}, {@link FlyingPet}, or {@link SlimeLikePet},
 * unless adding custom movement logic,
 * as this file does not contain custom movement logic for the entities.
 * <p> When creating a custom mob, always extend this class, as using the built-in
 * {@link GroundPet} or {@link FlyingPet} logic breaks them when acting as normal mobs.
 *
 * @see FlyingPet
 * @see GroundPet
 */
public abstract class AbstractPet extends TamableAnimal {

    protected final Entity dummy;
    public String petSkin = "";
    public boolean sitting = false;
    protected int waitingTime = 0;
    protected boolean isReturningToOwner = false;
    private float randomZ = (float) (Math.random() - 1);

    protected AbstractPet(EntityType<? extends @NotNull TamableAnimal> type, Level level) {
        super(type, level);
        this.setSpeed(0.5f);
        this.setId(UUID.randomUUID().hashCode());
        this.dummy = new Chicken(EntityType.CHICKEN, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 8.0F).add(Attributes.MOVEMENT_SPEED, 0.23F);
    }

    @Override
    public void tick() {
        super.tick();
        this.dummy.setPos(this.position().multiply(10, 1, 10));
    }

    /**
     * This method is used to determine when the entity stops moving, relative to the player.
     * For example, if we defined this:
     * <pre>{@code protected abstract int stopDistance() {
     *     return 2;
     * }}</pre>
     * The entity would stop moving towards the player when it is two blocks away from the player.
     */
    protected abstract int stopDistance();

    /**
     * Used to define how high the hearts that appear when you right-click on a pet will be.
     * Zero means it would appear right in the middle of the entity's hitbox.
     */
    protected abstract float heartHeight();

    /**
     * Used to define the entity's default ambient sound. For the uses of these last three
     * methods, please refer to {@link FlyingPet} and {@link GroundPet}.
     */
    protected abstract SoundEvent getAmbientSound();

    /**
     * Custom interactions.
     * - Right clicking on a pet with an empty hand will let make hearts appear above it:
     * <pre>
     *     {@code if (this.isTame() && itemStack.isEmpty() && !player.isShiftKeyDown()) {
     *         this.level().addParticle(
     *                 ParticleTypes.HEART,
     *                 this.getX(),
     *                 this.getY() + this.heartHeight(),
     *                 this.getZ(),
     *                 5, 5, 5
     *         );
     *         return InteractionResult.SUCCESS;
     *     }}</pre>
     * - Shifting and right clicking on a pet with an empty hand will pick it up:
     * <pre>
     *     {@code if (this.isTame() && itemStack.isEmpty() && player.isShiftKeyDown()) {
     *         if (!this.isPassenger()) {
     *             this.startRiding(player);
     *             this.lookAt(player, 1f, 1f);
     *             return InteractionResult.SUCCESS;
     *         } else {
     *             this.stopRiding();
     *         }
     *         return InteractionResult.SUCCESS;
     *     }
     *     }
     * </pre>
     *
     * @return It's super method
     */
    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        //if (hand == InteractionHand.MAIN_HAND) return super.mobInteract(player, hand);
        ItemStack itemStack = player.getItemInHand(hand);

        if (this.isTame() && itemStack.isEmpty() && CONFIG.interaction.isDown() && !CONFIG.pickUp.isDown() && !CONFIG.sit.isDown()) {
            try (Level level = this.level()) {
                level.addParticle(
                        ParticleTypes.HEART,
                        this.getX(),
                        this.getY() + this.heartHeight(),
                        this.getZ(),
                        5, 5, 5
                );
            } catch (Exception ignored) {
            }
            return InteractionResult.SUCCESS;
        }

        if (player instanceof LocalPlayer && this.isTame() && itemStack.isEmpty() && CONFIG.pickUp.isDown() && !CONFIG.sit.isDown()) {
            this.sitting = false;
            if (!this.isPassenger()) {
                this.startRiding(player);
                NetworkManager.get().broadcastHeadPayload(Objects.requireNonNull(Minecraft.getInstance().player).getStringUUID(), true);
                return InteractionResult.SUCCESS;
            } else {
                this.stopRiding();
            }
            return InteractionResult.SUCCESS;
        }

        if (this.isTame() && itemStack.isEmpty() && CONFIG.sit.isDown()) {
            this.sitting = !this.sitting;
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    /**
     * Custom method required for making the mob work on servers.
     * <p> Calls: It's super method, if the level is not client-sided.
     */
    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        try (Level level = this.level()) {
            if (!level.isClientSide()) {
                super.onSyncedDataUpdated(key);
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * IMPORTANT: Allows the entity to exist on servers, if only in the {@code ClientLevel}.
     * Never, under any circumstances, remove this method.
     */
    @Override
    public @NotNull Packet<@NotNull ClientGamePacketListener> getAddEntityPacket(@NotNull ServerEntity serverEntity) {
        try (Level level = this.level()) {
            if (level.isClientSide()) {
                return new ClientboundAddEntityPacket(this, serverEntity);
            } else {
                return super.getAddEntityPacket(serverEntity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls the previous abstract method so other classes extending this one don't have to.
     *
     * @return False, as breeding and taming is not needed for any {@code Client-} mobs.
     * Make sure to override this when using a custom-made mob.
     */
    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * These entities are not used in server worlds - as such, there is no reason to
     * return anything. However, this must be overridden when creating a custom entity.
     *
     * @return {@code null}
     */
    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

    /**
     * Easier way to call {@link TamableAnimal#setCustomName} that takes a String rather than a {@link Component}
     */
    public void setName(String string) {
        this.setCustomName(Component.nullToEmpty(string));
    }

    public void wander() {
        if (!CONFIG.wanderingEnabled) return;
        float speed = (float) (this.getSpeed() - 0.35);
        Vec3 lookDir;
        float z = speed * this.randomZ;

        float distance = this.distanceTo(Objects.requireNonNull(this.getOwner()));
        float yVelo = (float) this.getDeltaMovement().y;
        float absDistance = Math.abs(distance);

        if (absDistance > 5) {
            this.isReturningToOwner = true;
            this.reCalcPos();
            this.waitingTime = 15;
        } else if (distance < 2) {
            this.isReturningToOwner = false;
            this.reCalcPos();
            //this.waitingTime = 15;
        }

        if (!this.isReturningToOwner) {
            this.setDeltaMovement(new Vec3(speed, yVelo, z));
        } else {
            this.setDeltaMovement(new Vec3(-speed, yVelo, -z));
        }

        //this.lookAt(EntityAnchorArgument.Anchor.EYES, lookDir);

        double moveX = this.getDeltaMovement().x;
        double moveZ = this.getDeltaMovement().z;

        lookDir = new Vec3(
                this.getX() + (moveX * 2),
                this.getY() + this.getEyeHeight(),
                this.getZ() + (moveZ * 2)
        );
        this.getLookControl().setLookAt(lookDir.x, lookDir.y, lookDir.z, 1.0F, (float) this.getMaxHeadXRot());

        if (moveX * moveX + moveZ * moveZ > 0.001) {
            float targetYaw = (float) (Math.atan2(-moveX, moveZ) * (180D / Math.PI));
            float smoothYaw = net.minecraft.util.Mth.rotLerp(0.2f, this.getYRot(), targetYaw);

            this.setYRot(smoothYaw);
            this.setYHeadRot(smoothYaw);
            this.yBodyRot = smoothYaw;
        }

        if (this.horizontalCollision & this.onGround()) {
            this.jumpFromGround();
        }
        if (!this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
        }
        double dx = lookDir.x - this.getX();
        double dz = lookDir.z - this.getZ();
        float targetYaw = (float) (Math.atan2(-dx, dz) * (180D / Math.PI));

        this.setYRot(targetYaw);
        this.setYHeadRot(targetYaw);
        this.yBodyRot = targetYaw;
    }

    private void reCalcPos() {
        this.randomZ = (float) (Math.random() - 1);
    }

    @Override
    public boolean isTame() {
        if (!(this.getOwner() instanceof LocalPlayer)) {
            return false;
        }
        return super.isTame();
    }

    @Override
    public boolean updateFluidInteraction() {
        try {
            return super.updateFluidInteraction();
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    public boolean canBeCollidedWith(Entity entity) {
        if (entity instanceof Player && CONFIG.hitThroughPets && ((Player) entity).getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            return false;
        }
        return super.canBeCollidedWith(entity);
    }

    @Override
    public boolean isPickable() {
        if (CONFIG.hitThroughPets && !Objects.requireNonNull(this.getOwner()).getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            return false;
        }
        return super.isPickable();
    }
}
