//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering;

import net.minecraft.entity.LivingEntity;

public interface PetRenderLayer {
    void render(LivingEntity var1, float var2, float var3, float var4, float var5, float var6, float var7);

    boolean colorsWhenDamaged();
}
