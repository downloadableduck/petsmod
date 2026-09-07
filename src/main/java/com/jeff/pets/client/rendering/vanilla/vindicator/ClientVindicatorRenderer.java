package com.jeff.pets.client.rendering.vanilla.vindicator;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.mob.vanilla.hostile.ClientVindicator;
import net.minecraft.util.ResourceLocation;

public class ClientVindicatorRenderer extends PetRenderer<ClientVindicator, ClientEvokerModel> {

    public ClientVindicatorRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEvokerModel(0, 0, 64, 64), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientVindicator livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vindicator.png");
    }
}
