package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.Block;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.animal.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Stingray extends FlyingPet {
   public static final int IS_SERVER_ENTITY = 20;
   private final float nextFlap = 1.0F;
   public float oFlap;
   public float flap;
   public float flapping = 1.0F;

   public Stingray(World level) {
      super(level);
        this.setSize(1f, 0.4f);
       this.texture = "/mob/stingray/stingray.png";
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

   @Nullable
   public Stingray makeChild(@NotNull AnimalEntity partner) {
      Stingray stringray = new Stingray(this.world);
      return stringray;
   }

  /* @NotNull
   public Stingray initialize(Difficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return this;
   }*/

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return itemStack.equals(new ItemStack(Item.FISH.id, 1, 0))
         || itemStack.equals(new ItemStack(Item.FISH.id, 1, 1))
         || itemStack.equals(new ItemStack(Item.FISH.id, 1, 2));
   }

   public void writeCustomNbt(@NotNull NbtCompound output) {
      super.writeCustomNbt(output);
      output.putBoolean("isServerEntity", true);
   }

   public void readCustomNbt(@NotNull NbtCompound input) {
      super.readCustomNbt(input);
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
      MobEntity owner = this.getOwner();
      if (owner != null) {
         this.yaw = this.getOwner().yaw + 180;
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
         Vec3d ownerPos = Vec3d.of(owner.x, owner.y, owner.z).add(0.0, owner.getEyeHeight() * 0.8, 0.0);
         Vec3d vecToOwner = ownerPos.subtractFrom(this.getPosVec());
         double targetYaw = Math.atan2(dz, dx) * (180.0 / Math.PI) - 90.0;
         double distance = this.distanceTo(owner);
         float rotation = -this.pitch;
         float rotationToOwner = rotation + -this.getOwner().pitch;
         float bodyYawDiff = Utils.wrapDegrees(this.yaw - this.bodyYaw);
         if (rotationToOwner >= 50.0F) {
            this.bodyYaw = this.yaw - Math.signum(bodyYawDiff) * 50.0F;
         }

         if (distance > 2.0) {
            this.walkAnimationSpeed = 0.5F;
            Vec3d dir = vecToOwner.normalize();
            double speed = 0.2;
            this.bodyYaw = this.bodyYaw + Math.max(-50.0F, Math.min(50.0F, this.yaw - this.bodyYaw));
            this.lerpVelocity(Vec3d.of(-dir.x * speed, dir.y * speed, -dir.z * speed));
         } else {
            this.lerpVelocity(Vec3d.of(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
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

         if (Utils.squaredDistanceToOrigin(Vec3d.of(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
            this.waitingTime++;
            if (this.waitingTime > 30) {
               this.wander();
            }
         } else {
            this.waitingTime = 0;
         }

         this.setYRot(Duck.rotlerp(this.getYRot(), (float)targetYaw));
         if (Math.abs(bodyYawDiff) > 50.0F) {
            this.bodyYaw = this.yaw - Math.signum(bodyYawDiff) * 50.0F;
         } else {
            this.bodyYaw = this.bodyYaw + Math.max(-10.0F, Math.min(10.0F, this.yaw - this.bodyYaw));
         }

          // No manual move() here: LivingEntity.mobTick() already calls moveRelative() ->
          // move(this.velocityX, ...) on the client, so calling move() again moved the pet
          // twice per tick and doubled its apparent speed.
      }

      if (owner != null && this.distanceTo(owner) >= 10.0F) {
         this.setPosition(owner.x, owner.y, owner.z);
      }

      int ambient = (int)(Math.random() * 1200.0);
      if (ambient == 1) {
         this.world.playSound(this.x, this.y, this.z, "", 1.0F, 1.0F);
      }
   }
}
