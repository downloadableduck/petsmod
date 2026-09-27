package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientSheep extends GroundPet {
   public ClientSheep(World world) {
      super(world);
        this.setSize(0.9f, 1.3f);
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
      return "mob.sheep.say";
   }
}
