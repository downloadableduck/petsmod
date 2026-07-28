package com.jeff.pets.client.rendering;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<D extends MobEntity, K extends EntityModel<D>> extends MobRenderer<D, K> {
    public PetRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void render(D entity, double f, double g, double h, float i, float j) {
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (entity.isPassenger()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, 0.35f, 0);
        }
        super.render(entity, f, g, h, i, j);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();;
    }
}
