package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ModelSkeleton;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.util.ResourceLocation;

public class ClientWitherSkeletonRenderer extends PetRenderer {

    public ClientWitherSkeletonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}

