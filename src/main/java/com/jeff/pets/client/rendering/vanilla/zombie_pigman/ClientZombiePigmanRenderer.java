package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.resource.Identifier;

public class ClientZombiePigmanRenderer extends PetRenderer<ClientZombiePigman, ClientZombiePigmanModel> {
    public ClientZombiePigmanRenderer(EntityRenderDispatcher context, EntityRendererRegistry.Context content2) {
        super(context, new ClientZombiePigmanModel(), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(ClientZombiePigman entity) {
        return new Identifier("minecraft", "textures/entity/zombie_pigman.png");
    }
}
