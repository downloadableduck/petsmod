package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.render.model.entity.WitchModel;

import org.jetbrains.annotations.NotNull;

public class ClientWitchRenderer extends PetRenderer<ClientWitch> {

    public ClientWitchRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new WitchModel(0), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientWitch livingEntityRenderState = (ClientWitch) entity;
        return ("textures/mob/witch.png");
    }
}
