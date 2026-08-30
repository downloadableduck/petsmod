package com.jeff.pets.client.rendering;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.entity.living.mob.MobEntity;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */

public abstract class PetRenderer<D extends AbstractPet> extends MobRenderer<D> {
    public PetRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.minecraft.client.render.model.Model model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void renderModel(D entity, float f, float g, float h, float i, float j, float k) {
        GlStateManager.pushMatrix();
        if (entity.isPassenger()) {
            GlStateManager.translatef(0, -0.35f, 0);
        }
        super.renderModel(entity, f, g, h, i, j, k);
        GlStateManager.popMatrix();
    }
}
