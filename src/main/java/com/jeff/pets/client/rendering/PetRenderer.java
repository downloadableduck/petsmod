package com.jeff.pets.client.rendering;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.render.entity.MobRenderer;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.entity.living.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */

public abstract class PetRenderer<D extends AbstractPet> extends MobRenderer {

    private final List<PetRenderLayer> petLayers = new ArrayList<>();

    /**
     * @param context unused: 1.7.10's {@code MobRenderer} is constructed from just the model
     *                 and shadow size, unlike 1.8's which also takes an {@code EntityRenderDispatcher}.
     *                 It is kept so the renderer factory interface stays uniform across all pets.
     */
    public PetRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.minecraft.client.render.model.Model model, float shadow) {
        super(model, shadow);
        this.dispatcher = context;
    }

    /**
     * Registers an extra render pass that runs after the mob's model has been drawn.
     *
     * <p>1.7.10 has no equivalent of 1.8's {@code EntityRenderLayer} system, so layers are
     * tracked by this renderer and invoked from {@link #renderModel} instead.
     */
    public void addLayer(PetRenderLayer layer) {
        this.petLayers.add(layer);
    }

    /**
     * 1.8's {@code LivingEntityRenderer.getModel()}, which 1.7.10 lacks.
     */
    public net.minecraft.client.render.model.Model getModel() {
        return this.model;
    }

    /**
     * 1.8's {@code Minecraft.getBlockRenderDispatcher()}, which 1.7.10 lacks.
     */
    public net.minecraft.client.render.block.BlockRenderer getBlockRenderer() {
        return this.blockRenderer;
    }

    /**
     * 1.7.10's {@code LivingEntityRenderer} only satisfies {@code EntityRenderer}'s abstract
     * {@code render} through a compiler-generated bridge, so every concrete renderer has to
     * declare it. Delegating here covers all of the mod's pet renderers at once.
     */
    @Override
    public void render(net.minecraft.entity.Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        super.render((LivingEntity) entity, x, y, z, yaw, partialTicks);
    }

    @Override
    protected void renderModel(LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        GlStateManager.pushMatrix();
        if (entity.isRiding()) {
            GlStateManager.translatef(0, -0.35f, 0);
        }
        super.renderModel(entity, f, g, h, i, j, k);
        GlStateManager.popMatrix();

        for (PetRenderLayer layer : this.petLayers) {
            layer.render(entity, f, g, h, i, j, k);
        }
    }
}
