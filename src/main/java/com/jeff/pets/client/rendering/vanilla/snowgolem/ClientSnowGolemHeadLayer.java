package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer implements LayerRenderer<ClientSnowGolem> {
    private final BlockRendererDispatcher blockRenderer;
    private final RenderItem itemRenderer;
    private final RenderLiving<ClientSnowGolem> renderer;

    public ClientSnowGolemHeadLayer(RenderLiving<ClientSnowGolem> renderLayerParent, BlockRendererDispatcher blockRenderDispatcher, RenderItem itemRenderer) {
        this.renderer = renderLayerParent;
        this.blockRenderer = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    public void render(ClientSnowGolem snowGolem, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            if (!snowGolem.isInvisible()) {
                GlStateManager.pushMatrix();
                this.renderer.getMainModel().setRotationAngles(f, g, i, j, k, l, snowGolem);
                float m = 0.625F;
                GlStateManager.translatef(0.0F, -0F, 0.0F);
                GlStateManager.scalef(0.625F, -0.625F, -0.625F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                ItemStack itemStack = new ItemStack(Blocks.PUMPKIN);
                this.itemRenderer.renderItem(itemStack, ItemCameraTransforms.TransformType.HEAD);
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
