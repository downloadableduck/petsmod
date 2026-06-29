package com.jeff.pets.client.rendering.aprilfools.mooncow;

import com.jeff.pets.mob.aprilfools.MoonCow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;


public class MoonCowHelmetLayer extends RenderLayer<@NotNull MoonCow, @NotNull LegacyCowModel> {

    private final BlockRenderDispatcher dispatcher;

    public MoonCowHelmetLayer(final RenderLayerParent<@NotNull MoonCow, @NotNull LegacyCowModel> renderer, BlockRenderDispatcher dispatcher) {
        super(renderer);
        this.dispatcher = dispatcher;
    }

    @Override
    public void render(final PoseStack poseStack, MultiBufferSource bufferSource, final int lightCoords, final MoonCow state, final float yRot, final float xRot, float f, float g, float h, float i) {
        poseStack.pushPose();
        this.getParentModel().getHead().translateAndRotate(poseStack);
        poseStack.translate(0.0F, -0.035F, -0.2F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.scale(0.625F, -0.625F, -0.625F);
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        dispatcher.renderSingleBlock(Blocks.GLASS.defaultBlockState(), poseStack, bufferSource, lightCoords, overlayCoords);
        poseStack.popPose();
    }
}
