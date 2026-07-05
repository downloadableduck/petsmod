package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientTurtleRenderer extends PetRenderer<@NotNull ClientTurtle, @NotNull ClientTurtleModel> {
    public static final ModelLayerLocation TURTLE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientturtle"), "main");

    public ClientTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientTurtleModel(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientTurtle turtleRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/turtle/big_sea_turtle.png");
    }
}

