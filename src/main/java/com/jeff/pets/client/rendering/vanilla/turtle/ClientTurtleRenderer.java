package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientTurtleRenderer extends PetRenderer< ClientTurtle,  ClientTurtleModel> {
    public static final ModelLayerLocation TURTLE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientturtle"), "main");

    public ClientTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientTurtleModel(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientTurtle turtleRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/turtle/big_sea_turtle.png");
    }
}

