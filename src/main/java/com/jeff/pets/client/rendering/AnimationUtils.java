package com.jeff.pets.client.rendering;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CrossbowItem;

public class AnimationUtils {
   public static void animateCrossbowHold(ModelPart ModelPart, ModelPart ModelPart2, ModelPart ModelPart3, boolean bl) {
      ModelPart ModelPart4 = bl ? ModelPart : ModelPart2;
      ModelPart ModelPart5 = bl ? ModelPart2 : ModelPart;
      ModelPart4.yRot = (bl ? -0.3F : 0.3F) + ModelPart3.yRot;
      ModelPart5.yRot = (bl ? 0.6F : -0.6F) + ModelPart3.yRot;
      ModelPart4.xRot = (float) (-Math.PI / 2) + ModelPart3.xRot + 0.1F;
      ModelPart5.xRot = -1.5F + ModelPart3.xRot;
   }

   public static void animateCrossbowCharge(ModelPart ModelPart, ModelPart ModelPart2, LivingEntity livingEntity, boolean bl) {
      ModelPart ModelPart3 = bl ? ModelPart : ModelPart2;
      ModelPart ModelPart4 = bl ? ModelPart2 : ModelPart;
      ModelPart3.yRot = bl ? -0.8F : 0.8F;
      ModelPart3.xRot = -0.97079635F;
      ModelPart4.xRot = ModelPart3.xRot;
      float f = CrossbowItem.getChargeDuration(livingEntity.getUseItem());
      float g = Mth.clamp((float)livingEntity.getTicksUsingItem(), 0.0F, f);
      float h = g / f;
      ModelPart4.yRot = Mth.lerp(h, 0.4F, 0.85F) * (bl ? 1 : -1);
      ModelPart4.xRot = Mth.lerp(h, ModelPart4.xRot, (float) (-Math.PI / 2));
   }

   public static <T extends Mob> void swingWeaponDown(ModelPart ModelPart, ModelPart ModelPart2, T mob, float f, float g) {
      float h = Mth.sin(f * (float) Math.PI);
      float i = Mth.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
      ModelPart.zRot = 0.0F;
      ModelPart2.zRot = 0.0F;
      ModelPart.yRot = (float) (Math.PI / 20);
      ModelPart2.yRot = (float) (-Math.PI / 20);
      if (mob.getMainArm() == HumanoidArm.RIGHT) {
         ModelPart.xRot = -1.8849558F + Mth.cos(g * 0.09F) * 0.15F;
         ModelPart2.xRot = -0.0F + Mth.cos(g * 0.19F) * 0.5F;
         ModelPart.xRot += h * 2.2F - i * 0.4F;
         ModelPart2.xRot += h * 1.2F - i * 0.4F;
      } else {
         ModelPart.xRot = -0.0F + Mth.cos(g * 0.19F) * 0.5F;
         ModelPart2.xRot = -1.8849558F + Mth.cos(g * 0.09F) * 0.15F;
         ModelPart.xRot += h * 1.2F - i * 0.4F;
         ModelPart2.xRot += h * 2.2F - i * 0.4F;
      }

      bobArms(ModelPart, ModelPart2, g);
   }

   public static void bobArms(ModelPart ModelPart, ModelPart ModelPart2, float f) {
      ModelPart.zRot = ModelPart.zRot + (Mth.cos(f * 0.09F) * 0.05F + 0.05F);
      ModelPart2.zRot = ModelPart2.zRot - (Mth.cos(f * 0.09F) * 0.05F + 0.05F);
      ModelPart.xRot = ModelPart.xRot + Mth.sin(f * 0.067F) * 0.05F;
      ModelPart2.xRot = ModelPart2.xRot - Mth.sin(f * 0.067F) * 0.05F;
   }

   public static void animateZombieArms(ModelPart ModelPart, ModelPart ModelPart2, boolean bl, float f, float g) {
      float h = Mth.sin(f * (float) Math.PI);
      float i = Mth.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
      ModelPart2.zRot = 0.0F;
      ModelPart.zRot = 0.0F;
      ModelPart2.yRot = -(0.1F - h * 0.6F);
      ModelPart.yRot = 0.1F - h * 0.6F;
      float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
      ModelPart2.xRot = j;
      ModelPart.xRot = j;
      ModelPart2.xRot += h * 1.2F - i * 0.4F;
      ModelPart.xRot += h * 1.2F - i * 0.4F;
      bobArms(ModelPart2, ModelPart, g);
   }
}
