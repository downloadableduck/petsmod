package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer extends LayerRenderer<ClientSnowGolem, SnowManModel<ClientSnowGolem>> {
    private final BlockRendererDispatcher blockRenderer;
    private final ItemRenderer itemRenderer;

    public ClientSnowGolemHeadLayer(IEntityRenderer<ClientSnowGolem, SnowManModel<ClientSnowGolem>> renderLayerParent, BlockRendererDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    public void render(com.mojang.blaze3d.matrix.MatrixStack poseStack, net.minecraft.client.renderer.IRenderTypeBuffer multiBufferSource, int i, ClientSnowGolem snowGolem, float f, float g, float h, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            boolean bl = snowGolem.isGlowing() && snowGolem.isInvisible();
            if (!snowGolem.isInvisible() || bl) {
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                float m = 0.625F;
                poseStack.translate(0.0F, -0.34375F, 0.0F);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                poseStack.scale(0.625F, -0.625F, -0.625F);
                ItemStack itemStack = new ItemStack(Blocks.CARVED_PUMPKIN);
                if (bl) {
                    BlockState blockState = Blocks.CARVED_PUMPKIN.defaultBlockState();
                    IBakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);
                    int n = LivingRenderer.getOverlayCoords(snowGolem, 0.0F);
                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(AtlasTexture.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, n);
                } else {
                    this.itemRenderer.renderStatic(snowGolem, itemStack, ItemCameraTransforms.TransformType.HEAD, false, poseStack, multiBufferSource, snowGolem.level, i, LivingRenderer.getOverlayCoords(snowGolem, 0.0F));
                }

                poseStack.popPose();
            }
        }
    }
}
