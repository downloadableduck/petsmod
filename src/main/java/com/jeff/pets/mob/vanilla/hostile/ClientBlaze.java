package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;

public class ClientBlaze extends FlyingPet {
   public ClientBlaze(World world) {
      super(world);
        this.setSize(0.6f, 1.8f);
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
      return "mob.blaze.breathe";
   }
}
