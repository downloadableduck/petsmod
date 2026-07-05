package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientGhastRenderer extends PetRenderer<@NotNull ClientGhast, @NotNull GhastModel<ClientGhast>> {

    public static final ModelLayerLocation GHAST_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientghast"), "main");

    public ClientGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel<>(context.bakeLayer(ModelLayers.GHAST)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientGhast livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void scale(ClientGhast ghast, PoseStack poseStack, float f) {
        poseStack.scale(4.5F, 4.5F, 4.5F);
    }
}
