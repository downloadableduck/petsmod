package com.jeff.pets.client.rendering.aprilfools.mooncow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;


public class MoonCowHelmetLayer extends RenderLayer<@NotNull MoonCowRenderState, @NotNull LegacyCowModel> {

    private final BlockRenderDispatcher dispatcher;

    public MoonCowHelmetLayer(final RenderLayerParent<@NotNull MoonCowRenderState, @NotNull LegacyCowModel> renderer, BlockRenderDispatcher dispatcher) {
        super(renderer);
        this.dispatcher = dispatcher;
    }

    @Override
    public void submit(final PoseStack poseStack, final @NotNull SubmitNodeCollector submitNodeCollector, final int lightCoords, final MoonCowRenderState state, final float yRot, final float xRot) {
        poseStack.pushPose();
        this.getParentModel().getHead().translateAndRotate(poseStack);
        poseStack.translate(0.0F, -0.035F, -0.2F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.scale(0.625F, -0.625F, -0.625F);
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        submitNodeCollector.submitBlockModel(poseStack, ItemBlockRenderTypes.getRenderType(Blocks.GLASS.defaultBlockState()), this.dispatcher.getBlockModel(Blocks.GLASS.defaultBlockState()), 0, 0, 0, lightCoords, overlayCoords, state.outlineColor);
        poseStack.popPose();
    }
}
