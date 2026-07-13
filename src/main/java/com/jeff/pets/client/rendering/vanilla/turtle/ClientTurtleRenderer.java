package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.util.ResourceLocation;

public class ClientTurtleRenderer extends PetRenderer<ClientTurtle, ClientTurtleModel> {

    public ClientTurtleRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientTurtleModel(0), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientTurtle turtleRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/turtle/big_sea_turtle.png");
    }
}

