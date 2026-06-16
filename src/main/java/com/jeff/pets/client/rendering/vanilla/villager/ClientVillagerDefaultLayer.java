package com.jeff.pets.client.rendering.vanilla.villager;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer extends RenderLayer<@NotNull VillagerRenderState, @NotNull VillagerModel> {
    public static final ModelLayerLocation DEFAULT_VILLAGER_LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/plains.png"), "main");

    public ClientVillagerDefaultLayer(RenderLayerParent<@NotNull VillagerRenderState, @NotNull VillagerModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, VillagerRenderState entityRenderState, float f, float g) {
        renderColoredCutoutModel(this.getParentModel(), DEFAULT_VILLAGER_LAYER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
    }
}
