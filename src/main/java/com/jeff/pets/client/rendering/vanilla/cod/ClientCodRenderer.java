package com.jeff.pets.client.rendering.vanilla.cod;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCod;
import net.minecraft.client.renderer.entity.model.ModelCod;
import net.minecraft.util.ResourceLocation;

public class ClientCodRenderer extends PetRenderer<ClientCod, ModelCod> {

    public ClientCodRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelCod(), 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientCod livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/cod.png");
    }
}
