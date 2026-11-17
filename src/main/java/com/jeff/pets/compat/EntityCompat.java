//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class EntityCompat {
    private EntityCompat() {
    }

    public static Vec3d eyePosition(Entity entity, float partialTick) {
        double x = entity.lastTickX + (entity.x - entity.lastTickX) * (double)partialTick;
        double y = entity.lastTickY + (entity.y - entity.lastTickY) * (double)partialTick;
        double z = entity.lastTickZ + (entity.z - entity.lastTickZ) * (double)partialTick;
        return Vec3d.create(x, y + (double)entity.getEyeHeight(), z);
    }

    public static Vec3d lookVector(Entity entity, float partialTick) {
        float pitch = entity.prevPitch + (entity.pitch - entity.prevPitch) * partialTick;
        float yaw = entity.prevYaw + (entity.yaw - entity.prevYaw) * partialTick;
        float cosPitch = MathHelper.cos(pitch * 0.017453292F);
        float sinPitch = MathHelper.sin(pitch * 0.017453292F);
        float cosYaw = MathHelper.cos(yaw * 0.017453292F);
        float sinYaw = MathHelper.sin(yaw * 0.017453292F);
        return Vec3d.create((double)(cosYaw * sinPitch), (double)cosPitch, (double)(sinYaw * sinPitch));
    }
}
