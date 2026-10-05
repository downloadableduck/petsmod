package com.jeff.pets.client.rendering;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

/**
 * Used as a shared piece of code across all of the mob renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer extends RenderLiving {
    private PetLayer petLayer;

    public PetRenderer(ModelBase model, float shadow) {
        super(model, shadow);
    }

    /**
     * 1.8 exposes the animated model as {@code getMainModel()}; 1.7.10 only exposes the
     * protected {@code mainModel} field, so pet layers rely on this accessor to re-render
     * the same geometry for their overlay pass.
     */
    public ModelBase getMainModel() {
        return this.mainModel;
    }

    /**
     * 1.7.10 renders blocks in-world through the {@code RenderBlocks} instance held by
     * {@code Render#field_147909_c}; there is no {@code BlockRendererDispatcher} and no
     * {@code Minecraft#getBlockRendererDispatcher}. Both fields are {@code protected}, so
     * pet layers reach them through here.
     */
    public RenderBlocks getRenderBlocks() {
        return this.field_147909_c;
    }

    /** Exposes the protected {@code Render#renderManager} to pet layers. */
    public RenderManager getRenderManager() {
        return this.renderManager;
    }

    /**
     * Attaches an overlay drawn straight after the main model. 1.7.10 has no
     * {@code addLayer}, so a pet can only carry a single overlay.
     */
    protected void setPetLayer(PetLayer layer) {
        this.petLayer = layer;
    }

    @Override
    protected void renderModel( final EntityLivingBase entity, float f, float g, float h, float i, float j, float k) {
        GL11.glPushMatrix();
        if (entity.ridingEntity != null) {
            GL11.glTranslatef(0, (float) entity.ridingEntity.height - (entity.ridingEntity.isSneaking() ? 0.225F : 0), 0);
        }
        super.renderModel(entity, f, g, h, i, j, k);
        GL11.glPopMatrix();
        if (this.petLayer != null) {
            this.petLayer.render(entity, f, g, h, i, j, k);
        }
    }

    public void bindTexturePublic(ResourceLocation location) {
        this.bindTexture(location);
    }
}

