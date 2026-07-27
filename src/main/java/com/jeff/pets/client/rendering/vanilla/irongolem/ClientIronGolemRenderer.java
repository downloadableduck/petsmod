package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import net.minecraft.util.ResourceLocation;

public class ClientIronGolemRenderer extends PetRenderer<ClientIronGolem, ClientIronGolemModel<ClientIronGolem>> {

    public ClientIronGolemRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientIronGolemModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientIronGolem livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/iron_golem/iron_golem.png");
    }
}
