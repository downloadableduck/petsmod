package com.jeff.pets.client.rendering;

import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.model.Model;
import net.minecraft.entity.living.mob.MobEntity;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */

public abstract class PetRenderer<D extends MobEntity, K extends Model<D>> extends MobRenderer<D, K> {
    public PetRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void renderModel(D entity, float f, float g, float h, float i, float k, float j) {
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (entity.isRiding()) {
            com.mojang.blaze3d.platform.GlStateManager.translate(0, -0.35f, 0);
        }
        super.renderModel(entity, f, g, h, i, k, j);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
