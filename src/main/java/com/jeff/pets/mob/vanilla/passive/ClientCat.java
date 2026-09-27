package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientCat extends GroundPet {
   public boolean isOnHead;

   public ClientCat(World world) {
      super(world);
        this.setSize(0.6f, 0.7f);
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 0.5F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.cat.meow";
   }
}
