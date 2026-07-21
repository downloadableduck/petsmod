package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.render.model.entity.CowModel;
import net.minecraft.entity.Entity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel<T extends Entity> extends CowModel<T> {

    public ClientCowModel() {
        super();
    }

    @Override
    public void render(T poseStack, float f, float g, float h, float j, float k, float d) {
        super.render(poseStack, f, g, h, j, k, d);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(2, 2, 2);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        //this.head.rotate(poseStack);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
