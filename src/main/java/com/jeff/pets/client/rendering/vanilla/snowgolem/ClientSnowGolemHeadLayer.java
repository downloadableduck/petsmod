package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.block.state.BlockState;
import net.minecraft.client.render.block.BlockRenderDispatcher;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.block.ModelTransformations;
import net.minecraft.client.render.model.entity.SnowGolemModel;
import net.minecraft.client.resource.model.BakedModel;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer implements EntityRenderLayer<ClientSnowGolem> {
    private final net.minecraft.client.render.entity.MobRenderer renderer;
    private final BlockRenderDispatcher blockRenderer;
    private final ItemRenderer itemRenderer;

    public ClientSnowGolemHeadLayer(net.minecraft.client.render.entity.MobRenderer renderer, BlockRenderDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        this.renderer = renderer;
        this.blockRenderer = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(ClientSnowGolem snowGolem, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            boolean bl = snowGolem.isGlowing() && snowGolem.isInvisible();
            if (!snowGolem.isInvisible() || bl) {
                net.minecraft.client.render.platform.GlStateManager.pushMatrix();
                //this.getContextModel().method_2834().rotate(poseStack);
                float m = 0.625F;
                GlStateManager.translatef(0.0F, -0F, 0.0F);
                //poseStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
                net.minecraft.client.render.platform.GlStateManager.scalef(0.625F, -0.625F, -0.625F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                ItemStack itemStack = new ItemStack(Blocks.CARVED_PUMPKIN);
                if (bl) {
                    BlockState blockState = Blocks.CARVED_PUMPKIN.defaultState();
                    BakedModel bakedModel = this.blockRenderer.getModel(blockState);
                    int n = 0;
                    net.minecraft.client.render.platform.GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
                    this.blockRenderer.getModelRenderer().render(blockState, bakedModel, 0.0F, 0.0F, 0.0F, i);
                } else {
                    this.itemRenderer.renderItemInHand(itemStack, ModelTransformations.Type.HEAD);
                }

                net.minecraft.client.render.platform.GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
