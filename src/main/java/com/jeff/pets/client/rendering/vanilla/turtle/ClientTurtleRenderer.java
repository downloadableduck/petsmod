package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientTurtleRenderer extends PetRenderer<@NotNull ClientTurtle, @NotNull ClientTurtleModel> {

    public ClientTurtleRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientTurtleModel(0), 0.7F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientTurtle turtleRenderState) {
        return new Identifier("minecraft", "textures/entity/turtle/big_sea_turtle.png");
    }
}

