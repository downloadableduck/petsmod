package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.block.state.BlockState;
import net.minecraft.client.render.block.BlockRenderDispatcher;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.layer.EntityRenderLayerParent;
import net.minecraft.client.render.model.block.ModelTransformations;
import net.minecraft.client.render.model.entity.SnowGolemModel;
import net.minecraft.client.resource.model.BakedModel;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer extends EntityRenderLayer<ClientSnowGolem, SnowGolemModel<ClientSnowGolem>> {
    private final BlockRenderDispatcher blockRenderer;
    private final ItemRenderer itemRenderer;

    public ClientSnowGolemHeadLayer(EntityRenderLayerParent<ClientSnowGolem, SnowGolemModel<ClientSnowGolem>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(ClientSnowGolem snowGolem, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            boolean bl = snowGolem.isGlowing() && snowGolem.isInvisible();
            if (!snowGolem.isInvisible() || bl) {
                com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
                //this.getContextModel().method_2834().rotate(poseStack);
                float m = 0.625F;
                GlStateManager.translatef(0.0F, -0F, 0.0F);
                //poseStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
                com.mojang.blaze3d.platform.GlStateManager.scalef(0.625F, -0.625F, -0.625F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                ItemStack itemStack = new ItemStack(Blocks.CARVED_PUMPKIN);
                if (bl) {
                    BlockState blockState = Blocks.CARVED_PUMPKIN.defaultState();
                    BakedModel bakedModel = this.blockRenderer.getModel(blockState);
                    int n = 0;
                    com.mojang.blaze3d.platform.GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
                    this.blockRenderer.getModelRenderer().render(blockState, bakedModel, 0.0F, 0.0F, 0.0F, i);
                } else {
                    this.itemRenderer.renderItemInHand(itemStack, ModelTransformations.Type.HEAD);
                }

                com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
