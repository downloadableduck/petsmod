package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.renderer.entity.model.ModelEnderMite;
import net.minecraft.util.ResourceLocation;

public class ClientEndermiteRenderer extends PetRenderer<ClientEndermite, ModelEnderMite> {

    public ClientEndermiteRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelEnderMite(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientEndermite livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/endermite.png");
    }
}
