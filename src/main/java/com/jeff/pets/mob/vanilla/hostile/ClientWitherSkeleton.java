package com.jeff.pets.mob.vanilla.hostile;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.monster.RangedAttackMob;
import net.minecraft.world.World;

public class ClientWitherSkeleton extends GroundPet implements RangedAttackMob {
   public ClientWitherSkeleton(World world) {
      super(world);
        this.setSize(0.6f, 1.95f);
   }

   @Override
   protected int stopDistance() {
      return 2;
   }

   @Override
   protected float heartHeight() {
      return 2.0F;
   }

   @Override
   protected String getAmbientSound() {
      return "mob.skeleton.say";
   }

   public void doRangedAttack(LivingEntity livingEntity, float f) {
   }
}
