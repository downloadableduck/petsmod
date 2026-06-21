package com.jeff.pets.rendering.aprilfools.megaspud;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MegaSpudOuterLayer extends RenderLayer<@NotNull SlimeRenderState, @NotNull MegaSpudModel> {

    public static final ModelLayerLocation MEGA_SPUD_OUTER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("mega_spud_outer"), "main");

    private final EntityModel model;

    public MegaSpudOuterLayer(RenderLayerParent<@NotNull SlimeRenderState, @NotNull MegaSpudModel> renderLayerParent, EntityRendererProvider.Context context) {
        super(renderLayerParent);
        this.model = new SlimeModel(context.bakeLayer(MEGA_SPUD_OUTER_LOCATION));
    }

    public static ResourceLocation megaSpud() {
        return ResourceLocation.withDefaultNamespace("textures/entity/slime/mega_spud.png");
    }


    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int i, SlimeRenderState entityRenderState, float f, float g) {
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0f);
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(ResourceLocation.withDefaultNamespace("textures/entity/slime/mega_spud.png")));
        this.model.renderToBuffer(poseStack, vertexConsumer, i, overlayCoords);
    }
}
