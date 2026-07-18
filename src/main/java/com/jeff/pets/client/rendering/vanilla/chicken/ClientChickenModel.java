package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel<T extends ClientChicken> extends ChickenEntityModel<T> {

    public ClientChickenModel() {
        super();
    }

    @Override
    public void setAngles(@NotNull T state, float f, float g, float h, float i, float j, float s) {
        h = getBob(state, f);
        super.setAngles(state, f, g, h, i, j, s);
    }

    protected float getBob(T chicken, float f) {
        float g = MathHelper.lerp(f, chicken.oFlap, chicken.flap);
        float h = MathHelper.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(T poseStack, float f, float g, float h, float j, float k, float d) {
        super.render(poseStack, f, g, h, j, k, d);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            GlStateManager.scalef(2, 2, 2);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
