package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SnowmanPumpkinFeatureRenderer;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer extends FeatureRenderer<ClientSnowGolem, SnowmanEntityModel<ClientSnowGolem>> {
    private final BlockRenderManager blockRenderer;
    private final ItemRenderer itemRenderer;

    public ClientSnowGolemHeadLayer(FeatureRendererContext<ClientSnowGolem, SnowmanEntityModel<ClientSnowGolem>> renderLayerParent, BlockRenderManager blockRenderDispatcher, ItemRenderer itemRenderer) {
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
                    BlockState blockState = Blocks.CARVED_PUMPKIN.getDefaultState();
                    BakedModel bakedModel = this.blockRenderer.getModel(blockState);
                    int n = 0;
                    com.mojang.blaze3d.platform.GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
                    this.blockRenderer.getModelRenderer().render(blockState, bakedModel, 0.0F, 0.0F, 0.0F, i);
                } else {
                    this.itemRenderer.renderItem(itemStack, ModelTransformation.Type.HEAD);
                }

                com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean hasHurtOverlay() {
        return false;
    }
}
