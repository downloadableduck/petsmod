//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.QuadrupedModel;
import com.jeff.pets.compat.GlStateManager;

public class ClientPigModel extends QuadrupedModel {
    public ClientPigModel() {
        super(6, 0.0F);
    }

    public ClientPigModel(float reduction) {
        super(6, reduction);
    }

    public void render(float i, float j, float f, float g, float h, float k) {
        super.render(i, j, f, g, h, k);
        GlStateManager.pushMatrix();
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(1.5F, 1.5F, 1.5F);
        } else {
            GlStateManager.scalef(1.0F, 1.0F, 1.0F);
        }

        GlStateManager.popMatrix();
    }
}
