package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientPig extends GroundPet {
   public ClientPig(World world) {
      super(world);
        this.setSize(0.9f, 0.9f);
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
      return "mob.pig.say";
   }
}
