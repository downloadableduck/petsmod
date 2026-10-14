package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;

import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<ClientEndermite> {

    public ClientEndermiteRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermiteModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientEndermite livingEntityRenderState = (ClientEndermite) entity;
        return ("textures/mob/endermite.png");
    }
}
