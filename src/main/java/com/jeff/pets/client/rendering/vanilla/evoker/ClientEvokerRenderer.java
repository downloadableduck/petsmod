package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.util.ResourceLocation;

public class ClientEvokerRenderer extends PetRenderer<ClientEvoker, ClientEvokerModel<ClientEvoker>> {

    public ClientEvokerRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientEvokerModel(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientEvoker livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/evoker.png");
    }
}
