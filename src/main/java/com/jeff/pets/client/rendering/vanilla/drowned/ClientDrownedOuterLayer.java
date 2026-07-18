package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedOuterLayer extends FeatureRenderer<ClientDrowned, ClientDrownedModel> {

    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(FeatureRendererContext<@NotNull ClientDrowned, ClientDrownedModel> renderLayerParent, net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(renderLayerParent);
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void render(ClientDrowned zombieEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!zombieEntity.isInvisible()) {
            (this.getModel()).setAttributes(this.drownedModel);
            this.drownedModel.animateModel(zombieEntity, f, g, h);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindTexture(new Identifier("textures/entity/zombie/drowned_outer_layer.png"));
            this.drownedModel.method_17088(zombieEntity, f, g, i, j, k, l);
        }
    }

    @Override
    public boolean hasHurtOverlay() {
        return false;
    }
}
