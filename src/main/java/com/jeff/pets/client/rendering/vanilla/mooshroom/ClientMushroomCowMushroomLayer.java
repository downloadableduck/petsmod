package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.block.BlockRenderDispatcher;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.texture.TextureAtlas;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMushroomCowMushroomLayer implements EntityRenderLayer<ClientMooshroom> {
    private final net.minecraft.client.render.entity.MobRenderer renderer;
    private final BlockRenderDispatcher blockRenderer;

    public ClientMushroomCowMushroomLayer(net.minecraft.client.render.entity.MobRenderer renderer, BlockRenderDispatcher blockRenderDispatcher) {
        this.renderer = renderer;
        this.blockRenderer = blockRenderDispatcher;
    }

    public void render(ClientMooshroom mooshroomEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!mooshroomEntity.isBaby() && !mooshroomEntity.isInvisible()) {
            BlockState blockState = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM.defaultState() : Blocks.RED_MUSHROOM.defaultState();
            this.renderer.bindTexture(TextureAtlas.BLOCKS_LOCATION);
            GlStateManager.enableCull();
            GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.2F, 0.35F, 0.5F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            BlockRenderDispatcher blockRenderManager = Minecraft.getInstance().getBlockRenderDispatcher();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderAsItem(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.1F, 0.0F, -0.6F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderAsItem(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.0F, 0.7F, -0.2F);
            GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockRenderManager.renderAsItem(blockState, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.cullFace(GlStateManager.CullFace.BACK);
            GlStateManager.disableCull();
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
