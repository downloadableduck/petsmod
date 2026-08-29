package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.renderer.entity.model.ModelBlaze;
import net.minecraft.util.ResourceLocation;

public class ClientBlazeRenderer extends PetRenderer<ClientBlaze, ModelBlaze> {

    public ClientBlazeRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelBlaze(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientBlaze livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/blaze.png");
    }
}
