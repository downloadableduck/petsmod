package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMushroomCowMushroomLayer implements FeatureRenderer<ClientMooshroom> {
    private final BlockRenderManager blockRenderer;
    private final ClientMooshroomRenderer renderer;

    public ClientMushroomCowMushroomLayer(ClientMooshroomRenderer renderer) {
        this.renderer = renderer;
        this.blockRenderer = MinecraftClient.getInstance().getBlockRenderManager();
    }

    public void render(ClientMooshroom mooshroomEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!mooshroomEntity.isBaby() && !mooshroomEntity.isInvisible()) {
            BlockState blockState = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM.getDefaultState() : Blocks.RED_MUSHROOM.getDefaultState();
            this.renderer.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
            GlStateManager.enableCull();
            GlStateManager.method_12284(GlStateManager.class_2865.FRONT);
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F, -1.0F, 1.0F);
            GlStateManager.translate(0.2F, 0.35F, 0.5F);
            GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
            BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
            GlStateManager.pushMatrix();
            GlStateManager.translate(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderBlockEntity(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.1F, 0.0F, -0.6F);
            GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderBlockEntity(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            ((ClientCowModel) this.renderer.getModel()).getHead().preRender(0.0625F);
            GlStateManager.scale(1.0F, -1.0F, 1.0F);
            GlStateManager.translate(0.0F, 0.7F, -0.2F);
            GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderBlockEntity(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.method_12284(GlStateManager.class_2865.BACK);
            GlStateManager.disableCull();
        }
    }

    @Override
    public boolean combineTextures() {
        return false;
    }
}