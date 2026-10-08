package com.jeff.pets.client.rendering;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer extends RenderLiving {
    public PetRenderer(RenderManager context, ModelBase model, float shadow) {
        super(model, shadow);
        this.setRenderManager(context);
    }

    public ModelBase getMainModel() {
        return this.mainModel;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
        GL11.glPushMatrix();
        if (entity.ridingEntity != null) {
            GL11.glTranslatef(0.0F, 0.35F, 0.0F);
        }
        super.doRender((EntityLivingBase) entity, x, y, z, yaw, pitch);
        GL11.glPopMatrix();
    }
}
