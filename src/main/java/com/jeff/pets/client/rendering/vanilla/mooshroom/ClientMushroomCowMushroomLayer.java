package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMushroomCowMushroomLayer extends LayerRenderer<ClientMooshroom, ClientCowModel<ClientMooshroom>> {
    private final BlockRendererDispatcher blockRenderer;

    public ClientMushroomCowMushroomLayer(IEntityRenderer<ClientMooshroom, ClientCowModel<ClientMooshroom>> renderLayerParent, BlockRendererDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    @Override
    public void render(ClientMooshroom p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (!p_212842_1_.isBaby() && !p_212842_1_.isInvisible()) {
            BlockState blockstate = CONFIG.mooshroomSkin.equals("brown") ? Blocks.BROWN_MUSHROOM.defaultBlockState() : Blocks.RED_MUSHROOM.defaultBlockState();
            this.bindTexture(AtlasTexture.LOCATION_BLOCKS);
            GlStateManager.enableCull();
            GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.2F, 0.35F, 0.5F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockrendererdispatcher.renderSingleBlock(blockstate, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.1F, 0.0F, -0.6F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockrendererdispatcher.renderSingleBlock(blockstate, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            this.getParentModel().getHead().translateTo(0.0625F);
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.0F, 0.7F, -0.2F);
            GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            blockrendererdispatcher.renderSingleBlock(blockstate, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.cullFace(GlStateManager.CullFace.BACK);
            GlStateManager.disableCull();
        }
    }

    @Override
    public boolean colorsOnDamage() {
        return false;
    }
}
