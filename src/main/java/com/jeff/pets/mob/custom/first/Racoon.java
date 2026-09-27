package com.jeff.pets.mob.custom.first;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Racoon extends AbstractPet {
   public static final int IS_SERVER_ENTITY = 10;
   public boolean isOnHead;

   public Racoon(World level) {
      super(level);
        this.setSize(0.6f, 0.7f);
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 0.8F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.chicken.step";
   }

   @Nullable
   public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return super.initialize(difficulty, groupData);
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

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return false;
   }

   public void tick() {
      super.tick();
      LivingEntity owner = this.getOwner();
      if (owner != null) {
         if (this.vehicle == owner) {
            if (owner.isSneaking() && owner.jumping) {
               this.dismountFromVehicle();
               this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
               this.isOnHead = false;
            } else {
               this.setSitting(true);
            }
         }

         double dx = owner.x - this.x;
         double dz = owner.z - this.z;
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
            Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
            Vec3d dir = targetPos.subtract(this.getPosVec()).normalize();
            this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -50.0F, 50.0F);
            double speed = owner.getSpeed() * 2.0F;
            this.lerpVelocity(new Vec3d(dir.x * speed, this.getVelocity().y, dir.z * speed));
         } else {
            this.lerpVelocity(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityY * 0.8);
         }

         int yHeightToOwner = (int)(owner.y - this.y);
         if (this.collidingHorizontally && this.onGround) {
            this.jump();
         }

         if (yHeightToOwner > -1) {
            this.lerpVelocity(this.getVelocity().add(0.0, -0.01, 0.0));
         }

         if (Utils.squaredDistanceToOrigin(new Vec3d(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
            this.waitingTime++;
            if (this.waitingTime > 30) {
               this.wander();
            }
         } else {
            this.waitingTime = 0;
         }

         if (!this.onGround) {
         }

         this.setYRot(Duck.rotlerp(this.getYRot(), (float)targetYaw));
         if (Math.abs(bodyYawDiff) > 50.0F) {
            this.bodyYaw = this.getHeadYaw() - Math.signum(bodyYawDiff) * 50.0F;
         } else {
            this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -10.0F, 10.0F);
         }

         this.move(this.getVelocity().x, this.getVelocity().y, this.getVelocity().z);
         if (!this.onGround) {
            this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
         }
      }

      if (owner != null && this.distanceTo(owner) >= 10.0F) {
         this.teleport(owner.x, owner.y, owner.z);
      }
   }

   @Nullable
   @Override
   public PassiveEntity makeChild(@NotNull PassiveEntity AgableMob) {
      Racoon racoon = new Racoon(this.world);
      racoon.setServerEntity(false);
      return racoon;
   }

   @Override
   public void onDataValueChanged(int key) {
      if (!this.world.isClient) {
         super.onDataValueChanged(key);
      }
   }
}
