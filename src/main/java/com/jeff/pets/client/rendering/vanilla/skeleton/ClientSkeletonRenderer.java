package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientSkeletonRenderer extends PetRenderer<@NotNull ClientSkeleton, @NotNull SkeletonModel<@NotNull ClientSkeleton>> {

    public static final ModelLayerLocation SKELETON_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientskeleton"), "main");

    public ClientSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSkeleton livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void setupRotations(ClientSkeleton state, @NotNull PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(state, poseStack, f, g, h, i);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
