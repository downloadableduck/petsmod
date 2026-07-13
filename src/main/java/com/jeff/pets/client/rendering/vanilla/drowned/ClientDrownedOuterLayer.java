package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class ClientDrownedOuterLayer extends LayerRenderer {

    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(IEntityRenderer<ClientDrowned, ?> renderLayerParent, net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(renderLayerParent);
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void render(MatrixStack poseStack, IRenderTypeBuffer source, int i, Entity entityRenderState, float f, float g, float h, float b, float k, float t) {
        int overlayCoords = ClientDrownedRenderer.getOverlayCoords((LivingEntity) entityRenderState, 0.0f);
        poseStack.scale(1f, 1f, 1f);
        IVertexBuilder consumer = source.getBuffer(RenderType.entityTranslucent(new ResourceLocation("minecraft", "textures/entity/zombie/drowned_outer_layer.png")));
        this.getParentModel().renderToBuffer(poseStack, consumer, i, overlayCoords, 1, 1, 1, 1);
    }
}
