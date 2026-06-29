package com.jeff.pets.client.rendering.vanilla.bogged;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBogged;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBoggedRenderer extends PetRenderer<@NotNull ClientBogged, @NotNull ClientBoggedModel> {

    public static final ModelLayerLocation BOGGED_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientbogged"), "main");

    public ClientBoggedRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientBoggedModel(context.bakeLayer(ModelLayers.BOGGED)), 0.75f);
        this.addLayer(new SkeletonClothingLayer<>((RenderLayerParent) this, context.getModelSet(), ModelLayers.BOGGED_OUTER_LAYER, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged_overlay.png")));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientBogged livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged.png");
    }

    @Override
    public void setupRotations(ClientBogged state, @NotNull PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(state, poseStack, f, g, h, i);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
