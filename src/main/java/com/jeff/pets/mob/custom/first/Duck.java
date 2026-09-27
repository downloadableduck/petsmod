package com.jeff.pets.mob.custom.first;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Duck extends AbstractPet {
   public static final int IS_SERVER_ENTITY = 10;
   public static final int DUCK_SKIN = 11;
   private final float flyDist = 0.0F;
   public float flap;
   public float flapSpeed;
   public float oFlapSpeed;
   public float oFlap;
   public float flapping = 1.0F;
   public boolean isOnHead;
   public ServerPlayerEntity owner = (ServerPlayerEntity)this.getOwner();
   private float nextFlap = 1.0F;

   public Duck(World level) {
      super(level);
      this.setSize(0.5F, 0.5F);
   }

   public static float rotlerp(float start, float end) {
      float f = MathHelper.wrapDegrees(end - start);
      if (f > 10.0F) {
         f = 10.0F;
      }

      if (f < -10.0F) {
         f = -10.0F;
      }

      return start + f;
   }

   @Override
   public void initAttributes() {
      super.initAttributes();
      this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(0.25);
   }

   protected void registerSyncedData() {
      super.registerSyncedData();
      this.syncedData.register(11, 1);
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
      this.oFlap = this.flap;
      this.oFlapSpeed = this.flapSpeed;
      this.flapSpeed = this.flapSpeed + (this.onGround ? -1.0F : 4.0F) * 0.3F;
      this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
      if (!this.onGround && this.flapping < 1.0F) {
         this.flapping = 1.0F;
      }

      this.flapping *= 0.9F;
      Vec3d movement = this.getVelocity();
      if (!this.onGround && movement.y < 0.0) {
         this.lerpVelocity(this.velocityX, this.velocityY, this.velocityZ);
      }

      this.flap = this.flap + this.flapping * 2.0F;
   }

   protected boolean isFlapping() {
      return 0.0F > this.nextFlap;
   }

   protected void onFlap() {
      this.nextFlap = 0.0F + this.flapSpeed / 2.0F;
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 0.5F;
   }

   @Override
   protected String getAmbientSound() {
      return "duck_ambient";
   }

   protected String getHurtSound() {
      return "duck_ambient";
   }

   protected String getDeathSound() {
      return "duck_ambient";
   }

   protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockState) {
      this.playSound("mob.chicken.step", 0.15F, 1.0F);
   }

   @Nullable
   public Duck makeChild(@NotNull PassiveEntity partner) {
      Duck duck = new Duck(this.world);
      duck.setServerEntity(true);
      return duck;
   }

   public EntityData initialize(LocalDifficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      this.syncedData.update(11, this.random.nextInt(2));
      return super.initialize(difficulty, groupData);
   }

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return itemStack.matchesItem(new ItemStack(Items.FISH, 1, 0))
         || itemStack.matchesItem(new ItemStack(Items.FISH, 1, 1))
         || itemStack.matchesItem(new ItemStack(Items.FISH, 1, 2));
   }

   public void tick() {
      super.tick();
      this.oFlap = this.flap;
      this.oFlapSpeed = this.flapSpeed;
      this.flapSpeed = this.flapSpeed + (this.onGround ? -1.0F : 4.0F) * 0.3F;
      this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
      if (!this.onGround && this.flapping < 1.0F) {
         this.flapping = 1.0F;
      }

      this.flapping *= 0.9F;
      Vec3d movement = new Vec3d(this.velocityX, this.velocityY, this.velocityZ);
      if (!this.onGround && movement.y < 0.0) {
         this.lerpVelocity(movement.x * 1.0, movement.y * 0.6, movement.z * 1.0);
      }

      this.flap = this.flap + this.flapping * 2.0F;
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

   public void writeCustomNbt(@NotNull NbtCompound output) {
      super.writeCustomNbt(output);
      output.putBoolean("isServerEntity", true);
      output.putInt("variant", this.syncedData.getInt(11));
   }

   public void readCustomNbt(@NotNull NbtCompound input) {
      super.readCustomNbt(input);
      this.setServerEntity(input.getBoolean("isServerEntity"));
      this.syncedData.update(11, input.getInt("variant"));
   }

   @Override
   public void onDataValueChanged(int key) {
      if (!this.world.isClient) {
         super.onDataValueChanged(key);
      }
   }
}
