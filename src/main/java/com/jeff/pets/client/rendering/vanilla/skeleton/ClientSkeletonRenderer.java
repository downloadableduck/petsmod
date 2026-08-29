package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.util.ResourceLocation;

public class ClientSkeletonRenderer extends PetRenderer<ClientSkeleton, ModelSkeleton> {

    public ClientSkeletonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void applyRotations(ClientSkeleton state, float f, float g, float h) {
        super.applyRotations(state, f, g, h);
        if (state.isPassenger()) {
            net.minecraft.client.renderer.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
