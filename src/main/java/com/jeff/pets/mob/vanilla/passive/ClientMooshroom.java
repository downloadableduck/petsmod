package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientMooshroom extends GroundPet {
   public ClientMooshroom(World world) {
      super(world);
        this.setSize(0.9f, 1.4f);
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
      return "mob.cow.say";
   }
}
