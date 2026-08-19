package com.jeff.pets.rendering.aprilfools.nerdcreeper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class NerdCreeperNerdLayer extends RenderLayer<@NotNull CreeperRenderState, @NotNull CreeperModel> {

    private final CreeperModel nerdCreeperLayer;

    public NerdCreeperNerdLayer(RenderLayerParent<@NotNull CreeperRenderState, @NotNull CreeperModel> renderLayerParent, EntityRendererProvider.Context context) {
        super(renderLayerParent);
        this.nerdCreeperLayer = new CreeperModel(context.bakeLayer(ModelLayers.CREEPER));
    }

    @Override
    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, CreeperRenderState entityRenderState, float f, float g) {
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0f);
        poseStack.pushPose();
        poseStack.scale(1.1f, 1.0f, 1.1f);
        submitNodeCollector.order(1).submitModel(this.nerdCreeperLayer, entityRenderState, poseStack, RenderTypes.entityTranslucent(Identifier.withDefaultNamespace("textures/entity/creeper/nerd_creeper_overlay.png")), i, overlayCoords, -1, null, entityRenderState.outlineColor, null);
        poseStack.popPose();
    }
}
