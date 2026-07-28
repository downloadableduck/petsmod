package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.renderer.entity.model.ChickenModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel<T extends ClientChicken> extends ChickenModel<T> {

    public ClientChickenModel() {
        super();
    }

    @Override
    public void setupAnim(T state, float f, float g, float h, float i, float j, float s) {
        h = getBob(state, f);
        super.setupAnim(state, f, g, h, i, j, s);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = net.minecraft.util.math.MathHelper.lerp(f, chicken.oFlap, chicken.flap);
        float h = net.minecraft.util.math.MathHelper.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (net.minecraft.util.math.MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(T t, float i, float j, float f, float g, float h, float k) {
        super.render(t, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(2, 2, 2);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();;
    }
}
