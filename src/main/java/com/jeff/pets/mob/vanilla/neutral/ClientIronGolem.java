package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientIronGolem extends GroundPet {
   public ClientIronGolem(World world) {
      super(world);
        this.setSize(1.4f, 2.7f);
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
      return "mob.irongolem.walk";
   }
}
