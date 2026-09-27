package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientGuardian extends GroundPet {
   public ClientGuardian(World world) {
      super(world);
        this.setSize(0.85f, 0.85f);
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
      return "mob.guardian.idle";
   }
}
