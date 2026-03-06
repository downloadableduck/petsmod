package com.jeff.pets.vanilla.turtle;

import com.jeff.pets.vanilla.passive.ClientTurtle;
import net.minecraft.client.model.animal.turtle.TurtleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.TurtleRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientTurtleRenderer extends MobRenderer<@NotNull ClientTurtle, @NotNull TurtleRenderState, @NotNull TurtleModel> {
    public static final ModelLayerLocation TURTLE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientturtle"), "main");

    public String turtleTexturePath = "textures/entity/turtle/big_sea_turtle.png";

    public ClientTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new TurtleModel(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
    }

    public TurtleRenderState createRenderState() {
        return new TurtleRenderState();
    }

    public @NotNull Identifier getTextureLocation(TurtleRenderState turtleRenderState) {
        return Identifier.withDefaultNamespace(turtleTexturePath);
    }
}

