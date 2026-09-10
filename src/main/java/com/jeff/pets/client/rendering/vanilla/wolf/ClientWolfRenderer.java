package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWolfRenderer extends PetRenderer<@NotNull ClientWolf> {

    public ClientWolfRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    public void renderModel(ClientWolf wolf, float f, float g, float h, float i, float j, float k) {
        super.renderModel(wolf, f, g, h, i, j, k);
        wolf.setSitting(wolf.getVehicle() != null);
    }

    @Override
    public @NotNull Identifier getTexture(ClientWolf livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/wolf/wolf.png");
    }
}