package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWolfRenderer extends PetRenderer {

    public ClientWolfRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    public void renderModel(ClientWolf wolf, float f, float g, float h, float i, float j, float k) {
        super.renderModel(wolf, f, g, h, i, j, k);
        wolf.setSitting(wolf.vehicle != null);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientWolf livingEntityRenderState = (ClientWolf) __e;
        return new Identifier("minecraft", "textures/entity/wolf/wolf.png");
    }
}