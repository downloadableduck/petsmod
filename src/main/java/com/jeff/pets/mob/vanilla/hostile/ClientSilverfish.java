package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientSilverfish extends GroundPet {
   public ClientSilverfish(World world) {
      super(world);
        this.setSize(0.4f, 0.3f);
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
      return "mob.silverfish.say";
   }
}
