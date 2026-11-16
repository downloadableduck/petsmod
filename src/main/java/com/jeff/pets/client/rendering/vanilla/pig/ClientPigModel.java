package com.jeff.pets.client.rendering.vanilla.pig;

import net.minecraft.client.render.model.entity.PigModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigModel {

    public ClientPigModel() {
        super();
    }

    @Override
    public void render(float i, float j, float f, float g, float h, float k) {
        super.render(i, j, f, g, h, k);
        com.jeff.pets.compat.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            com.jeff.pets.compat.GlStateManager.scalef(1, 1, 1);
        }
        com.jeff.pets.compat.GlStateManager.popMatrix();
    }
}
