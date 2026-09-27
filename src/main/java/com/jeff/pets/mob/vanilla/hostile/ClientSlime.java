package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.world.World;

public class ClientSlime extends SlimeLikePet {
   public ClientSlime(World world) {
      super(world);
        this.setSize(2f, 2f);
   }

   @Override
   protected int stopDistance() {
      return 4;
   }

   @Override
   protected float heartHeight() {
      return 2.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.slime.big";
   }
}
