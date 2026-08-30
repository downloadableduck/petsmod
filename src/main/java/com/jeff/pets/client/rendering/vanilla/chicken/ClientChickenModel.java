package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.model.entity.ChickenModel;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel extends ChickenModel {

    public ClientChickenModel() {
        super();
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        ClientChicken state = (ClientChicken) entity;
        h = getBob(state, f);
        super.setupAnimation(f, g, h, i, j, s, entity);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = (float) Math2.lerp(f, chicken.oFlap, chicken.flap);
        float h = (float) Math2.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float j, float k, float d) {
        super.render(entity, f, g, h, j, k, d);
        net.minecraft.client.render.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            GlStateManager.scalef(2, 2, 2);
        } else {
            net.minecraft.client.render.platform.GlStateManager.scalef(1, 1, 1);
        }
        net.minecraft.client.render.platform.GlStateManager.popMatrix();
    }
}