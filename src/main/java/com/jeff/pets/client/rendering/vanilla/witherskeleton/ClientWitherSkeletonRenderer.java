package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ModelSkeleton;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientWitherSkeletonRenderer extends PetRenderer {

    public ClientWitherSkeletonRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelSkeleton(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}

