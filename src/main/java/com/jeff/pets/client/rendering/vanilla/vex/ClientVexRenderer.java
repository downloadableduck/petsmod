package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.util.ResourceLocation;

public class ClientVexRenderer extends PetRenderer<ClientVex, ClientVexModel> {

    public ClientVexRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientVexModel(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientVex livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vex.png");
    }
}
