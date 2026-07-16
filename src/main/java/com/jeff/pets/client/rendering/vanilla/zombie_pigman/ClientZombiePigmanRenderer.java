package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;

public class ClientZombiePigmanRenderer extends PetRenderer<ClientZombiePigman, ClientZombiePigmanModel> {
    public ClientZombiePigmanRenderer(EntityRenderDispatcher context, EntityRendererRegistry.Context content2) {
        super(context, new ClientZombiePigmanModel(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientZombiePigman entity) {
        return new ResourceLocation("minecraft", "textures/entity/zombie_pigman.png");
    }
}
