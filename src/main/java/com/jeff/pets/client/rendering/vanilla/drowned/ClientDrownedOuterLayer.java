package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedOuterLayer extends RenderLayer {

    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(RenderLayerParent<@NotNull ClientDrowned, ?> renderLayerParent, net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(renderLayerParent);
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i, Entity entityRenderState, float f, float g, float h, float b, float k, float t) {
        int overlayCoords = ClientDrownedRenderer.getOverlayCoords((LivingEntity) entityRenderState, 0.0f);
        poseStack.scale(1f, 1f, 1f);
        VertexConsumer consumer = source.getBuffer(RenderType.entityTranslucent(new ResourceLocation("minecraft", "textures/entity/zombie/drowned_outer_layer.png")));
        this.getParentModel().renderToBuffer(poseStack, consumer, i, overlayCoords, 1, 1, 1, 1);
    }
}
