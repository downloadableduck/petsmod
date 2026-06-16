package com.jeff.pets.rendering.vanilla.ghast;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientGhastRenderer extends PetRenderer<@NotNull ClientGhast, @NotNull GhastRenderState, @NotNull GhastModel> {

    public static final ModelLayerLocation GHAST_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientghast"), "main");

    public ClientGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel(context.bakeLayer(ModelLayers.GHAST)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GhastRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast.png");
    }

    @Override
    public GhastRenderState createRenderState() {
        return new GhastRenderState();
    }
}
