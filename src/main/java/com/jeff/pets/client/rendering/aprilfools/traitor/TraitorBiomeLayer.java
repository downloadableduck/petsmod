package com.jeff.pets.client.rendering.aprilfools.traitor;

import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.mob.aprilfools.Traitor;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class TraitorBiomeLayer extends RenderLayer<@NotNull Traitor, @NotNull ClientEvokerModel<Traitor>> {

    public static final ModelLayerLocation DESERT_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/desert.png"), "main");
    public static final ModelLayerLocation JUNGLE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/jungle.png"), "main");
    public static final ModelLayerLocation PLAINS_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"), "main");
    public static final ModelLayerLocation SAVANNA_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/savanna.png"), "main");
    public static final ModelLayerLocation SNOW_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/snow.png"), "main");
    public static final ModelLayerLocation SWAMP_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/swamp.png"), "main");
    public static final ModelLayerLocation TAIGA_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/taiga.png"), "main");

    public TraitorBiomeLayer(RenderLayerParent<@NotNull Traitor, @NotNull ClientEvokerModel<Traitor>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int i, Traitor entityRenderState, float f, float g, float h, float j, float k, float m) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        switch (CONFIG.traitorSkin) {
            case "desert" ->
                    renderColoredCutoutModel(this.getParentModel(), DESERT_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
            case "jungle" ->
                    renderColoredCutoutModel(this.getParentModel(), JUNGLE_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
            case "savanna" ->
                    renderColoredCutoutModel(this.getParentModel(), SAVANNA_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
            case "snow" ->
                    renderColoredCutoutModel(this.getParentModel(), SNOW_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
            case "swamp" ->
                    renderColoredCutoutModel(this.getParentModel(), SWAMP_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
            case "taiga" ->
                    renderColoredCutoutModel(this.getParentModel(), TAIGA_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
            case "plains" ->
                    renderColoredCutoutModel(this.getParentModel(), PLAINS_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);

           default ->
                    renderColoredCutoutModel(this.getParentModel(), PLAINS_LOCATION.getModel(), poseStack, bufferSource, i, entityRenderState, 1, 1, 1);
        }
        poseStack.popPose();
    }
}
