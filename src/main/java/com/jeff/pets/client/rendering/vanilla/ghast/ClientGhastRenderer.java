package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.util.ResourceLocation;

public class ClientGhastRenderer extends PetRenderer<ClientGhast, GhastModel<ClientGhast>> {

    public ClientGhastRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new GhastModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientGhast livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void scale(ClientGhast ghast, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scalef(4.5F, 4.5F, 4.5F);
    }
}
