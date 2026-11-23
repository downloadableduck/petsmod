//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.Vec3;

public final class EntityCompat {
    private EntityCompat() {
    }

    public static Vec3 eyePosition(Entity entity, float partialTick) {
        double x = entity.xo + (entity.x - entity.xo) * (double)partialTick;
        double y = entity.yo + (entity.y - entity.yo) * (double)partialTick;
        double z = entity.zo + (entity.z - entity.zo) * (double)partialTick;
        return Vec3.getPermanentVec3(x, y + (double)entity.getHeadHeight(), z);
    }

    public static Vec3 lookVector(Entity entity, float partialTick) {
        float xRot = entity.xRotO + (entity.xRot - entity.xRotO) * partialTick;
        float yRot = entity.yRotO + (entity.yRot - entity.yRotO) * partialTick;
        float cosPitch = MathHelper.cos(xRot * 0.017453292F);
        float sinPitch = MathHelper.sin(xRot * 0.017453292F);
        float cosYaw = MathHelper.cos(yRot * 0.017453292F);
        float sinYaw = MathHelper.sin(yRot * 0.017453292F);
        return Vec3.getPermanentVec3((double)(cosYaw * sinPitch), (double)cosPitch, (double)(sinYaw * sinPitch));
    }
}
