package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<@NotNull ClientWitherSkeleton, @NotNull SkeletonModel<@NotNull ClientWitherSkeleton>> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new SkeletonModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWitherSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
