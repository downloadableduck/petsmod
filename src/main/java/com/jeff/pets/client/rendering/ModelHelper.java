package com.jeff.pets.client.rendering;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class ModelHelper {
   public static void animateCrossbowHold(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, ModelRenderer ModelRenderer3, boolean bl) {
      ModelRenderer ModelRenderer4 = bl ? ModelRenderer : ModelRenderer2;
      ModelRenderer ModelRenderer5 = bl ? ModelRenderer2 : ModelRenderer;
      ModelRenderer4.yRot = (bl ? -0.3F : 0.3F) + ModelRenderer3.yRot;
      ModelRenderer5.yRot = (bl ? 0.6F : -0.6F) + ModelRenderer3.yRot;
      ModelRenderer4.xRot = (float) (-Math.PI / 2) + ModelRenderer3.xRot + 0.1F;
      ModelRenderer5.xRot = -1.5F + ModelRenderer3.xRot;
   }

   public static void animateCrossbowCharge(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, LivingEntity livingEntity, boolean bl) {
      ModelRenderer ModelRenderer3 = bl ? ModelRenderer : ModelRenderer2;
      ModelRenderer ModelRenderer4 = bl ? ModelRenderer2 : ModelRenderer;
      ModelRenderer3.yRot = bl ? -0.8F : 0.8F;
      ModelRenderer3.xRot = -0.97079635F;
      ModelRenderer4.xRot = ModelRenderer3.xRot;
      float f = CrossbowItem.getChargeDuration(livingEntity.getUseItem());
      float g = MathHelper.clamp((float)livingEntity.getTicksUsingItem(), 0.0F, f);
      float h = g / f;
      ModelRenderer4.yRot = MathHelper.lerp(h, 0.4F, 0.85F) * (bl ? 1 : -1);
      ModelRenderer4.xRot = MathHelper.lerp(h, ModelRenderer4.xRot, (float) (-Math.PI / 2));
   }

   public static <T extends MobEntity> void swingWeaponDown(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, T mob, float f, float g) {
      float h = MathHelper.sin(f * (float) Math.PI);
      float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
      ModelRenderer.zRot = 0.0F;
      ModelRenderer2.zRot = 0.0F;
      ModelRenderer.yRot = (float) (Math.PI / 20);
      ModelRenderer2.yRot = (float) (-Math.PI / 20);
      if (mob.getMainArm() == HandSide.RIGHT) {
         ModelRenderer.xRot = -1.8849558F + MathHelper.cos(g * 0.09F) * 0.15F;
         ModelRenderer2.xRot = -0.0F + MathHelper.cos(g * 0.19F) * 0.5F;
         ModelRenderer.xRot += h * 2.2F - i * 0.4F;
         ModelRenderer2.xRot += h * 1.2F - i * 0.4F;
      } else {
         ModelRenderer.xRot = -0.0F + MathHelper.cos(g * 0.19F) * 0.5F;
         ModelRenderer2.xRot = -1.8849558F + MathHelper.cos(g * 0.09F) * 0.15F;
         ModelRenderer.xRot += h * 1.2F - i * 0.4F;
         ModelRenderer2.xRot += h * 2.2F - i * 0.4F;
      }

      bobArms(ModelRenderer, ModelRenderer2, g);
   }

   public static void bobArms(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, float f) {
      ModelRenderer.zRot = ModelRenderer.zRot + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
      ModelRenderer2.zRot = ModelRenderer2.zRot - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
      ModelRenderer.xRot = ModelRenderer.xRot + MathHelper.sin(f * 0.067F) * 0.05F;
      ModelRenderer2.xRot = ModelRenderer2.xRot - MathHelper.sin(f * 0.067F) * 0.05F;
   }

   public static void animateZombieArms(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, boolean bl, float f, float g) {
      float h = MathHelper.sin(f * (float) Math.PI);
      float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
      ModelRenderer2.zRot = 0.0F;
      ModelRenderer.zRot = 0.0F;
      ModelRenderer2.yRot = -(0.1F - h * 0.6F);
      ModelRenderer.yRot = 0.1F - h * 0.6F;
      float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
      ModelRenderer2.xRot = j;
      ModelRenderer.xRot = j;
      ModelRenderer2.xRot += h * 1.2F - i * 0.4F;
      ModelRenderer.xRot += h * 1.2F - i * 0.4F;
      bobArms(ModelRenderer2, ModelRenderer, g);
   }
}
