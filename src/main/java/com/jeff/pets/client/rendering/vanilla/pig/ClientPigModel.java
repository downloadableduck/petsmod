package com.jeff.pets.client.rendering.vanilla.pig;

import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.Entity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigEntityModel {

    public ClientPigModel() {
        super();
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float k, float s, Entity entity) {
        super.setAngles(f, g, h, i, k, s, entity);
    }

    @Override
    public void render(Entity pig, float i, float j, float f, float g, float h, float k) {
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