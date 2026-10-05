package com.jeff.pets.mob.custom.first;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Racoon extends AbstractPet {
   public static final int IS_SERVER_ENTITY = 20;
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
   public Racoon initialize(Difficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return this;
   }

   protected void registerSyncedData() {
      super.registerSyncedData();
      this.syncedData.register(20, Byte.valueOf((byte) 0));
   }

   public boolean isServerEntity() {
       return this.syncedData.getByte(IS_SERVER_ENTITY) != 0;
   }

   public void setServerEntity(Boolean value) {
       this.syncedData.update(IS_SERVER_ENTITY, Byte.valueOf((byte) (value.booleanValue() ? 1 : 0)));
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
            Vec3d targetPos = Vec3d.of(owner.x, owner.y, owner.z);
            Vec3d dir = targetPos.subtractFrom(this.getPosVec()).normalize();
            this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -50.0F, 50.0F);
            double speed = owner.getSpeed() * 2.0F;
            this.lerpVelocity(Vec3d.of(-dir.x * speed, this.getVelocity().y, -dir.z * speed));
         } else {
             // Z component was reading velocityY - a copy/paste slip that fed vertical velocity
             // into horizontal motion.
             this.lerpVelocity(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityZ * 0.8);
         }

         int yHeightToOwner = (int)(owner.y - this.y);
         if (this.collidingHorizontally && this.onGround) {
            this.jump();
         }

         if (yHeightToOwner > -1) {
            this.lerpVelocity(this.getVelocity().add(0.0, -0.01, 0.0));
         }

         if (Utils.squaredDistanceToOrigin(Vec3d.of(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
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

             // No manual move() here: LivingEntity.mobTick() already calls moveRelative() ->
             // move(this.velocityX, ...) on the client, so calling move() again moved the pet
             // twice per tick and doubled its apparent speed.
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
      if (!(this.world instanceof ClientWorld)) {
         super.onDataValueChanged(key);
      }
   }
}
