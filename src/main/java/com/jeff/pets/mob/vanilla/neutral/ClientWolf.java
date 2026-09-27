package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientWolf extends GroundPet {
   public ClientWolf(World world) {
      super(world);
        this.setSize(0.6f, 0.85f);
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 1.5F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.wolf.bark";
   }
}
