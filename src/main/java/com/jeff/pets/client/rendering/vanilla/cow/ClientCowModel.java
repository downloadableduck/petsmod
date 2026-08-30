package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.render.model.entity.CowModel;
import net.minecraft.entity.Entity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel extends CowModel {

    public ClientCowModel() {
        super();
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float j, float k, float d) {
        super.render(entity, f, g, h, j, k, d);
        net.minecraft.client.render.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(2, 2, 2);
        } else {
            net.minecraft.client.render.platform.GlStateManager.scalef(1, 1, 1);
        }
        //this.head.rotate(poseStack);
        net.minecraft.client.render.platform.GlStateManager.popMatrix();
    }
}
