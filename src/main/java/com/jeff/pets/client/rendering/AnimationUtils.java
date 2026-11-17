//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering;

import net.minecraft.util.math.MathHelper;
import com.jeff.pets.compat.render.model.ModelPart;

public class AnimationUtils {
    public AnimationUtils() {
    }

    public static void animateCrossbowHold(ModelPart ModelPart, ModelPart ModelPart2, ModelPart ModelPart3, boolean bl) {
        ModelPart ModelPart4 = bl ? ModelPart : ModelPart2;
        ModelPart ModelPart5 = bl ? ModelPart2 : ModelPart;
        ModelPart4.rotationY = (bl ? -0.3F : 0.3F) + ModelPart3.rotationY;
        ModelPart5.rotationY = (bl ? 0.6F : -0.6F) + ModelPart3.rotationY;
        ModelPart4.rotationX = -1.5707964F + ModelPart3.rotationX + 0.1F;
        ModelPart5.rotationX = -1.5F + ModelPart3.rotationX;
    }

    public static void bobArms(ModelPart ModelPart, ModelPart ModelPart2, float f) {
        ModelPart.rotationZ += MathHelper.cos(f * 0.09F) * 0.05F + 0.05F;
        ModelPart2.rotationZ -= MathHelper.cos(f * 0.09F) * 0.05F + 0.05F;
        ModelPart.rotationX += MathHelper.sin(f * 0.067F) * 0.05F;
        ModelPart2.rotationX -= MathHelper.sin(f * 0.067F) * 0.05F;
    }

    public static void animateZombieArms(ModelPart ModelPart, ModelPart ModelPart2, boolean bl, float f, float g) {
        float h = MathHelper.sin(f * 3.1415927F);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * 3.1415927F);
        ModelPart2.rotationZ = 0.0F;
        ModelPart.rotationZ = 0.0F;
        ModelPart2.rotationY = -(0.1F - h * 0.6F);
        ModelPart.rotationY = 0.1F - h * 0.6F;
        float j = -3.1415927F / (bl ? 1.5F : 2.25F);
        ModelPart2.rotationX = j;
        ModelPart.rotationX = j;
        ModelPart2.rotationX += h * 1.2F - i * 0.4F;
        ModelPart.rotationX += h * 1.2F - i * 0.4F;
        bobArms(ModelPart2, ModelPart, g);
    }

    public static float rotlerpRad(float f, float g, float h) {
        float i = (g - f) % 6.2831855F;
        if (i < -3.1415927F) {
            i += 6.2831855F;
        }

        if (i >= 3.1415927F) {
            i -= 6.2831855F;
        }

        return f + h * i;
    }
}
