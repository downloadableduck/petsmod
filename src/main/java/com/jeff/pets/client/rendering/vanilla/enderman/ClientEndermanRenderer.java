package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.render.model.entity.EndermanModel;

import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer<ClientEnderman> {

    public ClientEndermanRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermanModel(), 0.5f);
        this.addLayer(new EndermanEyesLayer(this));
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientEnderman enderman = (ClientEnderman) entity;
        return ("textures/mob/enderman/enderman.png");
    }
}
