package com.jeff.pets.client.rendering.vanilla.phantom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import net.minecraft.resource.Identifier;
import net.minecraft.unmapped.C_15553557;
import net.minecraft.unmapped.C_58555521;
import org.jetbrains.annotations.NotNull;

public class ClientPhantomRenderer extends PetRenderer<@NotNull ClientPhantom, @NotNull C_58555521<ClientPhantom>> {

    public ClientPhantomRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new C_58555521<>(), 0.75f);
        this.addLayer(new C_15553557(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientPhantom livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/phantom.png");
    }

    /*@Override
    public void extractRenderState(ClientPhantom phantom, PhantomRenderState state, float f) {
        super.extractRenderState(phantom, state, f);
        phantom.flapTime = phantom.getId() * 3 + state.ageInTicks;
    }*/
}
