package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.model.ModelChicken;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel extends ModelChicken {

    public ClientChickenModel() {
        super();
    }

    public void setRotationAngles(ClientChicken state, float f, float g, float h, float i, float j, float s) {
        h = getBob(state, f);
        super.setRotationAngles(f, g, h, i, j, s, state);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = (float) net.minecraft.util.math.MathHelper.clampedLerp(f, chicken.oFlap, chicken.flap);
        float h = (float) net.minecraft.util.math.MathHelper.clampedLerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (net.minecraft.util.math.MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(net.minecraft.entity.Entity t, float i, float j, float f, float g, float h, float k) {
        super.render(t, i, j, f, g, h, k);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(2, 2, 2);
        } else {
            net.minecraft.client.renderer.GlStateManager.scalef(1, 1, 1);
        }
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
