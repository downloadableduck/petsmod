package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientSkeletonRenderer extends PetRenderer< ClientSkeleton,  SkeletonModel< ClientSkeleton>> {

    public static final ModelLayerLocation SKELETON_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientskeleton"), "main");

    public ClientSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void setupRotations(ClientSkeleton state,  PoseStack poseStack, float f, float g, float h) {
        super.setupRotations(state, poseStack, f, g, h);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
