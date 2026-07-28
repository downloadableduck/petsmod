package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedRenderer extends PetRenderer<ClientDrowned, ClientDrownedModel> {

    public ClientDrownedRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientDrownedModel(0.0F, 0.0F, 64, 64), 0.75f);
        this.addLayer(new ClientDrownedOuterLayer(this, context));
    }

    @Override
    protected void scale(ClientDrowned state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientDrowned livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/drowned.png");
    }

    @Override
    public void setupRotations(ClientDrowned state, float f, float g, float i) {
        super.setupRotations(state, f, g, i);
        if (state.isPassenger()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
