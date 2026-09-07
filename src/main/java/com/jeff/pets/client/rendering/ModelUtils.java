package com.jeff.pets.client.rendering;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelUtils {
    public static void animateCrossbowHold(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, ModelRenderer ModelRenderer3, boolean bl) {
        ModelRenderer ModelRenderer4 = bl ? ModelRenderer : ModelRenderer2;
        ModelRenderer ModelRenderer5 = bl ? ModelRenderer2 : ModelRenderer;
        ModelRenderer4.rotateAngleY = (bl ? -0.3F : 0.3F) + ModelRenderer3.rotateAngleY;
        ModelRenderer5.rotateAngleY = (bl ? 0.6F : -0.6F) + ModelRenderer3.rotateAngleY;
        ModelRenderer4.rotateAngleX = (float) (-Math.PI / 2) + ModelRenderer3.rotateAngleX + 0.1F;
        ModelRenderer5.rotateAngleX = -1.5F + ModelRenderer3.rotateAngleX;
    }

    public static void animateCrossbowCharge(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, EntityLivingBase livingEntity, boolean bl) {
        ModelRenderer ModelRenderer3 = bl ? ModelRenderer : ModelRenderer2;
        ModelRenderer ModelRenderer4 = bl ? ModelRenderer2 : ModelRenderer;
        ModelRenderer3.rotateAngleY = bl ? -0.8F : 0.8F;
        ModelRenderer3.rotateAngleX = -0.97079635F;
        ModelRenderer4.rotateAngleX = ModelRenderer3.rotateAngleX;
        float f = livingEntity.getActiveItemStack().getMaxStackSize();
        float g = MathHelper.clamp((float) livingEntity.getItemInUseCount(), 0.0F, f);
        float h = g / f;
        ModelRenderer4.rotateAngleY = (float) MathHelper.clampedLerp(h, 0.4D, 0.85D) * (bl ? 1 : -1);
        ModelRenderer4.rotateAngleX = (float) MathHelper.clampedLerp(h, ModelRenderer4.rotateAngleX, -((float) Math.PI / 2));
    }

    public static <T extends EntityLiving> void swingWeaponDown(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, T mob, float f, float g) {
        float h = MathHelper.sin(f * (float) Math.PI);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
        ModelRenderer.rotateAngleZ = 0.0F;
        ModelRenderer2.rotateAngleZ = 0.0F;
        ModelRenderer.rotateAngleY = (float) (Math.PI / 20);
        ModelRenderer2.rotateAngleY = (float) (-Math.PI / 20);
        if (mob.getPrimaryHand() == EnumHandSide.RIGHT) {
            ModelRenderer.rotateAngleX = -1.8849558F + MathHelper.cos(g * 0.09F) * 0.15F;
            ModelRenderer2.rotateAngleX = -0.0F + MathHelper.cos(g * 0.19F) * 0.5F;
            ModelRenderer.rotateAngleX += h * 2.2F - i * 0.4F;
            ModelRenderer2.rotateAngleX += h * 1.2F - i * 0.4F;
        } else {
            ModelRenderer.rotateAngleX = -0.0F + MathHelper.cos(g * 0.19F) * 0.5F;
            ModelRenderer2.rotateAngleX = -1.8849558F + MathHelper.cos(g * 0.09F) * 0.15F;
            ModelRenderer.rotateAngleX += h * 1.2F - i * 0.4F;
            ModelRenderer2.rotateAngleX += h * 2.2F - i * 0.4F;
        }

        bobArms(ModelRenderer, ModelRenderer2, g);
    }

    public static void bobArms(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, float f) {
        ModelRenderer.rotateAngleZ = ModelRenderer.rotateAngleZ + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        ModelRenderer2.rotateAngleZ = ModelRenderer2.rotateAngleZ - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        ModelRenderer.rotateAngleX = ModelRenderer.rotateAngleX + MathHelper.sin(f * 0.067F) * 0.05F;
        ModelRenderer2.rotateAngleX = ModelRenderer2.rotateAngleX - MathHelper.sin(f * 0.067F) * 0.05F;
    }

    public static void animateZombieArms(ModelRenderer ModelRenderer, ModelRenderer ModelRenderer2, boolean bl, float f, float g) {
        float h = MathHelper.sin(f * (float) Math.PI);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
        ModelRenderer2.rotateAngleZ = 0.0F;
        ModelRenderer.rotateAngleZ = 0.0F;
        ModelRenderer2.rotateAngleY = -(0.1F - h * 0.6F);
        ModelRenderer.rotateAngleY = 0.1F - h * 0.6F;
        float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
        ModelRenderer2.rotateAngleX = j;
        ModelRenderer.rotateAngleX = j;
        ModelRenderer2.rotateAngleX += h * 1.2F - i * 0.4F;
        ModelRenderer.rotateAngleX += h * 1.2F - i * 0.4F;
        bobArms(ModelRenderer2, ModelRenderer, g);
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
