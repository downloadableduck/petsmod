package com.jeff.pets.vanilla.vindicator;

import com.jeff.pets.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.vanilla.hostile.ClientVindicator;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVindicatorRenderer extends MobRenderer<@NotNull ClientVindicator, @NotNull EvokerRenderState, @NotNull ClientEvokerModel> {

    public static final ModelLayerLocation VINDICATOR_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientvindicator"), "main");

    public ClientVindicatorRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEvokerModel(context.bakeLayer(ModelLayers.VINDICATOR)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(EvokerRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/illager/vindicator.png");
    }

    @Override
    public EvokerRenderState createRenderState() {
        return new EvokerRenderState();
    }
}
