package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<ClientPolarBear, ClientPolarBearModel> {

    public ClientPolarBearRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    protected void scale(ClientPolarBear livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientPolarBear livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bear/polarbear.png");
    }
}
