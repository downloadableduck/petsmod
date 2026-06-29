package com.jeff.pets.rendering.vanilla.phantom;

import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPhantomRenderer extends PetRenderer<@NotNull ClientPhantom, @NotNull ClientPhantomModel> {

    public static final ModelLayerLocation PHANTOM_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientphantom"), "main");

    public ClientPhantomRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPhantomModel(context.bakeLayer(ModelLayers.PHANTOM)), 0.75f);
        this.addLayer(new PhantomEyesLayer(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPhantom livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/phantom.png");
    }

    /*@Override
    public void extractRenderState(ClientPhantom phantom, PhantomRenderState state, float f) {
        super.extractRenderState(phantom, state, f);
        phantom.flapTime = phantom.getId() * 3 + state.ageInTicks;
    }*/
}
