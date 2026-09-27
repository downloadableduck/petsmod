package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientHorse extends GroundPet {
   public ClientHorse(World world) {
      super(world);
        this.setSize(1.3965f, 1.6f);
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
      return "mob.horse.idle";
   }
}
