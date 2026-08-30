package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.resource.Identifier;

public class ClientZombiePigmanRenderer extends PetRenderer<ClientZombiePigman> {
    public ClientZombiePigmanRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientZombiePigmanModel(), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(ClientZombiePigman entity) {
        return new Identifier("minecraft", "textures/entity/zombie_pigman.png");
    }
}
