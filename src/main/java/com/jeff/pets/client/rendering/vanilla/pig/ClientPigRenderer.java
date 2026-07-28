package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer<ClientPig, ClientPigModel> {
    public String pigTexturePath;

    public ClientPigRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPigModel(), 0.7F);
    }

    @Override
    protected void scale(ClientPig livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    public ResourceLocation getTextureLocation(ClientPig pigRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/pig/pig.png");
    }
}
