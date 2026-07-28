package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.StayClothingLayer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class ClientStrayRenderer extends PetRenderer<ClientStray, SkeletonModel<ClientStray>> {

    public ClientStrayRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel<>(), 0.75f);
        this.addLayer(new StayClothingLayer<>((IEntityRenderer) this));
    }

    @Override
    public ResourceLocation getTextureLocation(ClientStray livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/stray.png");
    }

    @Override
    public void setupRotations(ClientStray state, float f, float g, float h) {
        super.setupRotations(state, f, g, h);
        if (state.isPassenger()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
