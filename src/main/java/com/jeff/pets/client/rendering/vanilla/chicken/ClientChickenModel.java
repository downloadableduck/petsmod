//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.chicken;

import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.util.math.MathHelper;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.Math2;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;

public class ClientChickenModel extends ChickenEntityModel {
    public ClientChickenModel() {
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = Math2.lerp(f, chicken.oFlap, chicken.flap);
        float h = Math2.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    public void render(float f, float g, float h, float j, float k, float d) {
        super.render(f, g, h, j, k, d);
        GlStateManager.pushMatrix();
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(2.0F, 2.0F, 2.0F);
        } else {
            GlStateManager.scalef(1.0F, 1.0F, 1.0F);
        }

        GlStateManager.popMatrix();
    }
}
