package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class ClientWitherSkeletonRenderer extends PetRenderer<ClientWitherSkeleton, SkeletonModel<ClientWitherSkeleton>> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new SkeletonModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientWitherSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
