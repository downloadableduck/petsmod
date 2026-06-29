package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<@NotNull ClientWitherSkeleton, @NotNull SkeletonModel<@NotNull ClientWitherSkeleton>> {

    public static final ModelLayerLocation WITHER_SKELETON_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientwitherskeleton"), "main");

    public ClientWitherSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.WITHER_SKELETON)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWitherSkeleton livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/skeleton/wither_skeleton.png");
    }
}
