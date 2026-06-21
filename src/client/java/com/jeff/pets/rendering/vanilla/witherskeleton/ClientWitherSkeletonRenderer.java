package com.jeff.pets.rendering.vanilla.witherskeleton;

import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<@NotNull ClientWitherSkeleton, @NotNull SkeletonRenderState, @NotNull SkeletonModel<@NotNull SkeletonRenderState>> {

    public static final ModelLayerLocation WITHER_SKELETON_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientwitherskeleton"), "main");

    public ClientWitherSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.WITHER_SKELETON)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SkeletonRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/skeleton/wither_skeleton.png");
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }
}
