package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientShulker extends GroundPet {
   public ClientShulker(World world) {
      super(world);
        this.setSize(1f, 2f);
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
}
