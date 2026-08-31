package com.jeff.pets.client.rendering.vanilla.phantom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import net.minecraft.client.renderer.entity.RenderPhantom;
import net.minecraft.client.renderer.entity.model.ModelPhantom;
import net.minecraft.util.ResourceLocation;

public class ClientPhantomRenderer extends PetRenderer<ClientPhantom, ModelPhantom> {

    public ClientPhantomRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelPhantom(), 0.75f);
        this.addLayer(new LayerPhantomEyes(this));
    }

    @Override
    public ResourceLocation getEntityTexture(ClientPhantom livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/phantom.png");
    }

    /*@Override
    public void extractRenderState(ClientPhantom phantom, PhantomRenderState state, float f) {
        super.extractRenderState(phantom, state, f);
        phantom.flapTime = phantom.getId() * 3 + state.ageInTicks;
    }*/
}
