package com.jeff.pets.client.rendering.vanilla.breeze;

import com.jeff.pets.mob.vanilla.hostile.ClientBreeze;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBreezeRenderer extends PetRenderer<@NotNull ClientBreeze, @NotNull ClientBreezeModel> {

    public static final ModelLayerLocation BREEZE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientbreeze"), "main");

    public ClientBreezeRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientBreezeModel(context.bakeLayer(BREEZE_LOCATION)), 0.75f);
        this.addLayer(new ClientBreezeWindLayer(context, this));
    }

    public static ClientBreezeModel enable(ClientBreezeModel breezeModel, ModelPart... modelParts) {
        breezeModel.head().visible = false;
        breezeModel.eyes.visible = false;
        breezeModel.rods.visible = false;
        breezeModel.wind.visible = false;

        for (ModelPart modelPart : modelParts) {
            modelPart.visible = true;
        }

        return breezeModel;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientBreeze livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/breeze/breeze.png");
    }

    @Override
    public void render(ClientBreeze breezeRenderState, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        ClientBreezeModel breezeModel = this.getModel();
        enable(breezeModel, breezeModel.head(), breezeModel.rods);
        super.render(breezeRenderState, f, g, poseStack, multiBufferSource, i);
    }
}
