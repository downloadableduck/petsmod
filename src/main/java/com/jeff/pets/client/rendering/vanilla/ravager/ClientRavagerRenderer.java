package com.jeff.pets.client.rendering.vanilla.ravager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import net.minecraft.util.ResourceLocation;

public class ClientRavagerRenderer extends PetRenderer<ClientRavager, ClientRavagerModel> {

    public ClientRavagerRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientRavagerModel(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientRavager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/ravager.png");
    }
}
