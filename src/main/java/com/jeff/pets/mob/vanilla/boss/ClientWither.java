package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.mob.FlyingPet;
import net.minecraft.world.World;

public class ClientWither extends FlyingPet {
   public ClientWither(World world) {
      super(world);
        this.setSize(2f, 3f);
   }

   @Override
   protected int stopDistance() {
      return 6;
   }

   @Override
   protected float heartHeight() {
      return 4.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.chicken.step";
   }
}
