package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMushroomCowMushroomLayer extends FeatureRenderer<ClientMooshroom, ClientCowModel<ClientMooshroom>> {
    private final BlockRenderManager blockRenderer;

    public ClientMushroomCowMushroomLayer(FeatureRendererContext<ClientMooshroom, ClientCowModel<ClientMooshroom>> renderLayerParent, BlockRenderManager blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    public void render(ClientMooshroom mooshroomEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!mooshroomEntity.isBaby() && !mooshroomEntity.isInvisible()) {
            BlockState blockState = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM.getDefaultState() : Blocks.RED_MUSHROOM.getDefaultState();
            this.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
            GlStateManager.enableCull();
            GlStateManager.cullFace(GlStateManager.FaceSides.FRONT);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.2F, 0.35F, 0.5F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderDynamic(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.1F, 0.0F, -0.6F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderDynamic(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            this.getModel().method_2800().applyTransform(0.0625F);
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.0F, 0.7F, -0.2F);
            GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderDynamic(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.cullFace(GlStateManager.FaceSides.BACK);
            GlStateManager.disableCull();
        }
    }

    @Override
    public boolean hasHurtOverlay() {
        return false;
    }
}
