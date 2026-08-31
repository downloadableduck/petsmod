package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ModelSkeleton;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.util.ResourceLocation;

public class ClientWitherSkeletonRenderer extends PetRenderer<ClientWitherSkeleton, ModelSkeleton> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientWitherSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
