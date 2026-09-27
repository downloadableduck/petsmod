package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ClientEnderDragon extends FlyingPet {
   public final double[][] positions = new double[64][3];
   public float oFlapTime;
   public float flapTime;
   public int posPointer = -1;

   public ClientEnderDragon(World world) {
      super(world);
        this.setSize(16f, 8f);
   }

   @Override
   protected int stopDistance() {
      return 10;
   }

   @Override
   protected float heartHeight() {
      return 3.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.enderdragon.growl";
   }

   @Override
   public void tick() {
      super.tick();
      this.oFlapTime = this.flapTime;
      Vec3d vec3 = this.getVelocity();
      float g = 0.2F / ((float)vec3.y * 10.0F + 1.0F);
      g *= (float)Math.pow(2.0, vec3.y);
      if (this.isInWall()) {
         this.flapTime += g * 0.5F;
      } else {
         this.flapTime += g;
      }
   }

   public double[] getLatencyPos(int i, float f) {
      if (this.dead) {
         f = 0.0F;
      }

      f = 1.0F - f;
      int j = this.posPointer - i & 63;
      int k = this.posPointer - i - 1 & 63;
      double[] ds = new double[3];
      double d = this.positions[j][0];
      double e = MathHelper.wrapDegrees(this.positions[k][0] - d);
      ds[0] = d + e * f;
      d = this.positions[j][1];
      e = this.positions[k][1] - d;
      ds[1] = d + e * f;
      ds[2] = Math2.lerp(f, this.positions[j][2], this.positions[k][2]);
      return ds;
   }

   public float getHeadPartYOffset(int i, double[] ds, double[] es) {
      double e;
      if (i == 6) {
         e = 0.0;
      } else {
         e = es[1] - ds[1];
      }

      return (float)e;
   }
}
