package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.renderer.entity.model.WitchModel;
import net.minecraft.util.ResourceLocation;

public class ClientWitchRenderer extends PetRenderer<ClientWitch, WitchModel<ClientWitch>> {

    public ClientWitchRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new WitchModel<>(0), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientWitch livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/witch.png");
    }
}
