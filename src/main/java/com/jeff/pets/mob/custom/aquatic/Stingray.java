package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Stingray extends FlyingPet {
   public static final int IS_SERVER_ENTITY = 10;
   private final float nextFlap = 1.0F;
   public float oFlap;
   public float flap;
   public float flapping = 1.0F;

   public Stingray(World level) {
      super(level);
        this.setSize(1f, 0.4f);
   }

   @Override
   public void initAttributes() {
      super.initAttributes();
      this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(0.25);
   }

   protected void registerSyncedData() {
      super.registerSyncedData();
      this.syncedData.register(10, (byte)0);
   }

   public boolean isServerEntity() {
      return this.syncedData.getByte(10) == 1;
   }

   public void setServerEntity(Boolean value) {
      this.syncedData.update(10, (byte)(value ? 1 : 0));
   }

   public void mobTick() {
      super.mobTick();
      if (!this.onGround && this.flapping < 1.0F) {
         this.flapping = 1.0F;
      }

      this.flapping *= 0.9F;
      Vec3d movement = this.getVelocity();
      if (!this.onGround && movement.y < 0.0) {
         this.lerpVelocity(this.velocityX * 1.0, this.velocityY * 0.6, this.velocityZ * 1.0);
      }

      this.flap = this.flap + this.flapping * 2.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "";
   }

   protected String getHurtSound() {
      return "";
   }

   protected String getDeathSound() {
      return "";
   }

   protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockState) {
      this.playSound("", 0.15F, 1.0F);
   }

   @Nullable
   public Stingray makeChild(@NotNull PassiveEntity partner) {
      Stingray stringray = new Stingray(this.world);
      stringray.setServerEntity(true);
      return stringray;
   }

   @NotNull
   public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return super.initialize(difficulty, groupData);
   }

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return itemStack.matchesItem(new ItemStack(Items.FISH, 1, 0))
         || itemStack.matchesItem(new ItemStack(Items.FISH, 1, 1))
         || itemStack.matchesItem(new ItemStack(Items.FISH, 1, 2));
   }

   public void writeCustomNbt(@NotNull NbtCompound output) {
      super.writeCustomNbt(output);
      output.putBoolean("isServerEntity", true);
   }

   public void readCustomNbt(@NotNull NbtCompound input) {
      super.readCustomNbt(input);
      this.setServerEntity(input.getBoolean("isServerEntity"));
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 0.5F;
   }

   public boolean canBreatheUnderwater() {
      return true;
   }

   @Override
   public void tick() {
      super.tick();
      this.oFlap = this.flap;
      if (!this.onGround && this.flapping < 1.0F) {
         this.flapping = 1.0F;
      }

      this.flapping *= 0.9F;
      this.flap = this.flap + this.flapping * 2.0F;
      LivingEntity owner = this.getOwner();
      if (owner != null) {
         if (this.vehicle == owner) {
            if (owner.isSneaking() && owner.jumping) {
               this.dismountFromVehicle();
               this.lerpVelocity(this.getVelocity().add(0.0, 0.1, 0.0));
            } else {
               this.setSitting(true);
            }
         }

         double dx = owner.x - this.x;
         double dz = owner.z - this.z;
         Vec3d ownerPos = new Vec3d(owner.x, owner.y, owner.z).add(0.0, owner.getEyeHeight() * 0.8, 0.0);
         Vec3d vecToOwner = ownerPos.subtract(this.getPosVec());
         double targetYaw = Math.atan2(dz, dx) * (180.0 / Math.PI) - 90.0;
         double distance = this.distanceTo(owner);
         float rotation = -this.pitch;
         float rotationToOwner = rotation + -this.getOwner().pitch;
         float bodyYawDiff = MathHelper.wrapDegrees(this.getHeadYaw() - this.bodyYaw);
         if (rotationToOwner >= 50.0F) {
            this.bodyYaw = this.getHeadYaw() - Math.signum(bodyYawDiff) * 50.0F;
         }

         if (distance > 2.0) {
            this.walkAnimationSpeed = 0.5F;
            Vec3d dir = vecToOwner.normalize();
            double speed = 0.2;
            this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -50.0F, 50.0F);
            this.lerpVelocity(new Vec3d(dir.x * speed, dir.y * speed, dir.z * speed));
         } else {
            this.lerpVelocity(new Vec3d(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
         }

         int yHeightToOwner = (int)(owner.y - this.y);
         if (yHeightToOwner > 1 || this.collidingHorizontally) {
            this.jump();
         }

         if (yHeightToOwner > -1) {
            this.lerpVelocity(this.getVelocity().add(0.0, -0.01, 0.0));
         }

         if (!this.onGround) {
         }

         if (Utils.squaredDistanceToOrigin(new Vec3d(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
            this.waitingTime++;
            if (this.waitingTime > 30) {
               this.wander();
            }
         } else {
            this.waitingTime = 0;
         }

         this.setYRot(Duck.rotlerp(this.getYRot(), (float)targetYaw));
         if (Math.abs(bodyYawDiff) > 50.0F) {
            this.bodyYaw = this.getHeadYaw() - Math.signum(bodyYawDiff) * 50.0F;
         } else {
            this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -10.0F, 10.0F);
         }

         this.move(this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);
      }

      if (owner != null && this.distanceTo(owner) >= 10.0F) {
         this.teleport(owner.x, owner.y, owner.z);
      }

      int ambient = (int)(Math.random() * 1200.0);
      if (ambient == 1) {
         this.world.playSound(this.x, this.y, this.z, "", 1.0F, 1.0F, true);
      }
   }
}
