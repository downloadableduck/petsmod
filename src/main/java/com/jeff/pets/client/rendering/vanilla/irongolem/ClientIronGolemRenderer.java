package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;

import org.jetbrains.annotations.NotNull;

public class ClientIronGolemRenderer extends PetRenderer<ClientIronGolem> {

    public ClientIronGolemRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientIronGolemModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientIronGolem livingEntityRenderState = (ClientIronGolem) entity;
        return ("textures/mob/iron_golem.png");
    }
}
