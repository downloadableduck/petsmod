package com.jeff.pets.rendering.vanilla.turtle;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.TurtleRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientTurtleRenderer extends PetRenderer<@NotNull ClientTurtle, @NotNull TurtleRenderState, @NotNull TurtleModel> {
    public static final ModelLayerLocation TURTLE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientturtle"), "main");

    public ClientTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new TurtleModel(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
    }

    @Override
    public TurtleRenderState createRenderState() {
        return new TurtleRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(TurtleRenderState turtleRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/turtle/big_sea_turtle.png");
    }
}

