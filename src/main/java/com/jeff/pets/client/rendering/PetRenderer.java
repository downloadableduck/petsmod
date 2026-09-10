package com.jeff.pets.client.rendering;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.mob.MobEntity;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<T extends MobEntity> extends MobEntityRenderer<T> {
    public PetRenderer(EntityRenderDispatcher context, EntityModel model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void render(T entity, double x, double y, double z, float yaw, float pitch) {
        GlStateManager.pushMatrix();
        if (entity.getVehicle() != null) {
            GlStateManager.translate(0.0F, -0.35F, 0.0F);
        }
        super.render(entity, x, y, z, yaw, pitch);
        GlStateManager.popMatrix();
    }
}