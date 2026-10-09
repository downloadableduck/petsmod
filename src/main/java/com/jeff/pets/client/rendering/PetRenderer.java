package com.jeff.pets.client.rendering;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer extends MobEntityRenderer {
    public PetRenderer(EntityRenderDispatcher context, EntityModel model, float shadow) {
        super(model, shadow);
        this.setRenderDispatcher(context);
    }

    public EntityModel getMainModel() {
        return this.model;
    }

    @Override
    public void render(Entity entity, double x, double y, double z, float yaw, float pitch) {
        GL11.glPushMatrix();
        if (entity.vehicle != null) {
            GL11.glTranslatef(0.0F, 0.35F, 0.0F);
        }
        super.render((LivingEntity) entity, x, y, z, yaw, pitch);
        GL11.glPopMatrix();
    }
}
