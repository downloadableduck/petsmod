package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.ghast.GhastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientGhastRenderer extends PetRenderer<@NotNull ClientGhast, @NotNull GhastRenderState, @NotNull GhastModel> {

    public static final ModelLayerLocation GHAST_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientghast"), "main");

    public ClientGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel(context.bakeLayer(ModelLayers.GHAST)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(GhastRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/ghast/ghast.png");
    }

    @Override
    public GhastRenderState createRenderState() {
        return new GhastRenderState();
    }
}
