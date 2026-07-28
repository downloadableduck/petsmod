package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.client.renderer.entity.model.PigModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigModel<ClientPig> {

    public ClientPigModel() {
        super();
    }

    @Override
    public void setupAnim(ClientPig state, float f, float g, float h, float i, float k, float j) {
        super.setupAnim(state, f, g, h, i, k, j);
    }

    @Override
    public void render(ClientPig clientPig, float i, float j, float f, float g, float h, float k) {
        super.render(clientPig, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();;
    }
}
