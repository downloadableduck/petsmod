package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.render.model.entity.CowModel;
import net.minecraft.entity.Entity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel extends CowModel {

    public ClientCowModel() {
        super();
    }

    @Override
    public void render(Entity entity, float f, float g, float h, float j, float k, float d) {
        super.render(entity, f, g, h, j, k, d);
        com.jeff.pets.compat.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(2, 2, 2);
        } else {
            com.jeff.pets.compat.GlStateManager.scalef(1, 1, 1);
        }
        //this.head.rotate(poseStack);
        com.jeff.pets.compat.GlStateManager.popMatrix();
    }
}
