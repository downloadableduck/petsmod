package com.jeff.pets.client.rendering;

import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class AnimationUtils {
    public static void animateCrossbowHold(ModelPart ModelPart, ModelPart ModelPart2, ModelPart ModelPart3, boolean bl) {
        ModelPart ModelPart4 = bl ? ModelPart : ModelPart2;
        ModelPart ModelPart5 = bl ? ModelPart2 : ModelPart;
        ModelPart4.rotationY = (bl ? -0.3F : 0.3F) + ModelPart3.rotationY;
        ModelPart5.rotationY = (bl ? 0.6F : -0.6F) + ModelPart3.rotationY;
        ModelPart4.rotationX = (float) (-Math.PI / 2) + ModelPart3.rotationX + 0.1F;
        ModelPart5.rotationX = -1.5F + ModelPart3.rotationX;
    }


    public static void bobArms(ModelPart ModelPart, ModelPart ModelPart2, float f) {
        ModelPart.rotationZ = ModelPart.rotationZ + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        ModelPart2.rotationZ = ModelPart2.rotationZ - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        ModelPart.rotationX = ModelPart.rotationX + MathHelper.sin(f * 0.067F) * 0.05F;
        ModelPart2.rotationX = ModelPart2.rotationX - MathHelper.sin(f * 0.067F) * 0.05F;
    }

    public static void animateZombieArms(ModelPart ModelPart, ModelPart ModelPart2, boolean bl, float f, float g) {
        float h = MathHelper.sin(f * (float) Math.PI);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
        ModelPart2.rotationZ = 0.0F;
        ModelPart.rotationZ = 0.0F;
        ModelPart2.rotationY = -(0.1F - h * 0.6F);
        ModelPart.rotationY = 0.1F - h * 0.6F;
        float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
        ModelPart2.rotationX = j;
        ModelPart.rotationX = j;
        ModelPart2.rotationX += h * 1.2F - i * 0.4F;
        ModelPart.rotationX += h * 1.2F - i * 0.4F;
        bobArms(ModelPart2, ModelPart, g);
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