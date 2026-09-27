package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;

public class ClientGhast extends FlyingPet {
   public ClientGhast(World world) {
      super(world);
        this.setSize(4f, 4f);
   }

   @Override
   protected int stopDistance() {
      return 6;
   }

   @Override
   protected float heartHeight() {
      return 6.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.ghast.moan";
   }
}
