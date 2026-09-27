package com.jeff.pets.mob.vanilla.neutral;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.world.World;

public class ClientSpider extends GroundPet {
   public ClientSpider(World world) {
      super(world);
        this.setSize(1.4f, 0.9f);
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
