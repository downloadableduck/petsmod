package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class ClientDrownedOuterLayer implements LayerRenderer<ClientDrowned> {
    private static final ResourceLocation DROWNED_OUTER_LAYER_LOCATION = new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png");

    private final ClientDrownedModel drownedModel;
    private final RenderLivingBase<ClientDrowned> renderer;

    public ClientDrownedOuterLayer(RenderLivingBase<ClientDrowned> renderLayerParent, RenderManager context) {
        this.renderer = renderLayerParent;
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void doRenderLayer(ClientDrowned p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (!p_212842_1_.isInvisible()) {
            this.drownedModel.setModelAttributes(this.renderer.getMainModel());
            this.drownedModel.setLivingAnimations(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_4_);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.renderer.bindTexture(DROWNED_OUTER_LAYER_LOCATION);
            this.drownedModel.render(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
