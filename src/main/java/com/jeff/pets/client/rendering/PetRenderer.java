package com.jeff.pets.client.rendering;

import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.mob.MobEntity;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<D extends MobEntity, K extends EntityModel<D>> extends MobEntityRenderer<D, K> {
    public PetRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void render(D entity, float f, float g, float h, float i, float k, float j) {
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (entity.hasVehicle()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.35f, 0);
        }
        super.render(entity, f, g, h, i, k, j);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
