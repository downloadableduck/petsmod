package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientCaveSpider extends GroundPet {
   public ClientCaveSpider(World world) {
      super(world);
        this.setSize(0.7f, 0.5f);
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
      return "mob.spider.say";
   }
}
