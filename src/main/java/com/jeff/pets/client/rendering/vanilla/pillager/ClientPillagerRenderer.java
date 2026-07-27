package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.util.ResourceLocation;

public class ClientPillagerRenderer extends PetRenderer<ClientPillager, ClientPillagerModel> {

    public ClientPillagerRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPillagerModel(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientPillager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/pillager.png");
    }
}
