package com.jeff.pets.rendering.vanilla.breeze;

import com.jeff.pets.mob.vanilla.hostile.ClientBreeze;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ClientBreezeWindLayer extends RenderLayer<ClientBreeze, ClientBreezeModel> {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/breeze/breeze_wind.png");
    private final ClientBreezeModel model;

    public ClientBreezeWindLayer(EntityRendererProvider.Context context, RenderLayerParent<ClientBreeze, ClientBreezeModel> renderLayerParent) {
        super(renderLayerParent);
        this.model = new ClientBreezeModel(context.bakeLayer(ModelLayers.BREEZE_WIND));
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ClientBreeze breeze, float f, float g, float h, float j, float k, float l) {
        float m = (float) breeze.tickCount + h;
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.breezeWind(TEXTURE_LOCATION, this.xOffset(m) % 1.0F, 0.0F));
        this.model.setupAnim(breeze, f, g, j, k, l);
        ClientBreezeRenderer.enable(this.model, new ModelPart[]{this.model.wind}).renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
    }

    private float xOffset(float f) {
        return f * 0.02F;
    }
}
