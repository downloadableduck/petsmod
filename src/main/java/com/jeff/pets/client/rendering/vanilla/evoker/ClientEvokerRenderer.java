package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.util.ResourceLocation;

public class ClientEvokerRenderer extends PetRenderer<ClientEvoker, ClientEvokerModel> {

    public ClientEvokerRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEvokerModel(0, 0, 64, 64), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientEvoker livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/evoker.png");
    }
}
