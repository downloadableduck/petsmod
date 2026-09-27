package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;

public class ClientTropicalFish extends FlyingPet {
   public ClientTropicalFish(World world) {
      super(world);
        this.setSize(0.5f, 0.4f);
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
      return "";
   }
}
