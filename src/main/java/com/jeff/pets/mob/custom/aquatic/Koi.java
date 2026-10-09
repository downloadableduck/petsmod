package com.jeff.pets.mob.custom.aquatic;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Koi extends FlyingPet {
   public static final int IS_SERVER_ENTITY = 20;

   public Koi(World level) {
      super(level);
        this.setSize(0.6f, 0.6f);
   }

   @Override
   public void initAttributes() {
      super.initAttributes();
      this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(1.0);
   }

   protected void registerSyncedData() {
      super.registerSyncedData();
      this.syncedData.register(20, (byte)0);
   }

   public boolean isServerEntity() {
      return this.syncedData.getByte(10) == 1;
   }

   public void setServerEntity(Boolean value) {
      this.syncedData.update(10, (byte)(value ? 1 : 0));
   }

   public void mobTick() {
      super.mobTick();
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

   protected void playStepSound(@NotNull BlockPos pos, @NotNull Block Block) {
      this.playSound("", 0.15F, 1.0F);
   }

   @Nullable
   public Koi makeChild(@NotNull PassiveEntity partner) {
      Koi koi = new Koi(this.world);
      koi.setServerEntity(true);
      return koi;
   }

   /*public Koi initialize(Difficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return this;
   }*/

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return itemStack.matchesItem(new ItemStack(Item.FISH, 1, 0))
         || itemStack.matchesItem(new ItemStack(Item.FISH, 1, 1))
         || itemStack.matchesItem(new ItemStack(Item.FISH, 1, 2));
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
         Vec3d ownerPos = Vec3d.of(owner.x, owner.y, owner.z).add(0.0, owner.getEyeHeight() * 0.8, 0.0);
         Vec3d vecToOwner = ownerPos.subtractFrom(this.getPosVec());
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
            this.lerpVelocity(Vec3d.of(-dir.x * speed, dir.y * speed, -dir.z * speed));
         } else {
            this.lerpVelocity(Vec3d.of(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
         }

         int yHeightToOwner = (int)(owner.y - this.y);
         if (yHeightToOwner > 1) {
            this.jump();
         }

         if (yHeightToOwner > -1 || this.collidingHorizontally) {
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
            this.bodyYaw = this.getHeadYaw() - Math.signum(bodyYawDiff) * 50.0F;
         } else {
            this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -10.0F, 10.0F);
         }

          // No manual move() here: LivingEntity.mobTick() already calls moveRelative() ->
          // move(this.velocityX, ...) on the client, so calling move() again moved the pet
          // twice per tick and doubled its apparent speed.
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
