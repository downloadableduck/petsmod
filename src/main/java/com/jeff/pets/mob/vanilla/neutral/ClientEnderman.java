package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientEnderman extends GroundPet {
   public ClientEnderman(World world) {
      super(world);
        this.setSize(0.6f, 2.9f);
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 3.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.endermen.idle";
   }
}
