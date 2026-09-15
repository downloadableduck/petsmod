package com.jeff.pets.client.rendering;

import net.minecraft.entity.Entity;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

/**
 * Used as a shared piece of code across all of the mob renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer extends RenderLiving {
    public PetRenderer(RenderManager context, ModelBase model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    protected void renderModel( final EntityLivingBase entity, float f, float g, float h, float i, float j, float k) {
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (entity.field_70154_o != null) {
            net.minecraft.client.renderer.GlStateManager.translatef(0, -0.35f, 0);
        }
        super.renderModel(entity, f, g, h, i, j, k);
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}

