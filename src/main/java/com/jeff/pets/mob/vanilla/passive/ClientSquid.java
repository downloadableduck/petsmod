package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ClientSquid extends FlyingPet {
   public float xBodyRotO;
   public float xBodyRot;
   public float zBodyRotO;
   public float zBodyRot;
   public float tentacleMovement;
   public float oldTentacleMovement;
   public float tentacleAngle;
   public float oldTentacleAngle;
   private float speed;
   private float tentacleSpeed;
   private float rotateSpeed;
   private float tx;
   private float ty;
   private float tz;

   public ClientSquid(World world) {
      super(world);
        this.setSize(0.8f, 0.8f);
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 2.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "";
   }

   @Override
   public void tick() {
      super.tick();
      this.xBodyRotO = this.xBodyRot;
      this.zBodyRotO = this.zBodyRot;
      this.oldTentacleMovement = this.tentacleMovement;
      this.oldTentacleAngle = this.tentacleAngle;
      this.tentacleMovement = this.tentacleMovement + this.tentacleSpeed;
      if (this.tentacleMovement > Math.PI * 2) {
         if (this.world.isClient) {
            this.tentacleMovement = (float) (Math.PI * 2);
         } else {
            this.tentacleMovement -= (float) (Math.PI * 2);
            if (this.random.nextInt(10) == 0) {
               this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
            }

            this.world.doEntityEvent(this, (byte)19);
         }
      }

      if (this.isInWater()) {
         if (this.tentacleMovement < (float) Math.PI) {
            float f = this.tentacleMovement / (float) Math.PI;
            this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
            if (f > 0.75) {
               this.speed = 1.0F;
               this.rotateSpeed = 1.0F;
            } else {
               this.rotateSpeed *= 0.8F;
            }
         } else {
            this.tentacleAngle = 0.0F;
            this.speed *= 0.9F;
            this.rotateSpeed *= 0.99F;
         }

         if (!this.world.isClient) {
            this.addVelocity(this.tx * this.speed, this.ty * this.speed, this.tz * this.speed);
         }

         Vec3d vec3 = this.getVelocity();
         double d = Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z);
         this.bodyYaw = this.bodyYaw + (-((float)MathHelper.fastAtan2(vec3.x, vec3.z)) * (180.0F / (float)Math.PI) - this.bodyYaw) * 0.1F;
         this.setYRot(this.bodyYaw);
         this.zBodyRot = this.zBodyRot + (float) Math.PI * this.rotateSpeed * 1.5F;
         this.xBodyRot = this.xBodyRot + (-((float)MathHelper.fastAtan2(d, vec3.y)) * (180.0F / (float)Math.PI) - this.xBodyRot) * 0.1F;
      } else {
         this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.tentacleMovement)) * (float) Math.PI * 0.25F;
         if (!this.world.isClient) {
            double e = this.getVelocity().y;
            if (this.hasStatusEffect(null)) {
               e = 0.05 * (this.getEffectInstance(null).getAmplifier() + 1);
            } else {
               e--;
            }

            this.addVelocity(0.0, e * 0.98F, 0.0);
         }

         this.xBodyRot = this.xBodyRot + (-90.0F - this.xBodyRot) * 0.02F;
      }
   }

   public void addVelocity(double x, double y, double z) {
      super.addVelocity(x, y, z);
      this.tx = (float)x;
      this.ty = (float)y;
      this.tz = (float)z;
   }
}
