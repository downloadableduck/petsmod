package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer {

    public ClientWitherSkeletonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientWitherSkeleton livingEntityRenderState = (ClientWitherSkeleton) __e;
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}