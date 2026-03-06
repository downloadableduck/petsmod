package com.jeff.pets.vanilla.goat;

import com.jeff.pets.vanilla.neutral.ClientGoat;
import net.minecraft.client.model.animal.goat.GoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.GoatRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientGoatRenderer extends MobRenderer<@NotNull ClientGoat, @NotNull GoatRenderState, @NotNull GoatModel> {

    public static final ModelLayerLocation GOAT_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientgoat"), "main");

    public ClientGoatRenderer(EntityRendererProvider.Context context) {
        super(context, new GoatModel(context.bakeLayer(ModelLayers.GOAT)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(GoatRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/goat/goat.png");
    }

    @Override
    public GoatRenderState createRenderState() {
        return new GoatRenderState();
    }
}
