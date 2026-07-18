package com.jeff.pets.client.rendering.vanilla.phantom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import net.minecraft.client.render.entity.feature.PhantomEyesFeatureRenderer;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPhantomRenderer extends PetRenderer<@NotNull ClientPhantom, @NotNull PhantomEntityModel<ClientPhantom>> {

    public ClientPhantomRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new PhantomEntityModel<>(), 0.75f);
        this.addFeature(new PhantomEyesFeatureRenderer(this));
    }

    @Override
    public @NotNull Identifier getTexture(ClientPhantom livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/phantom.png");
    }

    /*@Override
    public void extractRenderState(ClientPhantom phantom, PhantomRenderState state, float f) {
        super.extractRenderState(phantom, state, f);
        phantom.flapTime = phantom.getId() * 3 + state.ageInTicks;
    }*/
}
