package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientCreeper extends GroundPet {
   public boolean isPowered = false;

   public ClientCreeper(World world) {
      super(world);
        this.setSize(0.6f, 1.7f);
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
      return "creeper.primed";
   }
}
