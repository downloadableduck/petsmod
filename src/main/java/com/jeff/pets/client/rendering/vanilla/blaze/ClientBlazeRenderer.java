package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.renderer.entity.model.BlazeModel;
import net.minecraft.util.ResourceLocation;

public class ClientBlazeRenderer extends PetRenderer<ClientBlaze, BlazeModel<ClientBlaze>> {

    public ClientBlazeRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new BlazeModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientBlaze livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/blaze.png");
    }
}
