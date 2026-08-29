package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.renderer.entity.model.ModelWitch;
import net.minecraft.util.ResourceLocation;

public class ClientWitchRenderer extends PetRenderer<ClientWitch, ModelWitch> {

    public ClientWitchRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelWitch(0), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientWitch livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/witch.png");
    }
}
