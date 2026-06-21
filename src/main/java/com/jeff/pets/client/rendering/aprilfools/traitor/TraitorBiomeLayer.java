package com.jeff.pets.client.rendering.aprilfools.traitor;

import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class TraitorBiomeLayer extends RenderLayer<@NotNull EvokerRenderState, @NotNull ClientEvokerModel> {

    public static final ModelLayerLocation DESERT_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/desert.png"), "main");
    public static final ModelLayerLocation JUNGLE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/jungle.png"), "main");
    public static final ModelLayerLocation PLAINS_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/plains.png"), "main");
    public static final ModelLayerLocation SAVANNA_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/savanna.png"), "main");
    public static final ModelLayerLocation SNOW_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/snow.png"), "main");
    public static final ModelLayerLocation SWAMP_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/swamp.png"), "main");
    public static final ModelLayerLocation TAIGA_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/villager/type/taiga.png"), "main");

    public TraitorBiomeLayer(RenderLayerParent<@NotNull EvokerRenderState, @NotNull ClientEvokerModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int i, EvokerRenderState entityRenderState, float f, float g) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        switch (CONFIG.traitorSkin) {
            case "desert" ->
                    renderColoredCutoutModel(this.getParentModel(), DESERT_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
            case "jungle" ->
                    renderColoredCutoutModel(this.getParentModel(), JUNGLE_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
            case "savanna" ->
                    renderColoredCutoutModel(this.getParentModel(), SAVANNA_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
            case "snow" ->
                    renderColoredCutoutModel(this.getParentModel(), SNOW_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
            case "swamp" ->
                    renderColoredCutoutModel(this.getParentModel(), SWAMP_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
            case "taiga" ->
                    renderColoredCutoutModel(this.getParentModel(), TAIGA_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
            case "plains" ->
                    renderColoredCutoutModel(this.getParentModel(), PLAINS_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);

            case null, default ->
                    renderColoredCutoutModel(this.getParentModel(), PLAINS_LOCATION.model(), poseStack, bufferSource, i, entityRenderState, -1);
        }
        poseStack.popPose();
    }
}
