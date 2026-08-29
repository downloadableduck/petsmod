package com.jeff.pets.client.rendering;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.entity.EntityLiving;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<D extends AbstractPet, K extends ModelBase> extends RenderLiving<D> {
    public PetRenderer(RenderManager context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void renderModel(D entity, float f, float g, float h, float i, float j, float k) {
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (entity.isPassenger()) {
            net.minecraft.client.renderer.GlStateManager.translatef(0, -0.35f, 0);
        } if (entity.getOwner() != null && entity.isRidingOrBeingRiddenBy(entity.getOwner())) {
            GlStateManager.translatef(0, 0.35f, 0);
        }
        super.renderModel(entity, f, g, h, i, j, k);
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
