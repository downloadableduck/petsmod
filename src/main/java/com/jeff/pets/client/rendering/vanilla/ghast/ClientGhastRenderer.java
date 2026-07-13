package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.util.ResourceLocation;

public class ClientGhastRenderer extends PetRenderer<ClientGhast, GhastModel<ClientGhast>> {

    public ClientGhastRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new GhastModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientGhast livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void scale(ClientGhast ghast, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        poseStack.scale(4.5F, 4.5F, 4.5F);
    }
}
