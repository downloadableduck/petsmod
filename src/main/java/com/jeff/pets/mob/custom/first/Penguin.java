package com.jeff.pets.mob.custom.first;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.block.Block;
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
import net.minecraft.world.Difficulty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Penguin extends AbstractPet {
   public static final int IS_SERVER_ENTITY = 20;
   private final float nextFlap = 1.0F;
   public float flap;
   public float flapSpeed;
   public float oFlapSpeed;
   public float oFlap;
   public float flapping = 1.0F;
   public ServerPlayerEntity owner = (ServerPlayerEntity)this.getOwner();
   public boolean isOnHead;
   private boolean isFlapping = !this.onGround;

   public Penguin(World level) {
      super(level);
        this.setSize(0.7f, 1f);
   }

   @Override
   public void initAttributes() {
      super.initAttributes();
      this.getAttribute(EntityAttributes.MOVEMENT_SPEED).setBase(0.25);
   }

   @Override
   protected int stopDistance() {
      return 0;
   }

   @Override
   protected float heartHeight() {
      return 1.3F;
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
         this.lerpVelocity(this.velocityX * 1.0, this.velocityY * 0.6, this.velocityZ * 1.0);
      }

      this.flap = this.flap + this.flapping * 2.0F;
   }

   protected boolean isFlapping() {
      return this.isFlapping;
   }

   protected void onFlap() {
   }

   @Override
   protected String getAmbientSound() {
      return "penguin_ambient";
   }

   protected String getHurtSound() {
      return "penguin_ambient";
   }

   protected String getDeathSound() {
      return "penguin_ambient";
   }

   protected void playStepSound(@NotNull BlockPos pos, @NotNull Block Block) {
      this.playSound("mob.chicken.step", 0.15F, 1.0F);
   }

   @Nullable
   public Penguin makeChild(@NotNull PassiveEntity partner) {
      Penguin penguin = new Penguin(this.world);
      penguin.setServerEntity(true);
      return penguin;
   }

   public Penguin initialize(Difficulty difficulty, @Nullable EntityData groupData) {
      this.setServerEntity(true);
      return this;
   }

   @Override
   public boolean isBreedingItem(@NotNull ItemStack itemStack) {
      return itemStack.matchesItem(new ItemStack(Items.FISH, 1, 0))
         || itemStack.matchesItem(new ItemStack(Items.FISH, 1, 1))
         || itemStack.matchesItem(new ItemStack(Items.FISH, 1, 2));
   }

   public void tick() {
      super.tick();
      LivingEntity owner = this.getOwner();
      if (owner != null) {
         if (this.vehicle == owner) {
            this.isFlapping = false;
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
             if (!this.onGround) {
             this.lerpVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
             }
      }

      if (owner != null && this.distanceTo(owner) >= 10.0F) {
         this.teleport(owner.x, owner.y, owner.z);
      }

      int ambient = (int)(Math.random() * 1200.0);
      if (ambient == 1) {
         this.world.playSound(this.x, this.y, this.z, "penguin_ambient", 1.0F, 1.0F, true);
      }
   }

   @Override
   public void onDataValueChanged(int key) {
      if (!(this.world instanceof ClientWorld)) {
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
