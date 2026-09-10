package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.util.Identifier;

public class ClientDrownedOuterLayer implements FeatureRenderer<ClientDrowned> {

    private final ClientDrownedRenderer renderer;
    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(ClientDrownedRenderer renderer) {
        this.renderer = renderer;
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void render(ClientDrowned zombieEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!zombieEntity.isInvisible()) {
            this.renderer.getModel().copy(this.drownedModel);
            this.drownedModel.animateModel(zombieEntity, f, g, h);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.renderer.bindTexture(new Identifier("textures/entity/zombie/drowned_outer_layer.png"));
            this.drownedModel.render(zombieEntity, f, g, i, j, k, l);
        }
    }

    @Override
    public boolean combineTextures() {
        return false;
    }
}