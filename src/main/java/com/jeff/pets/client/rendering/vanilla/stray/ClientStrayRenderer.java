package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientStrayRenderer extends PetRenderer<@NotNull ClientStray, @NotNull SkeletonModel<@NotNull ClientStray>> {

    public static final ModelLayerLocation STRAY_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientstray"), "main");

    public ClientStrayRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.STRAY)), 0.75f);
        this.addLayer(new SkeletonClothingLayer<>((RenderLayerParent) this, context.getModelSet(), ModelLayers.STRAY_OUTER_LAYER, new ResourceLocation("minecraft", "textures/entity/skeleton/stray_overlay.png")));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientStray livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/stray.png");
    }

    @Override
    public void setupRotations(ClientStray state, @NotNull PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(state, poseStack, f, g, h, i);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
