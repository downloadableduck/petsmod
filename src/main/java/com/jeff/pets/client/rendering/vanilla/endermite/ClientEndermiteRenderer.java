package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.renderer.entity.model.EndermiteModel;
import net.minecraft.util.ResourceLocation;

public class ClientEndermiteRenderer extends PetRenderer<ClientEndermite, EndermiteModel<ClientEndermite>> {

    public ClientEndermiteRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermiteModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientEndermite livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/endermite.png");
    }
}
