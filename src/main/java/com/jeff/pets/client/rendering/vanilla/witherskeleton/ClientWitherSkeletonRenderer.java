package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientWitherSkeletonRenderer extends PetRenderer< ClientWitherSkeleton,  SkeletonModel< ClientWitherSkeleton>> {

    public static final ModelLayerLocation WITHER_SKELETON_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientwitherskeleton"), "main");

    public ClientWitherSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.WITHER_SKELETON)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientWitherSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
