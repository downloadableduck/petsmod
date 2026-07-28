package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class ClientDrownedOuterLayer extends LayerRenderer<ClientDrowned, ClientDrownedModel> {
    private static final ResourceLocation DROWNED_OUTER_LAYER_LOCATION = new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png");

    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(IEntityRenderer<ClientDrowned, ClientDrownedModel> renderLayerParent, EntityRendererManager context) {
        super(renderLayerParent);
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void render(ClientDrowned p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (!p_212842_1_.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.drownedModel);
            this.drownedModel.prepareMobModel(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_4_);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindTexture(DROWNED_OUTER_LAYER_LOCATION);
            this.drownedModel.render(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
        }
    }

    @Override
    public boolean colorsOnDamage() {
        return false;
    }
}
