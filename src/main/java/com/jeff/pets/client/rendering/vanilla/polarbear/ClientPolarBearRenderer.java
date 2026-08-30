package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.resource.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<ClientPolarBear> {

    public ClientPolarBearRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    protected void applyScale(ClientPolarBear livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public Identifier getTextureLocation(ClientPolarBear livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/bear/polarbear.png");
    }
}
