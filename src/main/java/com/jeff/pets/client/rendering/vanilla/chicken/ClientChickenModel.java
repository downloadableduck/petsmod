package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.Math2;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.render.model.entity.ChickenModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel extends ChickenModel {

    public ClientChickenModel() {
        super();
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = (float) Math2.lerp(f, chicken.oFlap, chicken.flap);
        float h = (float) Math2.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(float f, float g, float h, float j, float k, float d) {
        // entity not available in b1.7.3 6-arg render
        super.render(f, g, h, j, k, d);
        GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            GlStateManager.scalef(2, 2, 2);
        } else {
            GlStateManager.scalef(1, 1, 1);
        }
        GlStateManager.popMatrix();
    }
}