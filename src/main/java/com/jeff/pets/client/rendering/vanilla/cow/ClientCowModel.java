package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.class_4184;
import net.minecraft.client.render.model.ModelPart;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel extends class_4184 {

    public ClientCowModel() {
        super();
    }

    @Override
    public void render(net.minecraft.entity.Entity t, float i, float j, float f, float g, float h, float k) {
        super.render(t, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(2, 2, 2);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }

    public ModelPart getHead() {
        return this.head;
    }
}
