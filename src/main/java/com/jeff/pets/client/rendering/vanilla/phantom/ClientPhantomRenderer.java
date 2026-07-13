package com.jeff.pets.client.rendering.vanilla.phantom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.client.renderer.entity.model.PhantomModel;
import net.minecraft.util.ResourceLocation;

public class ClientPhantomRenderer extends PetRenderer<ClientPhantom, PhantomModel<ClientPhantom>> {

    public ClientPhantomRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new PhantomModel<>(), 0.75f);
        this.addLayer(new PhantomEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ClientPhantom livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/phantom.png");
    }

    /*@Override
    public void extractRenderState(ClientPhantom phantom, PhantomRenderState state, float f) {
        super.extractRenderState(phantom, state, f);
        phantom.flapTime = phantom.getId() * 3 + state.ageInTicks;
    }*/
}
