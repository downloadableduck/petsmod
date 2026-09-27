package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientZombiePigman extends GroundPet {
   public ClientZombiePigman(World world) {
      super(world);
        this.setSize(0.6f, 1.95f);
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
      return "mob.zombiepig.zpigangry";
   }
}
