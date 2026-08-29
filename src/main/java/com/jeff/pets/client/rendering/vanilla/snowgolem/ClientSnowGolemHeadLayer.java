package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer implements LayerRenderer<ClientSnowGolem> {
    private final BlockRendererDispatcher blockRenderer;
    private final ItemRenderer itemRenderer;
    private final RenderLivingBase<ClientSnowGolem> renderer;

    public ClientSnowGolemHeadLayer(RenderLivingBase<ClientSnowGolem> renderLayerParent, BlockRendererDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        this.renderer = renderLayerParent;
        this.blockRenderer = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    public void render(ClientSnowGolem snowGolem, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            boolean bl = snowGolem.isGlowing() && snowGolem.isInvisible();
            if (!snowGolem.isInvisible() || bl) {
                net.minecraft.client.renderer.GlStateManager.pushMatrix();
                this.renderer.getMainModel().setRotationAngles(f, g, i, j, k, l, snowGolem);
                float m = 0.625F;
                GlStateManager.translatef(0.0F, -0F, 0.0F);
                net.minecraft.client.renderer.GlStateManager.scalef(0.625F, -0.625F, -0.625F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                ItemStack itemStack = new ItemStack(Blocks.CARVED_PUMPKIN);
                if (bl) {
                    IBlockState blockState = Blocks.CARVED_PUMPKIN.getDefaultState();
                    net.minecraft.client.renderer.GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
                    this.blockRenderer.renderBlockBrightness(blockState, 1.0F);
                } else {
                    this.itemRenderer.renderItem(itemStack, ItemCameraTransforms.TransformType.HEAD);
                }

                net.minecraft.client.renderer.GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
