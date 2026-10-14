package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.render.model.entity.SilverfishModel;

import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<ClientSilverfish> {

    public ClientSilverfishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SilverfishModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientSilverfish livingEntityRenderState = (ClientSilverfish) entity;
        return ("textures/mob/silverfish.png");
    }
}
