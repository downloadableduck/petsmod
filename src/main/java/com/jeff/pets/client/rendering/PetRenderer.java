package com.jeff.pets.client.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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
    public boolean isBodyVisible(D entity) {
        return true;
    }

    @Override
    public void render(D entity, float f, float g, MatrixStack poseStack, IRenderTypeBuffer source, int i) {
        poseStack.pushPose();
        if (entity.isPassenger()) {
            poseStack.translate(0, 0.35, 0);
        }
        super.render(entity, f, g, poseStack, source, i);
        poseStack.popPose();
    }
}
