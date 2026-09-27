package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;

public class ClientElderGuardian extends FlyingPet {
   public ClientElderGuardian(World world) {
      super(world);
        this.setSize(1.9975f, 1.9975f);
   }

   @Override
   protected int stopDistance() {
      return 4;
   }

   @Override
   protected float heartHeight() {
      return 4.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.guardian.elder.idle";
   }
}
