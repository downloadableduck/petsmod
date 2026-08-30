package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.client.render.model.entity.PigModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigModel {

    public ClientPigModel() {
        super();
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float k, float s, net.minecraft.entity.Entity entity) {
        super.setupAnimation(f, g, h, i, k, s, entity);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float i, float j, float f, float g, float h, float k) {
        super.render(entity, i, j, f, g, h, k);
        net.minecraft.client.render.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            net.minecraft.client.render.platform.GlStateManager.scalef(1, 1, 1);
        }
        net.minecraft.client.render.platform.GlStateManager.popMatrix();
    }
}
