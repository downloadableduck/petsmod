package com.jeff.pets.client.rendering;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class AnimationUtils {
    public static void animateCrossbowHold(ModelRenderer part, ModelRenderer part2, ModelRenderer part3, boolean bl) {
        ModelRenderer part4 = bl ? part : part2;
        ModelRenderer part5 = bl ? part2 : part;
        part4.rotationPointY = (bl ? -0.3F : 0.3F) + part3.rotationPointY;
        part5.rotationPointY = (bl ? 0.6F : -0.6F) + part3.rotationPointY;
        part4.rotationPointX = (float) (-Math.PI / 2) + part3.rotationPointX + 0.1F;
        part5.rotationPointX = -1.5F + part3.rotationPointX;
    }

    public static void animateCrossbowCharge(ModelRenderer part, ModelRenderer part2, EntityLiving livingEntity, boolean bl) {
        ModelRenderer part3 = bl ? part : part2;
        ModelRenderer part4 = bl ? part2 : part;
        part3.rotationPointY = bl ? -0.8F : 0.8F;
        part3.rotationPointX = -0.97079635F;
        part4.rotationPointX = part3.rotationPointX;
        float f = 72000.0F;
        float g = 0.0F;
        float h = g / f;
        part4.rotationPointY = (float) MathHelper.clamp_float(h, 0.4F, 0.85F) * (bl ? 1 : -1);
        part4.rotationPointX = (float) MathHelper.clamp_float(h, part4.rotationPointX, (float) (-Math.PI / 2));
    }

    public static void bobArms(ModelRenderer part, ModelRenderer part2, float f) {
        part.rotationPointZ = part.rotationPointZ + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        part2.rotationPointZ = part2.rotationPointZ - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        part.rotationPointX = part.rotationPointX + MathHelper.sin(f * 0.067F) * 0.05F;
        part2.rotationPointX = part2.rotationPointX - MathHelper.sin(f * 0.067F) * 0.05F;
    }

    public static void animateZombieArms(ModelRenderer part, ModelRenderer part2, boolean bl, float f, float g) {
        float h = MathHelper.sin(f * (float) Math.PI);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
        part2.rotationPointZ = 0.0F;
        part.rotationPointZ = 0.0F;
        part2.rotationPointY = -(0.1F - h * 0.6F);
        part.rotationPointY = 0.1F - h * 0.6F;
        float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
        part2.rotationPointX = j;
        part.rotationPointX = j;
        part2.rotationPointX += h * 1.2F - i * 0.4F;
        part.rotationPointX += h * 1.2F - i * 0.4F;
        bobArms(part2, part, g);
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