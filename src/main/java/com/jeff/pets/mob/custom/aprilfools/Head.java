package com.jeff.pets.mob.custom.aprilfools;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff.pets.mob.custom.first.Duck.rotlerp;

public class Head extends AbstractPet {
   public static final int IS_SERVER_ENTITY = 10;

   public Head(World level) {
      super(level);
        this.setSize(0.5f, 0.5f);
   }

   @Nullable
   @Override
   public PassiveEntity makeChild(@NotNull PassiveEntity AgableMob) {
      return new Head(this.world);
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
      Vec3d movement = this.getVelocity();
      if (!this.onGround && movement.y < 0.0) {
         this.lerpVelocity(this.velocityX * 1.0, this.velocityY * 0.6, this.velocityZ * 1.0);
      }
   }

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return itemStack.matchesItem(new ItemStack(Items.CARROT));
   }

   public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return super.initialize(difficulty, groupData);
   }

   @Override
   protected int stopDistance() {
      return 0;
   }

   @Override
   protected float heartHeight() {
      return 0.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.chicken.step";
   }

   public void tick() {
       super.tick();
       Vec3d movement = new Vec3d(this.velocityX, this.velocityY, this.velocityZ);
       if (!this.onGround && movement.y < 0.0) {
           this.lerpVelocity(movement.x * 1.0, movement.y * 0.6, movement.z * 1.0);
       }

       LivingEntity owner = this.getOwner();
       if (owner != null) {
           if (this.vehicle == owner) {
               if (owner.isSneaking() && owner.jumping) {
                   this.dismountFromVehicle();
                   this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
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
               this.bodyYaw = this.headYaw - Math.signum(bodyYawDiff) * 50.0F;
           }

           if (distance > 2.0) {
               this.walkAnimationSpeed = 0.5F;
               Vec3d targetPos = new Vec3d(owner.x, owner.y, owner.z);
               Vec3d dir = targetPos.subtract(this.getPosVec()).normalize();
               this.setYRot(rotlerp(this.getYRot(), (float)targetYaw));
               this.bodyYaw = this.bodyYaw + MathHelper.clamp(this.getHeadYaw() - this.bodyYaw, -50.0F, 50.0F);
               double speed = owner.getSpeed() * 2.0F;
               this.lerpVelocity(new Vec3d(dir.x * speed, this.getVelocity().y, dir.z * speed));
           } else {
               this.lookAt(this.getOwner(), 5.0F, 0.0F);
               this.lerpVelocity(this.velocityX * 0.8, this.velocityY * 1.0, this.velocityZ * 0.8);
           }

           int yHeightToOwner = (int)(owner.y - this.y);
           if (this.collidingHorizontally && this.onGround) {
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

           this.setYRot(rotlerp(this.getYRot(), (float)targetYaw));
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

       int ambient = (int)(Math.random() * 1200.0);
       if (ambient == 1) {
           this.world.playSound(this.x, this.y, this.z, "duck_ambient", 1.0F, 1.0F, true);
       }
   }

   @Override
   public void onDataValueChanged(int key) {
      if (!this.world.isClient) {
         super.onDataValueChanged(key);
      }
   }

   public void writeCustomNbt(@NotNull NbtCompound output) {
      super.writeCustomNbt(output);
      output.putBoolean("isServerEntity", true);
   }

   public void readCustomNbt(@NotNull NbtCompound input) {
      super.readCustomNbt(input);
      this.setServerEntity(input.getBoolean("isServerEntity"));
   }
}
