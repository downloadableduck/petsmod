package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientSnowGolem extends GroundPet {
   public ClientSnowGolem(World world) {
      super(world);
        this.setSize(0.7f, 1.9f);
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
      return "";
   }
}
