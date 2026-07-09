package com.jeff.pets.client.rendering.aprilfools.nerdcreeper;

import com.jeff.pets.mob.aprilfools.NerdCreeper;
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
import net.minecraft.resources.ResourceLocation;


public class NerdCreeperNerdLayer extends RenderLayer< NerdCreeper,  CreeperModel<NerdCreeper>> {

    private final CreeperModel nerdCreeperLayer;

    public NerdCreeperNerdLayer(RenderLayerParent< NerdCreeper,  CreeperModel<NerdCreeper>> renderLayerParent, EntityRendererProvider.Context context) {
        super(renderLayerParent);
        this.nerdCreeperLayer = new CreeperModel(context.bakeLayer(ModelLayers.CREEPER));
    }

    @Override
    public void render( PoseStack poseStack,  MultiBufferSource bufferSource, int i, NerdCreeper entityRenderState, float f, float g, float k, float m, float l, float ignored) {
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0f);
        poseStack.pushPose();
        poseStack.scale(1.1f, 1.0f, 1.1f);
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation("minecraft", "textures/entity/creeper/nerd_creeper_overlay.png")));
        this.getParentModel().renderToBuffer(poseStack, consumer, i, overlayCoords, 1, 1, 1, 1);
        poseStack.popPose();
    }
}
