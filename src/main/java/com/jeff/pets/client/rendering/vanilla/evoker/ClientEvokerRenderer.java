package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEvokerRenderer extends PetRenderer<@NotNull ClientEvoker, @NotNull EvokerRenderState, @NotNull ClientEvokerModel> {
    public static final ModelLayerLocation EVOKER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientevoker"), "main");

    public ClientEvokerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEvokerModel(context.bakeLayer(ModelLayers.EVOKER)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(EvokerRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/illager/evoker.png");
    }

    @Override
    public EvokerRenderState createRenderState() {
        return new EvokerRenderState();
    }
}
