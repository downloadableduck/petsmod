package com.jeff.pets.rendering.vanilla.bogged;

import com.jeff.pets.mob.vanilla.hostile.ClientBogged;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.BoggedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.BoggedRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBoggedRenderer extends PetRenderer<@NotNull ClientBogged, @NotNull BoggedRenderState, @NotNull BoggedModel> {

    public static final ModelLayerLocation BOGGED_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientbogged"), "main");

    public ClientBoggedRenderer(EntityRendererProvider.Context context) {
        super(context, new BoggedModel(context.bakeLayer(ModelLayers.BOGGED)), 0.75f);
        this.addLayer(new SkeletonClothingLayer<>(this, EntityModelSet.vanilla(), ModelLayers.BOGGED_OUTER_LAYER, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged_overlay.png")));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BoggedRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged.png");
    }

    @Override
    public BoggedRenderState createRenderState() {
        return new BoggedRenderState();
    }

    @Override
    public void setupRotations(BoggedRenderState state, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(state, poseStack, f, g);
        if (state.isPassenger) {
            poseStack.translate(0, -0.5, 0);
        }
    }

    @Override
    public void extractRenderState(ClientBogged bogged, BoggedRenderState state, float f) {
        super.extractRenderState(bogged, state, f);
        state.isPassenger = bogged.isPassenger();
    }
}
