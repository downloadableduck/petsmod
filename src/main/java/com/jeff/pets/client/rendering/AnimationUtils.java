package com.jeff.pets.client.rendering;

import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.math.MathHelper;

public class AnimationUtils {
    public static void animateCrossbowHold(Cuboid Cuboid, Cuboid Cuboid2, Cuboid Cuboid3, boolean bl) {
        Cuboid Cuboid4 = bl ? Cuboid : Cuboid2;
        Cuboid Cuboid5 = bl ? Cuboid2 : Cuboid;
        Cuboid4.yaw = (bl ? -0.3F : 0.3F) + Cuboid3.yaw;
        Cuboid5.yaw = (bl ? 0.6F : -0.6F) + Cuboid3.yaw;
        Cuboid4.pitch = (float) (-Math.PI / 2) + Cuboid3.pitch + 0.1F;
        Cuboid5.pitch = -1.5F + Cuboid3.pitch;
    }

    public static void animateCrossbowCharge(Cuboid Cuboid, Cuboid Cuboid2, LivingEntity livingEntity, boolean bl) {
        Cuboid Cuboid3 = bl ? Cuboid : Cuboid2;
        Cuboid Cuboid4 = bl ? Cuboid2 : Cuboid;
        Cuboid3.yaw = bl ? -0.8F : 0.8F;
        Cuboid3.pitch = -0.97079635F;
        Cuboid4.pitch = Cuboid3.pitch;
        float f = CrossbowItem.getPullTime(livingEntity.getActiveItem());
        float g = MathHelper.clamp((float) livingEntity.getItemUseTime(), 0.0F, f);
        float h = g / f;
        Cuboid4.yaw = MathHelper.lerp(h, 0.4F, 0.85F) * (bl ? 1 : -1);
        Cuboid4.pitch = MathHelper.lerp(h, Cuboid4.pitch, (float) (-Math.PI / 2));
    }

    public static void bobArms(Cuboid Cuboid, Cuboid Cuboid2, float f) {
        Cuboid.roll = Cuboid.roll + (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        Cuboid2.roll = Cuboid2.roll - (MathHelper.cos(f * 0.09F) * 0.05F + 0.05F);
        Cuboid.pitch = Cuboid.pitch + MathHelper.sin(f * 0.067F) * 0.05F;
        Cuboid2.pitch = Cuboid2.pitch - MathHelper.sin(f * 0.067F) * 0.05F;
    }

    public static void animateZombieArms(Cuboid Cuboid, Cuboid Cuboid2, boolean bl, float f, float g) {
        float h = MathHelper.sin(f * (float) Math.PI);
        float i = MathHelper.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
        Cuboid2.roll = 0.0F;
        Cuboid.roll = 0.0F;
        Cuboid2.yaw = -(0.1F - h * 0.6F);
        Cuboid.yaw = 0.1F - h * 0.6F;
        float j = (float) -Math.PI / (bl ? 1.5F : 2.25F);
        Cuboid2.pitch = j;
        Cuboid.pitch = j;
        Cuboid2.pitch += h * 1.2F - i * 0.4F;
        Cuboid.pitch += h * 1.2F - i * 0.4F;
        bobArms(Cuboid2, Cuboid, g);
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
