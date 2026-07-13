package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.math.vector.Vector3f;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientMushroomCowMushroomLayer extends LayerRenderer<ClientMooshroom, ClientCowModel<ClientMooshroom>> {
    private final BlockRendererDispatcher blockRenderer;

    public ClientMushroomCowMushroomLayer(IEntityRenderer<ClientMooshroom, ClientCowModel<ClientMooshroom>> renderLayerParent, BlockRendererDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    @Override
    public void render(MatrixStack poseStack, IRenderTypeBuffer multiBufferSource, int i, ClientMooshroom mushroomCow, float f, float g, float h, float j, float k, float l) {
        if (!mushroomCow.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean bl = minecraft.shouldEntityAppearGlowing(mushroomCow) && mushroomCow.isInvisible();
            if (!mushroomCow.isInvisible() || bl) {
                BlockState blockState = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM.defaultBlockState() : Blocks.RED_MUSHROOM.defaultBlockState();
                int m = LivingRenderer.getOverlayCoords(mushroomCow, 0.0F);
                IBakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, multiBufferSource, i, bl, blockState, m, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, multiBufferSource, i, bl, blockState, m, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                (this.getParentModel()).getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, multiBufferSource, i, bl, blockState, m, bakedModel);
                poseStack.popPose();
            }
        }
    }

    private void renderMushroomBlock(MatrixStack poseStack, IRenderTypeBuffer multiBufferSource, int i, boolean bl, BlockState blockState, int j, IBakedModel bakedModel) {
        if (bl) {
            this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(AtlasTexture.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, j);
        } else {
            this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, i, j);
        }

    }
}
