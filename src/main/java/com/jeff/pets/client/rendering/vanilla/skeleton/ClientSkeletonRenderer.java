package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class ClientSkeletonRenderer extends PetRenderer<ClientSkeleton, SkeletonModel<ClientSkeleton>> {

    public ClientSkeletonRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new SkeletonModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void setupRotations(ClientSkeleton state, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f, float g, float h) {
        super.setupRotations(state, poseStack, f, g, h);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
