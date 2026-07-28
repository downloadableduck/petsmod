package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.util.ResourceLocation;

public class ClientBatRenderer extends PetRenderer<ClientBat, ClientBatModel> {

    public ClientBatRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    @Override
    protected void scale(ClientBat bat, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientBat batRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bat.png");
    }
}
