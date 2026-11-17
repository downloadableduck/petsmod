//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.render.entity.model.CowEntityModel;
import com.jeff.pets.client.Central;
import com.jeff.pets.compat.GlStateManager;

public class ClientCowModel extends CowEntityModel {
    public ClientCowModel() {
    }

    public void render(float f, float g, float h, float j, float k, float d) {
        super.render(f, g, h, j, k, d);
        GlStateManager.pushMatrix();
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(2.0F, 2.0F, 2.0F);
        } else {
            GlStateManager.scalef(1.0F, 1.0F, 1.0F);
        }

        GlStateManager.popMatrix();
    }
}
