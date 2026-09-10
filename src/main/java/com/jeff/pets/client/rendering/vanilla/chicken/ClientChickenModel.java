package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel extends ChickenEntityModel {

    public ClientChickenModel() {
        super();
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float s, Entity entity) {
        ClientChicken chicken = (ClientChicken) entity;
        h = getBob(chicken, f);
        super.setAngles(f, g, h, i, j, s, entity);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = (float) MathHelper.clampedLerp(chicken.oFlap, chicken.flap, f);
        float h = (float) MathHelper.clampedLerp(chicken.oFlapSpeed, chicken.flapSpeed, f);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(Entity entity, float f, float g, float h, float j, float k, float d) {
        super.render(entity, f, g, h, j, k, d);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            GlStateManager.scale(2, 2, 2);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}