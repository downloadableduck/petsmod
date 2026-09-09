package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.animal.turtle.AdultTurtleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.TurtleRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientTurtleRenderer extends PetRenderer<@NotNull ClientTurtle, @NotNull TurtleRenderState, @NotNull AdultTurtleModel> {
    public static final ModelLayerLocation TURTLE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientturtle"), "main");

    public ClientTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultTurtleModel(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
    }

    @Override
    public TurtleRenderState createRenderState() {
        return new TurtleRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(TurtleRenderState turtleRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/turtle/turtle.png");
    }
}

