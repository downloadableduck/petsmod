package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.SlimeLikePet;
import net.minecraft.world.World;

public class ClientRabbit extends SlimeLikePet {
   public ClientRabbit(World world) {
      super(world);
        this.setSize(0.4f, 0.5f);
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
      return "mob.rabbit.idle";
   }
}
