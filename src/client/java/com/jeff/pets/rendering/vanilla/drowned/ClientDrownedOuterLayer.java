package com.jeff.pets.rendering.vanilla.drowned;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedOuterLayer extends RenderLayer<@NotNull ZombieRenderState, @NotNull ClientDrownedModel> {

    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(RenderLayerParent<@NotNull ZombieRenderState, @NotNull ClientDrownedModel> renderLayerParent, EntityRendererProvider.Context context) {
        super(renderLayerParent);
        this.drownedModel = new ClientDrownedModel(context.bakeLayer(ModelLayers.DROWNED));
    }

    @Override
    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, ZombieRenderState entityRenderState, float f, float g) {
        int overlayCoords = ClientDrownedRenderer.getOverlayCoords(entityRenderState, 0.0f);
        poseStack.scale(1f, 1f, 1f);
        submitNodeCollector.order(1).submitModel(this.drownedModel, entityRenderState, poseStack, RenderType.entityTranslucent(ResourceLocation.withDefaultNamespace("textures/entity/zombie/drowned_outer_layer.png")), i, overlayCoords, -1, null, entityRenderState.outlineColor, null);
    }

}
