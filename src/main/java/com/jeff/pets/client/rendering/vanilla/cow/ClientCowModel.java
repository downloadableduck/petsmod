package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.entity.Entity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel<T extends Entity> extends CowModel<T> {

    public ClientCowModel() {
        super();
    }

    @Override
    public void render(T t, float i, float j, float f, float g, float h, float k) {
        super.render(t, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(2, 2, 2);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();;
    }
}
