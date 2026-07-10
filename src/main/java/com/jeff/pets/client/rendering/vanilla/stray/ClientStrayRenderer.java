package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.StrayClothingLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientStrayRenderer extends PetRenderer<@NotNull ClientStray, @NotNull SkeletonModel<@NotNull ClientStray>> {

    public ClientStrayRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new SkeletonModel<>(), 0.75f);
        this.addLayer(new StrayClothingLayer<>((RenderLayerParent) this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientStray livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/stray.png");
    }

    @Override
    public void setupRotations(ClientStray state, @NotNull PoseStack poseStack, float f, float g, float h) {
        super.setupRotations(state, poseStack, f, g, h);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
