package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientMushroomCowMushroomLayer extends RenderLayer<ClientMooshroom, ClientCowModel<ClientMooshroom>> {
    private final BlockRenderDispatcher blockRenderer;

    public ClientMushroomCowMushroomLayer(RenderLayerParent<ClientMooshroom, ClientCowModel<ClientMooshroom>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ClientMooshroom mushroomCow, float f, float g, float h, float j, float k, float l) {
        if (!mushroomCow.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean bl = minecraft.shouldEntityAppearGlowing(mushroomCow) && mushroomCow.isInvisible();
            if (!mushroomCow.isInvisible() || bl) {
                BlockState blockState = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM.defaultBlockState() : Blocks.RED_MUSHROOM.defaultBlockState();
                int m = LivingEntityRenderer.getOverlayCoords(mushroomCow, 0.0F);
                BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0f, 1.0f);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, multiBufferSource, i, bl, blockState, m, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0f, 1.0f);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, multiBufferSource, i, bl, blockState, m, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                (this.getParentModel()).getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0f, 1.0f);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack, multiBufferSource, i, bl, blockState, m, bakedModel);
                poseStack.popPose();
            }
        }
    }

    private void renderMushroomBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, boolean bl, BlockState blockState, int j, BakedModel bakedModel) {
        if (bl) {
            this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, j);
        } else {
            this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, i, j);
        }

    }
}
