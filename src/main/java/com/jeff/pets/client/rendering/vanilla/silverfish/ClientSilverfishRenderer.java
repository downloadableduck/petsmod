package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.renderer.entity.model.SilverfishModel;
import net.minecraft.util.ResourceLocation;

public class ClientSilverfishRenderer extends PetRenderer<ClientSilverfish, SilverfishModel<ClientSilverfish>> {

    public ClientSilverfishRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SilverfishModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSilverfish livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/silverfish.png");
    }
}
