package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.client.renderer.entity.model.ModelPig;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends ModelPig {

    public ClientPigModel() {
        super();
    }

    public void setRotationAngles(ClientPig state, float f, float g, float h, float i, float k, float j) {
        super.setRotationAngles(f, g, h, i, k, j, state);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float i, float j, float f, float g, float h, float k) {
        super.render(entity, i, j, f, g, h, k);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            net.minecraft.client.renderer.GlStateManager.scale(1, 1, 1);
        }
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
