package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.block.BlockRenderer;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.texture.TextureAtlas;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMushroomCowMushroomLayer implements PetRenderLayer {
    private final PetRenderer renderer;
    private final BlockRenderer blockRenderer;

    public ClientMushroomCowMushroomLayer(PetRenderer renderer, BlockRenderer BlockRenderer) {
        this.renderer = renderer;
        this.blockRenderer = BlockRenderer;
    }

    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientMooshroom mooshroomEntity = (ClientMooshroom) entity;
        if (!mooshroomEntity.isBaby() && !mooshroomEntity.isInvisible()) {
            net.minecraft.block.Block block = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM : Blocks.RED_MUSHROOM;
            this.renderer.bindTexture(TextureAtlas.BLOCKS_LOCATION);
            GlStateManager.enableCull();
            GlStateManager.cullFace(1029);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.2F, 0.35F, 0.5F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            
            GlStateManager.pushMatrix();
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            this.blockRenderer.renderAsItem(block, 0, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.1F, 0.0F, -0.6F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            this.blockRenderer.renderAsItem(block, 0, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.0F, 0.7F, -0.2F);
            GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            this.blockRenderer.renderAsItem(block, 0, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.cullFace(1028);
            GlStateManager.disableCull();
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
