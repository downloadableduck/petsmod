package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<ClientPolarBear, ClientPolarBearModel> {

    public ClientPolarBearRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    public void preRenderCallback(ClientPolarBear livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
        
    }

    @Override
    public ResourceLocation getEntityTexture(ClientPolarBear livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bear/polarbear.png");
    }
}
