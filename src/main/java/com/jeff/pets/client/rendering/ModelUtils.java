package com.jeff.pets.client.rendering;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class ModelUtils {
   public static void animateCrossbowHold(RendererModel RendererModel, RendererModel RendererModel2, RendererModel RendererModel3, boolean bl) {
      RendererModel RendererModel4 = bl ? RendererModel : RendererModel2;
      RendererModel RendererModel5 = bl ? RendererModel2 : RendererModel;
      RendererModel4.yRot = (bl ? -0.3F : 0.3F) + RendererModel3.yRot;
      RendererModel5.yRot = (bl ? 0.6F : -0.6F) + RendererModel3.yRot;
      RendererModel4.xRot = (float) (-Math.PI / 2) + RendererModel3.xRot + 0.1F;
      RendererModel5.xRot = -1.5F + RendererModel3.xRot;
   }

   public static void animateCrossbowCharge(RendererModel RendererModel, RendererModel RendererModel2, LivingEntity livingEntity, boolean bl) {
      RendererModel RendererModel3 = bl ? RendererModel : RendererModel2;
      RendererModel RendererModel4 = bl ? RendererModel2 : RendererModel;
      RendererModel3.yRot = bl ? -0.8F : 0.8F;
      RendererModel3.xRot = -0.97079635F;
      RendererModel4.xRot = RendererModel3.xRot;
      float f = CrossbowItem.getChargeDuration(livingEntity.getUseItem());
      float g = MathHelper.clamp((float)livingEntity.getTicksUsingItem(), 0.0F, f);
      float h = g / f;
      RendererModel4.yRot = MathHelper.lerp(h, 0.4F, 0.85F) * (bl ? 1 : -1);
      RendererModel4.xRot = MathHelper.lerp(h, RendererModel4.xRot, (float) (-Math.PI / 2));
   }

   public static <T extends MobEntity> void swingWeaponDown(RendererModel RendererModel, RendererModel RendererModel2, T mob, float f, float g) {
      float h = MathHelper.sin(f * (float) Math.PI);
      float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
      RendererModel.zRot = 0.0F;
      RendererModel2.zRot = 0.0F;
      RendererModel.yRot = (float) (Math.PI / 20);
      RendererModel2.yRot = (float) (-Math.PI / 20);
      if (mob.getMainArm() == HandSide.RIGHT) {
         RendererModel.xRot = -1.8849558F + MathHelper.cos(g * 0.09F) * 0.15F;
         RendererModel2.xRot = -0.0F + MathHelper.cos(g * 0.19F) * 0.5F;
         RendererModel.xRot += h * 2.2F - i * 0.4F;
         RendererModel2.xRot += h * 1.2F - i * 0.4F;
      } else {
         RendererModel.xRot = -0.0F + MathHelper.cos(g * 0.19F) * 0.5F;
         RendererModel2.xRot = -1.8849558F + MathHelper.cos(g * 0.09F) * 0.15F;
         RendererModel.xRot += h * 1.2F - i * 0.4F;
         RendererModel2.xRot += h * 2.2F - i * 0.4F;
      }

      bobArms(RendererModel, RendererModel2, g);
   }

   public static void bobArms(RendererModel RendererModel, RendererModel RendererModel2, float f) {
      RendererModel.zRot = RendererModel.zRot + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
      RendererModel2.zRot = RendererModel2.zRot - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
      RendererModel.xRot = RendererModel.xRot + MathHelper.sin(f * 0.067F) * 0.05F;
      RendererModel2.xRot = RendererModel2.xRot - MathHelper.sin(f * 0.067F) * 0.05F;
   }

   public static void animateZombieArms(RendererModel RendererModel, RendererModel RendererModel2, boolean bl, float f, float g) {
      float h = MathHelper.sin(f * (float) Math.PI);
      float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
      RendererModel2.zRot = 0.0F;
      RendererModel.zRot = 0.0F;
      RendererModel2.yRot = -(0.1F - h * 0.6F);
      RendererModel.yRot = 0.1F - h * 0.6F;
      float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
      RendererModel2.xRot = j;
      RendererModel.xRot = j;
      RendererModel2.xRot += h * 1.2F - i * 0.4F;
      RendererModel.xRot += h * 1.2F - i * 0.4F;
      bobArms(RendererModel2, RendererModel, g);
   }

    public static float rotlerpRad(float f, float g, float h) {
        float i = (g - f) % (float) (Math.PI * 2);
        if (i < (float) -Math.PI) {
            i += (float) (Math.PI * 2);
        }

        if (i >= (float) Math.PI) {
            i -= (float) (Math.PI * 2);
        }

        return f + h * i;
    }
}
