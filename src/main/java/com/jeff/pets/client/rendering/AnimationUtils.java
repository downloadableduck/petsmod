package com.jeff.pets.client.rendering;

import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class AnimationUtils {
    public static void animateCrossbowHold(ModelPart part, ModelPart part2, ModelPart part3, boolean bl) {
        ModelPart part4 = bl ? part : part2;
        ModelPart part5 = bl ? part2 : part;
        part4.pivotY = (bl ? -0.3F : 0.3F) + part3.pivotY;
        part5.pivotY = (bl ? 0.6F : -0.6F) + part3.pivotY;
        part4.pivotX = (float) (-Math.PI / 2) + part3.pivotX + 0.1F;
        part5.pivotX = -1.5F + part3.pivotX;
    }

    public static void animateCrossbowCharge(ModelPart part, ModelPart part2, LivingEntity livingEntity, boolean bl) {
        ModelPart part3 = bl ? part : part2;
        ModelPart part4 = bl ? part2 : part;
        part3.pivotY = bl ? -0.8F : 0.8F;
        part3.pivotX = -0.97079635F;
        part4.pivotX = part3.pivotX;
        float f = (float) livingEntity.method_13064().getMaxUseTime();
        float g = MathHelper.clamp((float) livingEntity.method_13065(), 0.0F, f);
        float h = g / f;
        part4.pivotY = MathHelper.method_21515(h, 0.4F, 0.85F) * (bl ? 1 : -1);
        part4.pivotX = MathHelper.method_21515(h, part4.pivotX, (float) (-Math.PI / 2));
    }

    public static void bobArms(ModelPart part, ModelPart part2, float f) {
        part.pivotZ = part.pivotZ + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        part2.pivotZ = part2.pivotZ - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        part.pivotX = part.pivotX + MathHelper.sin(f * 0.067F) * 0.05F;
        part2.pivotX = part2.pivotX - MathHelper.sin(f * 0.067F) * 0.05F;
    }

    public static void animateZombieArms(ModelPart part, ModelPart part2, boolean bl, float f, float g) {
        float h = MathHelper.sin(f * (float) Math.PI);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
        part2.pivotZ = 0.0F;
        part.pivotZ = 0.0F;
        part2.pivotY = -(0.1F - h * 0.6F);
        part.pivotY = 0.1F - h * 0.6F;
        float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
        part2.pivotX = j;
        part.pivotX = j;
        part2.pivotX += h * 1.2F - i * 0.4F;
        part.pivotX += h * 1.2F - i * 0.4F;
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