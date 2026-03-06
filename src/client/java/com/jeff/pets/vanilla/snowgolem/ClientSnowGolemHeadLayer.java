package com.jeff.pets.vanilla.snowgolem;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

@Environment(EnvType.CLIENT)
public class ClientSnowGolemHeadLayer extends RenderLayer<@NotNull SnowGolemRenderState, @NotNull SnowGolemModel> {
    private final BlockRenderDispatcher blockRenderer;

    public ClientSnowGolemHeadLayer(RenderLayerParent<@NotNull SnowGolemRenderState, @NotNull SnowGolemModel> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, SnowGolemRenderState snowGolemRenderState, float f, float g) {
        if (Objects.equals(CONFIG.snowGolemSkin, "pumpkin_on")) {
            poseStack.pushPose();
            (this.getParentModel()).getHead().translateAndRotate(poseStack);
            poseStack.translate(0.0F, -0.34375F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            poseStack.scale(0.625F, -0.625F, -0.625F);
            BlockState blockState = Blocks.CARVED_PUMPKIN.defaultBlockState();
            BlockStateModel blockStateModel = this.blockRenderer.getBlockModel(blockState);
            int j = LivingEntityRenderer.getOverlayCoords(snowGolemRenderState, 0.0F);
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            RenderType renderType = snowGolemRenderState.appearsGlowing() && snowGolemRenderState.isInvisible ? RenderTypes.outline(TextureAtlas.LOCATION_BLOCKS) : ItemBlockRenderTypes.getRenderType(blockState);
            submitNodeCollector.submitBlockModel(poseStack, renderType, blockStateModel, 0.0F, 0.0F, 0.0F, i, j, snowGolemRenderState.outlineColor);
            poseStack.popPose();
        }
    }
}
