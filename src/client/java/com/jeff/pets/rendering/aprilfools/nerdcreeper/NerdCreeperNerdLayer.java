package com.jeff.pets.rendering.aprilfools.nerdcreeper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NerdCreeperNerdLayer extends RenderLayer<@NotNull CreeperRenderState, @NotNull CreeperModel> {

    private final CreeperModel nerdCreeperLayer;

    public NerdCreeperNerdLayer(RenderLayerParent<@NotNull CreeperRenderState, @NotNull CreeperModel> renderLayerParent, EntityRendererProvider.Context context) {
        super(renderLayerParent);
        this.nerdCreeperLayer = new CreeperModel(context.bakeLayer(ModelLayers.CREEPER));
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int i, CreeperRenderState entityRenderState, float f, float g) {
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0f);
        poseStack.pushPose();
        poseStack.scale(1.1f, 1.0f, 1.1f);
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(ResourceLocation.withDefaultNamespace("textures/entity/creeper/nerd_creeper_overlay.png")));
        this.getParentModel().renderToBuffer(poseStack, consumer, i, overlayCoords);
        poseStack.popPose();
    }
}
