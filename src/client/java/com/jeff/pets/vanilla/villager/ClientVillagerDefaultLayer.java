package com.jeff.pets.vanilla.villager;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer extends RenderLayer<@NotNull LivingEntityRenderState, @NotNull ClientVillagerModel> {
    public static final ModelLayerLocation DEFAULT_VILLAGER_LAYER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/type/plains.png"), "main");

    public ClientVillagerDefaultLayer(RenderLayerParent<@NotNull LivingEntityRenderState, @NotNull ClientVillagerModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, LivingEntityRenderState entityRenderState, float f, float g) {
        renderColoredCutoutModel(this.getParentModel(), DEFAULT_VILLAGER_LAYER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
    }
}
