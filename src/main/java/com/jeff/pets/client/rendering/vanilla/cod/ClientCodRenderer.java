package com.jeff.pets.client.rendering.vanilla.cod;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCod;
import net.minecraft.client.renderer.entity.model.CodModel;
import net.minecraft.util.ResourceLocation;

public class ClientCodRenderer extends PetRenderer<ClientCod, CodModel<ClientCod>> {

    public ClientCodRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new CodModel(), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientCod livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/cod.png");
    }
}
