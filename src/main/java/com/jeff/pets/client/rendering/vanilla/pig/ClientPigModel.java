package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.client.render.model.entity.PigModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigModel<ClientPig> {

    public ClientPigModel() {
        super();
    }

    @Override
    public void setup(ClientPig state, float f, float g, float h, float i, float k, float s) {
        super.setup(state, f, g, h, i, k, s);
    }

    @Override
    public void render(ClientPig pig, float i, float j, float f, float g, float h, float k) {
        super.render(pig, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
