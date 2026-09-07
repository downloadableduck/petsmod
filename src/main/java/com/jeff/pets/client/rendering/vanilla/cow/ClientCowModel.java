package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.model.ModelCow;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel extends ModelCow {

    public ClientCowModel() {
        super();
    }

    @Override
    public void render(net.minecraft.entity.Entity t, float i, float j, float f, float g, float h, float k) {
        super.render(t, i, j, f, g, h, k);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(2, 2, 2);
        } else {
            net.minecraft.client.renderer.GlStateManager.scalef(1, 1, 1);
        }
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
